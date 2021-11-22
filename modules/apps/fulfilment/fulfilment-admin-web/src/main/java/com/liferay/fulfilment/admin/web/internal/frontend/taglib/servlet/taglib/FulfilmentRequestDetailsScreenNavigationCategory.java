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

package com.liferay.fulfilment.admin.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationCategory;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.fulfilment.admin.web.internal.constants.FulfilmentRequestScreenNavigationEntryConstants;
import com.liferay.fulfilment.admin.web.internal.display.context.FulfilmentRequestDisplayContext;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.FulfilmentRequestService;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	property = {
		"screen.navigation.category.order:Integer=10",
		"screen.navigation.entry.order:Integer=10"
	},
	service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class FulfilmentRequestDetailsScreenNavigationCategory
	implements ScreenNavigationCategory,
			   ScreenNavigationEntry<FulfilmentRequest> {

	@Override
	public String getCategoryKey() {
		return FulfilmentRequestScreenNavigationEntryConstants.
			CATEGORY_KEY_DETAILS;
	}

	@Override
	public String getEntryKey() {
		return FulfilmentRequestScreenNavigationEntryConstants.
			CATEGORY_KEY_DETAILS;
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(
			resourceBundle,
			FulfilmentRequestScreenNavigationEntryConstants.
				CATEGORY_KEY_DETAILS);
	}

	@Override
	public String getScreenNavigationKey() {
		return FulfilmentRequestScreenNavigationEntryConstants.
			SCREEN_NAVIGATION_KEY_FULFILMENT_REQUEST_GENERAL;
	}

	@Override
	public boolean isVisible(User user, FulfilmentRequest fulfilmentRequest) {
		if (fulfilmentRequest == null) {
			return false;
		}

		boolean hasPermission = false;

		try {
			hasPermission = _fulfilmentRequestModelResourcePermission.contains(
				PermissionThreadLocal.getPermissionChecker(),
				fulfilmentRequest.getFulfilmentRequestId(), ActionKeys.UPDATE);
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}

		return hasPermission;
	}

	@Override
	public void render(
			HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse)
		throws IOException {

		FulfilmentRequestDisplayContext fulfilmentRequestDisplayContext =
			new FulfilmentRequestDisplayContext(
				_fulfilmentRequestModelResourcePermission,
				_fulfilmentRequestService, _workflowDefinitionLinkLocalService,
				httpServletRequest, _portal);

		httpServletRequest.setAttribute(
			WebKeys.PORTLET_DISPLAY_CONTEXT, fulfilmentRequestDisplayContext);

		_jspRenderer.renderJSP(
			httpServletRequest, httpServletResponse,
			"/fulfilment_request/details.jsp");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FulfilmentRequestDetailsScreenNavigationCategory.class);

	@Reference(
		target = "(model.class.name=com.liferay.fulfilment.model.FulfilmentRequest)"
	)
	private ModelResourcePermission<FulfilmentRequest>
		_fulfilmentRequestModelResourcePermission;

	@Reference
	private FulfilmentRequestService _fulfilmentRequestService;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private Portal _portal;

	@Reference
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}