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

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link FulfilmentRequestLocalService}.
 *
 * @author Riccardo Alberti
 * @see FulfilmentRequestLocalService
 * @generated
 */
public class FulfilmentRequestLocalServiceWrapper
	implements FulfilmentRequestLocalService,
			   ServiceWrapper<FulfilmentRequestLocalService> {

	public FulfilmentRequestLocalServiceWrapper() {
		this(null);
	}

	public FulfilmentRequestLocalServiceWrapper(
		FulfilmentRequestLocalService fulfilmentRequestLocalService) {

		_fulfilmentRequestLocalService = fulfilmentRequestLocalService;
	}

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
	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest addFulfilmentRequest(
		com.liferay.fulfilment.model.FulfilmentRequest fulfilmentRequest) {

		return _fulfilmentRequestLocalService.addFulfilmentRequest(
			fulfilmentRequest);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest addFulfilmentRequest(
			String externalReferenceCode, long userId,
			String originalFulfilmentRequest, String inputParameters,
			String replyTo, String type,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.addFulfilmentRequest(
			externalReferenceCode, userId, originalFulfilmentRequest,
			inputParameters, replyTo, type, serviceContext);
	}

	/**
	 * Creates a new fulfilment request with the primary key. Does not add the fulfilment request to the database.
	 *
	 * @param fulfilmentRequestId the primary key for the new fulfilment request
	 * @return the new fulfilment request
	 */
	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
		createFulfilmentRequest(long fulfilmentRequestId) {

		return _fulfilmentRequestLocalService.createFulfilmentRequest(
			fulfilmentRequestId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.createPersistedModel(
			primaryKeyObj);
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
	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
		deleteFulfilmentRequest(
			com.liferay.fulfilment.model.FulfilmentRequest fulfilmentRequest) {

		return _fulfilmentRequestLocalService.deleteFulfilmentRequest(
			fulfilmentRequest);
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
	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
			deleteFulfilmentRequest(long fulfilmentRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.deleteFulfilmentRequest(
			fulfilmentRequestId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _fulfilmentRequestLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _fulfilmentRequestLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _fulfilmentRequestLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _fulfilmentRequestLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _fulfilmentRequestLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _fulfilmentRequestLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _fulfilmentRequestLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _fulfilmentRequestLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
		fetchFulfilmentRequest(long fulfilmentRequestId) {

		return _fulfilmentRequestLocalService.fetchFulfilmentRequest(
			fulfilmentRequestId);
	}

	/**
	 * Returns the fulfilment request with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the fulfilment request's external reference code
	 * @return the matching fulfilment request, or <code>null</code> if a matching fulfilment request could not be found
	 */
	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
		fetchFulfilmentRequestByExternalReferenceCode(
			long companyId, String externalReferenceCode) {

		return _fulfilmentRequestLocalService.
			fetchFulfilmentRequestByExternalReferenceCode(
				companyId, externalReferenceCode);
	}

	/**
	 * @deprecated As of Cavanaugh (7.4.x), replaced by {@link #fetchFulfilmentRequestByExternalReferenceCode(long, String)}
	 */
	@Deprecated
	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
		fetchFulfilmentRequestByReferenceCode(
			long companyId, String externalReferenceCode) {

		return _fulfilmentRequestLocalService.
			fetchFulfilmentRequestByReferenceCode(
				companyId, externalReferenceCode);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _fulfilmentRequestLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the fulfilment request with the primary key.
	 *
	 * @param fulfilmentRequestId the primary key of the fulfilment request
	 * @return the fulfilment request
	 * @throws PortalException if a fulfilment request with the primary key could not be found
	 */
	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest getFulfilmentRequest(
			long fulfilmentRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.getFulfilmentRequest(
			fulfilmentRequestId);
	}

	/**
	 * Returns the fulfilment request with the matching external reference code and company.
	 *
	 * @param companyId the primary key of the company
	 * @param externalReferenceCode the fulfilment request's external reference code
	 * @return the matching fulfilment request
	 * @throws PortalException if a matching fulfilment request could not be found
	 */
	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
			getFulfilmentRequestByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.
			getFulfilmentRequestByExternalReferenceCode(
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
	@Override
	public java.util.List<com.liferay.fulfilment.model.FulfilmentRequest>
		getFulfilmentRequests(int start, int end) {

		return _fulfilmentRequestLocalService.getFulfilmentRequests(start, end);
	}

	/**
	 * Returns the number of fulfilment requests.
	 *
	 * @return the number of fulfilment requests
	 */
	@Override
	public int getFulfilmentRequestsCount() {
		return _fulfilmentRequestLocalService.getFulfilmentRequestsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _fulfilmentRequestLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _fulfilmentRequestLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest startWorkflowInstance(
			long userId,
			com.liferay.fulfilment.model.FulfilmentRequest fulfilmentRequest,
			com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.startWorkflowInstance(
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
	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
		updateFulfilmentRequest(
			com.liferay.fulfilment.model.FulfilmentRequest fulfilmentRequest) {

		return _fulfilmentRequestLocalService.updateFulfilmentRequest(
			fulfilmentRequest);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
			updateFulfilmentRequest(
				long userId, long fulfilmentRequestId,
				String originalFulfilmentRequest, String inputParameters,
				String replyTo, String type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.updateFulfilmentRequest(
			userId, fulfilmentRequestId, originalFulfilmentRequest,
			inputParameters, replyTo, type, serviceContext);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
			updateFulfilmentRequest(
				long fulfilmentRequestId, String outputParameters)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.updateFulfilmentRequest(
			fulfilmentRequestId, outputParameters);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest updateStatus(
			long userId, long fulfilmentRequestId, int status,
			com.liferay.portal.kernel.service.ServiceContext serviceContext,
			java.util.Map<String, java.io.Serializable> workflowContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.updateStatus(
			userId, fulfilmentRequestId, status, serviceContext,
			workflowContext);
	}

	@Override
	public com.liferay.fulfilment.model.FulfilmentRequest
			updateWorkflowDefinitionLink(
				long fulfilmentRequestId, long workflowDefinitionLinkId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _fulfilmentRequestLocalService.updateWorkflowDefinitionLink(
			fulfilmentRequestId, workflowDefinitionLinkId);
	}

	@Override
	public FulfilmentRequestLocalService getWrappedService() {
		return _fulfilmentRequestLocalService;
	}

	@Override
	public void setWrappedService(
		FulfilmentRequestLocalService fulfilmentRequestLocalService) {

		_fulfilmentRequestLocalService = fulfilmentRequestLocalService;
	}

	private FulfilmentRequestLocalService _fulfilmentRequestLocalService;

}