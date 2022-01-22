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

package com.liferay.commerce.qualification.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceQualificationRelService}.
 *
 * @author Riccardo Alberti
 * @see CommerceQualificationRelService
 * @generated
 */
public class CommerceQualificationRelServiceWrapper
	implements CommerceQualificationRelService,
			   ServiceWrapper<CommerceQualificationRelService> {

	public CommerceQualificationRelServiceWrapper() {
		this(null);
	}

	public CommerceQualificationRelServiceWrapper(
		CommerceQualificationRelService commerceQualificationRelService) {

		_commerceQualificationRelService = commerceQualificationRelService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceQualificationRelService.getOSGiServiceIdentifier();
	}

	@Override
	public CommerceQualificationRelService getWrappedService() {
		return _commerceQualificationRelService;
	}

	@Override
	public void setWrappedService(
		CommerceQualificationRelService commerceQualificationRelService) {

		_commerceQualificationRelService = commerceQualificationRelService;
	}

	private CommerceQualificationRelService _commerceQualificationRelService;

}