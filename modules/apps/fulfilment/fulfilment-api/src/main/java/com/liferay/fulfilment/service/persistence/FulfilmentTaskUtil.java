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

package com.liferay.fulfilment.service.persistence;

import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the fulfilment task service. This utility wraps <code>com.liferay.fulfilment.service.persistence.impl.FulfilmentTaskPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @see FulfilmentTaskPersistence
 * @generated
 */
public class FulfilmentTaskUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(FulfilmentTask fulfilmentTask) {
		getPersistence().clearCache(fulfilmentTask);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, FulfilmentTask> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FulfilmentTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FulfilmentTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FulfilmentTask> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FulfilmentTask> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FulfilmentTask update(FulfilmentTask fulfilmentTask) {
		return getPersistence().update(fulfilmentTask);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FulfilmentTask update(
		FulfilmentTask fulfilmentTask, ServiceContext serviceContext) {

		return getPersistence().update(fulfilmentTask, serviceContext);
	}

	/**
	 * Returns the fulfilment task where correlationId = &#63; or throws a <code>NoSuchTaskException</code> if it could not be found.
	 *
	 * @param correlationId the correlation ID
	 * @return the matching fulfilment task
	 * @throws NoSuchTaskException if a matching fulfilment task could not be found
	 */
	public static FulfilmentTask findByCorrelationId(long correlationId)
		throws com.liferay.fulfilment.exception.NoSuchTaskException {

		return getPersistence().findByCorrelationId(correlationId);
	}

	/**
	 * Returns the fulfilment task where correlationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param correlationId the correlation ID
	 * @return the matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	public static FulfilmentTask fetchByCorrelationId(long correlationId) {
		return getPersistence().fetchByCorrelationId(correlationId);
	}

	/**
	 * Returns the fulfilment task where correlationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param correlationId the correlation ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	public static FulfilmentTask fetchByCorrelationId(
		long correlationId, boolean useFinderCache) {

		return getPersistence().fetchByCorrelationId(
			correlationId, useFinderCache);
	}

	/**
	 * Removes the fulfilment task where correlationId = &#63; from the database.
	 *
	 * @param correlationId the correlation ID
	 * @return the fulfilment task that was removed
	 */
	public static FulfilmentTask removeByCorrelationId(long correlationId)
		throws com.liferay.fulfilment.exception.NoSuchTaskException {

		return getPersistence().removeByCorrelationId(correlationId);
	}

	/**
	 * Returns the number of fulfilment tasks where correlationId = &#63;.
	 *
	 * @param correlationId the correlation ID
	 * @return the number of matching fulfilment tasks
	 */
	public static int countByCorrelationId(long correlationId) {
		return getPersistence().countByCorrelationId(correlationId);
	}

	/**
	 * Returns all the fulfilment tasks where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @return the matching fulfilment tasks
	 */
	public static List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId) {

		return getPersistence().findByFulfilmentRequestId(fulfilmentRequestId);
	}

	/**
	 * Returns a range of all the fulfilment tasks where fulfilmentRequestId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentTaskModelImpl</code>.
	 * </p>
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param start the lower bound of the range of fulfilment tasks
	 * @param end the upper bound of the range of fulfilment tasks (not inclusive)
	 * @return the range of matching fulfilment tasks
	 */
	public static List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId, int start, int end) {

		return getPersistence().findByFulfilmentRequestId(
			fulfilmentRequestId, start, end);
	}

	/**
	 * Returns an ordered range of all the fulfilment tasks where fulfilmentRequestId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentTaskModelImpl</code>.
	 * </p>
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param start the lower bound of the range of fulfilment tasks
	 * @param end the upper bound of the range of fulfilment tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fulfilment tasks
	 */
	public static List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId, int start, int end,
		OrderByComparator<FulfilmentTask> orderByComparator) {

		return getPersistence().findByFulfilmentRequestId(
			fulfilmentRequestId, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fulfilment tasks where fulfilmentRequestId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentTaskModelImpl</code>.
	 * </p>
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param start the lower bound of the range of fulfilment tasks
	 * @param end the upper bound of the range of fulfilment tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fulfilment tasks
	 */
	public static List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId, int start, int end,
		OrderByComparator<FulfilmentTask> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByFulfilmentRequestId(
			fulfilmentRequestId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment task
	 * @throws NoSuchTaskException if a matching fulfilment task could not be found
	 */
	public static FulfilmentTask findByFulfilmentRequestId_First(
			long fulfilmentRequestId,
			OrderByComparator<FulfilmentTask> orderByComparator)
		throws com.liferay.fulfilment.exception.NoSuchTaskException {

		return getPersistence().findByFulfilmentRequestId_First(
			fulfilmentRequestId, orderByComparator);
	}

	/**
	 * Returns the first fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	public static FulfilmentTask fetchByFulfilmentRequestId_First(
		long fulfilmentRequestId,
		OrderByComparator<FulfilmentTask> orderByComparator) {

		return getPersistence().fetchByFulfilmentRequestId_First(
			fulfilmentRequestId, orderByComparator);
	}

	/**
	 * Returns the last fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment task
	 * @throws NoSuchTaskException if a matching fulfilment task could not be found
	 */
	public static FulfilmentTask findByFulfilmentRequestId_Last(
			long fulfilmentRequestId,
			OrderByComparator<FulfilmentTask> orderByComparator)
		throws com.liferay.fulfilment.exception.NoSuchTaskException {

		return getPersistence().findByFulfilmentRequestId_Last(
			fulfilmentRequestId, orderByComparator);
	}

	/**
	 * Returns the last fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	public static FulfilmentTask fetchByFulfilmentRequestId_Last(
		long fulfilmentRequestId,
		OrderByComparator<FulfilmentTask> orderByComparator) {

		return getPersistence().fetchByFulfilmentRequestId_Last(
			fulfilmentRequestId, orderByComparator);
	}

	/**
	 * Returns the fulfilment tasks before and after the current fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentTaskId the primary key of the current fulfilment task
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fulfilment task
	 * @throws NoSuchTaskException if a fulfilment task with the primary key could not be found
	 */
	public static FulfilmentTask[] findByFulfilmentRequestId_PrevAndNext(
			long fulfilmentTaskId, long fulfilmentRequestId,
			OrderByComparator<FulfilmentTask> orderByComparator)
		throws com.liferay.fulfilment.exception.NoSuchTaskException {

		return getPersistence().findByFulfilmentRequestId_PrevAndNext(
			fulfilmentTaskId, fulfilmentRequestId, orderByComparator);
	}

	/**
	 * Removes all the fulfilment tasks where fulfilmentRequestId = &#63; from the database.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 */
	public static void removeByFulfilmentRequestId(long fulfilmentRequestId) {
		getPersistence().removeByFulfilmentRequestId(fulfilmentRequestId);
	}

	/**
	 * Returns the number of fulfilment tasks where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @return the number of matching fulfilment tasks
	 */
	public static int countByFulfilmentRequestId(long fulfilmentRequestId) {
		return getPersistence().countByFulfilmentRequestId(fulfilmentRequestId);
	}

	/**
	 * Caches the fulfilment task in the entity cache if it is enabled.
	 *
	 * @param fulfilmentTask the fulfilment task
	 */
	public static void cacheResult(FulfilmentTask fulfilmentTask) {
		getPersistence().cacheResult(fulfilmentTask);
	}

	/**
	 * Caches the fulfilment tasks in the entity cache if it is enabled.
	 *
	 * @param fulfilmentTasks the fulfilment tasks
	 */
	public static void cacheResult(List<FulfilmentTask> fulfilmentTasks) {
		getPersistence().cacheResult(fulfilmentTasks);
	}

	/**
	 * Creates a new fulfilment task with the primary key. Does not add the fulfilment task to the database.
	 *
	 * @param fulfilmentTaskId the primary key for the new fulfilment task
	 * @return the new fulfilment task
	 */
	public static FulfilmentTask create(long fulfilmentTaskId) {
		return getPersistence().create(fulfilmentTaskId);
	}

	/**
	 * Removes the fulfilment task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task that was removed
	 * @throws NoSuchTaskException if a fulfilment task with the primary key could not be found
	 */
	public static FulfilmentTask remove(long fulfilmentTaskId)
		throws com.liferay.fulfilment.exception.NoSuchTaskException {

		return getPersistence().remove(fulfilmentTaskId);
	}

	public static FulfilmentTask updateImpl(FulfilmentTask fulfilmentTask) {
		return getPersistence().updateImpl(fulfilmentTask);
	}

	/**
	 * Returns the fulfilment task with the primary key or throws a <code>NoSuchTaskException</code> if it could not be found.
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task
	 * @throws NoSuchTaskException if a fulfilment task with the primary key could not be found
	 */
	public static FulfilmentTask findByPrimaryKey(long fulfilmentTaskId)
		throws com.liferay.fulfilment.exception.NoSuchTaskException {

		return getPersistence().findByPrimaryKey(fulfilmentTaskId);
	}

	/**
	 * Returns the fulfilment task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task, or <code>null</code> if a fulfilment task with the primary key could not be found
	 */
	public static FulfilmentTask fetchByPrimaryKey(long fulfilmentTaskId) {
		return getPersistence().fetchByPrimaryKey(fulfilmentTaskId);
	}

	/**
	 * Returns all the fulfilment tasks.
	 *
	 * @return the fulfilment tasks
	 */
	public static List<FulfilmentTask> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the fulfilment tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentTaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fulfilment tasks
	 * @param end the upper bound of the range of fulfilment tasks (not inclusive)
	 * @return the range of fulfilment tasks
	 */
	public static List<FulfilmentTask> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the fulfilment tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentTaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fulfilment tasks
	 * @param end the upper bound of the range of fulfilment tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fulfilment tasks
	 */
	public static List<FulfilmentTask> findAll(
		int start, int end,
		OrderByComparator<FulfilmentTask> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the fulfilment tasks.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentTaskModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fulfilment tasks
	 * @param end the upper bound of the range of fulfilment tasks (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of fulfilment tasks
	 */
	public static List<FulfilmentTask> findAll(
		int start, int end, OrderByComparator<FulfilmentTask> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the fulfilment tasks from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of fulfilment tasks.
	 *
	 * @return the number of fulfilment tasks
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FulfilmentTaskPersistence getPersistence() {
		return _persistence;
	}

	private static volatile FulfilmentTaskPersistence _persistence;

}