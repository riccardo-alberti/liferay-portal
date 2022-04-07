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
 * Provides a wrapper for {@link CommerceInventoryWarehouseOrderTypeRelLocalService}.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseOrderTypeRelLocalService
 * @generated
 */
public class CommerceInventoryWarehouseOrderTypeRelLocalServiceWrapper
	implements CommerceInventoryWarehouseOrderTypeRelLocalService,
			   ServiceWrapper
				   <CommerceInventoryWarehouseOrderTypeRelLocalService> {

	public CommerceInventoryWarehouseOrderTypeRelLocalServiceWrapper() {
		this(null);
	}

	public CommerceInventoryWarehouseOrderTypeRelLocalServiceWrapper(
		CommerceInventoryWarehouseOrderTypeRelLocalService
			commerceInventoryWarehouseOrderTypeRelLocalService) {

		_commerceInventoryWarehouseOrderTypeRelLocalService =
			commerceInventoryWarehouseOrderTypeRelLocalService;
	}

	/**
	 * Adds the commerce inventory warehouse order type rel to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceInventoryWarehouseOrderTypeRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseOrderTypeRel the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel that was added
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
				addCommerceInventoryWarehouseOrderTypeRel(
					com.liferay.commerce.inventory.model.
						CommerceInventoryWarehouseOrderTypeRel
							commerceInventoryWarehouseOrderTypeRel) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			addCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseOrderTypeRel);
	}

	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
					addCommerceInventoryWarehouseOrderTypeRel(
						long userId, long commerceInventoryWarehouseId,
						long commerceOrderTypeId, int priority,
						com.liferay.portal.kernel.service.ServiceContext
							serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			addCommerceInventoryWarehouseOrderTypeRel(
				userId, commerceInventoryWarehouseId, commerceOrderTypeId,
				priority, serviceContext);
	}

	/**
	 * Creates a new commerce inventory warehouse order type rel with the primary key. Does not add the commerce inventory warehouse order type rel to the database.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key for the new commerce inventory warehouse order type rel
	 * @return the new commerce inventory warehouse order type rel
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
				createCommerceInventoryWarehouseOrderTypeRel(
					long commerceInventoryWarehouseOrderTypeRelId) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			createCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseOrderTypeRelId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the commerce inventory warehouse order type rel from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceInventoryWarehouseOrderTypeRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseOrderTypeRel the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel that was removed
	 * @throws PortalException
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
					deleteCommerceInventoryWarehouseOrderTypeRel(
						com.liferay.commerce.inventory.model.
							CommerceInventoryWarehouseOrderTypeRel
								commerceInventoryWarehouseOrderTypeRel)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			deleteCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseOrderTypeRel);
	}

	/**
	 * Deletes the commerce inventory warehouse order type rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceInventoryWarehouseOrderTypeRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel that was removed
	 * @throws PortalException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
					deleteCommerceInventoryWarehouseOrderTypeRel(
						long commerceInventoryWarehouseOrderTypeRelId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			deleteCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseOrderTypeRelId);
	}

	@Override
	public void deleteCommerceInventoryWarehouseOrderTypeRels(
		long commerceInventoryWarehouseId) {

		_commerceInventoryWarehouseOrderTypeRelLocalService.
			deleteCommerceInventoryWarehouseOrderTypeRels(
				commerceInventoryWarehouseId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _commerceInventoryWarehouseOrderTypeRelLocalService.dslQuery(
			dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.dynamicQuery(
			dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			dynamicQueryCount(dynamicQuery, projection);
	}

	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
				fetchCommerceInventoryWarehouseOrderTypeRel(
					long commerceInventoryWarehouseOrderTypeRelId) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			fetchCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseOrderTypeRelId);
	}

	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
				fetchCommerceInventoryWarehouseOrderTypeRel(
					long commerceInventoryWarehouseId,
					long commerceOrderTypeId) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			fetchCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseId, commerceOrderTypeId);
	}

	/**
	 * Returns the commerce inventory warehouse order type rel with the matching UUID and company.
	 *
	 * @param uuid the commerce inventory warehouse order type rel's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
				fetchCommerceInventoryWarehouseOrderTypeRelByUuidAndCompanyId(
					String uuid, long companyId) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			fetchCommerceInventoryWarehouseOrderTypeRelByUuidAndCompanyId(
				uuid, companyId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce inventory warehouse order type rel with the primary key.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel
	 * @throws PortalException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
					getCommerceInventoryWarehouseOrderTypeRel(
						long commerceInventoryWarehouseOrderTypeRelId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseOrderTypeRelId);
	}

	/**
	 * Returns the commerce inventory warehouse order type rel with the matching UUID and company.
	 *
	 * @param uuid the commerce inventory warehouse order type rel's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce inventory warehouse order type rel
	 * @throws PortalException if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
					getCommerceInventoryWarehouseOrderTypeRelByUuidAndCompanyId(
						String uuid, long companyId)
				throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getCommerceInventoryWarehouseOrderTypeRelByUuidAndCompanyId(
				uuid, companyId);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse order type rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @return the range of commerce inventory warehouse order type rels
	 */
	@Override
	public java.util.List
		<com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel>
				getCommerceInventoryWarehouseOrderTypeRels(int start, int end) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getCommerceInventoryWarehouseOrderTypeRels(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel>
				getCommerceInventoryWarehouseOrderTypeRels(
					long commerceInventoryWarehouseId) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getCommerceInventoryWarehouseOrderTypeRels(
				commerceInventoryWarehouseId);
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

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getCommerceInventoryWarehouseOrderTypeRels(
				commerceInventoryWarehouseId, name, start, end,
				orderByComparator);
	}

	/**
	 * Returns the number of commerce inventory warehouse order type rels.
	 *
	 * @return the number of commerce inventory warehouse order type rels
	 */
	@Override
	public int getCommerceInventoryWarehouseOrderTypeRelsCount() {
		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getCommerceInventoryWarehouseOrderTypeRelsCount();
	}

	@Override
	public int getCommerceInventoryWarehouseOrderTypeRelsCount(
			long commerceInventoryWarehouseId, String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getCommerceInventoryWarehouseOrderTypeRelsCount(
				commerceInventoryWarehouseId, name);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getExportActionableDynamicQuery(portletDataContext);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the commerce inventory warehouse order type rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceInventoryWarehouseOrderTypeRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseOrderTypeRel the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel that was updated
	 */
	@Override
	public
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
				updateCommerceInventoryWarehouseOrderTypeRel(
					com.liferay.commerce.inventory.model.
						CommerceInventoryWarehouseOrderTypeRel
							commerceInventoryWarehouseOrderTypeRel) {

		return _commerceInventoryWarehouseOrderTypeRelLocalService.
			updateCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseOrderTypeRel);
	}

	@Override
	public CommerceInventoryWarehouseOrderTypeRelLocalService
		getWrappedService() {

		return _commerceInventoryWarehouseOrderTypeRelLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceInventoryWarehouseOrderTypeRelLocalService
			commerceInventoryWarehouseOrderTypeRelLocalService) {

		_commerceInventoryWarehouseOrderTypeRelLocalService =
			commerceInventoryWarehouseOrderTypeRelLocalService;
	}

	private CommerceInventoryWarehouseOrderTypeRelLocalService
		_commerceInventoryWarehouseOrderTypeRelLocalService;

}