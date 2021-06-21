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

import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.engine.task.CommerceEngineTaskRegistry;
import com.liferay.commerce.pricing.web.internal.display.context.util.CommercePricingRequestHelper;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Riccardo Alberti
 */
public class PriceCommerceEngineTaskDisplayContext {

	public PriceCommerceEngineTaskDisplayContext(
		CommerceChannelLocalService commerceChannelLocalService,
		CommerceEngineTaskRegistry commerceEngineTaskRegistry,
		HttpServletRequest httpServletRequest) {

		_commerceChannelLocalService = commerceChannelLocalService;
		_commerceEngineTaskRegistry = commerceEngineTaskRegistry;

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

	public CommerceEngineTask getCommerceEngineTask() throws Exception {
		if (_commerceEngineTask != null) {
			return _commerceEngineTask;
		}

		String commerceEngineTaskKey = ParamUtil.getString(
			_commercePricingRequestHelper.getRequest(),
			"commerceEngineTaskKey");

		String commerceEngineTaskType = ParamUtil.getString(
			_commercePricingRequestHelper.getRequest(),
			"commerceEngineTaskType");

		Optional<CommerceEngineTask> commerceEngineTaskOptional =
			_commerceEngineTaskRegistry.getCommerceEngineTask(
				commerceEngineTaskKey, commerceEngineTaskType);

		if (commerceEngineTaskOptional.isPresent()) {
			_commerceEngineTask = commerceEngineTaskOptional.get();
		}

		return _commerceEngineTask;
	}

	public String getCondition() throws Exception {
		CommerceEngineTask commerceEngineTask = getCommerceEngineTask();

		if (commerceEngineTask != null) {
			CommerceChannel commerceChannel = getCommerceChannel();

			if (commerceChannel != null) {
				return commerceEngineTask.getEvaluateCondition(
					commerceChannel.getGroupId());
			}
		}

		return "";
	}

	public boolean isActive() throws Exception {
		CommerceEngineTask commerceEngineTask = getCommerceEngineTask();

		if (commerceEngineTask != null) {
			CommerceChannel commerceChannel = getCommerceChannel();

			return commerceEngineTask.isActive(commerceChannel.getGroupId());
		}

		return false;
	}

	private final CommerceChannelLocalService _commerceChannelLocalService;
	private CommerceEngineTask _commerceEngineTask;
	private final CommerceEngineTaskRegistry _commerceEngineTaskRegistry;
	private final CommercePricingRequestHelper _commercePricingRequestHelper;

}