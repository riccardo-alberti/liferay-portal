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

package com.liferay.commerce.internal.price;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.engine.CommerceEngine;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.price.CommerceOrderPrice;
import com.liferay.commerce.price.CommerceOrderPriceCalculation;
import com.liferay.commerce.price.CommerceOrderPriceImpl;
import com.liferay.commerce.price.engine.task.OrderPriceCommerceEngineTaskContext;
import com.liferay.commerce.tax.CommerceTaxCalculation;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.math.BigDecimal;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(enabled = false, service = CommerceOrderPriceCalculation.class)
public class CommerceOrderPriceCalculationImpl
	extends BaseCommerceOrderPriceCalculation {

	@Override
	public CommerceOrderPrice getCommerceOrderPrice(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (commerceOrder == null) {
			return getEmptyCommerceOrderPrice(
				commerceContext.getCommerceCurrency());
		}

		if (!commerceOrder.isOpen()) {
			return getCommerceOrderPriceFromOrder(commerceOrder);
		}

		OrderPriceCommerceEngineTaskContext
			orderPriceCommerceEngineTaskContext =
				new OrderPriceCommerceEngineTaskContext();

		orderPriceCommerceEngineTaskContext.setCommerceContext(commerceContext);
		orderPriceCommerceEngineTaskContext.setCommerceOrder(commerceOrder);

		try {
			_commerceEngine.executeTasks(
				commerceContext.getCommerceChannelGroupId(), "order-pricing",
				orderPriceCommerceEngineTaskContext);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return getEmptyCommerceOrderPrice(
				commerceContext.getCommerceCurrency());
		}

		return _getCommerceOrderPriceImpl(
			commerceContext.getCommerceCurrency(),
			orderPriceCommerceEngineTaskContext);
	}

	@Override
	public CommerceOrderPrice getCommerceOrderPrice(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getCommerceOrderPrice(commerceOrder, true, commerceContext);
	}

	@Override
	public CommerceMoney getSubtotal(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

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

	@Override
	public CommerceMoney getSubtotal(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getSubtotal(commerceOrder, true, commerceContext);
	}

	@Override
	public CommerceMoney getTaxValue(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (commerceOrder == null) {
			return commerceMoneyFactory.emptyCommerceMoney();
		}

		if (!commerceOrder.isOpen()) {
			return commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				commerceOrder.getTaxAmount());
		}

		return _commerceTaxCalculation.getTaxAmount(
			commerceOrder, commerceContext.getCommerceCurrency());
	}

	@Override
	public CommerceMoney getTaxValue(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getTaxValue(commerceOrder, true, commerceContext);
	}

	@Override
	public CommerceMoney getTotal(
			CommerceOrder commerceOrder, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		if (!commerceOrder.isOpen()) {
			return commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(),
				commerceOrder.getTotal());
		}

		CommerceOrderPrice commerceOrderPrice = getCommerceOrderPrice(
			commerceOrder, commerceContext);

		return commerceOrderPrice.getTotal();
	}

	@Override
	public CommerceMoney getTotal(
			CommerceOrder commerceOrder, CommerceContext commerceContext)
		throws PortalException {

		return getTotal(commerceOrder, true, commerceContext);
	}

	private CommerceOrderPriceImpl _getCommerceOrderPriceImpl(
		CommerceCurrency commerceCurrency,
		OrderPriceCommerceEngineTaskContext
			orderPriceCommerceEngineTaskContext) {

		CommerceOrderPriceImpl commerceOrderPriceImpl =
			new CommerceOrderPriceImpl();

		commerceOrderPriceImpl.setShippingValue(
			commerceMoneyFactory.create(
				commerceCurrency,
				orderPriceCommerceEngineTaskContext.getShippingAmount()));
		commerceOrderPriceImpl.setShippingValueWithTaxAmount(
			commerceMoneyFactory.create(
				commerceCurrency,
				orderPriceCommerceEngineTaskContext.
					getShippingWithTaxAmount()));
		commerceOrderPriceImpl.setSubtotal(
			commerceMoneyFactory.create(
				commerceCurrency,
				orderPriceCommerceEngineTaskContext.getSubtotalAmount()));
		commerceOrderPriceImpl.setSubtotalWithTaxAmount(
			commerceMoneyFactory.create(
				commerceCurrency,
				orderPriceCommerceEngineTaskContext.
					getSubtotalWithTaxAmount()));
		commerceOrderPriceImpl.setTaxValue(
			commerceMoneyFactory.create(
				commerceCurrency,
				orderPriceCommerceEngineTaskContext.getTotalTaxAmount()));
		commerceOrderPriceImpl.setTotal(
			commerceMoneyFactory.create(
				commerceCurrency,
				orderPriceCommerceEngineTaskContext.getTotalAmount()));
		commerceOrderPriceImpl.setTotalWithTaxAmount(
			commerceMoneyFactory.create(
				commerceCurrency,
				orderPriceCommerceEngineTaskContext.getTotalWithTaxAmount()));

		commerceOrderPriceImpl.setShippingDiscountValue(
			orderPriceCommerceEngineTaskContext.
				getShippingCommerceDiscountValue());
		commerceOrderPriceImpl.setSubtotalDiscountValue(
			orderPriceCommerceEngineTaskContext.
				getSubtotalCommerceDiscountValue());
		commerceOrderPriceImpl.setTotalDiscountValue(
			orderPriceCommerceEngineTaskContext.
				getTotalCommerceDiscountValue());

		commerceOrderPriceImpl.setShippingDiscountValueWithTaxAmount(
			orderPriceCommerceEngineTaskContext.
				getShippingCommerceDiscountValueWithTaxAmount());
		commerceOrderPriceImpl.setSubtotalDiscountValueWithTaxAmount(
			orderPriceCommerceEngineTaskContext.
				getSubtotalCommerceDiscountValueWithTaxAmount());
		commerceOrderPriceImpl.setTotalDiscountValueWithTaxAmount(
			orderPriceCommerceEngineTaskContext.
				getTotalCommerceDiscountValueWithTaxAmount());

		return commerceOrderPriceImpl;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderPriceCalculationImpl.class);

	@Reference
	private CommerceEngine _commerceEngine;

	@Reference
	private CommerceTaxCalculation _commerceTaxCalculation;

}