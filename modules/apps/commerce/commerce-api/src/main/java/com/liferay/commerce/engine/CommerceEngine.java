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

import com.liferay.commerce.engine.task.CommerceEngineTask;

import java.util.List;

/**
 * @author Riccardo Alberti
 */
public interface CommerceEngine<T> {

	public <T> T executeTask(long groupId, String key, String type, T t)
		throws Exception;

	public void executeTasks(
			long groupId, CommerceEngineTask commerceEngineTask, T t)
		throws Exception;

	public void executeTasks(long groupId, String type, T t) throws Exception;

	public List<CommerceEngineTask> getNextCommerceEngineTasks(
			long groupId, CommerceEngineTask commerceEngineTask, T t)
		throws Exception;

}