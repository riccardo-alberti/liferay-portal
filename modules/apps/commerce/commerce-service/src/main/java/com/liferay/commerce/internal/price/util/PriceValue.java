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

package com.liferay.commerce.internal.price.util;

import com.liferay.petra.string.StringPool;

import java.io.Serializable;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author Riccardo Alberti
 */
public class PriceValue implements Serializable {

	public PriceValue(
		long cpInstanceId, long commerceCurrencyId, String unitPrice) {

		_cpInstanceId = cpInstanceId;
		_commerceCurrencyId = commerceCurrencyId;
		_unitPrice = unitPrice;

		_promoPrice = StringPool.BLANK;

		_discount = new HashMap<>();
		_discountIds = new HashSet<>();
	}

	public void addDiscountId(long commerceDiscountId) {
		_discountIds.add(commerceDiscountId);
	}

	public long getCommerceCurrencyId() {
		return _commerceCurrencyId;
	}

	public long getCpInstanceId() {
		return _cpInstanceId;
	}

	public HashMap<String, String> getDiscount() {
		return _discount;
	}

	public HashSet<Long> getDiscountIds() {
		return _discountIds;
	}

	public String getPromoPrice() {
		return _promoPrice;
	}

	public String getUnitPrice() {
		return _unitPrice;
	}

	public void setCommerceCurrencyId(long commerceCurrencyId) {
		_commerceCurrencyId = commerceCurrencyId;
	}

	public void setCpInstanceId(long cpInstanceId) {
		_cpInstanceId = cpInstanceId;
	}

	public void setDiscount(HashMap<String, String> discount) {
		_discount = discount;
	}

	public void setPromoPrice(String promoPrice) {
		_promoPrice = promoPrice;
	}

	public void setUnitPrice(String unitPrice) {
		_unitPrice = unitPrice;
	}

	private long _commerceCurrencyId;
	private long _cpInstanceId;
	private HashMap<String, String> _discount;
	private final HashSet<Long> _discountIds;
	private String _promoPrice;
	private String _unitPrice;

}