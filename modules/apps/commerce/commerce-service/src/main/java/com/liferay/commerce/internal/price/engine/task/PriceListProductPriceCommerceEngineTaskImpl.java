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
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.price.engine.task.ProductPriceCommerceEngineTaskContext;
import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.math.BigDecimal;

import java.util.Locale;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"commerce.engine.task.key=" + PriceListProductPriceCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + PriceListProductPriceCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + PriceListProductPriceCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class PriceListProductPriceCommerceEngineTaskImpl
	extends BaseProductPriceCommerceEngineTask {

	public static final String KEY = "price-list-price";

	public static final int PRIORITY = 20;

	public static final String TYPE = "product-pricing";

	@Override
	public boolean evaluate(
		long groupId,
		ProductPriceCommerceEngineTaskContext
			productPriceCommerceEngineTaskContext) {

		try {
			PriceCommerceEngineTaskConfiguration
				priceCommerceEngineTaskConfiguration =
					getCommerceEngineTaskConfiguration(groupId);

			return evaluate(
				priceCommerceEngineTaskConfiguration.
					priceListPriceEvaluateCondition(),
				productPriceCommerceEngineTaskContext);
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return true;
	}

	@Override
	public void execute(
			ProductPriceCommerceEngineTaskContext
				productPriceCommerceEngineTaskContext)
		throws Exception {

		long cpInstanceId =
			productPriceCommerceEngineTaskContext.getCpInstanceId();

		CommerceContext commerceContext =
			productPriceCommerceEngineTaskContext.getCommerceContext();

		long commercePriceListId = _getCommercePriceListId(
			commerceContext.getCommerceChannelId(), cpInstanceId,
			commerceContext.getCommerceAccount());

		CommercePriceList commercePriceList =
			commercePriceListLocalService.fetchCommercePriceList(
				commercePriceListId);

		if (commercePriceList == null) {
			return;
		}

		int quantity = productPriceCommerceEngineTaskContext.getQuantity();

		BigDecimal commercePrice = getCommercePrice(
			commercePriceListId, cpInstanceId, quantity);

		BigDecimal optionValuesPrices = getCommerceOptionValuesPrice(
			commerceContext.getCommerceChannelGroupId(),
			productPriceCommerceEngineTaskContext.getCommerceOptionValues());

		commercePrice = commercePrice.add(optionValuesPrices);

		BigDecimal commercePriceWithTaxAmount = commercePrice;

		if (!commercePriceList.isNetPrice()) {
			commercePrice = getConvertedPrice(
				commerceContext.getCommerceChannelGroupId(), cpInstanceId,
				commercePrice, true, commerceContext.getCommerceAccount(),
				commerceContext.getCommerceOrder());
		}

		if (commercePrice.equals(BigDecimal.ZERO) &&
			hasCommercePriceModifiers(commercePriceListId, cpInstanceId)) {

			CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
				cpInstanceId);

			commercePrice =
				commercePriceModifierHelper.applyCommercePriceModifier(
					commercePriceListId, cpInstance.getCPDefinitionId(),
					commerceMoneyFactory.create(
						commerceContext.getCommerceCurrency(),
						productPriceCommerceEngineTaskContext.getUnitPrice()));
		}

		commercePrice = convertCommercePriceCurrency(
			commercePrice, commercePriceList.getCommerceCurrency(),
			commerceContext.getCommerceCurrency());

		commercePriceWithTaxAmount = convertCommercePriceCurrency(
			commercePriceWithTaxAmount, commercePriceList.getCommerceCurrency(),
			commerceContext.getCommerceCurrency());

		productPriceCommerceEngineTaskContext.setUnitPrice(commercePrice);
		productPriceCommerceEngineTaskContext.setUnitPriceWithTaxAmount(
			commercePriceWithTaxAmount);
		productPriceCommerceEngineTaskContext.setFinalUnitPrice(commercePrice);
		productPriceCommerceEngineTaskContext.setFinalUnitPriceWithTaxAmount(
			commercePriceWithTaxAmount);
		productPriceCommerceEngineTaskContext.setFinalPrice(
			commercePrice.multiply(BigDecimal.valueOf(quantity)));
		productPriceCommerceEngineTaskContext.setFinalPriceWithTaxAmount(
			commercePriceWithTaxAmount.multiply(BigDecimal.valueOf(quantity)));

		productPriceCommerceEngineTaskContext.setCommercePriceListId(
			commercePriceListId);
		productPriceCommerceEngineTaskContext.setFinalCommercePriceListId(
			commercePriceListId);
	}

	@Override
	public String getEvaluateCondition(long groupId) {
		try {
			PriceCommerceEngineTaskConfiguration
				priceCommerceEngineTaskConfiguration =
					getCommerceEngineTaskConfiguration(groupId);

			return priceCommerceEngineTaskConfiguration.
				priceListPriceEvaluateCondition();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return StringPool.BLANK;
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(locale, KEY);
	}

	@Override
	public Optional<CommerceEngineTask> getNext(
			ProductPriceCommerceEngineTaskContext
				productPriceCommerceEngineTaskContext)
		throws Exception {

		return Optional.empty();
	}

	@Override
	public int getPriority() {
		return PRIORITY;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public boolean isActive(long groupId) {
		try {
			PriceCommerceEngineTaskConfiguration
				priceCommerceEngineTaskConfiguration =
					getCommerceEngineTaskConfiguration(groupId);

			return priceCommerceEngineTaskConfiguration.priceListPrice();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return true;
	}

	private long _getCommercePriceListId(
			long commerceChannelId, long cpInstanceId,
			CommerceAccount commerceAccount)
		throws Exception {

		CommercePriceList commercePriceList = getCommercePriceList(
			commerceChannelId, cpInstanceId,
			CommercePriceListConstants.TYPE_PRICE_LIST, commerceAccount);

		long commercePriceListId = 0;

		if (commercePriceList != null) {
			commercePriceListId = commercePriceList.getCommercePriceListId();
		}

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryLocalService.fetchCommercePriceEntry(
				commercePriceListId, cpInstance.getCPInstanceUuid(), true);

		if (commercePriceEntry != null) {
			return commercePriceEntry.getCommercePriceListId();
		}

		if (hasCommercePriceModifiers(commercePriceListId, cpInstanceId)) {
			return commercePriceListId;
		}

		return 0;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PriceListProductPriceCommerceEngineTaskImpl.class);

}