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

package com.liferay.fulfilment.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FulfilmentTaskLocalService}.
 *
 * @author Riccardo Alberti
 * @see FulfilmentTaskLocalService
 * @generated
 */
public class FulfilmentTaskLocalServiceWrapper
	implements FulfilmentTaskLocalService,
			   ServiceWrapper<FulfilmentTaskLocalService> {

	public FulfilmentTaskLocalServiceWrapper() {
		this(null);
	}

	public FulfilmentTaskLocalServiceWrapper(
		FulfilmentTaskLocalService fulfilmentTaskLocalService) {

		_fulfilmentTaskLocalService = fulfilmentTaskLocalService;
	}

	/**
	 * Adds the fulfilment task to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FulfilmentTaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fulfilmentTask the fulfilment task
	 * @return the fulfilment task that was added
	 */
	@Override
	public com.liferay.fulfilment.model.FulfilmentTask addFulfilmentTask(
		com.liferay.fulfilment.model.FulfilmentTask fulfilmentTask) {

		return _fulfilmentTaskLocalService.addFulfilmentTask(fulfilmentTask);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentTask addFulfilmentTask(
			long userId, long correlationId, long fulfilmentRequestId,
			String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskLocalService.addFulfilmentTask(
			userId, correlationId, fulfilmentRequestId, type, serviceContext);
	}

	/**
	 * Creates a new fulfilment task with the primary key. Does not add the fulfilment task to the database.
	 *
	 * @param fulfilmentTaskId the primary key for the new fulfilment task
	 * @return the new fulfilment task
	 */
	@Override
	public com.liferay.fulfilment.model.FulfilmentTask createFulfilmentTask(
		long fulfilmentTaskId) {

		return _fulfilmentTaskLocalService.createFulfilmentTask(
			fulfilmentTaskId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the fulfilment task from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FulfilmentTaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fulfilmentTask the fulfilment task
	 * @return the fulfilment task that was removed
	 */
	@Override
	public com.liferay.fulfilment.model.FulfilmentTask deleteFulfilmentTask(
		com.liferay.fulfilment.model.FulfilmentTask fulfilmentTask) {

		return _fulfilmentTaskLocalService.deleteFulfilmentTask(fulfilmentTask);
	}

	/**
	 * Deletes the fulfilment task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FulfilmentTaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task that was removed
	 * @throws PortalException if a fulfilment task with the primary key could not be found
	 */
	@Override
	public com.liferay.fulfilment.model.FulfilmentTask deleteFulfilmentTask(
			long fulfilmentTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskLocalService.deleteFulfilmentTask(
			fulfilmentTaskId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskLocalService.deletePersistedModel(persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _fulfilmentTaskLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _fulfilmentTaskLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fulfilmentTaskLocalService.dynamicQuery();
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

		return _fulfilmentTaskLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fulfilment.model.impl.FulfilmentTaskModelImpl</code>.
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

		return _fulfilmentTaskLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fulfilment.model.impl.FulfilmentTaskModelImpl</code>.
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

		return _fulfilmentTaskLocalService.dynamicQuery(
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

		return _fulfilmentTaskLocalService.dynamicQueryCount(dynamicQuery);
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

		return _fulfilmentTaskLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentTask fetchFulfilmentTask(
		long fulfilmentTaskId) {

		return _fulfilmentTaskLocalService.fetchFulfilmentTask(
			fulfilmentTaskId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _fulfilmentTaskLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the fulfilment task with the primary key.
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task
	 * @throws PortalException if a fulfilment task with the primary key could not be found
	 */
	@Override
	public com.liferay.fulfilment.model.FulfilmentTask getFulfilmentTask(
			long fulfilmentTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskLocalService.getFulfilmentTask(fulfilmentTaskId);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentTask
			getFulfilmentTaskByCorrelationId(long correlationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskLocalService.getFulfilmentTaskByCorrelationId(
			correlationId);
	}

	/**
	 * Returns a range of all the fulfilment tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fulfilment.model.impl.FulfilmentTaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fulfilment tasks
	 * @param end the upper bound of the range of fulfilment tasks (not inclusive)
	 * @return the range of fulfilment tasks
	 */
	@Override
	public java.util.List<com.liferay.fulfilment.model.FulfilmentTask>
		getFulfilmentTasks(int start, int end) {

		return _fulfilmentTaskLocalService.getFulfilmentTasks(start, end);
	}

	@Override
	public java.util.List<com.liferay.fulfilment.model.FulfilmentTask>
			getFulfilmentTasks(long fulfilmentRequestId, int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskLocalService.getFulfilmentTasks(
			fulfilmentRequestId, start, end);
	}

	/**
	 * Returns the number of fulfilment tasks.
	 *
	 * @return the number of fulfilment tasks
	 */
	@Override
	public int getFulfilmentTasksCount() {
		return _fulfilmentTaskLocalService.getFulfilmentTasksCount();
	}

	@Override
	public int getFulfilmentTasksCount(long fulfilmentRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskLocalService.getFulfilmentTasksCount(
			fulfilmentRequestId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _fulfilmentTaskLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fulfilmentTaskLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the fulfilment task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FulfilmentTaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fulfilmentTask the fulfilment task
	 * @return the fulfilment task that was updated
	 */
	@Override
	public com.liferay.fulfilment.model.FulfilmentTask updateFulfilmentTask(
		com.liferay.fulfilment.model.FulfilmentTask fulfilmentTask) {

		return _fulfilmentTaskLocalService.updateFulfilmentTask(fulfilmentTask);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentTask updateFulfilmentTask(
			long userId, long fulfilmentTaskId, String inputParameters,
			String outputParameters, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentTaskLocalService.updateFulfilmentTask(
			userId, fulfilmentTaskId, inputParameters, outputParameters, status,
			serviceContext);
	}

	@Override
	public FulfilmentTaskLocalService getWrappedService() {
		return _fulfilmentTaskLocalService;
	}

	@Override
	public void setWrappedService(
		FulfilmentTaskLocalService fulfilmentTaskLocalService) {

		_fulfilmentTaskLocalService = fulfilmentTaskLocalService;
	}

	private FulfilmentTaskLocalService _fulfilmentTaskLocalService;

}