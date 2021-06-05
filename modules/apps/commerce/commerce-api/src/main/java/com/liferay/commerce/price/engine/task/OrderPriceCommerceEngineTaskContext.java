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

package com.liferay.commerce.price.engine.task;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.model.CommerceOrder;

import java.math.BigDecimal;

/**
 * @author Riccardo Alberti
 */
public class OrderPriceCommerceEngineTaskContext {

	public CommerceContext getCommerceContext() {
		return _commerceContext;
	}

	public CommerceOrder getCommerceOrder() {
		return _commerceOrder;
	}

	public BigDecimal getShippingAmount() {
		return _shippingAmount;
	}

	public CommerceDiscountValue getShippingCommerceDiscountValue() {
		return _shippingCommerceDiscountValue;
	}

	public CommerceDiscountValue
		getShippingCommerceDiscountValueWithTaxAmount() {

		return _shippingCommerceDiscountValueWithTaxAmount;
	}

	public BigDecimal getShippingDiscountedAmount() {
		return _shippingDiscountedAmount;
	}

	public BigDecimal getShippingDiscountedWithTaxAmount() {
		return _shippingDiscountedWithTaxAmount;
	}

	public BigDecimal getShippingTaxAmount() {
		return _shippingTaxAmount;
	}

	public BigDecimal getShippingWithTaxAmount() {
		return _shippingWithTaxAmount;
	}

	public BigDecimal getSubtotalAmount() {
		return _subtotalAmount;
	}

	public CommerceDiscountValue getSubtotalCommerceDiscountValue() {
		return _subtotalCommerceDiscountValue;
	}

	public CommerceDiscountValue
		getSubtotalCommerceDiscountValueWithTaxAmount() {

		return _subtotalCommerceDiscountValueWithTaxAmount;
	}

	public BigDecimal getSubtotalDiscountedAmount() {
		return _subtotalDiscountedAmount;
	}

	public BigDecimal getSubtotalDiscountedWithTaxAmount() {
		return _subtotalDiscountedWithTaxAmount;
	}

	public BigDecimal getSubtotalTaxAmount() {
		return _subtotalTaxAmount;
	}

	public BigDecimal getSubtotalWithTaxAmount() {
		return _subtotalWithTaxAmount;
	}

	public BigDecimal getTotalAmount() {
		return _totalAmount;
	}

	public CommerceDiscountValue getTotalCommerceDiscountValue() {
		return _totalCommerceDiscountValue;
	}

	public CommerceDiscountValue getTotalCommerceDiscountValueWithTaxAmount() {
		return _totalCommerceDiscountValueWithTaxAmount;
	}

	public BigDecimal getTotalDiscountedAmount() {
		return _totalDiscountedAmount;
	}

	public BigDecimal getTotalDiscountedWithTaxAmount() {
		return _totalDiscountedWithTaxAmount;
	}

	public BigDecimal getTotalTaxAmount() {
		return _totalTaxAmount;
	}

	public BigDecimal getTotalWithTaxAmount() {
		return _totalWithTaxAmount;
	}

	public void setCommerceContext(CommerceContext commerceContext) {
		_commerceContext = commerceContext;
	}

	public void setCommerceOrder(CommerceOrder commerceOrder) {
		_commerceOrder = commerceOrder;
	}

	public void setShippingAmount(BigDecimal shippingAmount) {
		_shippingAmount = shippingAmount;
	}

	public void setShippingCommerceDiscountValue(
		CommerceDiscountValue shippingCommerceDiscountValue) {

		_shippingCommerceDiscountValue = shippingCommerceDiscountValue;
	}

	public void setShippingCommerceDiscountValueWithTaxAmount(
		CommerceDiscountValue shippingCommerceDiscountValueWithTaxAmount) {

		_shippingCommerceDiscountValueWithTaxAmount =
			shippingCommerceDiscountValueWithTaxAmount;
	}

	public void setShippingDiscountedAmount(
		BigDecimal shippingDiscountedAmount) {

		_shippingDiscountedAmount = shippingDiscountedAmount;
	}

	public void setShippingDiscountedWithTaxAmount(
		BigDecimal shippingDiscountedWithTaxAmount) {

		_shippingDiscountedWithTaxAmount = shippingDiscountedWithTaxAmount;
	}

	public void setShippingTaxAmount(BigDecimal shippingTaxAmount) {
		_shippingTaxAmount = shippingTaxAmount;
	}

	public void setShippingWithTaxAmount(BigDecimal shippingWithTaxAmount) {
		_shippingWithTaxAmount = shippingWithTaxAmount;
	}

	public void setSubtotalAmount(BigDecimal subtotalAmount) {
		_subtotalAmount = subtotalAmount;
	}

	public void setSubtotalCommerceDiscountValue(
		CommerceDiscountValue subtotalCommerceDiscountValue) {

		_subtotalCommerceDiscountValue = subtotalCommerceDiscountValue;
	}

	public void setSubtotalCommerceDiscountValueWithTaxAmount(
		CommerceDiscountValue subtotalCommerceDiscountValueWithTaxAmount) {

		_subtotalCommerceDiscountValueWithTaxAmount =
			subtotalCommerceDiscountValueWithTaxAmount;
	}

	public void setSubtotalDiscountedAmount(
		BigDecimal subtotalDiscountedAmount) {

		_subtotalDiscountedAmount = subtotalDiscountedAmount;
	}

	public void setSubtotalDiscountedWithTaxAmount(
		BigDecimal subtotalDiscountedWithTaxAmount) {

		_subtotalDiscountedWithTaxAmount = subtotalDiscountedWithTaxAmount;
	}

	public void setSubtotalTaxAmount(BigDecimal subtotalTaxAmount) {
		_subtotalTaxAmount = subtotalTaxAmount;
	}

	public void setSubtotalWithTaxAmount(BigDecimal subtotalWithTaxAmount) {
		_subtotalWithTaxAmount = subtotalWithTaxAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		_totalAmount = totalAmount;
	}

	public void setTotalCommerceDiscountValue(
		CommerceDiscountValue totalCommerceDiscountValue) {

		_totalCommerceDiscountValue = totalCommerceDiscountValue;
	}

	public void setTotalCommerceDiscountValueWithTaxAmount(
		CommerceDiscountValue totalCommerceDiscountValueWithTaxAmount) {

		_totalCommerceDiscountValueWithTaxAmount =
			totalCommerceDiscountValueWithTaxAmount;
	}

	public void setTotalDiscountedAmount(BigDecimal totalDiscountedAmount) {
		_totalDiscountedAmount = totalDiscountedAmount;
	}

	public void setTotalDiscountedWithTaxAmount(
		BigDecimal totalDiscountedWithTaxAmount) {

		_totalDiscountedWithTaxAmount = totalDiscountedWithTaxAmount;
	}

	public void setTotalTaxAmount(BigDecimal totalTaxAmount) {
		_totalTaxAmount = totalTaxAmount;
	}

	public void setTotalWithTaxAmount(BigDecimal totalWithTaxAmount) {
		_totalWithTaxAmount = totalWithTaxAmount;
	}

	private CommerceContext _commerceContext;
	private CommerceOrder _commerceOrder;
	private BigDecimal _shippingAmount;
	private CommerceDiscountValue _shippingCommerceDiscountValue;
	private CommerceDiscountValue _shippingCommerceDiscountValueWithTaxAmount;
	private BigDecimal _shippingDiscountedAmount;
	private BigDecimal _shippingDiscountedWithTaxAmount;
	private BigDecimal _shippingTaxAmount;
	private BigDecimal _shippingWithTaxAmount;
	private BigDecimal _subtotalAmount;
	private CommerceDiscountValue _subtotalCommerceDiscountValue;
	private CommerceDiscountValue _subtotalCommerceDiscountValueWithTaxAmount;
	private BigDecimal _subtotalDiscountedAmount;
	private BigDecimal _subtotalDiscountedWithTaxAmount;
	private BigDecimal _subtotalTaxAmount;
	private BigDecimal _subtotalWithTaxAmount;
	private BigDecimal _totalAmount;
	private CommerceDiscountValue _totalCommerceDiscountValue;
	private CommerceDiscountValue _totalCommerceDiscountValueWithTaxAmount;
	private BigDecimal _totalDiscountedAmount;
	private BigDecimal _totalDiscountedWithTaxAmount;
	private BigDecimal _totalTaxAmount;
	private BigDecimal _totalWithTaxAmount;

}