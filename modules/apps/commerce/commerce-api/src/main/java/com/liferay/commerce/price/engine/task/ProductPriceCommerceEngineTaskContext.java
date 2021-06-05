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
import com.liferay.commerce.product.option.CommerceOptionValue;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.List;

/**
 * @author Riccardo Alberti
 */
public class ProductPriceCommerceEngineTaskContext implements Serializable {

	public CommerceContext getCommerceContext() {
		return _commerceContext;
	}

	public CommerceDiscountValue getCommerceDiscountValue() {
		return _commerceDiscountValue;
	}

	public CommerceDiscountValue getCommerceDiscountValueWithTaxAmount() {
		return _commerceDiscountValueWithTaxAmount;
	}

	public List<CommerceOptionValue> getCommerceOptionValues() {
		return _commerceOptionValues;
	}

	public long getCommercePriceListId() {
		return _commercePriceListId;
	}

	public long getCommercePromoPriceListId() {
		return _commercePromoPriceListId;
	}

	public long getCpInstanceId() {
		return _cpInstanceId;
	}

	public long getFinalCommercePriceListId() {
		return _finalCommercePriceListId;
	}

	public BigDecimal getFinalPrice() {
		return _finalPrice;
	}

	public BigDecimal getFinalPriceWithTaxAmount() {
		return _finalPriceWithTaxAmount;
	}

	public BigDecimal getFinalUnitPrice() {
		return _finalUnitPrice;
	}

	public BigDecimal getFinalUnitPriceWithTaxAmount() {
		return _finalUnitPriceWithTaxAmount;
	}

	public BigDecimal getPromoPrice() {
		return _promoPrice;
	}

	public BigDecimal getPromoPriceWithTaxAmount() {
		return _promoPriceWithTaxAmount;
	}

	public int getQuantity() {
		return _quantity;
	}

	public BigDecimal getUnitPrice() {
		return _unitPrice;
	}

	public BigDecimal getUnitPriceWithTaxAmount() {
		return _unitPriceWithTaxAmount;
	}

	public boolean isConditionValid() {
		return _conditionValid;
	}

	public void setCommerceContext(CommerceContext commerceContext) {
		_commerceContext = commerceContext;
	}

	public void setCommerceDiscountValue(
		CommerceDiscountValue commerceDiscountValue) {

		_commerceDiscountValue = commerceDiscountValue;
	}

	public void setCommerceDiscountValueWithTaxAmount(
		CommerceDiscountValue commerceDiscountValueWithTaxAmount) {

		_commerceDiscountValueWithTaxAmount =
			commerceDiscountValueWithTaxAmount;
	}

	public void setCommerceOptionValues(
		List<CommerceOptionValue> commerceOptionValues) {

		_commerceOptionValues = commerceOptionValues;
	}

	public void setCommercePriceListId(long commercePriceListId) {
		_commercePriceListId = commercePriceListId;
	}

	public void setCommercePromoPriceListId(long commercePromoPriceListId) {
		_commercePromoPriceListId = commercePromoPriceListId;
	}

	public void setConditionValid(boolean conditionValid) {
		_conditionValid = conditionValid;
	}

	public void setCpInstanceId(long cpInstanceId) {
		_cpInstanceId = cpInstanceId;
	}

	public void setFinalCommercePriceListId(long finalCommercePriceListId) {
		_finalCommercePriceListId = finalCommercePriceListId;
	}

	public void setFinalPrice(BigDecimal finalPrice) {
		_finalPrice = finalPrice;
	}

	public void setFinalPriceWithTaxAmount(BigDecimal finalPriceWithTaxAmount) {
		_finalPriceWithTaxAmount = finalPriceWithTaxAmount;
	}

	public void setFinalUnitPrice(BigDecimal finalUnitPrice) {
		_finalUnitPrice = finalUnitPrice;
	}

	public void setFinalUnitPriceWithTaxAmount(
		BigDecimal finalUnitPriceWithTaxAmount) {

		_finalUnitPriceWithTaxAmount = finalUnitPriceWithTaxAmount;
	}

	public void setPromoPrice(BigDecimal promoPrice) {
		_promoPrice = promoPrice;
	}

	public void setPromoPriceWithTaxAmount(BigDecimal promoPriceWithTaxAmount) {
		_promoPriceWithTaxAmount = promoPriceWithTaxAmount;
	}

	public void setQuantity(int quantity) {
		_quantity = quantity;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		_unitPrice = unitPrice;
	}

	public void setUnitPriceWithTaxAmount(BigDecimal unitPriceWithTaxAmount) {
		_unitPriceWithTaxAmount = unitPriceWithTaxAmount;
	}

	private CommerceContext _commerceContext;
	private CommerceDiscountValue _commerceDiscountValue;
	private CommerceDiscountValue _commerceDiscountValueWithTaxAmount;
	private List<CommerceOptionValue> _commerceOptionValues;
	private long _commercePriceListId;
	private long _commercePromoPriceListId;
	private boolean _conditionValid;
	private long _cpInstanceId;
	private long _finalCommercePriceListId;
	private BigDecimal _finalPrice;
	private BigDecimal _finalPriceWithTaxAmount;
	private BigDecimal _finalUnitPrice;
	private BigDecimal _finalUnitPriceWithTaxAmount;
	private BigDecimal _promoPrice;
	private BigDecimal _promoPriceWithTaxAmount;
	private int _quantity;
	private BigDecimal _unitPrice;
	private BigDecimal _unitPriceWithTaxAmount;

}