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

import com.liferay.fulfilment.exception.NoSuchRequestException;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.model.FulfilmentRequestTable;
import com.liferay.fulfilment.model.impl.FulfilmentRequestImpl;
import com.liferay.fulfilment.model.impl.FulfilmentRequestModelImpl;
import com.liferay.fulfilment.service.persistence.FulfilmentRequestPersistence;
import com.liferay.fulfilment.service.persistence.FulfilmentRequestUtil;
import com.liferay.fulfilment.service.persistence.impl.constants.FulfilmentPersistenceConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
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
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the fulfilment request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @generated
 */
@Component(
	service = {FulfilmentRequestPersistence.class, BasePersistence.class}
)
public class FulfilmentRequestPersistenceImpl
	extends BasePersistenceImpl<FulfilmentRequest>
	implements FulfilmentRequestPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>FulfilmentRequestUtil</code> to access the fulfilment request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		FulfilmentRequestImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUserId;
	private FinderPath _finderPathWithoutPaginationFindByUserId;
	private FinderPath _finderPathCountByUserId;

	/**
	 * Returns all the fulfilment requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching fulfilment requests
	 */
	@Override
	public List<FulfilmentRequest> findByUserId(long userId) {
		return findByUserId(userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fulfilment requests where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of fulfilment requests
	 * @param end the upper bound of the range of fulfilment requests (not inclusive)
	 * @return the range of matching fulfilment requests
	 */
	@Override
	public List<FulfilmentRequest> findByUserId(
		long userId, int start, int end) {

		return findByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fulfilment requests where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of fulfilment requests
	 * @param end the upper bound of the range of fulfilment requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fulfilment requests
	 */
	@Override
	public List<FulfilmentRequest> findByUserId(
		long userId, int start, int end,
		OrderByComparator<FulfilmentRequest> orderByComparator) {

		return findByUserId(userId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fulfilment requests where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of fulfilment requests
	 * @param end the upper bound of the range of fulfilment requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching fulfilment requests
	 */
	@Override
	public List<FulfilmentRequest> findByUserId(
		long userId, int start, int end,
		OrderByComparator<FulfilmentRequest> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUserId;
				finderArgs = new Object[] {userId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUserId;
			finderArgs = new Object[] {userId, start, end, orderByComparator};
		}

		List<FulfilmentRequest> list = null;

		if (useFinderCache) {
			list = (List<FulfilmentRequest>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (FulfilmentRequest fulfilmentRequest : list) {
					if (userId != fulfilmentRequest.getUserId()) {
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

			sb.append(_SQL_SELECT_FULFILMENTREQUEST_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(FulfilmentRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

				list = (List<FulfilmentRequest>)QueryUtil.list(
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
	 * Returns the first fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment request
	 * @throws NoSuchRequestException if a matching fulfilment request could not be found
	 */
	@Override
	public FulfilmentRequest findByUserId_First(
			long userId, OrderByComparator<FulfilmentRequest> orderByComparator)
		throws NoSuchRequestException {

		FulfilmentRequest fulfilmentRequest = fetchByUserId_First(
			userId, orderByComparator);

		if (fulfilmentRequest != null) {
			return fulfilmentRequest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the first fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	@Override
	public FulfilmentRequest fetchByUserId_First(
		long userId, OrderByComparator<FulfilmentRequest> orderByComparator) {

		List<FulfilmentRequest> list = findByUserId(
			userId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment request
	 * @throws NoSuchRequestException if a matching fulfilment request could not be found
	 */
	@Override
	public FulfilmentRequest findByUserId_Last(
			long userId, OrderByComparator<FulfilmentRequest> orderByComparator)
		throws NoSuchRequestException {

		FulfilmentRequest fulfilmentRequest = fetchByUserId_Last(
			userId, orderByComparator);

		if (fulfilmentRequest != null) {
			return fulfilmentRequest;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("userId=");
		sb.append(userId);

		sb.append("}");

		throw new NoSuchRequestException(sb.toString());
	}

	/**
	 * Returns the last fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	@Override
	public FulfilmentRequest fetchByUserId_Last(
		long userId, OrderByComparator<FulfilmentRequest> orderByComparator) {

		int count = countByUserId(userId);

		if (count == 0) {
			return null;
		}

		List<FulfilmentRequest> list = findByUserId(
			userId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the fulfilment requests before and after the current fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param fulfilmentRequestId the primary key of the current fulfilment request
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fulfilment request
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	@Override
	public FulfilmentRequest[] findByUserId_PrevAndNext(
			long fulfilmentRequestId, long userId,
			OrderByComparator<FulfilmentRequest> orderByComparator)
		throws NoSuchRequestException {

		FulfilmentRequest fulfilmentRequest = findByPrimaryKey(
			fulfilmentRequestId);

		Session session = null;

		try {
			session = openSession();

			FulfilmentRequest[] array = new FulfilmentRequestImpl[3];

			array[0] = getByUserId_PrevAndNext(
				session, fulfilmentRequest, userId, orderByComparator, true);

			array[1] = fulfilmentRequest;

			array[2] = getByUserId_PrevAndNext(
				session, fulfilmentRequest, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FulfilmentRequest getByUserId_PrevAndNext(
		Session session, FulfilmentRequest fulfilmentRequest, long userId,
		OrderByComparator<FulfilmentRequest> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_FULFILMENTREQUEST_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

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
			sb.append(FulfilmentRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						fulfilmentRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FulfilmentRequest> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the fulfilment requests that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching fulfilment requests that the user has permission to view
	 */
	@Override
	public List<FulfilmentRequest> filterFindByUserId(long userId) {
		return filterFindByUserId(
			userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fulfilment requests that the user has permission to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of fulfilment requests
	 * @param end the upper bound of the range of fulfilment requests (not inclusive)
	 * @return the range of matching fulfilment requests that the user has permission to view
	 */
	@Override
	public List<FulfilmentRequest> filterFindByUserId(
		long userId, int start, int end) {

		return filterFindByUserId(userId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the fulfilment requests that the user has permissions to view where userId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentRequestModelImpl</code>.
	 * </p>
	 *
	 * @param userId the user ID
	 * @param start the lower bound of the range of fulfilment requests
	 * @param end the upper bound of the range of fulfilment requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching fulfilment requests that the user has permission to view
	 */
	@Override
	public List<FulfilmentRequest> filterFindByUserId(
		long userId, int start, int end,
		OrderByComparator<FulfilmentRequest> orderByComparator) {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId(userId, start, end, orderByComparator);
		}

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				3 + (orderByComparator.getOrderByFields().length * 2));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_FULFILMENTREQUEST_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_FULFILMENTREQUEST_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_FULFILMENTREQUEST_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			if (getDB().isSupportsInlineDistinct()) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator, true);
			}
			else {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_TABLE, orderByComparator, true);
			}
		}
		else {
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(FulfilmentRequestModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(FulfilmentRequestModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FulfilmentRequest.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			if (getDB().isSupportsInlineDistinct()) {
				sqlQuery.addEntity(
					_FILTER_ENTITY_ALIAS, FulfilmentRequestImpl.class);
			}
			else {
				sqlQuery.addEntity(
					_FILTER_ENTITY_TABLE, FulfilmentRequestImpl.class);
			}

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(userId);

			return (List<FulfilmentRequest>)QueryUtil.list(
				sqlQuery, getDialect(), start, end);
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	/**
	 * Returns the fulfilment requests before and after the current fulfilment request in the ordered set of fulfilment requests that the user has permission to view where userId = &#63;.
	 *
	 * @param fulfilmentRequestId the primary key of the current fulfilment request
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fulfilment request
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	@Override
	public FulfilmentRequest[] filterFindByUserId_PrevAndNext(
			long fulfilmentRequestId, long userId,
			OrderByComparator<FulfilmentRequest> orderByComparator)
		throws NoSuchRequestException {

		if (!InlineSQLHelperUtil.isEnabled()) {
			return findByUserId_PrevAndNext(
				fulfilmentRequestId, userId, orderByComparator);
		}

		FulfilmentRequest fulfilmentRequest = findByPrimaryKey(
			fulfilmentRequestId);

		Session session = null;

		try {
			session = openSession();

			FulfilmentRequest[] array = new FulfilmentRequestImpl[3];

			array[0] = filterGetByUserId_PrevAndNext(
				session, fulfilmentRequest, userId, orderByComparator, true);

			array[1] = fulfilmentRequest;

			array[2] = filterGetByUserId_PrevAndNext(
				session, fulfilmentRequest, userId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected FulfilmentRequest filterGetByUserId_PrevAndNext(
		Session session, FulfilmentRequest fulfilmentRequest, long userId,
		OrderByComparator<FulfilmentRequest> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				5 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(4);
		}

		if (getDB().isSupportsInlineDistinct()) {
			sb.append(_FILTER_SQL_SELECT_FULFILMENTREQUEST_WHERE);
		}
		else {
			sb.append(
				_FILTER_SQL_SELECT_FULFILMENTREQUEST_NO_INLINE_DISTINCT_WHERE_1);
		}

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

		if (!getDB().isSupportsInlineDistinct()) {
			sb.append(
				_FILTER_SQL_SELECT_FULFILMENTREQUEST_NO_INLINE_DISTINCT_WHERE_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByConditionFields[i],
							true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByConditionFields[i],
							true));
				}

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
				if (getDB().isSupportsInlineDistinct()) {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_ALIAS, orderByFields[i], true));
				}
				else {
					sb.append(
						getColumnName(
							_ORDER_BY_ENTITY_TABLE, orderByFields[i], true));
				}

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
			if (getDB().isSupportsInlineDistinct()) {
				sb.append(FulfilmentRequestModelImpl.ORDER_BY_JPQL);
			}
			else {
				sb.append(FulfilmentRequestModelImpl.ORDER_BY_SQL);
			}
		}

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FulfilmentRequest.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

		sqlQuery.setFirstResult(0);
		sqlQuery.setMaxResults(2);

		if (getDB().isSupportsInlineDistinct()) {
			sqlQuery.addEntity(
				_FILTER_ENTITY_ALIAS, FulfilmentRequestImpl.class);
		}
		else {
			sqlQuery.addEntity(
				_FILTER_ENTITY_TABLE, FulfilmentRequestImpl.class);
		}

		QueryPos queryPos = QueryPos.getInstance(sqlQuery);

		queryPos.add(userId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						fulfilmentRequest)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<FulfilmentRequest> list = sqlQuery.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the fulfilment requests where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	@Override
	public void removeByUserId(long userId) {
		for (FulfilmentRequest fulfilmentRequest :
				findByUserId(
					userId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(fulfilmentRequest);
		}
	}

	/**
	 * Returns the number of fulfilment requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching fulfilment requests
	 */
	@Override
	public int countByUserId(long userId) {
		FinderPath finderPath = _finderPathCountByUserId;

		Object[] finderArgs = new Object[] {userId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_FULFILMENTREQUEST_WHERE);

			sb.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(userId);

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

	/**
	 * Returns the number of fulfilment requests that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching fulfilment requests that the user has permission to view
	 */
	@Override
	public int filterCountByUserId(long userId) {
		if (!InlineSQLHelperUtil.isEnabled()) {
			return countByUserId(userId);
		}

		StringBundler sb = new StringBundler(2);

		sb.append(_FILTER_SQL_COUNT_FULFILMENTREQUEST_WHERE);

		sb.append(_FINDER_COLUMN_USERID_USERID_2);

		String sql = InlineSQLHelperUtil.replacePermissionCheck(
			sb.toString(), FulfilmentRequest.class.getName(),
			_FILTER_ENTITY_TABLE_FILTER_PK_COLUMN);

		Session session = null;

		try {
			session = openSession();

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(
				COUNT_COLUMN_NAME, com.liferay.portal.kernel.dao.orm.Type.LONG);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(userId);

			Long count = (Long)sqlQuery.uniqueResult();

			return count.intValue();
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	private static final String _FINDER_COLUMN_USERID_USERID_2 =
		"fulfilmentRequest.userId = ?";

	private FinderPath _finderPathFetchByC_ERC;
	private FinderPath _finderPathCountByC_ERC;

	/**
	 * Returns the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchRequestException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching fulfilment request
	 * @throws NoSuchRequestException if a matching fulfilment request could not be found
	 */
	@Override
	public FulfilmentRequest findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchRequestException {

		FulfilmentRequest fulfilmentRequest = fetchByC_ERC(
			companyId, externalReferenceCode);

		if (fulfilmentRequest == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("companyId=");
			sb.append(companyId);

			sb.append(", externalReferenceCode=");
			sb.append(externalReferenceCode);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchRequestException(sb.toString());
		}

		return fulfilmentRequest;
	}

	/**
	 * Returns the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	@Override
	public FulfilmentRequest fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return fetchByC_ERC(companyId, externalReferenceCode, true);
	}

	/**
	 * Returns the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	@Override
	public FulfilmentRequest fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache) {

		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {companyId, externalReferenceCode};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(_finderPathFetchByC_ERC, finderArgs);
		}

		if (result instanceof FulfilmentRequest) {
			FulfilmentRequest fulfilmentRequest = (FulfilmentRequest)result;

			if ((companyId != fulfilmentRequest.getCompanyId()) ||
				!Objects.equals(
					externalReferenceCode,
					fulfilmentRequest.getExternalReferenceCode())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_FULFILMENTREQUEST_WHERE);

			sb.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExternalReferenceCode) {
					queryPos.add(externalReferenceCode);
				}

				List<FulfilmentRequest> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByC_ERC, finderArgs, list);
					}
				}
				else {
					if (list.size() > 1) {
						Collections.sort(list, Collections.reverseOrder());

						if (_log.isWarnEnabled()) {
							if (!useFinderCache) {
								finderArgs = new Object[] {
									companyId, externalReferenceCode
								};
							}

							_log.warn(
								"FulfilmentRequestPersistenceImpl.fetchByC_ERC(long, String, boolean) with parameters (" +
									StringUtil.merge(finderArgs) +
										") yields a result set with more than 1 result. This violates the logical unique restriction. There is no order guarantee on which result is returned by this finder.");
						}
					}

					FulfilmentRequest fulfilmentRequest = list.get(0);

					result = fulfilmentRequest;

					cacheResult(fulfilmentRequest);
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
			return (FulfilmentRequest)result;
		}
	}

	/**
	 * Removes the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the fulfilment request that was removed
	 */
	@Override
	public FulfilmentRequest removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchRequestException {

		FulfilmentRequest fulfilmentRequest = findByC_ERC(
			companyId, externalReferenceCode);

		return remove(fulfilmentRequest);
	}

	/**
	 * Returns the number of fulfilment requests where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching fulfilment requests
	 */
	@Override
	public int countByC_ERC(long companyId, String externalReferenceCode) {
		externalReferenceCode = Objects.toString(externalReferenceCode, "");

		FinderPath finderPath = _finderPathCountByC_ERC;

		Object[] finderArgs = new Object[] {companyId, externalReferenceCode};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_FULFILMENTREQUEST_WHERE);

			sb.append(_FINDER_COLUMN_C_ERC_COMPANYID_2);

			boolean bindExternalReferenceCode = false;

			if (externalReferenceCode.isEmpty()) {
				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3);
			}
			else {
				bindExternalReferenceCode = true;

				sb.append(_FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(companyId);

				if (bindExternalReferenceCode) {
					queryPos.add(externalReferenceCode);
				}

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

	private static final String _FINDER_COLUMN_C_ERC_COMPANYID_2 =
		"fulfilmentRequest.companyId = ? AND ";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_2 =
		"fulfilmentRequest.externalReferenceCode = ?";

	private static final String _FINDER_COLUMN_C_ERC_EXTERNALREFERENCECODE_3 =
		"(fulfilmentRequest.externalReferenceCode IS NULL OR fulfilmentRequest.externalReferenceCode = '')";

	public FulfilmentRequestPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("type", "type_");

		setDBColumnNames(dbColumnNames);

		setModelClass(FulfilmentRequest.class);

		setModelImplClass(FulfilmentRequestImpl.class);
		setModelPKClass(long.class);

		setTable(FulfilmentRequestTable.INSTANCE);
	}

	/**
	 * Caches the fulfilment request in the entity cache if it is enabled.
	 *
	 * @param fulfilmentRequest the fulfilment request
	 */
	@Override
	public void cacheResult(FulfilmentRequest fulfilmentRequest) {
		entityCache.putResult(
			FulfilmentRequestImpl.class, fulfilmentRequest.getPrimaryKey(),
			fulfilmentRequest);

		finderCache.putResult(
			_finderPathFetchByC_ERC,
			new Object[] {
				fulfilmentRequest.getCompanyId(),
				fulfilmentRequest.getExternalReferenceCode()
			},
			fulfilmentRequest);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the fulfilment requests in the entity cache if it is enabled.
	 *
	 * @param fulfilmentRequests the fulfilment requests
	 */
	@Override
	public void cacheResult(List<FulfilmentRequest> fulfilmentRequests) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (fulfilmentRequests.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (FulfilmentRequest fulfilmentRequest : fulfilmentRequests) {
			if (entityCache.getResult(
					FulfilmentRequestImpl.class,
					fulfilmentRequest.getPrimaryKey()) == null) {

				cacheResult(fulfilmentRequest);
			}
		}
	}

	/**
	 * Clears the cache for all fulfilment requests.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(FulfilmentRequestImpl.class);

		finderCache.clearCache(FulfilmentRequestImpl.class);
	}

	/**
	 * Clears the cache for the fulfilment request.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(FulfilmentRequest fulfilmentRequest) {
		entityCache.removeResult(
			FulfilmentRequestImpl.class, fulfilmentRequest);
	}

	@Override
	public void clearCache(List<FulfilmentRequest> fulfilmentRequests) {
		for (FulfilmentRequest fulfilmentRequest : fulfilmentRequests) {
			entityCache.removeResult(
				FulfilmentRequestImpl.class, fulfilmentRequest);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(FulfilmentRequestImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(FulfilmentRequestImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		FulfilmentRequestModelImpl fulfilmentRequestModelImpl) {

		Object[] args = new Object[] {
			fulfilmentRequestModelImpl.getCompanyId(),
			fulfilmentRequestModelImpl.getExternalReferenceCode()
		};

		finderCache.putResult(_finderPathCountByC_ERC, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByC_ERC, args, fulfilmentRequestModelImpl);
	}

	/**
	 * Creates a new fulfilment request with the primary key. Does not add the fulfilment request to the database.
	 *
	 * @param fulfilmentRequestId the primary key for the new fulfilment request
	 * @return the new fulfilment request
	 */
	@Override
	public FulfilmentRequest create(long fulfilmentRequestId) {
		FulfilmentRequest fulfilmentRequest = new FulfilmentRequestImpl();

		fulfilmentRequest.setNew(true);
		fulfilmentRequest.setPrimaryKey(fulfilmentRequestId);

		fulfilmentRequest.setCompanyId(CompanyThreadLocal.getCompanyId());

		return fulfilmentRequest;
	}

	/**
	 * Removes the fulfilment request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request that was removed
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	@Override
	public FulfilmentRequest remove(long fulfilmentRequestId)
		throws NoSuchRequestException {

		return remove((Serializable)fulfilmentRequestId);
	}

	/**
	 * Removes the fulfilment request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the fulfilment request
	 * @return the fulfilment request that was removed
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	@Override
	public FulfilmentRequest remove(Serializable primaryKey)
		throws NoSuchRequestException {

		Session session = null;

		try {
			session = openSession();

			FulfilmentRequest fulfilmentRequest =
				(FulfilmentRequest)session.get(
					FulfilmentRequestImpl.class, primaryKey);

			if (fulfilmentRequest == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchRequestException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(fulfilmentRequest);
		}
		catch (NoSuchRequestException noSuchEntityException) {
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
	protected FulfilmentRequest removeImpl(
		FulfilmentRequest fulfilmentRequest) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(fulfilmentRequest)) {
				fulfilmentRequest = (FulfilmentRequest)session.get(
					FulfilmentRequestImpl.class,
					fulfilmentRequest.getPrimaryKeyObj());
			}

			if (fulfilmentRequest != null) {
				session.delete(fulfilmentRequest);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (fulfilmentRequest != null) {
			clearCache(fulfilmentRequest);
		}

		return fulfilmentRequest;
	}

	@Override
	public FulfilmentRequest updateImpl(FulfilmentRequest fulfilmentRequest) {
		boolean isNew = fulfilmentRequest.isNew();

		if (!(fulfilmentRequest instanceof FulfilmentRequestModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(fulfilmentRequest.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					fulfilmentRequest);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in fulfilmentRequest proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom FulfilmentRequest implementation " +
					fulfilmentRequest.getClass());
		}

		FulfilmentRequestModelImpl fulfilmentRequestModelImpl =
			(FulfilmentRequestModelImpl)fulfilmentRequest;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (fulfilmentRequest.getCreateDate() == null)) {
			if (serviceContext == null) {
				fulfilmentRequest.setCreateDate(date);
			}
			else {
				fulfilmentRequest.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!fulfilmentRequestModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				fulfilmentRequest.setModifiedDate(date);
			}
			else {
				fulfilmentRequest.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(fulfilmentRequest);
			}
			else {
				fulfilmentRequest = (FulfilmentRequest)session.merge(
					fulfilmentRequest);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			FulfilmentRequestImpl.class, fulfilmentRequestModelImpl, false,
			true);

		cacheUniqueFindersCache(fulfilmentRequestModelImpl);

		if (isNew) {
			fulfilmentRequest.setNew(false);
		}

		fulfilmentRequest.resetOriginalValues();

		return fulfilmentRequest;
	}

	/**
	 * Returns the fulfilment request with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the fulfilment request
	 * @return the fulfilment request
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	@Override
	public FulfilmentRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchRequestException {

		FulfilmentRequest fulfilmentRequest = fetchByPrimaryKey(primaryKey);

		if (fulfilmentRequest == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchRequestException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return fulfilmentRequest;
	}

	/**
	 * Returns the fulfilment request with the primary key or throws a <code>NoSuchRequestException</code> if it could not be found.
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	@Override
	public FulfilmentRequest findByPrimaryKey(long fulfilmentRequestId)
		throws NoSuchRequestException {

		return findByPrimaryKey((Serializable)fulfilmentRequestId);
	}

	/**
	 * Returns the fulfilment request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request, or <code>null</code> if a fulfilment request with the primary key could not be found
	 */
	@Override
	public FulfilmentRequest fetchByPrimaryKey(long fulfilmentRequestId) {
		return fetchByPrimaryKey((Serializable)fulfilmentRequestId);
	}

	/**
	 * Returns all the fulfilment requests.
	 *
	 * @return the fulfilment requests
	 */
	@Override
	public List<FulfilmentRequest> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the fulfilment requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fulfilment requests
	 * @param end the upper bound of the range of fulfilment requests (not inclusive)
	 * @return the range of fulfilment requests
	 */
	@Override
	public List<FulfilmentRequest> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the fulfilment requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fulfilment requests
	 * @param end the upper bound of the range of fulfilment requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of fulfilment requests
	 */
	@Override
	public List<FulfilmentRequest> findAll(
		int start, int end,
		OrderByComparator<FulfilmentRequest> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the fulfilment requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>FulfilmentRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fulfilment requests
	 * @param end the upper bound of the range of fulfilment requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of fulfilment requests
	 */
	@Override
	public List<FulfilmentRequest> findAll(
		int start, int end,
		OrderByComparator<FulfilmentRequest> orderByComparator,
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

		List<FulfilmentRequest> list = null;

		if (useFinderCache) {
			list = (List<FulfilmentRequest>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_FULFILMENTREQUEST);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_FULFILMENTREQUEST;

				sql = sql.concat(FulfilmentRequestModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<FulfilmentRequest>)QueryUtil.list(
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
	 * Removes all the fulfilment requests from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (FulfilmentRequest fulfilmentRequest : findAll()) {
			remove(fulfilmentRequest);
		}
	}

	/**
	 * Returns the number of fulfilment requests.
	 *
	 * @return the number of fulfilment requests
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_FULFILMENTREQUEST);

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
		return "fulfilmentRequestId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_FULFILMENTREQUEST;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return FulfilmentRequestModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the fulfilment request persistence.
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

		_finderPathWithPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUserId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"userId"}, true);

		_finderPathWithoutPaginationFindByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"}, true);

		_finderPathCountByUserId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUserId",
			new String[] {Long.class.getName()}, new String[] {"userId"},
			false);

		_finderPathFetchByC_ERC = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "externalReferenceCode"}, true);

		_finderPathCountByC_ERC = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByC_ERC",
			new String[] {Long.class.getName(), String.class.getName()},
			new String[] {"companyId", "externalReferenceCode"}, false);

		_setFulfilmentRequestUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setFulfilmentRequestUtilPersistence(null);

		entityCache.removeCache(FulfilmentRequestImpl.class.getName());
	}

	private void _setFulfilmentRequestUtilPersistence(
		FulfilmentRequestPersistence fulfilmentRequestPersistence) {

		try {
			Field field = FulfilmentRequestUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, fulfilmentRequestPersistence);
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

	private static final String _SQL_SELECT_FULFILMENTREQUEST =
		"SELECT fulfilmentRequest FROM FulfilmentRequest fulfilmentRequest";

	private static final String _SQL_SELECT_FULFILMENTREQUEST_WHERE =
		"SELECT fulfilmentRequest FROM FulfilmentRequest fulfilmentRequest WHERE ";

	private static final String _SQL_COUNT_FULFILMENTREQUEST =
		"SELECT COUNT(fulfilmentRequest) FROM FulfilmentRequest fulfilmentRequest";

	private static final String _SQL_COUNT_FULFILMENTREQUEST_WHERE =
		"SELECT COUNT(fulfilmentRequest) FROM FulfilmentRequest fulfilmentRequest WHERE ";

	private static final String _FILTER_ENTITY_TABLE_FILTER_PK_COLUMN =
		"fulfilmentRequest.fulfilmentRequestId";

	private static final String _FILTER_SQL_SELECT_FULFILMENTREQUEST_WHERE =
		"SELECT DISTINCT {fulfilmentRequest.*} FROM FulfilmentRequest fulfilmentRequest WHERE ";

	private static final String
		_FILTER_SQL_SELECT_FULFILMENTREQUEST_NO_INLINE_DISTINCT_WHERE_1 =
			"SELECT {FulfilmentRequest.*} FROM (SELECT DISTINCT fulfilmentRequest.fulfilmentRequestId FROM FulfilmentRequest fulfilmentRequest WHERE ";

	private static final String
		_FILTER_SQL_SELECT_FULFILMENTREQUEST_NO_INLINE_DISTINCT_WHERE_2 =
			") TEMP_TABLE INNER JOIN FulfilmentRequest ON TEMP_TABLE.fulfilmentRequestId = FulfilmentRequest.fulfilmentRequestId";

	private static final String _FILTER_SQL_COUNT_FULFILMENTREQUEST_WHERE =
		"SELECT COUNT(DISTINCT fulfilmentRequest.fulfilmentRequestId) AS COUNT_VALUE FROM FulfilmentRequest fulfilmentRequest WHERE ";

	private static final String _FILTER_ENTITY_ALIAS = "fulfilmentRequest";

	private static final String _FILTER_ENTITY_TABLE = "FulfilmentRequest";

	private static final String _ORDER_BY_ENTITY_ALIAS = "fulfilmentRequest.";

	private static final String _ORDER_BY_ENTITY_TABLE = "FulfilmentRequest.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No FulfilmentRequest exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No FulfilmentRequest exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		FulfilmentRequestPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"type"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private FulfilmentRequestModelArgumentsResolver
		_fulfilmentRequestModelArgumentsResolver;

}