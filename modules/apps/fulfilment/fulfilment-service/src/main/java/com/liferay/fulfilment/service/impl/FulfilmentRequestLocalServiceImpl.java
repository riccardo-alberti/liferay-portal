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

package com.liferay.fulfilment.service.impl;

import com.liferay.fulfilment.constants.FulfilmentConstants;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.base.FulfilmentRequestLocalServiceBaseImpl;
import com.liferay.fulfilment.util.FulfilmentParametersUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = "model.class.name=com.liferay.fulfilment.model.FulfilmentRequest",
	service = AopService.class
)
public class FulfilmentRequestLocalServiceImpl
	extends FulfilmentRequestLocalServiceBaseImpl {

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FulfilmentRequest addFulfilmentRequest(
			String externalReferenceCode, long userId,
			String originalFulfilmentRequest, String inputParameters,
			String replyTo, String type, ServiceContext serviceContext)
		throws PortalException {

		FulfilmentRequest fulfilmentRequest =
			fulfilmentRequestPersistence.create(
				counterLocalService.increment());

		// TODO request validation

		fulfilmentRequest.setExternalReferenceCode(externalReferenceCode);

		User user = userLocalService.getUser(userId);

		fulfilmentRequest.setCompanyId(user.getCompanyId());
		fulfilmentRequest.setUserId(user.getUserId());
		fulfilmentRequest.setUserName(user.getFullName());

		fulfilmentRequest.setOriginalFulfilmentRequest(
			originalFulfilmentRequest);
		fulfilmentRequest.setInputParameters(inputParameters);

		fulfilmentRequest.setReplyTo(replyTo);
		fulfilmentRequest.setType(type);

		fulfilmentRequest.setWorkflowDefinitionLinkId(0);

		fulfilmentRequest.setStatusByUserId(user.getUserId());
		fulfilmentRequest.setStatusDate(
			serviceContext.getModifiedDate(new Date()));

		fulfilmentRequest = fulfilmentRequestPersistence.update(
			fulfilmentRequest);

		resourceLocalService.addModelResources(
			fulfilmentRequest, serviceContext);

		return fulfilmentRequest;
	}

	@Indexable(type = IndexableType.DELETE)
	@Override
	public FulfilmentRequest deleteFulfilmentRequest(long fulfilmentRequestId)
		throws PortalException {

		FulfilmentRequest fulfilmentRequest =
			fulfilmentRequestPersistence.findByPrimaryKey(fulfilmentRequestId);

		fulfilmentTaskPersistence.removeByFulfilmentRequestId(
			fulfilmentRequest.getFulfilmentRequestId());

		return fulfilmentRequestLocalService.deleteFulfilmentRequest(
			fulfilmentRequest);
	}

	@Override
	public FulfilmentRequest startWorkflowInstance(
			long userId, FulfilmentRequest fulfilmentRequest,
			ServiceContext serviceContext)
		throws PortalException {

		fulfilmentRequest.setStartDate(new Date());
		fulfilmentRequest.setStatus(FulfilmentConstants.STATUS_STARTED);

		fulfilmentRequest = fulfilmentRequestPersistence.update(
			fulfilmentRequest);

		Map<String, Serializable> workflowContext = new HashMap<>();

		workflowContext.put(
			FulfilmentConstants.ACTION_EXECUTOR_FULFILMENT_REQUEST_ID,
			fulfilmentRequest.getFulfilmentRequestId());

		workflowContext.putAll(
			FulfilmentParametersUtil.deserializeParameters(
				fulfilmentRequest.getInputParameters()));

		return WorkflowHandlerRegistryUtil.startWorkflowInstance(
			fulfilmentRequest.getCompanyId(), 0L, userId,
			FulfilmentRequest.class.getName(),
			fulfilmentRequest.getFulfilmentRequestId(), fulfilmentRequest,
			serviceContext, workflowContext);
	}

	@Override
	public FulfilmentRequest updateFulfilmentRequest(
			long userId, long fulfilmentRequestId,
			String originalFulfilmentRequest, String inputParameters,
			String replyTo, String type, ServiceContext serviceContext)
		throws PortalException {

		FulfilmentRequest fulfilmentRequest =
			fulfilmentRequestPersistence.findByPrimaryKey(fulfilmentRequestId);

		fulfilmentRequest.setOriginalFulfilmentRequest(
			originalFulfilmentRequest);

		fulfilmentRequest.setInputParameters(inputParameters);

		fulfilmentRequest.setReplyTo(replyTo);
		fulfilmentRequest.setType(type);

		fulfilmentRequest.setStatusByUserId(userId);
		fulfilmentRequest.setStatusDate(
			serviceContext.getModifiedDate(new Date()));

		return fulfilmentRequestPersistence.update(fulfilmentRequest);
	}

	@Override
	public FulfilmentRequest updateFulfilmentRequest(
			long fulfilmentRequestId, String outputParameters)
		throws PortalException {

		FulfilmentRequest fulfilmentRequest =
			fulfilmentRequestPersistence.findByPrimaryKey(fulfilmentRequestId);

		fulfilmentRequest.setInputParameters(outputParameters);

		return fulfilmentRequestPersistence.update(fulfilmentRequest);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FulfilmentRequest updateStatus(
			long userId, long fulfilmentRequestId, int status,
			ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		FulfilmentRequest fulfilmentRequest =
			fulfilmentRequestPersistence.findByPrimaryKey(fulfilmentRequestId);

		fulfilmentRequest.setStatus(status);

		User user = userLocalService.getUser(userId);

		fulfilmentRequest.setStatusByUserId(user.getUserId());
		fulfilmentRequest.setStatusByUserName(user.getFullName());

		fulfilmentRequest.setStatusDate(
			serviceContext.getModifiedDate(new Date()));

		if (status <= 0) {
			fulfilmentRequest.setEndDate(new Date());
		}

		return fulfilmentRequestPersistence.update(fulfilmentRequest);
	}

	@Indexable(type = IndexableType.REINDEX)
	@Override
	public FulfilmentRequest updateWorkflowDefinitionLink(
			long fulfilmentRequestId, long workflowDefinitionLinkId)
		throws PortalException {

		FulfilmentRequest fulfilmentRequest =
			fulfilmentRequestPersistence.findByPrimaryKey(fulfilmentRequestId);

		fulfilmentRequest.setWorkflowDefinitionLinkId(workflowDefinitionLinkId);

		return fulfilmentRequestPersistence.update(fulfilmentRequest);
	}

	@Reference
	private JSONFactory _jsonFactory;

}