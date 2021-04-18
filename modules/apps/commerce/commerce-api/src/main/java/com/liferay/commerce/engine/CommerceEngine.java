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

package com.liferay.commerce.engine;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.engine.task.CommerceEngineTask;

import java.util.List;
import java.util.Map;

/**
 * @author Riccardo Alberti
 */
public interface CommerceEngine {

	public Map<String, Object> executeTask(
			long companyId, CommerceContext commerceContext, String key,
			String type, long userId)
		throws Exception;

	public Map<String, Object> executeTask(
			Map<String, Object> context, String key, String type)
		throws Exception;

	public void executeTasks(
			CommerceEngineTask commerceEngineTask, Map<String, Object> context)
		throws Exception;

	public void executeTasks(
			long companyId, CommerceContext commerceContext, String type,
			long userId)
		throws Exception;

	public void executeTasks(Map<String, Object> context, String type)
		throws Exception;

	public List<CommerceEngineTask> getNextCommerceEngineTasks(
			CommerceEngineTask commerceEngineTask, Map<String, Object> context)
		throws Exception;

}