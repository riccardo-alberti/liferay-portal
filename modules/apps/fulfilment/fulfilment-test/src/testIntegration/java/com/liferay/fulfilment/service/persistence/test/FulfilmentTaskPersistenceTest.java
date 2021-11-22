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
import com.liferay.fulfilment.exception.NoSuchTaskException;
import com.liferay.fulfilment.model.FulfilmentTask;
import com.liferay.fulfilment.service.FulfilmentTaskLocalServiceUtil;
import com.liferay.fulfilment.service.persistence.FulfilmentTaskPersistence;
import com.liferay.fulfilment.service.persistence.FulfilmentTaskUtil;
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
public class FulfilmentTaskPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED, "com.liferay.fulfilment.service"));

	@Before
	public void setUp() {
		_persistence = FulfilmentTaskUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<FulfilmentTask> iterator = _fulfilmentTasks.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FulfilmentTask fulfilmentTask = _persistence.create(pk);

		Assert.assertNotNull(fulfilmentTask);

		Assert.assertEquals(fulfilmentTask.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		FulfilmentTask newFulfilmentTask = addFulfilmentTask();

		_persistence.remove(newFulfilmentTask);

		FulfilmentTask existingFulfilmentTask = _persistence.fetchByPrimaryKey(
			newFulfilmentTask.getPrimaryKey());

		Assert.assertNull(existingFulfilmentTask);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addFulfilmentTask();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FulfilmentTask newFulfilmentTask = _persistence.create(pk);

		newFulfilmentTask.setMvccVersion(RandomTestUtil.nextLong());

		newFulfilmentTask.setCompanyId(RandomTestUtil.nextLong());

		newFulfilmentTask.setUserId(RandomTestUtil.nextLong());

		newFulfilmentTask.setUserName(RandomTestUtil.randomString());

		newFulfilmentTask.setCreateDate(RandomTestUtil.nextDate());

		newFulfilmentTask.setModifiedDate(RandomTestUtil.nextDate());

		newFulfilmentTask.setCorrelationId(RandomTestUtil.nextLong());

		newFulfilmentTask.setEndDate(RandomTestUtil.nextDate());

		newFulfilmentTask.setFulfilmentRequestId(RandomTestUtil.nextLong());

		newFulfilmentTask.setIndex(RandomTestUtil.nextLong());

		newFulfilmentTask.setInputParameters(RandomTestUtil.randomString());

		newFulfilmentTask.setOutputParameters(RandomTestUtil.randomString());

		newFulfilmentTask.setStartDate(RandomTestUtil.nextDate());

		newFulfilmentTask.setType(RandomTestUtil.randomString());

		newFulfilmentTask.setStatus(RandomTestUtil.nextInt());

		newFulfilmentTask.setStatusByUserId(RandomTestUtil.nextLong());

		newFulfilmentTask.setStatusByUserName(RandomTestUtil.randomString());

		newFulfilmentTask.setStatusDate(RandomTestUtil.nextDate());

		_fulfilmentTasks.add(_persistence.update(newFulfilmentTask));

		FulfilmentTask existingFulfilmentTask = _persistence.findByPrimaryKey(
			newFulfilmentTask.getPrimaryKey());

		Assert.assertEquals(
			existingFulfilmentTask.getMvccVersion(),
			newFulfilmentTask.getMvccVersion());
		Assert.assertEquals(
			existingFulfilmentTask.getFulfilmentTaskId(),
			newFulfilmentTask.getFulfilmentTaskId());
		Assert.assertEquals(
			existingFulfilmentTask.getCompanyId(),
			newFulfilmentTask.getCompanyId());
		Assert.assertEquals(
			existingFulfilmentTask.getUserId(), newFulfilmentTask.getUserId());
		Assert.assertEquals(
			existingFulfilmentTask.getUserName(),
			newFulfilmentTask.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingFulfilmentTask.getCreateDate()),
			Time.getShortTimestamp(newFulfilmentTask.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(existingFulfilmentTask.getModifiedDate()),
			Time.getShortTimestamp(newFulfilmentTask.getModifiedDate()));
		Assert.assertEquals(
			existingFulfilmentTask.getCorrelationId(),
			newFulfilmentTask.getCorrelationId());
		Assert.assertEquals(
			Time.getShortTimestamp(existingFulfilmentTask.getEndDate()),
			Time.getShortTimestamp(newFulfilmentTask.getEndDate()));
		Assert.assertEquals(
			existingFulfilmentTask.getFulfilmentRequestId(),
			newFulfilmentTask.getFulfilmentRequestId());
		Assert.assertEquals(
			existingFulfilmentTask.getIndex(), newFulfilmentTask.getIndex());
		Assert.assertEquals(
			existingFulfilmentTask.getInputParameters(),
			newFulfilmentTask.getInputParameters());
		Assert.assertEquals(
			existingFulfilmentTask.getOutputParameters(),
			newFulfilmentTask.getOutputParameters());
		Assert.assertEquals(
			Time.getShortTimestamp(existingFulfilmentTask.getStartDate()),
			Time.getShortTimestamp(newFulfilmentTask.getStartDate()));
		Assert.assertEquals(
			existingFulfilmentTask.getType(), newFulfilmentTask.getType());
		Assert.assertEquals(
			existingFulfilmentTask.getStatus(), newFulfilmentTask.getStatus());
		Assert.assertEquals(
			existingFulfilmentTask.getStatusByUserId(),
			newFulfilmentTask.getStatusByUserId());
		Assert.assertEquals(
			existingFulfilmentTask.getStatusByUserName(),
			newFulfilmentTask.getStatusByUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(existingFulfilmentTask.getStatusDate()),
			Time.getShortTimestamp(newFulfilmentTask.getStatusDate()));
	}

	@Test
	public void testCountByCorrelationId() throws Exception {
		_persistence.countByCorrelationId(RandomTestUtil.nextLong());

		_persistence.countByCorrelationId(0L);
	}

	@Test
	public void testCountByFulfilmentRequestId() throws Exception {
		_persistence.countByFulfilmentRequestId(RandomTestUtil.nextLong());

		_persistence.countByFulfilmentRequestId(0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		FulfilmentTask newFulfilmentTask = addFulfilmentTask();

		FulfilmentTask existingFulfilmentTask = _persistence.findByPrimaryKey(
			newFulfilmentTask.getPrimaryKey());

		Assert.assertEquals(existingFulfilmentTask, newFulfilmentTask);
	}

	@Test(expected = NoSuchTaskException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<FulfilmentTask> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create(
			"FulfilmentTask", "mvccVersion", true, "fulfilmentTaskId", true,
			"companyId", true, "userId", true, "userName", true, "createDate",
			true, "modifiedDate", true, "correlationId", true, "endDate", true,
			"fulfilmentRequestId", true, "index", true, "startDate", true,
			"type", true, "status", true, "statusByUserId", true,
			"statusByUserName", true, "statusDate", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		FulfilmentTask newFulfilmentTask = addFulfilmentTask();

		FulfilmentTask existingFulfilmentTask = _persistence.fetchByPrimaryKey(
			newFulfilmentTask.getPrimaryKey());

		Assert.assertEquals(existingFulfilmentTask, newFulfilmentTask);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FulfilmentTask missingFulfilmentTask = _persistence.fetchByPrimaryKey(
			pk);

		Assert.assertNull(missingFulfilmentTask);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		FulfilmentTask newFulfilmentTask1 = addFulfilmentTask();
		FulfilmentTask newFulfilmentTask2 = addFulfilmentTask();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFulfilmentTask1.getPrimaryKey());
		primaryKeys.add(newFulfilmentTask2.getPrimaryKey());

		Map<Serializable, FulfilmentTask> fulfilmentTasks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, fulfilmentTasks.size());
		Assert.assertEquals(
			newFulfilmentTask1,
			fulfilmentTasks.get(newFulfilmentTask1.getPrimaryKey()));
		Assert.assertEquals(
			newFulfilmentTask2,
			fulfilmentTasks.get(newFulfilmentTask2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, FulfilmentTask> fulfilmentTasks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fulfilmentTasks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		FulfilmentTask newFulfilmentTask = addFulfilmentTask();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFulfilmentTask.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, FulfilmentTask> fulfilmentTasks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fulfilmentTasks.size());
		Assert.assertEquals(
			newFulfilmentTask,
			fulfilmentTasks.get(newFulfilmentTask.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, FulfilmentTask> fulfilmentTasks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(fulfilmentTasks.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		FulfilmentTask newFulfilmentTask = addFulfilmentTask();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newFulfilmentTask.getPrimaryKey());

		Map<Serializable, FulfilmentTask> fulfilmentTasks =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, fulfilmentTasks.size());
		Assert.assertEquals(
			newFulfilmentTask,
			fulfilmentTasks.get(newFulfilmentTask.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			FulfilmentTaskLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod<FulfilmentTask>() {

				@Override
				public void performAction(FulfilmentTask fulfilmentTask) {
					Assert.assertNotNull(fulfilmentTask);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		FulfilmentTask newFulfilmentTask = addFulfilmentTask();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FulfilmentTask.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fulfilmentTaskId", newFulfilmentTask.getFulfilmentTaskId()));

		List<FulfilmentTask> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(1, result.size());

		FulfilmentTask existingFulfilmentTask = result.get(0);

		Assert.assertEquals(existingFulfilmentTask, newFulfilmentTask);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FulfilmentTask.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fulfilmentTaskId", RandomTestUtil.nextLong()));

		List<FulfilmentTask> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		FulfilmentTask newFulfilmentTask = addFulfilmentTask();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FulfilmentTask.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("fulfilmentTaskId"));

		Object newFulfilmentTaskId = newFulfilmentTask.getFulfilmentTaskId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"fulfilmentTaskId", new Object[] {newFulfilmentTaskId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingFulfilmentTaskId = result.get(0);

		Assert.assertEquals(existingFulfilmentTaskId, newFulfilmentTaskId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FulfilmentTask.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("fulfilmentTaskId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"fulfilmentTaskId", new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		FulfilmentTask newFulfilmentTask = addFulfilmentTask();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(newFulfilmentTask.getPrimaryKey()));
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

		FulfilmentTask newFulfilmentTask = addFulfilmentTask();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			FulfilmentTask.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"fulfilmentTaskId", newFulfilmentTask.getFulfilmentTaskId()));

		List<FulfilmentTask> result = _persistence.findWithDynamicQuery(
			dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(FulfilmentTask fulfilmentTask) {
		Assert.assertEquals(
			Long.valueOf(fulfilmentTask.getCorrelationId()),
			ReflectionTestUtil.<Long>invoke(
				fulfilmentTask, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "correlationId"));
	}

	protected FulfilmentTask addFulfilmentTask() throws Exception {
		long pk = RandomTestUtil.nextLong();

		FulfilmentTask fulfilmentTask = _persistence.create(pk);

		fulfilmentTask.setMvccVersion(RandomTestUtil.nextLong());

		fulfilmentTask.setCompanyId(RandomTestUtil.nextLong());

		fulfilmentTask.setUserId(RandomTestUtil.nextLong());

		fulfilmentTask.setUserName(RandomTestUtil.randomString());

		fulfilmentTask.setCreateDate(RandomTestUtil.nextDate());

		fulfilmentTask.setModifiedDate(RandomTestUtil.nextDate());

		fulfilmentTask.setCorrelationId(RandomTestUtil.nextLong());

		fulfilmentTask.setEndDate(RandomTestUtil.nextDate());

		fulfilmentTask.setFulfilmentRequestId(RandomTestUtil.nextLong());

		fulfilmentTask.setIndex(RandomTestUtil.nextLong());

		fulfilmentTask.setInputParameters(RandomTestUtil.randomString());

		fulfilmentTask.setOutputParameters(RandomTestUtil.randomString());

		fulfilmentTask.setStartDate(RandomTestUtil.nextDate());

		fulfilmentTask.setType(RandomTestUtil.randomString());

		fulfilmentTask.setStatus(RandomTestUtil.nextInt());

		fulfilmentTask.setStatusByUserId(RandomTestUtil.nextLong());

		fulfilmentTask.setStatusByUserName(RandomTestUtil.randomString());

		fulfilmentTask.setStatusDate(RandomTestUtil.nextDate());

		_fulfilmentTasks.add(_persistence.update(fulfilmentTask));

		return fulfilmentTask;
	}

	private List<FulfilmentTask> _fulfilmentTasks =
		new ArrayList<FulfilmentTask>();
	private FulfilmentTaskPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}