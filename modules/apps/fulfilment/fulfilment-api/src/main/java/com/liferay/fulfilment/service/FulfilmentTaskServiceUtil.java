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

import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * Provides the remote service utility for FulfilmentTask. This utility wraps
 * <code>com.liferay.fulfilment.service.impl.FulfilmentTaskServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Riccardo Alberti
 * @see FulfilmentTaskService
 * @generated
 */
public class FulfilmentTaskServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.fulfilment.service.impl.FulfilmentTaskServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static FulfilmentTask getFulfilmentTask(long fulfilmentTaskId)
		throws PortalException {

		return getService().getFulfilmentTask(fulfilmentTaskId);
	}

	public static List<FulfilmentTask> getFulfilmentTasks(
			long fulfilmentRequestId, int start, int end)
		throws PortalException {

		return getService().getFulfilmentTasks(fulfilmentRequestId, start, end);
	}

	public static int getFulfilmentTasksCount(long fulfilmentRequestId)
		throws PortalException {

		return getService().getFulfilmentTasksCount(fulfilmentRequestId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static FulfilmentTaskService getService() {
		return _service;
	}

	private static volatile FulfilmentTaskService _service;

}