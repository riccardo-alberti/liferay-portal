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

package com.liferay.fulfilment.action.executor;

import com.liferay.fulfilment.constants.FulfilmentConstants;
import com.liferay.fulfilment.messaging.FulfilmentDestinationNames;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.FulfilmentRequestLocalService;
import com.liferay.fulfilment.service.FulfilmentTaskLocalService;
import com.liferay.fulfilment.util.FulfilmentParametersUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.kaleo.model.KaleoAction;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutorException;

import java.io.Serializable;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
public abstract class BaseFulfilmentActionExecutor implements ActionExecutor {

	@Override
	public void execute(
			KaleoAction kaleoAction, ExecutionContext executionContext)
		throws ActionExecutorException {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();

		long userId = GetterUtil.getLong(
			workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));

		Map<String, Serializable> inputParameters = _prepareActionParameters(
			kaleoAction, workflowContext);

		Map<String, Serializable> outputParameters = new HashMap<>();

		int status = FulfilmentConstants.STATUS_FAILED;

		long fulfilmentRequestId = GetterUtil.getLong(
			workflowContext.get(
				FulfilmentConstants.ACTION_EXECUTOR_FULFILMENT_REQUEST_ID));

		long correlationId = System.nanoTime();

		try {
			_sendAddFulfilmentTaskMessage(
				userId, correlationId, fulfilmentRequestId,
				kaleoAction.getName());

			try {
				status = doExecute(inputParameters, outputParameters);

				// Remove keywords as they cannot be set in an action

				Set<String> outputParametersKeySet = outputParameters.keySet();

				outputParametersKeySet.removeIf(
					key -> key.startsWith(
						FulfilmentConstants.ACTION_EXECUTOR_KEYWORDS_PREFIX));

				outputParameters.put(
					FulfilmentConstants.ACTION_EXECUTOR_STATUS, status);

				Collection<Serializable> outputParametersValues =
					outputParameters.values();

				outputParametersValues.removeIf(Objects::isNull);

				workflowContext.putAll(outputParameters);

				_addToFulfilmentRequestOutputParameters(
					fulfilmentRequestId, outputParameters);
			}
			catch (ActionExecutorException actionExecutorException) {
				_log.error(actionExecutorException, actionExecutorException);

				outputParameters.clear();

				outputParameters.put(
					FulfilmentConstants.ACTION_EXECUTOR_EXCEPTION,
					actionExecutorException.getMessage());

				throw new ActionExecutorException();
			}
		}
		catch (PortalException portalException) {
			_log.error(portalException, portalException);

			if (portalException instanceof ActionExecutorException) {
				throw new ActionExecutorException();
			}
		}
		finally {
			_sendUpdateFulfilmentTaskMessage(
				userId, correlationId,
				FulfilmentParametersUtil.serializeParameters(inputParameters),
				FulfilmentParametersUtil.serializeParameters(outputParameters),
				status);
		}
	}

	protected String[] addToFulfilmentRequestOutputParameters() {
		return new String[0];
	}

	protected abstract int doExecute(
			Map<String, Serializable> inputParameters,
			Map<String, Serializable> outputParameters)
		throws ActionExecutorException;

	@Reference
	protected FulfilmentRequestLocalService fulfilmentRequestLocalService;

	@Reference
	protected FulfilmentTaskLocalService fulfilmentTaskLocalService;

	private void _addToFulfilmentRequestOutputParameters(
		long fulfilmentRequestId, Map<String, Serializable> outputParameters) {

		FulfilmentRequest fulfilmentRequest =
			fulfilmentRequestLocalService.fetchFulfilmentRequest(
				fulfilmentRequestId);

		String[] addToFulfilmentRequestOutputParameters =
			addToFulfilmentRequestOutputParameters();

		try {
			Map<String, Serializable> stringSerializableMap =
				FulfilmentParametersUtil.deserializeParameters(
					fulfilmentRequest.getOutputParameters());

			for (String addToFulfilmentRequestOutputParameter :
					addToFulfilmentRequestOutputParameters) {

				stringSerializableMap.put(
					addToFulfilmentRequestOutputParameter,
					outputParameters.get(
						addToFulfilmentRequestOutputParameter));
			}

			fulfilmentRequestLocalService.updateFulfilmentRequest(
				fulfilmentRequestId,
				FulfilmentParametersUtil.serializeParameters(
					stringSerializableMap));
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}
	}

	private Map<String, Serializable> _prepareActionParameters(
		KaleoAction kaleoAction, Map<String, Serializable> workflowContext) {

		// taking from description until a proper implementation is done

		Map<String, Serializable> actionParameters = new HashMap<>();

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
				kaleoAction.getDescription());

			for (String key : jsonObject.keySet()) {
				Matcher matcher = _pattern.matcher(jsonObject.getString(key));

				if (matcher.find()) {
					String group = matcher.group(1);

					Serializable workflowContextValue = workflowContext.get(
						group);

					if (workflowContextValue != null) {
						actionParameters.put(key, workflowContext.get(group));
					}
				}
				else {
					actionParameters.put(
						key, (Serializable)jsonObject.get(key));
				}
			}
		}
		catch (JSONException jsonException) {
			_log.error(jsonException, jsonException);
		}
		finally {
			return actionParameters;
		}
	}

	private void _sendAddFulfilmentTaskMessage(
		long userId, long correlationId, long fulfilmentRequestId,
		String type) {

		Message message = new Message();

		message.setPayload(
			JSONUtil.put(
				"command", "addFulfilmentTask"
			).put(
				"correlationId", correlationId
			).put(
				"fulfilmentRequestId", fulfilmentRequestId
			).put(
				"type", type
			).put(
				"userId", userId
			));

		MessageBusUtil.sendMessage(
			FulfilmentDestinationNames.FULFILMENT_TASK, message);
	}

	private void _sendUpdateFulfilmentTaskMessage(
		long userId, long correlationId, String inputParameters,
		String outputParameters, int status) {

		Message message = new Message();

		message.setPayload(
			JSONUtil.put(
				"command", "updateFulfilmentTask"
			).put(
				"correlationId", correlationId
			).put(
				"inputParameters", inputParameters
			).put(
				"outputParameters", outputParameters
			).put(
				"status", status
			).put(
				"userId", userId
			));

		MessageBusUtil.sendMessage(
			FulfilmentDestinationNames.FULFILMENT_TASK, message);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		BaseFulfilmentActionExecutor.class);

	private static final Pattern _pattern = Pattern.compile("^\\$\\{(.*)\\}");

}