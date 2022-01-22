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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CommerceQualificationRelLocalService}.
 *
 * @author Riccardo Alberti
 * @see CommerceQualificationRelLocalService
 * @generated
 */
public class CommerceQualificationRelLocalServiceWrapper
	implements CommerceQualificationRelLocalService,
			   ServiceWrapper<CommerceQualificationRelLocalService> {

	public CommerceQualificationRelLocalServiceWrapper() {
		this(null);
	}

	public CommerceQualificationRelLocalServiceWrapper(
		CommerceQualificationRelLocalService
			commerceQualificationRelLocalService) {

		_commerceQualificationRelLocalService =
			commerceQualificationRelLocalService;
	}

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
	@Override
	public com.liferay.commerce.qualification.model.CommerceQualificationRel
		addCommerceQualificationRel(
			com.liferay.commerce.qualification.model.CommerceQualificationRel
				commerceQualificationRel) {

		return _commerceQualificationRelLocalService.
			addCommerceQualificationRel(commerceQualificationRel);
	}

	@Override
	public com.liferay.commerce.qualification.model.CommerceQualificationRel
			addCommerceQualificationRel(
				long userId, String sourceClassName, long sourceClassPK,
				String targetClassName, long targetClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualificationRelLocalService.
			addCommerceQualificationRel(
				userId, sourceClassName, sourceClassPK, targetClassName,
				targetClassPK);
	}

	/**
	 * Creates a new commerce qualification rel with the primary key. Does not add the commerce qualification rel to the database.
	 *
	 * @param commerceQualificationRelId the primary key for the new commerce qualification rel
	 * @return the new commerce qualification rel
	 */
	@Override
	public com.liferay.commerce.qualification.model.CommerceQualificationRel
		createCommerceQualificationRel(long commerceQualificationRelId) {

		return _commerceQualificationRelLocalService.
			createCommerceQualificationRel(commerceQualificationRelId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualificationRelLocalService.createPersistedModel(
			primaryKeyObj);
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
	@Override
	public com.liferay.commerce.qualification.model.CommerceQualificationRel
			deleteCommerceQualificationRel(
				com.liferay.commerce.qualification.model.
					CommerceQualificationRel commerceQualificationRel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualificationRelLocalService.
			deleteCommerceQualificationRel(commerceQualificationRel);
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
	@Override
	public com.liferay.commerce.qualification.model.CommerceQualificationRel
			deleteCommerceQualificationRel(long commerceQualificationRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualificationRelLocalService.
			deleteCommerceQualificationRel(commerceQualificationRelId);
	}

	@Override
	public void deleteCommerceQualificationRels(
			String sourceClassName, long sourceClassPK)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceQualificationRelLocalService.deleteCommerceQualificationRels(
			sourceClassName, sourceClassPK);
	}

	@Override
	public void deleteCommerceQualificationRels(
			String sourceClassName, long sourceClassPK, String targetClassName)
		throws com.liferay.portal.kernel.exception.PortalException {

		_commerceQualificationRelLocalService.deleteCommerceQualificationRels(
			sourceClassName, sourceClassPK, targetClassName);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualificationRelLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _commerceQualificationRelLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _commerceQualificationRelLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _commerceQualificationRelLocalService.dynamicQuery();
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

		return _commerceQualificationRelLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _commerceQualificationRelLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _commerceQualificationRelLocalService.dynamicQuery(
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

		return _commerceQualificationRelLocalService.dynamicQueryCount(
			dynamicQuery);
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

		return _commerceQualificationRelLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.commerce.qualification.model.CommerceQualificationRel
		fetchCommerceQualificationRel(long commerceQualificationRelId) {

		return _commerceQualificationRelLocalService.
			fetchCommerceQualificationRel(commerceQualificationRelId);
	}

	@Override
	public com.liferay.commerce.qualification.model.CommerceQualificationRel
		fetchCommerceQualificationRel(
			String sourceClassName, long sourceClassPK, String targetClassName,
			long targetClassPK) {

		return _commerceQualificationRelLocalService.
			fetchCommerceQualificationRel(
				sourceClassName, sourceClassPK, targetClassName, targetClassPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _commerceQualificationRelLocalService.
			getActionableDynamicQuery();
	}

	/**
	 * Returns the commerce qualification rel with the primary key.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel
	 * @throws PortalException if a commerce qualification rel with the primary key could not be found
	 */
	@Override
	public com.liferay.commerce.qualification.model.CommerceQualificationRel
			getCommerceQualificationRel(long commerceQualificationRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualificationRelLocalService.
			getCommerceQualificationRel(commerceQualificationRelId);
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
	@Override
	public java.util.List
		<com.liferay.commerce.qualification.model.CommerceQualificationRel>
			getCommerceQualificationRels(int start, int end) {

		return _commerceQualificationRelLocalService.
			getCommerceQualificationRels(start, end);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.qualification.model.CommerceQualificationRel>
			getCommerceQualificationRels(
				String sourceClassName, long sourceClassPK) {

		return _commerceQualificationRelLocalService.
			getCommerceQualificationRels(sourceClassName, sourceClassPK);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.qualification.model.CommerceQualificationRel>
			getCommerceQualificationRels(
				String sourceClassName, long sourceClassPK, int start, int end,
				com.liferay.portal.kernel.util.OrderByComparator
					<com.liferay.commerce.qualification.model.
						CommerceQualificationRel> orderByComparator) {

		return _commerceQualificationRelLocalService.
			getCommerceQualificationRels(
				sourceClassName, sourceClassPK, start, end, orderByComparator);
	}

	/**
	 * Returns the number of commerce qualification rels.
	 *
	 * @return the number of commerce qualification rels
	 */
	@Override
	public int getCommerceQualificationRelsCount() {
		return _commerceQualificationRelLocalService.
			getCommerceQualificationRelsCount();
	}

	@Override
	public int getCommerceQualificationRelsCount(
		String sourceClassName, long sourceClassPK) {

		return _commerceQualificationRelLocalService.
			getCommerceQualificationRelsCount(sourceClassName, sourceClassPK);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _commerceQualificationRelLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _commerceQualificationRelLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _commerceQualificationRelLocalService.getPersistedModel(
			primaryKeyObj);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.qualification.model.CommerceQualificationRel>
			getSourceCommerceQualificationRels(
				long companyId, String sourceClassName,
				java.util.Map<String, Long[]> targetFilters) {

		return _commerceQualificationRelLocalService.
			getSourceCommerceQualificationRels(
				companyId, sourceClassName, targetFilters);
	}

	@Override
	public java.util.List
		<com.liferay.commerce.qualification.model.CommerceQualificationRel>
			getTargetCommerceQualificationRels(
				String keywords, String sourceClassName, long sourceClassPK,
				String targetClassName, int start, int end) {

		return _commerceQualificationRelLocalService.
			getTargetCommerceQualificationRels(
				keywords, sourceClassName, sourceClassPK, targetClassName,
				start, end);
	}

	@Override
	public int getTargetCommerceQualificationRelsCount(
		String keywords, String sourceClassName, long sourceClassPK,
		String targetClassName) {

		return _commerceQualificationRelLocalService.
			getTargetCommerceQualificationRelsCount(
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
	@Override
	public com.liferay.commerce.qualification.model.CommerceQualificationRel
		updateCommerceQualificationRel(
			com.liferay.commerce.qualification.model.CommerceQualificationRel
				commerceQualificationRel) {

		return _commerceQualificationRelLocalService.
			updateCommerceQualificationRel(commerceQualificationRel);
	}

	@Override
	public CommerceQualificationRelLocalService getWrappedService() {
		return _commerceQualificationRelLocalService;
	}

	@Override
	public void setWrappedService(
		CommerceQualificationRelLocalService
			commerceQualificationRelLocalService) {

		_commerceQualificationRelLocalService =
			commerceQualificationRelLocalService;
	}

	private CommerceQualificationRelLocalService
		_commerceQualificationRelLocalService;

}