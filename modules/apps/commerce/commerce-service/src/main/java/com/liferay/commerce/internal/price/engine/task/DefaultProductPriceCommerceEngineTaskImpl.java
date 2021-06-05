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
import com.liferay.commerce.price.engine.task.ProductPriceCommerceEngineTaskContext;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.math.BigDecimal;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"commerce.engine.task.key=" + DefaultProductPriceCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + DefaultProductPriceCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + DefaultProductPriceCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class DefaultProductPriceCommerceEngineTaskImpl
	extends BaseProductPriceCommerceEngineTask {

	public static final String KEY = "base-price";

	public static final int PRIORITY = 10;

	public static final String TYPE = "product-pricing";

	@Override
	public boolean evaluate(
		long groupId,
		ProductPriceCommerceEngineTaskContext
			productPriceCommerceEngineTaskContext) {

		return true;
	}

	@Override
	public void execute(
			ProductPriceCommerceEngineTaskContext
				productPriceCommerceEngineTaskContext)
		throws Exception {

		long cpInstanceId =
			productPriceCommerceEngineTaskContext.getCpInstanceId();

		int quantity = productPriceCommerceEngineTaskContext.getQuantity();

		long baseCommercePriceListId = _getBaseCommercePriceListId(
			cpInstanceId);

		CommercePriceList baseCommercePriceList =
			commercePriceListLocalService.fetchCommercePriceList(
				baseCommercePriceListId);

		if (baseCommercePriceList == null) {
			productPriceCommerceEngineTaskContext.setUnitPrice(BigDecimal.ZERO);
			productPriceCommerceEngineTaskContext.setUnitPriceWithTaxAmount(
				BigDecimal.ZERO);
			productPriceCommerceEngineTaskContext.setFinalUnitPrice(
				BigDecimal.ZERO);
			productPriceCommerceEngineTaskContext.
				setFinalUnitPriceWithTaxAmount(BigDecimal.ZERO);

			productPriceCommerceEngineTaskContext.setCommercePriceListId(
				baseCommercePriceListId);
			productPriceCommerceEngineTaskContext.setFinalCommercePriceListId(
				baseCommercePriceListId);

			return;
		}

		CommerceContext commerceContext =
			productPriceCommerceEngineTaskContext.getCommerceContext();

		BigDecimal commercePrice = getCommercePrice(
			baseCommercePriceListId, cpInstanceId, quantity);

		BigDecimal optionValuesPrices = getCommerceOptionValuesPrice(
			commerceContext.getCommerceChannelGroupId(),
			productPriceCommerceEngineTaskContext.getCommerceOptionValues());

		commercePrice = commercePrice.add(optionValuesPrices);

		BigDecimal commercePriceWithTaxAmount = commercePrice;

		if (!baseCommercePriceList.isNetPrice()) {
			commercePrice = getConvertedPrice(
				commerceContext.getCommerceChannelGroupId(), cpInstanceId,
				commercePrice, true, commerceContext.getCommerceAccount(),
				commerceContext.getCommerceOrder());
		}

		commercePrice = convertCommercePriceCurrency(
			commercePrice, baseCommercePriceList.getCommerceCurrency(),
			commerceContext.getCommerceCurrency());

		commercePriceWithTaxAmount = convertCommercePriceCurrency(
			commercePriceWithTaxAmount,
			baseCommercePriceList.getCommerceCurrency(),
			commerceContext.getCommerceCurrency());

		productPriceCommerceEngineTaskContext.setUnitPrice(commercePrice);
		productPriceCommerceEngineTaskContext.setUnitPriceWithTaxAmount(
			commercePriceWithTaxAmount);
		productPriceCommerceEngineTaskContext.setFinalUnitPrice(commercePrice);
		productPriceCommerceEngineTaskContext.setFinalPriceWithTaxAmount(
			commercePriceWithTaxAmount);
		productPriceCommerceEngineTaskContext.setFinalPrice(
			commercePrice.multiply(BigDecimal.valueOf(quantity)));
		productPriceCommerceEngineTaskContext.setFinalPriceWithTaxAmount(
			commercePriceWithTaxAmount.multiply(BigDecimal.valueOf(quantity)));

		productPriceCommerceEngineTaskContext.setCommercePriceListId(
			baseCommercePriceListId);
		productPriceCommerceEngineTaskContext.setFinalCommercePriceListId(
			baseCommercePriceListId);
	}

	@Override
	public String getEvaluateCondition(long groupId) {
		return StringPool.BLANK;
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

	private long _getBaseCommercePriceListId(long cpInstanceId)
		throws Exception {

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CommerceCatalog commerceCatalog = cpInstance.getCommerceCatalog();

		CommercePriceList basePriceList =
			commercePriceListLocalService.fetchCatalogBaseCommercePriceList(
				commerceCatalog.getGroupId());

		if (basePriceList != null) {
			return basePriceList.getCommercePriceListId();
		}

		_log.error(
			"There is no base price list configured for the current catalog");

		return 0;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DefaultProductPriceCommerceEngineTaskImpl.class);

}