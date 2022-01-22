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

package com.liferay.commerce.qualification.service;

import com.liferay.commerce.qualification.model.CommerceQualificationRel;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for CommerceQualificationRel. This utility wraps
 * <code>com.liferay.commerce.qualification.service.impl.CommerceQualificationRelLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Riccardo Alberti
 * @see CommerceQualificationRelLocalService
 * @generated
 */
public class CommerceQualificationRelLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.commerce.qualification.service.impl.CommerceQualificationRelLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the commerce qualification rel to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceQualificationRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceQualificationRel the commerce qualification rel
	 * @return the commerce qualification rel that was added
	 */
	public static CommerceQualificationRel addCommerceQualificationRel(
		CommerceQualificationRel commerceQualificationRel) {

		return getService().addCommerceQualificationRel(
			commerceQualificationRel);
	}

	public static CommerceQualificationRel addCommerceQualificationRel(
			long userId, String sourceClassName, long sourceClassPK,
			String targetClassName, long targetClassPK)
		throws PortalException {

		return getService().addCommerceQualificationRel(
			userId, sourceClassName, sourceClassPK, targetClassName,
			targetClassPK);
	}

	/**
	 * Creates a new commerce qualification rel with the primary key. Does not add the commerce qualification rel to the database.
	 *
	 * @param commerceQualificationRelId the primary key for the new commerce qualification rel
	 * @return the new commerce qualification rel
	 */
	public static CommerceQualificationRel createCommerceQualificationRel(
		long commerceQualificationRelId) {

		return getService().createCommerceQualificationRel(
			commerceQualificationRelId);
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
	 * Deletes the commerce qualification rel from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceQualificationRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceQualificationRel the commerce qualification rel
	 * @return the commerce qualification rel that was removed
	 * @throws PortalException
	 */
	public static CommerceQualificationRel deleteCommerceQualificationRel(
			CommerceQualificationRel commerceQualificationRel)
		throws PortalException {

		return getService().deleteCommerceQualificationRel(
			commerceQualificationRel);
	}

	/**
	 * Deletes the commerce qualification rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceQualificationRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel that was removed
	 * @throws PortalException if a commerce qualification rel with the primary key could not be found
	 */
	public static CommerceQualificationRel deleteCommerceQualificationRel(
			long commerceQualificationRelId)
		throws PortalException {

		return getService().deleteCommerceQualificationRel(
			commerceQualificationRelId);
	}

	public static void deleteCommerceQualificationRels(
			String sourceClassName, long sourceClassPK)
		throws PortalException {

		getService().deleteCommerceQualificationRels(
			sourceClassName, sourceClassPK);
	}

	public static void deleteCommerceQualificationRels(
			String sourceClassName, long sourceClassPK, String targetClassName)
		throws PortalException {

		getService().deleteCommerceQualificationRels(
			sourceClassName, sourceClassPK, targetClassName);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualification.model.impl.CommerceQualificationRelModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualification.model.impl.CommerceQualificationRelModelImpl</code>.
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

	public static CommerceQualificationRel fetchCommerceQualificationRel(
		long commerceQualificationRelId) {

		return getService().fetchCommerceQualificationRel(
			commerceQualificationRelId);
	}

	public static CommerceQualificationRel fetchCommerceQualificationRel(
		String sourceClassName, long sourceClassPK, String targetClassName,
		long targetClassPK) {

		return getService().fetchCommerceQualificationRel(
			sourceClassName, sourceClassPK, targetClassName, targetClassPK);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce qualification rel with the primary key.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel
	 * @throws PortalException if a commerce qualification rel with the primary key could not be found
	 */
	public static CommerceQualificationRel getCommerceQualificationRel(
			long commerceQualificationRelId)
		throws PortalException {

		return getService().getCommerceQualificationRel(
			commerceQualificationRelId);
	}

	/**
	 * Returns a range of all the commerce qualification rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualification.model.impl.CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce qualification rels
	 * @param end the upper bound of the range of commerce qualification rels (not inclusive)
	 * @return the range of commerce qualification rels
	 */
	public static List<CommerceQualificationRel> getCommerceQualificationRels(
		int start, int end) {

		return getService().getCommerceQualificationRels(start, end);
	}

	public static List<CommerceQualificationRel> getCommerceQualificationRels(
		String sourceClassName, long sourceClassPK) {

		return getService().getCommerceQualificationRels(
			sourceClassName, sourceClassPK);
	}

	public static List<CommerceQualificationRel> getCommerceQualificationRels(
		String sourceClassName, long sourceClassPK, int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return getService().getCommerceQualificationRels(
			sourceClassName, sourceClassPK, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce qualification rels.
	 *
	 * @return the number of commerce qualification rels
	 */
	public static int getCommerceQualificationRelsCount() {
		return getService().getCommerceQualificationRelsCount();
	}

	public static int getCommerceQualificationRelsCount(
		String sourceClassName, long sourceClassPK) {

		return getService().getCommerceQualificationRelsCount(
			sourceClassName, sourceClassPK);
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

	public static List<CommerceQualificationRel>
		getSourceCommerceQualificationRels(
			long companyId, String sourceClassName,
			Map<String, Long[]> targetFilters) {

		return getService().getSourceCommerceQualificationRels(
			companyId, sourceClassName, targetFilters);
	}

	public static List<CommerceQualificationRel>
		getTargetCommerceQualificationRels(
			String keywords, String sourceClassName, long sourceClassPK,
			String targetClassName, int start, int end) {

		return getService().getTargetCommerceQualificationRels(
			keywords, sourceClassName, sourceClassPK, targetClassName, start,
			end);
	}

	public static int getTargetCommerceQualificationRelsCount(
		String keywords, String sourceClassName, long sourceClassPK,
		String targetClassName) {

		return getService().getTargetCommerceQualificationRelsCount(
			keywords, sourceClassName, sourceClassPK, targetClassName);
	}

	/**
	 * Updates the commerce qualification rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceQualificationRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceQualificationRel the commerce qualification rel
	 * @return the commerce qualification rel that was updated
	 */
	public static CommerceQualificationRel updateCommerceQualificationRel(
		CommerceQualificationRel commerceQualificationRel) {

		return getService().updateCommerceQualificationRel(
			commerceQualificationRel);
	}

	public static CommerceQualificationRelLocalService getService() {
		return _service;
	}

	private static volatile CommerceQualificationRelLocalService _service;

}