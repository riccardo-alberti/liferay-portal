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

package com.liferay.fulfilment.internal.messaging;

import com.liferay.fulfilment.messaging.FulfilmentDestinationNames;
import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.portal.kernel.messaging.Destination;
import com.liferay.portal.kernel.messaging.DestinationConfiguration;
import com.liferay.portal.kernel.messaging.DestinationFactory;
import com.liferay.portal.kernel.util.HashMapDictionaryBuilder;

import java.util.Dictionary;

import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(immediate = true, service = FulfilmentMessagingConfigurator.class)
public class FulfilmentMessagingConfigurator {

	@Activate
	protected void activate(BundleContext bundleContext) {
		_fulfilmentTaskServiceRegistration = _registerDestination(
			bundleContext, FulfilmentDestinationNames.FULFILMENT_TASK,
			FulfilmentTask.class.getName());
	}

	@Deactivate
	protected void deactivate() {
		if (_fulfilmentTaskServiceRegistration != null) {
			_fulfilmentTaskServiceRegistration.unregister();
		}
	}

	private ServiceRegistration<Destination> _registerDestination(
		BundleContext bundleContext, String destinationName, String className) {

		DestinationConfiguration destinationConfiguration =
			DestinationConfiguration.createSerialDestinationConfiguration(
				destinationName);

		Destination destination = _destinationFactory.createDestination(
			destinationConfiguration);

		Dictionary<String, Object> dictionary =
			HashMapDictionaryBuilder.<String, Object>put(
				"destination.name", destination.getName()
			).put(
				"object.action.trigger.class.name", className
			).build();

		return bundleContext.registerService(
			Destination.class, destination, dictionary);
	}

	@Reference
	private DestinationFactory _destinationFactory;

	private volatile ServiceRegistration<Destination>
		_fulfilmentTaskServiceRegistration;

}