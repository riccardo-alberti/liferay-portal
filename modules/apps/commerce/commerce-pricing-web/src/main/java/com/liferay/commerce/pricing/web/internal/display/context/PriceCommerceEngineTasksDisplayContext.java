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

package com.liferay.commerce.pricing.web.internal.display.context;

import com.liferay.commerce.pricing.web.internal.display.context.util.CommercePricingRequestHelper;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Alberti
 */
public class PriceCommerceEngineTasksDisplayContext {

	public PriceCommerceEngineTasksDisplayContext(
		CommerceChannelLocalService commerceChannelLocalService,
		HttpServletRequest httpServletRequest) {

		_commerceChannelLocalService = commerceChannelLocalService;

		_commercePricingRequestHelper = new CommercePricingRequestHelper(
			httpServletRequest);
	}

	public CommerceChannel getCommerceChannel() throws PortalException {
		long commerceChannelId = ParamUtil.getLong(
			_commercePricingRequestHelper.getRequest(), "commerceChannelId");

		if (commerceChannelId > 0) {
			return _commerceChannelLocalService.getCommerceChannel(
				commerceChannelId);
		}

		return null;
	}

	public long getCommerceChannelId() throws PortalException {
		CommerceChannel commerceChannel = getCommerceChannel();

		if (commerceChannel == null) {
			return 0;
		}

		return commerceChannel.getCommerceChannelId();
	}

	private final CommerceChannelLocalService _commerceChannelLocalService;
	private final CommercePricingRequestHelper _commercePricingRequestHelper;

}