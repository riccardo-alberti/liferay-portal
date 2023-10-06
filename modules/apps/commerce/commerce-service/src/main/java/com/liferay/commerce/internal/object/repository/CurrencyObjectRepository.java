/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.internal.object.repository;

import com.liferay.commerce.object.repository.entity.CurrencyObjectEntity;
import com.liferay.object.petra.sql.dsl.DynamicObjectDefinitionTable;
import com.liferay.object.repository.BaseObjectRepository;
import com.liferay.object.repository.ObjectRepository;
import com.liferay.petra.sql.dsl.expression.Predicate;

import java.util.Objects;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(service = ObjectRepository.class)
public class CurrencyObjectRepository
	extends BaseObjectRepository<CurrencyObjectEntity> {

	@Override
	protected Predicate getPredicate(
		String queryName,
		DynamicObjectDefinitionTable dynamicObjectDefinitionTable,
		Object... parameters) {

		if (Objects.equals(queryName, "ONLY_ACTIVE")) {
			return dynamicObjectDefinitionTable.getColumn(
				"active_", Boolean.class
			).eq(
				true
			);
		}

		return null;
	}

}