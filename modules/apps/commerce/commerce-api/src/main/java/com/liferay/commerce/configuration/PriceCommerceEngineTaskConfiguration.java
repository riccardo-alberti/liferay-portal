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

package com.liferay.commerce.configuration;

import aQute.bnd.annotation.metatype.Meta;

import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

/**
 * @author Riccardo Alberti
 */
@ExtendedObjectClassDefinition(
	category = "pricing", scope = ExtendedObjectClassDefinition.Scope.GROUP
)
@Meta.OCD(
	id = "com.liferay.commerce.configuration.PriceCommerceEngineTaskConfiguration",
	localization = "content/Language", name = "price-tasks-configuration-name"
)
public interface PriceCommerceEngineTaskConfiguration {

	@Meta.AD(deflt = "true", name = "price-list-price", required = false)
	public boolean priceListPrice();

	@Meta.AD(name = "price-list-price-evaluate-condition", required = false)
	public String priceListPriceEvaluateCondition();

	@Meta.AD(deflt = "true", name = "promotion-price", required = false)
	public boolean promotionPrice();

	@Meta.AD(name = "promotion-price-evaluate-condition", required = false)
	public String promotionPriceEvaluateCondition();

	@Meta.AD(deflt = "true", name = "discount-price", required = false)
	public boolean discountPrice();

	@Meta.AD(name = "discount-price-evaluate-condition", required = false)
	public String discountPriceEvaluateCondition();

	@Meta.AD(deflt = "true", name = "discount-shipping-price", required = false)
	public boolean discountShippingPrice();

	@Meta.AD(
		name = "discount-shipping-price-evaluate-condition", required = false
	)
	public String discountShippingPriceEvaluateCondition();

	@Meta.AD(deflt = "true", name = "discount-subtotal-price", required = false)
	public boolean discountSubtotalPrice();

	@Meta.AD(
		name = "discount-subtotal-price-evaluate-condition", required = false
	)
	public String discountSubtotalPriceEvaluateCondition();

	@Meta.AD(deflt = "true", name = "discount-total-price", required = false)
	public boolean discountTotalPrice();

	@Meta.AD(name = "discount-total-price-evaluate-condition", required = false)
	public String discountTotalPriceEvaluateCondition();

}