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

package com.liferay.fulfilment.internal.manager;

import com.liferay.fulfilment.constants.FulfilmentConstants;
import com.liferay.fulfilment.manager.FulfilmentManager;
import com.liferay.fulfilment.manager.FulfilmentManagerUtil;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.FulfilmentRequestLocalService;
import com.liferay.fulfilment.util.FulfilmentParametersUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.Map;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(immediate = true, service = FulfilmentManager.class)
public class FulfilmentManagerImpl implements FulfilmentManager {

	@Override
	public Map<String, Serializable> syncExecuteWorkflow(
			long companyId, long userId,
			Map<String, Serializable> inputParameters, String replyTo,
			String type)
		throws Exception {

		if (Validator.isBlank(replyTo)) {
			replyTo = FulfilmentConstants.REPLY_TO_INTERNAL;
		}

		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userId);

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestLocalService.addFulfilmentRequest(
				null, userId, StringPool.BLANK,
				FulfilmentParametersUtil.serializeParameters(inputParameters),
				replyTo, type, serviceContext);

		fulfilmentRequest =
			_fulfilmentRequestLocalService.startWorkflowInstance(
				userId, fulfilmentRequest, serviceContext);

		return FulfilmentParametersUtil.deserializeParameters(
			fulfilmentRequest.getOutputParameters());
	}

	@Activate
	protected void activate(BundleContext bundleContext) {
		FulfilmentManagerUtil.setFulfilmentManager(this);
	}

	@Reference
	private FulfilmentRequestLocalService _fulfilmentRequestLocalService;

	@Reference
	private JSONFactory _jsonFactory;

}