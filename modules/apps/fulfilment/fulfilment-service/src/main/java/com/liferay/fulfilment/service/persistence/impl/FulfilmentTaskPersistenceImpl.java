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

package com.liferay.fulfilment.service.persistence.impl;

import com.liferay.fulfilment.exception.NoSuchTaskException;
import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.fulfilment.model.FulfilmentTaskTable;
import com.liferay.fulfilment.model.impl.FulfilmentTaskImpl;
import com.liferay.fulfilment.model.impl.FulfilmentTaskModelImpl;
import com.liferay.fulfilment.service.persistence.FulfilmentTaskPersistence;
import com.liferay.fulfilment.service.persistence.FulfilmentTaskUtil;
import com.liferay.fulfilment.service.persistence.impl.constants.FulfilmentPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.BasePersistence;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the fulfilment task service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @generated
 */
@Component(service = {FulfilmentTaskPersistence.class, BasePersistence.class})
public class FulfilmentTaskPersistenceImpl
	extends BasePersistenceImpl<FulfilmentTask>
	implements FulfilmentTaskPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FulfilmentTaskUtil</code> to access the fulfilment task persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FulfilmentTaskImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByCorrelationId;
	private FinderPath _finderPathCountByCorrelationId;

	/**
	 * Returns the fulfilment task where correlationId = &#63; or throws a <code>NoSuchTaskException</code> if it could not be found.
	 *
	 * @param correlationId the correlation ID
	 * @return the matching fulfilment task
	 * @throws NoSuchTaskException if a matching fulfilment task could not be found
	 */
	@Override
	public FulfilmentTask findByCorrelationId(long correlationId)
		throws NoSuchTaskException {

		FulfilmentTask fulfilmentTask = fetchByCorrelationId(correlationId);

		if (fulfilmentTask == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("correlationId=");
			sb.append(correlationId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchTaskException(sb.toString());
		}

		return fulfilmentTask;
	}

	/**
	 * Returns the fulfilment task where correlationId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param correlationId the correlation ID
	 * @return the matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	@Override
	public FulfilmentTask fetchByCorrelationId(long correlationId) {
		return fetchByCorrelationId(correlationId, true);
	}

	/**
	 * Returns the fulfilment task where correlationId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param correlationId the correlation ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	@Override
	public FulfilmentTask fetchByCorrelationId(
		long correlationId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {correlationId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByCorrelationId, finderArgs);
		}

		if (result instanceof FulfilmentTask) {
			FulfilmentTask fulfilmentTask = (FulfilmentTask)result;

			if (correlationId != fulfilmentTask.getCorrelationId()) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_FULFILMENTTASK_WHERE);

			sb.append(_FINDER_COLUMN_CORRELATIONID_CORRELATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(correlationId);

				List<FulfilmentTask> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByCorrelationId, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {correlationId};
							}

							_log.warn(
								"FulfilmentTaskPersistenceImpl.fetchByCorrelationId(long, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					FulfilmentTask fulfilmentTask = list.get(0);

					result = fulfilmentTask;

					cacheResult(fulfilmentTask);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (FulfilmentTask)result;
		}
	}

	/**
	 * Removes the fulfilment task where correlationId = &#63; from the database.
	 *
	 * @param correlationId the correlation ID
	 * @return the fulfilment task that was removed
	 */
	@Override
	public FulfilmentTask removeByCorrelationId(long correlationId)
		throws NoSuchTaskException {

		FulfilmentTask fulfilmentTask = findByCorrelationId(correlationId);

		return remove(fulfilmentTask);
	}

	/**
	 * Returns the number of fulfilment tasks where correlationId = &#63;.
	 *
	 * @param correlationId the correlation ID
	 * @return the number of matching fulfilment tasks
	 */
	@Override
	public int countByCorrelationId(long correlationId) {
		FinderPath finderPath = _finderPathCountByCorrelationId;

		Object[] finderArgs = new Object[] {correlationId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FULFILMENTTASK_WHERE);

			sb.append(_FINDER_COLUMN_CORRELATIONID_CORRELATIONID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(correlationId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_CORRELATIONID_CORRELATIONID_2 =
		"fulfilmentTask.correlationId = ?";

	private FinderPath _finderPathWithPaginationFindByFulfilmentRequestId;
	private FinderPath _finderPathWithoutPaginationFindByFulfilmentRequestId;
	private FinderPath _finderPathCountByFulfilmentRequestId;

	/**
	 * Returns all the fulfilment tasks where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @return the matching fulfilment tasks
	 */
	@Override
	public List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId) {

		return findByFulfilmentRequestId(
			fulfilmentRequestId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId, int start, int end) {

		return findByFulfilmentRequestId(fulfilmentRequestId, start, end, null);
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
	@Override
	public List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId, int start, int end,
		OrderByComparator<FulfilmentTask> orderByComparator) {

		return findByFulfilmentRequestId(
			fulfilmentRequestId, start, end, orderByComparator, true);
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
	@Override
	public List<FulfilmentTask> findByFulfilmentRequestId(
		long fulfilmentRequestId, int start, int end,
		OrderByComparator<FulfilmentTask> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByFulfilmentRequestId;
				finderArgs = new Object[] {fulfilmentRequestId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByFulfilmentRequestId;
			finderArgs = new Object[] {
				fulfilmentRequestId, start, end, orderByComparator
			};
		}

		List<FulfilmentTask> list = null;

		if (useFinderCache) {
			list = (List<FulfilmentTask>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (FulfilmentTask fulfilmentTask : list) {
					if (fulfilmentRequestId !=
							fulfilmentTask.getFulfilmentRequestId()) {

						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_FULFILMENTTASK_WHERE);

			sb.append(_FINDER_COLUMN_FULFILMENTREQUESTID_FULFILMENTREQUESTID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FulfilmentTaskModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fulfilmentRequestId);

				list = (List<FulfilmentTask>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment task
	 * @throws NoSuchTaskException if a matching fulfilment task could not be found
	 */
	@Override
	public FulfilmentTask findByFulfilmentRequestId_First(
			long fulfilmentRequestId,
			OrderByComparator<FulfilmentTask> orderByComparator)
		throws NoSuchTaskException {

		FulfilmentTask fulfilmentTask = fetchByFulfilmentRequestId_First(
			fulfilmentRequestId, orderByComparator);

		if (fulfilmentTask != null) {
			return fulfilmentTask;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fulfilmentRequestId=");
		sb.append(fulfilmentRequestId);

		sb.append("}");

		throw new NoSuchTaskException(sb.toString());
	}

	/**
	 * Returns the first fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	@Override
	public FulfilmentTask fetchByFulfilmentRequestId_First(
		long fulfilmentRequestId,
		OrderByComparator<FulfilmentTask> orderByComparator) {

		List<FulfilmentTask> list = findByFulfilmentRequestId(
			fulfilmentRequestId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment task
	 * @throws NoSuchTaskException if a matching fulfilment task could not be found
	 */
	@Override
	public FulfilmentTask findByFulfilmentRequestId_Last(
			long fulfilmentRequestId,
			OrderByComparator<FulfilmentTask> orderByComparator)
		throws NoSuchTaskException {

		FulfilmentTask fulfilmentTask = fetchByFulfilmentRequestId_Last(
			fulfilmentRequestId, orderByComparator);

		if (fulfilmentTask != null) {
			return fulfilmentTask;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("fulfilmentRequestId=");
		sb.append(fulfilmentRequestId);

		sb.append("}");

		throw new NoSuchTaskException(sb.toString());
	}

	/**
	 * Returns the last fulfilment task in the ordered set where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment task, or <code>null</code> if a matching fulfilment task could not be found
	 */
	@Override
	public FulfilmentTask fetchByFulfilmentRequestId_Last(
		long fulfilmentRequestId,
		OrderByComparator<FulfilmentTask> orderByComparator) {

		int count = countByFulfilmentRequestId(fulfilmentRequestId);

		if (count == 0) {
			return null;
		}

		List<FulfilmentTask> list = findByFulfilmentRequestId(
			fulfilmentRequestId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public FulfilmentTask[] findByFulfilmentRequestId_PrevAndNext(
			long fulfilmentTaskId, long fulfilmentRequestId,
			OrderByComparator<FulfilmentTask> orderByComparator)
		throws NoSuchTaskException {

		FulfilmentTask fulfilmentTask = findByPrimaryKey(fulfilmentTaskId);

		Session session = null;

		try {
			session = openSession();

			FulfilmentTask[] array = new FulfilmentTaskImpl[3];

			array[0] = getByFulfilmentRequestId_PrevAndNext(
				session, fulfilmentTask, fulfilmentRequestId, orderByComparator,
				true);

			array[1] = fulfilmentTask;

			array[2] = getByFulfilmentRequestId_PrevAndNext(
				session, fulfilmentTask, fulfilmentRequestId, orderByComparator,
				false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FulfilmentTask getByFulfilmentRequestId_PrevAndNext(
		Session session, FulfilmentTask fulfilmentTask,
		long fulfilmentRequestId,
		OrderByComparator<FulfilmentTask> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FULFILMENTTASK_WHERE);

		sb.append(_FINDER_COLUMN_FULFILMENTREQUESTID_FULFILMENTREQUESTID_2);

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(FulfilmentTaskModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(fulfilmentRequestId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						fulfilmentTask)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FulfilmentTask> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fulfilment tasks where fulfilmentRequestId = &#63; from the database.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 */
	@Override
	public void removeByFulfilmentRequestId(long fulfilmentRequestId) {
		for (FulfilmentTask fulfilmentTask :
				findByFulfilmentRequestId(
					fulfilmentRequestId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
					null)) {

			remove(fulfilmentTask);
		}
	}

	/**
	 * Returns the number of fulfilment tasks where fulfilmentRequestId = &#63;.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID
	 * @return the number of matching fulfilment tasks
	 */
	@Override
	public int countByFulfilmentRequestId(long fulfilmentRequestId) {
		FinderPath finderPath = _finderPathCountByFulfilmentRequestId;

		Object[] finderArgs = new Object[] {fulfilmentRequestId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FULFILMENTTASK_WHERE);

			sb.append(_FINDER_COLUMN_FULFILMENTREQUESTID_FULFILMENTREQUESTID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(fulfilmentRequestId);

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String
		_FINDER_COLUMN_FULFILMENTREQUESTID_FULFILMENTREQUESTID_2 =
			"fulfilmentTask.fulfilmentRequestId = ?";

	public FulfilmentTaskPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("index", "index_");
		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(FulfilmentTask.class);

		setModelImplClass(FulfilmentTaskImpl.class);
		setModelPKClass(long.class);

		setTable(FulfilmentTaskTable.INSTANCE);
	}

	/**
	 * Caches the fulfilment task in the entity cache if it is enabled.
	 *
	 * @param fulfilmentTask the fulfilment task
	 */
	@Override
	public void cacheResult(FulfilmentTask fulfilmentTask) {
		entityCache.putResult(
			FulfilmentTaskImpl.class, fulfilmentTask.getPrimaryKey(),
			fulfilmentTask);

		finderCache.putResult(
			_finderPathFetchByCorrelationId,
			new Object[] {fulfilmentTask.getCorrelationId()}, fulfilmentTask);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the fulfilment tasks in the entity cache if it is enabled.
	 *
	 * @param fulfilmentTasks the fulfilment tasks
	 */
	@Override
	public void cacheResult(List<FulfilmentTask> fulfilmentTasks) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (fulfilmentTasks.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FulfilmentTask fulfilmentTask : fulfilmentTasks) {
			if (entityCache.getResult(
					FulfilmentTaskImpl.class, fulfilmentTask.getPrimaryKey()) ==
						null) {

				cacheResult(fulfilmentTask);
			}
		}
	}

	/**
	 * Clears the cache for all fulfilment tasks.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FulfilmentTaskImpl.class);

		finderCache.clearCache(FulfilmentTaskImpl.class);
	}

	/**
	 * Clears the cache for the fulfilment task.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FulfilmentTask fulfilmentTask) {
		entityCache.removeResult(FulfilmentTaskImpl.class, fulfilmentTask);
	}

	@Override
	public void clearCache(List<FulfilmentTask> fulfilmentTasks) {
		for (FulfilmentTask fulfilmentTask : fulfilmentTasks) {
			entityCache.removeResult(FulfilmentTaskImpl.class, fulfilmentTask);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FulfilmentTaskImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FulfilmentTaskImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		FulfilmentTaskModelImpl fulfilmentTaskModelImpl) {

		Object[] args = new Object[] {
			fulfilmentTaskModelImpl.getCorrelationId()
		};

		finderCache.putResult(
			_finderPathCountByCorrelationId, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByCorrelationId, args, fulfilmentTaskModelImpl);
	}

	/**
	 * Creates a new fulfilment task with the primary key. Does not add the fulfilment task to the database.
	 *
	 * @param fulfilmentTaskId the primary key for the new fulfilment task
	 * @return the new fulfilment task
	 */
	@Override
	public FulfilmentTask create(long fulfilmentTaskId) {
		FulfilmentTask fulfilmentTask = new FulfilmentTaskImpl();

		fulfilmentTask.setNew(true);
		fulfilmentTask.setPrimaryKey(fulfilmentTaskId);

		fulfilmentTask.setCompanyId(CompanyThreadLocal.getCompanyId());

		return fulfilmentTask;
	}

	/**
	 * Removes the fulfilment task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task that was removed
	 * @throws NoSuchTaskException if a fulfilment task with the primary key could not be found
	 */
	@Override
	public FulfilmentTask remove(long fulfilmentTaskId)
		throws NoSuchTaskException {

		return remove((Serializable)fulfilmentTaskId);
	}

	/**
	 * Removes the fulfilment task with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the fulfilment task
	 * @return the fulfilment task that was removed
	 * @throws NoSuchTaskException if a fulfilment task with the primary key could not be found
	 */
	@Override
	public FulfilmentTask remove(Serializable primaryKey)
		throws NoSuchTaskException {

		Session session = null;

		try {
			session = openSession();

			FulfilmentTask fulfilmentTask = (FulfilmentTask)session.get(
				FulfilmentTaskImpl.class, primaryKey);

			if (fulfilmentTask == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTaskException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(fulfilmentTask);
		}
		catch (NoSuchTaskException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected FulfilmentTask removeImpl(FulfilmentTask fulfilmentTask) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fulfilmentTask)) {
				fulfilmentTask = (FulfilmentTask)session.get(
					FulfilmentTaskImpl.class,
					fulfilmentTask.getPrimaryKeyObj());
			}

			if (fulfilmentTask != null) {
				session.delete(fulfilmentTask);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (fulfilmentTask != null) {
			clearCache(fulfilmentTask);
		}

		return fulfilmentTask;
	}

	@Override
	public FulfilmentTask updateImpl(FulfilmentTask fulfilmentTask) {
		boolean isNew = fulfilmentTask.isNew();

		if (!(fulfilmentTask instanceof FulfilmentTaskModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(fulfilmentTask.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					fulfilmentTask);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in fulfilmentTask proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FulfilmentTask implementation " +
					fulfilmentTask.getClass());
		}

		FulfilmentTaskModelImpl fulfilmentTaskModelImpl =
			(FulfilmentTaskModelImpl)fulfilmentTask;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (fulfilmentTask.getCreateDate() == null)) {
			if (serviceContext == null) {
				fulfilmentTask.setCreateDate(date);
			}
			else {
				fulfilmentTask.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!fulfilmentTaskModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				fulfilmentTask.setModifiedDate(date);
			}
			else {
				fulfilmentTask.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(fulfilmentTask);
			}
			else {
				fulfilmentTask = (FulfilmentTask)session.merge(fulfilmentTask);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FulfilmentTaskImpl.class, fulfilmentTaskModelImpl, false, true);

		cacheUniqueFindersCache(fulfilmentTaskModelImpl);

		if (isNew) {
			fulfilmentTask.setNew(false);
		}

		fulfilmentTask.resetOriginalValues();

		return fulfilmentTask;
	}

	/**
	 * Returns the fulfilment task with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the fulfilment task
	 * @return the fulfilment task
	 * @throws NoSuchTaskException if a fulfilment task with the primary key could not be found
	 */
	@Override
	public FulfilmentTask findByPrimaryKey(Serializable primaryKey)
		throws NoSuchTaskException {

		FulfilmentTask fulfilmentTask = fetchByPrimaryKey(primaryKey);

		if (fulfilmentTask == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchTaskException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return fulfilmentTask;
	}

	/**
	 * Returns the fulfilment task with the primary key or throws a <code>NoSuchTaskException</code> if it could not be found.
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task
	 * @throws NoSuchTaskException if a fulfilment task with the primary key could not be found
	 */
	@Override
	public FulfilmentTask findByPrimaryKey(long fulfilmentTaskId)
		throws NoSuchTaskException {

		return findByPrimaryKey((Serializable)fulfilmentTaskId);
	}

	/**
	 * Returns the fulfilment task with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fulfilmentTaskId the primary key of the fulfilment task
	 * @return the fulfilment task, or <code>null</code> if a fulfilment task with the primary key could not be found
	 */
	@Override
	public FulfilmentTask fetchByPrimaryKey(long fulfilmentTaskId) {
		return fetchByPrimaryKey((Serializable)fulfilmentTaskId);
	}

	/**
	 * Returns all the fulfilment tasks.
	 *
	 * @return the fulfilment tasks
	 */
	@Override
	public List<FulfilmentTask> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<FulfilmentTask> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<FulfilmentTask> findAll(
		int start, int end,
		OrderByComparator<FulfilmentTask> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<FulfilmentTask> findAll(
		int start, int end, OrderByComparator<FulfilmentTask> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<FulfilmentTask> list = null;

		if (useFinderCache) {
			list = (List<FulfilmentTask>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FULFILMENTTASK);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FULFILMENTTASK;

				sql = sql.concat(FulfilmentTaskModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FulfilmentTask>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the fulfilment tasks from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FulfilmentTask fulfilmentTask : findAll()) {
			remove(fulfilmentTask);
		}
	}

	/**
	 * Returns the number of fulfilment tasks.
	 *
	 * @return the number of fulfilment tasks
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FULFILMENTTASK);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "fulfilmentTaskId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FULFILMENTTASK;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FulfilmentTaskModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the fulfilment task persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathFetchByCorrelationId = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByCorrelationId",
			new String[] {Long.class.getName()}, new String[] {"correlationId"},
			true);

		_finderPathCountByCorrelationId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCorrelationId",
			new String[] {Long.class.getName()}, new String[] {"correlationId"},
			false);

		_finderPathWithPaginationFindByFulfilmentRequestId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByFulfilmentRequestId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"fulfilmentRequestId"}, true);

		_finderPathWithoutPaginationFindByFulfilmentRequestId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByFulfilmentRequestId", new String[] {Long.class.getName()},
			new String[] {"fulfilmentRequestId"}, true);

		_finderPathCountByFulfilmentRequestId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByFulfilmentRequestId", new String[] {Long.class.getName()},
			new String[] {"fulfilmentRequestId"}, false);

		_setFulfilmentTaskUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setFulfilmentTaskUtilPersistence(null);

		entityCache.removeCache(FulfilmentTaskImpl.class.getName());
	}

	private void _setFulfilmentTaskUtilPersistence(
		FulfilmentTaskPersistence fulfilmentTaskPersistence) {

		try {
			Field field = FulfilmentTaskUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, fulfilmentTaskPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = FulfilmentPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = FulfilmentPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = FulfilmentPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_FULFILMENTTASK =
		"SELECT fulfilmentTask FROM FulfilmentTask fulfilmentTask";

	private static final String _SQL_SELECT_FULFILMENTTASK_WHERE =
		"SELECT fulfilmentTask FROM FulfilmentTask fulfilmentTask WHERE ";

	private static final String _SQL_COUNT_FULFILMENTTASK =
		"SELECT COUNT(fulfilmentTask) FROM FulfilmentTask fulfilmentTask";

	private static final String _SQL_COUNT_FULFILMENTTASK_WHERE =
		"SELECT COUNT(fulfilmentTask) FROM FulfilmentTask fulfilmentTask WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "fulfilmentTask.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FulfilmentTask exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FulfilmentTask exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FulfilmentTaskPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"index", "type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private FulfilmentTaskModelArgumentsResolver
		_fulfilmentTaskModelArgumentsResolver;

}