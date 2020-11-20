/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 *
 *
 *
 */

package com.liferay.commerce.avalara.connector.internal.helper;

import com.liferay.commerce.avalara.connector.CommerceAvalaraConnector;
import com.liferay.commerce.avalara.connector.constants.CommerceAvalaraConstants;
import com.liferay.commerce.avalara.connector.helper.CommerceAvalaraConnectorHelper;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.exception.CommerceOrderShippingAddressException;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.service.CommerceCountryLocalService;
import com.liferay.commerce.service.CommerceOrderItemLocalService;
import com.liferay.commerce.service.CommerceOrderLocalService;
import com.liferay.commerce.service.CommerceRegionLocalService;
import com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRateAddressRel;
import com.liferay.commerce.tax.engine.fixed.service.CommerceTaxFixedRateAddressRelLocalService;
import com.liferay.commerce.tax.model.CommerceTaxMethod;
import com.liferay.commerce.tax.service.CommerceTaxMethodLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.List;
import java.util.Objects;

import net.avalara.avatax.rest.client.enums.ResolutionQuality;
import net.avalara.avatax.rest.client.models.AddressResolutionModel;
import net.avalara.avatax.rest.client.models.AvaTaxMessage;
import net.avalara.avatax.rest.client.models.TransactionLineModel;
import net.avalara.avatax.rest.client.models.TransactionModel;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	service = CommerceAvalaraConnectorHelper.class
)
public class CommerceAvalaraConnectorHelperImpl
	implements CommerceAvalaraConnectorHelper {

	@Override
	public void updateByAddressEntries(long groupId) throws Exception {
		String taxRateByZipCode = _commerceAvalaraConnector.getTaxRateByZipCode(
			groupId);

		if (Validator.isBlank(taxRateByZipCode)) {
			return;
		}

		CommerceTaxMethod commerceTaxMethod =
			_commerceTaxMethodLocalService.fetchCommerceTaxMethod(
				groupId, CommerceAvalaraConstants.KEY);

		if (commerceTaxMethod == null) {
			return;
		}

		String[] taxRatesByZipCodeLines = StringUtil.splitLines(
			taxRateByZipCode);

		for (int i = 1; i < taxRatesByZipCodeLines.length; i++) {
			String[] taxRatesByZipCodeLine = StringUtil.split(
				taxRatesByZipCodeLines[i], StringPool.COMMA);

			_upsertByAddressEntry(
				commerceTaxMethod.getCompanyId(), commerceTaxMethod.getUserId(),
				groupId, commerceTaxMethod.getCommerceTaxMethodId(), "US",
				taxRatesByZipCodeLine
					[CommerceAvalaraConstants.CSV_REGION_POSITION],
				taxRatesByZipCodeLine
					[CommerceAvalaraConstants.CSV_ZIP_CODE_POSITION],
				Double.valueOf(
					taxRatesByZipCodeLine
						[CommerceAvalaraConstants.
							CSV_TOTAL_SALES_TAX_POSITION]));
		}
	}

	@Override
	public CommerceOrder updateOrderInvoiceTaxInfo(CommerceOrder commerceOrder)
		throws Exception {

		TransactionModel salesInvoiceTransaction =
			_commerceAvalaraConnector.createSalesInvoiceTransaction(
				commerceOrder);

		return _updateTaxInfo(commerceOrder, salesInvoiceTransaction);
	}

	@Override
	public CommerceOrder updateOrderTaxInfo(CommerceOrder commerceOrder)
		throws Exception {

		TransactionModel salesOrderTransaction =
			_commerceAvalaraConnector.createSalesOrderTransaction(
				commerceOrder);

		return _updateTaxInfo(commerceOrder, salesOrderTransaction);
	}

	@Override
	public CommerceAddress validateCommerceAddress(
			long groupId, CommerceAddress commerceAddress)
		throws Exception {

		AddressResolutionModel addressResolutionModel =
			_commerceAvalaraConnector.validateAddress(groupId, commerceAddress);

		_processAddressResolutionMessages(addressResolutionModel.getMessages());

		if (Objects.equals(
				addressResolutionModel.getResolutionQuality(),
				ResolutionQuality.External)) {

			return commerceAddress;
		}

		// TODO modify fields to return the suggested address

		return commerceAddress;
	}

	private void _processAddressResolutionMessages(
			List<AvaTaxMessage> avaTaxMessages)
		throws Exception {

		for (AvaTaxMessage avaTaxMessage : avaTaxMessages) {
			if (Objects.equals(
					avaTaxMessage.getSeverity(),
					CommerceAvalaraConstants.AVA_TAX_ERROR_MESSAGE)) {

				throw new CommerceOrderShippingAddressException(
					avaTaxMessage.getSummary());
			}
		}
	}

	private void _updateDiscountLevelPercentages() {

		// TODO update discount percentages

	}

	private void _updateOrderItemPricingInfo(
			CommerceOrderItem commerceOrderItem,
			TransactionLineModel transactionLineModel)
		throws Exception {

		BigDecimal finalPrice = commerceOrderItem.getFinalPrice();

		BigDecimal taxCalculated = transactionLineModel.getTaxCalculated();

		commerceOrderItem.setFinalPriceWithTaxAmount(
			finalPrice.add(taxCalculated));

		BigDecimal discountAmount = commerceOrderItem.getDiscountAmount();

		CommerceOrder commerceOrder = commerceOrderItem.getCommerceOrder();

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		BigDecimal unitTaxAmount = taxCalculated.divide(
			BigDecimal.valueOf(commerceOrderItem.getQuantity()),
			RoundingMode.valueOf(commerceCurrency.getRoundingMode()));

		commerceOrderItem.setDiscountWithTaxAmount(
			discountAmount.add(unitTaxAmount));

		_updateDiscountLevelPercentages();
	}

	private void _updateOrderItemsAndShippingPricingInfo(
			CommerceOrder commerceOrder, TransactionModel transactionModel)
		throws Exception {

		List<TransactionLineModel> transactionLineModels =
			transactionModel.getLines();

		for (TransactionLineModel transactionLineModel :
				transactionLineModels) {

			if (Objects.equals(
					transactionLineModel.getItemCode(),
					CommerceAvalaraConstants.SHIPPING_LINE_ITEM)) {

				_updateShippingPricingInfo(commerceOrder, transactionLineModel);

				continue;
			}

			CommerceOrderItem commerceOrderItem =
				_commerceOrderItemLocalService.fetchCommerceOrderItem(
					Long.valueOf(transactionLineModel.getRef1()));

			if (commerceOrderItem == null) {
				continue;
			}

			_updateOrderItemPricingInfo(
				commerceOrderItem, transactionLineModel);

			_commerceOrderItemLocalService.updateCommerceOrderItem(
				commerceOrderItem);
		}
	}

	private void _updateShippingPricingInfo(
		CommerceOrder commerceOrder,
		TransactionLineModel transactionLineModel) {

		BigDecimal shippingAmount = commerceOrder.getShippingAmount();

		BigDecimal shippingTaxAmount = transactionLineModel.getTaxCalculated();

		commerceOrder.setShippingWithTaxAmount(
			shippingAmount.add(shippingTaxAmount));

		BigDecimal shippingDiscountAmount =
			commerceOrder.getShippingDiscountAmount();

		commerceOrder.setShippingDiscountWithTaxAmount(
			shippingDiscountAmount.add(shippingTaxAmount));

		_updateDiscountLevelPercentages();
	}

	private CommerceOrder _updateTaxInfo(
			CommerceOrder commerceOrder, TransactionModel transactionModel)
		throws Exception {

		// discount target net price

		_updateOrderItemsAndShippingPricingInfo(
			commerceOrder, transactionModel);

		// subtotal should use the by address calculation ??

		_updateTotalPricingInfo(commerceOrder, transactionModel);

		// discount target gross price

		commerceOrder.setManuallyAdjusted(true);

		return _commerceOrderLocalService.updateCommerceOrder(commerceOrder);
	}

	private void _updateTotalPricingInfo(
		CommerceOrder commerceOrder, TransactionModel transactionModel) {

		BigDecimal totalTaxCalculated =
			transactionModel.getTotalTaxCalculated();

		commerceOrder.setTaxAmount(totalTaxCalculated);

		BigDecimal total = commerceOrder.getTotal();

		commerceOrder.setTotalWithTaxAmount(total.add(totalTaxCalculated));

		BigDecimal totalDiscountAmount = commerceOrder.getTotalDiscountAmount();

		commerceOrder.setTotalDiscountWithTaxAmount(
			totalDiscountAmount.add(totalTaxCalculated));

		_updateDiscountLevelPercentages();
	}

	private void _upsertByAddressEntry(
			long companyId, long userId, long groupId, long commerceTaxMethodId,
			String countryCode, String regionCode, String zip, double rate)
		throws Exception {

		CommerceCountry commerceCountry =
			_commerceCountryLocalService.fetchCommerceCountry(
				companyId, countryCode);

		if (commerceCountry == null) {
			return;
		}

		CommerceRegion commerceRegion =
			_commerceRegionLocalService.getCommerceRegion(
				commerceCountry.getCommerceCountryId(), regionCode);

		CommerceTaxFixedRateAddressRel commerceTaxFixedRateAddressRel =
			_commerceTaxFixedRateAddressRelLocalService.
				fetchCommerceTaxFixedRateAddressRel(
					commerceTaxMethodId, commerceCountry.getCommerceCountryId(),
					commerceRegion.getCommerceRegionId(), zip);

		if (commerceTaxFixedRateAddressRel == null) {

			// TODO retrieve tax category for tangible personal property

			long cpTaxCategoryId = 0;

			_commerceTaxFixedRateAddressRelLocalService.
				addCommerceTaxFixedRateAddressRel(
					userId, groupId, commerceTaxMethodId, cpTaxCategoryId,
					commerceCountry.getCommerceCountryId(),
					commerceRegion.getCommerceRegionId(), zip, rate);
		}
		else {
			_commerceTaxFixedRateAddressRelLocalService.
				updateCommerceTaxFixedRateAddressRel(
					commerceTaxFixedRateAddressRel.
						getCommerceTaxFixedRateAddressRelId(),
					commerceCountry.getCommerceCountryId(),
					commerceRegion.getCommerceRegionId(), zip, rate);
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAvalaraConnectorHelperImpl.class);

	@Reference
	private CommerceAvalaraConnector _commerceAvalaraConnector;

	@Reference
	private CommerceCountryLocalService _commerceCountryLocalService;

	@Reference
	private CommerceOrderItemLocalService _commerceOrderItemLocalService;

	@Reference
	private CommerceOrderLocalService _commerceOrderLocalService;

	@Reference
	private CommerceRegionLocalService _commerceRegionLocalService;

	@Reference
	private CommerceTaxFixedRateAddressRelLocalService
		_commerceTaxFixedRateAddressRelLocalService;

	@Reference
	private CommerceTaxMethodLocalService _commerceTaxMethodLocalService;

}