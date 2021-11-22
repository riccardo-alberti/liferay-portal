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

package com.liferay.fulfilment.internal.workflow;

import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.FulfilmentRequestLocalService;
import com.liferay.fulfilment.workflow.helper.FulfilmentWorkflowHelper;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.BaseWorkflowHandler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandler;

import java.io.Serializable;

import java.util.Locale;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = "model.class.name=com.liferay.fulfilment.model.FulfilmentRequest",
	service = WorkflowHandler.class
)
public class FulfilmentRequestWorkflowHandler
	extends BaseWorkflowHandler<FulfilmentRequest> {

	@Override
	public String getClassName() {
		return FulfilmentRequest.class.getName();
	}

	@Override
	public String getType(Locale locale) {
		return ResourceActionsUtil.getModelResource(locale, getClassName());
	}

	@Override
	public WorkflowDefinitionLink getWorkflowDefinitionLink(
			long companyId, long groupId, long classPK)
		throws PortalException {

		return _fulfilmentWorkflowHelper.getWorkflowDefinitionLink(
			companyId, groupId, classPK);
	}

	@Override
	public boolean isVisible() {
		return _VISIBLE;
	}

	@Override
	public void startWorkflowInstance(
			long companyId, long groupId, long userId, long classPK,
			FulfilmentRequest model, Map<String, Serializable> workflowContext)
		throws PortalException {

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestLocalService.getFulfilmentRequest(classPK);

		boolean waitForCompletion = true;

		if (Validator.isBlank(fulfilmentRequest.getReplyTo())) {
			waitForCompletion = false;
		}

		_workflowInstanceLinkLocalService.startWorkflowInstance(
			companyId, groupId, userId, getClassName(), classPK,
			workflowContext, waitForCompletion);
	}

	@Override
	public FulfilmentRequest updateStatus(
			int status, Map<String, Serializable> workflowContext)
		throws PortalException {

		long userId = GetterUtil.getLong(
			(String)workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));
		long classPK = GetterUtil.getLong(
			(String)workflowContext.get(
				WorkflowConstants.CONTEXT_ENTRY_CLASS_PK));

		ServiceContext serviceContext = (ServiceContext)workflowContext.get(
			"serviceContext");

		return _fulfilmentRequestLocalService.updateStatus(
			userId, classPK, status, serviceContext, workflowContext);
	}

	private static final boolean _VISIBLE = false;

	@Reference
	private FulfilmentRequestLocalService _fulfilmentRequestLocalService;

	@Reference
	private FulfilmentWorkflowHelper _fulfilmentWorkflowHelper;

	@Reference
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

	@Reference
	private WorkflowInstanceLinkLocalService _workflowInstanceLinkLocalService;

}