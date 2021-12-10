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

package com.liferay.commerce.internal.action.executor;

import com.liferay.commerce.discount.constants.CommerceDiscountConstants;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.service.CommerceDiscountLocalService;
import com.liferay.commerce.internal.price.util.PriceValue;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.fulfilment.action.executor.BaseFulfilmentActionExecutor;
import com.liferay.fulfilment.constants.FulfilmentConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutorException;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = "com.liferay.portal.workflow.kaleo.runtime.action.executor.language=java",
	service = ActionExecutor.class
)
public class ProductDiscountFulfilmentActionExecutor
	extends BaseFulfilmentActionExecutor {

	@Override
	protected int doExecute(
			Map<String, Serializable> inputParameters,
			Map<String, Serializable> outputParameters)
		throws ActionExecutorException {

		long companyId = GetterUtil.getLong(inputParameters.get("companyId"));
		HashMap<String, Map<Integer, PriceValue>> priceMap =
			(HashMap<String, Map<Integer, PriceValue>>)GetterUtil.getObject(
				inputParameters.get("priceMap"), new HashMap<>());

		try {
			for (String key : priceMap.keySet()) {
				String[] keyEntry = key.split(StringPool.SLASH);

				long commerceAccountId = GetterUtil.getLong(keyEntry[1]);
				long commerceAccountGroupId = GetterUtil.getLong(keyEntry[2]);
				long commerceChannelId = GetterUtil.getLong(keyEntry[3]);
				long commerceOrderTypeId = GetterUtil.getLong(keyEntry[4]);
				long cpInstanceId = GetterUtil.getLong(keyEntry[5]);

				CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
					cpInstanceId);

				Map<Integer, PriceValue> priceValueMap = priceMap.get(key);

				List<CommerceDiscount> commerceDiscounts =
					_getCommerceDiscounts(
						companyId, commerceAccountId,
						new long[] {commerceAccountGroupId}, commerceChannelId,
						commerceOrderTypeId, cpInstance.getCPDefinitionId(),
						cpInstanceId);

				for (int tierMinQuantity : priceValueMap.keySet()) {
					PriceValue priceValue = priceValueMap.get(tierMinQuantity);

					HashMap<String, String> discountMap =
						priceValue.getDiscount();

					if (discountMap == null) {
						discountMap = new HashMap<>();
					}

					String level1 = "";
					String level2 = "";
					String level3 = "";
					String level4 = "";

					for (CommerceDiscount commerceDiscount :
							commerceDiscounts) {

						String discountValue = discountMap.get(
							commerceDiscount.getLevel());

						if (discountValue == null) {
							discountMap.put(commerceDiscount.getLevel(), "0");
						}

						priceValue.addDiscountId(
							commerceDiscount.getCommerceDiscountId());

						if (Objects.equals(
								CommerceDiscountConstants.LEVEL_L1,
								commerceDiscount.getLevel())) {

							level1 = StringBundler.concat(
								level1,
								_addCommerceDiscount(
									commerceDiscount.getCommerceDiscountId(),
									commerceDiscount.isUsePercentage(),
									commerceDiscount.getLevel1(),
									commerceDiscount.
										getMaximumDiscountAmount()),
								",");
						}
						else if (Objects.equals(
									CommerceDiscountConstants.LEVEL_L2,
									commerceDiscount.getLevel())) {

							level2 = StringBundler.concat(
								level2,
								_addCommerceDiscount(
									commerceDiscount.getCommerceDiscountId(),
									commerceDiscount.isUsePercentage(),
									commerceDiscount.getLevel2(),
									commerceDiscount.
										getMaximumDiscountAmount()),
								",");
						}
						else if (Objects.equals(
									CommerceDiscountConstants.LEVEL_L3,
									commerceDiscount.getLevel())) {

							level3 = StringBundler.concat(
								level3,
								_addCommerceDiscount(
									commerceDiscount.getCommerceDiscountId(),
									commerceDiscount.isUsePercentage(),
									commerceDiscount.getLevel3(),
									commerceDiscount.
										getMaximumDiscountAmount()),
								",");
						}
						else if (Objects.equals(
									CommerceDiscountConstants.LEVEL_L4,
									commerceDiscount.getLevel())) {

							level4 = StringBundler.concat(
								level4,
								_addCommerceDiscount(
									commerceDiscount.getCommerceDiscountId(),
									commerceDiscount.isUsePercentage(),
									commerceDiscount.getLevel4(),
									commerceDiscount.
										getMaximumDiscountAmount()),
								",");
						}
					}

					level1 = Validator.isBlank(level1) ? "0" :
						level1.substring(0, level1.length() - 1);
					level2 = Validator.isBlank(level2) ? "0" :
						level2.substring(0, level2.length() - 1);
					level3 = Validator.isBlank(level3) ? "0" :
						level3.substring(0, level3.length() - 1);
					level4 = Validator.isBlank(level4) ? "0" :
						level4.substring(0, level4.length() - 1);

					discountMap.put(
						CommerceDiscountConstants.LEVEL_L1,
						StringBundler.concat("groupMax(", level1, ")"));
					discountMap.put(
						CommerceDiscountConstants.LEVEL_L2,
						StringBundler.concat("groupMax(", level2, ")"));
					discountMap.put(
						CommerceDiscountConstants.LEVEL_L3,
						StringBundler.concat("groupMax(", level3, ")"));
					discountMap.put(
						CommerceDiscountConstants.LEVEL_L4,
						StringBundler.concat("groupMax(", level4, ")"));

					priceValue.setDiscount(discountMap);
				}
			}

			outputParameters.put("priceMap", priceMap);
		}
		catch (PortalException portalException) {
			_log.error(portalException, portalException);

			throw new ActionExecutorException(portalException);
		}

		return FulfilmentConstants.STATUS_COMPLETED;
	}

	private String _addCommerceDiscount(
		long commerceDiscountId, boolean usePercentage, BigDecimal amount,
		BigDecimal maxAmount) {

		String formulaDiscount;

		if (usePercentage) {
			formulaDiscount = StringBundler.concat(
				"commerceDiscount", commerceDiscountId,
				" * discountPercentage(minGreaterThanZero(unitPrice, promoPrice), ",
				amount.doubleValue(), ", ", maxAmount.doubleValue(), ")");
		}
		else {
			formulaDiscount = StringBundler.concat(
				"commerceDiscount", commerceDiscountId,
				" * discountFixedAmount(minGreaterThanZero(unitPrice, promoPrice), ",
				amount.doubleValue(), ")");
		}

		return formulaDiscount;
	}

	private List<CommerceDiscount> _getCommerceDiscounts(
			long companyId, long commerceAccountId,
			long[] commerceAccountGroupIds, long commerceChannelId,
			long commerceOrderTypeId, long cpDefinitionId, long cpInstanceId)
		throws PortalException {

		List<CommerceDiscount> commerceDiscounts =
			_commerceDiscountLocalService.
				getAccountAndChannelAndOrderTypeCommerceDiscounts(
					commerceAccountId, commerceChannelId, commerceOrderTypeId,
					cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			_commerceDiscountLocalService.getAccountAndChannelCommerceDiscounts(
				commerceAccountId, commerceChannelId, cpDefinitionId,
				cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			_commerceDiscountLocalService.getAccountCommerceDiscounts(
				commerceAccountId, cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			_commerceDiscountLocalService.
				getAccountGroupAndChannelAndOrderTypeCommerceDiscount(
					commerceAccountGroupIds, commerceChannelId,
					commerceOrderTypeId, cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			_commerceDiscountLocalService.
				getAccountGroupAndChannelCommerceDiscount(
					commerceAccountGroupIds, commerceChannelId, cpDefinitionId,
					cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			_commerceDiscountLocalService.getAccountGroupCommerceDiscount(
				commerceAccountGroupIds, cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			_commerceDiscountLocalService.
				getChannelAndOrderTypeCommerceDiscounts(
					commerceChannelId, commerceOrderTypeId, cpDefinitionId,
					cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			_commerceDiscountLocalService.getOrderTypeCommerceDiscounts(
				commerceOrderTypeId, cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		commerceDiscounts =
			_commerceDiscountLocalService.getChannelCommerceDiscounts(
				commerceChannelId, cpDefinitionId, cpInstanceId);

		if ((commerceDiscounts != null) && !commerceDiscounts.isEmpty()) {
			return commerceDiscounts;
		}

		return _commerceDiscountLocalService.getUnqualifiedCommerceDiscounts(
			companyId, cpDefinitionId, cpInstanceId);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductDiscountFulfilmentActionExecutor.class);

	@Reference
	private CommerceDiscountLocalService _commerceDiscountLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

}