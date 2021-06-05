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
		"commerce.engine.task.key=" + DiscountTotalOrderPriceCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + DiscountTotalOrderPriceCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + DiscountTotalOrderPriceCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class DiscountTotalOrderPriceCommerceEngineTaskImpl
	extends BaseOrderPriceCommerceEngineTask {

	public static final String KEY = "discount-total-price";

	public static final int PRIORITY = 35;

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
					discountTotalPriceEvaluateCondition(),
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

		BigDecimal totalAmount =
			orderPriceCommerceEngineTaskContext.getTotalAmount();
		BigDecimal totalWithTaxAmount =
			orderPriceCommerceEngineTaskContext.getTotalWithTaxAmount();

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
			_applyTotalCommerceDiscount(
				commerceOrder, totalAmount, orderPriceCommerceEngineTaskContext,
				discountsTargetNetPrice);
		}
		else {
			_applyTotalCommerceDiscount(
				commerceOrder, totalWithTaxAmount,
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
				discountTotalPriceEvaluateCondition();
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

			return priceCommerceEngineTaskConfiguration.discountTotalPrice();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return true;
	}

	private void _applyTotalCommerceDiscount(
			CommerceOrder commerceOrder, BigDecimal amount,
			OrderPriceCommerceEngineTaskContext
				orderPriceCommerceEngineTaskContext,
			boolean discountsTargetNetPrice)
		throws Exception {

		CommerceContext commerceContext =
			orderPriceCommerceEngineTaskContext.getCommerceContext();

		CommerceDiscountValue orderTotalCommerceDiscountValue =
			commerceDiscountCalculation.getOrderTotalCommerceDiscountValue(
				commerceOrder, amount, commerceContext);

		BigDecimal totalDiscountedAmount = amount;

		if (orderTotalCommerceDiscountValue != null) {
			CommerceMoney orderTotalCommerceDiscountValueDiscountAmount =
				orderTotalCommerceDiscountValue.getDiscountAmount();

			totalDiscountedAmount = amount.subtract(
				orderTotalCommerceDiscountValueDiscountAmount.getPrice());
		}

		CommerceCurrency commerceCurrency =
			commerceContext.getCommerceCurrency();

		RoundingMode roundingMode = RoundingMode.valueOf(
			commerceCurrency.getRoundingMode());

		BigDecimal totalTaxAmount =
			orderPriceCommerceEngineTaskContext.getTotalTaxAmount();

		BigDecimal totalDiscountedWithTaxAmount;

		if (discountsTargetNetPrice) {
			BigDecimal totalWithTaxAmount =
				orderPriceCommerceEngineTaskContext.getTotalWithTaxAmount();

			orderPriceCommerceEngineTaskContext.setTotalDiscountedAmount(
				totalDiscountedAmount);

			totalDiscountedWithTaxAmount = totalDiscountedAmount.add(
				totalTaxAmount);

			orderPriceCommerceEngineTaskContext.setTotalDiscountedWithTaxAmount(
				totalDiscountedWithTaxAmount);

			orderPriceCommerceEngineTaskContext.setTotalCommerceDiscountValue(
				orderTotalCommerceDiscountValue);

			orderPriceCommerceEngineTaskContext.
				setTotalCommerceDiscountValueWithTaxAmount(
					CommercePriceConverterUtil.
						getConvertedCommerceDiscountValue(
							orderTotalCommerceDiscountValue, totalWithTaxAmount,
							totalDiscountedAmount.add(totalTaxAmount),
							commerceMoneyFactory, roundingMode));
		}
		else {
			BigDecimal totalAmount =
				orderPriceCommerceEngineTaskContext.getTotalAmount();

			BigDecimal convertedTotalDiscountedAmount =
				_getConvertedTotalAmount(totalDiscountedAmount, totalTaxAmount);

			orderPriceCommerceEngineTaskContext.setTotalDiscountedAmount(
				convertedTotalDiscountedAmount);

			totalDiscountedWithTaxAmount = totalDiscountedAmount;

			orderPriceCommerceEngineTaskContext.setTotalDiscountedWithTaxAmount(
				totalDiscountedAmount);

			orderPriceCommerceEngineTaskContext.setTotalCommerceDiscountValue(
				CommercePriceConverterUtil.getConvertedCommerceDiscountValue(
					orderTotalCommerceDiscountValue, totalAmount,
					convertedTotalDiscountedAmount, commerceMoneyFactory,
					roundingMode));

			orderPriceCommerceEngineTaskContext.
				setTotalCommerceDiscountValueWithTaxAmount(
					orderTotalCommerceDiscountValue);
		}

		orderPriceCommerceEngineTaskContext.setTotalAmount(
			totalDiscountedWithTaxAmount);
	}

	private BigDecimal _getConvertedTotalAmount(
		BigDecimal totalDiscountedAmount, BigDecimal totalTaxAmount) {

		if (totalTaxAmount.compareTo(totalDiscountedAmount) <= 0) {
			return totalDiscountedAmount.subtract(totalTaxAmount);
		}

		return BigDecimal.ZERO;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		DiscountTotalOrderPriceCommerceEngineTaskImpl.class);

}