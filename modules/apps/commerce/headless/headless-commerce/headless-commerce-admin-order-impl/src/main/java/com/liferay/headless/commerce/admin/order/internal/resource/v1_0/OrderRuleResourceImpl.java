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
import com.liferay.account.model.AccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.order.rule.exception.NoSuchOrderRuleEntryException;
import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntry;
import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryRelService;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryService;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.commerce.service.CommerceOrderTypeService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRule;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRuleAccount;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRuleAccountGroup;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRuleChannel;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRuleOrderType;
import com.liferay.headless.commerce.admin.order.internal.dto.v1_0.converter.OrderRuleDTOConverter;
import com.liferay.headless.commerce.admin.order.internal.odata.entity.v1_0.OrderRuleEntityModel;
import com.liferay.headless.commerce.admin.order.internal.util.v1_0.OrderRuleAccountGroupUtil;
import com.liferay.headless.commerce.admin.order.internal.util.v1_0.OrderRuleAccountUtil;
import com.liferay.headless.commerce.admin.order.internal.util.v1_0.OrderRuleChannelUtil;
import com.liferay.headless.commerce.admin.order.internal.util.v1_0.OrderRuleOrderTypeUtil;
import com.liferay.headless.commerce.admin.order.resource.v1_0.OrderRuleResource;
import com.liferay.headless.commerce.core.util.DateConfig;
import com.liferay.headless.commerce.core.util.ServiceContextHelper;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Marco Leo
 */
@Component(
	enabled = false,
	properties = "OSGI-INF/liferay/rest/v1_0/order-rule.properties",
	scope = ServiceScope.PROTOTYPE, service = OrderRuleResource.class
)
public class OrderRuleResourceImpl extends BaseOrderRuleResourceImpl {

	@Override
	public void deleteOrderRule(Long id) throws Exception {
		_commerceOrderRuleEntryService.deleteCommerceOrderRuleEntry(id);
	}

	@Override
	public void deleteOrderRuleByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceOrderRuleEntry commerceOrderRuleEntry =
			_commerceOrderRuleEntryService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderRuleEntry == null) {
			throw new NoSuchOrderRuleEntryException(
				"Unable to find order rule with external reference code " +
					externalReferenceCode);
		}

		_commerceOrderRuleEntryService.deleteCommerceOrderRuleEntry(
			commerceOrderRuleEntry.getCommerceOrderRuleEntryId());
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap)
		throws Exception {

		return _entityModel;
	}

	@Override
	public OrderRule getOrderRule(Long id) throws Exception {
		return _toOrderRule(GetterUtil.getLong(id));
	}

	@Override
	public OrderRule getOrderRuleByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		CommerceOrderRuleEntry commerceOrderRuleEntry =
			_commerceOrderRuleEntryService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderRuleEntry == null) {
			throw new NoSuchOrderRuleEntryException(
				"Unable to find order rule with external reference code " +
					externalReferenceCode);
		}

		return _toOrderRule(
			commerceOrderRuleEntry.getCommerceOrderRuleEntryId());
	}

	@Override
	public Page<OrderRule> getOrderRulesPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			null, booleanQuery -> booleanQuery.getPreBooleanFilter(), filter,
			CommerceOrderRuleEntry.class.getName(), search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			new UnsafeConsumer() {

				public void accept(Object object) throws Exception {
					SearchContext searchContext = (SearchContext)object;

					searchContext.setAttribute(
						"status", WorkflowConstants.STATUS_ANY);
					searchContext.setCompanyId(contextCompany.getCompanyId());
				}

			},
			sorts,
			document -> _toOrderRule(
				GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK))));
	}

	@Override
	public OrderRule patchOrderRule(Long id, OrderRule orderRule)
		throws Exception {

		return _toOrderRule(
			_updateOrderRule(
				_commerceOrderRuleEntryService.getCommerceOrderRuleEntry(id),
				orderRule));
	}

	@Override
	public OrderRule patchOrderRuleByExternalReferenceCode(
			String externalReferenceCode, OrderRule orderRule)
		throws Exception {

		CommerceOrderRuleEntry commerceOrderRuleEntry =
			_commerceOrderRuleEntryService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (commerceOrderRuleEntry == null) {
			throw new NoSuchOrderRuleEntryException(
				"Unable to find order rule with external reference code " +
					externalReferenceCode);
		}

		return _toOrderRule(
			_updateOrderRule(commerceOrderRuleEntry, orderRule));
	}

	@Override
	public OrderRule postOrderRule(OrderRule orderRule) throws Exception {
		CommerceOrderRuleEntry commerceOrderRuleEntry =
			_addCommerceOrderRuleEntry(orderRule);

		return _toOrderRule(
			commerceOrderRuleEntry.getCommerceOrderRuleEntryId());
	}

	private CommerceOrderRuleEntry _addCommerceOrderRuleEntry(
			OrderRule orderRule)
		throws Exception {

		ServiceContext serviceContext =
			_serviceContextHelper.getServiceContext();

		DateConfig displayDateConfig = DateConfig.toDisplayDateConfig(
			orderRule.getDisplayDate(), serviceContext.getTimeZone());
		DateConfig expirationDateConfig = DateConfig.toExpirationDateConfig(
			orderRule.getExpirationDate(), serviceContext.getTimeZone());

		CommerceOrderRuleEntry commerceOrderRuleEntry =
			_commerceOrderRuleEntryService.addCommerceOrderRuleEntry(
				orderRule.getExternalReferenceCode(),
				GetterUtil.getBoolean(orderRule.getActive()),
				orderRule.getDescription(), orderRule.getName(),
				GetterUtil.getInteger(orderRule.getPriority()),
				orderRule.getType(), orderRule.getTypeSettings(),
				displayDateConfig.getMonth(), displayDateConfig.getDay(),
				displayDateConfig.getYear(), displayDateConfig.getHour(),
				displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
				expirationDateConfig.getDay(), expirationDateConfig.getYear(),
				expirationDateConfig.getHour(),
				expirationDateConfig.getMinute(),
				GetterUtil.getBoolean(orderRule.getNeverExpire(), true),
				serviceContext);

		// Update nested resources

		return _updateNestedResources(orderRule, commerceOrderRuleEntry);
	}

	private Map<String, Map<String, String>> _getActions(
			CommerceOrderRuleEntry commerceOrderRuleEntry)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"DELETE", commerceOrderRuleEntry.getCommerceOrderRuleEntryId(),
				"deleteOrderRule",
				_commerceOrderRuleEntryModelResourcePermission)
		).put(
			"get",
			addAction(
				"VIEW", commerceOrderRuleEntry.getCommerceOrderRuleEntryId(),
				"getOrderRule", _commerceOrderRuleEntryModelResourcePermission)
		).put(
			"permissions",
			addAction(
				"PERMISSIONS",
				commerceOrderRuleEntry.getCommerceOrderRuleEntryId(),
				"patchOrderRule",
				_commerceOrderRuleEntryModelResourcePermission)
		).put(
			"update",
			addAction(
				"UPDATE", commerceOrderRuleEntry.getCommerceOrderRuleEntryId(),
				"patchOrderRule",
				_commerceOrderRuleEntryModelResourcePermission)
		).build();
	}

	private OrderRule _toOrderRule(
			CommerceOrderRuleEntry commerceOrderRuleEntry)
		throws Exception {

		return _toOrderRule(
			commerceOrderRuleEntry.getCommerceOrderRuleEntryId());
	}

	private OrderRule _toOrderRule(Long commerceOrderRuleEntryId)
		throws Exception {

		CommerceOrderRuleEntry commerceOrderRuleEntry =
			_commerceOrderRuleEntryService.getCommerceOrderRuleEntry(
				commerceOrderRuleEntryId);

		return _orderRuleDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(commerceOrderRuleEntry), _dtoConverterRegistry,
				commerceOrderRuleEntryId,
				contextAcceptLanguage.getPreferredLocale(), contextUriInfo,
				contextUser));
	}

	private CommerceOrderRuleEntry _updateNestedResources(
			OrderRule orderRule, CommerceOrderRuleEntry commerceOrderRuleEntry)
		throws Exception {

		// Order rule account groups

		OrderRuleAccountGroup[] orderRuleAccountGroups =
			orderRule.getOrderRuleAccountGroup();

		if (orderRuleAccountGroups != null) {
			for (OrderRuleAccountGroup orderRuleAccountGroup :
					orderRuleAccountGroups) {

				CommerceOrderRuleEntryRel commerceOrderRuleEntryRel =
					_commerceOrderRuleEntryRelService.
						fetchCommerceOrderRuleEntryRel(
							AccountGroup.class.getName(),
							orderRuleAccountGroup.getAccountGroupId(),
							commerceOrderRuleEntry.
								getCommerceOrderRuleEntryId());

				if (commerceOrderRuleEntryRel != null) {
					continue;
				}

				OrderRuleAccountGroupUtil.
					addCommerceOrderRuleEntryCommerceAccountGroupRel(
						_commerceAccountGroupService,
						_commerceOrderRuleEntryRelService,
						commerceOrderRuleEntry, orderRuleAccountGroup);
			}
		}

		// Order rule accounts

		OrderRuleAccount[] orderRuleAccounts = orderRule.getOrderRuleAccount();

		if (orderRuleAccounts != null) {
			for (OrderRuleAccount orderRuleAccount : orderRuleAccounts) {
				CommerceOrderRuleEntryRel commerceOrderRuleEntryRel =
					_commerceOrderRuleEntryRelService.
						fetchCommerceOrderRuleEntryRel(
							AccountEntry.class.getName(),
							orderRuleAccount.getAccountId(),
							commerceOrderRuleEntry.
								getCommerceOrderRuleEntryId());

				if (commerceOrderRuleEntryRel != null) {
					continue;
				}

				OrderRuleAccountUtil.
					addCommerceOrderRuleEntryCommerceAccountRel(
						_commerceAccountService,
						_commerceOrderRuleEntryRelService,
						commerceOrderRuleEntry, orderRuleAccount);
			}
		}

		// Order rule channels

		OrderRuleChannel[] orderRuleChannels = orderRule.getOrderRuleChannel();

		if (orderRuleChannels != null) {
			for (OrderRuleChannel orderRuleChannel : orderRuleChannels) {
				CommerceOrderRuleEntryRel commerceOrderRuleEntryRel =
					_commerceOrderRuleEntryRelService.
						fetchCommerceOrderRuleEntryRel(
							CommerceChannel.class.getName(),
							orderRuleChannel.getChannelId(),
							commerceOrderRuleEntry.
								getCommerceOrderRuleEntryId());

				if (commerceOrderRuleEntryRel != null) {
					continue;
				}

				OrderRuleChannelUtil.
					addCommerceOrderRuleEntryCommerceChannelRel(
						_commerceChannelService,
						_commerceOrderRuleEntryRelService,
						commerceOrderRuleEntry, orderRuleChannel);
			}
		}

		// Order rule order types

		OrderRuleOrderType[] orderRuleOrderTypes =
			orderRule.getOrderRuleOrderType();

		if (orderRuleOrderTypes != null) {
			for (OrderRuleOrderType orderRuleOrderType : orderRuleOrderTypes) {
				CommerceOrderRuleEntryRel commerceOrderRuleEntryRel =
					_commerceOrderRuleEntryRelService.
						fetchCommerceOrderRuleEntryRel(
							CommerceOrderType.class.getName(),
							orderRuleOrderType.getOrderRuleId(),
							commerceOrderRuleEntry.
								getCommerceOrderRuleEntryId());

				if (commerceOrderRuleEntryRel != null) {
					continue;
				}

				OrderRuleOrderTypeUtil.
					addCommerceOrderRuleEntryCommerceOrderTypeRel(
						_commerceOrderRuleEntryRelService,
						commerceOrderRuleEntry, _commerceOrderTypeService,
						orderRuleOrderType);
			}
		}

		return commerceOrderRuleEntry;
	}

	private CommerceOrderRuleEntry _updateOrderRule(
			CommerceOrderRuleEntry commerceOrderRuleEntry, OrderRule orderRule)
		throws Exception {

		ServiceContext serviceContext =
			_serviceContextHelper.getServiceContext();

		DateConfig displayDateConfig = DateConfig.toDisplayDateConfig(
			orderRule.getDisplayDate(), serviceContext.getTimeZone());
		DateConfig expirationDateConfig = DateConfig.toExpirationDateConfig(
			orderRule.getExpirationDate(), serviceContext.getTimeZone());

		commerceOrderRuleEntry =
			_commerceOrderRuleEntryService.updateCommerceOrderRuleEntry(
				commerceOrderRuleEntry.getCommerceOrderRuleEntryId(),
				GetterUtil.getBoolean(
					orderRule.getActive(), commerceOrderRuleEntry.isActive()),
				GetterUtil.getString(
					orderRule.getDescription(),
					commerceOrderRuleEntry.getDescription()),
				GetterUtil.getString(
					orderRule.getName(), commerceOrderRuleEntry.getName()),
				GetterUtil.getInteger(
					orderRule.getPriority(),
					commerceOrderRuleEntry.getPriority()),
				GetterUtil.get(
					orderRule.getTypeSettings(),
					commerceOrderRuleEntry.getTypeSettings()),
				displayDateConfig.getMonth(), displayDateConfig.getDay(),
				displayDateConfig.getYear(), displayDateConfig.getHour(),
				displayDateConfig.getMinute(), expirationDateConfig.getMonth(),
				expirationDateConfig.getDay(), expirationDateConfig.getYear(),
				expirationDateConfig.getHour(),
				expirationDateConfig.getMinute(),
				GetterUtil.getBoolean(orderRule.getNeverExpire(), true),
				serviceContext);

		// Update nested resources

		return _updateNestedResources(orderRule, commerceOrderRuleEntry);
	}

	private static final EntityModel _entityModel = new OrderRuleEntityModel();

	@Reference
	private CommerceAccountGroupService _commerceAccountGroupService;

	@Reference
	private CommerceAccountService _commerceAccountService;

	@Reference
	private CommerceChannelService _commerceChannelService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.order.rule.model.CommerceOrderRuleEntry)"
	)
	private ModelResourcePermission<CommerceOrderRuleEntry>
		_commerceOrderRuleEntryModelResourcePermission;

	@Reference
	private CommerceOrderRuleEntryRelService _commerceOrderRuleEntryRelService;

	@Reference
	private CommerceOrderRuleEntryService _commerceOrderRuleEntryService;

	@Reference
	private CommerceOrderTypeService _commerceOrderTypeService;

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference
	private OrderRuleDTOConverter _orderRuleDTOConverter;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}