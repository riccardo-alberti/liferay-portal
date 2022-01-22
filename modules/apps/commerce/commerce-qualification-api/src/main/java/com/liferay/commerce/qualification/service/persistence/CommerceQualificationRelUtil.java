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

package com.liferay.commerce.qualification.service.persistence;

import com.liferay.commerce.qualification.model.CommerceQualificationRel;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the commerce qualification rel service. This utility wraps <code>com.liferay.commerce.qualification.service.persistence.impl.CommerceQualificationRelPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommerceQualificationRelPersistence
 * @generated
 */
public class CommerceQualificationRelUtil {

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
	public static void clearCache(
		CommerceQualificationRel commerceQualificationRel) {

		getPersistence().clearCache(commerceQualificationRel);
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
	public static Map<Serializable, CommerceQualificationRel>
		fetchByPrimaryKeys(Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CommerceQualificationRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CommerceQualificationRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CommerceQualificationRel> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CommerceQualificationRel update(
		CommerceQualificationRel commerceQualificationRel) {

		return getPersistence().update(commerceQualificationRel);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CommerceQualificationRel update(
		CommerceQualificationRel commerceQualificationRel,
		ServiceContext serviceContext) {

		return getPersistence().update(
			commerceQualificationRel, serviceContext);
	}

	/**
	 * Returns all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @return the matching commerce qualification rels
	 */
	public static List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK) {

		return getPersistence().findByS_S(sourceClassNameId, sourceClassPK);
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
	public static List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK, int start, int end) {

		return getPersistence().findByS_S(
			sourceClassNameId, sourceClassPK, start, end);
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
	public static List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK, int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return getPersistence().findByS_S(
			sourceClassNameId, sourceClassPK, start, end, orderByComparator);
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
	public static List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK, int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByS_S(
			sourceClassNameId, sourceClassPK, start, end, orderByComparator,
			useFinderCache);
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
	public static CommerceQualificationRel findByS_S_First(
			long sourceClassNameId, long sourceClassPK,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws com.liferay.commerce.qualification.exception.
			NoSuchQualificationRelException {

		return getPersistence().findByS_S_First(
			sourceClassNameId, sourceClassPK, orderByComparator);
	}

	/**
	 * Returns the first commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	public static CommerceQualificationRel fetchByS_S_First(
		long sourceClassNameId, long sourceClassPK,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return getPersistence().fetchByS_S_First(
			sourceClassNameId, sourceClassPK, orderByComparator);
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
	public static CommerceQualificationRel findByS_S_Last(
			long sourceClassNameId, long sourceClassPK,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws com.liferay.commerce.qualification.exception.
			NoSuchQualificationRelException {

		return getPersistence().findByS_S_Last(
			sourceClassNameId, sourceClassPK, orderByComparator);
	}

	/**
	 * Returns the last commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	public static CommerceQualificationRel fetchByS_S_Last(
		long sourceClassNameId, long sourceClassPK,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return getPersistence().fetchByS_S_Last(
			sourceClassNameId, sourceClassPK, orderByComparator);
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
	public static CommerceQualificationRel[] findByS_S_PrevAndNext(
			long commerceQualificationRelId, long sourceClassNameId,
			long sourceClassPK,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws com.liferay.commerce.qualification.exception.
			NoSuchQualificationRelException {

		return getPersistence().findByS_S_PrevAndNext(
			commerceQualificationRelId, sourceClassNameId, sourceClassPK,
			orderByComparator);
	}

	/**
	 * Removes all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; from the database.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 */
	public static void removeByS_S(long sourceClassNameId, long sourceClassPK) {
		getPersistence().removeByS_S(sourceClassNameId, sourceClassPK);
	}

	/**
	 * Returns the number of commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @return the number of matching commerce qualification rels
	 */
	public static int countByS_S(long sourceClassNameId, long sourceClassPK) {
		return getPersistence().countByS_S(sourceClassNameId, sourceClassPK);
	}

	/**
	 * Returns all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @return the matching commerce qualification rels
	 */
	public static List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId) {

		return getPersistence().findByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId);
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
	public static List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		int start, int end) {

		return getPersistence().findByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, start, end);
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
	public static List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return getPersistence().findByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, start, end,
			orderByComparator);
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
	public static List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, start, end,
			orderByComparator, useFinderCache);
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
	public static CommerceQualificationRel findByS_S_T_First(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws com.liferay.commerce.qualification.exception.
			NoSuchQualificationRelException {

		return getPersistence().findByS_S_T_First(
			sourceClassNameId, sourceClassPK, targetClassNameId,
			orderByComparator);
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
	public static CommerceQualificationRel fetchByS_S_T_First(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return getPersistence().fetchByS_S_T_First(
			sourceClassNameId, sourceClassPK, targetClassNameId,
			orderByComparator);
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
	public static CommerceQualificationRel findByS_S_T_Last(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws com.liferay.commerce.qualification.exception.
			NoSuchQualificationRelException {

		return getPersistence().findByS_S_T_Last(
			sourceClassNameId, sourceClassPK, targetClassNameId,
			orderByComparator);
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
	public static CommerceQualificationRel fetchByS_S_T_Last(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return getPersistence().fetchByS_S_T_Last(
			sourceClassNameId, sourceClassPK, targetClassNameId,
			orderByComparator);
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
	public static CommerceQualificationRel[] findByS_S_T_PrevAndNext(
			long commerceQualificationRelId, long sourceClassNameId,
			long sourceClassPK, long targetClassNameId,
			OrderByComparator<CommerceQualificationRel> orderByComparator)
		throws com.liferay.commerce.qualification.exception.
			NoSuchQualificationRelException {

		return getPersistence().findByS_S_T_PrevAndNext(
			commerceQualificationRelId, sourceClassNameId, sourceClassPK,
			targetClassNameId, orderByComparator);
	}

	/**
	 * Removes all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63; from the database.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 */
	public static void removeByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId) {

		getPersistence().removeByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId);
	}

	/**
	 * Returns the number of commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @return the number of matching commerce qualification rels
	 */
	public static int countByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId) {

		return getPersistence().countByS_S_T(
			sourceClassNameId, sourceClassPK, targetClassNameId);
	}

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
	public static CommerceQualificationRel findByS_S_T_T(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			long targetClassPK)
		throws com.liferay.commerce.qualification.exception.
			NoSuchQualificationRelException {

		return getPersistence().findByS_S_T_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, targetClassPK);
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
	public static CommerceQualificationRel fetchByS_S_T_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		long targetClassPK) {

		return getPersistence().fetchByS_S_T_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, targetClassPK);
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
	public static CommerceQualificationRel fetchByS_S_T_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		long targetClassPK, boolean useFinderCache) {

		return getPersistence().fetchByS_S_T_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, targetClassPK,
			useFinderCache);
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
	public static CommerceQualificationRel removeByS_S_T_T(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			long targetClassPK)
		throws com.liferay.commerce.qualification.exception.
			NoSuchQualificationRelException {

		return getPersistence().removeByS_S_T_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, targetClassPK);
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
	public static int countByS_S_T_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		long targetClassPK) {

		return getPersistence().countByS_S_T_T(
			sourceClassNameId, sourceClassPK, targetClassNameId, targetClassPK);
	}

	/**
	 * Caches the commerce qualification rel in the entity cache if it is enabled.
	 *
	 * @param commerceQualificationRel the commerce qualification rel
	 */
	public static void cacheResult(
		CommerceQualificationRel commerceQualificationRel) {

		getPersistence().cacheResult(commerceQualificationRel);
	}

	/**
	 * Caches the commerce qualification rels in the entity cache if it is enabled.
	 *
	 * @param commerceQualificationRels the commerce qualification rels
	 */
	public static void cacheResult(
		List<CommerceQualificationRel> commerceQualificationRels) {

		getPersistence().cacheResult(commerceQualificationRels);
	}

	/**
	 * Creates a new commerce qualification rel with the primary key. Does not add the commerce qualification rel to the database.
	 *
	 * @param commerceQualificationRelId the primary key for the new commerce qualification rel
	 * @return the new commerce qualification rel
	 */
	public static CommerceQualificationRel create(
		long commerceQualificationRelId) {

		return getPersistence().create(commerceQualificationRelId);
	}

	/**
	 * Removes the commerce qualification rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel that was removed
	 * @throws NoSuchQualificationRelException if a commerce qualification rel with the primary key could not be found
	 */
	public static CommerceQualificationRel remove(
			long commerceQualificationRelId)
		throws com.liferay.commerce.qualification.exception.
			NoSuchQualificationRelException {

		return getPersistence().remove(commerceQualificationRelId);
	}

	public static CommerceQualificationRel updateImpl(
		CommerceQualificationRel commerceQualificationRel) {

		return getPersistence().updateImpl(commerceQualificationRel);
	}

	/**
	 * Returns the commerce qualification rel with the primary key or throws a <code>NoSuchQualificationRelException</code> if it could not be found.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel
	 * @throws NoSuchQualificationRelException if a commerce qualification rel with the primary key could not be found
	 */
	public static CommerceQualificationRel findByPrimaryKey(
			long commerceQualificationRelId)
		throws com.liferay.commerce.qualification.exception.
			NoSuchQualificationRelException {

		return getPersistence().findByPrimaryKey(commerceQualificationRelId);
	}

	/**
	 * Returns the commerce qualification rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel, or <code>null</code> if a commerce qualification rel with the primary key could not be found
	 */
	public static CommerceQualificationRel fetchByPrimaryKey(
		long commerceQualificationRelId) {

		return getPersistence().fetchByPrimaryKey(commerceQualificationRelId);
	}

	/**
	 * Returns all the commerce qualification rels.
	 *
	 * @return the commerce qualification rels
	 */
	public static List<CommerceQualificationRel> findAll() {
		return getPersistence().findAll();
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
	public static List<CommerceQualificationRel> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<CommerceQualificationRel> findAll(
		int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<CommerceQualificationRel> findAll(
		int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the commerce qualification rels from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of commerce qualification rels.
	 *
	 * @return the number of commerce qualification rels
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CommerceQualificationRelPersistence getPersistence() {
		return _persistence;
	}

	private static volatile CommerceQualificationRelPersistence _persistence;

}