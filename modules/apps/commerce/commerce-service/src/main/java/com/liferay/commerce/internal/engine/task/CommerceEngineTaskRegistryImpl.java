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

package com.liferay.commerce.internal.engine.task;

import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.engine.task.CommerceEngineTaskRegistry;
import com.liferay.commerce.internal.engine.CommerceEngineTaskPriorityComparator;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerCustomizerFactory;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMap;
import com.liferay.osgi.service.tracker.collections.map.ServiceTrackerMapFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	service = CommerceEngineTaskRegistry.class
)
public class CommerceEngineTaskRegistryImpl
	implements CommerceEngineTaskRegistry {

	@Override
	public Optional<CommerceEngineTask> getCommerceEngineTask(
		String key, String type) {

		List<CommerceEngineTask> commerceEngineTasks = getCommerceEngineTasks(
			type);

		Stream<CommerceEngineTask> stream = commerceEngineTasks.stream();

		return stream.filter(
			e -> Objects.equals(e.getKey(), key)
		).findFirst();
	}

	@Override
	public List<CommerceEngineTask> getCommerceEngineTasks(String type) {
		List<ServiceTrackerCustomizerFactory.ServiceWrapper<CommerceEngineTask>>
			service = _serviceTrackerMap.getService(type);

		if (service == null) {
			Collections.emptyList();
		}

		List<ServiceTrackerCustomizerFactory.ServiceWrapper<CommerceEngineTask>>
			commerceEngineTaskServiceWrappers = new ArrayList<>(service);

		List<CommerceEngineTask> commerceEngineTasks = new ArrayList<>();

		Collections.sort(
			commerceEngineTaskServiceWrappers,
			_commerceEngineTaskPriorityComparator);

		for (ServiceTrackerCustomizerFactory.ServiceWrapper<CommerceEngineTask>
				commerceEngineTaskServiceWrapper :
					commerceEngineTaskServiceWrappers) {

			commerceEngineTasks.add(
				commerceEngineTaskServiceWrapper.getService());
		}

		return commerceEngineTasks;
	}

	@Override
	public Optional<CommerceEngineTask> getNextCommerceEngineTask(
		CommerceEngineTask commerceEngineTask) {

		List<CommerceEngineTask> commerceEngineTasks = getCommerceEngineTasks(
			commerceEngineTask.getType());

		int i = commerceEngineTasks.indexOf(commerceEngineTask);

		if (i > commerceEngineTasks.size()) {
			Optional.empty();
		}

		return Optional.of(commerceEngineTasks.get(i + 1));
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		_serviceTrackerMap = ServiceTrackerMapFactory.openMultiValueMap(
			bundleContext, CommerceEngineTask.class,
			"commerce.engine.task.type",
			ServiceTrackerCustomizerFactory.<CommerceEngineTask>serviceWrapper(
				bundleContext));
	}

	@Deactivate
	protected void deactivate() {
		_serviceTrackerMap.close();
	}

	private final Comparator
		<ServiceTrackerCustomizerFactory.ServiceWrapper<CommerceEngineTask>>
			_commerceEngineTaskPriorityComparator =
				new CommerceEngineTaskPriorityComparator();
	private ServiceTrackerMap
		<String,
		 List
			 <ServiceTrackerCustomizerFactory.ServiceWrapper
				 <CommerceEngineTask>>> _serviceTrackerMap;

}