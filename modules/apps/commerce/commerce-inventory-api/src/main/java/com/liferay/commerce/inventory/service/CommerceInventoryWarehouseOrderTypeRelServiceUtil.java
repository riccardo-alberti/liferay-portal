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

package com.liferay.commerce.inventory.service;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseOrderTypeRel;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.List;

/**
 * Provides the remote service utility for CommerceInventoryWarehouseOrderTypeRel. This utility wraps
 * <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseOrderTypeRelServiceImpl</code> and is an
 * access point for service operations in application layer code running on a
 * remote server. Methods of this service are expected to have security checks
 * based on the propagated JAAS credentials because this service can be
 * accessed remotely.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseOrderTypeRelService
 * @generated
 */
public class CommerceInventoryWarehouseOrderTypeRelServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseOrderTypeRelServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static CommerceInventoryWarehouseOrderTypeRel
			addCommerceInventoryWarehouseOrderTypeRel(
				long commerceInventoryWarehouseId, long commerceOrderTypeId,
				int priority,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceInventoryWarehouseOrderTypeRel(
			commerceInventoryWarehouseId, commerceOrderTypeId, priority,
			serviceContext);
	}

	public static void deleteCommerceInventoryWarehouseOrderTypeRel(
			long commerceInventoryWarehouseOrderTypeRelId)
		throws PortalException {

		getService().deleteCommerceInventoryWarehouseOrderTypeRel(
			commerceInventoryWarehouseOrderTypeRelId);
	}

	public static CommerceInventoryWarehouseOrderTypeRel
			fetchCommerceInventoryWarehouseOrderTypeRel(
				long commerceInventoryWarehouseId, long commerceOrderTypeId)
		throws PortalException {

		return getService().fetchCommerceInventoryWarehouseOrderTypeRel(
			commerceInventoryWarehouseId, commerceOrderTypeId);
	}

	public static CommerceInventoryWarehouseOrderTypeRel
			getCommerceInventoryWarehouseOrderTypeRel(
				long commerceInventoryWarehouseOrderTypeRelId)
		throws PortalException {

		return getService().getCommerceInventoryWarehouseOrderTypeRel(
			commerceInventoryWarehouseOrderTypeRelId);
	}

	public static List<CommerceInventoryWarehouseOrderTypeRel>
			getCommerceInventoryWarehouseOrderTypeRels(
				long commerceInventoryWarehouseId, String name, int start,
				int end,
				OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
					orderByComparator)
		throws PortalException {

		return getService().getCommerceInventoryWarehouseOrderTypeRels(
			commerceInventoryWarehouseId, name, start, end, orderByComparator);
	}

	public static int getCommerceInventoryWarehouseOrderTypeRelsCount(
			long commerceInventoryWarehouseId, String name)
		throws PortalException {

		return getService().getCommerceInventoryWarehouseOrderTypeRelsCount(
			commerceInventoryWarehouseId, name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static CommerceInventoryWarehouseOrderTypeRelService getService() {
		return _service;
	}

	private static volatile CommerceInventoryWarehouseOrderTypeRelService
		_service;

}