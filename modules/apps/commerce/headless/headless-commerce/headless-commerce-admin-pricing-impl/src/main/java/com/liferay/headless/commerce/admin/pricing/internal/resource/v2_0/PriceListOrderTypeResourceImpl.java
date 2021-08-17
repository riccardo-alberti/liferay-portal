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

package com.liferay.headless.commerce.admin.pricing.internal.resource.v2_0;

import com.liferay.commerce.model.CommerceOrderTypeRel;
import com.liferay.commerce.price.list.exception.NoSuchPriceListException;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListService;
import com.liferay.commerce.service.CommerceOrderTypeRelService;
import com.liferay.commerce.service.CommerceOrderTypeService;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceList;
import com.liferay.headless.commerce.admin.pricing.dto.v2_0.PriceListOrderType;
import com.liferay.headless.commerce.admin.pricing.internal.dto.v2_0.converter.PriceListOrderTypeDTOConverter;
import com.liferay.headless.commerce.admin.pricing.internal.util.v2_0.PriceListOrderTypeUtil;
import com.liferay.headless.commerce.admin.pricing.resource.v2_0.PriceListOrderTypeResource;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	enabled = false,
	properties = "OSGI-INF/liferay/rest/v2_0/price-list-order-type.properties",
	scope = ServiceScope.PROTOTYPE, service = PriceListOrderTypeResource.class
)
public class PriceListOrderTypeResourceImpl
	extends BasePriceListOrderTypeResourceImpl {

	@Override
	public void deletePriceListOrderType(Long id) throws Exception {
		_commerceOrderTypeRelService.deleteCommerceOrderTypeRel(id);
	}

	@Override
	public Page<PriceListOrderType>
			getPriceListByExternalReferenceCodePriceListOrderTypesPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommercePriceList commercePriceList =
			_commercePriceListService.fetchByExternalReferenceCode(
				externalReferenceCode, contextCompany.getCompanyId());

		if (commercePriceList == null) {
			throw new NoSuchPriceListException(
				"Unable to find price list with external reference code " +
					externalReferenceCode);
		}

		return Page.of(
			_toPriceListOrderTypes(
				_commerceOrderTypeRelService.getCommerceOrderTypeRels(
					CommercePriceList.class.getName(),
					commercePriceList.getCommercePriceListId(),
					pagination.getStartPosition(), pagination.getEndPosition(),
					null)),
			pagination,
			_commerceOrderTypeRelService.getCommerceOrderTypeRelsCount(
				CommercePriceList.class.getName(),
				commercePriceList.getCommercePriceListId()));
	}

	@NestedField(parentClass = PriceList.class, value = "priceListOrderTypes")
	@Override
	public Page<PriceListOrderType> getPriceListIdPriceListOrderTypesPage(
			Long id, String search, Pagination pagination)
		throws Exception {

		CommercePriceList commercePriceList =
			_commercePriceListService.fetchCommercePriceList(id);

		if (commercePriceList == null) {
			throw new NoSuchPriceListException(
				"Unable to find price list with ID " + id);
		}

		return Page.of(
			_toPriceListOrderTypes(
				_commerceOrderTypeRelService.getCommerceOrderTypeRels(
					CommercePriceList.class.getName(), id, search,
					pagination.getStartPosition(),
					pagination.getEndPosition())),
			pagination,
			_commerceOrderTypeRelService.getCommerceOrderTypeRelsCount(
				CommercePriceList.class.getName(), id, search));
	}

	@Override
	public PriceListOrderType
			postPriceListByExternalReferenceCodePriceListOrderType(
				String externalReferenceCode,
				PriceListOrderType priceListOrderType)
		throws Exception {

		CommercePriceList commercePriceList =
			_commercePriceListService.fetchByExternalReferenceCode(
				externalReferenceCode, contextCompany.getCompanyId());

		if (commercePriceList == null) {
			throw new NoSuchPriceListException(
				"Unable to find price list with external reference code " +
					externalReferenceCode);
		}

		return _toPriceListOrderType(
			PriceListOrderTypeUtil.addCommerceOrderTypeRel(
				_commerceOrderTypeService, _commerceOrderTypeRelService,
				priceListOrderType, commercePriceList, _serviceContextHelper));
	}

	@Override
	public PriceListOrderType postPriceListIdPriceListOrderType(
			Long id, PriceListOrderType priceListOrderType)
		throws Exception {

		return _toPriceListOrderType(
			PriceListOrderTypeUtil.addCommerceOrderTypeRel(
				_commerceOrderTypeService, _commerceOrderTypeRelService,
				priceListOrderType,
				_commercePriceListService.getCommercePriceList(id),
				_serviceContextHelper));
	}

	private Map<String, Map<String, String>> _getActions(
		CommerceOrderTypeRel commerceOrderTypeRel) {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"UPDATE", commerceOrderTypeRel.getCommerceOrderTypeRelId(),
				"deletePriceListOrderType",
				_commerceOrderTypeRelModelResourcePermission)
		).build();
	}

	private PriceListOrderType _toPriceListOrderType(
			CommerceOrderTypeRel commerceOrderTypeRel)
		throws Exception {

		return _priceListOrderTypeDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceOrderTypeRel), _dtoConverterRegistry,
				commerceOrderTypeRel.getCommerceOrderTypeRelId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	private List<PriceListOrderType> _toPriceListOrderTypes(
		List<CommerceOrderTypeRel> commerceOrderTypeRels) {

		return TransformUtil.transform(
			commerceOrderTypeRels,
			commerceOrderTypeRel -> _toPriceListOrderType(
				commerceOrderTypeRel));
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrderTypeRel)"
	)
	private ModelResourcePermission<CommerceOrderTypeRel>
		_commerceOrderTypeRelModelResourcePermission;

	@Reference
	private CommerceOrderTypeRelService _commerceOrderTypeRelService;

	@Reference
	private CommerceOrderTypeService _commerceOrderTypeService;

	@Reference
	private CommercePriceListService _commercePriceListService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private PriceListOrderTypeDTOConverter _priceListOrderTypeDTOConverter;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}