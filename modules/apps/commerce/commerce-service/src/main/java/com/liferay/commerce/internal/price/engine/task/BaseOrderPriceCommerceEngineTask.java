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

package com.liferay.commerce.internal.price.engine.task;

import com.liferay.commerce.configuration.PriceCommerceEngineTaskConfiguration;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoneyFactory;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.discount.CommerceDiscountCalculation;
import com.liferay.commerce.discount.application.strategy.CommerceDiscountApplicationStrategy;
import com.liferay.commerce.engine.CommerceEngine;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.price.engine.task.OrderPriceCommerceEngineTaskContext;
import com.liferay.commerce.pricing.configuration.CommercePricingConfiguration;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.tax.CommerceTaxCalculation;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.resource.StringResourceRetriever;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.settings.ModifiableSettings;
import com.liferay.portal.kernel.settings.Settings;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.util.CamelCaseUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.rules.engine.Fact;
import com.liferay.portal.rules.engine.RulesEngine;
import com.liferay.portal.rules.engine.RulesEngineException;
import com.liferay.portal.rules.engine.RulesResourceRetriever;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Riccardo Alberti
 */
public abstract class BaseOrderPriceCommerceEngineTask
	implements CommerceEngineTask<OrderPriceCommerceEngineTaskContext> {

	@Override
	public String getDescription(Locale locale) {
		StringBundler sb = new StringBundler(4);

		sb.append(getType());
		sb.append(StringPool.MINUS);
		sb.append(getKey());
		sb.append("-task-description");

		return LanguageUtil.get(locale, sb.toString());
	}

	@Override
	public Optional<CommerceEngineTask> getNext(
			OrderPriceCommerceEngineTaskContext
				orderPriceCommerceEngineTaskContext)
		throws Exception {

		return Optional.empty();
	}

	@Override
	public Optional<CommerceEngineTask> handleException(
			Exception exception,
			OrderPriceCommerceEngineTaskContext
				orderPriceCommerceEngineTaskContext)
		throws Exception {

		return Optional.empty();
	}

	@Override
	public boolean isActive(long groupId) {
		return true;
	}

	@Override
	public boolean isWorkflowEnabled(
		OrderPriceCommerceEngineTaskContext
			orderPriceCommerceEngineTaskContext) {

		return false;
	}

	public void unsetCommerceDiscountApplicationStrategy(
		CommerceDiscountApplicationStrategy commerceDiscountApplicationStrategy,
		Map<String, Object> properties) {

		String commerceDiscountApplicationStrategyKey = GetterUtil.getString(
			properties.get("commerce.discount.application.strategy.key"));

		_commerceDiscountApplicationStrategyMap.remove(
			commerceDiscountApplicationStrategyKey);
	}

	@Override
	public void updateEvaluateCondition(
		long groupId, String evaluateCondition) {

		try {
			Settings settings = settingsFactory.getSettings(
				new GroupServiceSettingsLocator(
					groupId, CommerceConstants.SERVICE_NAME_PRICE));

			ModifiableSettings modifiableSettings =
				settings.getModifiableSettings();

			StringBundler sb = new StringBundler(2);

			sb.append(CamelCaseUtil.toCamelCase(getKey(), '-'));
			sb.append("EvaluateCondition");

			modifiableSettings.setValue(sb.toString(), evaluateCondition);

			modifiableSettings.store();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}
	}

	@Override
	public void updateStatus(long groupId) {
		try {
			Settings settings = settingsFactory.getSettings(
				new GroupServiceSettingsLocator(
					groupId, CommerceConstants.SERVICE_NAME_PRICE));

			ModifiableSettings modifiableSettings =
				settings.getModifiableSettings();

			modifiableSettings.setValue(
				CamelCaseUtil.toCamelCase(getKey(), '-'),
				String.valueOf(!isActive(groupId)));

			modifiableSettings.store();
		}
		catch (Exception exception) {
			if (_log.isDebugEnabled()) {
				_log.debug(exception, exception);
			}
		}
	}

	protected BigDecimal convertCommercePriceCurrency(
			BigDecimal commercePrice, CommerceCurrency fromCommerceCurrency,
			CommerceCurrency toCommerceCurrency)
		throws Exception {

		if (fromCommerceCurrency.getCommerceCurrencyId() !=
				toCommerceCurrency.getCommerceCurrencyId()) {

			commercePrice = commercePrice.divide(
				fromCommerceCurrency.getRate(),
				RoundingMode.valueOf(fromCommerceCurrency.getRoundingMode()));

			return commercePrice.multiply(toCommerceCurrency.getRate());
		}

		return commercePrice;
	}

	protected boolean evaluate(
			String rule,
			OrderPriceCommerceEngineTaskContext
				orderPriceCommerceEngineTaskContext)
		throws RulesEngineException {

		if (Validator.isBlank(rule)) {
			return true;
		}

		RulesResourceRetriever rulesResourceRetriever =
			new RulesResourceRetriever(new StringResourceRetriever(rule));

		orderPriceCommerceEngineTaskContext.setConditionValid(false);

		rulesEngine.execute(
			rulesResourceRetriever,
			Arrays.asList(
				new Fact<OrderPriceCommerceEngineTaskContext>(
					"context", orderPriceCommerceEngineTaskContext)));

		return orderPriceCommerceEngineTaskContext.isConditionValid();
	}

	protected CommerceDiscountApplicationStrategy
			getCommerceDiscountApplicationStrategy()
		throws Exception {

		CommercePricingConfiguration commercePricingConfiguration =
			configurationProvider.getSystemConfiguration(
				CommercePricingConfiguration.class);

		String commerceDiscountApplicationStrategy =
			commercePricingConfiguration.commerceDiscountApplicationStrategy();

		if (!_commerceDiscountApplicationStrategyMap.containsKey(
				commerceDiscountApplicationStrategy)) {

			if (_log.isWarnEnabled()) {
				_log.warn(
					"No commerce discount application strategy specified for " +
						commerceDiscountApplicationStrategy);
			}

			return null;
		}

		return _commerceDiscountApplicationStrategyMap.get(
			commerceDiscountApplicationStrategy);
	}

	protected PriceCommerceEngineTaskConfiguration
			getCommerceEnginePriceConfiguration(long groupId)
		throws ConfigurationException {

		return configurationProvider.getConfiguration(
			PriceCommerceEngineTaskConfiguration.class,
			new GroupServiceSettingsLocator(
				groupId, CommerceConstants.SERVICE_NAME_PRICE));
	}

	protected PriceCommerceEngineTaskConfiguration
			getCommerceEngineTaskConfiguration(long groupId)
		throws ConfigurationException {

		return configurationProvider.getConfiguration(
			PriceCommerceEngineTaskConfiguration.class,
			new GroupServiceSettingsLocator(
				groupId, CommerceConstants.SERVICE_NAME_PRICE));
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setCommerceDiscountApplicationStrategy(
		CommerceDiscountApplicationStrategy commerceDiscountApplicationStrategy,
		Map<String, Object> properties) {

		String commerceDiscountApplicationStrategyKey = GetterUtil.getString(
			properties.get("commerce.discount.application.strategy.key"));

		_commerceDiscountApplicationStrategyMap.put(
			commerceDiscountApplicationStrategyKey,
			commerceDiscountApplicationStrategy);
	}

	@Reference
	protected CommerceChannelLocalService commerceChannelLocalService;

	@Reference
	protected CommerceCurrencyLocalService commerceCurrencyLocalService;

	@Reference
	protected CommerceDiscountCalculation commerceDiscountCalculation;

	@Reference
	protected CommerceEngine commerceEngine;

	@Reference
	protected CommerceMoneyFactory commerceMoneyFactory;

	@Reference
	protected CommerceTaxCalculation commerceTaxCalculation;

	@Reference
	protected ConfigurationProvider configurationProvider;

	@Reference
	protected CPInstanceLocalService cpInstanceLocalService;

	@Reference
	protected RulesEngine rulesEngine;

	@Reference
	protected SettingsFactory settingsFactory;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseOrderPriceCommerceEngineTask.class);

	private final Map<String, CommerceDiscountApplicationStrategy>
		_commerceDiscountApplicationStrategyMap = new ConcurrentHashMap<>();

}