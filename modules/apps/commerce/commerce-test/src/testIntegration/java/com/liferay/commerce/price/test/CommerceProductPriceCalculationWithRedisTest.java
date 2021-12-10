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

package com.liferay.commerce.price.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.test.util.CommerceCurrencyTestUtil;
import com.liferay.commerce.discount.application.strategy.CommerceDiscountApplicationStrategy;
import com.liferay.commerce.discount.service.CommerceDiscountLocalService;
import com.liferay.commerce.discount.validator.helper.CommerceDiscountValidatorHelper;
import com.liferay.commerce.internal.price.util.CalculatePrice;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.price.CommerceProductPriceRequest;
import com.liferay.commerce.pricing.constants.CommercePricingConstants;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.test.util.context.TestCommerceContext;
import com.liferay.fulfilment.manager.FulfilmentManagerUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.test.util.UserTestUtil;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;
import com.liferay.redis.RedisConnection;

import java.io.FileWriter;
import java.io.PrintWriter;

import java.util.HashMap;
import java.util.List;

import org.frutilla.FrutillaRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Riccardo Alberti
 */
@RunWith(Arquillian.class)
public class CommerceProductPriceCalculationWithRedisTest {

	@ClassRule
	@Rule
	public static AggregateTestRule aggregateTestRule = new AggregateTestRule(
		new LiferayIntegrationTestRule(),
		PermissionCheckerMethodTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_group = GroupTestUtil.addGroup();

		_user = UserTestUtil.addUser();

		_commerceAccount =
			_commerceAccountLocalService.getPersonalCommerceAccount(
				_user.getUserId());

		_commerceCurrency = CommerceCurrencyTestUtil.addCommerceCurrency(
			_group.getCompanyId());

		_serviceContext = ServiceContextTestUtil.getServiceContext(
			_group.getCompanyId(), _group.getGroupId(), _user.getUserId());
	}

	@Test
	public void testCalculatePriceHitDifferentManyTimes() throws Exception {
		CommerceContext commerceContext = new TestCommerceContext(
			_commerceCurrency, null, _user, _group, _commerceAccount, null);

		FulfilmentManagerUtil.syncExecuteWorkflow(
			TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
			new HashMap<>(), null, "pricing");

		List<CPDefinition> cpDefinitions =
			_cpDefinitionLocalService.getCPDefinitions(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		PrintWriter printWriter = new PrintWriter(new FileWriter("/tmp/testCalculatePriceHitDifferentManyTimes.log"));

		for (int i = 0; i < 1000; i++) {
			for (CPDefinition cpDefinition : cpDefinitions) {
				List<CPInstance> cpDefinitionInstances =
					_cpInstanceLocalService.getCPDefinitionInstances(
						cpDefinition.getCPDefinitionId());

				long offlineAlgorithmTimestamp = System.nanoTime();

				for (CPInstance cpInstance : cpDefinitionInstances) {
					CommerceProductPrice commerceProductPrice =
						CalculatePrice.getCommerceProductPrice(
							cpInstance.getCPInstanceId(), 1, commerceContext,
							_commerceDiscountApplicationStrategy,
							_commerceDiscountValidatorHelper,
							_commerceDiscountLocalService, _redisConnection);
				}

				offlineAlgorithmTimestamp =
					System.nanoTime() - offlineAlgorithmTimestamp;

				long onlineAlgorithmTimestamp = System.nanoTime();

				for (CPInstance cpInstance : cpDefinitionInstances) {
					CommerceProductPriceRequest commerceProductPriceRequest =
						new CommerceProductPriceRequest();

					commerceProductPriceRequest.setCpInstanceId(
						cpInstance.getCPInstanceId());
					commerceProductPriceRequest.setQuantity(1);
					commerceProductPriceRequest.setSecure(true);
					commerceProductPriceRequest.setCommerceContext(
						commerceContext);

					CommerceProductPrice commerceProductPrice =
						_commerceProductPriceCalculation.
							getCommerceProductPrice(
								commerceProductPriceRequest);
				}

				onlineAlgorithmTimestamp =
					System.nanoTime() - onlineAlgorithmTimestamp;

				printWriter.println(
					onlineAlgorithmTimestamp + "," +
						offlineAlgorithmTimestamp + "," +
							(100 * (double) offlineAlgorithmTimestamp /
							 (double) onlineAlgorithmTimestamp));
			}
		}

		printWriter.close();
	}

	@Test
	public void testCalculatePriceHitSameManyTimes() throws Exception {
		CommerceContext commerceContext = new TestCommerceContext(
			_commerceCurrency, null, _user, _group, _commerceAccount, null);

		PrintWriter printWriter = new PrintWriter(new FileWriter("/tmp/testCalculatePriceHitSameManyTimes.log"));

		FulfilmentManagerUtil.syncExecuteWorkflow(
			TestPropsValues.getCompanyId(), TestPropsValues.getUserId(),
			new HashMap<>(), null, "pricing");

		List<CPDefinition> cpDefinitions =
			_cpDefinitionLocalService.getCPDefinitions(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		for (CPDefinition cpDefinition : cpDefinitions) {
			List<CPInstance> cpDefinitionInstances =
				_cpInstanceLocalService.getCPDefinitionInstances(
					cpDefinition.getCPDefinitionId());

			long offlineAlgorithmTimestamp = System.nanoTime();

			for (CPInstance cpInstance : cpDefinitionInstances) {
				for (int i = 0; i < 1000; i++) {
					CommerceProductPrice commerceProductPrice =
						CalculatePrice.getCommerceProductPrice(
							cpInstance.getCPInstanceId(), 1, commerceContext,
							_commerceDiscountApplicationStrategy,
							_commerceDiscountValidatorHelper,
							_commerceDiscountLocalService, _redisConnection);
				}
			}

			offlineAlgorithmTimestamp =
				System.nanoTime() - offlineAlgorithmTimestamp;

			long onlineAlgorithmTimestamp = System.nanoTime();

			for (CPInstance cpInstance : cpDefinitionInstances) {
				CommerceProductPriceRequest commerceProductPriceRequest =
					new CommerceProductPriceRequest();

				commerceProductPriceRequest.setCpInstanceId(
					cpInstance.getCPInstanceId());
				commerceProductPriceRequest.setQuantity(1);
				commerceProductPriceRequest.setSecure(true);
				commerceProductPriceRequest.setCommerceContext(commerceContext);

				for (int i = 0; i < 1000; i++) {
					CommerceProductPrice commerceProductPrice =
						_commerceProductPriceCalculation.
							getCommerceProductPrice(
								commerceProductPriceRequest);
				}
			}

			onlineAlgorithmTimestamp =
				System.nanoTime() - onlineAlgorithmTimestamp;

			printWriter.println(
				onlineAlgorithmTimestamp + "," +
					offlineAlgorithmTimestamp + "," +
						(100 * (double) offlineAlgorithmTimestamp / (double) onlineAlgorithmTimestamp));
		}

		printWriter.close();
	}

	@Rule
	public FrutillaRule frutillaRule = new FrutillaRule();

	private static User _user;

	private CommerceAccount _commerceAccount;

	@Inject
	private CommerceAccountLocalService _commerceAccountLocalService;

	private CommerceCurrency _commerceCurrency;

	@Inject(
		filter = "commerce.discount.application.strategy.key=" + CommercePricingConstants.DISCOUNT_CHAIN_METHOD
	)
	private CommerceDiscountApplicationStrategy
		_commerceDiscountApplicationStrategy;

	@Inject
	private CommerceDiscountLocalService _commerceDiscountLocalService;

	@Inject
	private CommerceDiscountValidatorHelper _commerceDiscountValidatorHelper;

	@Inject
	private CommerceProductPriceCalculation _commerceProductPriceCalculation;

	@Inject
	private CPDefinitionLocalService _cpDefinitionLocalService;

	@Inject
	private CPInstanceLocalService _cpInstanceLocalService;

	private Group _group;

	@Inject
	private RedisConnection _redisConnection;

	private ServiceContext _serviceContext;

}