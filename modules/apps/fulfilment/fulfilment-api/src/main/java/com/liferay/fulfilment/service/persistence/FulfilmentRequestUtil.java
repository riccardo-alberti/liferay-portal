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

import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the fulfilment request service. This utility wraps <code>com.liferay.fulfilment.service.persistence.impl.FulfilmentRequestPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @see FulfilmentRequestPersistence
 * @generated
 */
public class FulfilmentRequestUtil {

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
	public static void clearCache(FulfilmentRequest fulfilmentRequest) {
		getPersistence().clearCache(fulfilmentRequest);
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
	public static Map<Serializable, FulfilmentRequest> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<FulfilmentRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<FulfilmentRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<FulfilmentRequest> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<FulfilmentRequest> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static FulfilmentRequest update(
		FulfilmentRequest fulfilmentRequest) {

		return getPersistence().update(fulfilmentRequest);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static FulfilmentRequest update(
		FulfilmentRequest fulfilmentRequest, ServiceContext serviceContext) {

		return getPersistence().update(fulfilmentRequest, serviceContext);
	}

	/**
	 * Returns all the fulfilment requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching fulfilment requests
	 */
	public static List<FulfilmentRequest> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
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
	public static List<FulfilmentRequest> findByUserId(
		long userId, int start, int end) {

		return getPersistence().findByUserId(userId, start, end);
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
	public static List<FulfilmentRequest> findByUserId(
		long userId, int start, int end,
		OrderByComparator<FulfilmentRequest> orderByComparator) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator);
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
	public static List<FulfilmentRequest> findByUserId(
		long userId, int start, int end,
		OrderByComparator<FulfilmentRequest> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByUserId(
			userId, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment request
	 * @throws NoSuchRequestException if a matching fulfilment request could not be found
	 */
	public static FulfilmentRequest findByUserId_First(
			long userId, OrderByComparator<FulfilmentRequest> orderByComparator)
		throws com.liferay.fulfilment.exception.NoSuchRequestException {

		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the first fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	public static FulfilmentRequest fetchByUserId_First(
		long userId, OrderByComparator<FulfilmentRequest> orderByComparator) {

		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	 * Returns the last fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment request
	 * @throws NoSuchRequestException if a matching fulfilment request could not be found
	 */
	public static FulfilmentRequest findByUserId_Last(
			long userId, OrderByComparator<FulfilmentRequest> orderByComparator)
		throws com.liferay.fulfilment.exception.NoSuchRequestException {

		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	 * Returns the last fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	public static FulfilmentRequest fetchByUserId_Last(
		long userId, OrderByComparator<FulfilmentRequest> orderByComparator) {

		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
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
	public static FulfilmentRequest[] findByUserId_PrevAndNext(
			long fulfilmentRequestId, long userId,
			OrderByComparator<FulfilmentRequest> orderByComparator)
		throws com.liferay.fulfilment.exception.NoSuchRequestException {

		return getPersistence().findByUserId_PrevAndNext(
			fulfilmentRequestId, userId, orderByComparator);
	}

	/**
	 * Returns all the fulfilment requests that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching fulfilment requests that the user has permission to view
	 */
	public static List<FulfilmentRequest> filterFindByUserId(long userId) {
		return getPersistence().filterFindByUserId(userId);
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
	public static List<FulfilmentRequest> filterFindByUserId(
		long userId, int start, int end) {

		return getPersistence().filterFindByUserId(userId, start, end);
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
	public static List<FulfilmentRequest> filterFindByUserId(
		long userId, int start, int end,
		OrderByComparator<FulfilmentRequest> orderByComparator) {

		return getPersistence().filterFindByUserId(
			userId, start, end, orderByComparator);
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
	public static FulfilmentRequest[] filterFindByUserId_PrevAndNext(
			long fulfilmentRequestId, long userId,
			OrderByComparator<FulfilmentRequest> orderByComparator)
		throws com.liferay.fulfilment.exception.NoSuchRequestException {

		return getPersistence().filterFindByUserId_PrevAndNext(
			fulfilmentRequestId, userId, orderByComparator);
	}

	/**
	 * Removes all the fulfilment requests where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	 * Returns the number of fulfilment requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching fulfilment requests
	 */
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	 * Returns the number of fulfilment requests that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching fulfilment requests that the user has permission to view
	 */
	public static int filterCountByUserId(long userId) {
		return getPersistence().filterCountByUserId(userId);
	}

	/**
	 * Returns the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchRequestException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching fulfilment request
	 * @throws NoSuchRequestException if a matching fulfilment request could not be found
	 */
	public static FulfilmentRequest findByC_ERC(
			long companyId, String externalReferenceCode)
		throws com.liferay.fulfilment.exception.NoSuchRequestException {

		return getPersistence().findByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	public static FulfilmentRequest fetchByC_ERC(
		long companyId, String externalReferenceCode) {

		return getPersistence().fetchByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	public static FulfilmentRequest fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache) {

		return getPersistence().fetchByC_ERC(
			companyId, externalReferenceCode, useFinderCache);
	}

	/**
	 * Removes the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the fulfilment request that was removed
	 */
	public static FulfilmentRequest removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws com.liferay.fulfilment.exception.NoSuchRequestException {

		return getPersistence().removeByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Returns the number of fulfilment requests where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching fulfilment requests
	 */
	public static int countByC_ERC(
		long companyId, String externalReferenceCode) {

		return getPersistence().countByC_ERC(companyId, externalReferenceCode);
	}

	/**
	 * Caches the fulfilment request in the entity cache if it is enabled.
	 *
	 * @param fulfilmentRequest the fulfilment request
	 */
	public static void cacheResult(FulfilmentRequest fulfilmentRequest) {
		getPersistence().cacheResult(fulfilmentRequest);
	}

	/**
	 * Caches the fulfilment requests in the entity cache if it is enabled.
	 *
	 * @param fulfilmentRequests the fulfilment requests
	 */
	public static void cacheResult(List<FulfilmentRequest> fulfilmentRequests) {
		getPersistence().cacheResult(fulfilmentRequests);
	}

	/**
	 * Creates a new fulfilment request with the primary key. Does not add the fulfilment request to the database.
	 *
	 * @param fulfilmentRequestId the primary key for the new fulfilment request
	 * @return the new fulfilment request
	 */
	public static FulfilmentRequest create(long fulfilmentRequestId) {
		return getPersistence().create(fulfilmentRequestId);
	}

	/**
	 * Removes the fulfilment request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request that was removed
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	public static FulfilmentRequest remove(long fulfilmentRequestId)
		throws com.liferay.fulfilment.exception.NoSuchRequestException {

		return getPersistence().remove(fulfilmentRequestId);
	}

	public static FulfilmentRequest updateImpl(
		FulfilmentRequest fulfilmentRequest) {

		return getPersistence().updateImpl(fulfilmentRequest);
	}

	/**
	 * Returns the fulfilment request with the primary key or throws a <code>NoSuchRequestException</code> if it could not be found.
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	public static FulfilmentRequest findByPrimaryKey(long fulfilmentRequestId)
		throws com.liferay.fulfilment.exception.NoSuchRequestException {

		return getPersistence().findByPrimaryKey(fulfilmentRequestId);
	}

	/**
	 * Returns the fulfilment request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request, or <code>null</code> if a fulfilment request with the primary key could not be found
	 */
	public static FulfilmentRequest fetchByPrimaryKey(
		long fulfilmentRequestId) {

		return getPersistence().fetchByPrimaryKey(fulfilmentRequestId);
	}

	/**
	 * Returns all the fulfilment requests.
	 *
	 * @return the fulfilment requests
	 */
	public static List<FulfilmentRequest> findAll() {
		return getPersistence().findAll();
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
	public static List<FulfilmentRequest> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<FulfilmentRequest> findAll(
		int start, int end,
		OrderByComparator<FulfilmentRequest> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<FulfilmentRequest> findAll(
		int start, int end,
		OrderByComparator<FulfilmentRequest> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the fulfilment requests from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of fulfilment requests.
	 *
	 * @return the number of fulfilment requests
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static FulfilmentRequestPersistence getPersistence() {
		return _persistence;
	}

	private static volatile FulfilmentRequestPersistence _persistence;

}