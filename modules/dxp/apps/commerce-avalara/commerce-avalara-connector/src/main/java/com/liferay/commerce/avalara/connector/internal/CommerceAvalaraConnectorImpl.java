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

package com.liferay.commerce.avalara.connector.internal;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.avalara.connector.CommerceAvalaraConnector;
import com.liferay.commerce.avalara.connector.configuration.CommerceAvalaraTaxTypeConfiguration;
import com.liferay.commerce.avalara.connector.constants.CommerceAvalaraConstants;
import com.liferay.commerce.avalara.connector.exception.CommerceAvalaraConnectionException;
import com.liferay.commerce.constants.CommerceConstants;
import com.liferay.commerce.currency.model.CommerceCurrency;
import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceCountry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.model.CommerceOrderItem;
import com.liferay.commerce.model.CommerceRegion;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPTaxCategory;
import com.liferay.commerce.product.service.CPTaxCategoryLocalService;
import com.liferay.commerce.tax.configuration.CommerceShippingTaxConfiguration;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationProvider;
import com.liferay.portal.kernel.settings.GroupServiceSettingsLocator;
import com.liferay.portal.kernel.util.Base64;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import net.avalara.avatax.rest.client.AvaTaxClient;
import net.avalara.avatax.rest.client.FetchResult;
import net.avalara.avatax.rest.client.TransactionBuilder;
import net.avalara.avatax.rest.client.enums.DocumentType;
import net.avalara.avatax.rest.client.enums.TextCase;
import net.avalara.avatax.rest.client.enums.TransactionAddressType;
import net.avalara.avatax.rest.client.models.AddressResolutionModel;
import net.avalara.avatax.rest.client.models.CreateTransactionModel;
import net.avalara.avatax.rest.client.models.EntityUseCodeModel;
import net.avalara.avatax.rest.client.models.PingResultModel;
import net.avalara.avatax.rest.client.models.TaxCodeModel;
import net.avalara.avatax.rest.client.models.TransactionModel;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Calvin Keum
 */
@Component(
	enabled = false, immediate = true, service = CommerceAvalaraConnector.class
)
public class CommerceAvalaraConnectorImpl implements CommerceAvalaraConnector {

	@Override
	public TransactionModel createSalesInvoiceTransaction(
			CommerceOrder commerceOrder)
		throws Exception {

		return _createTransaction(DocumentType.SalesInvoice, commerceOrder);
	}

	@Override
	public TransactionModel createSalesOrderTransaction(
			CommerceOrder commerceOrder)
		throws Exception {

		return _createTransaction(DocumentType.SalesOrder, commerceOrder);
	}

	@Override
	public List<EntityUseCodeModel> getEntityUseCodes(long groupId)
		throws Exception {

		AvaTaxClient avaTaxClient = _getAvaTaxClient(groupId);

		FetchResult<EntityUseCodeModel> entityUseCodeModelFetchResult =
			avaTaxClient.listEntityUseCodes(null, null, null, null);

		return entityUseCodeModelFetchResult.getValue();
	}

	@Override
	public List<TaxCodeModel> getTaxCodes(long groupId) throws Exception {
		AvaTaxClient avaTaxClient = _getAvaTaxClient(groupId);

		FetchResult<TaxCodeModel> taxCodeModelFetchResult =
			avaTaxClient.listTaxCodes(null, null, null, null);

		return taxCodeModelFetchResult.getValue();
	}

	@Override
	public String getTaxRateByZipCode(long groupId) throws Exception {
		AvaTaxClient avaTaxClient = _getAvaTaxClient(groupId);

		Date date = new Date() {

			@Override
			public String toString() {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");

				return simpleDateFormat.format(this);
			}

		};

		return avaTaxClient.downloadTaxRatesByZipCode(date, null);
	}

	@Override
	public AddressResolutionModel validateAddress(
			long groupId, CommerceAddress commerceAddress)
		throws Exception {

		AvaTaxClient avaTaxClient = _getAvaTaxClient(groupId);

		CommerceRegion commerceRegion = commerceAddress.getCommerceRegion();

		CommerceCountry commerceCountry = commerceAddress.getCommerceCountry();

		return avaTaxClient.resolveAddress(
			commerceAddress.getStreet1(), commerceAddress.getStreet2(),
			commerceAddress.getStreet3(), commerceAddress.getCity(),
			commerceRegion.getName(), commerceAddress.getZip(),
			commerceCountry.getName(), TextCase.Upper);
	}

	@Override
	public void verifyConnection(
			String accountNumber, String licenseKey, String serviceUrl)
		throws Exception {

		try {
			AvaTaxClient avaTaxClient = _getAvaTaxClient(
				accountNumber, licenseKey, serviceUrl);

			PingResultModel pingResultModel = avaTaxClient.ping();

			if (!pingResultModel.getAuthenticated()) {
				throw new CommerceAvalaraConnectionException();
			}
		}
		catch (Exception exception) {
			throw new CommerceAvalaraConnectionException(exception.getCause());
		}
	}

	private TransactionModel _createTransaction(
			DocumentType documentType, CommerceOrder commerceOrder)
		throws Exception {

		AvaTaxClient avaTaxClient = _getAvaTaxClient(
			commerceOrder.getGroupId());

		CommerceAccount commerceAccount = commerceOrder.getCommerceAccount();

		// TODO customerCode is accountId or ERC?

		TransactionBuilder transactionBuilder = new TransactionBuilder(
			avaTaxClient, _commerceAvalaraTaxTypeConfiguration.companyCode(),
			documentType,
			String.valueOf(commerceAccount.getCommerceAccountId()));

		transactionBuilder.withCode(
			String.valueOf(commerceOrder.getCommerceOrderId()));

		CreateTransactionModel intermediaryTransactionModel =
			transactionBuilder.getIntermediaryTransactionModel();

		CommerceCurrency commerceCurrency = commerceOrder.getCommerceCurrency();

		intermediaryTransactionModel.setCurrencyCode(
			commerceCurrency.getCode());

		// TODO get entity use code from account

		intermediaryTransactionModel.setEntityUseCode("");

		_setAddress(commerceOrder, transactionBuilder);

		_setLines(commerceOrder, transactionBuilder);

		return transactionBuilder.create();
	}

	private AvaTaxClient _getAvaTaxClient(long groupId) throws PortalException {
		_commerceAvalaraTaxTypeConfiguration =
			_getCommerceTaxTypeAvalaraConfiguration(groupId);

		return _getAvaTaxClient(
			_commerceAvalaraTaxTypeConfiguration.accountNumber(),
			_commerceAvalaraTaxTypeConfiguration.licenseKey(),
			_commerceAvalaraTaxTypeConfiguration.serviceURL());
	}

	private AvaTaxClient _getAvaTaxClient(
		String accountNumber, String licenseKey, String serviceUrl) {

		AvaTaxClient avaTaxClient = new AvaTaxClient(
			CommerceAvalaraConstants.APP_MACHINE,
			CommerceAvalaraConstants.APP_VERSION,
			CommerceAvalaraConstants.MACHINE_NAME, serviceUrl);

		StringBundler sb = new StringBundler(3);

		sb.append(accountNumber);
		sb.append(StringPool.COLON);
		sb.append(licenseKey);

		String securityHeader = sb.toString();

		byte[] securityHeaderBytes = securityHeader.getBytes();

		String encodedSecurityHeader = Base64.encode(securityHeaderBytes);

		return avaTaxClient.withSecurity(encodedSecurityHeader);
	}

	private CommerceAvalaraTaxTypeConfiguration
			_getCommerceTaxTypeAvalaraConfiguration(long groupId)
		throws PortalException {

		return _configurationProvider.getConfiguration(
			CommerceAvalaraTaxTypeConfiguration.class,
			new GroupServiceSettingsLocator(
				groupId, CommerceAvalaraConstants.SERVICE_NAME));
	}

	private void _setAddress(
			CommerceOrder commerceOrder, TransactionBuilder transactionBuilder)
		throws Exception {

		if (commerceOrder.getShippingAddress() != null) {
			CommerceAddress commerceOrderShippingAddress =
				commerceOrder.getShippingAddress();

			CommerceRegion commerceRegion =
				commerceOrderShippingAddress.getCommerceRegion();

			CommerceCountry commerceCountry =
				commerceOrderShippingAddress.getCommerceCountry();

			// TODO we need to get the shipFrom address from the warehouse

			transactionBuilder.withAddress(
				TransactionAddressType.ShipTo,
				commerceOrderShippingAddress.getStreet1(),
				commerceOrderShippingAddress.getStreet2(),
				commerceOrderShippingAddress.getStreet3(),
				commerceOrderShippingAddress.getCity(),
				commerceRegion.getName(), commerceOrderShippingAddress.getZip(),
				commerceCountry.getTwoLettersISOCode());
		}
	}

	private void _setLines(
			CommerceOrder commerceOrder, TransactionBuilder transactionBuilder)
		throws Exception {

		List<CommerceOrderItem> commerceOrderItems =
			commerceOrder.getCommerceOrderItems();

		for (CommerceOrderItem commerceOrderItem : commerceOrderItems) {
			CPDefinition cpDefinition = commerceOrderItem.getCPDefinition();

			CPTaxCategory cpTaxCategory = cpDefinition.getCPTaxCategory();

			String taxCode = StringPool.BLANK;

			if (cpTaxCategory != null) {
				taxCode = cpTaxCategory.getExternalReferenceCode();
			}

			transactionBuilder.withLine(
				commerceOrderItem.getFinalPrice(),
				BigDecimal.valueOf(commerceOrderItem.getQuantity()), taxCode,
				commerceOrderItem.getSku(), cpDefinition.getDescription(),
				String.valueOf(commerceOrderItem.getCommerceOrderItemId()),
				String.valueOf(commerceOrderItem.getCPInstanceId()));
		}

		CommerceShippingTaxConfiguration commerceShippingTaxConfiguration =
			_configurationProvider.getConfiguration(
				CommerceShippingTaxConfiguration.class,
				new GroupServiceSettingsLocator(
					commerceOrder.getGroupId(),
					CommerceConstants.TAX_SERVICE_NAME));

		CPTaxCategory cpTaxCategory =
			_cpTaxCategoryLocalService.fetchCPTaxCategory(
				commerceShippingTaxConfiguration.taxCategoryId());

		if (cpTaxCategory == null) {
			return;
		}

		transactionBuilder.withLine(
			commerceOrder.getShippingAmount(), BigDecimal.ONE,
			cpTaxCategory.getExternalReferenceCode(),
			CommerceAvalaraConstants.SHIPPING_LINE_ITEM, StringPool.BLANK);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAvalaraConnectorImpl.class);

	private CommerceAvalaraTaxTypeConfiguration
		_commerceAvalaraTaxTypeConfiguration;

	@Reference
	private ConfigurationProvider _configurationProvider;

	@Reference
	private CPTaxCategoryLocalService _cpTaxCategoryLocalService;

}