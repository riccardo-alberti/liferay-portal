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

package com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter;

import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.model.CommerceOrderTypeRel;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.service.CommerceOrderTypeRelService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceListOrderType;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	enabled = false,
	property = "model.class.name=com.liferay.commerce.model.CommerceOrderTypeRel",
	service = {DTOConverter.class, PriceListOrderTypeDTOConverter.class}
)
public class PriceListOrderTypeDTOConverter
	implements DTOConverter<CommerceOrderTypeRel, PriceListOrderType> {

	@Override
	public String getContentType() {
		return PriceListOrderType.class.getSimpleName();
	}

	@Override
	public PriceListOrderType toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceOrderTypeRel commerceOrderTypeRel =
			_commerceOrderTypeRelService.getCommerceOrderTypeRel(
				(Long)dtoConverterContext.getId());

		CommerceOrderType commerceOrderType =
			commerceOrderTypeRel.getCommerceOrderType();

		CommercePriceList commercePriceList =
			_commercePriceListService.getCommercePriceList(
				commerceOrderTypeRel.getClassPK());

		return new PriceListOrderType() {
			{
				actions = dtoConverterContext.getActions();
				orderTypeExternalReferenceCode =
					commerceOrderType.getExternalReferenceCode();
				orderTypeId = commerceOrderType.getCommerceOrderTypeId();
				priceListExternalReferenceCode =
					commercePriceList.getExternalReferenceCode();
				priceListId = commercePriceList.getCommercePriceListId();
				priceListOrderTypeId =
					commerceOrderTypeRel.getCommerceOrderTypeRelId();
			}
		};
	}

	@Reference
	private CommerceOrderTypeRelService _commerceOrderTypeRelService;

	@Reference
	private CommercePriceListService _commercePriceListService;

}