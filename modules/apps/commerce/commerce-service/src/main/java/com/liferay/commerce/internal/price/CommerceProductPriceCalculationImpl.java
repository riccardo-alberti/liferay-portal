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

package com.liferay.commerce.internal.price;

import com.liferay.commerce.context.CommerceContext;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.currency.model.CommerceMoney;
import com.liferay.commerce.currency.model.CommerceMoneyFactory;
import com.liferay.commerce.currency.util.PriceFormat;
import com.liferay.commerce.discount.CommerceDiscountValue;
import com.liferay.commerce.engine.CommerceEngine;
import com.liferay.commerce.price.CommerceProductOptionValueRelativePriceRequest;
import com.liferay.commerce.price.CommerceProductPrice;
import com.liferay.commerce.price.CommerceProductPriceCalculation;
import com.liferay.commerce.price.CommerceProductPriceImpl;
import com.liferay.commerce.price.CommerceProductPriceRequest;
import com.liferay.commerce.price.list.constants.CommercePriceListConstants;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.service.CommercePriceEntryLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.product.constants.CPConstants;
import com.liferay.commerce.product.model.CPDefinitionOptionRel;
import com.liferay.commerce.product.model.CPDefinitionOptionValueRel;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.service.CPDefinitionOptionRelLocalService;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.commerce.util.CommerceBigDecimalUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(enabled = false, service = CommerceProductPriceCalculation.class)
public class CommerceProductPriceCalculationImpl
	implements CommerceProductPriceCalculation {

	@Override
	public CommerceMoney getBasePrice(
			long cpInstanceId, CommerceCurrency commerceCurrency)
		throws PortalException {

		return _commerceMoneyFactory.create(
			commerceCurrency,
			_getBasePrice(
				cpInstanceId, commerceCurrency,
				CommercePriceListConstants.TYPE_PRICE_LIST));
	}

	@Override
	public CommerceMoney getBasePromoPrice(
			long cpInstanceId, CommerceCurrency commerceCurrency)
		throws PortalException {

		return _commerceMoneyFactory.create(
			commerceCurrency,
			_getBasePrice(
				cpInstanceId, commerceCurrency,
				CommercePriceListConstants.TYPE_PROMOTION));
	}

	@Override
	public CommerceProductPrice getCommerceProductPrice(
			CommerceProductPriceRequest commerceProductPriceRequest)
		throws PortalException {

		Map<String, Object> context = HashMapBuilder.<String, Object>put(
			"_commerceContext", commerceProductPriceRequest.getCommerceContext()
		).put(
			"_commerceOptionValues",
			commerceProductPriceRequest.getCommerceOptionValues()
		).put(
			"_companyId", 0
		).put(
			"_cpInstanceId", commerceProductPriceRequest.getCpInstanceId()
		).put(
			"_quantity", commerceProductPriceRequest.getQuantity()
		).put(
			"_type", "product-pricing"
		).put(
			"_userId", 0
		).build();

		try {
			_commerceEngine.executeTasks(context, "product-pricing");
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			return _getCommerceProductPriceImpl();
		}

		CommerceContext commerceContext =
			commerceProductPriceRequest.getCommerceContext();

		return _getCommerceProductPriceImpl(
			commerceContext.getCommerceCurrency(), context);
	}

	@Override
	public CommerceProductPrice getCommerceProductPrice(
			long cpInstanceId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceProductPriceRequest commerceProductPriceRequest =
			new CommerceProductPriceRequest();

		commerceProductPriceRequest.setCpInstanceId(cpInstanceId);
		commerceProductPriceRequest.setQuantity(quantity);
		commerceProductPriceRequest.setSecure(secure);
		commerceProductPriceRequest.setCommerceContext(commerceContext);
		commerceProductPriceRequest.setCommerceOptionValues(
			Collections.emptyList());

		return getCommerceProductPrice(commerceProductPriceRequest);
	}

	@Override
	public CommerceProductPrice getCommerceProductPrice(
			long cpInstanceId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getCommerceProductPrice(
			cpInstanceId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getCPDefinitionMinimumPrice(
			long cpDefinitionId, CommerceContext commerceContext)
		throws PortalException {

		CommerceMoney commerceMoney = getUnitMinPrice(
			cpDefinitionId, 1, commerceContext);

		if (commerceMoney.isEmpty()) {
			return commerceMoney;
		}

		BigDecimal cpDefinitionMinimumPrice = BigDecimal.ZERO;

		cpDefinitionMinimumPrice = cpDefinitionMinimumPrice.add(
			commerceMoney.getPrice());

		List<CPDefinitionOptionRel> cpDefinitionOptionRels =
			_cpDefinitionOptionRelLocalService.getCPDefinitionOptionRels(
				cpDefinitionId);

		for (CPDefinitionOptionRel cpDefinitionOptionRel :
				cpDefinitionOptionRels) {

			if (!_isRequiredPriceContributor(cpDefinitionOptionRel)) {
				continue;
			}

			if (cpDefinitionOptionRel.isPriceTypeStatic()) {
				cpDefinitionMinimumPrice = cpDefinitionMinimumPrice.add(
					_getCPDefinitionOptionMinStaticPrice(
						cpDefinitionOptionRel, commerceContext));

				continue;
			}

			cpDefinitionMinimumPrice = cpDefinitionMinimumPrice.add(
				_getCPDefinitionOptionMinDynamicPrice(
					cpDefinitionOptionRel, commerceContext));
		}

		return _commerceMoneyFactory.create(
			commerceContext.getCommerceCurrency(), cpDefinitionMinimumPrice);
	}

	@Override
	public CommerceMoney getCPDefinitionOptionValueRelativePrice(
			CommerceProductOptionValueRelativePriceRequest
				commerceProductOptionValueRelativePriceRequest)
		throws PortalException {

		_validate(
			commerceProductOptionValueRelativePriceRequest.
				getCPDefinitionOptionValueRel(),
			commerceProductOptionValueRelativePriceRequest.
				getSelectedCPDefinitionOptionValueRel());

		BigDecimal relativePrice = BigDecimal.ZERO;

		CommerceContext commerceContext =
			commerceProductOptionValueRelativePriceRequest.getCommerceContext();

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel =
			commerceProductOptionValueRelativePriceRequest.
				getCPDefinitionOptionValueRel();

		CPDefinitionOptionRel cpDefinitionOptionRel =
			cpDefinitionOptionValueRel.getCPDefinitionOptionRel();

		if (!cpDefinitionOptionRel.isPriceContributor()) {
			return _commerceMoneyFactory.create(
				commerceContext.getCommerceCurrency(), relativePrice,
				PriceFormat.RELATIVE);
		}

		relativePrice = relativePrice.add(
			_getCPInstancePriceDifference(
				commerceProductOptionValueRelativePriceRequest.
					getCPInstanceId(),
				commerceProductOptionValueRelativePriceRequest.
					getCPInstanceMinQuantity(),
				commerceProductOptionValueRelativePriceRequest.
					getSelectedCPInstanceId(),
				commerceProductOptionValueRelativePriceRequest.
					getSelectedCPInstanceMinQuantity(),
				commerceContext));

		relativePrice = relativePrice.add(
			_getCPDefinitionOptionValuePriceDifference(
				commerceProductOptionValueRelativePriceRequest.
					getCPDefinitionOptionValueRel(),
				commerceProductOptionValueRelativePriceRequest.
					getSelectedCPDefinitionOptionValueRel(),
				cpDefinitionOptionRel.getPriceType(), commerceContext));

		return _commerceMoneyFactory.create(
			commerceContext.getCommerceCurrency(), relativePrice,
			PriceFormat.RELATIVE);
	}

	@Override
	public CommerceMoney getFinalPrice(
			long cpInstanceId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceProductPrice commerceProductPrice = getCommerceProductPrice(
			cpInstanceId, quantity, commerceContext);

		if (commerceProductPrice == null) {
			return _commerceMoneyFactory.emptyCommerceMoney();
		}

		return commerceProductPrice.getFinalPrice();
	}

	@Override
	public CommerceMoney getFinalPrice(
			long cpInstanceId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getFinalPrice(cpInstanceId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getPromoPrice(
			long cpInstanceId, int quantity, CommerceCurrency commerceCurrency,
			boolean secure, CommerceContext commerceContext)
		throws PortalException {

		CommerceProductPrice commerceProductPrice = getCommerceProductPrice(
			cpInstanceId, quantity, commerceContext);

		if (commerceProductPrice == null) {
			return _commerceMoneyFactory.emptyCommerceMoney();
		}

		return commerceProductPrice.getUnitPromoPrice();
	}

	@Override
	public CommerceMoney getUnitMaxPrice(
			long cpDefinitionId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceMoney commerceMoney =
			_commerceMoneyFactory.emptyCommerceMoney();

		List<CPInstance> cpInstances =
			_cpInstanceLocalService.getCPDefinitionInstances(
				cpDefinitionId, WorkflowConstants.STATUS_APPROVED,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (CPInstance cpInstance : cpInstances) {
			CommerceMoney cpInstanceCommerceMoney = getUnitPrice(
				cpInstance.getCPInstanceId(), quantity,
				commerceContext.getCommerceCurrency(), secure, commerceContext);

			if (commerceMoney.isEmpty() ||
				CommerceBigDecimalUtil.lt(
					commerceMoney.getPrice(),
					cpInstanceCommerceMoney.getPrice())) {

				commerceMoney = cpInstanceCommerceMoney;
			}
		}

		return commerceMoney;
	}

	@Override
	public CommerceMoney getUnitMaxPrice(
			long cpDefinitionId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getUnitMaxPrice(cpDefinitionId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getUnitMinPrice(
			long cpDefinitionId, int quantity, boolean secure,
			CommerceContext commerceContext)
		throws PortalException {

		CommerceMoney commerceMoney =
			_commerceMoneyFactory.emptyCommerceMoney();

		List<CPInstance> cpInstances =
			_cpInstanceLocalService.getCPDefinitionInstances(
				cpDefinitionId, WorkflowConstants.STATUS_APPROVED,
				QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);

		for (CPInstance cpInstance : cpInstances) {
			CommerceMoney cpInstanceCommerceMoney = getUnitPrice(
				cpInstance.getCPInstanceId(), quantity,
				commerceContext.getCommerceCurrency(), secure, commerceContext);

			if (commerceMoney.isEmpty() ||
				CommerceBigDecimalUtil.gt(
					commerceMoney.getPrice(),
					cpInstanceCommerceMoney.getPrice())) {

				commerceMoney = cpInstanceCommerceMoney;
			}
		}

		return commerceMoney;
	}

	@Override
	public CommerceMoney getUnitMinPrice(
			long cpDefinitionId, int quantity, CommerceContext commerceContext)
		throws PortalException {

		return getUnitMinPrice(cpDefinitionId, quantity, true, commerceContext);
	}

	@Override
	public CommerceMoney getUnitPrice(
			long cpInstanceId, int quantity, CommerceCurrency commerceCurrency,
			boolean secure, CommerceContext commerceContext)
		throws PortalException {

		CommerceProductPrice commerceProductPrice = getCommerceProductPrice(
			cpInstanceId, quantity, commerceContext);

		if (commerceProductPrice == null) {
			return _commerceMoneyFactory.emptyCommerceMoney();
		}

		return commerceProductPrice.getUnitPrice();
	}

	private BigDecimal _getBasePrice(
			long cpInstanceId, CommerceCurrency commerceCurrency,
			String commercePriceListType)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCPInstance(
			cpInstanceId);

		CommercePriceList commercePriceList =
			_commercePriceListLocalService.
				fetchCatalogBaseCommercePriceListByType(
					cpInstance.getGroupId(), commercePriceListType);

		if (commercePriceList == null) {
			return BigDecimal.ZERO;
		}

		CommercePriceEntry commercePriceEntry =
			_commercePriceEntryLocalService.fetchCommercePriceEntry(
				commercePriceList.getCommercePriceListId(),
				cpInstance.getCPInstanceUuid(), false);

		if (commercePriceEntry == null) {
			return BigDecimal.ZERO;
		}

		BigDecimal price = commercePriceEntry.getPrice();

		CommerceCurrency priceListCurrency =
			commercePriceList.getCommerceCurrency();

		if (priceListCurrency.getCommerceCurrencyId() !=
				commerceCurrency.getCommerceCurrencyId()) {

			price = price.divide(
				priceListCurrency.getRate(),
				RoundingMode.valueOf(priceListCurrency.getRoundingMode()));

			price = price.multiply(commerceCurrency.getRate());
		}

		return price;
	}

	private CommerceProductPriceImpl _getCommerceProductPriceImpl() {
		CommerceProductPriceImpl commerceProductPriceImpl =
			new CommerceProductPriceImpl();

		commerceProductPriceImpl.setFinalPrice(
			_commerceMoneyFactory.emptyCommerceMoney());
		commerceProductPriceImpl.setFinalPriceWithTaxAmount(
			_commerceMoneyFactory.emptyCommerceMoney());
		commerceProductPriceImpl.setUnitPrice(
			_commerceMoneyFactory.emptyCommerceMoney());
		commerceProductPriceImpl.setUnitPriceWithTaxAmount(
			_commerceMoneyFactory.emptyCommerceMoney());
		commerceProductPriceImpl.setUnitPromoPrice(
			_commerceMoneyFactory.emptyCommerceMoney());
		commerceProductPriceImpl.setUnitPromoPriceWithTaxAmount(
			_commerceMoneyFactory.emptyCommerceMoney());

		return commerceProductPriceImpl;
	}

	private CommerceProductPriceImpl _getCommerceProductPriceImpl(
		CommerceCurrency commerceCurrency, Map<String, Object> context) {

		CommerceProductPriceImpl commerceProductPriceImpl =
			new CommerceProductPriceImpl();

		commerceProductPriceImpl.setFinalPrice(
			_commerceMoneyFactory.create(
				commerceCurrency, (BigDecimal)context.get("_finalPrice")));
		commerceProductPriceImpl.setFinalPriceWithTaxAmount(
			_commerceMoneyFactory.create(
				commerceCurrency,
				(BigDecimal)context.get("_finalPriceWithTaxAmount")));
		commerceProductPriceImpl.setUnitPrice(
			_commerceMoneyFactory.create(
				commerceCurrency, (BigDecimal)context.get("_unitPrice")));
		commerceProductPriceImpl.setUnitPriceWithTaxAmount(
			_commerceMoneyFactory.create(
				commerceCurrency,
				(BigDecimal)context.get("_unitPriceWithTaxAmount")));
		commerceProductPriceImpl.setUnitPromoPrice(
			_commerceMoneyFactory.create(
				commerceCurrency, (BigDecimal)context.get("_promoPrice")));
		commerceProductPriceImpl.setUnitPromoPriceWithTaxAmount(
			_commerceMoneyFactory.create(
				commerceCurrency,
				(BigDecimal)context.get("_promoPriceWithTaxAmount")));
		commerceProductPriceImpl.setCommercePriceListId(
			(Long)context.get("_finalCommercePriceListId"));
		commerceProductPriceImpl.setCommerceDiscountValue(
			(CommerceDiscountValue)context.get("_commerceDiscountValue"));
		commerceProductPriceImpl.setCommerceDiscountValueWithTaxAmount(
			(CommerceDiscountValue)context.get(
				"_commerceDiscountValueWithTaxAmount"));

		return commerceProductPriceImpl;
	}

	private BigDecimal _getCPDefinitionOptionMinDynamicPrice(
			CPDefinitionOptionRel cpDefinitionOptionRel,
			CommerceContext commerceContext)
		throws PortalException {

		List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
			cpDefinitionOptionRel.getCPDefinitionOptionValueRels();

		if (cpDefinitionOptionValueRels.isEmpty()) {
			return BigDecimal.ZERO;
		}

		Iterator<CPDefinitionOptionValueRel> iterator =
			cpDefinitionOptionValueRels.iterator();

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel = iterator.next();

		BigDecimal cpDefinitionOptionMinDynamicPrice = _getCPInstanceFinalPrice(
			cpDefinitionOptionValueRel.getCProductId(),
			cpDefinitionOptionValueRel.getCPInstanceUuid(),
			cpDefinitionOptionValueRel.getQuantity(), commerceContext);

		while (iterator.hasNext()) {
			cpDefinitionOptionValueRel = iterator.next();

			BigDecimal cpInstanceFinalPrice = _getCPInstanceFinalPrice(
				cpDefinitionOptionValueRel.getCProductId(),
				cpDefinitionOptionValueRel.getCPInstanceUuid(),
				cpDefinitionOptionValueRel.getQuantity(), commerceContext);

			if (CommerceBigDecimalUtil.gt(
					cpDefinitionOptionMinDynamicPrice, cpInstanceFinalPrice)) {

				cpDefinitionOptionMinDynamicPrice = cpInstanceFinalPrice;
			}
		}

		return cpDefinitionOptionMinDynamicPrice;
	}

	private BigDecimal _getCPDefinitionOptionMinStaticPrice(
			CPDefinitionOptionRel cpDefinitionOptionRel,
			CommerceContext commerceContext)
		throws PortalException {

		List<CPDefinitionOptionValueRel> cpDefinitionOptionValueRels =
			cpDefinitionOptionRel.getCPDefinitionOptionValueRels();

		if (cpDefinitionOptionValueRels.isEmpty()) {
			return BigDecimal.ZERO;
		}

		Iterator<CPDefinitionOptionValueRel> iterator =
			cpDefinitionOptionValueRels.iterator();

		CPDefinitionOptionValueRel cpDefinitionOptionValueRel = iterator.next();

		BigDecimal cpDefinitionOptionMinStaticPrice =
			_getCPDefinitionOptionValueFinalPrice(
				cpDefinitionOptionValueRel.getPrice(),
				cpDefinitionOptionValueRel.getQuantity());

		while (iterator.hasNext()) {
			cpDefinitionOptionValueRel = iterator.next();

			BigDecimal cpDefinitionOptionValueFinalPrice =
				_getCPDefinitionOptionValueFinalPrice(
					cpDefinitionOptionValueRel.getPrice(),
					cpDefinitionOptionValueRel.getQuantity());

			if (CommerceBigDecimalUtil.gt(
					cpDefinitionOptionMinStaticPrice,
					cpDefinitionOptionValueFinalPrice)) {

				cpDefinitionOptionMinStaticPrice =
					cpDefinitionOptionValueFinalPrice;
			}
		}

		CommerceCurrency commerceCurrency =
			commerceContext.getCommerceCurrency();

		return cpDefinitionOptionMinStaticPrice.multiply(
			commerceCurrency.getRate());
	}

	private BigDecimal _getCPDefinitionOptionValueFinalPrice(
		BigDecimal price, int quantity) {

		return price.multiply(BigDecimal.valueOf(quantity));
	}

	private BigDecimal _getCPDefinitionOptionValuePriceDifference(
			CPDefinitionOptionValueRel cpDefinitionOptionValueRel,
			CPDefinitionOptionValueRel selectedCPDefinitionOptionValueRel,
			String priceType, CommerceContext commerceContext)
		throws PortalException {

		CommerceCurrency commerceCurrency =
			commerceContext.getCommerceCurrency();

		if (_isStaticPriceType(priceType)) {
			BigDecimal price = cpDefinitionOptionValueRel.getPrice();

			if (selectedCPDefinitionOptionValueRel != null) {
				price = price.subtract(
					selectedCPDefinitionOptionValueRel.getPrice());
			}

			return price.multiply(commerceCurrency.getRate());
		}

		BigDecimal cpInstanceFinalPrice = _getCPInstanceFinalPrice(
			cpDefinitionOptionValueRel.getCProductId(),
			cpDefinitionOptionValueRel.getCPInstanceUuid(),
			cpDefinitionOptionValueRel.getQuantity(), commerceContext);

		if (selectedCPDefinitionOptionValueRel == null) {
			return cpInstanceFinalPrice;
		}

		BigDecimal selectedCPInstanceFinalPrice = _getCPInstanceFinalPrice(
			selectedCPDefinitionOptionValueRel.getCProductId(),
			selectedCPDefinitionOptionValueRel.getCPInstanceUuid(),
			selectedCPDefinitionOptionValueRel.getQuantity(), commerceContext);

		return cpInstanceFinalPrice.subtract(selectedCPInstanceFinalPrice);
	}

	private BigDecimal _getCPInstanceFinalPrice(
			long cProductId, String cpInstanceUuid, int quantity,
			CommerceContext commerceContext)
		throws PortalException {

		CPInstance cpInstance = _cpInstanceLocalService.getCProductInstance(
			cProductId, cpInstanceUuid);

		CommerceMoney commerceMoney = getFinalPrice(
			cpInstance.getCPInstanceId(), quantity, commerceContext);

		if (commerceMoney.isEmpty()) {
			return BigDecimal.ZERO;
		}

		return commerceMoney.getPrice();
	}

	private BigDecimal _getCPInstancePriceDifference(
			long cpInstanceId1, int cpInstance1MinQuantity, long cpInstanceId2,
			int cpInstance2MinQuantity, CommerceContext commerceContext)
		throws PortalException {

		BigDecimal priceDifference = BigDecimal.ZERO;

		if (cpInstanceId1 > 0) {
			CommerceMoney cpInstance1FinalPriceCommerceMoney = getFinalPrice(
				cpInstanceId1, cpInstance1MinQuantity, commerceContext);

			if (!cpInstance1FinalPriceCommerceMoney.isEmpty()) {
				priceDifference = priceDifference.add(
					cpInstance1FinalPriceCommerceMoney.getPrice());
			}
		}

		if (cpInstanceId2 > 0) {
			CommerceMoney cpInstance2FinalPriceCommerceMoney = getFinalPrice(
				cpInstanceId2, cpInstance2MinQuantity, commerceContext);

			if (!cpInstance2FinalPriceCommerceMoney.isEmpty()) {
				priceDifference = priceDifference.subtract(
					cpInstance2FinalPriceCommerceMoney.getPrice());
			}
		}

		return priceDifference;
	}

	private boolean _isRequiredPriceContributor(
		CPDefinitionOptionRel cpDefinitionOptionRel) {

		if (cpDefinitionOptionRel.isPriceContributor() &&
			(cpDefinitionOptionRel.isRequired() ||
			 cpDefinitionOptionRel.isSkuContributor())) {

			return true;
		}

		return false;
	}

	private boolean _isStaticPriceType(String value) {
		if (Objects.equals(
				value, CPConstants.PRODUCT_OPTION_PRICE_TYPE_STATIC)) {

			return true;
		}

		return false;
	}

	private void _validate(
		CPDefinitionOptionValueRel cpDefinitionOptionValueRel,
		CPDefinitionOptionValueRel selectedCPDefinitionOptionValueRel) {

		if ((selectedCPDefinitionOptionValueRel != null) &&
			(cpDefinitionOptionValueRel.getCPDefinitionOptionRelId() !=
				selectedCPDefinitionOptionValueRel.
					getCPDefinitionOptionRelId())) {

			throw new IllegalArgumentException(
				"Provided CPDefinitionOptionValueRel parameters must belong " +
					"to the same CPDefinitionOptionRel");
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceProductPriceCalculationImpl.class);

	@Reference
	private CommerceEngine _commerceEngine;

	@Reference
	private CommerceMoneyFactory _commerceMoneyFactory;

	@Reference
	private CommercePriceEntryLocalService _commercePriceEntryLocalService;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CPDefinitionOptionRelLocalService
		_cpDefinitionOptionRelLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

}