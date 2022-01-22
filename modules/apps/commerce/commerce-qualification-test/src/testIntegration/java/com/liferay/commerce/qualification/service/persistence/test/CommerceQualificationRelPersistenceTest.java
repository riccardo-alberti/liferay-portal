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

package com.liferay.commerce.qualification.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.qualification.exception.NoSuchQualificationRelException;
import com.liferay.commerce.qualification.model.CommerceQualificationRel;
import com.liferay.commerce.qualification.service.CommerceQualificationRelLocalServiceUtil;
import com.liferay.commerce.qualification.service.persistence.CommerceQualificationRelPersistence;
import com.liferay.commerce.qualification.service.persistence.CommerceQualificationRelUtil;
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
public class CommerceQualificationRelPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.commerce.qualification.service"));

	@Before
	public void setUp() {
		_persistence = CommerceQualificationRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceQualificationRel> iterator =
			_commerceQualificationRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceQualificationRel commerceQualificationRel = _persistence.create(
			pk);

		Assert.assertNotNull(commerceQualificationRel);

		Assert.assertEquals(commerceQualificationRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceQualificationRel newCommerceQualificationRel =
			addCommerceQualificationRel();

		_persistence.remove(newCommerceQualificationRel);

		CommerceQualificationRel existingCommerceQualificationRel =
			_persistence.fetchByPrimaryKey(
				newCommerceQualificationRel.getPrimaryKey());

		Assert.assertNull(existingCommerceQualificationRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceQualificationRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceQualificationRel newCommerceQualificationRel =
			_persistence.create(pk);

		newCommerceQualificationRel.setMvccVersion(RandomTestUtil.nextLong());

		newCommerceQualificationRel.setCompanyId(RandomTestUtil.nextLong());

		newCommerceQualificationRel.setUserId(RandomTestUtil.nextLong());

		newCommerceQualificationRel.setUserName(RandomTestUtil.randomString());

		newCommerceQualificationRel.setCreateDate(RandomTestUtil.nextDate());

		newCommerceQualificationRel.setModifiedDate(RandomTestUtil.nextDate());

		newCommerceQualificationRel.setSourceClassNameId(
			RandomTestUtil.nextLong());

		newCommerceQualificationRel.setSourceClassPK(RandomTestUtil.nextLong());

		newCommerceQualificationRel.setTargetClassNameId(
			RandomTestUtil.nextLong());

		newCommerceQualificationRel.setTargetClassPK(RandomTestUtil.nextLong());

		_commerceQualificationRels.add(
			_persistence.update(newCommerceQualificationRel));

		CommerceQualificationRel existingCommerceQualificationRel =
			_persistence.findByPrimaryKey(
				newCommerceQualificationRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceQualificationRel.getMvccVersion(),
			newCommerceQualificationRel.getMvccVersion());
		Assert.assertEquals(
			existingCommerceQualificationRel.getCommerceQualificationRelId(),
			newCommerceQualificationRel.getCommerceQualificationRelId());
		Assert.assertEquals(
			existingCommerceQualificationRel.getCompanyId(),
			newCommerceQualificationRel.getCompanyId());
		Assert.assertEquals(
			existingCommerceQualificationRel.getUserId(),
			newCommerceQualificationRel.getUserId());
		Assert.assertEquals(
			existingCommerceQualificationRel.getUserName(),
			newCommerceQualificationRel.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceQualificationRel.getCreateDate()),
			Time.getShortTimestamp(
				newCommerceQualificationRel.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceQualificationRel.getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceQualificationRel.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceQualificationRel.getSourceClassNameId(),
			newCommerceQualificationRel.getSourceClassNameId());
		Assert.assertEquals(
			existingCommerceQualificationRel.getSourceClassPK(),
			newCommerceQualificationRel.getSourceClassPK());
		Assert.assertEquals(
			existingCommerceQualificationRel.getTargetClassNameId(),
			newCommerceQualificationRel.getTargetClassNameId());
		Assert.assertEquals(
			existingCommerceQualificationRel.getTargetClassPK(),
			newCommerceQualificationRel.getTargetClassPK());
	}

	@Test
	public void testCountByS_S() throws Exception {
		_persistence.countByS_S(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByS_S(0L, 0L);
	}

	@Test
	public void testCountByS_S_T() throws Exception {
		_persistence.countByS_S_T(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong());

		_persistence.countByS_S_T(0L, 0L, 0L);
	}

	@Test
	public void testCountByS_S_T_T() throws Exception {
		_persistence.countByS_S_T_T(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong(),
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByS_S_T_T(0L, 0L, 0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceQualificationRel newCommerceQualificationRel =
			addCommerceQualificationRel();

		CommerceQualificationRel existingCommerceQualificationRel =
			_persistence.findByPrimaryKey(
				newCommerceQualificationRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceQualificationRel, newCommerceQualificationRel);
	}

	@Test(expected = NoSuchQualificationRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceQualificationRel>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"CommerceQualificationRel", "mvccVersion", true,
			"commerceQualificationRelId", true, "companyId", true, "userId",
			true, "userName", true, "createDate", true, "modifiedDate", true,
			"sourceClassNameId", true, "sourceClassPK", true,
			"targetClassNameId", true, "targetClassPK", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceQualificationRel newCommerceQualificationRel =
			addCommerceQualificationRel();

		CommerceQualificationRel existingCommerceQualificationRel =
			_persistence.fetchByPrimaryKey(
				newCommerceQualificationRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceQualificationRel, newCommerceQualificationRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceQualificationRel missingCommerceQualificationRel =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceQualificationRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceQualificationRel newCommerceQualificationRel1 =
			addCommerceQualificationRel();
		CommerceQualificationRel newCommerceQualificationRel2 =
			addCommerceQualificationRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceQualificationRel1.getPrimaryKey());
		primaryKeys.add(newCommerceQualificationRel2.getPrimaryKey());

		Map<Serializable, CommerceQualificationRel> commerceQualificationRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceQualificationRels.size());
		Assert.assertEquals(
			newCommerceQualificationRel1,
			commerceQualificationRels.get(
				newCommerceQualificationRel1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceQualificationRel2,
			commerceQualificationRels.get(
				newCommerceQualificationRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceQualificationRel> commerceQualificationRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceQualificationRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceQualificationRel newCommerceQualificationRel =
			addCommerceQualificationRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceQualificationRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceQualificationRel> commerceQualificationRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceQualificationRels.size());
		Assert.assertEquals(
			newCommerceQualificationRel,
			commerceQualificationRels.get(
				newCommerceQualificationRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceQualificationRel> commerceQualificationRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceQualificationRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceQualificationRel newCommerceQualificationRel =
			addCommerceQualificationRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCommerceQualificationRel.getPrimaryKey());

		Map<Serializable, CommerceQualificationRel> commerceQualificationRels =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceQualificationRels.size());
		Assert.assertEquals(
			newCommerceQualificationRel,
			commerceQualificationRels.get(
				newCommerceQualificationRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceQualificationRelLocalServiceUtil.
				getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceQualificationRel>() {

				@Override
				public void performAction(
					CommerceQualificationRel commerceQualificationRel) {

					Assert.assertNotNull(commerceQualificationRel);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceQualificationRel newCommerceQualificationRel =
			addCommerceQualificationRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceQualificationRel.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceQualificationRelId",
				newCommerceQualificationRel.getCommerceQualificationRelId()));

		List<CommerceQualificationRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceQualificationRel existingCommerceQualificationRel = result.get(
			0);

		Assert.assertEquals(
			existingCommerceQualificationRel, newCommerceQualificationRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceQualificationRel.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceQualificationRelId", RandomTestUtil.nextLong()));

		List<CommerceQualificationRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceQualificationRel newCommerceQualificationRel =
			addCommerceQualificationRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceQualificationRel.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceQualificationRelId"));

		Object newCommerceQualificationRelId =
			newCommerceQualificationRel.getCommerceQualificationRelId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceQualificationRelId",
				new Object[] {newCommerceQualificationRelId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceQualificationRelId = result.get(0);

		Assert.assertEquals(
			existingCommerceQualificationRelId, newCommerceQualificationRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceQualificationRel.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("commerceQualificationRelId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceQualificationRelId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceQualificationRel newCommerceQualificationRel =
			addCommerceQualificationRel();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(
				newCommerceQualificationRel.getPrimaryKey()));
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

		CommerceQualificationRel newCommerceQualificationRel =
			addCommerceQualificationRel();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceQualificationRel.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceQualificationRelId",
				newCommerceQualificationRel.getCommerceQualificationRelId()));

		List<CommerceQualificationRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(
		CommerceQualificationRel commerceQualificationRel) {

		Assert.assertEquals(
			Long.valueOf(commerceQualificationRel.getSourceClassNameId()),
			ReflectionTestUtil.<Long>invoke(
				commerceQualificationRel, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "sourceClassNameId"));
		Assert.assertEquals(
			Long.valueOf(commerceQualificationRel.getSourceClassPK()),
			ReflectionTestUtil.<Long>invoke(
				commerceQualificationRel, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "sourceClassPK"));
		Assert.assertEquals(
			Long.valueOf(commerceQualificationRel.getTargetClassNameId()),
			ReflectionTestUtil.<Long>invoke(
				commerceQualificationRel, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "targetClassNameId"));
		Assert.assertEquals(
			Long.valueOf(commerceQualificationRel.getTargetClassPK()),
			ReflectionTestUtil.<Long>invoke(
				commerceQualificationRel, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "targetClassPK"));
	}

	protected CommerceQualificationRel addCommerceQualificationRel()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CommerceQualificationRel commerceQualificationRel = _persistence.create(
			pk);

		commerceQualificationRel.setMvccVersion(RandomTestUtil.nextLong());

		commerceQualificationRel.setCompanyId(RandomTestUtil.nextLong());

		commerceQualificationRel.setUserId(RandomTestUtil.nextLong());

		commerceQualificationRel.setUserName(RandomTestUtil.randomString());

		commerceQualificationRel.setCreateDate(RandomTestUtil.nextDate());

		commerceQualificationRel.setModifiedDate(RandomTestUtil.nextDate());

		commerceQualificationRel.setSourceClassNameId(
			RandomTestUtil.nextLong());

		commerceQualificationRel.setSourceClassPK(RandomTestUtil.nextLong());

		commerceQualificationRel.setTargetClassNameId(
			RandomTestUtil.nextLong());

		commerceQualificationRel.setTargetClassPK(RandomTestUtil.nextLong());

		_commerceQualificationRels.add(
			_persistence.update(commerceQualificationRel));

		return commerceQualificationRel;
	}

	private List<CommerceQualificationRel> _commerceQualificationRels =
		new ArrayList<CommerceQualificationRel>();
	private CommerceQualificationRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}