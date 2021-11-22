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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FulfilmentRequestService}.
 *
 * @author Riccardo Alberti
 * @see FulfilmentRequestService
 * @generated
 */
public class FulfilmentRequestServiceWrapper
	implements FulfilmentRequestService,
			   ServiceWrapper<FulfilmentRequestService> {

	public FulfilmentRequestServiceWrapper() {
		this(null);
	}

	public FulfilmentRequestServiceWrapper(
		FulfilmentRequestService fulfilmentRequestService) {

		_fulfilmentRequestService = fulfilmentRequestService;
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest addFulfilmentRequest(
			String externalReferenceCode, String originalFulfilmentRequest,
			String inputParameters, String replyTo, String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestService.addFulfilmentRequest(
			externalReferenceCode, originalFulfilmentRequest, inputParameters,
			replyTo, type, serviceContext);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
			deleteFulfilmentRequest(long fulfilmentRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestService.deleteFulfilmentRequest(
			fulfilmentRequestId);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestService.fetchByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
			fetchFulfilmentRequest(long fulfilmentRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestService.fetchFulfilmentRequest(
			fulfilmentRequestId);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest getFulfilmentRequest(
			long fulfilmentRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestService.getFulfilmentRequest(
			fulfilmentRequestId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fulfilmentRequestService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest startWorkflowInstance(
			long userId,
			com.liferay.fulfilment.model.FulfilmentRequest fulfilmentRequest,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestService.startWorkflowInstance(
			userId, fulfilmentRequest, serviceContext);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
			updateFulfilmentRequest(
				long fulfilmentRequestId, String originalFulfilmentRequest,
				String inputParameters, String replyTo, String type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestService.updateFulfilmentRequest(
			fulfilmentRequestId, originalFulfilmentRequest, inputParameters,
			replyTo, type, serviceContext);
	}

	@Override
	public FulfilmentRequestService getWrappedService() {
		return _fulfilmentRequestService;
	}

	@Override
	public void setWrappedService(
		FulfilmentRequestService fulfilmentRequestService) {

		_fulfilmentRequestService = fulfilmentRequestService;
	}

	private FulfilmentRequestService _fulfilmentRequestService;

}