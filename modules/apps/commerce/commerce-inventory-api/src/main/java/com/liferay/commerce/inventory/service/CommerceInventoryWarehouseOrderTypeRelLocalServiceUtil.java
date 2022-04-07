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
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for CommerceInventoryWarehouseOrderTypeRel. This utility wraps
 * <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseOrderTypeRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseOrderTypeRelLocalService
 * @generated
 */
public class CommerceInventoryWarehouseOrderTypeRelLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.inventory.service.impl.CommerceInventoryWarehouseOrderTypeRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static CommerceInventoryWarehouseOrderTypeRel
		addCommerceInventoryWarehouseOrderTypeRel(
			CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel) {

		return getService().addCommerceInventoryWarehouseOrderTypeRel(
			commerceInventoryWarehouseOrderTypeRel);
	}

	public static CommerceInventoryWarehouseOrderTypeRel
			addCommerceInventoryWarehouseOrderTypeRel(
				long userId, long commerceInventoryWarehouseId,
				long commerceOrderTypeId, int priority,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addCommerceInventoryWarehouseOrderTypeRel(
			userId, commerceInventoryWarehouseId, commerceOrderTypeId, priority,
			serviceContext);
	}

	/**
	 * Creates a new commerce inventory warehouse order type rel with the primary key. Does not add the commerce inventory warehouse order type rel to the database.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key for the new commerce inventory warehouse order type rel
	 * @return the new commerce inventory warehouse order type rel
	 */
	public static CommerceInventoryWarehouseOrderTypeRel
		createCommerceInventoryWarehouseOrderTypeRel(
			long commerceInventoryWarehouseOrderTypeRelId) {

		return getService().createCommerceInventoryWarehouseOrderTypeRel(
			commerceInventoryWarehouseOrderTypeRelId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
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
	public static CommerceInventoryWarehouseOrderTypeRel
			deleteCommerceInventoryWarehouseOrderTypeRel(
				CommerceInventoryWarehouseOrderTypeRel
					commerceInventoryWarehouseOrderTypeRel)
		throws PortalException {

		return getService().deleteCommerceInventoryWarehouseOrderTypeRel(
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
	public static CommerceInventoryWarehouseOrderTypeRel
			deleteCommerceInventoryWarehouseOrderTypeRel(
				long commerceInventoryWarehouseOrderTypeRelId)
		throws PortalException {

		return getService().deleteCommerceInventoryWarehouseOrderTypeRel(
			commerceInventoryWarehouseOrderTypeRelId);
	}

	public static void deleteCommerceInventoryWarehouseOrderTypeRels(
		long commerceInventoryWarehouseId) {

		getService().deleteCommerceInventoryWarehouseOrderTypeRels(
			commerceInventoryWarehouseId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
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
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static CommerceInventoryWarehouseOrderTypeRel
		fetchCommerceInventoryWarehouseOrderTypeRel(
			long commerceInventoryWarehouseOrderTypeRelId) {

		return getService().fetchCommerceInventoryWarehouseOrderTypeRel(
			commerceInventoryWarehouseOrderTypeRelId);
	}

	public static CommerceInventoryWarehouseOrderTypeRel
		fetchCommerceInventoryWarehouseOrderTypeRel(
			long commerceInventoryWarehouseId, long commerceOrderTypeId) {

		return getService().fetchCommerceInventoryWarehouseOrderTypeRel(
			commerceInventoryWarehouseId, commerceOrderTypeId);
	}

	/**
	 * Returns the commerce inventory warehouse order type rel with the matching UUID and company.
	 *
	 * @param uuid the commerce inventory warehouse order type rel's UUID
	 * @param companyId the primary key of the company
	 * @return the matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	public static CommerceInventoryWarehouseOrderTypeRel
		fetchCommerceInventoryWarehouseOrderTypeRelByUuidAndCompanyId(
			String uuid, long companyId) {

		return getService().
			fetchCommerceInventoryWarehouseOrderTypeRelByUuidAndCompanyId(
				uuid, companyId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce inventory warehouse order type rel with the primary key.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel
	 * @throws PortalException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	public static CommerceInventoryWarehouseOrderTypeRel
			getCommerceInventoryWarehouseOrderTypeRel(
				long commerceInventoryWarehouseOrderTypeRelId)
		throws PortalException {

		return getService().getCommerceInventoryWarehouseOrderTypeRel(
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
	public static CommerceInventoryWarehouseOrderTypeRel
			getCommerceInventoryWarehouseOrderTypeRelByUuidAndCompanyId(
				String uuid, long companyId)
		throws PortalException {

		return getService().
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
	public static List<CommerceInventoryWarehouseOrderTypeRel>
		getCommerceInventoryWarehouseOrderTypeRels(int start, int end) {

		return getService().getCommerceInventoryWarehouseOrderTypeRels(
			start, end);
	}

	public static List<CommerceInventoryWarehouseOrderTypeRel>
		getCommerceInventoryWarehouseOrderTypeRels(
			long commerceInventoryWarehouseId) {

		return getService().getCommerceInventoryWarehouseOrderTypeRels(
			commerceInventoryWarehouseId);
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

	/**
	 * Returns the number of commerce inventory warehouse order type rels.
	 *
	 * @return the number of commerce inventory warehouse order type rels
	 */
	public static int getCommerceInventoryWarehouseOrderTypeRelsCount() {
		return getService().getCommerceInventoryWarehouseOrderTypeRelsCount();
	}

	public static int getCommerceInventoryWarehouseOrderTypeRelsCount(
			long commerceInventoryWarehouseId, String name)
		throws PortalException {

		return getService().getCommerceInventoryWarehouseOrderTypeRelsCount(
			commerceInventoryWarehouseId, name);
	}

	public static com.liferay.portal.kernel.dao.orm.ExportActionableDynamicQuery
		getExportActionableDynamicQuery(
			com.liferay.exportimport.kernel.lar.PortletDataContext
				portletDataContext) {

		return getService().getExportActionableDynamicQuery(portletDataContext);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
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
	public static CommerceInventoryWarehouseOrderTypeRel
		updateCommerceInventoryWarehouseOrderTypeRel(
			CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel) {

		return getService().updateCommerceInventoryWarehouseOrderTypeRel(
			commerceInventoryWarehouseOrderTypeRel);
	}

	public static CommerceInventoryWarehouseOrderTypeRelLocalService
		getService() {

		return _service;
	}

	private static volatile CommerceInventoryWarehouseOrderTypeRelLocalService
		_service;

}