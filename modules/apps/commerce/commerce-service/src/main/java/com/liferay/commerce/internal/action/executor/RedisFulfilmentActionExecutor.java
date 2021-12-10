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

package com.liferay.commerce.internal.action.executor;

import com.liferay.commerce.internal.price.util.PriceValue;
import com.liferay.fulfilment.action.executor.BaseFulfilmentActionExecutor;
import com.liferay.fulfilment.constants.FulfilmentConstants;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutorException;
import com.liferay.redis.RedisConnection;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = "com.liferay.portal.workflow.kaleo.runtime.action.executor.language=java",
	service = ActionExecutor.class
)
public class RedisFulfilmentActionExecutor
	extends BaseFulfilmentActionExecutor {

	@Override
	protected int doExecute(
			Map<String, Serializable> inputParameters,
			Map<String, Serializable> outputParameters)
		throws ActionExecutorException {

		HashMap<String, Map<Integer, PriceValue>> priceMap =
			(HashMap<String, Map<Integer, PriceValue>>)GetterUtil.getObject(
				inputParameters.get("priceMap"), new HashMap<>());

		for (String key : priceMap.keySet()) {
			_redisConnection.set(key, priceMap.get(key));
		}

		return FulfilmentConstants.STATUS_COMPLETED;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RedisFulfilmentActionExecutor.class);

	@Reference
	private RedisConnection _redisConnection;

}