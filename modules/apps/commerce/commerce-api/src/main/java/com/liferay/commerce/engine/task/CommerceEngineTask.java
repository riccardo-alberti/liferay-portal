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

package com.liferay.commerce.engine.task;

import java.util.Locale;
import java.util.Optional;

/**
 * @author Riccardo Alberti
 */
public interface CommerceEngineTask<T> {

	public boolean evaluate(long groupId, T t);

	public void execute(T t) throws Exception;

	public String getDescription(Locale locale);

	public String getEvaluateCondition(long groupId);

	public String getKey();

	public String getLabel(Locale locale);

	public Optional<CommerceEngineTask> getNext(T t) throws Exception;

	public int getPriority();

	public String getType();

	public Optional<CommerceEngineTask> handleException(
			Exception exception, T t)
		throws Exception;

	public boolean isActive(long groupId);

	public boolean isWorkflowEnabled(T t);

	public void updateEvaluateCondition(long groupId, String evaluateCondition);

	public void updateStatus(long groupId);

}