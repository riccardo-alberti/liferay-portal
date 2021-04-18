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
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.option.CommerceOptionValue;
import com.liferay.commerce.util.CommerceBigDecimalUtil;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.math.BigDecimal;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"commerce.engine.task.key=" + PromotionProductPriceCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + PromotionProductPriceCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + PromotionProductPriceCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class PromotionProductPriceCommerceEngineTaskImpl
	extends BaseProductPriceCommerceEngineTask {

	public static final String KEY = "promotion-price";

	public static final int PRIORITY = 30;

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

		long commercePromoPriceListId = _getCommercePromoPriceListId(
			cpInstanceId, commerceContext);

		CommercePriceList commercePromoPriceList =
			commercePriceListLocalService.fetchCommercePriceList(
				commercePromoPriceListId);

		if (commercePromoPriceList == null) {
			context.put("_promoPrice", BigDecimal.ZERO);
			context.put("_commercePromoPriceListId", 0);

			return;
		}

		BigDecimal commercePrice = getCommercePrice(
			commercePromoPriceListId, cpInstanceId, quantity);

		BigDecimal optionValuesPrices = getCommerceOptionValuesPrice(
			(List<CommerceOptionValue>)context.get("_commerceOptionValues"),
			commerceContext);

		if (commercePrice.compareTo(BigDecimal.ZERO) > 0) {
			commercePrice = commercePrice.add(optionValuesPrices);
		}

		BigDecimal commercePriceWithTaxAmount = commercePrice;

		if (!commercePromoPriceList.isNetPrice()) {
			commercePrice = getConvertedPrice(
				cpInstanceId, commercePrice, true, commerceContext);
		}

		if (commercePrice.equals(BigDecimal.ZERO) &&
			hasCommercePriceModifiers(commercePromoPriceListId, cpInstanceId)) {

			CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
				cpInstanceId);

			commercePrice =
				commercePriceModifierHelper.applyCommercePriceModifier(
					commercePromoPriceListId, cpInstance.getCPDefinitionId(),
					commerceMoneyFactory.create(
						commerceContext.getCommerceCurrency(),
						(BigDecimal)context.get("_unitPrice")));
		}

		commercePrice = convertCommercePriceCurrency(
			commercePrice, commercePromoPriceList.getCommerceCurrency(),
			commerceContext.getCommerceCurrency());

		commercePriceWithTaxAmount = convertCommercePriceCurrency(
			commercePriceWithTaxAmount,
			commercePromoPriceList.getCommerceCurrency(),
			commerceContext.getCommerceCurrency());

		context.put("_promoPrice", commercePrice);
		context.put("_promoPriceWithTaxAmount", commercePriceWithTaxAmount);

		context.put("_commercePromoPriceListId", commercePromoPriceListId);

		BigDecimal unitCommercePrice = (BigDecimal)context.get("_unitPrice");

		if (CommerceBigDecimalUtil.gt(commercePrice, BigDecimal.ZERO) &&
			CommerceBigDecimalUtil.lte(commercePrice, unitCommercePrice)) {

			context.put("_finalUnitPrice", commercePrice);
			context.put(
				"_finalUnitPriceWithTaxAmount", commercePriceWithTaxAmount);
		}
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

	private long _getCommercePromoPriceListId(
			long cpInstanceId, CommerceContext commerceContext)
		throws Exception {

		CommercePriceList commercePriceList = getCommercePriceList(
			cpInstanceId, commerceContext,
			CommercePriceListConstants.TYPE_PROMOTION);

		if (commercePriceList != null) {
			return commercePriceList.getCommercePriceListId();
		}

		return 0;
	}

}