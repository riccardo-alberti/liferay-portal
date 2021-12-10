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

package com.liferay.commerce.internal.price.util;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceMoneyFactoryUtil;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.discount.application.strategy.CommerceDiscountApplicationStrategy;
import com.liferay.commerce.discount.constants.CommerceDiscountConstants;
import com.liferay.commerce.discount.service.CommerceDiscountLocalService;
import com.liferay.commerce.discount.validator.helper.CommerceDiscountValidatorHelper;
import com.liferay.commerce.internal.function.DiscountFixedAmountFunction;
import com.liferay.commerce.internal.function.DiscountPercentageFunction;
import com.liferay.commerce.internal.function.GroupMaxFunction;
import com.liferay.commerce.internal.function.GroupMinFunction;
import com.liferay.commerce.internal.function.MinGreaterThanZeroFunction;
import com.liferay.commerce.internal.function.ModifierFixedAmountFunction;
import com.liferay.commerce.internal.function.ModifierPercentageFunction;
import com.liferay.commerce.internal.function.ModifierReplaceFunction;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.redis.RedisConnection;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import net.objecthunter.exp4j.ExpressionBuilder;

/**
 * @author Riccardo Alberti
 */
public class CalculatePrice {

	public static CommerceProductPrice getCommerceProductPrice(
			long cpInstanceId, int quantity, CommerceContext commerceContext,
			CommerceDiscountApplicationStrategy
				commerceDiscountApplicationStrategy,
			CommerceDiscountValidatorHelper commerceDiscountValidatorHelper,
			CommerceDiscountLocalService commerceDiscountLocalService,
			RedisConnection redisConnection)
		throws PortalException {

		Set<String> keys = _generateKeys(commerceContext, cpInstanceId);

		Map<Integer, PriceValue> priceValueMap = null;

		for (String key : keys) {
			priceValueMap = (Map<Integer, PriceValue>)redisConnection.get(key);

			if (priceValueMap != null) {
				break;
			}
		}

		Set<Integer> integers = priceValueMap.keySet();

		Integer index = integers.stream(
		).filter(
			key -> key < quantity
		).max(
			Integer::max
		).orElse(
			1
		);

		PriceValue priceValue = priceValueMap.get(index);

		double unitPriceDouble = _getPriceValue(
			priceValue.getUnitPrice(), quantity);

		double promoPriceDouble = _getPriceValue(
			priceValue.getPromoPrice(), quantity);

		HashSet<Long> discountIds = priceValue.getDiscountIds();

		Map<String, Double> discountMap = new HashMap<>();

		for (long discountId : discountIds) {
			boolean valid = commerceDiscountValidatorHelper.isValid(
				commerceContext,
				commerceDiscountLocalService.getCommerceDiscount(discountId),
				CommerceDiscountConstants.VALIDATOR_TYPE_POST_QUALIFICATION);

			discountMap.put("commerceDiscount" + discountId, valid ? 1d : 0d);
		}

		HashMap<String, String> discount = priceValue.getDiscount();

		double finalPriceDouble;

		if ((promoPriceDouble > 0) && (promoPriceDouble < unitPriceDouble)) {
			finalPriceDouble = promoPriceDouble * quantity;
		}
		else {
			finalPriceDouble = unitPriceDouble * quantity;
		}

		BigDecimal[] levels = {
			_getLevel(
				finalPriceDouble,
				_getDiscountLevelValue(
					discount.get(CommerceDiscountConstants.LEVEL_L1), quantity,
					unitPriceDouble, promoPriceDouble, discountMap)),
			_getLevel(
				finalPriceDouble,
				_getDiscountLevelValue(
					discount.get(CommerceDiscountConstants.LEVEL_L2), quantity,
					unitPriceDouble, promoPriceDouble, discountMap)),
			_getLevel(
				finalPriceDouble,
				_getDiscountLevelValue(
					discount.get(CommerceDiscountConstants.LEVEL_L3), quantity,
					unitPriceDouble, promoPriceDouble, discountMap)),
			_getLevel(
				finalPriceDouble,
				_getDiscountLevelValue(
					discount.get(CommerceDiscountConstants.LEVEL_L4), quantity,
					unitPriceDouble, promoPriceDouble, discountMap))
		};

		BigDecimal finalPrice = BigDecimal.valueOf(finalPriceDouble);

		BigDecimal discountedAmount =
			commerceDiscountApplicationStrategy.applyCommerceDiscounts(
				finalPrice, levels);

		CommerceProductPriceImpl commerceProductPriceImpl =
			new CommerceProductPriceImpl();

		commerceProductPriceImpl.setQuantity(quantity);
		commerceProductPriceImpl.setUnitPrice(
			CommerceMoneyFactoryUtil.create(
				priceValue.getCommerceCurrencyId(),
				BigDecimal.valueOf(unitPriceDouble)));
		commerceProductPriceImpl.setUnitPromoPrice(
			CommerceMoneyFactoryUtil.create(
				priceValue.getCommerceCurrencyId(),
				BigDecimal.valueOf(promoPriceDouble)));
		commerceProductPriceImpl.setCommerceDiscountValue(
			new CommerceDiscountValue(
				0,
				CommerceMoneyFactoryUtil.create(
					priceValue.getCommerceCurrencyId(),
					finalPrice.subtract(discountedAmount)),
				_getDiscountPercentage(
					discountedAmount, finalPriceDouble, RoundingMode.HALF_EVEN),
				levels));
		commerceProductPriceImpl.setFinalPrice(
			CommerceMoneyFactoryUtil.create(
				priceValue.getCommerceCurrencyId(), discountedAmount));

		return commerceProductPriceImpl;
	}

	private static Set<String> _generateKeys(
			CommerceContext commerceContext, long cpInstanceId)
		throws PortalException {

		long commerceAccountId = 0;
		CommerceAccount commerceAccount = commerceContext.getCommerceAccount();

		if (commerceAccount != null) {
			commerceAccountId = commerceAccount.getCommerceAccountId();
		}

		String commerceAccountIdString =
			(commerceAccountId == 0) ? "" : String.valueOf(commerceAccountId);

		long commerceChannelId = commerceContext.getCommerceChannelId();

		String commerceChannelIdString =
			(commerceChannelId == 0) ? "" : String.valueOf(commerceChannelId);

		long commerceOrderTypeId = 0;

		CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

		if (commerceOrder != null) {
			commerceOrderTypeId = commerceOrder.getCommerceOrderTypeId();
		}

		String commerceOrderTypeIdString =
			(commerceOrderTypeId == 0) ? "" :
				String.valueOf(commerceOrderTypeId);

		Set<String> keys = new LinkedHashSet<>();

		keys.add(
			StringBundler.concat(
				"/", commerceAccountIdString, "//", commerceChannelIdString,
				"/", commerceOrderTypeIdString, "/",
				String.valueOf(cpInstanceId)));

		keys.add(
			StringBundler.concat(
				"/", commerceAccountIdString, "//", "/",
				commerceOrderTypeIdString, "/", String.valueOf(cpInstanceId)));

		keys.add(
			StringBundler.concat(
				"/", commerceAccountIdString, "//", commerceChannelIdString,
				"/", "/", String.valueOf(cpInstanceId)));

		keys.add(
			StringBundler.concat(
				"/", commerceAccountIdString, "//", "/", "/",
				String.valueOf(cpInstanceId)));

		keys.add(
			StringBundler.concat(
				"/", "//", commerceChannelIdString, "/",
				commerceOrderTypeIdString, "/", String.valueOf(cpInstanceId)));

		keys.add(
			StringBundler.concat(
				"/", "//", "/", commerceOrderTypeIdString, "/",
				String.valueOf(cpInstanceId)));

		keys.add(
			StringBundler.concat(
				"/", "//", commerceChannelIdString, "/", "/",
				String.valueOf(cpInstanceId)));

		keys.add(
			StringBundler.concat(
				"/", "//", "/", "/", String.valueOf(cpInstanceId)));

		return keys;
	}

	private static double _getDiscountLevelValue(
		String expression, int quantity, double unitPriceDouble,
		double promoPriceDouble, Map<String, Double> discountMap) {

		return new ExpressionBuilder(
			expression
		).variables(
			"q", "unitPrice", "promoPrice"
		).variables(
			discountMap.keySet()
		).functions(
			new GroupMinFunction("groupMin"), new GroupMaxFunction("groupMax"),
			new ModifierFixedAmountFunction("modifierFixedAmount", 2),
			new ModifierPercentageFunction("modifierPercentage", 2),
			new ModifierReplaceFunction("modifierReplace", 1),
			new DiscountPercentageFunction("discountPercentage", 3),
			new DiscountFixedAmountFunction("discountReplace", 2),
			new MinGreaterThanZeroFunction("minGreaterThanZero", 2)
		).build(
		).setVariable(
			"q", quantity
		).setVariable(
			"unitPrice", unitPriceDouble
		).setVariable(
			"promoPrice", promoPriceDouble
		).setVariables(
			discountMap
		).evaluate();
	}

	private static BigDecimal _getDiscountPercentage(
		BigDecimal discountedAmount, double originalPrice,
		RoundingMode roundingMode) {

		double percentage = 100 * discountedAmount.doubleValue() / originalPrice;

		BigDecimal discountPercentage = new BigDecimal(percentage);

		MathContext mathContext = new MathContext(
			discountPercentage.precision(), roundingMode);

		return _ONE_HUNDRED.subtract(discountPercentage, mathContext);
	}

	private static BigDecimal _getLevel(
		double finalPriceDouble, double levelDiscount) {

		if (finalPriceDouble == 0) {
			return BigDecimal.valueOf(100);
		}

		return BigDecimal.valueOf(100 * levelDiscount / finalPriceDouble);
	}

	private static double _getPriceValue(String expression, int quantity) {
		return new ExpressionBuilder(
			expression
		).variable(
			"q"
		).functions(
			new GroupMinFunction("groupMin"),
			new ModifierFixedAmountFunction("modifierFixedAmount", 2),
			new ModifierPercentageFunction("modifierPercentage", 2),
			new ModifierReplaceFunction("modifierReplace", 1)
		).build(
		).setVariable(
			"q", quantity
		).evaluate();
	}

	private static final BigDecimal _ONE_HUNDRED = BigDecimal.valueOf(100);

}