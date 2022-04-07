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

package com.liferay.commerce.inventory.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseOrderTypeRelException;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseOrderTypeRel;
import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseOrderTypeRelLocalServiceUtil;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseOrderTypeRelPersistence;
import com.liferay.commerce.inventory.service.persistence.CommerceInventoryWarehouseOrderTypeRelUtil;
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
public class CommerceInventoryWarehouseOrderTypeRelPersistenceTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(), PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(
				Propagation.REQUIRED,
				"com.liferay.commerce.inventory.service"));

	@Before
	public void setUp() {
		_persistence =
			CommerceInventoryWarehouseOrderTypeRelUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CommerceInventoryWarehouseOrderTypeRel> iterator =
			_commerceInventoryWarehouseOrderTypeRels.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = _persistence.create(pk);

		Assert.assertNotNull(commerceInventoryWarehouseOrderTypeRel);

		Assert.assertEquals(
			commerceInventoryWarehouseOrderTypeRel.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel =
				addCommerceInventoryWarehouseOrderTypeRel();

		_persistence.remove(newCommerceInventoryWarehouseOrderTypeRel);

		CommerceInventoryWarehouseOrderTypeRel
			existingCommerceInventoryWarehouseOrderTypeRel =
				_persistence.fetchByPrimaryKey(
					newCommerceInventoryWarehouseOrderTypeRel.getPrimaryKey());

		Assert.assertNull(existingCommerceInventoryWarehouseOrderTypeRel);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCommerceInventoryWarehouseOrderTypeRel();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel = _persistence.create(pk);

		newCommerceInventoryWarehouseOrderTypeRel.setMvccVersion(
			RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseOrderTypeRel.setUuid(
			RandomTestUtil.randomString());

		newCommerceInventoryWarehouseOrderTypeRel.setCompanyId(
			RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseOrderTypeRel.setUserId(
			RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseOrderTypeRel.setUserName(
			RandomTestUtil.randomString());

		newCommerceInventoryWarehouseOrderTypeRel.setCreateDate(
			RandomTestUtil.nextDate());

		newCommerceInventoryWarehouseOrderTypeRel.setModifiedDate(
			RandomTestUtil.nextDate());

		newCommerceInventoryWarehouseOrderTypeRel.
			setCommerceInventoryWarehouseId(RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseOrderTypeRel.setCommerceOrderTypeId(
			RandomTestUtil.nextLong());

		newCommerceInventoryWarehouseOrderTypeRel.setPriority(
			RandomTestUtil.nextInt());

		newCommerceInventoryWarehouseOrderTypeRel.setLastPublishDate(
			RandomTestUtil.nextDate());

		_commerceInventoryWarehouseOrderTypeRels.add(
			_persistence.update(newCommerceInventoryWarehouseOrderTypeRel));

		CommerceInventoryWarehouseOrderTypeRel
			existingCommerceInventoryWarehouseOrderTypeRel =
				_persistence.findByPrimaryKey(
					newCommerceInventoryWarehouseOrderTypeRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel.getMvccVersion(),
			newCommerceInventoryWarehouseOrderTypeRel.getMvccVersion());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel.getUuid(),
			newCommerceInventoryWarehouseOrderTypeRel.getUuid());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel.
				getCommerceInventoryWarehouseOrderTypeRelId(),
			newCommerceInventoryWarehouseOrderTypeRel.
				getCommerceInventoryWarehouseOrderTypeRelId());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel.getCompanyId(),
			newCommerceInventoryWarehouseOrderTypeRel.getCompanyId());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel.getUserId(),
			newCommerceInventoryWarehouseOrderTypeRel.getUserId());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel.getUserName(),
			newCommerceInventoryWarehouseOrderTypeRel.getUserName());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceInventoryWarehouseOrderTypeRel.getCreateDate()),
			Time.getShortTimestamp(
				newCommerceInventoryWarehouseOrderTypeRel.getCreateDate()));
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceInventoryWarehouseOrderTypeRel.
					getModifiedDate()),
			Time.getShortTimestamp(
				newCommerceInventoryWarehouseOrderTypeRel.getModifiedDate()));
		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel.
				getCommerceInventoryWarehouseId(),
			newCommerceInventoryWarehouseOrderTypeRel.
				getCommerceInventoryWarehouseId());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel.
				getCommerceOrderTypeId(),
			newCommerceInventoryWarehouseOrderTypeRel.getCommerceOrderTypeId());
		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel.getPriority(),
			newCommerceInventoryWarehouseOrderTypeRel.getPriority());
		Assert.assertEquals(
			Time.getShortTimestamp(
				existingCommerceInventoryWarehouseOrderTypeRel.
					getLastPublishDate()),
			Time.getShortTimestamp(
				newCommerceInventoryWarehouseOrderTypeRel.
					getLastPublishDate()));
	}

	@Test
	public void testCountByUuid() throws Exception {
		_persistence.countByUuid("");

		_persistence.countByUuid("null");

		_persistence.countByUuid((String)null);
	}

	@Test
	public void testCountByUuid_C() throws Exception {
		_persistence.countByUuid_C("", RandomTestUtil.nextLong());

		_persistence.countByUuid_C("null", 0L);

		_persistence.countByUuid_C((String)null, 0L);
	}

	@Test
	public void testCountByCommerceInventoryWarehouseId() throws Exception {
		_persistence.countByCommerceInventoryWarehouseId(
			RandomTestUtil.nextLong());

		_persistence.countByCommerceInventoryWarehouseId(0L);
	}

	@Test
	public void testCountByCommerceOrderTypeId() throws Exception {
		_persistence.countByCommerceOrderTypeId(RandomTestUtil.nextLong());

		_persistence.countByCommerceOrderTypeId(0L);
	}

	@Test
	public void testCountByCIW_COTI() throws Exception {
		_persistence.countByCIW_COTI(
			RandomTestUtil.nextLong(), RandomTestUtil.nextLong());

		_persistence.countByCIW_COTI(0L, 0L);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel =
				addCommerceInventoryWarehouseOrderTypeRel();

		CommerceInventoryWarehouseOrderTypeRel
			existingCommerceInventoryWarehouseOrderTypeRel =
				_persistence.findByPrimaryKey(
					newCommerceInventoryWarehouseOrderTypeRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel,
			newCommerceInventoryWarehouseOrderTypeRel);
	}

	@Test(expected = NoSuchInventoryWarehouseOrderTypeRelException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"CIWarehouseOrderTypeRel", "mvccVersion", true, "uuid", true,
			"commerceInventoryWarehouseOrderTypeRelId", true, "companyId", true,
			"userId", true, "userName", true, "createDate", true,
			"modifiedDate", true, "commerceInventoryWarehouseId", true,
			"commerceOrderTypeId", true, "priority", true, "lastPublishDate",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel =
				addCommerceInventoryWarehouseOrderTypeRel();

		CommerceInventoryWarehouseOrderTypeRel
			existingCommerceInventoryWarehouseOrderTypeRel =
				_persistence.fetchByPrimaryKey(
					newCommerceInventoryWarehouseOrderTypeRel.getPrimaryKey());

		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel,
			newCommerceInventoryWarehouseOrderTypeRel);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseOrderTypeRel
			missingCommerceInventoryWarehouseOrderTypeRel =
				_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCommerceInventoryWarehouseOrderTypeRel);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel1 =
				addCommerceInventoryWarehouseOrderTypeRel();
		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel2 =
				addCommerceInventoryWarehouseOrderTypeRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(
			newCommerceInventoryWarehouseOrderTypeRel1.getPrimaryKey());
		primaryKeys.add(
			newCommerceInventoryWarehouseOrderTypeRel2.getPrimaryKey());

		Map<Serializable, CommerceInventoryWarehouseOrderTypeRel>
			commerceInventoryWarehouseOrderTypeRels =
				_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, commerceInventoryWarehouseOrderTypeRels.size());
		Assert.assertEquals(
			newCommerceInventoryWarehouseOrderTypeRel1,
			commerceInventoryWarehouseOrderTypeRels.get(
				newCommerceInventoryWarehouseOrderTypeRel1.getPrimaryKey()));
		Assert.assertEquals(
			newCommerceInventoryWarehouseOrderTypeRel2,
			commerceInventoryWarehouseOrderTypeRels.get(
				newCommerceInventoryWarehouseOrderTypeRel2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CommerceInventoryWarehouseOrderTypeRel>
			commerceInventoryWarehouseOrderTypeRels =
				_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceInventoryWarehouseOrderTypeRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel =
				addCommerceInventoryWarehouseOrderTypeRel();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(
			newCommerceInventoryWarehouseOrderTypeRel.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CommerceInventoryWarehouseOrderTypeRel>
			commerceInventoryWarehouseOrderTypeRels =
				_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceInventoryWarehouseOrderTypeRels.size());
		Assert.assertEquals(
			newCommerceInventoryWarehouseOrderTypeRel,
			commerceInventoryWarehouseOrderTypeRels.get(
				newCommerceInventoryWarehouseOrderTypeRel.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CommerceInventoryWarehouseOrderTypeRel>
			commerceInventoryWarehouseOrderTypeRels =
				_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(commerceInventoryWarehouseOrderTypeRels.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel =
				addCommerceInventoryWarehouseOrderTypeRel();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(
			newCommerceInventoryWarehouseOrderTypeRel.getPrimaryKey());

		Map<Serializable, CommerceInventoryWarehouseOrderTypeRel>
			commerceInventoryWarehouseOrderTypeRels =
				_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, commerceInventoryWarehouseOrderTypeRels.size());
		Assert.assertEquals(
			newCommerceInventoryWarehouseOrderTypeRel,
			commerceInventoryWarehouseOrderTypeRels.get(
				newCommerceInventoryWarehouseOrderTypeRel.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery =
			CommerceInventoryWarehouseOrderTypeRelLocalServiceUtil.
				getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(
			new ActionableDynamicQuery.PerformActionMethod
				<CommerceInventoryWarehouseOrderTypeRel>() {

				@Override
				public void performAction(
					CommerceInventoryWarehouseOrderTypeRel
						commerceInventoryWarehouseOrderTypeRel) {

					Assert.assertNotNull(
						commerceInventoryWarehouseOrderTypeRel);

					count.increment();
				}

			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel =
				addCommerceInventoryWarehouseOrderTypeRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceInventoryWarehouseOrderTypeRel.class,
			_dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceInventoryWarehouseOrderTypeRelId",
				newCommerceInventoryWarehouseOrderTypeRel.
					getCommerceInventoryWarehouseOrderTypeRelId()));

		List<CommerceInventoryWarehouseOrderTypeRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CommerceInventoryWarehouseOrderTypeRel
			existingCommerceInventoryWarehouseOrderTypeRel = result.get(0);

		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRel,
			newCommerceInventoryWarehouseOrderTypeRel);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceInventoryWarehouseOrderTypeRel.class,
			_dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceInventoryWarehouseOrderTypeRelId",
				RandomTestUtil.nextLong()));

		List<CommerceInventoryWarehouseOrderTypeRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel =
				addCommerceInventoryWarehouseOrderTypeRel();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceInventoryWarehouseOrderTypeRel.class,
			_dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property(
				"commerceInventoryWarehouseOrderTypeRelId"));

		Object newCommerceInventoryWarehouseOrderTypeRelId =
			newCommerceInventoryWarehouseOrderTypeRel.
				getCommerceInventoryWarehouseOrderTypeRelId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceInventoryWarehouseOrderTypeRelId",
				new Object[] {newCommerceInventoryWarehouseOrderTypeRelId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCommerceInventoryWarehouseOrderTypeRelId = result.get(0);

		Assert.assertEquals(
			existingCommerceInventoryWarehouseOrderTypeRelId,
			newCommerceInventoryWarehouseOrderTypeRelId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceInventoryWarehouseOrderTypeRel.class,
			_dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property(
				"commerceInventoryWarehouseOrderTypeRelId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"commerceInventoryWarehouseOrderTypeRelId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel =
				addCommerceInventoryWarehouseOrderTypeRel();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(
				newCommerceInventoryWarehouseOrderTypeRel.getPrimaryKey()));
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

		CommerceInventoryWarehouseOrderTypeRel
			newCommerceInventoryWarehouseOrderTypeRel =
				addCommerceInventoryWarehouseOrderTypeRel();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CommerceInventoryWarehouseOrderTypeRel.class,
			_dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"commerceInventoryWarehouseOrderTypeRelId",
				newCommerceInventoryWarehouseOrderTypeRel.
					getCommerceInventoryWarehouseOrderTypeRelId()));

		List<CommerceInventoryWarehouseOrderTypeRel> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(
		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel) {

		Assert.assertEquals(
			Long.valueOf(
				commerceInventoryWarehouseOrderTypeRel.
					getCommerceInventoryWarehouseId()),
			ReflectionTestUtil.<Long>invoke(
				commerceInventoryWarehouseOrderTypeRel,
				"getColumnOriginalValue", new Class<?>[] {String.class},
				"commerceInventoryWarehouseId"));
		Assert.assertEquals(
			Long.valueOf(
				commerceInventoryWarehouseOrderTypeRel.
					getCommerceOrderTypeId()),
			ReflectionTestUtil.<Long>invoke(
				commerceInventoryWarehouseOrderTypeRel,
				"getColumnOriginalValue", new Class<?>[] {String.class},
				"commerceOrderTypeId"));
	}

	protected CommerceInventoryWarehouseOrderTypeRel
			addCommerceInventoryWarehouseOrderTypeRel()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel = _persistence.create(pk);

		commerceInventoryWarehouseOrderTypeRel.setMvccVersion(
			RandomTestUtil.nextLong());

		commerceInventoryWarehouseOrderTypeRel.setUuid(
			RandomTestUtil.randomString());

		commerceInventoryWarehouseOrderTypeRel.setCompanyId(
			RandomTestUtil.nextLong());

		commerceInventoryWarehouseOrderTypeRel.setUserId(
			RandomTestUtil.nextLong());

		commerceInventoryWarehouseOrderTypeRel.setUserName(
			RandomTestUtil.randomString());

		commerceInventoryWarehouseOrderTypeRel.setCreateDate(
			RandomTestUtil.nextDate());

		commerceInventoryWarehouseOrderTypeRel.setModifiedDate(
			RandomTestUtil.nextDate());

		commerceInventoryWarehouseOrderTypeRel.setCommerceInventoryWarehouseId(
			RandomTestUtil.nextLong());

		commerceInventoryWarehouseOrderTypeRel.setCommerceOrderTypeId(
			RandomTestUtil.nextLong());

		commerceInventoryWarehouseOrderTypeRel.setPriority(
			RandomTestUtil.nextInt());

		commerceInventoryWarehouseOrderTypeRel.setLastPublishDate(
			RandomTestUtil.nextDate());

		_commerceInventoryWarehouseOrderTypeRels.add(
			_persistence.update(commerceInventoryWarehouseOrderTypeRel));

		return commerceInventoryWarehouseOrderTypeRel;
	}

	private List<CommerceInventoryWarehouseOrderTypeRel>
		_commerceInventoryWarehouseOrderTypeRels =
			new ArrayList<CommerceInventoryWarehouseOrderTypeRel>();
	private CommerceInventoryWarehouseOrderTypeRelPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}