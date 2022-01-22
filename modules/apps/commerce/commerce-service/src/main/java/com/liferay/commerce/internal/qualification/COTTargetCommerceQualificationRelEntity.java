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

package com.liferay.commerce.internal.qualification;

import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.model.CommerceOrderTypeTable;
import com.liferay.commerce.qualification.entity.CommerceQualificationRelEntity;
import com.liferay.commerce.qualification.entity.TargetCommerceQualificationRelEntity;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.sql.dsl.expression.Expression;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = "commerce.qualification.model.class.name=com.liferay.commerce.model.CommerceOrderType",
	service = CommerceQualificationRelEntity.class
)
public class COTTargetCommerceQualificationRelEntity
	extends TargetCommerceQualificationRelEntity {

	@Override
	public Expression<String> getKeywordsExpression() {
		return CommerceOrderTypeTable.INSTANCE.name;
	}

	@Override
	public String getModelClassName() {
		return CommerceOrderType.class.getName();
	}

	@Override
	public ModelResourcePermission<?> getModelResourcePermission() {
		return _commerceOrderTypeModelResourcePermission;
	}

	@Override
	public String getName() {
		return "order-type";
	}

	@Override
	public Column<?, Long> getPrimaryKeyColumn() {
		return CommerceOrderTypeTable.INSTANCE.commerceOrderTypeId;
	}

	@Override
	public Table getTable() {
		return CommerceOrderTypeTable.INSTANCE;
	}

	@Reference(
		target = "(model.class.name=com.liferay.commerce.model.CommerceOrderType)"
	)
	private ModelResourcePermission<CommerceOrderType>
		_commerceOrderTypeModelResourcePermission;

}