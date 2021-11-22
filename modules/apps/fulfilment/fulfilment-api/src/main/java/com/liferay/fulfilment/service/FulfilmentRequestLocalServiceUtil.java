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

package com.liferay.fulfilment.service;

import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

/**
 * Provides the local service utility for FulfilmentRequest. This utility wraps
 * <code>com.liferay.fulfilment.service.impl.FulfilmentRequestLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Riccardo Alberti
 * @see FulfilmentRequestLocalService
 * @generated
 */
public class FulfilmentRequestLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.fulfilment.service.impl.FulfilmentRequestLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the fulfilment request to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FulfilmentRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fulfilmentRequest the fulfilment request
	 * @return the fulfilment request that was added
	 */
	public static FulfilmentRequest addFulfilmentRequest(
		FulfilmentRequest fulfilmentRequest) {

		return getService().addFulfilmentRequest(fulfilmentRequest);
	}

	public static FulfilmentRequest addFulfilmentRequest(
			String externalReferenceCode, long userId,
			String originalFulfilmentRequest, String inputParameters,
			String replyTo, String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().addFulfilmentRequest(
			externalReferenceCode, userId, originalFulfilmentRequest,
			inputParameters, replyTo, type, serviceContext);
	}

	/**
	 * Creates a new fulfilment request with the primary key. Does not add the fulfilment request to the database.
	 *
	 * @param fulfilmentRequestId the primary key for the new fulfilment request
	 * @return the new fulfilment request
	 */
	public static FulfilmentRequest createFulfilmentRequest(
		long fulfilmentRequestId) {

		return getService().createFulfilmentRequest(fulfilmentRequestId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Deletes the fulfilment request from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FulfilmentRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fulfilmentRequest the fulfilment request
	 * @return the fulfilment request that was removed
	 */
	public static FulfilmentRequest deleteFulfilmentRequest(
		FulfilmentRequest fulfilmentRequest) {

		return getService().deleteFulfilmentRequest(fulfilmentRequest);
	}

	/**
	 * Deletes the fulfilment request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FulfilmentRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request that was removed
	 * @throws PortalException if a fulfilment request with the primary key could not be found
	 */
	public static FulfilmentRequest deleteFulfilmentRequest(
			long fulfilmentRequestId)
		throws PortalException {

		return getService().deleteFulfilmentRequest(fulfilmentRequestId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fulfilment.model.impl.FulfilmentRequestModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fulfilment.model.impl.FulfilmentRequestModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static FulfilmentRequest fetchFulfilmentRequest(
		long fulfilmentRequestId) {

		return getService().fetchFulfilmentRequest(fulfilmentRequestId);
	}

	/**
	 * Returns the fulfilment request with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the fulfilment request's external reference code
	 * @return the matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	public static FulfilmentRequest
		fetchFulfilmentRequestByExternalReferenceCode(
			long companyId, String externalReferenceCode) {

		return getService().fetchFulfilmentRequestByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchFulfilmentRequestByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	public static FulfilmentRequest fetchFulfilmentRequestByReferenceCode(
		long companyId, String externalReferenceCode) {

		return getService().fetchFulfilmentRequestByReferenceCode(
			companyId, externalReferenceCode);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the fulfilment request with the primary key.
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request
	 * @throws PortalException if a fulfilment request with the primary key could not be found
	 */
	public static FulfilmentRequest getFulfilmentRequest(
			long fulfilmentRequestId)
		throws PortalException {

		return getService().getFulfilmentRequest(fulfilmentRequestId);
	}

	/**
	 * Returns the fulfilment request with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the fulfilment request's external reference code
	 * @return the matching fulfilment request
	 * @throws PortalException if a matching fulfilment request could not be found
	 */
	public static FulfilmentRequest getFulfilmentRequestByExternalReferenceCode(
			long companyId, String externalReferenceCode)
		throws PortalException {

		return getService().getFulfilmentRequestByExternalReferenceCode(
			companyId, externalReferenceCode);
	}

	/**
	 * Returns a range of all the fulfilment requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.fulfilment.model.impl.FulfilmentRequestModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of fulfilment requests
	 * @param end the upper bound of the range of fulfilment requests (not inclusive)
	 * @return the range of fulfilment requests
	 */
	public static List<FulfilmentRequest> getFulfilmentRequests(
		int start, int end) {

		return getService().getFulfilmentRequests(start, end);
	}

	/**
	 * Returns the number of fulfilment requests.
	 *
	 * @return the number of fulfilment requests
	 */
	public static int getFulfilmentRequestsCount() {
		return getService().getFulfilmentRequestsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static FulfilmentRequest startWorkflowInstance(
			long userId, FulfilmentRequest fulfilmentRequest,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().startWorkflowInstance(
			userId, fulfilmentRequest, serviceContext);
	}

	/**
	 * Updates the fulfilment request in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect FulfilmentRequestLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param fulfilmentRequest the fulfilment request
	 * @return the fulfilment request that was updated
	 */
	public static FulfilmentRequest updateFulfilmentRequest(
		FulfilmentRequest fulfilmentRequest) {

		return getService().updateFulfilmentRequest(fulfilmentRequest);
	}

	public static FulfilmentRequest updateFulfilmentRequest(
			long userId, long fulfilmentRequestId,
			String originalFulfilmentRequest, String inputParameters,
			String replyTo, String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws PortalException {

		return getService().updateFulfilmentRequest(
			userId, fulfilmentRequestId, originalFulfilmentRequest,
			inputParameters, replyTo, type, serviceContext);
	}

	public static FulfilmentRequest updateFulfilmentRequest(
			long fulfilmentRequestId, String outputParameters)
		throws PortalException {

		return getService().updateFulfilmentRequest(
			fulfilmentRequestId, outputParameters);
	}

	public static FulfilmentRequest updateStatus(
			long userId, long fulfilmentRequestId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			Map<String, Serializable> workflowContext)
		throws PortalException {

		return getService().updateStatus(
			userId, fulfilmentRequestId, status, serviceContext,
			workflowContext);
	}

	public static FulfilmentRequest updateWorkflowDefinitionLink(
			long fulfilmentRequestId, long workflowDefinitionLinkId)
		throws PortalException {

		return getService().updateWorkflowDefinitionLink(
			fulfilmentRequestId, workflowDefinitionLinkId);
	}

	public static FulfilmentRequestLocalService getService() {
		return _service;
	}

	private static volatile FulfilmentRequestLocalService _service;

}