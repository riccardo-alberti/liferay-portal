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

package com.liferay.commerce.segments.extension.web.internal.field.customizer;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.ClassedModel;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.security.permission.ResourceActionsUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.segments.constants.SegmentsPortletKeys;
import com.liferay.segments.field.Field;
import com.liferay.segments.field.customizer.SegmentsFieldCustomizer;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stefano Puzzuoli
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"segments.field.customizer.entity.name=CommerceAccount",
		"segments.field.customizer.key=" + CommerceAccountSegmentsFieldCustomizer.KEY,
		"segments.field.customizer.priority:Integer=600"
	},
	service = SegmentsFieldCustomizer.class
)
public class CommerceAccountSegmentsFieldCustomizer
	implements SegmentsFieldCustomizer {

	public static final String KEY = "commerceAccount";

	@Override
	public ClassedModel getClassedModel(String fieldValue) {
		return _getCommerceAccount(fieldValue);
	}

	@Override
	public String getClassName() {
		return CommerceAccount.class.getName();
	}

	@Override
	public List<String> getFieldNames() {
		return _fieldNames;
	}

	@Override
	public String getFieldValueName(String fieldValue, Locale locale) {
		CommerceAccount commerceAccount = _getCommerceAccount(fieldValue);

		if (commerceAccount == null) {
			return fieldValue;
		}

		return commerceAccount.getName();
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public Field.SelectEntity getSelectEntity(PortletRequest portletRequest) {
		try {
			PortletURL portletURL = _portal.getControlPanelPortletURL(
				portletRequest, SegmentsPortletKeys.SEGMENTS,
				PortletRequest.RENDER_PHASE);

			portletURL.setParameter(
				"mvcRenderCommandName", "/segments/select_commerce_accounts");
			portletURL.setParameter("eventName", "selectEntity");
			portletURL.setWindowState(LiferayWindowState.POP_UP);

			return new Field.SelectEntity(
				"selectEntity",
				getSelectEntityTitle(
					_portal.getLocale(portletRequest),
					CommerceAccount.class.getName()),
				portletURL.toString(), true);
		}
		catch (Exception exception) {
			if (_log.isWarnEnabled()) {
				_log.warn("Unable to get select entity", exception);
			}

			return null;
		}
	}

	protected String getSelectEntityTitle(Locale locale, String className) {
		String title = ResourceActionsUtil.getModelResource(locale, className);

		return LanguageUtil.format(locale, "select-x", title);
	}

	private CommerceAccount _getCommerceAccount(String fieldValue) {
		long commerceAccountId = GetterUtil.getLong(fieldValue);

		if (commerceAccountId == 0) {
			return null;
		}

		return _commerceAccountLocalService.fetchCommerceAccount(
			commerceAccountId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountSegmentsFieldCustomizer.class);

	private static final List<String> _fieldNames = ListUtil.fromArray(
		"commerceAccountId");

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference
	private Portal _portal;

}