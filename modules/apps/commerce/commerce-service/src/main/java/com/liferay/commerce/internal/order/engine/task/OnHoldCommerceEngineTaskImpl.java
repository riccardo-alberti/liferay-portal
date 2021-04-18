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

package com.liferay.commerce.internal.order.engine.task;

import com.liferay.commerce.constants.CommerceOrderConstants;
import com.liferay.commerce.engine.CommerceEngine;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"commerce.engine.task.key=" + OnHoldCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + OnHoldCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + OnHoldCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class OnHoldCommerceEngineTaskImpl implements CommerceEngineTask {

	public static final String KEY = "on-hold";

	public static final int PRIORITY = -1;

	public static final String TYPE = "order-engine";

	@Override
	public boolean evaluate(Map<String, Object> context) {
		return true;
	}

	@Override
	public void execute(Map<String, Object> context) throws Exception {
		CommerceOrder commerceOrder = (CommerceOrder)context.get(
			"_commerceOrder");

		if (commerceOrder.getOrderStatus() ==
				CommerceOrderConstants.ORDER_STATUS_ON_HOLD) {

			_commerceEngine.executeTask(context, "processing", "order-engine");

			return;
		}

		commerceOrder.setOrderStatus(
			CommerceOrderConstants.ORDER_STATUS_ON_HOLD);

		_commerceOrderService.updateCommerceOrder(commerceOrder);

		context.put("_commerceOrder", commerceOrder);
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(
			locale,
			CommerceOrderConstants.getOrderStatusLabel(
				CommerceOrderConstants.ORDER_STATUS_ON_HOLD));
	}

	@Override
	public Optional<CommerceEngineTask> getNext(Map<String, Object> context)
		throws Exception {

		return Optional.empty();
	}

	@Override
	public int getPriority() {
		return PRIORITY;
	}

	@Override
	public String getType() {
		return TYPE;
	}

	@Override
	public Optional<CommerceEngineTask> handleException(
			Exception exception, Map<String, Object> context)
		throws Exception {

		return Optional.empty();
	}

	@Override
	public boolean isActive(Map<String, Object> context) {
		return true;
	}

	@Override
	public boolean isWorkflowEnabled(Map<String, Object> context) {
		return false;
	}

	@Reference
	private CommerceEngine _commerceEngine;

	@Reference
	private CommerceOrderService _commerceOrderService;

}