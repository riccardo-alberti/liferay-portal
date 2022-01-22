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

package com.liferay.commerce.qualification.service;

import com.liferay.commerce.qualification.model.CommerceQualificationRel;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for CommerceQualificationRel. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Riccardo Alberti
 * @see CommerceQualificationRelLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CommerceQualificationRelLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add custom service methods to <code>com.liferay.commerce.qualification.service.impl.CommerceQualificationRelLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface. Consume the commerce qualification rel local service via injection or a <code>org.osgi.util.tracker.ServiceTracker</code>. Use {@link CommerceQualificationRelLocalServiceUtil} if injection and service tracking are not available.
	 */

	/**
	 * Adds the commerce qualification rel to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceQualificationRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceQualificationRel the commerce qualification rel
	 * @return the commerce qualification rel that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CommerceQualificationRel addCommerceQualificationRel(
		CommerceQualificationRel commerceQualificationRel);

	public CommerceQualificationRel addCommerceQualificationRel(
			long userId, String sourceClassName, long sourceClassPK,
			String targetClassName, long targetClassPK)
		throws PortalException;

	/**
	 * Creates a new commerce qualification rel with the primary key. Does not add the commerce qualification rel to the database.
	 *
	 * @param commerceQualificationRelId the primary key for the new commerce qualification rel
	 * @return the new commerce qualification rel
	 */
	@Transactional(enabled = false)
	public CommerceQualificationRel createCommerceQualificationRel(
		long commerceQualificationRelId);

	/**
	 * @throws PortalException
	 */
	public PersistedModel createPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	/**
	 * Deletes the commerce qualification rel from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceQualificationRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceQualificationRel the commerce qualification rel
	 * @return the commerce qualification rel that was removed
	 * @throws PortalException
	 */
	@Indexable(type = IndexableType.DELETE)
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceQualificationRel deleteCommerceQualificationRel(
			CommerceQualificationRel commerceQualificationRel)
		throws PortalException;

	/**
	 * Deletes the commerce qualification rel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceQualificationRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel that was removed
	 * @throws PortalException if a commerce qualification rel with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public CommerceQualificationRel deleteCommerceQualificationRel(
			long commerceQualificationRelId)
		throws PortalException;

	public void deleteCommerceQualificationRels(
			String sourceClassName, long sourceClassPK)
		throws PortalException;

	public void deleteCommerceQualificationRels(
			String sourceClassName, long sourceClassPK, String targetClassName)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> T dslQuery(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int dslQueryCount(DSLQuery dslQuery);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualification.model.impl.CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualification.model.impl.CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceQualificationRel fetchCommerceQualificationRel(
		long commerceQualificationRelId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceQualificationRel fetchCommerceQualificationRel(
		String sourceClassName, long sourceClassPK, String targetClassName,
		long targetClassPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

	/**
	 * Returns the commerce qualification rel with the primary key.
	 *
	 * @param commerceQualificationRelId the primary key of the commerce qualification rel
	 * @return the commerce qualification rel
	 * @throws PortalException if a commerce qualification rel with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CommerceQualificationRel getCommerceQualificationRel(
			long commerceQualificationRelId)
		throws PortalException;

	/**
	 * Returns a range of all the commerce qualification rels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.liferay.commerce.qualification.model.impl.CommerceQualificationRelModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of commerce qualification rels
	 * @param end the upper bound of the range of commerce qualification rels (not inclusive)
	 * @return the range of commerce qualification rels
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceQualificationRel> getCommerceQualificationRels(
		int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceQualificationRel> getCommerceQualificationRels(
		String sourceClassName, long sourceClassPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceQualificationRel> getCommerceQualificationRels(
		String sourceClassName, long sourceClassPK, int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator);

	/**
	 * Returns the number of commerce qualification rels.
	 *
	 * @return the number of commerce qualification rels
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceQualificationRelsCount();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCommerceQualificationRelsCount(
		String sourceClassName, long sourceClassPK);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	/**
	 * @throws PortalException
	 */
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceQualificationRel> getSourceCommerceQualificationRels(
		long companyId, String sourceClassName,
		Map<String, Long[]> targetFilters);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CommerceQualificationRel> getTargetCommerceQualificationRels(
		String keywords, String sourceClassName, long sourceClassPK,
		String targetClassName, int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getTargetCommerceQualificationRelsCount(
		String keywords, String sourceClassName, long sourceClassPK,
		String targetClassName);

	/**
	 * Updates the commerce qualification rel in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect CommerceQualificationRelLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param commerceQualificationRel the commerce qualification rel
	 * @return the commerce qualification rel that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CommerceQualificationRel updateCommerceQualificationRel(
		CommerceQualificationRel commerceQualificationRel);

}