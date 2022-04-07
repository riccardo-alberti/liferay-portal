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

package com.liferay.commerce.inventory.service.persistence.impl;

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseOrderTypeRelException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseOrderTypeRel;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseOrderTypeRelTable;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseOrderTypeRelImpl;
import com.liferay.commerce.inventory.model.impl.CommerceInventoryWarehouseOrderTypeRelModelImpl;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseOrderTypeRelPersistence;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseOrderTypeRelUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * The persistence implementation for the commerce inventory warehouse order type rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @generated
 */
public class CommerceInventoryWarehouseOrderTypeRelPersistenceImpl
	extends BasePersistenceImpl<CommerceInventoryWarehouseOrderTypeRel>
	implements CommerceInventoryWarehouseOrderTypeRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceInventoryWarehouseOrderTypeRelUtil</code> to access the commerce inventory warehouse order type rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceInventoryWarehouseOrderTypeRelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByUuid;
	private FinderPath _finderPathWithoutPaginationFindByUuid;
	private FinderPath _finderPathCountByUuid;

	/**
	 * Returns all the commerce inventory warehouse order type rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findByUuid(
		String uuid) {

		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse order type rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @return the range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findByUuid(
		String uuid, int start, int end) {

		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse order type rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator) {

		return findByUuid(uuid, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse order type rels where uuid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findByUuid(
		String uuid, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid;
				finderArgs = new Object[] {uuid};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid;
			finderArgs = new Object[] {uuid, start, end, orderByComparator};
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list = null;

		if (useFinderCache) {
			list =
				(List<CommerceInventoryWarehouseOrderTypeRel>)
					finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryWarehouseOrderTypeRel
						commerceInventoryWarehouseOrderTypeRel : list) {

					if (!uuid.equals(
							commerceInventoryWarehouseOrderTypeRel.getUuid())) {

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

			sb.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(
					CommerceInventoryWarehouseOrderTypeRelModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				list =
					(List<CommerceInventoryWarehouseOrderTypeRel>)
						QueryUtil.list(query, getDialect(), start, end);

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
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel findByUuid_First(
			String uuid,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = fetchByUuid_First(
				uuid, orderByComparator);

		if (commerceInventoryWarehouseOrderTypeRel != null) {
			return commerceInventoryWarehouseOrderTypeRel;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchInventoryWarehouseOrderTypeRelException(sb.toString());
	}

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel fetchByUuid_First(
		String uuid,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator) {

		List<CommerceInventoryWarehouseOrderTypeRel> list = findByUuid(
			uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel findByUuid_Last(
			String uuid,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = fetchByUuid_Last(
				uuid, orderByComparator);

		if (commerceInventoryWarehouseOrderTypeRel != null) {
			return commerceInventoryWarehouseOrderTypeRel;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append("}");

		throw new NoSuchInventoryWarehouseOrderTypeRelException(sb.toString());
	}

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel fetchByUuid_Last(
		String uuid,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator) {

		int count = countByUuid(uuid);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list = findByUuid(
			uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory warehouse order type rels before and after the current commerce inventory warehouse order type rel in the ordered set where uuid = &#63;.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the current commerce inventory warehouse order type rel
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel[] findByUuid_PrevAndNext(
			long commerceInventoryWarehouseOrderTypeRelId, String uuid,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		uuid = Objects.toString(uuid, "");

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = findByPrimaryKey(
				commerceInventoryWarehouseOrderTypeRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouseOrderTypeRel[] array =
				new CommerceInventoryWarehouseOrderTypeRelImpl[3];

			array[0] = getByUuid_PrevAndNext(
				session, commerceInventoryWarehouseOrderTypeRel, uuid,
				orderByComparator, true);

			array[1] = commerceInventoryWarehouseOrderTypeRel;

			array[2] = getByUuid_PrevAndNext(
				session, commerceInventoryWarehouseOrderTypeRel, uuid,
				orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceInventoryWarehouseOrderTypeRel getByUuid_PrevAndNext(
		Session session,
		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel,
		String uuid,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator,
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

		sb.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_UUID_2);
		}

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
			sb.append(
				CommerceInventoryWarehouseOrderTypeRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceInventoryWarehouseOrderTypeRel)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory warehouse order type rels where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	@Override
	public void removeByUuid(String uuid) {
		for (CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel :
					findByUuid(
						uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceInventoryWarehouseOrderTypeRel);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouse order type rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce inventory warehouse order type rels
	 */
	@Override
	public int countByUuid(String uuid) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid;

		Object[] finderArgs = new Object[] {uuid};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_UUID_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
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

	private static final String _FINDER_COLUMN_UUID_UUID_2 =
		"commerceInventoryWarehouseOrderTypeRel.uuid = ?";

	private static final String _FINDER_COLUMN_UUID_UUID_3 =
		"(commerceInventoryWarehouseOrderTypeRel.uuid IS NULL OR commerceInventoryWarehouseOrderTypeRel.uuid = '')";

	private FinderPath _finderPathWithPaginationFindByUuid_C;
	private FinderPath _finderPathWithoutPaginationFindByUuid_C;
	private FinderPath _finderPathCountByUuid_C;

	/**
	 * Returns all the commerce inventory warehouse order type rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findByUuid_C(
		String uuid, long companyId) {

		return findByUuid_C(
			uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse order type rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @return the range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findByUuid_C(
		String uuid, long companyId, int start, int end) {

		return findByUuid_C(uuid, companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse order type rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator) {

		return findByUuid_C(
			uuid, companyId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse order type rels where uuid = &#63; and companyId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator,
		boolean useFinderCache) {

		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByUuid_C;
				finderArgs = new Object[] {uuid, companyId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByUuid_C;
			finderArgs = new Object[] {
				uuid, companyId, start, end, orderByComparator
			};
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list = null;

		if (useFinderCache) {
			list =
				(List<CommerceInventoryWarehouseOrderTypeRel>)
					finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryWarehouseOrderTypeRel
						commerceInventoryWarehouseOrderTypeRel : list) {

					if (!uuid.equals(
							commerceInventoryWarehouseOrderTypeRel.getUuid()) ||
						(companyId !=
							commerceInventoryWarehouseOrderTypeRel.
								getCompanyId())) {

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
					4 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(4);
			}

			sb.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(
					CommerceInventoryWarehouseOrderTypeRelModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

				list =
					(List<CommerceInventoryWarehouseOrderTypeRel>)
						QueryUtil.list(query, getDialect(), start, end);

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
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel findByUuid_C_First(
			String uuid, long companyId,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = fetchByUuid_C_First(
				uuid, companyId, orderByComparator);

		if (commerceInventoryWarehouseOrderTypeRel != null) {
			return commerceInventoryWarehouseOrderTypeRel;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchInventoryWarehouseOrderTypeRelException(sb.toString());
	}

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel fetchByUuid_C_First(
		String uuid, long companyId,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator) {

		List<CommerceInventoryWarehouseOrderTypeRel> list = findByUuid_C(
			uuid, companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel findByUuid_C_Last(
			String uuid, long companyId,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = fetchByUuid_C_Last(
				uuid, companyId, orderByComparator);

		if (commerceInventoryWarehouseOrderTypeRel != null) {
			return commerceInventoryWarehouseOrderTypeRel;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("uuid=");
		sb.append(uuid);

		sb.append(", companyId=");
		sb.append(companyId);

		sb.append("}");

		throw new NoSuchInventoryWarehouseOrderTypeRelException(sb.toString());
	}

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel fetchByUuid_C_Last(
		String uuid, long companyId,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator) {

		int count = countByUuid_C(uuid, companyId);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list = findByUuid_C(
			uuid, companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory warehouse order type rels before and after the current commerce inventory warehouse order type rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the current commerce inventory warehouse order type rel
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel[] findByUuid_C_PrevAndNext(
			long commerceInventoryWarehouseOrderTypeRelId, String uuid,
			long companyId,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		uuid = Objects.toString(uuid, "");

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = findByPrimaryKey(
				commerceInventoryWarehouseOrderTypeRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouseOrderTypeRel[] array =
				new CommerceInventoryWarehouseOrderTypeRelImpl[3];

			array[0] = getByUuid_C_PrevAndNext(
				session, commerceInventoryWarehouseOrderTypeRel, uuid,
				companyId, orderByComparator, true);

			array[1] = commerceInventoryWarehouseOrderTypeRel;

			array[2] = getByUuid_C_PrevAndNext(
				session, commerceInventoryWarehouseOrderTypeRel, uuid,
				companyId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceInventoryWarehouseOrderTypeRel getByUuid_C_PrevAndNext(
		Session session,
		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel,
		String uuid, long companyId,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator,
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

		sb.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

		boolean bindUuid = false;

		if (uuid.isEmpty()) {
			sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
		}
		else {
			bindUuid = true;

			sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
		}

		sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

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
			sb.append(
				CommerceInventoryWarehouseOrderTypeRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindUuid) {
			queryPos.add(uuid);
		}

		queryPos.add(companyId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceInventoryWarehouseOrderTypeRel)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory warehouse order type rels where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	@Override
	public void removeByUuid_C(String uuid, long companyId) {
		for (CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel :
					findByUuid_C(
						uuid, companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
						null)) {

			remove(commerceInventoryWarehouseOrderTypeRel);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouse order type rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce inventory warehouse order type rels
	 */
	@Override
	public int countByUuid_C(String uuid, long companyId) {
		uuid = Objects.toString(uuid, "");

		FinderPath finderPath = _finderPathCountByUuid_C;

		Object[] finderArgs = new Object[] {uuid, companyId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

			boolean bindUuid = false;

			if (uuid.isEmpty()) {
				sb.append(_FINDER_COLUMN_UUID_C_UUID_3);
			}
			else {
				bindUuid = true;

				sb.append(_FINDER_COLUMN_UUID_C_UUID_2);
			}

			sb.append(_FINDER_COLUMN_UUID_C_COMPANYID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindUuid) {
					queryPos.add(uuid);
				}

				queryPos.add(companyId);

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

	private static final String _FINDER_COLUMN_UUID_C_UUID_2 =
		"commerceInventoryWarehouseOrderTypeRel.uuid = ? AND ";

	private static final String _FINDER_COLUMN_UUID_C_UUID_3 =
		"(commerceInventoryWarehouseOrderTypeRel.uuid IS NULL OR commerceInventoryWarehouseOrderTypeRel.uuid = '') AND ";

	private static final String _FINDER_COLUMN_UUID_C_COMPANYID_2 =
		"commerceInventoryWarehouseOrderTypeRel.companyId = ?";

	private FinderPath
		_finderPathWithPaginationFindByCommerceInventoryWarehouseId;
	private FinderPath
		_finderPathWithoutPaginationFindByCommerceInventoryWarehouseId;
	private FinderPath _finderPathCountByCommerceInventoryWarehouseId;

	/**
	 * Returns all the commerce inventory warehouse order type rels where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @return the matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceInventoryWarehouseId(long commerceInventoryWarehouseId) {

		return findByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse order type rels where commerceInventoryWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @return the range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end) {

		return findByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse order type rels where commerceInventoryWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator) {

		return findByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse order type rels where commerceInventoryWarehouseId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator,
			boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByCommerceInventoryWarehouseId;
				finderArgs = new Object[] {commerceInventoryWarehouseId};
			}
		}
		else if (useFinderCache) {
			finderPath =
				_finderPathWithPaginationFindByCommerceInventoryWarehouseId;
			finderArgs = new Object[] {
				commerceInventoryWarehouseId, start, end, orderByComparator
			};
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list = null;

		if (useFinderCache) {
			list =
				(List<CommerceInventoryWarehouseOrderTypeRel>)
					finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryWarehouseOrderTypeRel
						commerceInventoryWarehouseOrderTypeRel : list) {

					if (commerceInventoryWarehouseId !=
							commerceInventoryWarehouseOrderTypeRel.
								getCommerceInventoryWarehouseId()) {

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

			sb.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

			sb.append(
				_FINDER_COLUMN_COMMERCEINVENTORYWAREHOUSEID_COMMERCEINVENTORYWAREHOUSEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(
					CommerceInventoryWarehouseOrderTypeRelModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(commerceInventoryWarehouseId);

				list =
					(List<CommerceInventoryWarehouseOrderTypeRel>)
						QueryUtil.list(query, getDialect(), start, end);

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
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel
			findByCommerceInventoryWarehouseId_First(
				long commerceInventoryWarehouseId,
				OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
					orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel =
				fetchByCommerceInventoryWarehouseId_First(
					commerceInventoryWarehouseId, orderByComparator);

		if (commerceInventoryWarehouseOrderTypeRel != null) {
			return commerceInventoryWarehouseOrderTypeRel;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("commerceInventoryWarehouseId=");
		sb.append(commerceInventoryWarehouseId);

		sb.append("}");

		throw new NoSuchInventoryWarehouseOrderTypeRelException(sb.toString());
	}

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel
		fetchByCommerceInventoryWarehouseId_First(
			long commerceInventoryWarehouseId,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator) {

		List<CommerceInventoryWarehouseOrderTypeRel> list =
			findByCommerceInventoryWarehouseId(
				commerceInventoryWarehouseId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel
			findByCommerceInventoryWarehouseId_Last(
				long commerceInventoryWarehouseId,
				OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
					orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel =
				fetchByCommerceInventoryWarehouseId_Last(
					commerceInventoryWarehouseId, orderByComparator);

		if (commerceInventoryWarehouseOrderTypeRel != null) {
			return commerceInventoryWarehouseOrderTypeRel;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("commerceInventoryWarehouseId=");
		sb.append(commerceInventoryWarehouseId);

		sb.append("}");

		throw new NoSuchInventoryWarehouseOrderTypeRelException(sb.toString());
	}

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel
		fetchByCommerceInventoryWarehouseId_Last(
			long commerceInventoryWarehouseId,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator) {

		int count = countByCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list =
			findByCommerceInventoryWarehouseId(
				commerceInventoryWarehouseId, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory warehouse order type rels before and after the current commerce inventory warehouse order type rel in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the current commerce inventory warehouse order type rel
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel[]
			findByCommerceInventoryWarehouseId_PrevAndNext(
				long commerceInventoryWarehouseOrderTypeRelId,
				long commerceInventoryWarehouseId,
				OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
					orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = findByPrimaryKey(
				commerceInventoryWarehouseOrderTypeRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouseOrderTypeRel[] array =
				new CommerceInventoryWarehouseOrderTypeRelImpl[3];

			array[0] = getByCommerceInventoryWarehouseId_PrevAndNext(
				session, commerceInventoryWarehouseOrderTypeRel,
				commerceInventoryWarehouseId, orderByComparator, true);

			array[1] = commerceInventoryWarehouseOrderTypeRel;

			array[2] = getByCommerceInventoryWarehouseId_PrevAndNext(
				session, commerceInventoryWarehouseOrderTypeRel,
				commerceInventoryWarehouseId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceInventoryWarehouseOrderTypeRel
		getByCommerceInventoryWarehouseId_PrevAndNext(
			Session session,
			CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel,
			long commerceInventoryWarehouseId,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator,
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

		sb.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

		sb.append(
			_FINDER_COLUMN_COMMERCEINVENTORYWAREHOUSEID_COMMERCEINVENTORYWAREHOUSEID_2);

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
			sb.append(
				CommerceInventoryWarehouseOrderTypeRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(commerceInventoryWarehouseId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceInventoryWarehouseOrderTypeRel)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory warehouse order type rels where commerceInventoryWarehouseId = &#63; from the database.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 */
	@Override
	public void removeByCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		for (CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel :
					findByCommerceInventoryWarehouseId(
						commerceInventoryWarehouseId, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null)) {

			remove(commerceInventoryWarehouseOrderTypeRel);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouse order type rels where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @return the number of matching commerce inventory warehouse order type rels
	 */
	@Override
	public int countByCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		FinderPath finderPath = _finderPathCountByCommerceInventoryWarehouseId;

		Object[] finderArgs = new Object[] {commerceInventoryWarehouseId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

			sb.append(
				_FINDER_COLUMN_COMMERCEINVENTORYWAREHOUSEID_COMMERCEINVENTORYWAREHOUSEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(commerceInventoryWarehouseId);

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
		_FINDER_COLUMN_COMMERCEINVENTORYWAREHOUSEID_COMMERCEINVENTORYWAREHOUSEID_2 =
			"commerceInventoryWarehouseOrderTypeRel.commerceInventoryWarehouseId = ?";

	private FinderPath _finderPathWithPaginationFindByCommerceOrderTypeId;
	private FinderPath _finderPathWithoutPaginationFindByCommerceOrderTypeId;
	private FinderPath _finderPathCountByCommerceOrderTypeId;

	/**
	 * Returns all the commerce inventory warehouse order type rels where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceOrderTypeId(long commerceOrderTypeId) {

		return findByCommerceOrderTypeId(
			commerceOrderTypeId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse order type rels where commerceOrderTypeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @return the range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceOrderTypeId(
			long commerceOrderTypeId, int start, int end) {

		return findByCommerceOrderTypeId(commerceOrderTypeId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse order type rels where commerceOrderTypeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceOrderTypeId(
			long commerceOrderTypeId, int start, int end,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator) {

		return findByCommerceOrderTypeId(
			commerceOrderTypeId, start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse order type rels where commerceOrderTypeId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceOrderTypeId(
			long commerceOrderTypeId, int start, int end,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator,
			boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath =
					_finderPathWithoutPaginationFindByCommerceOrderTypeId;
				finderArgs = new Object[] {commerceOrderTypeId};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCommerceOrderTypeId;
			finderArgs = new Object[] {
				commerceOrderTypeId, start, end, orderByComparator
			};
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list = null;

		if (useFinderCache) {
			list =
				(List<CommerceInventoryWarehouseOrderTypeRel>)
					finderCache.getResult(finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceInventoryWarehouseOrderTypeRel
						commerceInventoryWarehouseOrderTypeRel : list) {

					if (commerceOrderTypeId !=
							commerceInventoryWarehouseOrderTypeRel.
								getCommerceOrderTypeId()) {

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

			sb.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

			sb.append(_FINDER_COLUMN_COMMERCEORDERTYPEID_COMMERCEORDERTYPEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(
					CommerceInventoryWarehouseOrderTypeRelModelImpl.
						ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(commerceOrderTypeId);

				list =
					(List<CommerceInventoryWarehouseOrderTypeRel>)
						QueryUtil.list(query, getDialect(), start, end);

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
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel
			findByCommerceOrderTypeId_First(
				long commerceOrderTypeId,
				OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
					orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel =
				fetchByCommerceOrderTypeId_First(
					commerceOrderTypeId, orderByComparator);

		if (commerceInventoryWarehouseOrderTypeRel != null) {
			return commerceInventoryWarehouseOrderTypeRel;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("commerceOrderTypeId=");
		sb.append(commerceOrderTypeId);

		sb.append("}");

		throw new NoSuchInventoryWarehouseOrderTypeRelException(sb.toString());
	}

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel
		fetchByCommerceOrderTypeId_First(
			long commerceOrderTypeId,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator) {

		List<CommerceInventoryWarehouseOrderTypeRel> list =
			findByCommerceOrderTypeId(
				commerceOrderTypeId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel
			findByCommerceOrderTypeId_Last(
				long commerceOrderTypeId,
				OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
					orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel =
				fetchByCommerceOrderTypeId_Last(
					commerceOrderTypeId, orderByComparator);

		if (commerceInventoryWarehouseOrderTypeRel != null) {
			return commerceInventoryWarehouseOrderTypeRel;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("commerceOrderTypeId=");
		sb.append(commerceOrderTypeId);

		sb.append("}");

		throw new NoSuchInventoryWarehouseOrderTypeRelException(sb.toString());
	}

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel
		fetchByCommerceOrderTypeId_Last(
			long commerceOrderTypeId,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator) {

		int count = countByCommerceOrderTypeId(commerceOrderTypeId);

		if (count == 0) {
			return null;
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list =
			findByCommerceOrderTypeId(
				commerceOrderTypeId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce inventory warehouse order type rels before and after the current commerce inventory warehouse order type rel in the ordered set where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the current commerce inventory warehouse order type rel
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel[]
			findByCommerceOrderTypeId_PrevAndNext(
				long commerceInventoryWarehouseOrderTypeRelId,
				long commerceOrderTypeId,
				OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
					orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = findByPrimaryKey(
				commerceInventoryWarehouseOrderTypeRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouseOrderTypeRel[] array =
				new CommerceInventoryWarehouseOrderTypeRelImpl[3];

			array[0] = getByCommerceOrderTypeId_PrevAndNext(
				session, commerceInventoryWarehouseOrderTypeRel,
				commerceOrderTypeId, orderByComparator, true);

			array[1] = commerceInventoryWarehouseOrderTypeRel;

			array[2] = getByCommerceOrderTypeId_PrevAndNext(
				session, commerceInventoryWarehouseOrderTypeRel,
				commerceOrderTypeId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceInventoryWarehouseOrderTypeRel
		getByCommerceOrderTypeId_PrevAndNext(
			Session session,
			CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel,
			long commerceOrderTypeId,
			OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
				orderByComparator,
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

		sb.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

		sb.append(_FINDER_COLUMN_COMMERCEORDERTYPEID_COMMERCEORDERTYPEID_2);

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
			sb.append(
				CommerceInventoryWarehouseOrderTypeRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(commerceOrderTypeId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceInventoryWarehouseOrderTypeRel)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceInventoryWarehouseOrderTypeRel> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce inventory warehouse order type rels where commerceOrderTypeId = &#63; from the database.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 */
	@Override
	public void removeByCommerceOrderTypeId(long commerceOrderTypeId) {
		for (CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel :
					findByCommerceOrderTypeId(
						commerceOrderTypeId, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null)) {

			remove(commerceInventoryWarehouseOrderTypeRel);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouse order type rels where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the number of matching commerce inventory warehouse order type rels
	 */
	@Override
	public int countByCommerceOrderTypeId(long commerceOrderTypeId) {
		FinderPath finderPath = _finderPathCountByCommerceOrderTypeId;

		Object[] finderArgs = new Object[] {commerceOrderTypeId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

			sb.append(_FINDER_COLUMN_COMMERCEORDERTYPEID_COMMERCEORDERTYPEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(commerceOrderTypeId);

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
		_FINDER_COLUMN_COMMERCEORDERTYPEID_COMMERCEORDERTYPEID_2 =
			"commerceInventoryWarehouseOrderTypeRel.commerceOrderTypeId = ?";

	private FinderPath _finderPathFetchByCIW_COTI;
	private FinderPath _finderPathCountByCIW_COTI;

	/**
	 * Returns the commerce inventory warehouse order type rel where commerceInventoryWarehouseId = &#63; and commerceOrderTypeId = &#63; or throws a <code>NoSuchInventoryWarehouseOrderTypeRelException</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel findByCIW_COTI(
			long commerceInventoryWarehouseId, long commerceOrderTypeId)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = fetchByCIW_COTI(
				commerceInventoryWarehouseId, commerceOrderTypeId);

		if (commerceInventoryWarehouseOrderTypeRel == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("commerceInventoryWarehouseId=");
			sb.append(commerceInventoryWarehouseId);

			sb.append(", commerceOrderTypeId=");
			sb.append(commerceOrderTypeId);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchInventoryWarehouseOrderTypeRelException(
				sb.toString());
		}

		return commerceInventoryWarehouseOrderTypeRel;
	}

	/**
	 * Returns the commerce inventory warehouse order type rel where commerceInventoryWarehouseId = &#63; and commerceOrderTypeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel fetchByCIW_COTI(
		long commerceInventoryWarehouseId, long commerceOrderTypeId) {

		return fetchByCIW_COTI(
			commerceInventoryWarehouseId, commerceOrderTypeId, true);
	}

	/**
	 * Returns the commerce inventory warehouse order type rel where commerceInventoryWarehouseId = &#63; and commerceOrderTypeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel fetchByCIW_COTI(
		long commerceInventoryWarehouseId, long commerceOrderTypeId,
		boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {
				commerceInventoryWarehouseId, commerceOrderTypeId
			};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByCIW_COTI, finderArgs);
		}

		if (result instanceof CommerceInventoryWarehouseOrderTypeRel) {
			CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel =
					(CommerceInventoryWarehouseOrderTypeRel)result;

			if ((commerceInventoryWarehouseId !=
					commerceInventoryWarehouseOrderTypeRel.
						getCommerceInventoryWarehouseId()) ||
				(commerceOrderTypeId !=
					commerceInventoryWarehouseOrderTypeRel.
						getCommerceOrderTypeId())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

			sb.append(_FINDER_COLUMN_CIW_COTI_COMMERCEINVENTORYWAREHOUSEID_2);

			sb.append(_FINDER_COLUMN_CIW_COTI_COMMERCEORDERTYPEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(commerceInventoryWarehouseId);

				queryPos.add(commerceOrderTypeId);

				List<CommerceInventoryWarehouseOrderTypeRel> list =
					query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByCIW_COTI, finderArgs, list);
					}
				}
				else {
					CommerceInventoryWarehouseOrderTypeRel
						commerceInventoryWarehouseOrderTypeRel = list.get(0);

					result = commerceInventoryWarehouseOrderTypeRel;

					cacheResult(commerceInventoryWarehouseOrderTypeRel);
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
			return (CommerceInventoryWarehouseOrderTypeRel)result;
		}
	}

	/**
	 * Removes the commerce inventory warehouse order type rel where commerceInventoryWarehouseId = &#63; and commerceOrderTypeId = &#63; from the database.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the commerce inventory warehouse order type rel that was removed
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel removeByCIW_COTI(
			long commerceInventoryWarehouseId, long commerceOrderTypeId)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = findByCIW_COTI(
				commerceInventoryWarehouseId, commerceOrderTypeId);

		return remove(commerceInventoryWarehouseOrderTypeRel);
	}

	/**
	 * Returns the number of commerce inventory warehouse order type rels where commerceInventoryWarehouseId = &#63; and commerceOrderTypeId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the number of matching commerce inventory warehouse order type rels
	 */
	@Override
	public int countByCIW_COTI(
		long commerceInventoryWarehouseId, long commerceOrderTypeId) {

		FinderPath finderPath = _finderPathCountByCIW_COTI;

		Object[] finderArgs = new Object[] {
			commerceInventoryWarehouseId, commerceOrderTypeId
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE);

			sb.append(_FINDER_COLUMN_CIW_COTI_COMMERCEINVENTORYWAREHOUSEID_2);

			sb.append(_FINDER_COLUMN_CIW_COTI_COMMERCEORDERTYPEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(commerceInventoryWarehouseId);

				queryPos.add(commerceOrderTypeId);

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
		_FINDER_COLUMN_CIW_COTI_COMMERCEINVENTORYWAREHOUSEID_2 =
			"commerceInventoryWarehouseOrderTypeRel.commerceInventoryWarehouseId = ? AND ";

	private static final String _FINDER_COLUMN_CIW_COTI_COMMERCEORDERTYPEID_2 =
		"commerceInventoryWarehouseOrderTypeRel.commerceOrderTypeId = ?";

	public CommerceInventoryWarehouseOrderTypeRelPersistenceImpl() {
		Map<String, String> dbColumnNames = new HashMap<String, String>();

		dbColumnNames.put("uuid", "uuid_");
		dbColumnNames.put(
			"commerceInventoryWarehouseOrderTypeRelId",
			"CIWarehouseOrderTypeRelId");

		setDBColumnNames(dbColumnNames);

		setModelClass(CommerceInventoryWarehouseOrderTypeRel.class);

		setModelImplClass(CommerceInventoryWarehouseOrderTypeRelImpl.class);
		setModelPKClass(long.class);

		setTable(CommerceInventoryWarehouseOrderTypeRelTable.INSTANCE);
	}

	/**
	 * Caches the commerce inventory warehouse order type rel in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRel the commerce inventory warehouse order type rel
	 */
	@Override
	public void cacheResult(
		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel) {

		entityCache.putResult(
			CommerceInventoryWarehouseOrderTypeRelImpl.class,
			commerceInventoryWarehouseOrderTypeRel.getPrimaryKey(),
			commerceInventoryWarehouseOrderTypeRel);

		finderCache.putResult(
			_finderPathFetchByCIW_COTI,
			new Object[] {
				commerceInventoryWarehouseOrderTypeRel.
					getCommerceInventoryWarehouseId(),
				commerceInventoryWarehouseOrderTypeRel.getCommerceOrderTypeId()
			},
			commerceInventoryWarehouseOrderTypeRel);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the commerce inventory warehouse order type rels in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRels the commerce inventory warehouse order type rels
	 */
	@Override
	public void cacheResult(
		List<CommerceInventoryWarehouseOrderTypeRel>
			commerceInventoryWarehouseOrderTypeRels) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (commerceInventoryWarehouseOrderTypeRels.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel :
					commerceInventoryWarehouseOrderTypeRels) {

			if (entityCache.getResult(
					CommerceInventoryWarehouseOrderTypeRelImpl.class,
					commerceInventoryWarehouseOrderTypeRel.getPrimaryKey()) ==
						null) {

				cacheResult(commerceInventoryWarehouseOrderTypeRel);
			}
		}
	}

	/**
	 * Clears the cache for all commerce inventory warehouse order type rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(
			CommerceInventoryWarehouseOrderTypeRelImpl.class);

		finderCache.clearCache(
			CommerceInventoryWarehouseOrderTypeRelImpl.class);
	}

	/**
	 * Clears the cache for the commerce inventory warehouse order type rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(
		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel) {

		entityCache.removeResult(
			CommerceInventoryWarehouseOrderTypeRelImpl.class,
			commerceInventoryWarehouseOrderTypeRel);
	}

	@Override
	public void clearCache(
		List<CommerceInventoryWarehouseOrderTypeRel>
			commerceInventoryWarehouseOrderTypeRels) {

		for (CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel :
					commerceInventoryWarehouseOrderTypeRels) {

			entityCache.removeResult(
				CommerceInventoryWarehouseOrderTypeRelImpl.class,
				commerceInventoryWarehouseOrderTypeRel);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(
			CommerceInventoryWarehouseOrderTypeRelImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CommerceInventoryWarehouseOrderTypeRelImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceInventoryWarehouseOrderTypeRelModelImpl
			commerceInventoryWarehouseOrderTypeRelModelImpl) {

		Object[] args = new Object[] {
			commerceInventoryWarehouseOrderTypeRelModelImpl.
				getCommerceInventoryWarehouseId(),
			commerceInventoryWarehouseOrderTypeRelModelImpl.
				getCommerceOrderTypeId()
		};

		finderCache.putResult(
			_finderPathCountByCIW_COTI, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByCIW_COTI, args,
			commerceInventoryWarehouseOrderTypeRelModelImpl);
	}

	/**
	 * Creates a new commerce inventory warehouse order type rel with the primary key. Does not add the commerce inventory warehouse order type rel to the database.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key for the new commerce inventory warehouse order type rel
	 * @return the new commerce inventory warehouse order type rel
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel create(
		long commerceInventoryWarehouseOrderTypeRelId) {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel =
				new CommerceInventoryWarehouseOrderTypeRelImpl();

		commerceInventoryWarehouseOrderTypeRel.setNew(true);
		commerceInventoryWarehouseOrderTypeRel.setPrimaryKey(
			commerceInventoryWarehouseOrderTypeRelId);

		String uuid = PortalUUIDUtil.generate();

		commerceInventoryWarehouseOrderTypeRel.setUuid(uuid);

		commerceInventoryWarehouseOrderTypeRel.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commerceInventoryWarehouseOrderTypeRel;
	}

	/**
	 * Removes the commerce inventory warehouse order type rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel that was removed
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel remove(
			long commerceInventoryWarehouseOrderTypeRelId)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		return remove((Serializable)commerceInventoryWarehouseOrderTypeRelId);
	}

	/**
	 * Removes the commerce inventory warehouse order type rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel that was removed
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel remove(
			Serializable primaryKey)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		Session session = null;

		try {
			session = openSession();

			CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel =
					(CommerceInventoryWarehouseOrderTypeRel)session.get(
						CommerceInventoryWarehouseOrderTypeRelImpl.class,
						primaryKey);

			if (commerceInventoryWarehouseOrderTypeRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInventoryWarehouseOrderTypeRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceInventoryWarehouseOrderTypeRel);
		}
		catch (NoSuchInventoryWarehouseOrderTypeRelException
					noSuchEntityException) {

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
	protected CommerceInventoryWarehouseOrderTypeRel removeImpl(
		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceInventoryWarehouseOrderTypeRel)) {
				commerceInventoryWarehouseOrderTypeRel =
					(CommerceInventoryWarehouseOrderTypeRel)session.get(
						CommerceInventoryWarehouseOrderTypeRelImpl.class,
						commerceInventoryWarehouseOrderTypeRel.
							getPrimaryKeyObj());
			}

			if (commerceInventoryWarehouseOrderTypeRel != null) {
				session.delete(commerceInventoryWarehouseOrderTypeRel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (commerceInventoryWarehouseOrderTypeRel != null) {
			clearCache(commerceInventoryWarehouseOrderTypeRel);
		}

		return commerceInventoryWarehouseOrderTypeRel;
	}

	@Override
	public CommerceInventoryWarehouseOrderTypeRel updateImpl(
		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel) {

		boolean isNew = commerceInventoryWarehouseOrderTypeRel.isNew();

		if (!(commerceInventoryWarehouseOrderTypeRel instanceof
				CommerceInventoryWarehouseOrderTypeRelModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(
					commerceInventoryWarehouseOrderTypeRel.getClass())) {

				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceInventoryWarehouseOrderTypeRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceInventoryWarehouseOrderTypeRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceInventoryWarehouseOrderTypeRel implementation " +
					commerceInventoryWarehouseOrderTypeRel.getClass());
		}

		CommerceInventoryWarehouseOrderTypeRelModelImpl
			commerceInventoryWarehouseOrderTypeRelModelImpl =
				(CommerceInventoryWarehouseOrderTypeRelModelImpl)
					commerceInventoryWarehouseOrderTypeRel;

		if (Validator.isNull(
				commerceInventoryWarehouseOrderTypeRel.getUuid())) {

			String uuid = PortalUUIDUtil.generate();

			commerceInventoryWarehouseOrderTypeRel.setUuid(uuid);
		}

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew &&
			(commerceInventoryWarehouseOrderTypeRel.getCreateDate() == null)) {

			if (serviceContext == null) {
				commerceInventoryWarehouseOrderTypeRel.setCreateDate(date);
			}
			else {
				commerceInventoryWarehouseOrderTypeRel.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!commerceInventoryWarehouseOrderTypeRelModelImpl.
				hasSetModifiedDate()) {

			if (serviceContext == null) {
				commerceInventoryWarehouseOrderTypeRel.setModifiedDate(date);
			}
			else {
				commerceInventoryWarehouseOrderTypeRel.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(commerceInventoryWarehouseOrderTypeRel);
			}
			else {
				commerceInventoryWarehouseOrderTypeRel =
					(CommerceInventoryWarehouseOrderTypeRel)session.merge(
						commerceInventoryWarehouseOrderTypeRel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CommerceInventoryWarehouseOrderTypeRelImpl.class,
			commerceInventoryWarehouseOrderTypeRelModelImpl, false, true);

		cacheUniqueFindersCache(
			commerceInventoryWarehouseOrderTypeRelModelImpl);

		if (isNew) {
			commerceInventoryWarehouseOrderTypeRel.setNew(false);
		}

		commerceInventoryWarehouseOrderTypeRel.resetOriginalValues();

		return commerceInventoryWarehouseOrderTypeRel;
	}

	/**
	 * Returns the commerce inventory warehouse order type rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel findByPrimaryKey(
			Serializable primaryKey)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = fetchByPrimaryKey(
				primaryKey);

		if (commerceInventoryWarehouseOrderTypeRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchInventoryWarehouseOrderTypeRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceInventoryWarehouseOrderTypeRel;
	}

	/**
	 * Returns the commerce inventory warehouse order type rel with the primary key or throws a <code>NoSuchInventoryWarehouseOrderTypeRelException</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel findByPrimaryKey(
			long commerceInventoryWarehouseOrderTypeRelId)
		throws NoSuchInventoryWarehouseOrderTypeRelException {

		return findByPrimaryKey(
			(Serializable)commerceInventoryWarehouseOrderTypeRelId);
	}

	/**
	 * Returns the commerce inventory warehouse order type rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel, or <code>null</code> if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	@Override
	public CommerceInventoryWarehouseOrderTypeRel fetchByPrimaryKey(
		long commerceInventoryWarehouseOrderTypeRelId) {

		return fetchByPrimaryKey(
			(Serializable)commerceInventoryWarehouseOrderTypeRelId);
	}

	/**
	 * Returns all the commerce inventory warehouse order type rels.
	 *
	 * @return the commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce inventory warehouse order type rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @return the range of commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findAll(
		int start, int end) {

		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse order type rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findAll(
		int start, int end,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce inventory warehouse order type rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceInventoryWarehouseOrderTypeRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce inventory warehouse order type rels
	 * @param end the upper bound of the range of commerce inventory warehouse order type rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce inventory warehouse order type rels
	 */
	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel> findAll(
		int start, int end,
		OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
			orderByComparator,
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

		List<CommerceInventoryWarehouseOrderTypeRel> list = null;

		if (useFinderCache) {
			list =
				(List<CommerceInventoryWarehouseOrderTypeRel>)
					finderCache.getResult(finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL;

				sql = sql.concat(
					CommerceInventoryWarehouseOrderTypeRelModelImpl.
						ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list =
					(List<CommerceInventoryWarehouseOrderTypeRel>)
						QueryUtil.list(query, getDialect(), start, end);

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
	 * Removes all the commerce inventory warehouse order type rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceInventoryWarehouseOrderTypeRel
				commerceInventoryWarehouseOrderTypeRel : findAll()) {

			remove(commerceInventoryWarehouseOrderTypeRel);
		}
	}

	/**
	 * Returns the number of commerce inventory warehouse order type rels.
	 *
	 * @return the number of commerce inventory warehouse order type rels
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(
					_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL);

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
		return "CIWarehouseOrderTypeRelId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceInventoryWarehouseOrderTypeRelModelImpl.
			TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce inventory warehouse order type rel persistence.
	 */
	public void afterPropertiesSet() {
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

		_finderPathWithPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"uuid_"}, true);

		_finderPathWithoutPaginationFindByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			true);

		_finderPathCountByUuid = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid",
			new String[] {String.class.getName()}, new String[] {"uuid_"},
			false);

		_finderPathWithPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByUuid_C",
			new String[] {
				String.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathWithoutPaginationFindByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, true);

		_finderPathCountByUuid_C = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByUuid_C",
			new String[] {String.class.getName(), Long.class.getName()},
			new String[] {"uuid_", "companyId"}, false);

		_finderPathWithPaginationFindByCommerceInventoryWarehouseId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
				"findByCommerceInventoryWarehouseId",
				new String[] {
					Long.class.getName(), Integer.class.getName(),
					Integer.class.getName(), OrderByComparator.class.getName()
				},
				new String[] {"commerceInventoryWarehouseId"}, true);

		_finderPathWithoutPaginationFindByCommerceInventoryWarehouseId =
			new FinderPath(
				FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
				"findByCommerceInventoryWarehouseId",
				new String[] {Long.class.getName()},
				new String[] {"commerceInventoryWarehouseId"}, true);

		_finderPathCountByCommerceInventoryWarehouseId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceInventoryWarehouseId",
			new String[] {Long.class.getName()},
			new String[] {"commerceInventoryWarehouseId"}, false);

		_finderPathWithPaginationFindByCommerceOrderTypeId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCommerceOrderTypeId",
			new String[] {
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"commerceOrderTypeId"}, true);

		_finderPathWithoutPaginationFindByCommerceOrderTypeId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findByCommerceOrderTypeId", new String[] {Long.class.getName()},
			new String[] {"commerceOrderTypeId"}, true);

		_finderPathCountByCommerceOrderTypeId = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"countByCommerceOrderTypeId", new String[] {Long.class.getName()},
			new String[] {"commerceOrderTypeId"}, false);

		_finderPathFetchByCIW_COTI = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByCIW_COTI",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {
				"commerceInventoryWarehouseId", "commerceOrderTypeId"
			},
			true);

		_finderPathCountByCIW_COTI = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCIW_COTI",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {
				"commerceInventoryWarehouseId", "commerceOrderTypeId"
			},
			false);

		_setCommerceInventoryWarehouseOrderTypeRelUtilPersistence(this);
	}

	public void destroy() {
		_setCommerceInventoryWarehouseOrderTypeRelUtilPersistence(null);

		entityCache.removeCache(
			CommerceInventoryWarehouseOrderTypeRelImpl.class.getName());
	}

	private void _setCommerceInventoryWarehouseOrderTypeRelUtilPersistence(
		CommerceInventoryWarehouseOrderTypeRelPersistence
			commerceInventoryWarehouseOrderTypeRelPersistence) {

		try {
			Field field =
				CommerceInventoryWarehouseOrderTypeRelUtil.class.
					getDeclaredField("_persistence");

			field.setAccessible(true);

			field.set(null, commerceInventoryWarehouseOrderTypeRelPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;

	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;

	private static final String
		_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL =
			"SELECT commerceInventoryWarehouseOrderTypeRel FROM CommerceInventoryWarehouseOrderTypeRel commerceInventoryWarehouseOrderTypeRel";

	private static final String
		_SQL_SELECT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE =
			"SELECT commerceInventoryWarehouseOrderTypeRel FROM CommerceInventoryWarehouseOrderTypeRel commerceInventoryWarehouseOrderTypeRel WHERE ";

	private static final String
		_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL =
			"SELECT COUNT(commerceInventoryWarehouseOrderTypeRel) FROM CommerceInventoryWarehouseOrderTypeRel commerceInventoryWarehouseOrderTypeRel";

	private static final String
		_SQL_COUNT_COMMERCEINVENTORYWAREHOUSEORDERTYPEREL_WHERE =
			"SELECT COUNT(commerceInventoryWarehouseOrderTypeRel) FROM CommerceInventoryWarehouseOrderTypeRel commerceInventoryWarehouseOrderTypeRel WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceInventoryWarehouseOrderTypeRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceInventoryWarehouseOrderTypeRel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceInventoryWarehouseOrderTypeRel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceInventoryWarehouseOrderTypeRelPersistenceImpl.class);

	private static final Set<String> _badColumnNames = SetUtil.fromArray(
		new String[] {"uuid", "commerceInventoryWarehouseOrderTypeRelId"});

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}