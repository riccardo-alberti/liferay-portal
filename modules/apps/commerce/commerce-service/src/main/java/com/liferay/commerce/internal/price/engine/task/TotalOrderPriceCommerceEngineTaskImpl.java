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
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.price.engine.task.OrderPriceCommerceEngineTaskContext;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.math.BigDecimal;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"commerce.engine.task.key=" + TotalOrderPriceCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + TotalOrderPriceCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + TotalOrderPriceCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class TotalOrderPriceCommerceEngineTaskImpl
	extends BaseOrderPriceCommerceEngineTask {

	public static final String KEY = "total-price";

	public static final int PRIORITY = 30;

	public static final String TYPE = "order-pricing";

	@Override
	public boolean evaluate(
		long groupId,
		OrderPriceCommerceEngineTaskContext
			orderPriceCommerceEngineTaskContext) {

		return true;
	}

	@Override
	public void execute(
			OrderPriceCommerceEngineTaskContext
				orderPriceCommerceEngineTaskContext)
		throws Exception {

		CommerceContext commerceContext =
			orderPriceCommerceEngineTaskContext.getCommerceContext();

		CommerceOrder commerceOrder =
			orderPriceCommerceEngineTaskContext.getCommerceOrder();

		BigDecimal shippingAmount =
			orderPriceCommerceEngineTaskContext.getShippingAmount();
		BigDecimal subtotalAmount =
			orderPriceCommerceEngineTaskContext.getSubtotalAmount();

		if (orderPriceCommerceEngineTaskContext.getShippingDiscountedAmount() !=
				null) {

			shippingAmount =
				orderPriceCommerceEngineTaskContext.
					getShippingDiscountedAmount();
		}

		if (orderPriceCommerceEngineTaskContext.getSubtotalDiscountedAmount() !=
				null) {

			shippingAmount =
				orderPriceCommerceEngineTaskContext.
					getSubtotalDiscountedAmount();
		}

		BigDecimal totalAmount = shippingAmount.add(subtotalAmount);

		BigDecimal shippingTaxAmount =
			orderPriceCommerceEngineTaskContext.getShippingTaxAmount();

		BigDecimal totalTaxAmount = shippingTaxAmount.add(
			orderPriceCommerceEngineTaskContext.getSubtotalTaxAmount());

		BigDecimal totalWithTaxAmount = totalAmount.add(totalTaxAmount);

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		totalAmount = convertCommercePriceCurrency(
			totalAmount, commerceCurrency,
			commerceContext.getCommerceCurrency());

		totalWithTaxAmount = convertCommercePriceCurrency(
			totalWithTaxAmount, commerceCurrency,
			commerceContext.getCommerceCurrency());

		totalTaxAmount = convertCommercePriceCurrency(
			totalTaxAmount, commerceCurrency,
			commerceContext.getCommerceCurrency());

		orderPriceCommerceEngineTaskContext.setTotalAmount(totalAmount);
		orderPriceCommerceEngineTaskContext.setTotalWithTaxAmount(
			totalWithTaxAmount);
		orderPriceCommerceEngineTaskContext.setTotalTaxAmount(totalTaxAmount);
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

}