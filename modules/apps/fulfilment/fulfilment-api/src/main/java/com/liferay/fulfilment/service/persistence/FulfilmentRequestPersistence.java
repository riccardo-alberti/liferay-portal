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

import com.liferay.fulfilment.exception.NoSuchRequestException;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the fulfilment request service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Riccardo Alberti
 * @see FulfilmentRequestUtil
 * @generated
 */
@ProviderType
public interface FulfilmentRequestPersistence
	extends BasePersistence<FulfilmentRequest> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link FulfilmentRequestUtil} to access the fulfilment request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns all the fulfilment requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching fulfilment requests
	 */
	public java.util.List<FulfilmentRequest> findByUserId(long userId);

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
	public java.util.List<FulfilmentRequest> findByUserId(
		long userId, int start, int end);

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
	public java.util.List<FulfilmentRequest> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentRequest>
			orderByComparator);

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
	public java.util.List<FulfilmentRequest> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment request
	 * @throws NoSuchRequestException if a matching fulfilment request could not be found
	 */
	public FulfilmentRequest findByUserId_First(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<FulfilmentRequest>
				orderByComparator)
		throws NoSuchRequestException;

	/**
	 * Returns the first fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	public FulfilmentRequest fetchByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentRequest>
			orderByComparator);

	/**
	 * Returns the last fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment request
	 * @throws NoSuchRequestException if a matching fulfilment request could not be found
	 */
	public FulfilmentRequest findByUserId_Last(
			long userId,
			com.liferay.portal.kernel.util.OrderByComparator<FulfilmentRequest>
				orderByComparator)
		throws NoSuchRequestException;

	/**
	 * Returns the last fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	public FulfilmentRequest fetchByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentRequest>
			orderByComparator);

	/**
	 * Returns the fulfilment requests before and after the current fulfilment request in the ordered set where userId = &#63;.
	 *
	 * @param fulfilmentRequestId the primary key of the current fulfilment request
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fulfilment request
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	public FulfilmentRequest[] findByUserId_PrevAndNext(
			long fulfilmentRequestId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<FulfilmentRequest>
				orderByComparator)
		throws NoSuchRequestException;

	/**
	 * Returns all the fulfilment requests that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the matching fulfilment requests that the user has permission to view
	 */
	public java.util.List<FulfilmentRequest> filterFindByUserId(long userId);

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
	public java.util.List<FulfilmentRequest> filterFindByUserId(
		long userId, int start, int end);

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
	public java.util.List<FulfilmentRequest> filterFindByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentRequest>
			orderByComparator);

	/**
	 * Returns the fulfilment requests before and after the current fulfilment request in the ordered set of fulfilment requests that the user has permission to view where userId = &#63;.
	 *
	 * @param fulfilmentRequestId the primary key of the current fulfilment request
	 * @param userId the user ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next fulfilment request
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	public FulfilmentRequest[] filterFindByUserId_PrevAndNext(
			long fulfilmentRequestId, long userId,
			com.liferay.portal.kernel.util.OrderByComparator<FulfilmentRequest>
				orderByComparator)
		throws NoSuchRequestException;

	/**
	 * Removes all the fulfilment requests where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 */
	public void removeByUserId(long userId);

	/**
	 * Returns the number of fulfilment requests where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching fulfilment requests
	 */
	public int countByUserId(long userId);

	/**
	 * Returns the number of fulfilment requests that the user has permission to view where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching fulfilment requests that the user has permission to view
	 */
	public int filterCountByUserId(long userId);

	/**
	 * Returns the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; or throws a <code>NoSuchRequestException</code> if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching fulfilment request
	 * @throws NoSuchRequestException if a matching fulfilment request could not be found
	 */
	public FulfilmentRequest findByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchRequestException;

	/**
	 * Returns the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	public FulfilmentRequest fetchByC_ERC(
		long companyId, String externalReferenceCode);

	/**
	 * Returns the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	public FulfilmentRequest fetchByC_ERC(
		long companyId, String externalReferenceCode, boolean useFinderCache);

	/**
	 * Removes the fulfilment request where companyId = &#63; and externalReferenceCode = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the fulfilment request that was removed
	 */
	public FulfilmentRequest removeByC_ERC(
			long companyId, String externalReferenceCode)
		throws NoSuchRequestException;

	/**
	 * Returns the number of fulfilment requests where companyId = &#63; and externalReferenceCode = &#63;.
	 *
	 * @param companyId the company ID
	 * @param externalReferenceCode the external reference code
	 * @return the number of matching fulfilment requests
	 */
	public int countByC_ERC(long companyId, String externalReferenceCode);

	/**
	 * Caches the fulfilment request in the entity cache if it is enabled.
	 *
	 * @param fulfilmentRequest the fulfilment request
	 */
	public void cacheResult(FulfilmentRequest fulfilmentRequest);

	/**
	 * Caches the fulfilment requests in the entity cache if it is enabled.
	 *
	 * @param fulfilmentRequests the fulfilment requests
	 */
	public void cacheResult(
		java.util.List<FulfilmentRequest> fulfilmentRequests);

	/**
	 * Creates a new fulfilment request with the primary key. Does not add the fulfilment request to the database.
	 *
	 * @param fulfilmentRequestId the primary key for the new fulfilment request
	 * @return the new fulfilment request
	 */
	public FulfilmentRequest create(long fulfilmentRequestId);

	/**
	 * Removes the fulfilment request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request that was removed
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	public FulfilmentRequest remove(long fulfilmentRequestId)
		throws NoSuchRequestException;

	public FulfilmentRequest updateImpl(FulfilmentRequest fulfilmentRequest);

	/**
	 * Returns the fulfilment request with the primary key or throws a <code>NoSuchRequestException</code> if it could not be found.
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request
	 * @throws NoSuchRequestException if a fulfilment request with the primary key could not be found
	 */
	public FulfilmentRequest findByPrimaryKey(long fulfilmentRequestId)
		throws NoSuchRequestException;

	/**
	 * Returns the fulfilment request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request, or <code>null</code> if a fulfilment request with the primary key could not be found
	 */
	public FulfilmentRequest fetchByPrimaryKey(long fulfilmentRequestId);

	/**
	 * Returns all the fulfilment requests.
	 *
	 * @return the fulfilment requests
	 */
	public java.util.List<FulfilmentRequest> findAll();

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
	public java.util.List<FulfilmentRequest> findAll(int start, int end);

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
	public java.util.List<FulfilmentRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentRequest>
			orderByComparator);

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
	public java.util.List<FulfilmentRequest> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<FulfilmentRequest>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the fulfilment requests from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of fulfilment requests.
	 *
	 * @return the number of fulfilment requests
	 */
	public int countAll();

}