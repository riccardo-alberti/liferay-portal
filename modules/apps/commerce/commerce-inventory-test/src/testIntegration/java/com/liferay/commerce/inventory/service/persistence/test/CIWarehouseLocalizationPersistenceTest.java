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
import com.liferay.commerce.inventory.exception.NoSuchCIWarehouseLocalizationException;
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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
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
public class CIWarehouseLocalizationPersistenceTest {

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
		_persistence = CIWarehouseLocalizationUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CIWarehouseLocalization> iterator =
			_ciWarehouseLocalizations.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CIWarehouseLocalization ciWarehouseLocalization = _persistence.create(
			pk);

		Assert.assertNotNull(ciWarehouseLocalization);

		Assert.assertEquals(ciWarehouseLocalization.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CIWarehouseLocalization newCIWarehouseLocalization =
			addCIWarehouseLocalization();

		_persistence.remove(newCIWarehouseLocalization);

		CIWarehouseLocalization existingCIWarehouseLocalization =
			_persistence.fetchByPrimaryKey(
				newCIWarehouseLocalization.getPrimaryKey());

		Assert.assertNull(existingCIWarehouseLocalization);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCIWarehouseLocalization();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CIWarehouseLocalization newCIWarehouseLocalization =
			_persistence.create(pk);

		newCIWarehouseLocalization.setMvccVersion(RandomTestUtil.nextLong());

		newCIWarehouseLocalization.setCompanyId(RandomTestUtil.nextLong());

		newCIWarehouseLocalization.setCommerceInventoryWarehouseId(
			RandomTestUtil.nextLong());

		newCIWarehouseLocalization.setLanguageId(RandomTestUtil.randomString());

		newCIWarehouseLocalization.setDescription(
			RandomTestUtil.randomString());

		newCIWarehouseLocalization.setLabel(RandomTestUtil.randomString());

		_ciWarehouseLocalizations.add(
			_persistence.update(newCIWarehouseLocalization));

		CIWarehouseLocalization existingCIWarehouseLocalization =
			_persistence.findByPrimaryKey(
				newCIWarehouseLocalization.getPrimaryKey());

		Assert.assertEquals(
			existingCIWarehouseLocalization.getMvccVersion(),
			newCIWarehouseLocalization.getMvccVersion());
		Assert.assertEquals(
			existingCIWarehouseLocalization.getCiWarehouseLocalizationId(),
			newCIWarehouseLocalization.getCiWarehouseLocalizationId());
		Assert.assertEquals(
			existingCIWarehouseLocalization.getCompanyId(),
			newCIWarehouseLocalization.getCompanyId());
		Assert.assertEquals(
			existingCIWarehouseLocalization.getCommerceInventoryWarehouseId(),
			newCIWarehouseLocalization.getCommerceInventoryWarehouseId());
		Assert.assertEquals(
			existingCIWarehouseLocalization.getLanguageId(),
			newCIWarehouseLocalization.getLanguageId());
		Assert.assertEquals(
			existingCIWarehouseLocalization.getDescription(),
			newCIWarehouseLocalization.getDescription());
		Assert.assertEquals(
			existingCIWarehouseLocalization.getLabel(),
			newCIWarehouseLocalization.getLabel());
	}

	@Test
	public void testCountByCommerceInventoryWarehouseId() throws Exception {
		_persistence.countByCommerceInventoryWarehouseId(
			RandomTestUtil.nextLong());

		_persistence.countByCommerceInventoryWarehouseId(0L);
	}

	@Test
	public void testCountByCommerceInventoryWarehouseId_LanguageId()
		throws Exception {

		_persistence.countByCommerceInventoryWarehouseId_LanguageId(
			RandomTestUtil.nextLong(), "");

		_persistence.countByCommerceInventoryWarehouseId_LanguageId(0L, "null");

		_persistence.countByCommerceInventoryWarehouseId_LanguageId(
			0L, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CIWarehouseLocalization newCIWarehouseLocalization =
			addCIWarehouseLocalization();

		CIWarehouseLocalization existingCIWarehouseLocalization =
			_persistence.findByPrimaryKey(
				newCIWarehouseLocalization.getPrimaryKey());

		Assert.assertEquals(
			existingCIWarehouseLocalization, newCIWarehouseLocalization);
	}

	@Test(expected = NoSuchCIWarehouseLocalizationException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, getOrderByComparator());
	}

	protected OrderByComparator<CIWarehouseLocalization>
		getOrderByComparator() {

		return OrderByComparatorFactoryUtil.create(
			"CIWarehouseLocalization", "mvccVersion", true,
			"ciWarehouseLocalizationId", true, "companyId", true,
			"commerceInventoryWarehouseId", true, "languageId", true,
			"description", true, "label", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CIWarehouseLocalization newCIWarehouseLocalization =
			addCIWarehouseLocalization();

		CIWarehouseLocalization existingCIWarehouseLocalization =
			_persistence.fetchByPrimaryKey(
				newCIWarehouseLocalization.getPrimaryKey());

		Assert.assertEquals(
			existingCIWarehouseLocalization, newCIWarehouseLocalization);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		long pk = RandomTestUtil.nextLong();

		CIWarehouseLocalization missingCIWarehouseLocalization =
			_persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCIWarehouseLocalization);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {

		CIWarehouseLocalization newCIWarehouseLocalization1 =
			addCIWarehouseLocalization();
		CIWarehouseLocalization newCIWarehouseLocalization2 =
			addCIWarehouseLocalization();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCIWarehouseLocalization1.getPrimaryKey());
		primaryKeys.add(newCIWarehouseLocalization2.getPrimaryKey());

		Map<Serializable, CIWarehouseLocalization> ciWarehouseLocalizations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ciWarehouseLocalizations.size());
		Assert.assertEquals(
			newCIWarehouseLocalization1,
			ciWarehouseLocalizations.get(
				newCIWarehouseLocalization1.getPrimaryKey()));
		Assert.assertEquals(
			newCIWarehouseLocalization2,
			ciWarehouseLocalizations.get(
				newCIWarehouseLocalization2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {

		long pk1 = RandomTestUtil.nextLong();

		long pk2 = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CIWarehouseLocalization> ciWarehouseLocalizations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ciWarehouseLocalizations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {

		CIWarehouseLocalization newCIWarehouseLocalization =
			addCIWarehouseLocalization();

		long pk = RandomTestUtil.nextLong();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCIWarehouseLocalization.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CIWarehouseLocalization> ciWarehouseLocalizations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ciWarehouseLocalizations.size());
		Assert.assertEquals(
			newCIWarehouseLocalization,
			ciWarehouseLocalizations.get(
				newCIWarehouseLocalization.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys() throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CIWarehouseLocalization> ciWarehouseLocalizations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ciWarehouseLocalizations.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey() throws Exception {
		CIWarehouseLocalization newCIWarehouseLocalization =
			addCIWarehouseLocalization();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCIWarehouseLocalization.getPrimaryKey());

		Map<Serializable, CIWarehouseLocalization> ciWarehouseLocalizations =
			_persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ciWarehouseLocalizations.size());
		Assert.assertEquals(
			newCIWarehouseLocalization,
			ciWarehouseLocalizations.get(
				newCIWarehouseLocalization.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting() throws Exception {
		CIWarehouseLocalization newCIWarehouseLocalization =
			addCIWarehouseLocalization();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CIWarehouseLocalization.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"ciWarehouseLocalizationId",
				newCIWarehouseLocalization.getCiWarehouseLocalizationId()));

		List<CIWarehouseLocalization> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CIWarehouseLocalization existingCIWarehouseLocalization = result.get(0);

		Assert.assertEquals(
			existingCIWarehouseLocalization, newCIWarehouseLocalization);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CIWarehouseLocalization.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"ciWarehouseLocalizationId", RandomTestUtil.nextLong()));

		List<CIWarehouseLocalization> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting() throws Exception {
		CIWarehouseLocalization newCIWarehouseLocalization =
			addCIWarehouseLocalization();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CIWarehouseLocalization.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("ciWarehouseLocalizationId"));

		Object newCiWarehouseLocalizationId =
			newCIWarehouseLocalization.getCiWarehouseLocalizationId();

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"ciWarehouseLocalizationId",
				new Object[] {newCiWarehouseLocalizationId}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCiWarehouseLocalizationId = result.get(0);

		Assert.assertEquals(
			existingCiWarehouseLocalizationId, newCiWarehouseLocalizationId);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CIWarehouseLocalization.class, _dynamicQueryClassLoader);

		dynamicQuery.setProjection(
			ProjectionFactoryUtil.property("ciWarehouseLocalizationId"));

		dynamicQuery.add(
			RestrictionsFactoryUtil.in(
				"ciWarehouseLocalizationId",
				new Object[] {RandomTestUtil.nextLong()}));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		CIWarehouseLocalization newCIWarehouseLocalization =
			addCIWarehouseLocalization();

		_persistence.clearCache();

		_assertOriginalValues(
			_persistence.findByPrimaryKey(
				newCIWarehouseLocalization.getPrimaryKey()));
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

		CIWarehouseLocalization newCIWarehouseLocalization =
			addCIWarehouseLocalization();

		if (clearSession) {
			Session session = _persistence.openSession();

			session.flush();

			session.clear();
		}

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(
			CIWarehouseLocalization.class, _dynamicQueryClassLoader);

		dynamicQuery.add(
			RestrictionsFactoryUtil.eq(
				"ciWarehouseLocalizationId",
				newCIWarehouseLocalization.getCiWarehouseLocalizationId()));

		List<CIWarehouseLocalization> result =
			_persistence.findWithDynamicQuery(dynamicQuery);

		_assertOriginalValues(result.get(0));
	}

	private void _assertOriginalValues(
		CIWarehouseLocalization ciWarehouseLocalization) {

		Assert.assertEquals(
			Long.valueOf(
				ciWarehouseLocalization.getCommerceInventoryWarehouseId()),
			ReflectionTestUtil.<Long>invoke(
				ciWarehouseLocalization, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "commerceInventoryWarehouseId"));
		Assert.assertEquals(
			ciWarehouseLocalization.getLanguageId(),
			ReflectionTestUtil.invoke(
				ciWarehouseLocalization, "getColumnOriginalValue",
				new Class<?>[] {String.class}, "languageId"));
	}

	protected CIWarehouseLocalization addCIWarehouseLocalization()
		throws Exception {

		long pk = RandomTestUtil.nextLong();

		CIWarehouseLocalization ciWarehouseLocalization = _persistence.create(
			pk);

		ciWarehouseLocalization.setMvccVersion(RandomTestUtil.nextLong());

		ciWarehouseLocalization.setCompanyId(RandomTestUtil.nextLong());

		ciWarehouseLocalization.setCommerceInventoryWarehouseId(
			RandomTestUtil.nextLong());

		ciWarehouseLocalization.setLanguageId(RandomTestUtil.randomString());

		ciWarehouseLocalization.setDescription(RandomTestUtil.randomString());

		ciWarehouseLocalization.setLabel(RandomTestUtil.randomString());

		_ciWarehouseLocalizations.add(
			_persistence.update(ciWarehouseLocalization));

		return ciWarehouseLocalization;
	}

	private List<CIWarehouseLocalization> _ciWarehouseLocalizations =
		new ArrayList<CIWarehouseLocalization>();
	private CIWarehouseLocalizationPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;

}