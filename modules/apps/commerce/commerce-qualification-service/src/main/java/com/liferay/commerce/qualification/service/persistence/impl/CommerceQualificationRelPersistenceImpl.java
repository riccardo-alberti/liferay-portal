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

package com.liferay.commerce.qualification.service.persistence.impl;

import com.liferay.commerce.qualification.exception.NoSuchQualificationRelException;
import com.liferay.commerce.qualification.model.CommerceQualificationRel;
import com.liferay.commerce.qualification.model.CommerceQualificationRelTable;
import com.liferay.commerce.qualification.model.impl.CommerceQualificationRelImpl;
import com.liferay.commerce.qualification.model.impl.CommerceQualificationRelModelImpl;
import com.liferay.commerce.qualification.service.persistence.CommerceQualificationRelPersistence;
import com.liferay.commerce.qualification.service.persistence.CommerceQualificationRelUtil;
import com.liferay.commerce.qualification.service.persistence.impl.constants.CommercePersistenceConstants;
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

import java.io.Serializable;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the commerce qualification rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @generated
 */
@Component(
	service = {CommerceQualificationRelPersistence.class, BasePersistence.class}
)
public class CommerceQualificationRelPersistenceImpl
	extends BasePersistenceImpl<CommerceQualificationRel>
	implements CommerceQualificationRelPersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CommerceQualificationRelUtil</code> to access the commerce qualification rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CommerceQualificationRelImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathWithPaginationFindByS_S;
	private FinderPath _finderPathWithoutPaginationFindByS_S;
	private FinderPath _finderPathCountByS_S;

	/**
	 * Returns all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @return the matching commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK) {

		return findByS_S(
			sourceClassNameId, sourceClassPK, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param start the lower bound of the range of commerce qualification rels
	 * @param end the upper bound of the range of commerce qualification rels (not inclusive)
	 * @return the range of matching commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK, int start, int end) {

		return findByS_S(sourceClassNameId, sourceClassPK, start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param start the lower bound of the range of commerce qualification rels
	 * @param end the upper bound of the range of commerce qualification rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK, int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return findByS_S(
			sourceClassNameId, sourceClassPK, start, end, orderByComparator,
			true);
	}

	/**
	 * Returns an ordered range of all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param start the lower bound of the range of commerce qualification rels
	 * @param end the upper bound of the range of commerce qualification rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK, int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByS_S;
				finderArgs = new Object[] {sourceClassNameId, sourceClassPK};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByS_S;
			finderArgs = new Object[] {
				sourceClassNameId, sourceClassPK, start, end, orderByComparator
			};
		}

		List<CommerceQualificationRel> list = null;

		if (useFinderCache) {
			list = (List<CommerceQualificationRel>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceQualificationRel commerceQualificationRel : list) {
					if ((sourceClassNameId !=
							commerceQualificationRel.getSourceClassNameId()) ||
						(sourceClassPK !=
							commerceQualificationRel.getSourceClassPK())) {

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

			sb.append(_SQL_SELECT_COMMERCEQUALIFICATIONREL_WHERE);

			sb.append(_FINDER_COLUMN_S_S_SOURCECLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_S_S_SOURCECLASSPK_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommerceQualificationRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sourceClassNameId);

				queryPos.add(sourceClassPK);

				list = (List<CommerceQualificationRel>)QueryUtil.list(
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
	 * Returns the first commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce qualification rel
	 * @throws NoSuchQualificationRelException if a matching commerce qualification rel could not be found
	 */
	@Override
	public CommerceQualificationRel findByS_S_First(
			long sourceClassNameId, long sourceClassPK,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException {

		CommerceQualificationRel commerceQualificationRel = fetchByS_S_First(
			sourceClassNameId, sourceClassPK, orderByComparator);

		if (commerceQualificationRel != null) {
			return commerceQualificationRel;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sourceClassNameId=");
		sb.append(sourceClassNameId);

		sb.append(", sourceClassPK=");
		sb.append(sourceClassPK);

		sb.append("}");

		throw new NoSuchQualificationRelException(sb.toString());
	}

	/**
	 * Returns the first commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	@Override
	public CommerceQualificationRel fetchByS_S_First(
		long sourceClassNameId, long sourceClassPK,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		List<CommerceQualificationRel> list = findByS_S(
			sourceClassNameId, sourceClassPK, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce qualification rel
	 * @throws NoSuchQualificationRelException if a matching commerce qualification rel could not be found
	 */
	@Override
	public CommerceQualificationRel findByS_S_Last(
			long sourceClassNameId, long sourceClassPK,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException {

		CommerceQualificationRel commerceQualificationRel = fetchByS_S_Last(
			sourceClassNameId, sourceClassPK, orderByComparator);

		if (commerceQualificationRel != null) {
			return commerceQualificationRel;
		}

		StringBundler sb = new StringBundler(6);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sourceClassNameId=");
		sb.append(sourceClassNameId);

		sb.append(", sourceClassPK=");
		sb.append(sourceClassPK);

		sb.append("}");

		throw new NoSuchQualificationRelException(sb.toString());
	}

	/**
	 * Returns the last commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	@Override
	public CommerceQualificationRel fetchByS_S_Last(
		long sourceClassNameId, long sourceClassPK,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		int count = countByS_S(sourceClassNameId, sourceClassPK);

		if (count == 0) {
			return null;
		}

		List<CommerceQualificationRel> list = findByS_S(
			sourceClassNameId, sourceClassPK, count - 1, count,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce qualification rels before and after the current commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param commerceQualificationRelId the primary key of the current commerce qualification rel
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce qualification rel
	 * @throws NoSuchQualificationRelException if a commerce qualification rel with the primary key could not be found
	 */
	@Override
	public CommerceQualificationRel[] findByS_S_PrevAndNext(
			long commerceQualificationRelId, long sourceClassNameId,
			long sourceClassPK,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException {

		CommerceQualificationRel commerceQualificationRel = findByPrimaryKey(
			commerceQualificationRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceQualificationRel[] array =
				new CommerceQualificationRelImpl[3];

			array[0] = getByS_S_PrevAndNext(
				session, commerceQualificationRel, sourceClassNameId,
				sourceClassPK, orderByComparator, true);

			array[1] = commerceQualificationRel;

			array[2] = getByS_S_PrevAndNext(
				session, commerceQualificationRel, sourceClassNameId,
				sourceClassPK, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceQualificationRel getByS_S_PrevAndNext(
		Session session, CommerceQualificationRel commerceQualificationRel,
		long sourceClassNameId, long sourceClassPK,
		OrderByComparator<CommerceQualificationRel> orderByComparator,
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

		sb.append(_SQL_SELECT_COMMERCEQUALIFICATIONREL_WHERE);

		sb.append(_FINDER_COLUMN_S_S_SOURCECLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_S_S_SOURCECLASSPK_2);

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
			sb.append(CommerceQualificationRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(sourceClassNameId);

		queryPos.add(sourceClassPK);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceQualificationRel)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceQualificationRel> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; from the database.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 */
	@Override
	public void removeByS_S(long sourceClassNameId, long sourceClassPK) {
		for (CommerceQualificationRel commerceQualificationRel :
				findByS_S(
					sourceClassNameId, sourceClassPK, QueryUtil.ALL_POS,
					QueryUtil.ALL_POS, null)) {

			remove(commerceQualificationRel);
		}
	}

	/**
	 * Returns the number of commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @return the number of matching commerce qualification rels
	 */
	@Override
	public int countByS_S(long sourceClassNameId, long sourceClassPK) {
		FinderPath finderPath = _finderPathCountByS_S;

		Object[] finderArgs = new Object[] {sourceClassNameId, sourceClassPK};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_COUNT_COMMERCEQUALIFICATIONREL_WHERE);

			sb.append(_FINDER_COLUMN_S_S_SOURCECLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_S_S_SOURCECLASSPK_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sourceClassNameId);

				queryPos.add(sourceClassPK);

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

	private static final String _FINDER_COLUMN_S_S_SOURCECLASSNAMEID_2 =
		"commerceQualificationRel.sourceClassNameId = ? AND ";

	private static final String _FINDER_COLUMN_S_S_SOURCECLASSPK_2 =
		"commerceQualificationRel.sourceClassPK = ?";

	private FinderPath _finderPathWithPaginationFindByS_S_T;
	private FinderPath _finderPathWithoutPaginationFindByS_S_T;
	private FinderPath _finderPathCountByS_S_T;

	/**
	 * Returns all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @return the matching commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId) {

		return findByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param start the lower bound of the range of commerce qualification rels
	 * @param end the upper bound of the range of commerce qualification rels (not inclusive)
	 * @return the range of matching commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		int start, int end) {

		return findByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param start the lower bound of the range of commerce qualification rels
	 * @param end the upper bound of the range of commerce qualification rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return findByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, start, end,
			orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param start the lower bound of the range of commerce qualification rels
	 * @param end the upper bound of the range of commerce qualification rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByS_S_T;
				finderArgs = new Object[] {
					sourceClassNameId, sourceClassPK, targetClassNameId
				};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByS_S_T;
			finderArgs = new Object[] {
				sourceClassNameId, sourceClassPK, targetClassNameId, start, end,
				orderByComparator
			};
		}

		List<CommerceQualificationRel> list = null;

		if (useFinderCache) {
			list = (List<CommerceQualificationRel>)finderCache.getResult(
				finderPath, finderArgs);

			if ((list != null) && !list.isEmpty()) {
				for (CommerceQualificationRel commerceQualificationRel : list) {
					if ((sourceClassNameId !=
							commerceQualificationRel.getSourceClassNameId()) ||
						(sourceClassPK !=
							commerceQualificationRel.getSourceClassPK()) ||
						(targetClassNameId !=
							commerceQualificationRel.getTargetClassNameId())) {

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
					5 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(5);
			}

			sb.append(_SQL_SELECT_COMMERCEQUALIFICATIONREL_WHERE);

			sb.append(_FINDER_COLUMN_S_S_T_SOURCECLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_S_S_T_SOURCECLASSPK_2);

			sb.append(_FINDER_COLUMN_S_S_T_TARGETCLASSNAMEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(CommerceQualificationRelModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sourceClassNameId);

				queryPos.add(sourceClassPK);

				queryPos.add(targetClassNameId);

				list = (List<CommerceQualificationRel>)QueryUtil.list(
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
	 * Returns the first commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce qualification rel
	 * @throws NoSuchQualificationRelException if a matching commerce qualification rel could not be found
	 */
	@Override
	public CommerceQualificationRel findByS_S_T_First(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException {

		CommerceQualificationRel commerceQualificationRel = fetchByS_S_T_First(
			sourceClassNameId, sourceClassPK, targetClassNameId,
			orderByComparator);

		if (commerceQualificationRel != null) {
			return commerceQualificationRel;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sourceClassNameId=");
		sb.append(sourceClassNameId);

		sb.append(", sourceClassPK=");
		sb.append(sourceClassPK);

		sb.append(", targetClassNameId=");
		sb.append(targetClassNameId);

		sb.append("}");

		throw new NoSuchQualificationRelException(sb.toString());
	}

	/**
	 * Returns the first commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	@Override
	public CommerceQualificationRel fetchByS_S_T_First(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		List<CommerceQualificationRel> list = findByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, 0, 1,
			orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce qualification rel
	 * @throws NoSuchQualificationRelException if a matching commerce qualification rel could not be found
	 */
	@Override
	public CommerceQualificationRel findByS_S_T_Last(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException {

		CommerceQualificationRel commerceQualificationRel = fetchByS_S_T_Last(
			sourceClassNameId, sourceClassPK, targetClassNameId,
			orderByComparator);

		if (commerceQualificationRel != null) {
			return commerceQualificationRel;
		}

		StringBundler sb = new StringBundler(8);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("sourceClassNameId=");
		sb.append(sourceClassNameId);

		sb.append(", sourceClassPK=");
		sb.append(sourceClassPK);

		sb.append(", targetClassNameId=");
		sb.append(targetClassNameId);

		sb.append("}");

		throw new NoSuchQualificationRelException(sb.toString());
	}

	/**
	 * Returns the last commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	@Override
	public CommerceQualificationRel fetchByS_S_T_Last(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		int count = countByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId);

		if (count == 0) {
			return null;
		}

		List<CommerceQualificationRel> list = findByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, count - 1,
			count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the commerce qualification rels before and after the current commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param commerceQualificationRelId the primary key of the current commerce qualification rel
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce qualification rel
	 * @throws NoSuchQualificationRelException if a commerce qualification rel with the primary key could not be found
	 */
	@Override
	public CommerceQualificationRel[] findByS_S_T_PrevAndNext(
			long commerceQualificationRelId, long sourceClassNameId,
			long sourceClassPK, long targetClassNameId,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException {

		CommerceQualificationRel commerceQualificationRel = findByPrimaryKey(
			commerceQualificationRelId);

		Session session = null;

		try {
			session = openSession();

			CommerceQualificationRel[] array =
				new CommerceQualificationRelImpl[3];

			array[0] = getByS_S_T_PrevAndNext(
				session, commerceQualificationRel, sourceClassNameId,
				sourceClassPK, targetClassNameId, orderByComparator, true);

			array[1] = commerceQualificationRel;

			array[2] = getByS_S_T_PrevAndNext(
				session, commerceQualificationRel, sourceClassNameId,
				sourceClassPK, targetClassNameId, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected CommerceQualificationRel getByS_S_T_PrevAndNext(
		Session session, CommerceQualificationRel commerceQualificationRel,
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		OrderByComparator<CommerceQualificationRel> orderByComparator,
		boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				6 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(5);
		}

		sb.append(_SQL_SELECT_COMMERCEQUALIFICATIONREL_WHERE);

		sb.append(_FINDER_COLUMN_S_S_T_SOURCECLASSNAMEID_2);

		sb.append(_FINDER_COLUMN_S_S_T_SOURCECLASSPK_2);

		sb.append(_FINDER_COLUMN_S_S_T_TARGETCLASSNAMEID_2);

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
			sb.append(CommerceQualificationRelModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		queryPos.add(sourceClassNameId);

		queryPos.add(sourceClassPK);

		queryPos.add(targetClassNameId);

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(
						commerceQualificationRel)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<CommerceQualificationRel> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63; from the database.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 */
	@Override
	public void removeByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId) {

		for (CommerceQualificationRel commerceQualificationRel :
				findByS_S_T(
					sourceClassNameId, sourceClassPK, targetClassNameId,
					QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(commerceQualificationRel);
		}
	}

	/**
	 * Returns the number of commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @return the number of matching commerce qualification rels
	 */
	@Override
	public int countByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId) {

		FinderPath finderPath = _finderPathCountByS_S_T;

		Object[] finderArgs = new Object[] {
			sourceClassNameId, sourceClassPK, targetClassNameId
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_SQL_COUNT_COMMERCEQUALIFICATIONREL_WHERE);

			sb.append(_FINDER_COLUMN_S_S_T_SOURCECLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_S_S_T_SOURCECLASSPK_2);

			sb.append(_FINDER_COLUMN_S_S_T_TARGETCLASSNAMEID_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sourceClassNameId);

				queryPos.add(sourceClassPK);

				queryPos.add(targetClassNameId);

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

	private static final String _FINDER_COLUMN_S_S_T_SOURCECLASSNAMEID_2 =
		"commerceQualificationRel.sourceClassNameId = ? AND ";

	private static final String _FINDER_COLUMN_S_S_T_SOURCECLASSPK_2 =
		"commerceQualificationRel.sourceClassPK = ? AND ";

	private static final String _FINDER_COLUMN_S_S_T_TARGETCLASSNAMEID_2 =
		"commerceQualificationRel.targetClassNameId = ?";

	private FinderPath _finderPathFetchByS_S_T_T;
	private FinderPath _finderPathCountByS_S_T_T;

	/**
	 * Returns the commerce qualification rel where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63; and targetClassPK = &#63; or throws a <code>NoSuchQualificationRelException</code> if it could not be found.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param targetClassPK the target class pk
	 * @return the matching commerce qualification rel
	 * @throws NoSuchQualificationRelException if a matching commerce qualification rel could not be found
	 */
	@Override
	public CommerceQualificationRel findByS_S_T_T(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			long targetClassPK)
		throws NoSuchQualificationRelException {

		CommerceQualificationRel commerceQualificationRel = fetchByS_S_T_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, targetClassPK);

		if (commerceQualificationRel == null) {
			StringBundler sb = new StringBundler(10);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("sourceClassNameId=");
			sb.append(sourceClassNameId);

			sb.append(", sourceClassPK=");
			sb.append(sourceClassPK);

			sb.append(", targetClassNameId=");
			sb.append(targetClassNameId);

			sb.append(", targetClassPK=");
			sb.append(targetClassPK);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchQualificationRelException(sb.toString());
		}

		return commerceQualificationRel;
	}

	/**
	 * Returns the commerce qualification rel where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63; and targetClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param targetClassPK the target class pk
	 * @return the matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	@Override
	public CommerceQualificationRel fetchByS_S_T_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		long targetClassPK) {

		return fetchByS_S_T_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, targetClassPK,
			true);
	}

	/**
	 * Returns the commerce qualification rel where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63; and targetClassPK = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param targetClassPK the target class pk
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	@Override
	public CommerceQualificationRel fetchByS_S_T_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		long targetClassPK, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {
				sourceClassNameId, sourceClassPK, targetClassNameId,
				targetClassPK
			};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByS_S_T_T, finderArgs);
		}

		if (result instanceof CommerceQualificationRel) {
			CommerceQualificationRel commerceQualificationRel =
				(CommerceQualificationRel)result;

			if ((sourceClassNameId !=
					commerceQualificationRel.getSourceClassNameId()) ||
				(sourceClassPK !=
					commerceQualificationRel.getSourceClassPK()) ||
				(targetClassNameId !=
					commerceQualificationRel.getTargetClassNameId()) ||
				(targetClassPK !=
					commerceQualificationRel.getTargetClassPK())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(6);

			sb.append(_SQL_SELECT_COMMERCEQUALIFICATIONREL_WHERE);

			sb.append(_FINDER_COLUMN_S_S_T_T_SOURCECLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_S_S_T_T_SOURCECLASSPK_2);

			sb.append(_FINDER_COLUMN_S_S_T_T_TARGETCLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_S_S_T_T_TARGETCLASSPK_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sourceClassNameId);

				queryPos.add(sourceClassPK);

				queryPos.add(targetClassNameId);

				queryPos.add(targetClassPK);

				List<CommerceQualificationRel> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByS_S_T_T, finderArgs, list);
					}
				}
				else {
					CommerceQualificationRel commerceQualificationRel =
						list.get(0);

					result = commerceQualificationRel;

					cacheResult(commerceQualificationRel);
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
			return (CommerceQualificationRel)result;
		}
	}

	/**
	 * Removes the commerce qualification rel where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63; and targetClassPK = &#63; from the database.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param targetClassPK the target class pk
	 * @return the commerce qualification rel that was removed
	 */
	@Override
	public CommerceQualificationRel removeByS_S_T_T(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			long targetClassPK)
		throws NoSuchQualificationRelException {

		CommerceQualificationRel commerceQualificationRel = findByS_S_T_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, targetClassPK);

		return remove(commerceQualificationRel);
	}

	/**
	 * Returns the number of commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63; and targetClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param targetClassPK the target class pk
	 * @return the number of matching commerce qualification rels
	 */
	@Override
	public int countByS_S_T_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		long targetClassPK) {

		FinderPath finderPath = _finderPathCountByS_S_T_T;

		Object[] finderArgs = new Object[] {
			sourceClassNameId, sourceClassPK, targetClassNameId, targetClassPK
		};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs);

		if (count == null) {
			StringBundler sb = new StringBundler(5);

			sb.append(_SQL_COUNT_COMMERCEQUALIFICATIONREL_WHERE);

			sb.append(_FINDER_COLUMN_S_S_T_T_SOURCECLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_S_S_T_T_SOURCECLASSPK_2);

			sb.append(_FINDER_COLUMN_S_S_T_T_TARGETCLASSNAMEID_2);

			sb.append(_FINDER_COLUMN_S_S_T_T_TARGETCLASSPK_2);

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				queryPos.add(sourceClassNameId);

				queryPos.add(sourceClassPK);

				queryPos.add(targetClassNameId);

				queryPos.add(targetClassPK);

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

	private static final String _FINDER_COLUMN_S_S_T_T_SOURCECLASSNAMEID_2 =
		"commerceQualificationRel.sourceClassNameId = ? AND ";

	private static final String _FINDER_COLUMN_S_S_T_T_SOURCECLASSPK_2 =
		"commerceQualificationRel.sourceClassPK = ? AND ";

	private static final String _FINDER_COLUMN_S_S_T_T_TARGETCLASSNAMEID_2 =
		"commerceQualificationRel.targetClassNameId = ? AND ";

	private static final String _FINDER_COLUMN_S_S_T_T_TARGETCLASSPK_2 =
		"commerceQualificationRel.targetClassPK = ?";

	public CommerceQualificationRelPersistenceImpl() {
		setModelClass(CommerceQualificationRel.class);

		setModelImplClass(CommerceQualificationRelImpl.class);
		setModelPKClass(long.class);

		setTable(CommerceQualificationRelTable.INSTANCE);
	}

	/**
	 * Caches the commerce qualification rel in the entity cache if it is enabled.
	 *
	 * @param commerceQualificationRel the commerce qualification rel
	 */
	@Override
	public void cacheResult(CommerceQualificationRel commerceQualificationRel) {
		entityCache.putResult(
			CommerceQualificationRelImpl.class,
			commerceQualificationRel.getPrimaryKey(), commerceQualificationRel);

		finderCache.putResult(
			_finderPathFetchByS_S_T_T,
			new Object[] {
				commerceQualificationRel.getSourceClassNameId(),
				commerceQualificationRel.getSourceClassPK(),
				commerceQualificationRel.getTargetClassNameId(),
				commerceQualificationRel.getTargetClassPK()
			},
			commerceQualificationRel);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the commerce qualification rels in the entity cache if it is enabled.
	 *
	 * @param commerceQualificationRels the commerce qualification rels
	 */
	@Override
	public void cacheResult(
		List<CommerceQualificationRel> commerceQualificationRels) {

		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (commerceQualificationRels.size() >
				 _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (CommerceQualificationRel commerceQualificationRel :
				commerceQualificationRels) {

			if (entityCache.getResult(
					CommerceQualificationRelImpl.class,
					commerceQualificationRel.getPrimaryKey()) == null) {

				cacheResult(commerceQualificationRel);
			}
		}
	}

	/**
	 * Clears the cache for all commerce qualification rels.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CommerceQualificationRelImpl.class);

		finderCache.clearCache(CommerceQualificationRelImpl.class);
	}

	/**
	 * Clears the cache for the commerce qualification rel.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CommerceQualificationRel commerceQualificationRel) {
		entityCache.removeResult(
			CommerceQualificationRelImpl.class, commerceQualificationRel);
	}

	@Override
	public void clearCache(
		List<CommerceQualificationRel> commerceQualificationRels) {

		for (CommerceQualificationRel commerceQualificationRel :
				commerceQualificationRels) {

			entityCache.removeResult(
				CommerceQualificationRelImpl.class, commerceQualificationRel);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(CommerceQualificationRelImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(
				CommerceQualificationRelImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		CommerceQualificationRelModelImpl commerceQualificationRelModelImpl) {

		Object[] args = new Object[] {
			commerceQualificationRelModelImpl.getSourceClassNameId(),
			commerceQualificationRelModelImpl.getSourceClassPK(),
			commerceQualificationRelModelImpl.getTargetClassNameId(),
			commerceQualificationRelModelImpl.getTargetClassPK()
		};

		finderCache.putResult(_finderPathCountByS_S_T_T, args, Long.valueOf(1));
		finderCache.putResult(
			_finderPathFetchByS_S_T_T, args, commerceQualificationRelModelImpl);
	}

	/**
	 * Creates a new commerce qualification rel with the primary key. Does not add the commerce qualification rel to the database.
	 *
	 * @param commerceQualificationRelId the primary key for the new commerce qualification rel
	 * @return the new commerce qualification rel
	 */
	@Override
	public CommerceQualificationRel create(long commerceQualificationRelId) {
		CommerceQualificationRel commerceQualificationRel =
			new CommerceQualificationRelImpl();

		commerceQualificationRel.setNew(true);
		commerceQualificationRel.setPrimaryKey(commerceQualificationRelId);

		commerceQualificationRel.setCompanyId(
			CompanyThreadLocal.getCompanyId());

		return commerceQualificationRel;
	}

	/**
	 * Removes the commerce qualification rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel that was removed
	 * @throws NoSuchQualificationRelException if a commerce qualification rel with the primary key could not be found
	 */
	@Override
	public CommerceQualificationRel remove(long commerceQualificationRelId)
		throws NoSuchQualificationRelException {

		return remove((Serializable)commerceQualificationRelId);
	}

	/**
	 * Removes the commerce qualification rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the commerce qualification rel
	 * @return the commerce qualification rel that was removed
	 * @throws NoSuchQualificationRelException if a commerce qualification rel with the primary key could not be found
	 */
	@Override
	public CommerceQualificationRel remove(Serializable primaryKey)
		throws NoSuchQualificationRelException {

		Session session = null;

		try {
			session = openSession();

			CommerceQualificationRel commerceQualificationRel =
				(CommerceQualificationRel)session.get(
					CommerceQualificationRelImpl.class, primaryKey);

			if (commerceQualificationRel == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchQualificationRelException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(commerceQualificationRel);
		}
		catch (NoSuchQualificationRelException noSuchEntityException) {
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
	protected CommerceQualificationRel removeImpl(
		CommerceQualificationRel commerceQualificationRel) {

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(commerceQualificationRel)) {
				commerceQualificationRel =
					(CommerceQualificationRel)session.get(
						CommerceQualificationRelImpl.class,
						commerceQualificationRel.getPrimaryKeyObj());
			}

			if (commerceQualificationRel != null) {
				session.delete(commerceQualificationRel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (commerceQualificationRel != null) {
			clearCache(commerceQualificationRel);
		}

		return commerceQualificationRel;
	}

	@Override
	public CommerceQualificationRel updateImpl(
		CommerceQualificationRel commerceQualificationRel) {

		boolean isNew = commerceQualificationRel.isNew();

		if (!(commerceQualificationRel instanceof
				CommerceQualificationRelModelImpl)) {

			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(commerceQualificationRel.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					commerceQualificationRel);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in commerceQualificationRel proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CommerceQualificationRel implementation " +
					commerceQualificationRel.getClass());
		}

		CommerceQualificationRelModelImpl commerceQualificationRelModelImpl =
			(CommerceQualificationRelModelImpl)commerceQualificationRel;

		ServiceContext serviceContext =
			ServiceContextThreadLocal.getServiceContext();

		Date date = new Date();

		if (isNew && (commerceQualificationRel.getCreateDate() == null)) {
			if (serviceContext == null) {
				commerceQualificationRel.setCreateDate(date);
			}
			else {
				commerceQualificationRel.setCreateDate(
					serviceContext.getCreateDate(date));
			}
		}

		if (!commerceQualificationRelModelImpl.hasSetModifiedDate()) {
			if (serviceContext == null) {
				commerceQualificationRel.setModifiedDate(date);
			}
			else {
				commerceQualificationRel.setModifiedDate(
					serviceContext.getModifiedDate(date));
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(commerceQualificationRel);
			}
			else {
				commerceQualificationRel =
					(CommerceQualificationRel)session.merge(
						commerceQualificationRel);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			CommerceQualificationRelImpl.class,
			commerceQualificationRelModelImpl, false, true);

		cacheUniqueFindersCache(commerceQualificationRelModelImpl);

		if (isNew) {
			commerceQualificationRel.setNew(false);
		}

		commerceQualificationRel.resetOriginalValues();

		return commerceQualificationRel;
	}

	/**
	 * Returns the commerce qualification rel with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the commerce qualification rel
	 * @return the commerce qualification rel
	 * @throws NoSuchQualificationRelException if a commerce qualification rel with the primary key could not be found
	 */
	@Override
	public CommerceQualificationRel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchQualificationRelException {

		CommerceQualificationRel commerceQualificationRel = fetchByPrimaryKey(
			primaryKey);

		if (commerceQualificationRel == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchQualificationRelException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return commerceQualificationRel;
	}

	/**
	 * Returns the commerce qualification rel with the primary key or throws a <code>NoSuchQualificationRelException</code> if it could not be found.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel
	 * @throws NoSuchQualificationRelException if a commerce qualification rel with the primary key could not be found
	 */
	@Override
	public CommerceQualificationRel findByPrimaryKey(
			long commerceQualificationRelId)
		throws NoSuchQualificationRelException {

		return findByPrimaryKey((Serializable)commerceQualificationRelId);
	}

	/**
	 * Returns the commerce qualification rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel, or <code>null</code> if a commerce qualification rel with the primary key could not be found
	 */
	@Override
	public CommerceQualificationRel fetchByPrimaryKey(
		long commerceQualificationRelId) {

		return fetchByPrimaryKey((Serializable)commerceQualificationRelId);
	}

	/**
	 * Returns all the commerce qualification rels.
	 *
	 * @return the commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the commerce qualification rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce qualification rels
	 * @param end the upper bound of the range of commerce qualification rels (not inclusive)
	 * @return the range of commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the commerce qualification rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce qualification rels
	 * @param end the upper bound of the range of commerce qualification rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findAll(
		int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the commerce qualification rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce qualification rels
	 * @param end the upper bound of the range of commerce qualification rels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of commerce qualification rels
	 */
	@Override
	public List<CommerceQualificationRel> findAll(
		int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator,
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

		List<CommerceQualificationRel> list = null;

		if (useFinderCache) {
			list = (List<CommerceQualificationRel>)finderCache.getResult(
				finderPath, finderArgs);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_COMMERCEQUALIFICATIONREL);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_COMMERCEQUALIFICATIONREL;

				sql = sql.concat(
					CommerceQualificationRelModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<CommerceQualificationRel>)QueryUtil.list(
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
	 * Removes all the commerce qualification rels from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CommerceQualificationRel commerceQualificationRel : findAll()) {
			remove(commerceQualificationRel);
		}
	}

	/**
	 * Returns the number of commerce qualification rels.
	 *
	 * @return the number of commerce qualification rels
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
					_SQL_COUNT_COMMERCEQUALIFICATIONREL);

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
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "commerceQualificationRelId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_COMMERCEQUALIFICATIONREL;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CommerceQualificationRelModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the commerce qualification rel persistence.
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

		_finderPathWithPaginationFindByS_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_S",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Integer.class.getName(), Integer.class.getName(),
				OrderByComparator.class.getName()
			},
			new String[] {"sourceClassNameId", "sourceClassPK"}, true);

		_finderPathWithoutPaginationFindByS_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS_S",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sourceClassNameId", "sourceClassPK"}, true);

		_finderPathCountByS_S = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_S",
			new String[] {Long.class.getName(), Long.class.getName()},
			new String[] {"sourceClassNameId", "sourceClassPK"}, false);

		_finderPathWithPaginationFindByS_S_T = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByS_S_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {
				"sourceClassNameId", "sourceClassPK", "targetClassNameId"
			},
			true);

		_finderPathWithoutPaginationFindByS_S_T = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByS_S_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			new String[] {
				"sourceClassNameId", "sourceClassPK", "targetClassNameId"
			},
			true);

		_finderPathCountByS_S_T = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_S_T",
			new String[] {
				Long.class.getName(), Long.class.getName(), Long.class.getName()
			},
			new String[] {
				"sourceClassNameId", "sourceClassPK", "targetClassNameId"
			},
			false);

		_finderPathFetchByS_S_T_T = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByS_S_T_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			},
			new String[] {
				"sourceClassNameId", "sourceClassPK", "targetClassNameId",
				"targetClassPK"
			},
			true);

		_finderPathCountByS_S_T_T = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByS_S_T_T",
			new String[] {
				Long.class.getName(), Long.class.getName(),
				Long.class.getName(), Long.class.getName()
			},
			new String[] {
				"sourceClassNameId", "sourceClassPK", "targetClassNameId",
				"targetClassPK"
			},
			false);

		_setCommerceQualificationRelUtilPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		_setCommerceQualificationRelUtilPersistence(null);

		entityCache.removeCache(CommerceQualificationRelImpl.class.getName());
	}

	private void _setCommerceQualificationRelUtilPersistence(
		CommerceQualificationRelPersistence
			commerceQualificationRelPersistence) {

		try {
			Field field = CommerceQualificationRelUtil.class.getDeclaredField(
				"_persistence");

			field.setAccessible(true);

			field.set(null, commerceQualificationRelPersistence);
		}
		catch (ReflectiveOperationException reflectiveOperationException) {
			throw new RuntimeException(reflectiveOperationException);
		}
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = CommercePersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_COMMERCEQUALIFICATIONREL =
		"SELECT commerceQualificationRel FROM CommerceQualificationRel commerceQualificationRel";

	private static final String _SQL_SELECT_COMMERCEQUALIFICATIONREL_WHERE =
		"SELECT commerceQualificationRel FROM CommerceQualificationRel commerceQualificationRel WHERE ";

	private static final String _SQL_COUNT_COMMERCEQUALIFICATIONREL =
		"SELECT COUNT(commerceQualificationRel) FROM CommerceQualificationRel commerceQualificationRel";

	private static final String _SQL_COUNT_COMMERCEQUALIFICATIONREL_WHERE =
		"SELECT COUNT(commerceQualificationRel) FROM CommerceQualificationRel commerceQualificationRel WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS =
		"commerceQualificationRel.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CommerceQualificationRel exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CommerceQualificationRel exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceQualificationRelPersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

	@Reference
	private CommerceQualificationRelModelArgumentsResolver
		_commerceQualificationRelModelArgumentsResolver;

}