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

import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for FulfilmentTask. This utility wraps
 * <code>com.liferay.fulfilment.service.impl.FulfilmentTaskLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Riccardo Alberti
 * @see FulfilmentTaskLocalService
 * @generated
 */
public class FulfilmentTaskLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.fulfilment.service.impl.FulfilmentTaskLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static FulfilmentTask addFulfilmentTask(
		FulfilmentTask fulfilmentTask) {

		return getService().addFulfilmentTask(fulfilmentTask);
	}

	public static FulfilmentTask addFulfilmentTask(
			long userId, long correlationId, long fulfilmentRequestId,
			String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addFulfilmentTask(
			userId, correlationId, fulfilmentRequestId, type, serviceContext);
	}

	/**
	 * Creates a new fulfilment task with the primary key. Does not add the fulfilment task to the database.
	 *
	 * @param fulfilmentTaskId the primary key for the new fulfilment task
	 * @return the new fulfilment task
	 */
	public static FulfilmentTask createFulfilmentTask(long fulfilmentTaskId) {
		return getService().createFulfilmentTask(fulfilmentTaskId);
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
	 * Deletes the fulfilment task from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FulfilmentTaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fulfilmentTask the fulfilment task
	 * @return the fulfilment task that was removed
	 */
	public static FulfilmentTask deleteFulfilmentTask(
		FulfilmentTask fulfilmentTask) {

		return getService().deleteFulfilmentTask(fulfilmentTask);
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
	public static FulfilmentTask deleteFulfilmentTask(long fulfilmentTaskId)
		throws PortalException {

		return getService().deleteFulfilmentTask(fulfilmentTaskId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fulfilment.model.impl.FulfilmentTaskModelImpl</code>.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fulfilment.model.impl.FulfilmentTaskModelImpl</code>.
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

	public static FulfilmentTask fetchFulfilmentTask(long fulfilmentTaskId) {
		return getService().fetchFulfilmentTask(fulfilmentTaskId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the fulfilment task with the primary key.
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task
	 * @throws PortalException if a fulfilment task with the primary key could not be found
	 */
	public static FulfilmentTask getFulfilmentTask(long fulfilmentTaskId)
		throws PortalException {

		return getService().getFulfilmentTask(fulfilmentTaskId);
	}

	public static FulfilmentTask getFulfilmentTaskByCorrelationId(
			long correlationId)
		throws PortalException {

		return getService().getFulfilmentTaskByCorrelationId(correlationId);
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
	public static List<FulfilmentTask> getFulfilmentTasks(int start, int end) {
		return getService().getFulfilmentTasks(start, end);
	}

	public static List<FulfilmentTask> getFulfilmentTasks(
			long fulfilmentRequestId, int start, int end)
		throws PortalException {

		return getService().getFulfilmentTasks(fulfilmentRequestId, start, end);
	}

	/**
	 * Returns the number of fulfilment tasks.
	 *
	 * @return the number of fulfilment tasks
	 */
	public static int getFulfilmentTasksCount() {
		return getService().getFulfilmentTasksCount();
	}

	public static int getFulfilmentTasksCount(long fulfilmentRequestId)
		throws PortalException {

		return getService().getFulfilmentTasksCount(fulfilmentRequestId);
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
	 * Updates the fulfilment task in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FulfilmentTaskLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fulfilmentTask the fulfilment task
	 * @return the fulfilment task that was updated
	 */
	public static FulfilmentTask updateFulfilmentTask(
		FulfilmentTask fulfilmentTask) {

		return getService().updateFulfilmentTask(fulfilmentTask);
	}

	public static FulfilmentTask updateFulfilmentTask(
			long userId, long fulfilmentTaskId, String inputParameters,
			String outputParameters, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateFulfilmentTask(
			userId, fulfilmentTaskId, inputParameters, outputParameters, status,
			serviceContext);
	}

	public static FulfilmentTaskLocalService getService() {
		return _service;
	}

	private static volatile FulfilmentTaskLocalService _service;

}