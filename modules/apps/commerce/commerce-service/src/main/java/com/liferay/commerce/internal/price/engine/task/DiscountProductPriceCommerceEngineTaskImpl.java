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

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.discount.CommerceDiscountCalculation;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.discount.application.strategy.CommerceDiscountApplicationStrategy;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.internal.util.CommercePriceConverterUtil;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.commerce.util.CommerceBigDecimalUtil;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import java.util.Locale;
import java.util.Map;

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

	public static final int PRIORITY = 50;

	public static final String TYPE = "product-pricing";

	@Override
	public boolean evaluate(Map<String, Object> context) {
		return true;
	}

	@Override
	public void execute(Map<String, Object> context) throws Exception {
		BigDecimal finalUnitCommercePrice = (BigDecimal)context.get(
			"_finalUnitPrice");
		BigDecimal finalUnitCommercePriceWithTaxAmount =
			(BigDecimal)context.get("_finalUnitPriceWithTaxAmount");

		long finalCommercePriceListId = (Long)context.get(
			"_finalCommercePriceListId");

		long cpInstanceId = (Long)context.get("_cpInstanceId");

		int quantity = (Integer)context.get("_quantity");

		CommerceContext commerceContext = (CommerceContext)context.get(
			"_commerceContext");

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
				finalUnitCommercePrice, context, discountsTargetNetPrice);
		}
		else {
			_applyCommerceDiscounts(
				cpInstanceId, finalCommercePriceListId, quantity,
				finalUnitCommercePriceWithTaxAmount, context,
				discountsTargetNetPrice);
		}
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
	public boolean isActive(Map<String, Object> context) {
		return true;
	}

	private void _applyCommerceDiscounts(
			long cpInstanceId, long commercePriceListId, int quantity,
			BigDecimal commercePrice, Map<String, Object> context,
			boolean discountsTargetNetPrice)
		throws Exception {

		CommerceContext commerceContext = (CommerceContext)context.get(
			"_commerceContext");

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

		if (discountsTargetNetPrice) {
			context.put(
				"_finalPrice",
				getConvertedPrice(
					cpInstanceId, commercePrice, true, commerceContext));

			context.put("_finalPriceWithTaxAmount", discountedCommercePrice);

			context.put(
				"_commerceDiscountValue",
				CommercePriceConverterUtil.getConvertedCommerceDiscountValue(
					commerceDiscountValue,
					commercePrice.multiply(BigDecimal.valueOf(quantity)),
					discountedCommercePrice, commerceMoneyFactory,
					RoundingMode.valueOf(commerceCurrency.getRoundingMode())));

			context.put(
				"_commerceDiscountValueWithTaxAmount", commerceDiscountValue);
		}
		else {
			context.put("_finalPrice", discountedCommercePrice);

			context.put(
				"_finalPriceWithTaxAmount",
				getConvertedPrice(
					cpInstanceId, commercePrice, true, commerceContext));

			context.put("_commerceDiscountValue", commerceDiscountValue);

			context.put(
				"_commerceDiscountValueWithTaxAmount",
				CommercePriceConverterUtil.getConvertedCommerceDiscountValue(
					commerceDiscountValue,
					commercePrice.multiply(BigDecimal.valueOf(quantity)),
					discountedCommercePrice, commerceMoneyFactory,
					RoundingMode.valueOf(commerceCurrency.getRoundingMode())));
		}
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

	@Reference
	private CommerceChannelLocalService _commerceChannelLocalService;

	@Reference(target = "(commerce.discount.calculation.key=v2.0)")
	private CommerceDiscountCalculation _commerceDiscountCalculation;

}