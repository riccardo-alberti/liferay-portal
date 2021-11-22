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

package com.liferay.fulfilment.internal.permission;

import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.permission.FulfilmentRequestPermission;
import com.liferay.fulfilment.service.FulfilmentRequestLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.util.ArrayUtil;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Alessio Antonio Rendina
 */
@Component(immediate = true, service = FulfilmentRequestPermission.class)
public class FulfilmentRequestPermissionImpl
	implements FulfilmentRequestPermission {

	@Override
	public void check(
			PermissionChecker permissionChecker,
			FulfilmentRequest fulfilmentRequest, String actionId)
		throws PortalException {

		if (!contains(permissionChecker, fulfilmentRequest, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, FulfilmentRequest.class.getName(),
				fulfilmentRequest.getFulfilmentRequestId(), actionId);
		}
	}

	@Override
	public void check(
			PermissionChecker permissionChecker, long fulfilmentRequestId,
			String actionId)
		throws PortalException {

		if (!contains(permissionChecker, fulfilmentRequestId, actionId)) {
			throw new PrincipalException.MustHavePermission(
				permissionChecker, FulfilmentRequest.class.getName(),
				fulfilmentRequestId, actionId);
		}
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker,
			FulfilmentRequest fulfilmentRequest, String actionId)
		throws PortalException {

		if (contains(
				permissionChecker, fulfilmentRequest.getFulfilmentRequestId(),
				actionId)) {

			return true;
		}

		return false;
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long fulfilmentRequestId,
			String actionId)
		throws PortalException {

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestLocalService.fetchFulfilmentRequest(
				fulfilmentRequestId);

		if (fulfilmentRequest == null) {
			return false;
		}

		return _contains(permissionChecker, fulfilmentRequest, actionId);
	}

	@Override
	public boolean contains(
			PermissionChecker permissionChecker, long[] fulfilmentRequestIds,
			String actionId)
		throws PortalException {

		if (ArrayUtil.isEmpty(fulfilmentRequestIds)) {
			return false;
		}

		for (long fulfilmentRequestId : fulfilmentRequestIds) {
			if (!contains(permissionChecker, fulfilmentRequestId, actionId)) {
				return false;
			}
		}

		return true;
	}

	private boolean _contains(
		PermissionChecker permissionChecker,
		FulfilmentRequest fulfilmentRequest, String actionId) {

		if (permissionChecker.isCompanyAdmin(
				fulfilmentRequest.getCompanyId()) ||
			permissionChecker.isOmniadmin() ||
			permissionChecker.hasOwnerPermission(
				fulfilmentRequest.getCompanyId(),
				FulfilmentRequest.class.getName(),
				fulfilmentRequest.getFulfilmentRequestId(),
				fulfilmentRequest.getUserId(), actionId)) {

			return true;
		}

		return permissionChecker.hasPermission(
			null, FulfilmentRequest.class.getName(),
			fulfilmentRequest.getFulfilmentRequestId(), actionId);
	}

	@Reference
	private FulfilmentRequestLocalService _fulfilmentRequestLocalService;

}