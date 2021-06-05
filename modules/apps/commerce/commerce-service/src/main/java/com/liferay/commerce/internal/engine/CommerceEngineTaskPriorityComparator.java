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

package com.liferay.commerce.internal.engine;

import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.portal.kernel.util.MapUtil;

import java.io.Serializable;

import java.util.Comparator;

/**
 * @author Riccardo Alberti
 */
public class CommerceEngineTaskPriorityComparator
	implements Comparator
		<ServiceTrackerCustomizerFactory.ServiceWrapper<CommerceEngineTask>>,
			   Serializable {

	public CommerceEngineTaskPriorityComparator() {
		this(true);
	}

	public CommerceEngineTaskPriorityComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(
		ServiceTrackerCustomizerFactory.ServiceWrapper<CommerceEngineTask>
			serviceWrapper1,
		ServiceTrackerCustomizerFactory.ServiceWrapper<CommerceEngineTask>
			serviceWrapper2) {

		int commerceEngineTaskPriority1 = MapUtil.getInteger(
			serviceWrapper1.getProperties(), "commerce.engine.task.priority",
			Integer.MAX_VALUE);
		int commerceEngineTaskPriority2 = MapUtil.getInteger(
			serviceWrapper2.getProperties(), "commerce.engine.task.priority",
			Integer.MAX_VALUE);

		int value = Integer.compare(
			commerceEngineTaskPriority1, commerceEngineTaskPriority2);

		if (_ascending) {
			return value;
		}

		return Math.negateExact(value);
	}

	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}