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

package com.liferay.headless.fulfilment.admin.internal.dto.v1_0.converter;

import com.liferay.fulfilment.constants.FulfilmentConstants;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.FulfilmentRequestService;
import com.liferay.headless.fulfilment.admin.dto.v1_0.Request;
import com.liferay.headless.fulfilment.admin.internal.util.DTOConverterUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.language.LanguageResources;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = "dto.class.name=com.liferay.fulfilment.model.FulfilmentRequest",
	service = {DTOConverter.class, RequestDTOConverter.class}
)
public class RequestDTOConverter
	implements DTOConverter<FulfilmentRequest, Request> {

	@Override
	public String getContentType() {
		return Request.class.getSimpleName();
	}

	@Override
	public Request toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestService.getFulfilmentRequest(
				(Long)dtoConverterContext.getId());

		return new Request() {
			{
				actions = dtoConverterContext.getActions();
				createDate = fulfilmentRequest.getCreateDate();
				endDate = fulfilmentRequest.getEndDate();
				externalReferenceCode =
					fulfilmentRequest.getExternalReferenceCode();
				id = fulfilmentRequest.getFulfilmentRequestId();
				inputParameters = DTOConverterUtil.toParameters(
					fulfilmentRequest.getInputParameters());
				outputParameters = DTOConverterUtil.toParameters(
					fulfilmentRequest.getOutputParameters());
				startDate = fulfilmentRequest.getStartDate();
				type = fulfilmentRequest.getType();
				workflowDefinition = _toWorkflowDefinition(
					fulfilmentRequest, dtoConverterContext.getLocale());
				workflowStatusInfo = DTOConverterUtil.toStatus(
					FulfilmentConstants.getStatusLabel(
						fulfilmentRequest.getStatus()),
					LanguageUtil.get(
						LanguageResources.getResourceBundle(
							dtoConverterContext.getLocale()),
						FulfilmentConstants.getStatusLabel(
							fulfilmentRequest.getStatus())),
					fulfilmentRequest.getStatus());
			}
		};
	}

	private String _toWorkflowDefinition(
		FulfilmentRequest fulfilmentRequest, Locale locale) {

		WorkflowDefinitionLink workflowDefinitionLink =
			_workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
				fulfilmentRequest.getWorkflowDefinitionLinkId());

		if (workflowDefinitionLink == null) {
			return LanguageUtil.get(
				LanguageResources.getResourceBundle(locale),
				"no-workflow-has-been-executed");
		}

		return workflowDefinitionLink.getWorkflowDefinitionName() +
			StringPool.AT +
				workflowDefinitionLink.getWorkflowDefinitionVersion();
	}

	@Reference
	private FulfilmentRequestService _fulfilmentRequestService;

	@Reference
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}