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

package com.liferay.headless.commerce.admin.order.internal.util.v1_0;

import com.liferay.account.model.AccountEntry;
import com.liferay.commerce.account.exception.NoSuchAccountException;
import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.service.CommerceAccountService;
import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntry;
import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryRelService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRuleAccount;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author Alessio Antonio Rendina
 */
public class OrderRuleAccountUtil {

	public static CommerceOrderRuleEntryRel
			addCommerceOrderRuleEntryCommerceAccountRel(
				CommerceAccountService commerceAccountService,
				CommerceOrderRuleEntryRelService
					commerceOrderRuleEntryRelService,
				CommerceOrderRuleEntry commerceOrderRuleEntry,
				OrderRuleAccount orderRuleAccount)
		throws PortalException {

		CommerceAccount commerceAccount;

		if (Validator.isNull(
				orderRuleAccount.getAccountExternalReferenceCode())) {

			commerceAccount = commerceAccountService.getCommerceAccount(
				orderRuleAccount.getAccountId());
		}
		else {
			commerceAccount =
				commerceAccountService.fetchByExternalReferenceCode(
					commerceOrderRuleEntry.getCompanyId(),
					orderRuleAccount.getAccountExternalReferenceCode());

			if (commerceAccount == null) {
				String accountExternalReferenceCode =
					orderRuleAccount.getAccountExternalReferenceCode();

				throw new NoSuchAccountException(
					"Unable to find account with external reference code " +
						accountExternalReferenceCode);
			}
		}

		return commerceOrderRuleEntryRelService.addCommerceOrderRuleEntryRel(
			AccountEntry.class.getName(),
			commerceAccount.getCommerceAccountId(),
			commerceOrderRuleEntry.getCommerceOrderRuleEntryId());
	}

}