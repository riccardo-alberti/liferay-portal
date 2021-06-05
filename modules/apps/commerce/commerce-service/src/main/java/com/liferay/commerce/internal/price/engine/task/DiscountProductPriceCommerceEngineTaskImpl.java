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
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.discount.CommerceDiscountCalculation;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.discount.application.strategy.CommerceDiscountApplicationStrategy;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.internal.util.CommercePriceConverterUtil;
import com.liferay.commerce.price.engine.task.ProductPriceCommerceEngineTaskContext;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.util.CommerceBigDecimalUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"commerce.engine.task.key=" + DiscountProductPriceCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + DiscountProductPriceCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + DiscountProductPriceCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class DiscountProductPriceCommerceEngineTaskImpl
	extends BaseProductPriceCommerceEngineTask {

	public static final String KEY = "discount-price";

	public static final int PRIORITY = 35;

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
					discountPriceEvaluateCondition(),
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

		BigDecimal finalUnitCommercePrice =
			productPriceCommerceEngineTaskContext.getFinalUnitPrice();
		BigDecimal finalUnitCommercePriceWithTaxAmount =
			productPriceCommerceEngineTaskContext.
				getFinalUnitPriceWithTaxAmount();

		long finalCommercePriceListId =
			productPriceCommerceEngineTaskContext.getFinalCommercePriceListId();

		long cpInstanceId =
			productPriceCommerceEngineTaskContext.getCpInstanceId();

		int quantity = productPriceCommerceEngineTaskContext.getQuantity();

		CommerceContext commerceContext =
			productPriceCommerceEngineTaskContext.getCommerceContext();

		boolean discountsTargetNetPrice = true;

		CommerceChannel commerceChannel =
			_commerceChannelLocalService.fetchCommerceChannel(
				commerceContext.getCommerceChannelId());

		if (commerceChannel != null) {
			discountsTargetNetPrice =
				commerceChannel.isDiscountsTargetNetPrice();
		}

		if (discountsTargetNetPrice) {
			_applyCommerceDiscounts(
				cpInstanceId, finalCommercePriceListId, quantity,
				finalUnitCommercePrice, productPriceCommerceEngineTaskContext,
				discountsTargetNetPrice);
		}
		else {
			_applyCommerceDiscounts(
				cpInstanceId, finalCommercePriceListId, quantity,
				finalUnitCommercePriceWithTaxAmount,
				productPriceCommerceEngineTaskContext, discountsTargetNetPrice);
		}
	}

	@Override
	public String getEvaluateCondition(long groupId) {
		try {
			PriceCommerceEngineTaskConfiguration
				priceCommerceEngineTaskConfiguration =
					getCommerceEngineTaskConfiguration(groupId);

			return priceCommerceEngineTaskConfiguration.
				discountPriceEvaluateCondition();
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

			return priceCommerceEngineTaskConfiguration.discountPrice();
		}
		catch (PortalException portalException) {
			if (_log.isDebugEnabled()) {
				_log.debug(portalException, portalException);
			}
		}

		return true;
	}

	private void _applyCommerceDiscounts(
			long cpInstanceId, long commercePriceListId, int quantity,
			BigDecimal commercePrice,
			ProductPriceCommerceEngineTaskContext
				productPriceCommerceEngineTaskContext,
			boolean discountsTargetNetPrice)
		throws Exception {

		CommerceContext commerceContext =
			productPriceCommerceEngineTaskContext.getCommerceContext();

		CommerceDiscountValue commerceDiscountValue = _getCommerceDiscountValue(
			cpInstanceId, commercePriceListId, quantity, commercePrice,
			commerceContext);

		BigDecimal discountedCommercePrice = commercePrice.multiply(
			BigDecimal.valueOf(quantity));

		if (commerceDiscountValue != null) {
			CommerceMoney discountAmountCommerceMoney =
				commerceDiscountValue.getDiscountAmount();

			discountedCommercePrice = discountedCommercePrice.subtract(
				discountAmountCommerceMoney.getPrice());
		}

		CommerceCurrency commerceCurrency =
			commerceContext.getCommerceCurrency();

		RoundingMode roundingMode = RoundingMode.valueOf(
			commerceCurrency.getRoundingMode());

		BigDecimal finalUnitPrice;
		BigDecimal finalUnitPriceWithTaxAmount;

		if (discountsTargetNetPrice) {
			BigDecimal finalPriceWithTaxAmount =
				productPriceCommerceEngineTaskContext.
					getFinalPriceWithTaxAmount();

			finalUnitPrice = discountedCommercePrice.divide(
				BigDecimal.valueOf(quantity), roundingMode);

			BigDecimal convertedPrice = getConvertedPrice(
				commerceContext.getCommerceChannelGroupId(), cpInstanceId,
				discountedCommercePrice, false,
				commerceContext.getCommerceAccount(),
				commerceContext.getCommerceOrder());

			finalUnitPriceWithTaxAmount = convertedPrice.divide(
				BigDecimal.valueOf(quantity), roundingMode);

			productPriceCommerceEngineTaskContext.setFinalPrice(
				discountedCommercePrice);

			productPriceCommerceEngineTaskContext.setFinalPriceWithTaxAmount(
				convertedPrice);

			productPriceCommerceEngineTaskContext.setCommerceDiscountValue(
				commerceDiscountValue);

			productPriceCommerceEngineTaskContext.
				setCommerceDiscountValueWithTaxAmount(
					CommercePriceConverterUtil.
						getConvertedCommerceDiscountValue(
							commerceDiscountValue, finalPriceWithTaxAmount,
							convertedPrice, commerceMoneyFactory,
							roundingMode));
		}
		else {
			BigDecimal finalPrice =
				productPriceCommerceEngineTaskContext.getFinalPrice();

			finalUnitPriceWithTaxAmount = discountedCommercePrice.divide(
				BigDecimal.valueOf(quantity), roundingMode);

			BigDecimal convertedPrice = getConvertedPrice(
				commerceContext.getCommerceChannelGroupId(), cpInstanceId,
				discountedCommercePrice, true,
				commerceContext.getCommerceAccount(),
				commerceContext.getCommerceOrder());

			finalUnitPrice = convertedPrice.divide(
				BigDecimal.valueOf(quantity), roundingMode);

			productPriceCommerceEngineTaskContext.setFinalPrice(convertedPrice);

			productPriceCommerceEngineTaskContext.setFinalPriceWithTaxAmount(
				discountedCommercePrice);

			productPriceCommerceEngineTaskContext.setCommerceDiscountValue(
				CommercePriceConverterUtil.getConvertedCommerceDiscountValue(
					commerceDiscountValue, finalPrice, discountedCommercePrice,
					commerceMoneyFactory, roundingMode));

			productPriceCommerceEngineTaskContext.
				setCommerceDiscountValueWithTaxAmount(commerceDiscountValue);
		}

		productPriceCommerceEngineTaskContext.setFinalUnitPrice(finalUnitPrice);
		productPriceCommerceEngineTaskContext.setFinalUnitPriceWithTaxAmount(
			finalUnitPriceWithTaxAmount);
	}

	private CommerceDiscountValue _calculateCommerceDiscountValue(
			BigDecimal[] values, int quantity, BigDecimal finalPrice,
			CommerceContext commerceContext)
		throws Exception {

		CommerceCurrency commerceCurrency =
			commerceContext.getCommerceCurrency();

		RoundingMode roundingMode = RoundingMode.valueOf(
			commerceCurrency.getRoundingMode());

		CommerceDiscountApplicationStrategy
			commerceDiscountApplicationStrategy =
				getCommerceDiscountApplicationStrategy();

		BigDecimal discountedAmount =
			commerceDiscountApplicationStrategy.applyCommerceDiscounts(
				finalPrice, values);

		BigDecimal currentDiscountAmount = finalPrice.subtract(
			discountedAmount);

		currentDiscountAmount = currentDiscountAmount.setScale(
			_SCALE, roundingMode);

		CommerceMoney discountAmountCommerceMoney = commerceMoneyFactory.create(
			commerceCurrency,
			currentDiscountAmount.multiply(new BigDecimal(quantity)));

		return new CommerceDiscountValue(
			0, discountAmountCommerceMoney,
			_getDiscountPercentage(discountedAmount, finalPrice, roundingMode),
			values);
	}

	private CommerceDiscountValue _getCommerceDiscountValue(
			long cpInstanceId, long commercePriceListId, int quantity,
			BigDecimal finalPrice, CommerceContext commerceContext)
		throws Exception {

		if ((finalPrice == null) ||
			CommerceBigDecimalUtil.lte(finalPrice, BigDecimal.ZERO)) {

			return null;
		}

		CPInstance cpInstance = cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CommercePriceEntry commercePriceEntry =
			commercePriceEntryLocalService.fetchCommercePriceEntry(
				commercePriceListId, cpInstance.getCPInstanceUuid(), true);

		if (commercePriceEntry == null) {
			return _commerceDiscountCalculation.getProductCommerceDiscountValue(
				cpInstanceId, quantity, finalPrice, commerceContext);
		}

		BigDecimal[] values = new BigDecimal[4];

		if (!commercePriceEntry.isHasTierPrice() &&
			!commercePriceEntry.isDiscountDiscovery()) {

			values[0] = commercePriceEntry.getDiscountLevel1();
			values[1] = commercePriceEntry.getDiscountLevel2();
			values[2] = commercePriceEntry.getDiscountLevel3();
			values[3] = commercePriceEntry.getDiscountLevel4();

			return _calculateCommerceDiscountValue(
				values, quantity, finalPrice, commerceContext);
		}

		if (!commercePriceEntry.isBulkPricing()) {
			return _commerceDiscountCalculation.getProductCommerceDiscountValue(
				cpInstanceId, quantity, finalPrice, commerceContext);
		}

		CommerceTierPriceEntry commerceTierPriceEntry =
			commerceTierPriceEntryLocalService.
				findClosestCommerceTierPriceEntry(
					commercePriceEntry.getCommercePriceEntryId(), quantity);

		if ((commerceTierPriceEntry == null) ||
			commerceTierPriceEntry.isDiscountDiscovery()) {

			return _commerceDiscountCalculation.getProductCommerceDiscountValue(
				cpInstanceId, quantity, finalPrice, commerceContext);
		}

		values[0] = commerceTierPriceEntry.getDiscountLevel1();
		values[1] = commerceTierPriceEntry.getDiscountLevel2();
		values[2] = commerceTierPriceEntry.getDiscountLevel3();
		values[3] = commerceTierPriceEntry.getDiscountLevel4();

		return _calculateCommerceDiscountValue(
			values, quantity, finalPrice, commerceContext);
	}

	private BigDecimal _getDiscountPercentage(
		BigDecimal discountedAmount, BigDecimal amount,
		RoundingMode roundingMode) {

		double actualPrice = discountedAmount.doubleValue();
		double originalPrice = amount.doubleValue();

		double percentage = actualPrice / originalPrice;

		BigDecimal discountPercentage = new BigDecimal(percentage);

		discountPercentage = discountPercentage.multiply(_ONE_HUNDRED);

		MathContext mathContext = new MathContext(
			discountPercentage.precision(), roundingMode);

		return _ONE_HUNDRED.subtract(discountPercentage, mathContext);
	}

	private static final BigDecimal _ONE_HUNDRED = BigDecimal.valueOf(100);

	private static final int _SCALE = 10;

	private static final Log _log = LogFactoryUtil.getLog(
		DiscountProductPriceCommerceEngineTaskImpl.class);

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference(target = "(commerce.discount.calculation.key=v2.0)")
	private CommerceDiscountCalculation _commerceDiscountCalculation;

}