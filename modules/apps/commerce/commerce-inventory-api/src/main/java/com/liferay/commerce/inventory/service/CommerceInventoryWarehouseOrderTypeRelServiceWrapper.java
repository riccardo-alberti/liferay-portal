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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceInventoryWarehouseOrderTypeRelService}.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseOrderTypeRelService
 * @generated
 */
public class CommerceInventoryWarehouseOrderTypeRelServiceWrapper
	implements CommerceInventoryWarehouseOrderTypeRelService,
			   ServiceWrapper<CommerceInventoryWarehouseOrderTypeRelService> {

	public CommerceInventoryWarehouseOrderTypeRelServiceWrapper() {
		this(null);
	}

	public CommerceInventoryWarehouseOrderTypeRelServiceWrapper(
		CommerceInventoryWarehouseOrderTypeRelService
			commerceInventoryWarehouseOrderTypeRelService) {

		_commerceInventoryWarehouseOrderTypeRelService =
			commerceInventoryWarehouseOrderTypeRelService;
	}

	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
					addCommerceInventoryWarehouseOrderTypeRel(
						long commerceInventoryWarehouseId,
						long commerceOrderTypeId, int priority,
						com.liferay.portal.kernel.service.ServiceContext
							serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelService.
			addCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseId, commerceOrderTypeId, priority,
				serviceContext);
	}

	@Override
	public void deleteCommerceInventoryWarehouseOrderTypeRel(
			long commerceInventoryWarehouseOrderTypeRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceInventoryWarehouseOrderTypeRelService.
			deleteCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseOrderTypeRelId);
	}

	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
					fetchCommerceInventoryWarehouseOrderTypeRel(
						long commerceInventoryWarehouseId,
						long commerceOrderTypeId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelService.
			fetchCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseId, commerceOrderTypeId);
	}

	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
					getCommerceInventoryWarehouseOrderTypeRel(
						long commerceInventoryWarehouseOrderTypeRelId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelService.
			getCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseOrderTypeRelId);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel>
					getCommerceInventoryWarehouseOrderTypeRels(
						long commerceInventoryWarehouseId, String name,
						int start, int end,
						com.liferay.portal.kernel.util.OrderByComparator
							<com.liferay.commerce.inventory.model.
								CommerceInventoryWarehouseOrderTypeRel>
									orderByComparator)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelService.
			getCommerceInventoryWarehouseOrderTypeRels(
				commerceInventoryWarehouseId, name, start, end,
				orderByComparator);
	}

	@Override
	public int getCommerceInventoryWarehouseOrderTypeRelsCount(
			long commerceInventoryWarehouseId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelService.
			getCommerceInventoryWarehouseOrderTypeRelsCount(
				commerceInventoryWarehouseId, name);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceInventoryWarehouseOrderTypeRelService.
			getOSGiServiceIdentifier();
	}

	@Override
	public CommerceInventoryWarehouseOrderTypeRelService getWrappedService() {
		return _commerceInventoryWarehouseOrderTypeRelService;
	}

	@Override
	public void setWrappedService(
		CommerceInventoryWarehouseOrderTypeRelService
			commerceInventoryWarehouseOrderTypeRelService) {

		_commerceInventoryWarehouseOrderTypeRelService =
			commerceInventoryWarehouseOrderTypeRelService;
	}

	private CommerceInventoryWarehouseOrderTypeRelService
		_commerceInventoryWarehouseOrderTypeRelService;

}