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

package com.liferay.commerce.inventory.service.persistence.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseOrderTypeRelTable;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseOrderTypeRelImpl;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseOrderTypeRelModelImpl;
import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The arguments resolver class for retrieving value from CommerceInventoryWarehouseOrderTypeRel.
 *
 * @author Luca Pellizzon
 * @generated
 */
public class CommerceInventoryWarehouseOrderTypeRelModelArgumentsResolver
	implements ArgumentsResolver {

	@Override
	public Object[] getArguments(
		FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
		boolean original) {

		String[] columnNames = finderPath.getColumnNames();

		if ((columnNames == null) || (columnNames.length == 0)) {
			if (baseModel.isNew()) {
				return new Object[0];
			}

			return null;
		}

		CommerceInventoryWarehouseOrderTypeRelModelImpl
			commerceInventoryWarehouseOrderTypeRelModelImpl =
				(CommerceInventoryWarehouseOrderTypeRelModelImpl)baseModel;

		long columnBitmask =
			commerceInventoryWarehouseOrderTypeRelModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(
				commerceInventoryWarehouseOrderTypeRelModelImpl, columnNames,
				original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					commerceInventoryWarehouseOrderTypeRelModelImpl.
						getColumnBitmask(columnName);
			}

			if (finderPath.isBaseModelResult() &&
				(CommerceInventoryWarehouseOrderTypeRelPersistenceImpl.
					FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION ==
						finderPath.getCacheName())) {

				finderPathColumnBitmask |= _ORDER_BY_COLUMNS_BITMASK;
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(
				commerceInventoryWarehouseOrderTypeRelModelImpl, columnNames,
				original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return CommerceInventoryWarehouseOrderTypeRelImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return CommerceInventoryWarehouseOrderTypeRelTable.INSTANCE.
			getTableName();
	}

	private static Object[] _getValue(
		CommerceInventoryWarehouseOrderTypeRelModelImpl
			commerceInventoryWarehouseOrderTypeRelModelImpl,
		String[] columnNames, boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] =
					commerceInventoryWarehouseOrderTypeRelModelImpl.
						getColumnOriginalValue(columnName);
			}
			else {
				arguments[i] =
					commerceInventoryWarehouseOrderTypeRelModelImpl.
						getColumnValue(columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

	private static final long _ORDER_BY_COLUMNS_BITMASK;

	static {
		long orderByColumnsBitmask = 0;

		orderByColumnsBitmask |=
			CommerceInventoryWarehouseOrderTypeRelModelImpl.getColumnBitmask(
				"priority");

		_ORDER_BY_COLUMNS_BITMASK = orderByColumnsBitmask;
	}

}