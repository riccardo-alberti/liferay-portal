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

package com.liferay.commerce.order.rule.internal.security.permission.resource;

import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel;
import com.liferay.commerce.order.rule.permission.CommerceOrderRuleEntryPermission;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryRelLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = "model.class.name=com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel",
	service = ModelResourcePermission.class
)
public class CommerceOrderRuleEntryRelModelResourcePermission
	implements ModelResourcePermission<CommerceOrderRuleEntryRel> {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommerceOrderRuleEntryRel commerceOrderRuleEntryRel,
			String actionId)
		throws PortalException {

		_commerceOrderRuleEntryPermission.check(
			permissionChecker,
			commerceOrderRuleEntryRel.getCommerceOrderRuleEntryId(), actionId);
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			long commerceOrderRuleEntryRelId, String actionId)
		throws PortalException {

		CommerceOrderRuleEntryRel commerceOrderRuleEntryRel =
			_commerceOrderRuleEntryRelLocalService.getCommerceOrderRuleEntryRel(
				commerceOrderRuleEntryRelId);

		_commerceOrderRuleEntryPermission.check(
			permissionChecker,
			commerceOrderRuleEntryRel.getCommerceOrderRuleEntryId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommerceOrderRuleEntryRel commerceOrderRuleEntryRel,
			String actionId)
		throws PortalException {

		return _commerceOrderRuleEntryPermission.contains(
			permissionChecker,
			commerceOrderRuleEntryRel.getCommerceOrderRuleEntryId(), actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long commerceOrderRuleEntryRelId, String actionId)
		throws PortalException {

		CommerceOrderRuleEntryRel commerceOrderRuleEntryRel =
			_commerceOrderRuleEntryRelLocalService.getCommerceOrderRuleEntryRel(
				commerceOrderRuleEntryRelId);

		return _commerceOrderRuleEntryPermission.contains(
			permissionChecker,
			commerceOrderRuleEntryRel.getCommerceOrderRuleEntryId(), actionId);
	}

	@Override
	public String getModelName() {
		return CommerceOrderRuleEntryRel.class.getName();
	}

	@Override
	public PortletResourcePermission getPortletResourcePermission() {
		return null;
	}

	@Reference
	private CommerceOrderRuleEntryPermission _commerceOrderRuleEntryPermission;

	@Reference
	private CommerceOrderRuleEntryRelLocalService
		_commerceOrderRuleEntryRelLocalService;

}