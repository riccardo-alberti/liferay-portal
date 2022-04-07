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

package com.liferay.commerce.inventory.service.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseOrderTypeRel;
import com.liferay.commerce.inventory.service.base.CommerceInventoryWarehouseOrderTypeRelServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermissionFactory;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryWarehouseOrderTypeRelServiceImpl
	extends CommerceInventoryWarehouseOrderTypeRelServiceBaseImpl {

	@Override
	public CommerceInventoryWarehouseOrderTypeRel
			addCommerceInventoryWarehouseOrderTypeRel(
				long commerceInventoryWarehouseId, long commerceOrderTypeId,
				int priority, ServiceContext serviceContext)
		throws PortalException {

		_commerceInventoryWarehouseModelResourcePermission.check(
			getPermissionChecker(), commerceInventoryWarehouseId,
			ActionKeys.UPDATE);

		return commerceInventoryWarehouseOrderTypeRelLocalService.
			addCommerceInventoryWarehouseOrderTypeRel(
				getUserId(), commerceInventoryWarehouseId, commerceOrderTypeId,
				priority, serviceContext);
	}

	@Override
	public void deleteCommerceInventoryWarehouseOrderTypeRel(
			long commerceInventoryWarehouseOrderTypeRelId)
		throws PortalException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel =
				commerceInventoryWarehouseOrderTypeRelLocalService.
					getCommerceInventoryWarehouseOrderTypeRel(
						commerceInventoryWarehouseOrderTypeRelId);

		_commerceInventoryWarehouseModelResourcePermission.check(
			getPermissionChecker(),
			commerceInventoryWarehouseOrderTypeRel.
				getCommerceInventoryWarehouseId(),
			ActionKeys.UPDATE);

		commerceInventoryWarehouseOrderTypeRelLocalService.
			deleteCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseOrderTypeRel);
	}

	@Override
	public CommerceInventoryWarehouseOrderTypeRel
			fetchCommerceInventoryWarehouseOrderTypeRel(
				long commerceInventoryWarehouseId, long commerceOrderTypeId)
		throws PortalException {

		_commerceInventoryWarehouseModelResourcePermission.check(
			getPermissionChecker(), commerceInventoryWarehouseId,
			ActionKeys.VIEW);

		return commerceInventoryWarehouseOrderTypeRelLocalService.
			fetchCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseId, commerceOrderTypeId);
	}

	@Override
	public CommerceInventoryWarehouseOrderTypeRel
			getCommerceInventoryWarehouseOrderTypeRel(
				long commerceInventoryWarehouseOrderTypeRelId)
		throws PortalException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel =
				commerceInventoryWarehouseOrderTypeRelLocalService.
					getCommerceInventoryWarehouseOrderTypeRel(
						commerceInventoryWarehouseOrderTypeRelId);

		_commerceInventoryWarehouseModelResourcePermission.check(
			getPermissionChecker(),
			commerceInventoryWarehouseOrderTypeRel.
				getCommerceInventoryWarehouseId(),
			ActionKeys.VIEW);

		return commerceInventoryWarehouseOrderTypeRel;
	}

	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel>
			getCommerceInventoryWarehouseOrderTypeRels(
				long commerceInventoryWarehouseId, String name, int start,
				int end,
				OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
					orderByComparator)
		throws PortalException {

		_commerceInventoryWarehouseModelResourcePermission.check(
			getPermissionChecker(), commerceInventoryWarehouseId,
			ActionKeys.VIEW);

		return commerceInventoryWarehouseOrderTypeRelLocalService.
			getCommerceInventoryWarehouseOrderTypeRels(
				commerceInventoryWarehouseId, name, start, end,
				orderByComparator);
	}

	@Override
	public int getCommerceInventoryWarehouseOrderTypeRelsCount(
			long commerceInventoryWarehouseId, String name)
		throws PortalException {

		_commerceInventoryWarehouseModelResourcePermission.check(
			getPermissionChecker(), commerceInventoryWarehouseId,
			ActionKeys.VIEW);

		return commerceInventoryWarehouseOrderTypeRelLocalService.
			getCommerceInventoryWarehouseOrderTypeRelsCount(
				commerceInventoryWarehouseId, name);
	}

	private static volatile ModelResourcePermission<CommerceInventoryWarehouse>
		_commerceInventoryWarehouseModelResourcePermission =
			ModelResourcePermissionFactory.getInstance(
				CommerceInventoryWarehouseOrderTypeRelServiceImpl.class,
				"_commerceInventoryWarehouseModelResourcePermission",
				CommerceInventoryWarehouse.class);

}