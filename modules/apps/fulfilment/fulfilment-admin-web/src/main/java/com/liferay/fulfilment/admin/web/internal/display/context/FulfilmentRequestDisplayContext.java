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

package com.liferay.fulfilment.admin.web.internal.display.context;

import com.liferay.frontend.taglib.clay.data.set.servlet.taglib.util.ClayDataSetActionDropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.fulfilment.admin.web.internal.constants.FulfilmentAdminPortletKeys;
import com.liferay.fulfilment.admin.web.internal.display.context.util.FulfilmentRequestRequestHelper;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.FulfilmentRequestService;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.PortletResourcePermission;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Alessio Antonio Rendina
 */
public class FulfilmentRequestDisplayContext {

	public FulfilmentRequestDisplayContext(
		ModelResourcePermission<FulfilmentRequest>
			fulfilmentRequestModelResourcePermission,
		FulfilmentRequestService fulfilmentRequestService,
		WorkflowDefinitionLinkLocalService workflowDefinitionLinkLocalService,
		HttpServletRequest httpServletRequest, Portal portal) {

		_fulfilmentRequestModelResourcePermission =
			fulfilmentRequestModelResourcePermission;
		_fulfilmentRequestService = fulfilmentRequestService;
		_workflowDefinitionLinkLocalService =
			workflowDefinitionLinkLocalService;
		this.httpServletRequest = httpServletRequest;
		this.portal = portal;

		fulfilmentRequestRequestHelper = new FulfilmentRequestRequestHelper(
			httpServletRequest);
	}

	public String getAddFulfilmentRequestRenderURL() throws Exception {
		return PortletURLBuilder.createRenderURL(
			fulfilmentRequestRequestHelper.getLiferayPortletResponse()
		).setMVCRenderCommandName(
			"/fulfilment_request/add_fulfilment_request"
		).setWindowState(
			LiferayWindowState.POP_UP
		).buildString();
	}

	public CreationMenu getCreationMenu() throws Exception {
		CreationMenu creationMenu = new CreationMenu();

		if (hasAddPermission()) {
			creationMenu.addDropdownItem(
				dropdownItem -> {
					dropdownItem.setHref(getAddFulfilmentRequestRenderURL());
					dropdownItem.setLabel(
						LanguageUtil.get(
							fulfilmentRequestRequestHelper.getRequest(),
							"add-request"));
					dropdownItem.setTarget("modal");
				});
		}

		return creationMenu;
	}

	public PortletURL getEditFulfilmentRequestRenderURL() {
		return PortletURLBuilder.create(
			portal.getControlPanelPortletURL(
				fulfilmentRequestRequestHelper.getRequest(),
				FulfilmentAdminPortletKeys.FULFILMENT_REQUEST_ADMIN_PORTLET,
				PortletRequest.RENDER_PHASE)
		).setMVCRenderCommandName(
			"/fulfilment_request/edit_fulfilment_request"
		).buildPortletURL();
	}

	public FulfilmentRequest getFulfilmentRequest() throws PortalException {
		long fulfilmentRequestId = ParamUtil.getLong(
			fulfilmentRequestRequestHelper.getRequest(), "fulfilmentRequestId");

		if (fulfilmentRequestId == 0) {
			return null;
		}

		return _fulfilmentRequestService.fetchFulfilmentRequest(
			fulfilmentRequestId);
	}

	public List<ClayDataSetActionDropdownItem>
			getFulfilmentRequestClayDataSetActionDropdownItems()
		throws PortalException {

		return ListUtil.fromArray(
			new ClayDataSetActionDropdownItem(
				PortletURLBuilder.create(
					PortletProviderUtil.getPortletURL(
						httpServletRequest, FulfilmentRequest.class.getName(),
						PortletProvider.Action.MANAGE)
				).setMVCRenderCommandName(
					"/fulfilment_request/edit_fulfilment_request"
				).setRedirect(
					fulfilmentRequestRequestHelper.getCurrentURL()
				).setParameter(
					"fulfilmentRequestId", "{id}"
				).buildString(),
				"pencil", "edit", LanguageUtil.get(httpServletRequest, "edit"),
				"get", null, null),
			new ClayDataSetActionDropdownItem(
				null, "trash", "delete",
				LanguageUtil.get(httpServletRequest, "delete"), "delete",
				"delete", "headless"),
			new ClayDataSetActionDropdownItem(
				_getManagePermissionsURL(), null, "permissions",
				LanguageUtil.get(httpServletRequest, "permissions"), "get",
				"permissions", "modal-permissions"));
	}

	public long getFulfilmentRequestId() throws PortalException {
		FulfilmentRequest fulfilmentRequest = getFulfilmentRequest();

		if (fulfilmentRequest == null) {
			return 0;
		}

		return fulfilmentRequest.getFulfilmentRequestId();
	}

	public String getFulfilmentTaskApiUrl() throws PortalException {
		return "/o/headless-fulfilment-admin/v1.0/requests/" +
			getFulfilmentRequestId() + "/tasks";
	}

	public PortletURL getPortletURL() {
		LiferayPortletResponse liferayPortletResponse =
			fulfilmentRequestRequestHelper.getLiferayPortletResponse();

		PortletURL portletURL = liferayPortletResponse.createRenderURL();

		String redirect = ParamUtil.getString(httpServletRequest, "redirect");

		if (Validator.isNotNull(redirect)) {
			portletURL.setParameter("redirect", redirect);
		}

		long fulfilmentRequestId = ParamUtil.getLong(
			httpServletRequest, "fulfilmentRequestId");

		if (fulfilmentRequestId > 0) {
			portletURL.setParameter(
				"fulfilmentRequestId", String.valueOf(fulfilmentRequestId));
		}

		return portletURL;
	}

	public String getWorkflowDefinition() throws PortalException {
		FulfilmentRequest fulfilmentRequest = getFulfilmentRequest();

		WorkflowDefinitionLink workflowDefinitionLink =
			_workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
				fulfilmentRequest.getWorkflowDefinitionLinkId());

		if (workflowDefinitionLink == null) {
			return "no-workflow-has-been-executed";
		}

		return workflowDefinitionLink.getWorkflowDefinitionName() +
			StringPool.AT +
				workflowDefinitionLink.getWorkflowDefinitionVersion();
	}

	public boolean hasAddPermission() throws PortalException {
		PortletResourcePermission portletResourcePermission =
			_fulfilmentRequestModelResourcePermission.
				getPortletResourcePermission();

		return portletResourcePermission.contains(
			fulfilmentRequestRequestHelper.getPermissionChecker(), null,
			"ADD_FULFILMENT_REQUEST");
	}

	public boolean hasPermission(String actionId) throws PortalException {
		return _fulfilmentRequestModelResourcePermission.contains(
			fulfilmentRequestRequestHelper.getPermissionChecker(),
			getFulfilmentRequestId(), actionId);
	}

	protected final FulfilmentRequestRequestHelper
		fulfilmentRequestRequestHelper;
	protected final HttpServletRequest httpServletRequest;
	protected final Portal portal;

	private String _getManagePermissionsURL() throws PortalException {
		return PortletURLBuilder.create(
			portal.getControlPanelPortletURL(
				httpServletRequest,
				"com_liferay_portlet_configuration_web_portlet_" +
					"PortletConfigurationPortlet",
				ActionRequest.RENDER_PHASE)
		).setMVCPath(
			"/edit_permissions.jsp"
		).setRedirect(
			fulfilmentRequestRequestHelper.getCurrentURL()
		).setParameter(
			"modelResource", FulfilmentRequest.class.getName()
		).setParameter(
			"modelResourceDescription", "{name}"
		).setParameter(
			"resourcePrimKey", "{id}"
		).setWindowState(
			LiferayWindowState.POP_UP
		).buildString();
	}

	private final ModelResourcePermission<FulfilmentRequest>
		_fulfilmentRequestModelResourcePermission;
	private final FulfilmentRequestService _fulfilmentRequestService;
	private final WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}