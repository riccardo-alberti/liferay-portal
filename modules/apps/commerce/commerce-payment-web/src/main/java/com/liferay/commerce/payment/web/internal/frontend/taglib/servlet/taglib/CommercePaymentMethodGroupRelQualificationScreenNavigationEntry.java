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

package com.liferay.commerce.payment.web.internal.frontend.taglib.servlet.taglib;

import com.liferay.commerce.payment.constants.CommercePaymentScreenNavigationConstants;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.qualification.display.context.CommerceQualificationRelDisplayContext;
import com.liferay.commerce.qualification.entity.CommerceQualificationRelEntityRegistry;
import com.liferay.commerce.qualification.entity.SourceCommerceQualificationRelEntity;
import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.ResourceBundleUtil;

import java.io.IOException;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, property = "screen.navigation.entry.order:Integer=20",
	service = ScreenNavigationEntry.class
)
public class CommercePaymentMethodGroupRelQualificationScreenNavigationEntry
	implements ScreenNavigationEntry<CommercePaymentMethodGroupRel> {

	@Override
	public String getCategoryKey() {
		return CommercePaymentScreenNavigationConstants.
			CATEGORY_KEY_COMMERCE_PAYMENT_METHOD_DETAILS;
	}

	@Override
	public String getEntryKey() {
		return "qualifiers";
	}

	@Override
	public String getLabel(Locale locale) {
		ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
			"content.Language", locale, getClass());

		return LanguageUtil.get(resourceBundle, "eligibility");
	}

	@Override
	public String getScreenNavigationKey() {
		return CommercePaymentScreenNavigationConstants.
			SCREEN_NAVIGATION_KEY_COMMERCE_PAYMENT_METHOD;
	}

	@Override
	public boolean isVisible(
		User user,
		CommercePaymentMethodGroupRel commercePaymentMethodGroupRel) {

		if (commercePaymentMethodGroupRel == null) {
			return false;
		}

		SourceCommerceQualificationRelEntity
			sourceCommerceQualificationRelEntity =
				(SourceCommerceQualificationRelEntity)
					_commerceQualificationRelEntityRegistry.
						getCommerceQualificationRelEntity(
							CommercePaymentMethodGroupRel.class.getName());

		if (sourceCommerceQualificationRelEntity == null) {
			return false;
		}

		String[] availableTargetClassNames =
			sourceCommerceQualificationRelEntity.getAvailableTargetClassNames();

		if ((availableTargetClassNames == null) ||
			(availableTargetClassNames.length == 0)) {

			return false;
		}

		ModelResourcePermission<?> modelResourcePermission =
			sourceCommerceQualificationRelEntity.getModelResourcePermission();

		if (modelResourcePermission == null) {
			return true;
		}

		boolean hasPermission = false;

		try {
			hasPermission = modelResourcePermission.contains(
				PermissionThreadLocal.getPermissionChecker(),
				commercePaymentMethodGroupRel.
					getCommercePaymentMethodGroupRelId(),
				ActionKeys.UPDATE);
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

		CommerceQualificationRelDisplayContext
			commerceQualificationRelDisplayContext =
				new CommerceQualificationRelDisplayContext(
					_commerceQualificationRelEntityRegistry,
					httpServletRequest);

		httpServletRequest.setAttribute(
			"view.jsp-commerceQualificationRelDisplayContext",
			commerceQualificationRelDisplayContext);

		_jspRenderer.renderJSP(
			_servletContext, httpServletRequest, httpServletResponse,
			"/view.jsp");
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommercePaymentMethodGroupRelQualificationScreenNavigationEntry.class);

	@Reference
	private CommerceQualificationRelEntityRegistry
		_commerceQualificationRelEntityRegistry;

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference(
		target = "(osgi.web.symbolicname=com.liferay.commerce.qualification.web)"
	)
	private ServletContext _servletContext;

}