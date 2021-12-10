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

import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.internal.price.util.PriceValue;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommercePriceEntryLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryLocalService;
import com.liferay.commerce.pricing.model.CommercePriceModifier;
import com.liferay.commerce.pricing.modifier.CommercePriceModifierHelper;
import com.liferay.commerce.pricing.service.CommercePriceModifierLocalService;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.fulfilment.action.executor.BaseFulfilmentActionExecutor;
import com.liferay.fulfilment.constants.FulfilmentConstants;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutorException;

import java.io.Serializable;

import java.math.BigDecimal;

import java.util.HashMap;
import java.util.HashSet;
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
public class ProductPriceFulfilmentActionExecutor
	extends BaseFulfilmentActionExecutor {

	@Override
	protected int doExecute(
			Map<String, Serializable> inputParameters,
			Map<String, Serializable> outputParameters)
		throws ActionExecutorException {

		HashSet<String> keys = (HashSet<String>)inputParameters.get("keys");
		String type = (String)inputParameters.get("type");
		HashMap<String, Map<Integer, PriceValue>> priceMap =
			(HashMap<String, Map<Integer, PriceValue>>)GetterUtil.getObject(
				inputParameters.get("priceMap"), new HashMap<>());

		try {
			for (String key : keys) {

				// TODO inline discounts

				String[] keyEntry = key.split(StringPool.SLASH);

				long commerceAccountId = GetterUtil.getLong(keyEntry[1]);
				long commerceAccountGroupId = GetterUtil.getLong(keyEntry[2]);
				long commerceChannelId = GetterUtil.getLong(keyEntry[3]);
				long commerceOrderTypeId = GetterUtil.getLong(keyEntry[4]);
				long cpInstanceId = GetterUtil.getLong(keyEntry[5]);

				CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
					cpInstanceId);

				CommercePriceList commercePriceList = _getCommercePriceList(
					cpInstance.getGroupId(), commerceAccountId,
					new long[] {commerceAccountGroupId}, commerceChannelId,
					commerceOrderTypeId, type);

				if (commercePriceList == null) {
					commercePriceList =
						_commercePriceListLocalService.
							getCatalogBaseCommercePriceListByType(
								cpInstance.getGroupId(), type);
				}

				CommerceCurrency commerceCurrency =
					commercePriceList.getCommerceCurrency();

				CommercePriceEntry commercePriceEntry =
					_commercePriceEntryLocalService.fetchCommercePriceEntry(
						commercePriceList.getCommercePriceListId(),
						cpInstance.getCPInstanceUuid(), true);

				BigDecimal commercePriceEntryPrice =
					commercePriceEntry.getPrice();

				if (commercePriceEntry.getCommercePriceListId() !=
						commercePriceList.getCommercePriceListId()) {

					commercePriceEntryPrice =
						_commercePriceModifierHelper.applyCommercePriceModifier(
							commercePriceList.getCommercePriceListId(),
							cpInstance.getCPDefinitionId(),
							commercePriceEntry.getPriceCommerceMoney(
								commerceCurrency.getCommerceCurrencyId()));
				}

				_updatePriceValue(
					cpInstanceId, commerceCurrency.getCommerceCurrencyId(),
					commercePriceEntryPrice.toString(), priceMap, key, 1);

				if (commercePriceEntry.isHasTierPrice()) {
					List<CommerceTierPriceEntry> commerceTierPriceEntries =
						_commerceTierPriceEntryLocalService.
							getCommerceTierPriceEntries(
								commercePriceEntry.getCommercePriceEntryId(),
								QueryUtil.ALL_POS, QueryUtil.ALL_POS);

					if (commercePriceEntry.isBulkPricing()) {
						for (CommerceTierPriceEntry commerceTierPriceEntry :
								commerceTierPriceEntries) {

							BigDecimal commerceTierPriceEntryPrice =
								commerceTierPriceEntry.getPrice();

							if (commercePriceEntry.getCommercePriceListId() !=
									commercePriceList.
										getCommercePriceListId()) {

								commerceTierPriceEntryPrice =
									_commercePriceModifierHelper.
										applyCommercePriceModifier(
											commercePriceList.
												getCommercePriceListId(),
											cpInstance.getCPDefinitionId(),
											commerceTierPriceEntry.
												getPriceCommerceMoney(
													commerceCurrency.
														getCommerceCurrencyId()));
							}

							_updatePriceValue(
								cpInstanceId,
								commerceCurrency.getCommerceCurrencyId(),
								commerceTierPriceEntryPrice.toString(),
								priceMap, key,
								commerceTierPriceEntry.getMinQuantity());
						}
					}
					else {
						int totalTier = 1;
						BigDecimal totalTierPrice = commercePriceEntryPrice;

						for (CommerceTierPriceEntry commerceTierPriceEntry :
								commerceTierPriceEntries) {

							BigDecimal commerceTierPriceEntryPrice =
								commerceTierPriceEntry.getPrice();

							String formulaPrice = _createFormula(
								commerceTierPriceEntryPrice.doubleValue(),
								totalTier, totalTierPrice.doubleValue());

							if (commercePriceEntry.getCommercePriceListId() !=
									commercePriceList.
										getCommercePriceListId()) {

								formulaPrice = _createCommerceModifierFormula(
									commercePriceList.getCommercePriceListId(),
									cpInstance.getCPDefinitionId(),
									formulaPrice);
							}

							_updatePriceValue(
								cpInstanceId,
								commerceCurrency.getCommerceCurrencyId(),
								formulaPrice, priceMap, key,
								commerceTierPriceEntry.getMinQuantity());

							totalTier =
								totalTier +
									commerceTierPriceEntry.getMinQuantity();
							totalTierPrice = totalTierPrice.add(
								commerceTierPriceEntryPrice.multiply(
									BigDecimal.valueOf(
										commerceTierPriceEntry.
											getMinQuantity())));
						}
					}
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

	private String _createCommerceModifierFormula(
			long commercePriceListId, long cpDefinitionId, String formulaPrice)
		throws PortalException {

		List<CommercePriceModifier> commercePriceModifiers =
			_commercePriceModifierLocalService.
				getQualifiedCommercePriceModifiers(
					commercePriceListId, cpDefinitionId);

		StringBundler sb = new StringBundler();

		sb.append("groupMin(");

		for (CommercePriceModifier commercePriceModifier :
				commercePriceModifiers) {

			BigDecimal commerceModifierAmount =
				commercePriceModifier.getModifierAmount();

			if (Objects.equals(
					commercePriceModifier.getModifierType(), "FIXED-AMOUNT")) {

				sb.append("modifierFixedAmount(");
			}
			else if (Objects.equals(
						commercePriceModifier.getModifierType(),
						"PERCENTAGE")) {

				sb.append("modifierPercentage(");
			}
			else if (Objects.equals(
						commercePriceModifier.getModifierType(), "REPLACE")) {

				sb.append("modifierReplace(");
			}

			sb.append(formulaPrice);
			sb.append(",");
			sb.append(commerceModifierAmount.doubleValue());
			sb.append("),");
		}

		String modifierFormula = sb.toString();

		modifierFormula = modifierFormula.substring(
			0, modifierFormula.length() - 1);
		modifierFormula = modifierFormula + ")";

		return modifierFormula;
	}

	private String _createFormula(
		double currTierPrice, int totalTier, double totalTierPrice) {

		return StringBundler.concat(
			totalTierPrice, " + (q - (", totalTier, ") * ", currTierPrice);
	}

	private CommercePriceList _getCommercePriceList(
			long groupId, long commerceAccountId,
			long[] commerceAccountGroupIds, long commerceChannelId,
			long commerceOrderTypeId, String type)
		throws PortalException {

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountAndChannelAndOrderTypeId(
					groupId, commerceAccountId, commerceChannelId,
					commerceOrderTypeId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountAndOrderTypeId(
					groupId, commerceAccountId, commerceOrderTypeId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountAndChannelId(
					groupId, commerceAccountId, commerceChannelId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.getCommercePriceListByAccountId(
				groupId, commerceAccountId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountGroupsAndChannelAndOrderTypeId(
					groupId, commerceAccountGroupIds, commerceChannelId,
					commerceOrderTypeId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountGroupsAndOrderTypeId(
					groupId, commerceAccountGroupIds, commerceOrderTypeId,
					type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountGroupsAndChannelId(
					groupId, commerceAccountGroupIds, commerceChannelId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByAccountGroupIds(
					groupId, commerceAccountGroupIds, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.
				getCommercePriceListByChannelAndOrderTypeId(
					groupId, commerceChannelId, commerceOrderTypeId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.getCommercePriceListByOrderTypeId(
				groupId, commerceOrderTypeId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.getCommercePriceListByChannelId(
				groupId, commerceChannelId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		commercePriceList =
			_commercePriceListLocalService.getCommercePriceListByUnqualified(
				groupId, type);

		if (commercePriceList != null) {
			return commercePriceList;
		}

		return null;
	}

	private void _updatePriceValue(
		long cpInstanceId, long commerceCurrencyId, String price,
		HashMap<String, Map<Integer, PriceValue>> priceMap, String key,
		int quantity) {

		Map<Integer, PriceValue> priceValue = priceMap.get(key);

		if (priceValue == null) {
			priceValue = new HashMap<>();

			priceValue.put(
				quantity,
				new PriceValue(cpInstanceId, commerceCurrencyId, price));
		}
		else {
			PriceValue currPriceValue = priceValue.get(quantity);

			currPriceValue.setPromoPrice(price);
		}

		priceMap.put(key, priceValue);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		ProductPriceFulfilmentActionExecutor.class);

	@Reference
	private CommercePriceEntryLocalService _commercePriceEntryLocalService;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CommercePriceModifierHelper _commercePriceModifierHelper;

	@Reference
	private CommercePriceModifierLocalService
		_commercePriceModifierLocalService;

	@Reference
	private CommerceTierPriceEntryLocalService
		_commerceTierPriceEntryLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

}