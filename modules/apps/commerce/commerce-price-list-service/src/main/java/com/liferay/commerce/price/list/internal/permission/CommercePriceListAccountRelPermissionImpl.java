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

package com.liferay.commerce.price.list.internal.permission;

import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListAccountRel;
import com.liferay.commerce.price.list.permission.CommercePriceListAccountRelPermission;
import com.liferay.commerce.price.list.service.CommercePriceListAccountRelLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	service = CommercePriceListAccountRelPermission.class
)
public class CommercePriceListAccountRelPermissionImpl
	implements CommercePriceListAccountRelPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			CommercePriceListAccountRel commercePriceListAccountRel,
			String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, commercePriceListAccountRel, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommercePriceListAccountRel.class.getName(),
				commercePriceListAccountRel.getCommercePriceListAccountRelId(),
				actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker,
			long commercePriceListAccountRelId, String actionId)
		throws PortalException {

		if (!contains(
				permissionChecker, commercePriceListAccountRelId, actionId)) {

			throw new PrincipalException.MustHavePermission(
				permissionChecker, CommercePriceListAccountRel.class.getName(),
				commercePriceListAccountRelId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			CommercePriceListAccountRel commercePriceListAccountRel,
			String actionId)
		throws PortalException {

		if (contains(
				permissionChecker,
				commercePriceListAccountRel.getCommercePriceListAccountRelId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long commercePriceListAccountRelId, String actionId)
		throws PortalException {

		CommercePriceListAccountRel commercePriceListAccountRel =
			_commercePriceListAccountRelLocalService.
				fetchCommercePriceListAccountRel(commercePriceListAccountRelId);

		if (commercePriceListAccountRel == null) {
			return false;
		}

		return _contains(
			permissionChecker, commercePriceListAccountRel, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			long[] commercePriceListAccountRelIds, String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(commercePriceListAccountRelIds)) {
			return false;
		}

		for (long commercePriceListAccountRelId :
				commercePriceListAccountRelIds) {

			if (!contains(
					permissionChecker, commercePriceListAccountRelId,
					actionId)) {

				return false;
			}
		}

		return true;
	}

	private boolean _contains(
			PermissionChecker permissionChecker,
			CommercePriceListAccountRel commercePriceListAccountRel,
			String actionId)
		throws PortalException {

		if (permissionChecker.isCompanyAdmin(
				commercePriceListAccountRel.getCompanyId()) ||
			permissionChecker.isOmniadmin()) {

			return true;
		}

		if (permissionChecker.hasOwnerPermission(
				permissionChecker.getCompanyId(),
				CommercePriceList.class.getName(),
				commercePriceListAccountRel.getCommercePriceListAccountRelId(),
				permissionChecker.getUserId(), actionId) &&
			(commercePriceListAccountRel.getUserId() ==
				permissionChecker.getUserId())) {

			return true;
		}

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.fetchCommercePriceList(
				commercePriceListAccountRel.getCommercePriceListId());

		if (commercePriceList == null) {
			return false;
		}

		return permissionChecker.hasPermission(
			commercePriceList.getGroupId(), CommercePriceList.class.getName(),
			commercePriceList.getCommercePriceListId(), actionId);
	}

	@Reference
	private CommercePriceListAccountRelLocalService
		_commercePriceListAccountRelLocalService;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

}