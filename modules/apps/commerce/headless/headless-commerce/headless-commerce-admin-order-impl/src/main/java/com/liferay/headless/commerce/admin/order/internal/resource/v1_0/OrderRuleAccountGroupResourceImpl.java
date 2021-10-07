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

package com.liferay.headless.commerce.admin.order.internal.resource.v1_0;

import com.liferay.account.model.AccountGroup;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.commerce.order.rule.exception.NoSuchOrderRuleEntryException;
import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntry;
import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryRelService;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRule;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRuleAccountGroup;
import com.liferay.headless.commerce.admin.order.internal.dto.v1_0.converter.OrderRuleAccountGroupDTOConverter;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderRuleAccountGroupResource;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Marco Leo
 */
@Component(
	enabled = false,
	properties = "OSGI-INF/liferay/rest/v1_0/order-rule-account-group.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {NestedFieldSupport.class, OrderRuleAccountGroupResource.class}
)
public class OrderRuleAccountGroupResourceImpl
	extends BaseOrderRuleAccountGroupResourceImpl
	implements NestedFieldSupport {

	@Override
	public void deleteOrderRuleAccountGroup(Long id) throws Exception {
		_commerceOrderRuleEntryRelService.deleteCommerceOrderRuleEntryRel(id);
	}

	@Override
	public Page<OrderRuleAccountGroup>
			getOrderRuleByExternalReferenceCodeOrderRuleAccountGroupsPage(
				String externalReferenceCode, Pagination pagination)
		throws Exception {

		CommerceOrderRuleEntry commerceOrderRuleEntry =
			_commerceOrderRuleEntryService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderRuleEntry == null) {
			throw new NoSuchOrderRuleEntryException(
				"Unable to find rule entry with external reference code " +
					externalReferenceCode);
		}

		return Page.of(
			TransformUtil.transform(
				_commerceOrderRuleEntryRelService.
					getAccountGroupCommerceOrderRuleEntryRels(
						commerceOrderRuleEntry.getCommerceOrderRuleEntryId(),
						null, pagination.getStartPosition(),
						pagination.getEndPosition()),
				commerceOrderRuleEntryRel -> _toOrderRuleAccountGroup(
					commerceOrderRuleEntryRel)),
			pagination,
			_commerceOrderRuleEntryRelService.
				getAccountGroupCommerceOrderRuleEntryRelsCount(
					commerceOrderRuleEntry.getCommerceOrderRuleEntryId(),
					null));
	}

	@NestedField(
		parentClass = OrderRule.class, value = "orderRuleAccountGroups"
	)
	@Override
	public Page<OrderRuleAccountGroup> getOrderRuleIdOrderRuleAccountGroupsPage(
			Long id, String search, Filter filter, Pagination pagination,
			Sort[] sorts)
		throws Exception {

		CommerceOrderRuleEntry commerceOrderRuleEntry =
			_commerceOrderRuleEntryService.fetchCommerceOrderRuleEntry(id);

		if (commerceOrderRuleEntry == null) {
			throw new NoSuchOrderRuleEntryException(
				"Unable to find rule entry with id: " + id);
		}

		return Page.of(
			TransformUtil.transform(
				_commerceOrderRuleEntryRelService.
					getAccountGroupCommerceOrderRuleEntryRels(
						id, search, pagination.getStartPosition(),
						pagination.getEndPosition()),
				commerceOrderRuleEntryRel -> _toOrderRuleAccountGroup(
					commerceOrderRuleEntryRel)),
			pagination,
			_commerceOrderRuleEntryRelService.
				getAccountGroupCommerceOrderRuleEntryRelsCount(id, search));
	}

	@Override
	public OrderRuleAccountGroup
			postOrderRuleByExternalReferenceCodeOrderRuleAccountGroup(
				String externalReferenceCode,
				OrderRuleAccountGroup orderRuleAccountGroup)
		throws Exception {

		CommerceOrderRuleEntry commerceOrderRuleEntry =
			_commerceOrderRuleEntryService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderRuleEntry == null) {
			throw new NoSuchOrderRuleEntryException(
				"Unable to find rule entry with external reference code " +
					externalReferenceCode);
		}

		CommerceAccountGroup commerceAccountGroup = _getCommerceAccountGroup(
			orderRuleAccountGroup);

		return _toOrderRuleAccountGroup(
			_commerceOrderRuleEntryRelService.addCommerceOrderRuleEntryRel(
				AccountGroup.class.getName(),
				commerceAccountGroup.getCommerceAccountGroupId(),
				commerceOrderRuleEntry.getCommerceOrderRuleEntryId()));
	}

	@Override
	public OrderRuleAccountGroup postOrderRuleIdOrderRuleAccountGroup(
			Long id, OrderRuleAccountGroup orderRuleAccountGroup)
		throws Exception {

		CommerceAccountGroup commerceAccountGroup = _getCommerceAccountGroup(
			orderRuleAccountGroup);

		return _toOrderRuleAccountGroup(
			_commerceOrderRuleEntryRelService.addCommerceOrderRuleEntryRel(
				AccountGroup.class.getName(),
				commerceAccountGroup.getCommerceAccountGroupId(), id));
	}

	private Map<String, Map<String, String>> _getActions(
			CommerceOrderRuleEntryRel commerceOrderRuleEntryRel)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"UPDATE",
				commerceOrderRuleEntryRel.getCommerceOrderRuleEntryRelId(),
				"deleteOrderRuleAccountGroup",
				_commerceOrderRuleEntryRelModelResourcePermission)
		).build();
	}

	private CommerceAccountGroup _getCommerceAccountGroup(
			OrderRuleAccountGroup orderRuleAccountGroup)
		throws Exception {

		CommerceAccountGroup commerceAccountGroup = null;

		if (orderRuleAccountGroup.getAccountGroupId() > 0) {
			commerceAccountGroup =
				_commerceAccountGroupService.getCommerceAccountGroup(
					orderRuleAccountGroup.getAccountGroupId());
		}
		else {
			commerceAccountGroup =
				_commerceAccountGroupService.fetchByExternalReferenceCode(
					contextCompany.getCompanyId(),
					orderRuleAccountGroup.
						getAccountGroupExternalReferenceCode());
		}

		return commerceAccountGroup;
	}

	private OrderRuleAccountGroup _toOrderRuleAccountGroup(
			CommerceOrderRuleEntryRel commerceOrderRuleEntryRel)
		throws Exception {

		return _orderRuleAccountGroupDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceOrderRuleEntryRel), _dtoConverterRegistry,
				commerceOrderRuleEntryRel.getCommerceOrderRuleEntryRelId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	@Reference
	private CommerceAccountGroupService _commerceAccountGroupService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel)"
	)
	private ModelResourcePermission<CommerceOrderRuleEntryRel>
		_commerceOrderRuleEntryRelModelResourcePermission;

	@Reference
	private CommerceOrderRuleEntryRelService _commerceOrderRuleEntryRelService;

	@Reference
	private CommerceOrderRuleEntryService _commerceOrderRuleEntryService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private OrderRuleAccountGroupDTOConverter
		_orderRuleAccountGroupDTOConverter;

}