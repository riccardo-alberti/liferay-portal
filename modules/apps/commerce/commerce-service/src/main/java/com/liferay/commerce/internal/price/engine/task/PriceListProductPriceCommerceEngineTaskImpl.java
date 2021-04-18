/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.commerce.internal.price.engine.task;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.option.CommerceOptionValue;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.math.BigDecimal;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"commerce.engine.task.key=" + PriceListProductPriceCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + PriceListProductPriceCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + PriceListProductPriceCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class PriceListProductPriceCommerceEngineTaskImpl
	extends BaseProductPriceCommerceEngineTask {

	public static final String KEY = "price-list-price";

	public static final int PRIORITY = 20;

	public static final String TYPE = "product-pricing";

	@Override
	public boolean evaluate(Map<String, Object> context) {
		return true;
	}

	@Override
	public void execute(Map<String, Object> context) throws Exception {
		long cpInstanceId = (Long)context.get("_cpInstanceId");

		int quantity = (Integer)context.get("_quantity");

		CommerceContext commerceContext = (CommerceContext)context.get(
			"_commerceContext");

		long commercePriceListId = _getCommercePriceListId(
			cpInstanceId, commerceContext);

		CommercePriceList commercePriceList =
			commercePriceListLocalService.fetchCommercePriceList(
				commercePriceListId);

		if (commercePriceList == null) {
			return;
		}

		BigDecimal commercePrice = getCommercePrice(
			commercePriceListId, cpInstanceId, quantity);

		BigDecimal optionValuesPrices = getCommerceOptionValuesPrice(
			(List<CommerceOptionValue>)context.get("_commerceOptionValues"),
			commerceContext);

		commercePrice = commercePrice.add(optionValuesPrices);

		BigDecimal commercePriceWithTaxAmount = commercePrice;

		if (!commercePriceList.isNetPrice()) {
			commercePrice = getConvertedPrice(
				cpInstanceId, commercePrice, true, commerceContext);
		}

		if (commercePrice.equals(BigDecimal.ZERO) &&
			hasCommercePriceModifiers(commercePriceListId, cpInstanceId)) {

			CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
				cpInstanceId);

			commercePrice =
				commercePriceModifierHelper.applyCommercePriceModifier(
					commercePriceListId, cpInstance.getCPDefinitionId(),
					commerceMoneyFactory.create(
						commerceContext.getCommerceCurrency(),
						(BigDecimal)context.get("_unitPrice")));
		}

		commercePrice = convertCommercePriceCurrency(
			commercePrice, commercePriceList.getCommerceCurrency(),
			commerceContext.getCommerceCurrency());

		commercePriceWithTaxAmount = convertCommercePriceCurrency(
			commercePriceWithTaxAmount, commercePriceList.getCommerceCurrency(),
			commerceContext.getCommerceCurrency());

		context.put("_unitPrice", commercePrice);
		context.put("_unitPriceWithTaxAmount", commercePriceWithTaxAmount);
		context.put("_finalUnitPrice", commercePrice);
		context.put("_finalUnitPriceWithTaxAmount", commercePriceWithTaxAmount);

		context.put("_commercePriceListId", commercePriceListId);
		context.put("_finalCommercePriceListId", commercePriceListId);
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, KEY);
	}

	@Override
	public Optional<CommerceEngineTask> getNext(Map<String, Object> context)
		throws Exception {

		return Optional.empty();
	}

	@Override
	public int getPriority() {
		return PRIORITY;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean isActive(Map<String, Object> context) {
		return true;
	}

	private long _getCommercePriceListId(
			long cpInstanceId, CommerceContext commerceContext)
		throws Exception {

		CommercePriceList commercePriceList = getCommercePriceList(
			cpInstanceId, commerceContext,
			CommercePriceListConstants.TYPE_PRICE_LIST);

		long commercePriceListId = 0;

		if (commercePriceList != null) {
			commercePriceListId = commercePriceList.getCommercePriceListId();
		}

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryLocalService.fetchCommercePriceEntry(
				commercePriceListId, cpInstance.getCPInstanceUuid(), true);

		if (commercePriceEntry != null) {
			return commercePriceEntry.getCommercePriceListId();
		}

		if (hasCommercePriceModifiers(commercePriceListId, cpInstanceId)) {
			return commercePriceListId;
		}

		return 0;
	}

}