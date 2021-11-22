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

package com.liferay.headless.fulfilment.admin.internal.resource.v1_0;

import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.fulfilment.service.FulfilmentRequestService;
import com.liferay.fulfilment.service.FulfilmentTaskService;
import com.liferay.headless.fulfilment.admin.dto.v1_0.Request;
import com.liferay.headless.fulfilment.admin.dto.v1_0.Task;
import com.liferay.headless.fulfilment.admin.internal.dto.v1_0.converter.TaskDTOConverter;
import com.liferay.headless.fulfilment.admin.resource.v1_0.TaskResource;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.fields.NestedField;
import com.liferay.portal.vulcan.fields.NestedFieldSupport;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Alberti
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/task.properties",
	scope = ServiceScope.PROTOTYPE,
	service = {NestedFieldSupport.class, TaskResource.class}
)
public class TaskResourceImpl
	extends BaseTaskResourceImpl implements NestedFieldSupport {

	@NestedField(parentClass = Request.class, value = "tasks")
	@Override
	public Page<Task> getRequestIdTasksPage(Long id, Pagination pagination)
		throws Exception {

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestService.fetchFulfilmentRequest(id);

		if (fulfilmentRequest == null) {
			return Page.of(Collections.emptyList());
		}

		List<FulfilmentTask> fulfilmentTasks =
			_fulfilmentTaskService.getFulfilmentTasks(
				id, pagination.getStartPosition(), pagination.getEndPosition());

		int totalItems = _fulfilmentTaskService.getFulfilmentTasksCount(id);

		return Page.of(
			_toFulfilmentTasks(
				fulfilmentTasks, contextAcceptLanguage.getPreferredLocale()),
			pagination, totalItems);
	}

	private List<Task> _toFulfilmentTasks(
			List<FulfilmentTask> fulfilmentTasks, Locale locale)
		throws Exception {

		List<Task> tasks = new ArrayList<>();

		for (FulfilmentTask fulfilmentTask : fulfilmentTasks) {
			tasks.add(
				_taskDTOConverter.toDTO(
					new DefaultDTOConverterContext(
						fulfilmentTask.getFulfilmentTaskId(), locale)));
		}

		return tasks;
	}

	@Reference
	private FulfilmentRequestService _fulfilmentRequestService;

	@Reference
	private FulfilmentTaskService _fulfilmentTaskService;

	@Reference
	private TaskDTOConverter _taskDTOConverter;

}