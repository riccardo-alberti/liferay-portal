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

package com.liferay.commerce.payment.internal.qualification;

import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRelTable;
import com.liferay.commerce.qualification.entity.CommerceQualificationRelEntity;
import com.liferay.commerce.qualification.entity.SourceCommerceQualificationRelEntity;
import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.Table;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = "commerce.qualification.model.class.name=com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel",
	service = CommerceQualificationRelEntity.class
)
public class CPMGroupRelSourceCommerceQualificationRelEntity
	extends SourceCommerceQualificationRelEntity {

	@Override
	public String[] getAvailableTargetClassNames() {
		return new String[] {CommerceOrderType.class.getName()};
	}

	@Override
	public Predicate getFilterPredicate(long companyId) {
		return CommercePaymentMethodGroupRelTable.INSTANCE.companyId.eq(
			companyId
		).and(
			CommercePaymentMethodGroupRelTable.INSTANCE.active.eq(true)
		);
	}

	@Override
	public String getModelClassName() {
		return CommercePaymentMethodGroupRel.class.getName();
	}

	@Override
	public ModelResourcePermission<?> getModelResourcePermission() {
		return null;
	}

	@Override
	public String getName() {
		return "payment-method";
	}

	@Override
	public Column<?, Long> getPrimaryKeyColumn() {
		return CommercePaymentMethodGroupRelTable.INSTANCE.
			commercePaymentMethodGroupRelId;
	}

	@Override
	public Table getTable() {
		return CommercePaymentMethodGroupRelTable.INSTANCE;
	}

}