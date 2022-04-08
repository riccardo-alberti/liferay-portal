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

package com.liferay.commerce.initializer.util.internal.osgi.commands;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupLocalServiceUtil;
import com.liferay.commerce.account.service.CommerceAccountLocalServiceUtil;
import com.liferay.commerce.initializer.util.CommerceOrderGenerator;
import com.liferay.commerce.initializer.util.CommerceShipmentGenerator;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseLocalServiceUtil;
import com.liferay.commerce.model.CommerceAvailabilityEstimate;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceShipment;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceListLocalServiceUtil;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPMeasurementUnit;
import com.liferay.commerce.product.model.CPOption;
import com.liferay.commerce.product.model.CPOptionCategory;
import com.liferay.commerce.product.model.CPSpecificationOption;
import com.liferay.commerce.product.model.CommerceCatalog;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil;
import com.liferay.commerce.product.service.CPMeasurementUnitLocalServiceUtil;
import com.liferay.commerce.product.service.CPOptionCategoryLocalServiceUtil;
import com.liferay.commerce.product.service.CPOptionLocalServiceUtil;
import com.liferay.commerce.product.service.CPSpecificationOptionLocalServiceUtil;
import com.liferay.commerce.product.service.CommerceCatalogLocalServiceUtil;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalServiceUtil;
import com.liferay.commerce.service.CommerceAvailabilityEstimateLocalServiceUtil;
import com.liferay.commerce.service.CommerceOrderLocalServiceUtil;
import com.liferay.commerce.service.CommerceShipmentLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.site.initializer.SiteInitializer;
import com.liferay.site.initializer.SiteInitializerRegistry;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"osgi.command.function=commerceAdminTearDown",
		"osgi.command.function=generateOrders",
		"osgi.command.function=generateShipments",
		"osgi.command.function=initializeSite", "osgi.command.scope=commerce"
	},
	service = CommerceOSGiCommands.class
)
public class CommerceOSGiCommands {

	public void commerceAdminTearDown() throws Exception {
		_deleteCommerceOrders();
		_deleteCommerceShipments();
		_deleteCommerceProducts();
		_deleteCommerceOptions();
		_deleteCommerceSpecifications();
		_deleteCommerceOptionCategories();
		_deleteCommerceChannels();
		_deleteCommerceMeasurementUnits();
		_deleteCommerceAvailabilityEstimates();
		_deleteCommerceWarehouses();
	}

	private void _deleteCommerceOrders() throws PortalException {
		List<CommerceOrder> commerceOrders =
			CommerceOrderLocalServiceUtil.getCommerceOrders(-1, -1);

		for (CommerceOrder commerceOrder : commerceOrders) {
			CommerceOrderLocalServiceUtil.deleteCommerceOrder(commerceOrder);
		}
	}

	private void _deleteCommerceShipments() throws PortalException {
		List<CommerceShipment> commerceShipments =
			CommerceShipmentLocalServiceUtil.getCommerceShipments(-1, -1);

		for (CommerceShipment commerceShipment : commerceShipments) {
			CommerceShipmentLocalServiceUtil.deleteCommerceShipment(commerceShipment, false);
		}
	}

	private void _deleteCommerceProducts() throws PortalException {
		List<CPDefinition> cpDefinitions =
			CPDefinitionLocalServiceUtil.getCPDefinitions(-1, -1);

		for (CPDefinition cpDefinition : cpDefinitions) {
			CPDefinitionLocalServiceUtil.deleteCPDefinition(cpDefinition);
		}
	}

	private void _deleteCommerceOptions() throws PortalException {
		List<CPOption> cpOptions =
			CPOptionLocalServiceUtil.getCPOptions(-1, -1);

		for (CPOption cpOption : cpOptions) {
			CPOptionLocalServiceUtil.deleteCPOption(cpOption);
		}
	}

	private void _deleteCommerceSpecifications() throws PortalException {
		List<CPSpecificationOption> cpSpecificationOptions =
			CPSpecificationOptionLocalServiceUtil.getCPSpecificationOptions(-1, -1);

		for (CPSpecificationOption cpSpecificationOption : cpSpecificationOptions) {
			CPSpecificationOptionLocalServiceUtil.deleteCPSpecificationOption(cpSpecificationOption);
		}
	}

	private void _deleteCommerceOptionCategories() throws PortalException {
		List<CPOptionCategory> cpOptionCategories =
			CPOptionCategoryLocalServiceUtil.getCPOptionCategories(-1, -1);

		for (CPOptionCategory cpOptionCategory : cpOptionCategories) {
			CPOptionCategoryLocalServiceUtil.deleteCPOptionCategory(cpOptionCategory);
		}
	}

	private void _deleteCommerceChannels() throws PortalException {
		List<CommerceChannel> commerceChannels =
			CommerceChannelLocalServiceUtil.getCommerceChannels(-1, -1);

		for (CommerceChannel commerceChannel : commerceChannels) {
			CommerceChannelLocalServiceUtil.deleteCommerceChannel(commerceChannel);
		}
	}

	private void _deleteCommerceWarehouses() throws PortalException {
		List<CommerceInventoryWarehouse> commerceInventoryWarehouses =
			CommerceInventoryWarehouseLocalServiceUtil.getCommerceInventoryWarehouses(-1, -1);

		for (CommerceInventoryWarehouse commerceInventoryWarehouse : commerceInventoryWarehouses) {
			CommerceInventoryWarehouseLocalServiceUtil.deleteCommerceInventoryWarehouse(commerceInventoryWarehouse);
		}
	}

	private void _deleteCommerceMeasurementUnits() {
		List<CPMeasurementUnit> cpMeasurementUnits =
			CPMeasurementUnitLocalServiceUtil.getCPMeasurementUnits(-1, -1);

		for (CPMeasurementUnit cpMeasurementUnit : cpMeasurementUnits) {
			CPMeasurementUnitLocalServiceUtil.deleteCPMeasurementUnit(cpMeasurementUnit);
		}
	}

	private void _deleteCommerceAvailabilityEstimates() throws PortalException {
		List<CommerceAvailabilityEstimate> commerceAvailabilityEstimates =
			CommerceAvailabilityEstimateLocalServiceUtil.getCommerceAvailabilityEstimates(-1, -1);

		for (CommerceAvailabilityEstimate commerceAvailabilityEstimate : commerceAvailabilityEstimates) {
			CommerceAvailabilityEstimateLocalServiceUtil.deleteCommerceAvailabilityEstimate(commerceAvailabilityEstimate);
		}
	}

	public void generateOrders(long groupId, int ordersCount) {
		_commerceOrderGenerator.generate(groupId, ordersCount);
	}

	public void generateShipments(long groupId, int shipmentsCount)
		throws Exception {

		_commerceShipmentGenerator.generate(groupId, shipmentsCount);
	}

	public void initializeSite(long groupId, String key) throws Exception {
		Group group = _groupLocalService.getGroup(groupId);

		Company company = _companyLocalService.getCompanyById(
			group.getCompanyId());

		Role role = _roleLocalService.fetchRole(
			company.getCompanyId(), RoleConstants.ADMINISTRATOR);

		List<User> roleUsers = _userLocalService.getRoleUsers(role.getRoleId());

		User user = roleUsers.get(0);

		PermissionChecker permissionChecker = _permissionCheckerFactory.create(
			user);

		PrincipalThreadLocal.setName(user.getUserId());

		PermissionThreadLocal.setPermissionChecker(permissionChecker);

		SiteInitializer siteInitializer =
			_siteInitializerRegistry.getSiteInitializer(key);

		siteInitializer.initialize(groupId);
	}

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference
	private CommerceOrderGenerator _commerceOrderGenerator;

	@Reference
	private CommerceShipmentGenerator _commerceShipmentGenerator;

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private GroupLocalService _groupLocalService;

	@Reference
	private PermissionCheckerFactory _permissionCheckerFactory;

	@Reference
	private RoleLocalService _roleLocalService;

	@Reference
	private SiteInitializerRegistry _siteInitializerRegistry;

	@Reference
	private UserLocalService _userLocalService;

}