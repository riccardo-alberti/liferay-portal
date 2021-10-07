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

package com.liferay.headless.commerce.admin.order.internal.dto.v1_0.converter;

import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.service.CommerceAccountGroupService;
import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntry;
import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryRelService;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRuleAccountGroup;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	enabled = false,
	property = "dto.class.name=com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel-AccountGroup",
	service = {DTOConverter.class, OrderRuleAccountGroupDTOConverter.class}
)
public class OrderRuleAccountGroupDTOConverter
	implements DTOConverter<CommerceOrderRuleEntryRel, OrderRuleAccountGroup> {

	@Override
	public String getContentType() {
		return OrderRuleAccountGroup.class.getSimpleName();
	}

	@Override
	public OrderRuleAccountGroup toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceOrderRuleEntryRel commerceOrderRuleEntryRel =
			_commerceOrderRuleEntryRelService.getCommerceOrderRuleEntryRel(
				(Long)dtoConverterContext.getId());

		CommerceAccountGroup commerceAccountGroup =
			_commerceAccountGroupService.getCommerceAccountGroup(
				commerceOrderRuleEntryRel.getClassPK());
		CommerceOrderRuleEntry commerceOrderRuleEntry =
			commerceOrderRuleEntryRel.getCommerceOrderRuleEntry();

		return new OrderRuleAccountGroup() {
			{
				accountGroupExternalReferenceCode =
					commerceAccountGroup.getExternalReferenceCode();
				accountGroupId =
					commerceAccountGroup.getCommerceAccountGroupId();
				actions = dtoConverterContext.getActions();
				orderRuleAccountGroupId =
					commerceOrderRuleEntryRel.getCommerceOrderRuleEntryRelId();
				orderRuleExternalReferenceCode =
					commerceOrderRuleEntry.getExternalReferenceCode();
				orderRuleId =
					commerceOrderRuleEntry.getCommerceOrderRuleEntryId();
			}
		};
	}

	@Reference
	private CommerceAccountGroupService _commerceAccountGroupService;

	@Reference
	private CommerceOrderRuleEntryRelService _commerceOrderRuleEntryRelService;

	@Reference
	private CommerceOrderRuleEntryService _commerceOrderRuleEntryService;

}