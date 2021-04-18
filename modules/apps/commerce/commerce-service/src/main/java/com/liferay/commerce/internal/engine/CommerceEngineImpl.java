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

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.engine.CommerceEngine;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.engine.task.CommerceEngineTaskRegistry;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.ListUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(enabled = false, immediate = true, service = CommerceEngine.class)
public class CommerceEngineImpl implements CommerceEngine {

	@Override
	public Map<String, Object> executeTask(
			long companyId, CommerceContext commerceContext, String key,
			String type, long userId)
		throws Exception {

		Map<String, Object> context = HashMapBuilder.<String, Object>put(
			"_commerceContext", commerceContext
		).put(
			"_companyId", companyId
		).put(
			"_type", type
		).put(
			"_userId", userId
		).build();

		return executeTask(context, key, type);
	}

	@Override
	public Map<String, Object> executeTask(
			Map<String, Object> context, String key, String type)
		throws Exception {

		Optional<CommerceEngineTask> commerceEngineTaskOptional =
			_commerceEngineTaskRegistry.getCommerceEngineTask(key, type);

		if (!commerceEngineTaskOptional.isPresent()) {
			return context;
		}

		CommerceEngineTask commerceEngineTask =
			commerceEngineTaskOptional.get();

		try {
			if (!commerceEngineTask.isActive(context) ||
				!commerceEngineTask.evaluate(context)) {

				return context;
			}

			commerceEngineTask.execute(context);
		}
		catch (Exception exception) {
			commerceEngineTask.handleException(exception, context);
		}

		return context;
	}

	@Override
	public void executeTasks(
			CommerceEngineTask commerceEngineTask, Map<String, Object> context)
		throws Exception {

		Optional<CommerceEngineTask> commerceEngineTaskOptional = Optional.of(
			commerceEngineTask);

		_executeTasks(commerceEngineTaskOptional, context);
	}

	@Override
	public void executeTasks(
			long companyId, CommerceContext commerceContext, String type,
			long userId)
		throws Exception {

		Map<String, Object> context = HashMapBuilder.<String, Object>put(
			"_commerceContext", commerceContext
		).put(
			"_companyId", companyId
		).put(
			"_type", type
		).put(
			"_userId", userId
		).build();

		List<CommerceEngineTask> commerceEngineTasks =
			_commerceEngineTaskRegistry.getCommerceEngineTasks(type);

		Optional<CommerceEngineTask> commerceEngineTaskOptional =
			Optional.ofNullable(commerceEngineTasks.get(0));

		_executeTasks(commerceEngineTaskOptional, context);
	}

	@Override
	public void executeTasks(Map<String, Object> context, String type)
		throws Exception {

		List<CommerceEngineTask> commerceEngineTasks =
			_commerceEngineTaskRegistry.getCommerceEngineTasks(type);

		Optional<CommerceEngineTask> commerceEngineTaskOptional =
			Optional.ofNullable(commerceEngineTasks.get(0));

		_executeTasks(commerceEngineTaskOptional, context);
	}

	@Override
	public List<CommerceEngineTask> getNextCommerceEngineTasks(
			CommerceEngineTask commerceEngineTask, Map<String, Object> context)
		throws Exception {

		List<CommerceEngineTask> nextCommerceEngineTasks = new ArrayList<>();

		Optional<CommerceEngineTask> nextCommerceEngineTaskOptional =
			commerceEngineTask.getNext(context);

		int i = 0;

		while (nextCommerceEngineTaskOptional.isPresent() ||
			   (i < _TASKS_LIMIT)) {

			CommerceEngineTask nextCommerceEngineTask =
				nextCommerceEngineTaskOptional.get();

			nextCommerceEngineTasks.add(nextCommerceEngineTask);

			nextCommerceEngineTaskOptional = nextCommerceEngineTask.getNext(
				context);

			if (!nextCommerceEngineTaskOptional.isPresent()) {
				nextCommerceEngineTaskOptional =
					_commerceEngineTaskRegistry.getNextCommerceEngineTask(
						nextCommerceEngineTask);
			}

			i++;
		}

		return ListUtil.filter(
			nextCommerceEngineTasks, e -> e.isActive(context));
	}

	private void _executeTasks(
			Optional<CommerceEngineTask> commerceEngineTaskOptional,
			Map<String, Object> context)
		throws Exception {

		while (commerceEngineTaskOptional.isPresent()) {
			CommerceEngineTask curCommerceEngineTask =
				commerceEngineTaskOptional.get();

			try {
				if (!curCommerceEngineTask.isActive(context)) {
					commerceEngineTaskOptional =
						_commerceEngineTaskRegistry.getNextCommerceEngineTask(
							curCommerceEngineTask);

					continue;
				}

				if (curCommerceEngineTask.evaluate(context)) {
					curCommerceEngineTask.execute(context);
				}

				commerceEngineTaskOptional = curCommerceEngineTask.getNext(
					context);

				if (!commerceEngineTaskOptional.isPresent()) {
					commerceEngineTaskOptional =
						_commerceEngineTaskRegistry.getNextCommerceEngineTask(
							curCommerceEngineTask);
				}
			}
			catch (Exception exception) {
				commerceEngineTaskOptional =
					curCommerceEngineTask.handleException(exception, context);
			}
		}
	}

	private static final int _TASKS_LIMIT = 100;

	@Reference
	private CommerceEngineTaskRegistry _commerceEngineTaskRegistry;

}