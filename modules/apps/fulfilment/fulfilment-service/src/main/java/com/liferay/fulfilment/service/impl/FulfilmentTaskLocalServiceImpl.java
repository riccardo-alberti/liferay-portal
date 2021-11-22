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

import com.liferay.fulfilment.constants.FulfilmentConstants;
import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.fulfilment.service.base.FulfilmentTaskLocalServiceBaseImpl;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = "model.class.name=com.liferay.fulfilment.model.FulfilmentTask",
	service = AopService.class
)
public class FulfilmentTaskLocalServiceImpl
	extends FulfilmentTaskLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FulfilmentTask addFulfilmentTask(
			long userId, long correlationId, long fulfilmentRequestId,
			String type, ServiceContext serviceContext)
		throws PortalException {

		FulfilmentTask fulfilmentTask = fulfilmentTaskPersistence.create(
			counterLocalService.increment());

		User user = userLocalService.getUser(userId);

		fulfilmentTask.setCompanyId(user.getCompanyId());
		fulfilmentTask.setUserId(user.getUserId());
		fulfilmentTask.setUserName(user.getFullName());

		fulfilmentTask.setCorrelationId(correlationId);
		fulfilmentTask.setFulfilmentRequestId(fulfilmentRequestId);
		fulfilmentTask.setIndex(
			counterLocalService.increment(
				"FulfilmentTask_" + fulfilmentRequestId));

		fulfilmentTask.setType(type);

		Date now = new Date();

		fulfilmentTask.setStartDate(now);

		fulfilmentTask.setStatus(FulfilmentConstants.STATUS_STARTED);
		fulfilmentTask.setStatusByUserId(user.getUserId());
		fulfilmentTask.setStatusDate(serviceContext.getModifiedDate(now));

		return fulfilmentTaskPersistence.update(fulfilmentTask);
	}

	@Override
	public FulfilmentTask getFulfilmentTaskByCorrelationId(long correlationId)
		throws PortalException {

		return fulfilmentTaskPersistence.findByCorrelationId(correlationId);
	}

	@Override
	public List<FulfilmentTask> getFulfilmentTasks(
			long fulfilmentRequestId, int start, int end)
		throws PortalException {

		return fulfilmentTaskPersistence.findByFulfilmentRequestId(
			fulfilmentRequestId, start, end);
	}

	@Override
	public int getFulfilmentTasksCount(long fulfilmentRequestId)
		throws PortalException {

		return fulfilmentTaskPersistence.countByFulfilmentRequestId(
			fulfilmentRequestId);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FulfilmentTask updateFulfilmentTask(
			long userId, long fulfilmentTaskId, String inputParameters,
			String outputParameters, int status, ServiceContext serviceContext)
		throws PortalException {

		FulfilmentTask fulfilmentTask =
			fulfilmentTaskPersistence.findByPrimaryKey(fulfilmentTaskId);

		User user = userLocalService.getUser(userId);

		fulfilmentTask.setUserId(user.getUserId());

		fulfilmentTask.setEndDate(new Date());

		fulfilmentTask.setInputParameters(inputParameters);
		fulfilmentTask.setOutputParameters(outputParameters);
		fulfilmentTask.setStatus(status);

		fulfilmentTask.setStatusByUserId(user.getUserId());
		fulfilmentTask.setStatusDate(
			serviceContext.getModifiedDate(new Date()));

		return fulfilmentTaskPersistence.update(fulfilmentTask);
	}

}