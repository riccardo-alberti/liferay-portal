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
import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.fulfilment.service.base.FulfilmentTaskServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;

import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = {
		"json.web.service.context.name=fulfilment",
		"json.web.service.context.path=FulfilmentTask"
	},
	service = AopService.class
)
public class FulfilmentTaskServiceImpl extends FulfilmentTaskServiceBaseImpl {

	@Override
	public FulfilmentTask getFulfilmentTask(long fulfilmentTaskId)
		throws PortalException {

		FulfilmentTask fulfilmentTask =
			fulfilmentTaskLocalService.fetchFulfilmentTask(fulfilmentTaskId);

		if (fulfilmentTask != null) {
			_fulfilmentRequestModelResourcePermission.check(
				getPermissionChecker(), fulfilmentTask.getFulfilmentRequestId(),
				ActionKeys.VIEW);
		}

		return fulfilmentTask;
	}

	@Override
	public List<FulfilmentTask> getFulfilmentTasks(
			long fulfilmentRequestId, int start, int end)
		throws PortalException {

		_fulfilmentRequestModelResourcePermission.check(
			getPermissionChecker(), fulfilmentRequestId, ActionKeys.VIEW);

		return fulfilmentTaskLocalService.getFulfilmentTasks(
			fulfilmentRequestId, start, end);
	}

	@Override
	public int getFulfilmentTasksCount(long fulfilmentRequestId)
		throws PortalException {

		_fulfilmentRequestModelResourcePermission.check(
			getPermissionChecker(), fulfilmentRequestId, ActionKeys.VIEW);

		return fulfilmentTaskLocalService.getFulfilmentTasksCount(
			fulfilmentRequestId);
	}

	private static volatile ModelResourcePermission<FulfilmentRequest>
		_fulfilmentRequestModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				FulfilmentTaskServiceImpl.class,
				"_fulfilmentRequestModelResourcePermission",
				FulfilmentRequest.class);

}