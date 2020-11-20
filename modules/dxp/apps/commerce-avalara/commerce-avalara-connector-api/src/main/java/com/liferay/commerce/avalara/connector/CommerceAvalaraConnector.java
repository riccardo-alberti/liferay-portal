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

package com.liferay.commerce.avalara.connector;

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;

import java.util.List;

import net.avalara.avatax.rest.client.models.AddressResolutionModel;
import net.avalara.avatax.rest.client.models.EntityUseCodeModel;
import net.avalara.avatax.rest.client.models.TaxCodeModel;
import net.avalara.avatax.rest.client.models.TransactionModel;

/**
 * @author Riccardo Alberti
 */
public interface CommerceAvalaraConnector {

	public TransactionModel createSalesInvoiceTransaction(
			CommerceOrder commerceOrder)
		throws Exception;

	public TransactionModel createSalesOrderTransaction(
			CommerceOrder commerceOrder)
		throws Exception;

	public List<EntityUseCodeModel> getEntityUseCodes(long groupId)
		throws Exception;

	public List<TaxCodeModel> getTaxCodes(long groupId) throws Exception;

	public String getTaxRateByZipCode(long groupId) throws Exception;

	public AddressResolutionModel validateAddress(
			long groupId, CommerceAddress commerceAddress)
		throws Exception;

	public void verifyConnection(
			String accountNumber, String licenseKey, String serviceUrl)
		throws Exception;

}