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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.configuration.PriceCommerceEngineTaskConfiguration;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.price.engine.task.ProductPriceCommerceEngineTaskContext;
import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.util.CommerceBigDecimalUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
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
	public boolean evaluate(
		long groupId,
		ProductPriceCommerceEngineTaskContext
			productPriceCommerceEngineTaskContext) {

		try {
			PriceCommerceEngineTaskConfiguration
				priceCommerceEngineTaskConfiguration =
					getCommerceEngineTaskConfiguration(groupId);

			return evaluate(
				priceCommerceEngineTaskConfiguration.
					promotionPriceEvaluateCondition(),
				productPriceCommerceEngineTaskContext);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

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

		CommerceContext commerceContext =
			productPriceCommerceEngineTaskContext.getCommerceContext();

		long commercePromoPriceListId = _getCommercePromoPriceListId(
			commerceContext.getCommerceChannelId(), cpInstanceId,
			commerceContext.getCommerceAccount());

		CommercePriceList commercePromoPriceList =
			commercePriceListLocalService.fetchCommercePriceList(
				commercePromoPriceListId);

		if (commercePromoPriceList == null) {
			productPriceCommerceEngineTaskContext.setPromoPrice(
				BigDecimal.ZERO);
			productPriceCommerceEngineTaskContext.setCommercePromoPriceListId(
				0);

			return;
		}

		BigDecimal commercePrice = getCommercePrice(
			commercePromoPriceListId, cpInstanceId, quantity);

		BigDecimal optionValuesPrices = getCommerceOptionValuesPrice(
			commerceContext.getCommerceChannelGroupId(),
			productPriceCommerceEngineTaskContext.getCommerceOptionValues());

		if (commercePrice.compareTo(BigDecimal.ZERO) > 0) {
			commercePrice = commercePrice.add(optionValuesPrices);
		}

		BigDecimal commercePriceWithTaxAmount = commercePrice;

		if (!commercePromoPriceList.isNetPrice()) {
			commercePrice = getConvertedPrice(
				commerceContext.getCommerceChannelGroupId(), cpInstanceId,
				commercePrice, true, commerceContext.getCommerceAccount(),
				commerceContext.getCommerceOrder());
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
						productPriceCommerceEngineTaskContext.getUnitPrice()));
		}

		commercePrice = convertCommercePriceCurrency(
			commercePrice, commercePromoPriceList.getCommerceCurrency(),
			commerceContext.getCommerceCurrency());

		commercePriceWithTaxAmount = convertCommercePriceCurrency(
			commercePriceWithTaxAmount,
			commercePromoPriceList.getCommerceCurrency(),
			commerceContext.getCommerceCurrency());

		productPriceCommerceEngineTaskContext.setPromoPrice(commercePrice);
		productPriceCommerceEngineTaskContext.setPromoPriceWithTaxAmount(
			commercePriceWithTaxAmount);

		productPriceCommerceEngineTaskContext.setCommercePromoPriceListId(
			commercePromoPriceListId);

		BigDecimal unitCommercePrice =
			productPriceCommerceEngineTaskContext.getUnitPrice();

		if (CommerceBigDecimalUtil.gt(commercePrice, BigDecimal.ZERO) &&
			CommerceBigDecimalUtil.lte(commercePrice, unitCommercePrice)) {

			productPriceCommerceEngineTaskContext.setFinalUnitPrice(
				commercePrice);
			productPriceCommerceEngineTaskContext.
				setFinalUnitPriceWithTaxAmount(commercePriceWithTaxAmount);
			productPriceCommerceEngineTaskContext.setFinalPrice(
				commercePrice.multiply(BigDecimal.valueOf(quantity)));
			productPriceCommerceEngineTaskContext.setFinalPriceWithTaxAmount(
				commercePriceWithTaxAmount.multiply(
					BigDecimal.valueOf(quantity)));
		}
	}

	@Override
	public String getEvaluateCondition(long groupId) {
		try {
			PriceCommerceEngineTaskConfiguration
				priceCommerceEngineTaskConfiguration =
					getCommerceEngineTaskConfiguration(groupId);

			return priceCommerceEngineTaskConfiguration.
				promotionPriceEvaluateCondition();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

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

	@Override
	public boolean isActive(long groupId) {
		try {
			PriceCommerceEngineTaskConfiguration
				priceCommerceEngineTaskConfiguration =
					getCommerceEngineTaskConfiguration(groupId);

			return priceCommerceEngineTaskConfiguration.promotionPrice();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return true;
	}

	private long _getCommercePromoPriceListId(
			long commerceChannelId, long cpInstanceId,
			CommerceAccount commerceAccount)
		throws Exception {

		CommercePriceList commercePriceList = getCommercePriceList(
			commerceChannelId, cpInstanceId,
			CommercePriceListConstants.TYPE_PROMOTION, commerceAccount);

		if (commercePriceList != null) {
			return commercePriceList.getCommercePriceListId();
		}

		return 0;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PromotionProductPriceCommerceEngineTaskImpl.class);

}