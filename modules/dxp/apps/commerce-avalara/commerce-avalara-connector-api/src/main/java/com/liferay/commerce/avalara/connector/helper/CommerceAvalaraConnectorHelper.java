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

package com.liferay.commerce.avalara.connector.helper;

import com.liferay.commerce.model.CommerceAddress;
import com.liferay.commerce.model.CommerceOrder;

/**
 * @author Riccardo Alberti
 */
public interface CommerceAvalaraConnectorHelper {

	public void updateByAddressEntries(long groupId) throws Exception;

	public CommerceOrder updateOrderInvoiceTaxInfo(CommerceOrder commerceOrder)
		throws Exception;

	public CommerceOrder updateOrderTaxInfo(CommerceOrder commerceOrder)
		throws Exception;

	public CommerceAddress validateCommerceAddress(
			long groupId, CommerceAddress commerceAddress)
		throws Exception;

}