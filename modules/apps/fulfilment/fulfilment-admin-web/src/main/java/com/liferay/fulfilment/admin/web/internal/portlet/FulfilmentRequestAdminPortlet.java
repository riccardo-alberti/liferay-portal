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

package com.liferay.fulfilment.admin.web.internal.portlet;

import com.liferay.fulfilment.admin.web.internal.constants.FulfilmentAdminPortletKeys;
import com.liferay.fulfilment.admin.web.internal.display.context.FulfilmentRequestDisplayContext;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.FulfilmentRequestService;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true",
		"com.liferay.portlet.css-class-wrapper=portlet-fulfilment-admin",
		"com.liferay.portlet.display-category=category.hidden",
		"com.liferay.portlet.layout-cacheable=true",
		"com.liferay.portlet.preferences-owned-by-group=true",
		"com.liferay.portlet.preferences-unique-per-layout=false",
		"com.liferay.portlet.private-request-attributes=false",
		"com.liferay.portlet.private-session-attributes=false",
		"com.liferay.portlet.render-weight=50",
		"com.liferay.portlet.scopeable=true",
		"javax.portlet.display-name=Requests",
		"javax.portlet.expiration-cache=0",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + FulfilmentAdminPortletKeys.FULFILMENT_REQUEST_ADMIN_PORTLET,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = {FulfilmentRequestAdminPortlet.class, Portlet.class}
)
public class FulfilmentRequestAdminPortlet extends MVCPortlet {

	@Override
	public void render(
			RenderRequest renderRequest, RenderResponse renderResponse)
		throws IOException, PortletException {

		FulfilmentRequestDisplayContext fulfilmentRequestDisplayContext =
			new FulfilmentRequestDisplayContext(
				_fulfilmentRequestModelResourcePermission,
				_fulfilmentRequestService, _workflowDefinitionLinkLocalService,
				_portal.getHttpServletRequest(renderRequest), _portal);

		renderRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT, fulfilmentRequestDisplayContext);

		super.render(renderRequest, renderResponse);
	}

	@Reference(
		target = "(model.class.name=com.liferay.fulfilment.model.FulfilmentRequest)"
	)
	private ModelResourcePermission<FulfilmentRequest>
		_fulfilmentRequestModelResourcePermission;

	@Reference
	private FulfilmentRequestService _fulfilmentRequestService;

	@Reference
	private Portal _portal;

	@Reference
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}