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

package com.liferay.headless.fulfilment.admin.internal.dto.v1_0.converter;

import com.liferay.fulfilment.constants.FulfilmentConstants;
import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.fulfilment.service.FulfilmentTaskService;
import com.liferay.headless.fulfilment.admin.dto.v1_0.Task;
import com.liferay.headless.fulfilment.admin.internal.util.DTOConverterUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.language.LanguageResources;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = "dto.class.name=com.liferay.fulfilment.model.FulfilmentTask",
	service = {DTOConverter.class, TaskDTOConverter.class}
)
public class TaskDTOConverter implements DTOConverter<FulfilmentTask, Task> {

	@Override
	public String getContentType() {
		return Task.class.getSimpleName();
	}

	@Override
	public Task toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		FulfilmentTask fulfilmentTask =
			_fulfilmentTaskService.getFulfilmentTask(
				(Long)dtoConverterContext.getId());

		return new Task() {
			{
				createDate = fulfilmentTask.getCreateDate();
				endDate = fulfilmentTask.getEndDate();
				id = fulfilmentTask.getFulfilmentTaskId();
				index = fulfilmentTask.getIndex();
				inputParameters = DTOConverterUtil.toParameters(
					fulfilmentTask.getInputParameters());
				outputParameters = DTOConverterUtil.toParameters(
					fulfilmentTask.getOutputParameters());
				requestId = fulfilmentTask.getFulfilmentRequestId();
				startDate = fulfilmentTask.getStartDate();
				type = fulfilmentTask.getType();
				workflowStatusInfo = DTOConverterUtil.toStatus(
					FulfilmentConstants.getStatusLabel(
						fulfilmentTask.getStatus()),
					LanguageUtil.get(
						LanguageResources.getResourceBundle(
							dtoConverterContext.getLocale()),
						FulfilmentConstants.getStatusLabel(
							fulfilmentTask.getStatus())),
					fulfilmentTask.getStatus());
			}
		};
	}

	@Reference
	private FulfilmentTaskService _fulfilmentTaskService;

}