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

package com.liferay.fulfilment.action.executor.internal;

import com.liferay.fulfilment.action.executor.BaseFulfilmentActionExecutor;
import com.liferay.fulfilment.constants.FulfilmentConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutorException;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	immediate = true,
	property = "com.liferay.portal.workflow.kaleo.runtime.action.executor.language=java",
	service = ActionExecutor.class
)
public class LogFulfilmentActionExecutor extends BaseFulfilmentActionExecutor {

	@Override
	protected String[] addToFulfilmentRequestOutputParameters() {
		return new String[] {
			FulfilmentConstants.ACTION_EXECUTOR_FULFILMENT_REQUEST_ID
		};
	}

	@Override
	protected int doExecute(
			Map<String, Serializable> inputParameters,
			Map<String, Serializable> outputParameters)
		throws ActionExecutorException {

		_log.info(inputParameters);

		return FulfilmentConstants.STATUS_COMPLETED;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		LogFulfilmentActionExecutor.class);

}