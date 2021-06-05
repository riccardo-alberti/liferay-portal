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

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.configuration.PriceCommerceEngineTaskConfiguration;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoneyFactory;
import com.liferay.commerce.currency.service.CommerceCurrencyLocalService;
import com.liferay.commerce.discount.application.strategy.CommerceDiscountApplicationStrategy;
import com.liferay.commerce.engine.CommerceEngine;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.internal.util.CommercePriceConverterUtil;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.price.engine.task.ProductPriceCommerceEngineTaskContext;
import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.discovery.CommercePriceListDiscovery;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommercePriceEntryLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryLocalService;
import com.liferay.commerce.pricing.configuration.CommercePricingConfiguration;
import com.liferay.commerce.pricing.constants.CommercePricingConstants;
import com.liferay.commerce.pricing.modifier.CommercePriceModifierHelper;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.option.CommerceOptionValue;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.tax.CommerceTaxCalculation;
import com.liferay.commerce.util.CommerceBigDecimalUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
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
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Riccardo Alberti
 */
public abstract class BaseProductPriceCommerceEngineTask
	implements CommerceEngineTask<ProductPriceCommerceEngineTaskContext> {

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
			ProductPriceCommerceEngineTaskContext
				productPriceCommerceEngineTaskContext)
		throws Exception {

		return Optional.empty();
	}

	@Override
	public Optional<CommerceEngineTask> handleException(
			Exception exception,
			ProductPriceCommerceEngineTaskContext
				productPriceCommerceEngineTaskContext)
		throws Exception {

		return Optional.empty();
	}

	@Override
	public boolean isActive(long groupId) {
		return true;
	}

	@Override
	public boolean isWorkflowEnabled(
		ProductPriceCommerceEngineTaskContext
			productPriceCommerceEngineTaskContext) {

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

	public void unsetCommercePriceListDiscovery(
		CommercePriceListDiscovery commercePriceListDiscovery,
		Map<String, Object> properties) {

		String commercePriceListDiscoveryKey = GetterUtil.getString(
			properties.get("commerce.price.list.discovery.key"));

		_commercePriceListDiscoveryMap.remove(commercePriceListDiscoveryKey);
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
			ProductPriceCommerceEngineTaskContext
				productPriceCommerceEngineTaskContext)
		throws RulesEngineException {

		if (Validator.isBlank(rule)) {
			return true;
		}

		RulesResourceRetriever rulesResourceRetriever =
			new RulesResourceRetriever(new StringResourceRetriever(rule));

		productPriceCommerceEngineTaskContext.setConditionValid(false);

		rulesEngine.execute(
			rulesResourceRetriever,
			Arrays.asList(
				new Fact<ProductPriceCommerceEngineTaskContext>(
					"context", productPriceCommerceEngineTaskContext)));

		return productPriceCommerceEngineTaskContext.isConditionValid();
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
			getCommerceEngineTaskConfiguration(long groupId)
		throws ConfigurationException {

		return configurationProvider.getConfiguration(
			PriceCommerceEngineTaskConfiguration.class,
			new GroupServiceSettingsLocator(
				groupId, CommerceConstants.SERVICE_NAME_PRICE));
	}

	protected BigDecimal getCommerceOptionValuesPrice(
			long commerceChannelGroupId,
			List<CommerceOptionValue> commerceOptionValues)
		throws Exception {

		if ((commerceOptionValues == null) || commerceOptionValues.isEmpty()) {
			return BigDecimal.ZERO;
		}

		BigDecimal optionValuesPrice = BigDecimal.ZERO;

		for (CommerceOptionValue commerceOptionValue : commerceOptionValues) {
			if (_isStaticPriceType(commerceOptionValue.getPriceType())) {
				BigDecimal optionValuePrice = commerceOptionValue.getPrice();

				if ((optionValuePrice != null) &&
					CommerceBigDecimalUtil.gt(
						optionValuePrice, BigDecimal.ZERO)) {

					if (commerceOptionValue.getCPInstanceId() > 0) {
						optionValuePrice = optionValuePrice.multiply(
							BigDecimal.valueOf(
								commerceOptionValue.getQuantity()));
					}

					optionValuesPrice = optionValuesPrice.add(optionValuePrice);
				}
			}
			else if (Objects.equals(
						commerceOptionValue.getPriceType(),
						CPConstants.PRODUCT_OPTION_PRICE_TYPE_DYNAMIC)) {

				ProductPriceCommerceEngineTaskContext
					productPriceCommerceEngineTaskContext =
						new ProductPriceCommerceEngineTaskContext();

				productPriceCommerceEngineTaskContext.setCpInstanceId(
					commerceOptionValue.getCPInstanceId());
				productPriceCommerceEngineTaskContext.setQuantity(
					commerceOptionValue.getQuantity());

				commerceEngine.executeTasks(
					commerceChannelGroupId, "product-pricing",
					productPriceCommerceEngineTaskContext);

				optionValuesPrice = optionValuesPrice.add(
					productPriceCommerceEngineTaskContext.getFinalPrice());
			}
		}

		return optionValuesPrice;
	}

	protected BigDecimal getCommercePrice(
			long commercePriceListId, long cpInstanceId, int quantity)
		throws Exception {

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryLocalService.fetchCommercePriceEntry(
				commercePriceListId, cpInstance.getCPInstanceUuid(), false);

		if (commercePriceEntry == null) {
			return BigDecimal.ZERO;
		}

		BigDecimal commercePrice = commercePriceEntry.getPrice();

		if (!commercePriceEntry.isHasTierPrice()) {
			return commercePrice;
		}

		if (commercePriceEntry.isBulkPricing()) {
			CommerceTierPriceEntry commerceTierPriceEntry =
				commerceTierPriceEntryLocalService.
					findClosestCommerceTierPriceEntry(
						commercePriceEntry.getCommercePriceEntryId(), quantity);

			if (commerceTierPriceEntry == null) {
				return commercePriceEntry.getPrice();
			}

			return commerceTierPriceEntry.getPrice();
		}

		List<CommerceTierPriceEntry> commerceTierPriceEntries =
			commerceTierPriceEntryLocalService.findCommerceTierPriceEntries(
				commercePriceEntry.getCommercePriceEntryId(), quantity);

		if (commerceTierPriceEntries.isEmpty()) {
			return commercePrice;
		}

		commercePrice = BigDecimal.ZERO;

		CommerceTierPriceEntry commerceTierPriceEntry1 =
			commerceTierPriceEntries.get(0);

		int totalTierCounter = 0;

		int tierCounter =
			commerceTierPriceEntry1.getMinQuantity() - totalTierCounter - 1;

		BigDecimal currentPrice = commercePriceEntry.getPrice();

		currentPrice = currentPrice.multiply(BigDecimal.valueOf(tierCounter));

		commercePrice = commercePrice.add(currentPrice);

		totalTierCounter += tierCounter;

		for (int i = 0; i < (commerceTierPriceEntries.size() - 1); i++) {
			CommerceTierPriceEntry commerceTierPriceEntry2 =
				commerceTierPriceEntries.get(i);

			currentPrice = commerceTierPriceEntry2.getPrice();

			CommerceTierPriceEntry commerceTierPriceEntry3 =
				commerceTierPriceEntries.get(i + 1);

			tierCounter =
				commerceTierPriceEntry3.getMinQuantity() - totalTierCounter - 1;

			currentPrice = currentPrice.multiply(
				BigDecimal.valueOf(tierCounter));

			commercePrice = commercePrice.add(currentPrice);

			totalTierCounter += tierCounter;
		}

		totalTierCounter = quantity - totalTierCounter;

		CommerceTierPriceEntry commerceTierPriceEntry2 =
			commerceTierPriceEntries.get(commerceTierPriceEntries.size() - 1);

		currentPrice = commerceTierPriceEntry2.getPrice();

		currentPrice = currentPrice.multiply(
			BigDecimal.valueOf(totalTierCounter));

		commercePrice = commercePrice.add(currentPrice);

		CommercePriceList commercePriceList =
			commercePriceListLocalService.getCommercePriceList(
				commercePriceEntry.getCommercePriceListId());

		CommerceCurrency commerceCurrency =
			commerceCurrencyLocalService.getCommerceCurrency(
				commercePriceList.getCommerceCurrencyId());

		RoundingMode roundingMode = RoundingMode.valueOf(
			commerceCurrency.getRoundingMode());

		return commercePrice.divide(
			BigDecimal.valueOf(quantity), _SCALE, roundingMode);
	}

	protected CommercePriceList getCommercePriceList(
			long commerceChannelId, long cpInstanceId,
			String commercePriceListType, CommerceAccount commerceAccount)
		throws Exception {

		long commerceAccountId = 0;

		if (commerceAccount != null) {
			commerceAccountId = commerceAccount.getCommerceAccountId();
		}

		CommercePriceListDiscovery commercePriceListDiscovery =
			getCommercePriceListDiscovery(commercePriceListType);

		if (commercePriceListDiscovery == null) {
			return null;
		}

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		return commercePriceListDiscovery.getCommercePriceList(
			cpInstance.getGroupId(), commerceAccountId, commerceChannelId,
			cpInstance.getCPInstanceUuid(), commercePriceListType);
	}

	protected CommercePriceListDiscovery getCommercePriceListDiscovery(
			String commercePriceListType)
		throws Exception {

		CommercePricingConfiguration commercePricingConfiguration =
			configurationProvider.getSystemConfiguration(
				CommercePricingConfiguration.class);

		String discoveryMethod = CommercePricingConstants.ORDER_BY_HIERARCHY;

		if (commercePriceListType.equals(
				CommercePriceListConstants.TYPE_PRICE_LIST)) {

			discoveryMethod =
				commercePricingConfiguration.commercePriceListDiscovery();
		}
		else if (commercePriceListType.equals(
					CommercePriceListConstants.TYPE_PROMOTION)) {

			discoveryMethod =
				commercePricingConfiguration.commercePromotionDiscovery();
		}

		if (!_commercePriceListDiscoveryMap.containsKey(discoveryMethod)) {
			if (_log.isWarnEnabled()) {
				_log.warn(
					"No commerce price list discovery specified for " +
						discoveryMethod);
			}

			return null;
		}

		return _commercePriceListDiscoveryMap.get(discoveryMethod);
	}

	protected BigDecimal getConvertedPrice(
			long commerceChannelGroupId, long cpInstanceId, BigDecimal price,
			boolean includeTax, CommerceAccount commerceAccount,
			CommerceOrder commerceOrder)
		throws PortalException {

		long commerceBillingAddressId = 0;
		long commerceShippingAddressId = 0;

		if (commerceOrder != null) {
			commerceChannelGroupId = commerceOrder.getGroupId();
			commerceBillingAddressId = commerceOrder.getBillingAddressId();
			commerceShippingAddressId = commerceOrder.getShippingAddressId();
		}
		else {
			if (commerceAccount != null) {
				commerceBillingAddressId =
					commerceAccount.getDefaultBillingAddressId();
				commerceShippingAddressId =
					commerceAccount.getDefaultShippingAddressId();
			}
		}

		return CommercePriceConverterUtil.getConvertedPrice(
			commerceChannelGroupId, cpInstanceId, commerceBillingAddressId,
			commerceShippingAddressId, price, includeTax,
			commerceTaxCalculation);
	}

	protected boolean hasCommercePriceModifiers(
			long commercePriceListId, long cpInstanceId)
		throws Exception {

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		boolean hasCommercePriceModifiers =
			commercePriceModifierHelper.hasCommercePriceModifiers(
				commercePriceListId, cpInstance.getCPDefinitionId());

		if (hasCommercePriceModifiers) {
			return true;
		}

		return false;
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

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY
	)
	protected void setCommercePriceListDiscovery(
		CommercePriceListDiscovery commercePriceListDiscovery,
		Map<String, Object> properties) {

		String commercePriceListDiscoveryKey = GetterUtil.getString(
			properties.get("commerce.price.list.discovery.key"));

		_commercePriceListDiscoveryMap.put(
			commercePriceListDiscoveryKey, commercePriceListDiscovery);
	}

	@Reference
	protected CommerceCurrencyLocalService commerceCurrencyLocalService;

	@Reference
	protected CommerceEngine commerceEngine;

	@Reference
	protected CommerceMoneyFactory commerceMoneyFactory;

	@Reference
	protected CommercePriceEntryLocalService commercePriceEntryLocalService;

	@Reference
	protected CommercePriceListLocalService commercePriceListLocalService;

	@Reference
	protected CommercePriceModifierHelper commercePriceModifierHelper;

	@Reference
	protected CommerceTaxCalculation commerceTaxCalculation;

	@Reference
	protected CommerceTierPriceEntryLocalService
		commerceTierPriceEntryLocalService;

	@Reference
	protected ConfigurationProvider configurationProvider;

	@Reference
	protected CPInstanceLocalService cpInstanceLocalService;

	@Reference
	protected RulesEngine rulesEngine;

	@Reference
	protected SettingsFactory settingsFactory;

	private boolean _isStaticPriceType(String value) {
		if (Objects.equals(
				value, CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC)) {

			return true;
		}

		return false;
	}

	private static final int _SCALE = 10;

	private static final Log _log = LogFactoryUtil.getLog(
		BaseProductPriceCommerceEngineTask.class);

	private final Map<String, CommerceDiscountApplicationStrategy>
		_commerceDiscountApplicationStrategyMap = new ConcurrentHashMap<>();
	private final Map<String, CommercePriceListDiscovery>
		_commercePriceListDiscoveryMap = new ConcurrentHashMap<>();

}