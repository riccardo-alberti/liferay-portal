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

package com.liferay.commerce.qualification.display.context;

import com.liferay.commerce.qualification.display.context.helper.CommerceQualificationRelRequestHelper;
import com.liferay.commerce.qualification.entity.CommerceQualificationRelEntityRegistry;
import com.liferay.commerce.qualification.entity.SourceCommerceQualificationRelEntity;
import com.liferay.commerce.qualification.entity.TargetCommerceQualificationRelEntity;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Alberti
 */
public class CommerceQualificationRelDisplayContext {

	public CommerceQualificationRelDisplayContext(
		CommerceQualificationRelEntityRegistry
			commerceQualificationRelEntityRegistry,
		HttpServletRequest httpServletRequest) {

		_commerceQualificationRelEntityRegistry =
			commerceQualificationRelEntityRegistry;
		this.httpServletRequest = httpServletRequest;

		commerceQualificationRelRequestHelper =
			new CommerceQualificationRelRequestHelper(httpServletRequest);
	}

	public SourceCommerceQualificationRelEntity
		getSourceCommerceQualificationRelEntity() {

		String sourceClassName = ParamUtil.getString(
			commerceQualificationRelRequestHelper.getRequest(),
			"sourceClassName");

		return (SourceCommerceQualificationRelEntity)
			_commerceQualificationRelEntityRegistry.
				getCommerceQualificationRelEntity(sourceClassName);
	}

	public TargetCommerceQualificationRelEntity
		getTargetCommerceQualificationRelEntity(String targetClassName) {

		return (TargetCommerceQualificationRelEntity)
			_commerceQualificationRelEntityRegistry.
				getCommerceQualificationRelEntity(targetClassName);
	}

	public boolean hasUpdatePermission() throws PortalException {
		SourceCommerceQualificationRelEntity
			sourceCommerceQualificationRelEntity =
				getSourceCommerceQualificationRelEntity();

		ModelResourcePermission<?> modelResourcePermission =
			sourceCommerceQualificationRelEntity.getModelResourcePermission();

		if (modelResourcePermission == null) {
			return true;
		}

		long sourceClassPK = ParamUtil.getLong(
			commerceQualificationRelRequestHelper.getRequest(),
			"sourceClassPK");

		return modelResourcePermission.contains(
			commerceQualificationRelRequestHelper.getPermissionChecker(),
			sourceClassPK, ActionKeys.UPDATE);
	}

	protected final CommerceQualificationRelRequestHelper
		commerceQualificationRelRequestHelper;
	protected final HttpServletRequest httpServletRequest;

	private final CommerceQualificationRelEntityRegistry
		_commerceQualificationRelEntityRegistry;

}