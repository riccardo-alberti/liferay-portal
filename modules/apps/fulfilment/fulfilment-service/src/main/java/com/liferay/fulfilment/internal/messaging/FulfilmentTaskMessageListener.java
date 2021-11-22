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

package com.liferay.fulfilment.internal.messaging;

import com.liferay.fulfilment.messaging.FulfilmentDestinationNames;
import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.fulfilment.service.FulfilmentTaskLocalService;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.messaging.BaseMessageListener;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageListener;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.Date;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	immediate = true,
	property = "destination.name=" + FulfilmentDestinationNames.FULFILMENT_TASK,
	service = MessageListener.class
)
public class FulfilmentTaskMessageListener extends BaseMessageListener {

	@Override
	protected void doReceive(Message message) throws Exception {
		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			String.valueOf(message.getPayload()));

		String command = jsonObject.getString("command");
		long userId = jsonObject.getLong("userId");

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setModifiedDate(new Date());

		long correlationId = jsonObject.getLong("correlationId");

		if (Objects.equals(command, "addFulfilmentTask")) {
			long fulfilmentRequestId = jsonObject.getLong(
				"fulfilmentRequestId");
			String type = jsonObject.getString("type");

			FulfilmentTask fulfilmentTask =
				_fulfilmentTaskLocalService.addFulfilmentTask(
					userId, correlationId, fulfilmentRequestId, type,
					serviceContext);

			message.setResponse(fulfilmentTask.getFulfilmentTaskId());

			return;
		}

		String inputParameters = jsonObject.getString("inputParameters");
		String outputParameters = jsonObject.getString("outputParameters");
		int status = jsonObject.getInt("status");

		FulfilmentTask fulfilmentTask =
			_fulfilmentTaskLocalService.getFulfilmentTaskByCorrelationId(
				correlationId);

		_fulfilmentTaskLocalService.updateFulfilmentTask(
			userId, fulfilmentTask.getFulfilmentTaskId(), inputParameters,
			outputParameters, status, serviceContext);
	}

	@Reference
	private FulfilmentTaskLocalService _fulfilmentTaskLocalService;

}