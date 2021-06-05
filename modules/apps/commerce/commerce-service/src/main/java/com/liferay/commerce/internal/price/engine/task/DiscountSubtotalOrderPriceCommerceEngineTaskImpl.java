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
		"commerce.engine.task.key=" + DiscountSubtotalOrderPriceCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + DiscountSubtotalOrderPriceCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + DiscountSubtotalOrderPriceCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class DiscountSubtotalOrderPriceCommerceEngineTaskImpl
	extends BaseOrderPriceCommerceEngineTask {

	public static final String KEY = "discount-subtotal-price";

	public static final int PRIORITY = 25;

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
					discountSubtotalPriceEvaluateCondition(),
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

		BigDecimal subtotalAmount =
			orderPriceCommerceEngineTaskContext.getSubtotalAmount();
		BigDecimal subtotalWithTaxAmount =
			orderPriceCommerceEngineTaskContext.getSubtotalWithTaxAmount();

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
			_applySubtotalCommerceDiscount(
				commerceOrder, subtotalAmount,
				orderPriceCommerceEngineTaskContext, discountsTargetNetPrice);
		}
		else {
			_applySubtotalCommerceDiscount(
				commerceOrder, subtotalWithTaxAmount,
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
				discountSubtotalPriceEvaluateCondition();
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

			return priceCommerceEngineTaskConfiguration.discountSubtotalPrice();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return true;
	}

	private void _applySubtotalCommerceDiscount(
			CommerceOrder commerceOrder, BigDecimal amount,
			OrderPriceCommerceEngineTaskContext
				orderPriceCommerceEngineTaskContext,
			boolean discountsTargetNetPrice)
		throws Exception {

		CommerceContext commerceContext =
			orderPriceCommerceEngineTaskContext.getCommerceContext();

		CommerceDiscountValue orderSubtotalCommerceDiscountValue =
			commerceDiscountCalculation.getOrderSubtotalCommerceDiscountValue(
				commerceOrder, amount, commerceContext);

		BigDecimal subtotalDiscountedAmount = amount;

		if (orderSubtotalCommerceDiscountValue != null) {
			CommerceMoney orderSubtotalCommerceDiscountValueDiscountAmount =
				orderSubtotalCommerceDiscountValue.getDiscountAmount();

			subtotalDiscountedAmount = amount.subtract(
				orderSubtotalCommerceDiscountValueDiscountAmount.getPrice());
		}

		CommerceCurrency commerceCurrency =
			commerceContext.getCommerceCurrency();

		RoundingMode roundingMode = RoundingMode.valueOf(
			commerceCurrency.getRoundingMode());

		BigDecimal subtotalTaxAmount =
			orderPriceCommerceEngineTaskContext.getSubtotalTaxAmount();

		if (discountsTargetNetPrice) {
			BigDecimal subtotalWithTaxAmount =
				orderPriceCommerceEngineTaskContext.getSubtotalWithTaxAmount();

			orderPriceCommerceEngineTaskContext.setSubtotalDiscountedAmount(
				subtotalDiscountedAmount);

			orderPriceCommerceEngineTaskContext.
				setSubtotalDiscountedWithTaxAmount(
					subtotalDiscountedAmount.add(subtotalTaxAmount));

			orderPriceCommerceEngineTaskContext.
				setSubtotalCommerceDiscountValue(
					orderSubtotalCommerceDiscountValue);

			orderPriceCommerceEngineTaskContext.
				setSubtotalCommerceDiscountValueWithTaxAmount(
					CommercePriceConverterUtil.
						getConvertedCommerceDiscountValue(
							orderSubtotalCommerceDiscountValue,
							subtotalWithTaxAmount,
							subtotalDiscountedAmount.add(subtotalTaxAmount),
							commerceMoneyFactory, roundingMode));
		}
		else {
			BigDecimal subtotalAmount =
				orderPriceCommerceEngineTaskContext.getSubtotalAmount();

			BigDecimal convertedSubtotalDiscountedAmount =
				_getConvertedSubtotalAmount(
					subtotalDiscountedAmount, subtotalTaxAmount);

			orderPriceCommerceEngineTaskContext.setSubtotalDiscountedAmount(
				convertedSubtotalDiscountedAmount);

			orderPriceCommerceEngineTaskContext.
				setSubtotalDiscountedWithTaxAmount(subtotalDiscountedAmount);

			orderPriceCommerceEngineTaskContext.
				setSubtotalCommerceDiscountValue(
					CommercePriceConverterUtil.
						getConvertedCommerceDiscountValue(
							orderSubtotalCommerceDiscountValue, subtotalAmount,
							convertedSubtotalDiscountedAmount,
							commerceMoneyFactory, roundingMode));

			orderPriceCommerceEngineTaskContext.
				setSubtotalCommerceDiscountValueWithTaxAmount(
					orderSubtotalCommerceDiscountValue);
		}
	}

	private BigDecimal _getConvertedSubtotalAmount(
		BigDecimal subtotalDiscountedAmount, BigDecimal subtotalTaxAmount) {

		if (subtotalTaxAmount.compareTo(subtotalDiscountedAmount) <= 0) {
			return subtotalDiscountedAmount.subtract(subtotalTaxAmount);
		}

		return BigDecimal.ZERO;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DiscountSubtotalOrderPriceCommerceEngineTaskImpl.class);

}