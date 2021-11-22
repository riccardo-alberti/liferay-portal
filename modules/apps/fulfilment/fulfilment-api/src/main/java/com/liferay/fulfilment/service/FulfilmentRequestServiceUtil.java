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

package com.liferay.fulfilment.service;

import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.portal.kernel.exception.PortalException;

/**
 * Provides the remote service utility for FulfilmentRequest. This utility wraps
 * <code>com.liferay.fulfilment.service.impl.FulfilmentRequestServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Riccardo Alberti
 * @see FulfilmentRequestService
 * @generated
 */
public class FulfilmentRequestServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.fulfilment.service.impl.FulfilmentRequestServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static FulfilmentRequest addFulfilmentRequest(
			String externalReferenceCode, String originalFulfilmentRequest,
			String inputParameters, String replyTo, String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addFulfilmentRequest(
			externalReferenceCode, originalFulfilmentRequest, inputParameters,
			replyTo, type, serviceContext);
	}

	public static FulfilmentRequest deleteFulfilmentRequest(
			long fulfilmentRequestId)
		throws PortalException {

		return getService().deleteFulfilmentRequest(fulfilmentRequestId);
	}

	public static FulfilmentRequest fetchByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		return getService().fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	public static FulfilmentRequest fetchFulfilmentRequest(
			long fulfilmentRequestId)
		throws PortalException {

		return getService().fetchFulfilmentRequest(fulfilmentRequestId);
	}

	public static FulfilmentRequest getFulfilmentRequest(
			long fulfilmentRequestId)
		throws PortalException {

		return getService().getFulfilmentRequest(fulfilmentRequestId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static FulfilmentRequest startWorkflowInstance(
			long userId, FulfilmentRequest fulfilmentRequest,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().startWorkflowInstance(
			userId, fulfilmentRequest, serviceContext);
	}

	public static FulfilmentRequest updateFulfilmentRequest(
			long fulfilmentRequestId, String originalFulfilmentRequest,
			String inputParameters, String replyTo, String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateFulfilmentRequest(
			fulfilmentRequestId, originalFulfilmentRequest, inputParameters,
			replyTo, type, serviceContext);
	}

	public static FulfilmentRequestService getService() {
		return _service;
	}

	private static volatile FulfilmentRequestService _service;

}