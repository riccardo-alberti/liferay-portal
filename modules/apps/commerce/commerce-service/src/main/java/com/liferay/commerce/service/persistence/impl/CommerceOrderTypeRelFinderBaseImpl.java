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

package com.liferay.commerce.service.persistence.impl;

import com.liferay.commerce.model.CommerceOrderTypeRel;
import com.liferay.commerce.service.persistence.CommerceOrderTypeRelPersistence;
import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

/**
 * @author Alessio Antonio Rendina
 * @generated
 */
public class CommerceOrderTypeRelFinderBaseImpl
	extends BasePersistenceImpl<CommerceOrderTypeRel> {

	public CommerceOrderTypeRelFinderBaseImpl() {
		setModelClass(CommerceOrderTypeRel.class);
	}

	/**
	 * Returns the commerce order type rel persistence.
	 *
	 * @return the commerce order type rel persistence
	 */
	public CommerceOrderTypeRelPersistence
		getCommerceOrderTypeRelPersistence() {

		return commerceOrderTypeRelPersistence;
	}

	/**
	 * Sets the commerce order type rel persistence.
	 *
	 * @param commerceOrderTypeRelPersistence the commerce order type rel persistence
	 */
	public void setCommerceOrderTypeRelPersistence(
		CommerceOrderTypeRelPersistence commerceOrderTypeRelPersistence) {

		this.commerceOrderTypeRelPersistence = commerceOrderTypeRelPersistence;
	}

	@BeanReference(type = CommerceOrderTypeRelPersistence.class)
	protected CommerceOrderTypeRelPersistence commerceOrderTypeRelPersistence;

}