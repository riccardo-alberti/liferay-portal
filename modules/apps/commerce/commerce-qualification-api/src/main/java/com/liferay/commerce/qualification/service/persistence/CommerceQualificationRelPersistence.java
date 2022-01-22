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

import com.liferay.commerce.qualification.exception.NoSuchQualificationRelException;
import com.liferay.commerce.qualification.model.CommerceQualificationRel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the commerce qualification rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommerceQualificationRelUtil
 * @generated
 */
@ProviderType
public interface CommerceQualificationRelPersistence
	extends BasePersistence<CommerceQualificationRel> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceQualificationRelUtil} to access the commerce qualification rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @return the matching commerce qualification rels
	 */
	public java.util.List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK);

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
	public java.util.List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK, int start, int end);

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
	public java.util.List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceQualificationRel> orderByComparator);

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
	public java.util.List<CommerceQualificationRel> findByS_S(
		long sourceClassNameId, long sourceClassPK, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceQualificationRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce qualification rel
	 * @throws NoSuchQualificationRelException if a matching commerce qualification rel could not be found
	 */
	public CommerceQualificationRel findByS_S_First(
			long sourceClassNameId, long sourceClassPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException;

	/**
	 * Returns the first commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	public CommerceQualificationRel fetchByS_S_First(
		long sourceClassNameId, long sourceClassPK,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceQualificationRel> orderByComparator);

	/**
	 * Returns the last commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce qualification rel
	 * @throws NoSuchQualificationRelException if a matching commerce qualification rel could not be found
	 */
	public CommerceQualificationRel findByS_S_Last(
			long sourceClassNameId, long sourceClassPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException;

	/**
	 * Returns the last commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	public CommerceQualificationRel fetchByS_S_Last(
		long sourceClassNameId, long sourceClassPK,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceQualificationRel> orderByComparator);

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
	public CommerceQualificationRel[] findByS_S_PrevAndNext(
			long commerceQualificationRelId, long sourceClassNameId,
			long sourceClassPK,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException;

	/**
	 * Removes all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; from the database.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 */
	public void removeByS_S(long sourceClassNameId, long sourceClassPK);

	/**
	 * Returns the number of commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @return the number of matching commerce qualification rels
	 */
	public int countByS_S(long sourceClassNameId, long sourceClassPK);

	/**
	 * Returns all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @return the matching commerce qualification rels
	 */
	public java.util.List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId);

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
	public java.util.List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		int start, int end);

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
	public java.util.List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceQualificationRel> orderByComparator);

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
	public java.util.List<CommerceQualificationRel> findByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceQualificationRel> orderByComparator,
		boolean useFinderCache);

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
	public CommerceQualificationRel findByS_S_T_First(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException;

	/**
	 * Returns the first commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	public CommerceQualificationRel fetchByS_S_T_First(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceQualificationRel> orderByComparator);

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
	public CommerceQualificationRel findByS_S_T_Last(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException;

	/**
	 * Returns the last commerce qualification rel in the ordered set where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	public CommerceQualificationRel fetchByS_S_T_Last(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceQualificationRel> orderByComparator);

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
	public CommerceQualificationRel[] findByS_S_T_PrevAndNext(
			long commerceQualificationRelId, long sourceClassNameId,
			long sourceClassPK, long targetClassNameId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceQualificationRel> orderByComparator)
		throws NoSuchQualificationRelException;

	/**
	 * Removes all the commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63; from the database.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 */
	public void removeByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId);

	/**
	 * Returns the number of commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @return the number of matching commerce qualification rels
	 */
	public int countByS_S_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId);

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
	public CommerceQualificationRel findByS_S_T_T(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			long targetClassPK)
		throws NoSuchQualificationRelException;

	/**
	 * Returns the commerce qualification rel where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63; and targetClassPK = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param targetClassPK the target class pk
	 * @return the matching commerce qualification rel, or <code>null</code> if a matching commerce qualification rel could not be found
	 */
	public CommerceQualificationRel fetchByS_S_T_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		long targetClassPK);

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
	public CommerceQualificationRel fetchByS_S_T_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		long targetClassPK, boolean useFinderCache);

	/**
	 * Removes the commerce qualification rel where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63; and targetClassPK = &#63; from the database.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param targetClassPK the target class pk
	 * @return the commerce qualification rel that was removed
	 */
	public CommerceQualificationRel removeByS_S_T_T(
			long sourceClassNameId, long sourceClassPK, long targetClassNameId,
			long targetClassPK)
		throws NoSuchQualificationRelException;

	/**
	 * Returns the number of commerce qualification rels where sourceClassNameId = &#63; and sourceClassPK = &#63; and targetClassNameId = &#63; and targetClassPK = &#63;.
	 *
	 * @param sourceClassNameId the source class name ID
	 * @param sourceClassPK the source class pk
	 * @param targetClassNameId the target class name ID
	 * @param targetClassPK the target class pk
	 * @return the number of matching commerce qualification rels
	 */
	public int countByS_S_T_T(
		long sourceClassNameId, long sourceClassPK, long targetClassNameId,
		long targetClassPK);

	/**
	 * Caches the commerce qualification rel in the entity cache if it is enabled.
	 *
	 * @param commerceQualificationRel the commerce qualification rel
	 */
	public void cacheResult(CommerceQualificationRel commerceQualificationRel);

	/**
	 * Caches the commerce qualification rels in the entity cache if it is enabled.
	 *
	 * @param commerceQualificationRels the commerce qualification rels
	 */
	public void cacheResult(
		java.util.List<CommerceQualificationRel> commerceQualificationRels);

	/**
	 * Creates a new commerce qualification rel with the primary key. Does not add the commerce qualification rel to the database.
	 *
	 * @param commerceQualificationRelId the primary key for the new commerce qualification rel
	 * @return the new commerce qualification rel
	 */
	public CommerceQualificationRel create(long commerceQualificationRelId);

	/**
	 * Removes the commerce qualification rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel that was removed
	 * @throws NoSuchQualificationRelException if a commerce qualification rel with the primary key could not be found
	 */
	public CommerceQualificationRel remove(long commerceQualificationRelId)
		throws NoSuchQualificationRelException;

	public CommerceQualificationRel updateImpl(
		CommerceQualificationRel commerceQualificationRel);

	/**
	 * Returns the commerce qualification rel with the primary key or throws a <code>NoSuchQualificationRelException</code> if it could not be found.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel
	 * @throws NoSuchQualificationRelException if a commerce qualification rel with the primary key could not be found
	 */
	public CommerceQualificationRel findByPrimaryKey(
			long commerceQualificationRelId)
		throws NoSuchQualificationRelException;

	/**
	 * Returns the commerce qualification rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel, or <code>null</code> if a commerce qualification rel with the primary key could not be found
	 */
	public CommerceQualificationRel fetchByPrimaryKey(
		long commerceQualificationRelId);

	/**
	 * Returns all the commerce qualification rels.
	 *
	 * @return the commerce qualification rels
	 */
	public java.util.List<CommerceQualificationRel> findAll();

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
	public java.util.List<CommerceQualificationRel> findAll(int start, int end);

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
	public java.util.List<CommerceQualificationRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceQualificationRel> orderByComparator);

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
	public java.util.List<CommerceQualificationRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceQualificationRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the commerce qualification rels from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of commerce qualification rels.
	 *
	 * @return the number of commerce qualification rels
	 */
	public int countAll();

}