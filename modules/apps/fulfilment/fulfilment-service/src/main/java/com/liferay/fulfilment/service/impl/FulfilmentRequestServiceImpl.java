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

package com.liferay.fulfilment.service.impl;

import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.base.FulfilmentRequestServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = {
		"json.web.service.context.name=fulfilment",
		"json.web.service.context.path=FulfilmentRequest"
	},
	service = AopService.class
)
public class FulfilmentRequestServiceImpl
	extends FulfilmentRequestServiceBaseImpl {

	@Override
	public FulfilmentRequest addFulfilmentRequest(
			String externalReferenceCode, String originalFulfilmentRequest,
			String inputParameters, String replyTo, String type,
			ServiceContext serviceContext)
		throws PortalException {

		PortletResourcePermission portletResourcePermission =
			_fulfilmentRequestModelResourcePermission.
				getPortletResourcePermission();

		portletResourcePermission.check(
			getPermissionChecker(), null, "ADD_FULFILMENT_REQUEST");

		return fulfilmentRequestLocalService.addFulfilmentRequest(
			externalReferenceCode, getUserId(), originalFulfilmentRequest,
			inputParameters, replyTo, type, serviceContext);
	}

	@Override
	public FulfilmentRequest deleteFulfilmentRequest(long fulfilmentRequestId)
		throws PortalException {

		_fulfilmentRequestModelResourcePermission.check(
			getPermissionChecker(), fulfilmentRequestId, ActionKeys.DELETE);

		return fulfilmentRequestLocalService.deleteFulfilmentRequest(
			fulfilmentRequestId);
	}

	@Override
	public FulfilmentRequest fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		FulfilmentRequest fulfilmentRequest =
			fulfilmentRequestLocalService.
				fetchFulfilmentRequestByExternalReferenceCode(
					companyId, externalReferenceCode);

		if (fulfilmentRequest != null) {
			_fulfilmentRequestModelResourcePermission.check(
				getPermissionChecker(), fulfilmentRequest, ActionKeys.VIEW);
		}

		return fulfilmentRequest;
	}

	@Override
	public FulfilmentRequest fetchFulfilmentRequest(long fulfilmentRequestId)
		throws PortalException {

		_fulfilmentRequestModelResourcePermission.check(
			getPermissionChecker(), fulfilmentRequestId, ActionKeys.VIEW);

		return fulfilmentRequestLocalService.fetchFulfilmentRequest(
			fulfilmentRequestId);
	}

	@Override
	public FulfilmentRequest getFulfilmentRequest(long fulfilmentRequestId)
		throws PortalException {

		_fulfilmentRequestModelResourcePermission.check(
			getPermissionChecker(), fulfilmentRequestId, ActionKeys.VIEW);

		return fulfilmentRequestLocalService.getFulfilmentRequest(
			fulfilmentRequestId);
	}

	@Override
	public FulfilmentRequest startWorkflowInstance(
			long userId, FulfilmentRequest fulfilmentRequest,
			ServiceContext serviceContext)
		throws PortalException {

		_fulfilmentRequestModelResourcePermission.check(
			getPermissionChecker(), fulfilmentRequest.getFulfilmentRequestId(),
			ActionKeys.UPDATE);

		return fulfilmentRequestLocalService.startWorkflowInstance(
			userId, fulfilmentRequest, serviceContext);
	}

	@Override
	public FulfilmentRequest updateFulfilmentRequest(
			long fulfilmentRequestId, String originalFulfilmentRequest,
			String inputParameters, String replyTo, String type,
			ServiceContext serviceContext)
		throws PortalException {

		_fulfilmentRequestModelResourcePermission.check(
			getPermissionChecker(), fulfilmentRequestId, ActionKeys.UPDATE);

		return fulfilmentRequestLocalService.updateFulfilmentRequest(
			getUserId(), fulfilmentRequestId, originalFulfilmentRequest,
			inputParameters, replyTo, type, serviceContext);
	}

	private static volatile ModelResourcePermission<FulfilmentRequest>
		_fulfilmentRequestModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				FulfilmentRequestServiceImpl.class,
				"_fulfilmentRequestModelResourcePermission",
				FulfilmentRequest.class);

}