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

package com.liferay.fulfilment.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.fulfilment.exception.NoSuchRequestException;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.FulfilmentRequestLocalServiceUtil;
import com.liferay.fulfilment.service.persistence.FulfilmentRequestPersistence;
import com.liferay.fulfilment.service.persistence.FulfilmentRequestUtil;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.test.ReflectionTestUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class FulfilmentRequestPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.fulfilment.service"));

	@Before
	public void setUp() {
		_persistence = FulfilmentRequestUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FulfilmentRequest> iterator = _fulfilmentRequests.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FulfilmentRequest fulfilmentRequest = _persistence.create(pk);

		Assert.assertNotNull(fulfilmentRequest);

		Assert.assertEquals(fulfilmentRequest.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FulfilmentRequest newFulfilmentRequest = addFulfilmentRequest();

		_persistence.remove(newFulfilmentRequest);

		FulfilmentRequest existingFulfilmentRequest =
			_persistence.fetchByPrimaryKey(
				newFulfilmentRequest.getPrimaryKey());

		Assert.assertNull(existingFulfilmentRequest);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFulfilmentRequest();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FulfilmentRequest newFulfilmentRequest = _persistence.create(pk);

		newFulfilmentRequest.setMvccVersion(RandomTestUtil.nextLong());

		newFulfilmentRequest.setExternalReferenceCode(
			RandomTestUtil.randomString());

		newFulfilmentRequest.setCompanyId(RandomTestUtil.nextLong());

		newFulfilmentRequest.setUserId(RandomTestUtil.nextLong());

		newFulfilmentRequest.setUserName(RandomTestUtil.randomString());

		newFulfilmentRequest.setCreateDate(RandomTestUtil.nextDate());

		newFulfilmentRequest.setModifiedDate(RandomTestUtil.nextDate());

		newFulfilmentRequest.setEndDate(RandomTestUtil.nextDate());

		newFulfilmentRequest.setOriginalFulfilmentRequest(
			RandomTestUtil.randomString());

		newFulfilmentRequest.setInputParameters(RandomTestUtil.randomString());

		newFulfilmentRequest.setOutputParameters(RandomTestUtil.randomString());

		newFulfilmentRequest.setReplyTo(RandomTestUtil.randomString());

		newFulfilmentRequest.setStartDate(RandomTestUtil.nextDate());

		newFulfilmentRequest.setType(RandomTestUtil.randomString());

		newFulfilmentRequest.setWorkflowDefinitionLinkId(
			RandomTestUtil.nextLong());

		newFulfilmentRequest.setStatus(RandomTestUtil.nextInt());

		newFulfilmentRequest.setStatusByUserId(RandomTestUtil.nextLong());

		newFulfilmentRequest.setStatusByUserName(RandomTestUtil.randomString());

		newFulfilmentRequest.setStatusDate(RandomTestUtil.nextDate());

		_fulfilmentRequests.add(_persistence.update(newFulfilmentRequest));

		FulfilmentRequest existingFulfilmentRequest =
			_persistence.findByPrimaryKey(newFulfilmentRequest.getPrimaryKey());

		Assert.assertEquals(
			existingFulfilmentRequest.getMvccVersion(),
			newFulfilmentRequest.getMvccVersion());
		Assert.assertEquals(
			existingFulfilmentRequest.getExternalReferenceCode(),
			newFulfilmentRequest.getExternalReferenceCode());
		Assert.assertEquals(
			existingFulfilmentRequest.getFulfilmentRequestId(),
			newFulfilmentRequest.getFulfilmentRequestId());
		Assert.assertEquals(
			existingFulfilmentRequest.getCompanyId(),
			newFulfilmentRequest.getCompanyId());
		Assert.assertEquals(
			existingFulfilmentRequest.getUserId(),
			newFulfilmentRequest.getUserId());
		Assert.assertEquals(
			existingFulfilmentRequest.getUserName(),
			newFulfilmentRequest.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingFulfilmentRequest.getCreateDate()),
			Time.getShortTimestamp(newFulfilmentRequest.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingFulfilmentRequest.getModifiedDate()),
			Time.getShortTimestamp(newFulfilmentRequest.getModifiedDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingFulfilmentRequest.getEndDate()),
			Time.getShortTimestamp(newFulfilmentRequest.getEndDate()));
		Assert.assertEquals(
			existingFulfilmentRequest.getOriginalFulfilmentRequest(),
			newFulfilmentRequest.getOriginalFulfilmentRequest());
		Assert.assertEquals(
			existingFulfilmentRequest.getInputParameters(),
			newFulfilmentRequest.getInputParameters());
		Assert.assertEquals(
			existingFulfilmentRequest.getOutputParameters(),
			newFulfilmentRequest.getOutputParameters());
		Assert.assertEquals(
			existingFulfilmentRequest.getReplyTo(),
			newFulfilmentRequest.getReplyTo());
		Assert.assertEquals(
			Time.getShortTimestamp(existingFulfilmentRequest.getStartDate()),
			Time.getShortTimestamp(newFulfilmentRequest.getStartDate()));
		Assert.assertEquals(
			existingFulfilmentRequest.getType(),
			newFulfilmentRequest.getType());
		Assert.assertEquals(
			existingFulfilmentRequest.getWorkflowDefinitionLinkId(),
			newFulfilmentRequest.getWorkflowDefinitionLinkId());
		Assert.assertEquals(
			existingFulfilmentRequest.getStatus(),
			newFulfilmentRequest.getStatus());
		Assert.assertEquals(
			existingFulfilmentRequest.getStatusByUserId(),
			newFulfilmentRequest.getStatusByUserId());
		Assert.assertEquals(
			existingFulfilmentRequest.getStatusByUserName(),
			newFulfilmentRequest.getStatusByUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingFulfilmentRequest.getStatusDate()),
			Time.getShortTimestamp(newFulfilmentRequest.getStatusDate()));
	}

	@Test
	public void testCountByUserId() throws Exception {
		_persistence.countByUserId(RandomTestUtil.nextLong());

		_persistence.countByUserId(0L);
	}

	@Test
	public void testCountByC_ERC() throws Exception {
		_persistence.countByC_ERC(RandomTestUtil.nextLong(), "");

		_persistence.countByC_ERC(0L, "null");

		_persistence.countByC_ERC(0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FulfilmentRequest newFulfilmentRequest = addFulfilmentRequest();

		FulfilmentRequest existingFulfilmentRequest =
			_persistence.findByPrimaryKey(newFulfilmentRequest.getPrimaryKey());

		Assert.assertEquals(existingFulfilmentRequest, newFulfilmentRequest);
	}

	@Test(expected = NoSuchRequestException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<FulfilmentRequest> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"FulfilmentRequest", "mvccVersion", true, "externalReferenceCode",
			true, "fulfilmentRequestId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"endDate", true, "replyTo", true, "startDate", true, "type", true,
			"workflowDefinitionLinkId", true, "status", true, "statusByUserId",
			true, "statusByUserName", true, "statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FulfilmentRequest newFulfilmentRequest = addFulfilmentRequest();

		FulfilmentRequest existingFulfilmentRequest =
			_persistence.fetchByPrimaryKey(
				newFulfilmentRequest.getPrimaryKey());

		Assert.assertEquals(existingFulfilmentRequest, newFulfilmentRequest);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FulfilmentRequest missingFulfilmentRequest =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingFulfilmentRequest);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		FulfilmentRequest newFulfilmentRequest1 = addFulfilmentRequest();
		FulfilmentRequest newFulfilmentRequest2 = addFulfilmentRequest();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFulfilmentRequest1.getPrimaryKey());
		primaryKeys.add(newFulfilmentRequest2.getPrimaryKey());

		Map<Serializable, FulfilmentRequest> fulfilmentRequests =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, fulfilmentRequests.size());
		Assert.assertEquals(
			newFulfilmentRequest1,
			fulfilmentRequests.get(newFulfilmentRequest1.getPrimaryKey()));
		Assert.assertEquals(
			newFulfilmentRequest2,
			fulfilmentRequests.get(newFulfilmentRequest2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FulfilmentRequest> fulfilmentRequests =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fulfilmentRequests.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		FulfilmentRequest newFulfilmentRequest = addFulfilmentRequest();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFulfilmentRequest.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FulfilmentRequest> fulfilmentRequests =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fulfilmentRequests.size());
		Assert.assertEquals(
			newFulfilmentRequest,
			fulfilmentRequests.get(newFulfilmentRequest.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FulfilmentRequest> fulfilmentRequests =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fulfilmentRequests.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		FulfilmentRequest newFulfilmentRequest = addFulfilmentRequest();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFulfilmentRequest.getPrimaryKey());

		Map<Serializable, FulfilmentRequest> fulfilmentRequests =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fulfilmentRequests.size());
		Assert.assertEquals(
			newFulfilmentRequest,
			fulfilmentRequests.get(newFulfilmentRequest.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			FulfilmentRequestLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<FulfilmentRequest>() {

				@Override
				public void performAction(FulfilmentRequest fulfilmentRequest) {
					Assert.assertNotNull(fulfilmentRequest);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		FulfilmentRequest newFulfilmentRequest = addFulfilmentRequest();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FulfilmentRequest.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fulfilmentRequestId",
				newFulfilmentRequest.getFulfilmentRequestId()));

		List<FulfilmentRequest> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		FulfilmentRequest existingFulfilmentRequest = result.get(0);

		Assert.assertEquals(existingFulfilmentRequest, newFulfilmentRequest);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FulfilmentRequest.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fulfilmentRequestId", RandomTestUtil.nextLong()));

		List<FulfilmentRequest> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		FulfilmentRequest newFulfilmentRequest = addFulfilmentRequest();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FulfilmentRequest.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("fulfilmentRequestId"));

		Object newFulfilmentRequestId =
			newFulfilmentRequest.getFulfilmentRequestId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"fulfilmentRequestId", new Object[] {newFulfilmentRequestId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFulfilmentRequestId = result.get(0);

		Assert.assertEquals(
			existingFulfilmentRequestId, newFulfilmentRequestId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FulfilmentRequest.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("fulfilmentRequestId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"fulfilmentRequestId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		FulfilmentRequest newFulfilmentRequest = addFulfilmentRequest();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(
				newFulfilmentRequest.getPrimaryKey()));
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromDatabase()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(true);
	}

	@Test
	public void testResetOriginalValuesWithDynamicQueryLoadFromSession()
		throws Exception {

		_testResetOriginalValuesWithDynamicQuery(false);
	}

	private void _testResetOriginalValuesWithDynamicQuery(boolean clearSession)
		throws Exception {

		FulfilmentRequest newFulfilmentRequest = addFulfilmentRequest();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FulfilmentRequest.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fulfilmentRequestId",
				newFulfilmentRequest.getFulfilmentRequestId()));

		List<FulfilmentRequest> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(FulfilmentRequest fulfilmentRequest) {
		Assert.assertEquals(
			Long.valueOf(fulfilmentRequest.getCompanyId()),
			ReflectionTestUtil.<Long>invoke(
				fulfilmentRequest, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "companyId"));
		Assert.assertEquals(
			fulfilmentRequest.getExternalReferenceCode(),
			ReflectionTestUtil.invoke(
				fulfilmentRequest, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "externalReferenceCode"));
	}

	protected FulfilmentRequest addFulfilmentRequest() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FulfilmentRequest fulfilmentRequest = _persistence.create(pk);

		fulfilmentRequest.setMvccVersion(RandomTestUtil.nextLong());

		fulfilmentRequest.setExternalReferenceCode(
			RandomTestUtil.randomString());

		fulfilmentRequest.setCompanyId(RandomTestUtil.nextLong());

		fulfilmentRequest.setUserId(RandomTestUtil.nextLong());

		fulfilmentRequest.setUserName(RandomTestUtil.randomString());

		fulfilmentRequest.setCreateDate(RandomTestUtil.nextDate());

		fulfilmentRequest.setModifiedDate(RandomTestUtil.nextDate());

		fulfilmentRequest.setEndDate(RandomTestUtil.nextDate());

		fulfilmentRequest.setOriginalFulfilmentRequest(
			RandomTestUtil.randomString());

		fulfilmentRequest.setInputParameters(RandomTestUtil.randomString());

		fulfilmentRequest.setOutputParameters(RandomTestUtil.randomString());

		fulfilmentRequest.setReplyTo(RandomTestUtil.randomString());

		fulfilmentRequest.setStartDate(RandomTestUtil.nextDate());

		fulfilmentRequest.setType(RandomTestUtil.randomString());

		fulfilmentRequest.setWorkflowDefinitionLinkId(
			RandomTestUtil.nextLong());

		fulfilmentRequest.setStatus(RandomTestUtil.nextInt());

		fulfilmentRequest.setStatusByUserId(RandomTestUtil.nextLong());

		fulfilmentRequest.setStatusByUserName(RandomTestUtil.randomString());

		fulfilmentRequest.setStatusDate(RandomTestUtil.nextDate());

		_fulfilmentRequests.add(_persistence.update(fulfilmentRequest));

		return fulfilmentRequest;
	}

	private List<FulfilmentRequest> _fulfilmentRequests =
		new ArrayList<FulfilmentRequest>();
	private FulfilmentRequestPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}