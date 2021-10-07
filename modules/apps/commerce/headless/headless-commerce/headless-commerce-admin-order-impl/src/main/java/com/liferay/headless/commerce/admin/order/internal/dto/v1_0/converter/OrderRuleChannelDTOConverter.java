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

import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntry;
import com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryRelService;
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryService;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRuleChannel;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Marco Leo
 */
@Component(
	enabled = false,
	property = "dto.class.name=com.liferay.commerce.order.rule.model.CommerceOrderRuleEntryRel-Channel",
	service = {DTOConverter.class, OrderRuleChannelDTOConverter.class}
)
public class OrderRuleChannelDTOConverter
	implements DTOConverter<CommerceOrderRuleEntryRel, OrderRuleChannel> {

	@Override
	public String getContentType() {
		return OrderRuleChannel.class.getSimpleName();
	}

	@Override
	public OrderRuleChannel toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceOrderRuleEntryRel commerceOrderRuleEntryRel =
			_commerceOrderRuleEntryRelService.getCommerceOrderRuleEntryRel(
				(Long)dtoConverterContext.getId());

		CommerceChannel commerceChannel =
			_commerceChannelService.getCommerceChannel(
				commerceOrderRuleEntryRel.getClassPK());
		CommerceOrderRuleEntry commerceOrderRuleEntry =
			commerceOrderRuleEntryRel.getCommerceOrderRuleEntry();

		return new OrderRuleChannel() {
			{
				actions = dtoConverterContext.getActions();
				channelExternalReferenceCode =
					commerceChannel.getExternalReferenceCode();
				channelId = commerceChannel.getCommerceChannelId();
				orderRuleChannelId =
					commerceOrderRuleEntryRel.getCommerceOrderRuleEntryRelId();
				orderRuleExternalReferenceCode =
					commerceOrderRuleEntry.getExternalReferenceCode();
				orderRuleId =
					commerceOrderRuleEntry.getCommerceOrderRuleEntryId();
			}
		};
	}

	@Reference
	private CommerceChannelService _commerceChannelService;

	@Reference
	private CommerceOrderRuleEntryRelService _commerceOrderRuleEntryRelService;

	@Reference
	private CommerceOrderRuleEntryService _commerceOrderRuleEntryService;

}