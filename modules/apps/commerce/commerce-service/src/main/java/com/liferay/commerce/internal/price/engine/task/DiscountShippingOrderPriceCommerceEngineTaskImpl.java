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

import com.liferay.commerce.configuration.PriceCommerceEngineTaskConfiguration;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.internal.util.CommercePriceConverterUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.price.engine.task.OrderPriceCommerceEngineTaskContext;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"commerce.engine.task.key=" + DiscountShippingOrderPriceCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + DiscountShippingOrderPriceCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + DiscountShippingOrderPriceCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class DiscountShippingOrderPriceCommerceEngineTaskImpl
	extends BaseOrderPriceCommerceEngineTask {

	public static final String KEY = "discount-shipping-price";

	public static final int PRIORITY = 15;

	public static final String TYPE = "order-pricing";

	@Override
	public boolean evaluate(
		long groupId,
		OrderPriceCommerceEngineTaskContext
			orderPriceCommerceEngineTaskContext) {

		try {
			PriceCommerceEngineTaskConfiguration
				priceCommerceEngineTaskConfiguration =
					getCommerceEnginePriceConfiguration(groupId);

			return evaluate(
				priceCommerceEngineTaskConfiguration.
					discountShippingPriceEvaluateCondition(),
				orderPriceCommerceEngineTaskContext);
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
			OrderPriceCommerceEngineTaskContext
				orderPriceCommerceEngineTaskContext)
		throws Exception {

		BigDecimal shippingAmount =
			orderPriceCommerceEngineTaskContext.getShippingAmount();
		BigDecimal shippingWithTaxAmount =
			orderPriceCommerceEngineTaskContext.getShippingWithTaxAmount();

		CommerceContext commerceContext =
			orderPriceCommerceEngineTaskContext.getCommerceContext();

		CommerceOrder commerceOrder =
			orderPriceCommerceEngineTaskContext.getCommerceOrder();

		boolean discountsTargetNetPrice = true;

		CommerceChannel commerceChannel =
			commerceChannelLocalService.fetchCommerceChannel(
				commerceContext.getCommerceChannelId());

		if (commerceChannel != null) {
			discountsTargetNetPrice =
				commerceChannel.isDiscountsTargetNetPrice();
		}

		if (discountsTargetNetPrice) {
			_applyShippingCommerceDiscount(
				commerceOrder, shippingAmount,
				orderPriceCommerceEngineTaskContext, discountsTargetNetPrice);
		}
		else {
			_applyShippingCommerceDiscount(
				commerceOrder, shippingWithTaxAmount,
				orderPriceCommerceEngineTaskContext, discountsTargetNetPrice);
		}
	}

	@Override
	public String getEvaluateCondition(long groupId) {
		try {
			PriceCommerceEngineTaskConfiguration
				priceCommerceEngineTaskConfiguration =
					getCommerceEnginePriceConfiguration(groupId);

			return priceCommerceEngineTaskConfiguration.
				discountShippingPriceEvaluateCondition();
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
					getCommerceEnginePriceConfiguration(groupId);

			return priceCommerceEngineTaskConfiguration.discountShippingPrice();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return true;
	}

	private void _applyShippingCommerceDiscount(
			CommerceOrder commerceOrder, BigDecimal amount,
			OrderPriceCommerceEngineTaskContext
				orderPriceCommerceEngineTaskContext,
			boolean discountsTargetNetPrice)
		throws Exception {

		CommerceContext commerceContext =
			orderPriceCommerceEngineTaskContext.getCommerceContext();

		CommerceDiscountValue orderShippingCommerceDiscountValue =
			commerceDiscountCalculation.getOrderShippingCommerceDiscountValue(
				commerceOrder, amount, commerceContext);

		BigDecimal shippingDiscountedAmount = amount;

		if (orderShippingCommerceDiscountValue != null) {
			CommerceMoney orderShippingCommerceDiscountValueDiscountAmount =
				orderShippingCommerceDiscountValue.getDiscountAmount();

			shippingDiscountedAmount = amount.subtract(
				orderShippingCommerceDiscountValueDiscountAmount.getPrice());
		}

		CommerceCurrency commerceCurrency =
			commerceContext.getCommerceCurrency();

		RoundingMode roundingMode = RoundingMode.valueOf(
			commerceCurrency.getRoundingMode());

		BigDecimal shippingTaxAmount;

		if (discountsTargetNetPrice) {
			BigDecimal shippingWithTaxAmount =
				orderPriceCommerceEngineTaskContext.getShippingWithTaxAmount();

			orderPriceCommerceEngineTaskContext.setShippingDiscountedAmount(
				shippingDiscountedAmount);

			BigDecimal convertedShippingDiscountedAmount =
				_getConvertedShippingAmount(
					commerceOrder, shippingDiscountedAmount, false);

			orderPriceCommerceEngineTaskContext.
				setShippingDiscountedWithTaxAmount(
					convertedShippingDiscountedAmount);

			shippingTaxAmount = convertedShippingDiscountedAmount.subtract(
				shippingDiscountedAmount);

			orderPriceCommerceEngineTaskContext.
				setShippingCommerceDiscountValue(
					orderShippingCommerceDiscountValue);

			orderPriceCommerceEngineTaskContext.
				setShippingCommerceDiscountValueWithTaxAmount(
					CommercePriceConverterUtil.
						getConvertedCommerceDiscountValue(
							orderShippingCommerceDiscountValue,
							shippingWithTaxAmount,
							convertedShippingDiscountedAmount,
							commerceMoneyFactory, roundingMode));
		}
		else {
			BigDecimal shippingAmount =
				orderPriceCommerceEngineTaskContext.getShippingAmount();

			BigDecimal convertedShippingDiscountedAmount =
				_getConvertedShippingAmount(
					commerceOrder, shippingDiscountedAmount, true);

			orderPriceCommerceEngineTaskContext.setShippingDiscountedAmount(
				convertedShippingDiscountedAmount);

			orderPriceCommerceEngineTaskContext.
				setShippingDiscountedWithTaxAmount(shippingDiscountedAmount);

			shippingTaxAmount = shippingDiscountedAmount.subtract(
				convertedShippingDiscountedAmount);

			orderPriceCommerceEngineTaskContext.
				setShippingCommerceDiscountValue(
					CommercePriceConverterUtil.
						getConvertedCommerceDiscountValue(
							orderShippingCommerceDiscountValue, shippingAmount,
							convertedShippingDiscountedAmount,
							commerceMoneyFactory, roundingMode));

			orderPriceCommerceEngineTaskContext.
				setShippingCommerceDiscountValueWithTaxAmount(
					orderShippingCommerceDiscountValue);
		}

		orderPriceCommerceEngineTaskContext.setShippingTaxAmount(
			shippingTaxAmount);
	}

	private BigDecimal _getConvertedShippingAmount(
			CommerceOrder commerceOrder, BigDecimal amount, boolean includeTax)
		throws Exception {

		return amount;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DiscountShippingOrderPriceCommerceEngineTaskImpl.class);

}