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

import com.liferay.commerce.engine.CommerceEngine;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.engine.task.CommerceEngineTaskRegistry;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(enabled = false, immediate = true, service = CommerceEngine.class)
public class CommerceEngineImpl<T> implements CommerceEngine<T> {

	@Override
	public <T> T executeTask(long groupId, String key, String type, T t)
		throws Exception {

		T result = t;

		Optional<CommerceEngineTask> commerceEngineTaskOptional =
			_commerceEngineTaskRegistry.getCommerceEngineTask(key, type);

		if (commerceEngineTaskOptional.isPresent()) {
			CommerceEngineTask commerceEngineTask =
				commerceEngineTaskOptional.get();

			try {
				if (commerceEngineTask.isActive(groupId) &&
					commerceEngineTask.evaluate(groupId, t)) {

					commerceEngineTask.execute(t);
				}
			}
			catch (Exception exception) {
				commerceEngineTask.handleException(exception, t);
			}
		}

		return result;
	}

	@Override
	public void executeTasks(
			long groupId, CommerceEngineTask commerceEngineTask, T t)
		throws Exception {

		Optional<CommerceEngineTask> commerceEngineTaskOptional = Optional.of(
			commerceEngineTask);

		_executeTasks(groupId, commerceEngineTaskOptional, t);
	}

	@Override
	public void executeTasks(long groupId, String type, T t) throws Exception {
		List<CommerceEngineTask> commerceEngineTasks =
			_commerceEngineTaskRegistry.getCommerceEngineTasks(type);

		Optional<CommerceEngineTask> commerceEngineTaskOptional =
			Optional.ofNullable(commerceEngineTasks.get(0));

		_executeTasks(groupId, commerceEngineTaskOptional, t);
	}

	@Override
	public List<CommerceEngineTask> getNextCommerceEngineTasks(
			long groupId, CommerceEngineTask commerceEngineTask, T t)
		throws Exception {

		List<CommerceEngineTask> nextCommerceEngineTasks = new ArrayList<>();

		Optional<CommerceEngineTask> nextCommerceEngineTaskOptional =
			commerceEngineTask.getNext(t);

		int i = 0;

		while (nextCommerceEngineTaskOptional.isPresent() ||
			   (i < _TASKS_LIMIT)) {

			CommerceEngineTask nextCommerceEngineTask =
				nextCommerceEngineTaskOptional.get();

			nextCommerceEngineTasks.add(nextCommerceEngineTask);

			nextCommerceEngineTaskOptional = nextCommerceEngineTask.getNext(t);

			if (!nextCommerceEngineTaskOptional.isPresent()) {
				nextCommerceEngineTaskOptional =
					_commerceEngineTaskRegistry.getNextCommerceEngineTask(
						nextCommerceEngineTask);
			}

			i++;
		}

		return ListUtil.filter(
			nextCommerceEngineTasks, e -> e.isActive(groupId));
	}

	private void _executeTasks(
			long groupId,
			Optional<CommerceEngineTask> commerceEngineTaskOptional, T t)
		throws Exception {

		while (commerceEngineTaskOptional.isPresent()) {
			CommerceEngineTask curCommerceEngineTask =
				commerceEngineTaskOptional.get();

			try {
				if (!curCommerceEngineTask.isActive(groupId)) {
					commerceEngineTaskOptional =
						_commerceEngineTaskRegistry.getNextCommerceEngineTask(
							curCommerceEngineTask);

					continue;
				}

				if (curCommerceEngineTask.evaluate(groupId, t)) {
					curCommerceEngineTask.execute(t);
				}

				commerceEngineTaskOptional = curCommerceEngineTask.getNext(t);

				if (!commerceEngineTaskOptional.isPresent()) {
					commerceEngineTaskOptional =
						_commerceEngineTaskRegistry.getNextCommerceEngineTask(
							curCommerceEngineTask);
				}
			}
			catch (Exception exception) {
				commerceEngineTaskOptional =
					curCommerceEngineTask.handleException(exception, t);
			}
		}
	}

	private static final int _TASKS_LIMIT = 100;

	@Reference
	private CommerceEngineTaskRegistry _commerceEngineTaskRegistry;

}