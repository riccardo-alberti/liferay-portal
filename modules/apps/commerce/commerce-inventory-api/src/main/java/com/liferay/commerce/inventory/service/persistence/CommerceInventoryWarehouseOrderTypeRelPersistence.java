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

package com.liferay.commerce.inventory.service.persistence;

import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseOrderTypeRelException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseOrderTypeRel;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the commerce inventory warehouse order type rel service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouseOrderTypeRelUtil
 * @generated
 */
@ProviderType
public interface CommerceInventoryWarehouseOrderTypeRelPersistence
	extends BasePersistence<CommerceInventoryWarehouseOrderTypeRel> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CommerceInventoryWarehouseOrderTypeRelUtil} to access the commerce inventory warehouse order type rel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the commerce inventory warehouse order type rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching commerce inventory warehouse order type rels
	 */
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findByUuid(
		String uuid);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findByUuid(
		String uuid, int start, int end);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findByUuid(
		String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseOrderTypeRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel findByUuid_First(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel fetchByUuid_First(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel findByUuid_Last(
			String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel fetchByUuid_Last(
		String uuid,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

	/**
	 * Returns the commerce inventory warehouse order type rels before and after the current commerce inventory warehouse order type rel in the ordered set where uuid = &#63;.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the current commerce inventory warehouse order type rel
	 * @param uuid the uuid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel[] findByUuid_PrevAndNext(
			long commerceInventoryWarehouseOrderTypeRelId, String uuid,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Removes all the commerce inventory warehouse order type rels where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 */
	public void removeByUuid(String uuid);

	/**
	 * Returns the number of commerce inventory warehouse order type rels where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching commerce inventory warehouse order type rels
	 */
	public int countByUuid(String uuid);

	/**
	 * Returns all the commerce inventory warehouse order type rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the matching commerce inventory warehouse order type rels
	 */
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findByUuid_C(
		String uuid, long companyId);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findByUuid_C(
		String uuid, long companyId, int start, int end);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findByUuid_C(
		String uuid, long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseOrderTypeRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel findByUuid_C_First(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel fetchByUuid_C_First(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel findByUuid_C_Last(
			String uuid, long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel fetchByUuid_C_Last(
		String uuid, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

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
	public CommerceInventoryWarehouseOrderTypeRel[] findByUuid_C_PrevAndNext(
			long commerceInventoryWarehouseOrderTypeRelId, String uuid,
			long companyId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Removes all the commerce inventory warehouse order type rels where uuid = &#63; and companyId = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 */
	public void removeByUuid_C(String uuid, long companyId);

	/**
	 * Returns the number of commerce inventory warehouse order type rels where uuid = &#63; and companyId = &#63;.
	 *
	 * @param uuid the uuid
	 * @param companyId the company ID
	 * @return the number of matching commerce inventory warehouse order type rels
	 */
	public int countByUuid_C(String uuid, long companyId);

	/**
	 * Returns all the commerce inventory warehouse order type rels where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @return the matching commerce inventory warehouse order type rels
	 */
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceInventoryWarehouseId(long commerceInventoryWarehouseId);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceInventoryWarehouseId(
			long commerceInventoryWarehouseId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel
			findByCommerceInventoryWarehouseId_First(
				long commerceInventoryWarehouseId,
				com.liferay.portal.kernel.util.OrderByComparator
					<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel
		fetchByCommerceInventoryWarehouseId_First(
			long commerceInventoryWarehouseId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel
			findByCommerceInventoryWarehouseId_Last(
				long commerceInventoryWarehouseId,
				com.liferay.portal.kernel.util.OrderByComparator
					<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel
		fetchByCommerceInventoryWarehouseId_Last(
			long commerceInventoryWarehouseId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

	/**
	 * Returns the commerce inventory warehouse order type rels before and after the current commerce inventory warehouse order type rel in the ordered set where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the current commerce inventory warehouse order type rel
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel[]
			findByCommerceInventoryWarehouseId_PrevAndNext(
				long commerceInventoryWarehouseOrderTypeRelId,
				long commerceInventoryWarehouseId,
				com.liferay.portal.kernel.util.OrderByComparator
					<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Removes all the commerce inventory warehouse order type rels where commerceInventoryWarehouseId = &#63; from the database.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 */
	public void removeByCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId);

	/**
	 * Returns the number of commerce inventory warehouse order type rels where commerceInventoryWarehouseId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @return the number of matching commerce inventory warehouse order type rels
	 */
	public int countByCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId);

	/**
	 * Returns all the commerce inventory warehouse order type rels where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the matching commerce inventory warehouse order type rels
	 */
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceOrderTypeId(long commerceOrderTypeId);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceOrderTypeId(long commerceOrderTypeId, int start, int end);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceOrderTypeId(
			long commerceOrderTypeId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel>
		findByCommerceOrderTypeId(
			long commerceOrderTypeId, int start, int end,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator,
			boolean useFinderCache);

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel
			findByCommerceOrderTypeId_First(
				long commerceOrderTypeId,
				com.liferay.portal.kernel.util.OrderByComparator
					<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Returns the first commerce inventory warehouse order type rel in the ordered set where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel
		fetchByCommerceOrderTypeId_First(
			long commerceOrderTypeId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel
			findByCommerceOrderTypeId_Last(
				long commerceOrderTypeId,
				com.liferay.portal.kernel.util.OrderByComparator
					<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Returns the last commerce inventory warehouse order type rel in the ordered set where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel
		fetchByCommerceOrderTypeId_Last(
			long commerceOrderTypeId,
			com.liferay.portal.kernel.util.OrderByComparator
				<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

	/**
	 * Returns the commerce inventory warehouse order type rels before and after the current commerce inventory warehouse order type rel in the ordered set where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the current commerce inventory warehouse order type rel
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel[]
			findByCommerceOrderTypeId_PrevAndNext(
				long commerceInventoryWarehouseOrderTypeRelId,
				long commerceOrderTypeId,
				com.liferay.portal.kernel.util.OrderByComparator
					<CommerceInventoryWarehouseOrderTypeRel> orderByComparator)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Removes all the commerce inventory warehouse order type rels where commerceOrderTypeId = &#63; from the database.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 */
	public void removeByCommerceOrderTypeId(long commerceOrderTypeId);

	/**
	 * Returns the number of commerce inventory warehouse order type rels where commerceOrderTypeId = &#63;.
	 *
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the number of matching commerce inventory warehouse order type rels
	 */
	public int countByCommerceOrderTypeId(long commerceOrderTypeId);

	/**
	 * Returns the commerce inventory warehouse order type rel where commerceInventoryWarehouseId = &#63; and commerceOrderTypeId = &#63; or throws a <code>NoSuchInventoryWarehouseOrderTypeRelException</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the matching commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel findByCIW_COTI(
			long commerceInventoryWarehouseId, long commerceOrderTypeId)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Returns the commerce inventory warehouse order type rel where commerceInventoryWarehouseId = &#63; and commerceOrderTypeId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel fetchByCIW_COTI(
		long commerceInventoryWarehouseId, long commerceOrderTypeId);

	/**
	 * Returns the commerce inventory warehouse order type rel where commerceInventoryWarehouseId = &#63; and commerceOrderTypeId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param commerceOrderTypeId the commerce order type ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching commerce inventory warehouse order type rel, or <code>null</code> if a matching commerce inventory warehouse order type rel could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel fetchByCIW_COTI(
		long commerceInventoryWarehouseId, long commerceOrderTypeId,
		boolean useFinderCache);

	/**
	 * Removes the commerce inventory warehouse order type rel where commerceInventoryWarehouseId = &#63; and commerceOrderTypeId = &#63; from the database.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the commerce inventory warehouse order type rel that was removed
	 */
	public CommerceInventoryWarehouseOrderTypeRel removeByCIW_COTI(
			long commerceInventoryWarehouseId, long commerceOrderTypeId)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Returns the number of commerce inventory warehouse order type rels where commerceInventoryWarehouseId = &#63; and commerceOrderTypeId = &#63;.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID
	 * @param commerceOrderTypeId the commerce order type ID
	 * @return the number of matching commerce inventory warehouse order type rels
	 */
	public int countByCIW_COTI(
		long commerceInventoryWarehouseId, long commerceOrderTypeId);

	/**
	 * Caches the commerce inventory warehouse order type rel in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRel the commerce inventory warehouse order type rel
	 */
	public void cacheResult(
		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel);

	/**
	 * Caches the commerce inventory warehouse order type rels in the entity cache if it is enabled.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRels the commerce inventory warehouse order type rels
	 */
	public void cacheResult(
		java.util.List<CommerceInventoryWarehouseOrderTypeRel>
			commerceInventoryWarehouseOrderTypeRels);

	/**
	 * Creates a new commerce inventory warehouse order type rel with the primary key. Does not add the commerce inventory warehouse order type rel to the database.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key for the new commerce inventory warehouse order type rel
	 * @return the new commerce inventory warehouse order type rel
	 */
	public CommerceInventoryWarehouseOrderTypeRel create(
		long commerceInventoryWarehouseOrderTypeRelId);

	/**
	 * Removes the commerce inventory warehouse order type rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel that was removed
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel remove(
			long commerceInventoryWarehouseOrderTypeRelId)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	public CommerceInventoryWarehouseOrderTypeRel updateImpl(
		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel);

	/**
	 * Returns the commerce inventory warehouse order type rel with the primary key or throws a <code>NoSuchInventoryWarehouseOrderTypeRelException</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel
	 * @throws NoSuchInventoryWarehouseOrderTypeRelException if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel findByPrimaryKey(
			long commerceInventoryWarehouseOrderTypeRelId)
		throws NoSuchInventoryWarehouseOrderTypeRelException;

	/**
	 * Returns the commerce inventory warehouse order type rel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param commerceInventoryWarehouseOrderTypeRelId the primary key of the commerce inventory warehouse order type rel
	 * @return the commerce inventory warehouse order type rel, or <code>null</code> if a commerce inventory warehouse order type rel with the primary key could not be found
	 */
	public CommerceInventoryWarehouseOrderTypeRel fetchByPrimaryKey(
		long commerceInventoryWarehouseOrderTypeRelId);

	/**
	 * Returns all the commerce inventory warehouse order type rels.
	 *
	 * @return the commerce inventory warehouse order type rels
	 */
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findAll();

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findAll(
		int start, int end);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseOrderTypeRel> orderByComparator);

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
	public java.util.List<CommerceInventoryWarehouseOrderTypeRel> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator
			<CommerceInventoryWarehouseOrderTypeRel> orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the commerce inventory warehouse order type rels from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of commerce inventory warehouse order type rels.
	 *
	 * @return the number of commerce inventory warehouse order type rels
	 */
	public int countAll();

}