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
import com.liferay.fulfilment.util.FulfilmentParametersUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.RandomUtil;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutorException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

import java.net.HttpURLConnection;
import java.net.URL;

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
public class PNIFulfilmentActionExecutor extends BaseFulfilmentActionExecutor {

	@Override
	protected int doExecute(
			Map<String, Serializable> inputParameters,
			Map<String, Serializable> outputParameters)
		throws ActionExecutorException {

		int i = RandomUtil.nextInt(10);

		if ((i % 2) == 0) {
			return FulfilmentConstants.STATUS_FAILED;
		}

		String command = (String)inputParameters.get("command");

		try {
			URL url = new URL(_URL + command);

			HttpURLConnection connection =
				(HttpURLConnection)url.openConnection();

			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			int status = connection.getResponseCode();
			BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder content = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}

			in.close();
			;
			outputParameters.putAll(
				FulfilmentParametersUtil.deserializeParameters(
					content.toString()));

			if (status == 200) {
				return FulfilmentConstants.STATUS_COMPLETED;
			}

			return FulfilmentConstants.STATUS_FAILED;
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new ActionExecutorException(exception);
		}
	}

	private static final String _URL =
		"https://1dfcd9ea-7c64-47d4-9ba0-28a667f7e605.mock.pstmn.io/";

	private static final Log _log = LogFactoryUtil.getLog(
		PNIFulfilmentActionExecutor.class);

}