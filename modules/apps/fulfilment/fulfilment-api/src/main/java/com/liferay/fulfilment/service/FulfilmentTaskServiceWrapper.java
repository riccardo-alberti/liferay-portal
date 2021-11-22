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
 * Provides a wrapper for {@link FulfilmentTaskService}.
 *
 * @author Riccardo Alberti
 * @see FulfilmentTaskService
 * @generated
 */
public class FulfilmentTaskServiceWrapper
	implements FulfilmentTaskService, ServiceWrapper<FulfilmentTaskService> {

	public FulfilmentTaskServiceWrapper() {
		this(null);
	}

	public FulfilmentTaskServiceWrapper(
		FulfilmentTaskService fulfilmentTaskService) {

		_fulfilmentTaskService = fulfilmentTaskService;
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentTask getFulfilmentTask(
			long fulfilmentTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskService.getFulfilmentTask(fulfilmentTaskId);
	}

	@Override
	public java.util.List<com.liferay.fulfilment.model.FulfilmentTask>
			getFulfilmentTasks(long fulfilmentRequestId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskService.getFulfilmentTasks(
			fulfilmentRequestId, start, end);
	}

	@Override
	public int getFulfilmentTasksCount(long fulfilmentRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskService.getFulfilmentTasksCount(
			fulfilmentRequestId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fulfilmentTaskService.getOSGiServiceIdentifier();
	}

	@Override
	public FulfilmentTaskService getWrappedService() {
		return _fulfilmentTaskService;
	}

	@Override
	public void setWrappedService(FulfilmentTaskService fulfilmentTaskService) {
		_fulfilmentTaskService = fulfilmentTaskService;
	}

	private FulfilmentTaskService _fulfilmentTaskService;

}