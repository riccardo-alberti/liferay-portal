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

import com.liferay.fulfilment.exception.NoSuchTaskException;
import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the fulfilment task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @see FulfilmentTaskUtil
 * @generated
 */
@ProviderType
public interface FulfilmentTaskPersistence
	extends BasePersistence<FulfilmentTask> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FulfilmentTaskUtil} to access the fulfilment task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the fulfilment task where correlationId = &#63; or throws a <code>NoSuchTaskException</code> if it could not be found.
	 *
	 * @param correlationId the correlation ID
	 * @return the matching fulfilment task
	 * @throws NoSuchTaskException if a matching fulfilment task could not be found
	 */
	public FulfilmentTask findByCorrelationId(long correlationId)
		throws NoSuchTaskException;

	/**
	 * Returns the fulfilment task where correlationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param correlationId the correlation ID
	 * @return the matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	public FulfilmentTask fetchByCorrelationId(long correlationId);

	/**
	 * Returns the fulfilment task where correlationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param correlationId the correlation ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	public FulfilmentTask fetchByCorrelationId(
		long correlationId, boolean useFinderCache);

	/**
	 * Removes the fulfilment task where correlationId = &#63; from the database.
	 *
	 * @param correlationId the correlation ID
	 * @return the fulfilment task that was removed
	 */
	public FulfilmentTask removeByCorrelationId(long correlationId)
		throws NoSuchTaskException;

	/**
	 * Returns the number of fulfilment tasks where correlationId = &#63;.
	 *
	 * @param correlationId the correlation ID
	 * @return the number of matching fulfilment tasks
	 */
	public int countByCorrelationId(long correlationId);

	/**
	 * Returns all the fulfilment tasks where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @return the matching fulfilment tasks
	 */
	public java.util.List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId);

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
	public java.util.List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId, int start, int end);

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
	public java.util.List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentTask>
			orderByComparator);

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
	public java.util.List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentTask>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment task
	 * @throws NoSuchTaskException if a matching fulfilment task could not be found
	 */
	public FulfilmentTask findByFulfilmentRequestId_First(
			long fulfilmentRequestId,
			com.liferay.portal.kernel.util.OrderByComparator<FulfilmentTask>
				orderByComparator)
		throws NoSuchTaskException;

	/**
	 * Returns the first fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	public FulfilmentTask fetchByFulfilmentRequestId_First(
		long fulfilmentRequestId,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentTask>
			orderByComparator);

	/**
	 * Returns the last fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment task
	 * @throws NoSuchTaskException if a matching fulfilment task could not be found
	 */
	public FulfilmentTask findByFulfilmentRequestId_Last(
			long fulfilmentRequestId,
			com.liferay.portal.kernel.util.OrderByComparator<FulfilmentTask>
				orderByComparator)
		throws NoSuchTaskException;

	/**
	 * Returns the last fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	public FulfilmentTask fetchByFulfilmentRequestId_Last(
		long fulfilmentRequestId,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentTask>
			orderByComparator);

	/**
	 * Returns the fulfilment tasks before and after the current fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentTaskId the primary key of the current fulfilment task
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fulfilment task
	 * @throws NoSuchTaskException if a fulfilment task with the primary key could not be found
	 */
	public FulfilmentTask[] findByFulfilmentRequestId_PrevAndNext(
			long fulfilmentTaskId, long fulfilmentRequestId,
			com.liferay.portal.kernel.util.OrderByComparator<FulfilmentTask>
				orderByComparator)
		throws NoSuchTaskException;

	/**
	 * Removes all the fulfilment tasks where fulfilmentRequestId = &#63; from the database.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 */
	public void removeByFulfilmentRequestId(long fulfilmentRequestId);

	/**
	 * Returns the number of fulfilment tasks where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @return the number of matching fulfilment tasks
	 */
	public int countByFulfilmentRequestId(long fulfilmentRequestId);

	/**
	 * Caches the fulfilment task in the entity cache if it is enabled.
	 *
	 * @param fulfilmentTask the fulfilment task
	 */
	public void cacheResult(FulfilmentTask fulfilmentTask);

	/**
	 * Caches the fulfilment tasks in the entity cache if it is enabled.
	 *
	 * @param fulfilmentTasks the fulfilment tasks
	 */
	public void cacheResult(java.util.List<FulfilmentTask> fulfilmentTasks);

	/**
	 * Creates a new fulfilment task with the primary key. Does not add the fulfilment task to the database.
	 *
	 * @param fulfilmentTaskId the primary key for the new fulfilment task
	 * @return the new fulfilment task
	 */
	public FulfilmentTask create(long fulfilmentTaskId);

	/**
	 * Removes the fulfilment task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task that was removed
	 * @throws NoSuchTaskException if a fulfilment task with the primary key could not be found
	 */
	public FulfilmentTask remove(long fulfilmentTaskId)
		throws NoSuchTaskException;

	public FulfilmentTask updateImpl(FulfilmentTask fulfilmentTask);

	/**
	 * Returns the fulfilment task with the primary key or throws a <code>NoSuchTaskException</code> if it could not be found.
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task
	 * @throws NoSuchTaskException if a fulfilment task with the primary key could not be found
	 */
	public FulfilmentTask findByPrimaryKey(long fulfilmentTaskId)
		throws NoSuchTaskException;

	/**
	 * Returns the fulfilment task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task, or <code>null</code> if a fulfilment task with the primary key could not be found
	 */
	public FulfilmentTask fetchByPrimaryKey(long fulfilmentTaskId);

	/**
	 * Returns all the fulfilment tasks.
	 *
	 * @return the fulfilment tasks
	 */
	public java.util.List<FulfilmentTask> findAll();

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
	public java.util.List<FulfilmentTask> findAll(int start, int end);

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
	public java.util.List<FulfilmentTask> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentTask>
			orderByComparator);

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
	public java.util.List<FulfilmentTask> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentTask>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the fulfilment tasks from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of fulfilment tasks.
	 *
	 * @return the number of fulfilment tasks
	 */
	public int countAll();

}