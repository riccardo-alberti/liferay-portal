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
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
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
		"commerce.engine.task.key=" + SubtotalOrderPriceCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + SubtotalOrderPriceCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + SubtotalOrderPriceCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class SubtotalOrderPriceCommerceEngineTaskImpl
	extends BaseOrderPriceCommerceEngineTask {

	public static final String KEY = "subtotal-price";

	public static final int PRIORITY = 20;

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

		CommerceMoney subtotalCommerceMoney = _getSubtotal(
			commerceOrder, commerceContext);

		BigDecimal subtotalAmount = subtotalCommerceMoney.getPrice();

		CommerceMoney subtotalTaxCommerceMoney = _getSubtotalTaxAmount(
			commerceOrder, commerceContext);

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		subtotalAmount = convertCommercePriceCurrency(
			subtotalAmount, commerceCurrency,
			commerceContext.getCommerceCurrency());

		BigDecimal subtotalWithTaxAmount = convertCommercePriceCurrency(
			subtotalAmount.add(subtotalTaxCommerceMoney.getPrice()),
			commerceCurrency, commerceContext.getCommerceCurrency());

		BigDecimal subtotalTaxAmount = convertCommercePriceCurrency(
			subtotalTaxCommerceMoney.getPrice(), commerceCurrency,
			commerceContext.getCommerceCurrency());

		orderPriceCommerceEngineTaskContext.setSubtotalAmount(subtotalAmount);
		orderPriceCommerceEngineTaskContext.setSubtotalWithTaxAmount(
			subtotalWithTaxAmount);
		orderPriceCommerceEngineTaskContext.setSubtotalTaxAmount(
			subtotalTaxAmount);
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

	private CommerceMoney _getSubtotal(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws Exception {

		BigDecimal subtotal = BigDecimal.ZERO;

		if (commerceOrder == null) {
			return commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), subtotal);
		}

		if (!commerceOrder.isOpen()) {
			return commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				commerceOrder.getSubtotal());
		}

		for (CommerceOrderItem commerceOrderItem :
				commerceOrder.getCommerceOrderItems()) {

			subtotal = subtotal.add(commerceOrderItem.getFinalPrice());
		}

		return commerceMoneyFactory.create(
			commerceContext.getCommerceCurrency(), subtotal);
	}

	private CommerceMoney _getSubtotalTaxAmount(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws Exception {

		if (commerceOrder == null) {
			return commerceMoneyFactory.emptyCommerceMoney();
		}

		if (!commerceOrder.isOpen()) {
			return commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				commerceOrder.getTaxAmount());
		}

		return commerceTaxCalculation.getTaxAmount(
			commerceOrder, commerceContext.getCommerceCurrency());
	}

}