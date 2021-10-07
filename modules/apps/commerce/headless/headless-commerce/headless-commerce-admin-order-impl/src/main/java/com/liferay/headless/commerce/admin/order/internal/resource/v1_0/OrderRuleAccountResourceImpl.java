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

import com.liferay.account.model.AccountEntry;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.order.rule.exception.NoSuchOrderRuleEntryException;
import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntry;
import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryRelService;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRule;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRuleAccount;
import com.liferay.headless.commerce.admin.order.internal.dto.v1_0.converter.OrderRuleAccountDTOConverter;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderRuleAccountResource;
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
	properties = "OSGI-INF/liferay/rest/v1_0/order-rule-account.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {NestedFieldSupport.class, OrderRuleAccountResource.class}
)
public class OrderRuleAccountResourceImpl
	extends BaseOrderRuleAccountResourceImpl implements NestedFieldSupport {

	@Override
	public void deleteOrderRuleAccount(Long id) throws Exception {
		_commerceOrderRuleEntryRelService.deleteCommerceOrderRuleEntryRel(id);
	}

	@Override
	public Page<OrderRuleAccount>
			getOrderRuleByExternalReferenceCodeOrderRuleAccountsPage(
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
					getAccountEntryCommerceOrderRuleEntryRels(
						commerceOrderRuleEntry.getCommerceOrderRuleEntryId(),
						null, pagination.getStartPosition(),
						pagination.getEndPosition()),
				commerceOrderRuleEntryRel -> _toOrderRuleAccount(
					commerceOrderRuleEntryRel)),
			pagination,
			_commerceOrderRuleEntryRelService.
				getAccountEntryCommerceOrderRuleEntryRelsCount(
					commerceOrderRuleEntry.getCommerceOrderRuleEntryId(),
					null));
	}

	@NestedField(parentClass = OrderRule.class, value = "orderRuleAccounts")
	@Override
	public Page<OrderRuleAccount> getOrderRuleIdOrderRuleAccountsPage(
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
					getAccountEntryCommerceOrderRuleEntryRels(
						id, search, pagination.getStartPosition(),
						pagination.getEndPosition()),
				commerceOrderRuleEntryRel -> _toOrderRuleAccount(
					commerceOrderRuleEntryRel)),
			pagination,
			_commerceOrderRuleEntryRelService.
				getAccountEntryCommerceOrderRuleEntryRelsCount(id, search));
	}

	@Override
	public OrderRuleAccount
			postOrderRuleByExternalReferenceCodeOrderRuleAccount(
				String externalReferenceCode, OrderRuleAccount orderRuleAccount)
		throws Exception {

		CommerceOrderRuleEntry commerceOrderRuleEntry =
			_commerceOrderRuleEntryService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderRuleEntry == null) {
			throw new NoSuchOrderRuleEntryException(
				"Unable to find rule entry with external reference code " +
					externalReferenceCode);
		}

		CommerceAccount commerceAccount = _getCommerceAccount(orderRuleAccount);

		return _toOrderRuleAccount(
			_commerceOrderRuleEntryRelService.addCommerceOrderRuleEntryRel(
				AccountEntry.class.getName(),
				commerceAccount.getCommerceAccountId(),
				commerceOrderRuleEntry.getCommerceOrderRuleEntryId()));
	}

	@Override
	public OrderRuleAccount postOrderRuleIdOrderRuleAccount(
			Long id, OrderRuleAccount orderRuleAccount)
		throws Exception {

		CommerceAccount commerceAccount = _getCommerceAccount(orderRuleAccount);

		return _toOrderRuleAccount(
			_commerceOrderRuleEntryRelService.addCommerceOrderRuleEntryRel(
				AccountEntry.class.getName(),
				commerceAccount.getCommerceAccountId(), id));
	}

	private Map<String, Map<String, String>> _getActions(
			CommerceOrderRuleEntryRel commerceOrderRuleEntryRel)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"UPDATE",
				commerceOrderRuleEntryRel.getCommerceOrderRuleEntryRelId(),
				"deleteOrderRuleAccount",
				_commerceOrderRuleEntryRelModelResourcePermission)
		).build();
	}

	private CommerceAccount _getCommerceAccount(
			OrderRuleAccount orderRuleAccount)
		throws Exception {

		CommerceAccount commerceAccount = null;

		if (orderRuleAccount.getAccountId() > 0) {
			commerceAccount = _commerceAccountService.getCommerceAccount(
				orderRuleAccount.getAccountId());
		}
		else {
			commerceAccount =
				_commerceAccountService.fetchByExternalReferenceCode(
					contextCompany.getCompanyId(),
					orderRuleAccount.getAccountExternalReferenceCode());
		}

		return commerceAccount;
	}

	private OrderRuleAccount _toOrderRuleAccount(
			CommerceOrderRuleEntryRel commerceOrderRuleEntryRel)
		throws Exception {

		return _orderRuleAccountDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceOrderRuleEntryRel), _dtoConverterRegistry,
				commerceOrderRuleEntryRel.getCommerceOrderRuleEntryRelId(),
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	@Reference
	private CommerceAccountService _commerceAccountService;

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
	private OrderRuleAccountDTOConverter _orderRuleAccountDTOConverter;

}