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
import com.liferay.commerce.order.rule.service.CommerceOrderRuleEntryService;
import com.liferay.headless.commerce.admin.order.dto.v1_0.OrderRule;
import com.liferay.headless.commerce.admin.order.dto.v1_0.Status;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.language.LanguageResources;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = "dto.class.name=com.liferay.commerce.order.rule.model.CommerceOrderRuleEntry",
	service = {DTOConverter.class, OrderRuleDTOConverter.class}
)
public class OrderRuleDTOConverter
	implements DTOConverter<CommerceOrderRuleEntry, OrderRule> {

	@Override
	public String getContentType() {
		return OrderRule.class.getSimpleName();
	}

	@Override
	public OrderRule toDTO(DTOConverterContext dtoConverterContext)
		throws Exception {

		CommerceOrderRuleEntry commerceOrderRuleEntry =
			_commerceOrderRuleEntryService.getCommerceOrderRuleEntry(
				(Long)dtoConverterContext.getId());

		return new OrderRule() {
			{
				actions = dtoConverterContext.getActions();
				active = commerceOrderRuleEntry.isActive();
				description = commerceOrderRuleEntry.getDescription();
				displayDate = commerceOrderRuleEntry.getDisplayDate();
				expirationDate = commerceOrderRuleEntry.getExpirationDate();
				externalReferenceCode =
					commerceOrderRuleEntry.getExternalReferenceCode();
				id = commerceOrderRuleEntry.getCommerceOrderRuleEntryId();
				name = commerceOrderRuleEntry.getName();
				type = commerceOrderRuleEntry.getType();
				typeSettings = commerceOrderRuleEntry.getTypeSettings();
				workflowStatusInfo = _getWorkflowStatusInfo(
					WorkflowConstants.getStatusLabel(
						commerceOrderRuleEntry.getStatus()),
					LanguageUtil.get(
						LanguageResources.getResourceBundle(
							dtoConverterContext.getLocale()),
						WorkflowConstants.getStatusLabel(
							commerceOrderRuleEntry.getStatus())),
					commerceOrderRuleEntry.getStatus());
			}
		};
	}

	private Status _getWorkflowStatusInfo(
		String orderTypeStatusLabel, String orderTypeStatusLabelI18n,
		int statusCode) {

		return new Status() {
			{
				code = statusCode;
				label = orderTypeStatusLabel;
				label_i18n = orderTypeStatusLabelI18n;
			}
		};
	}

	@Reference
	private CommerceOrderRuleEntryService _commerceOrderRuleEntryService;

}