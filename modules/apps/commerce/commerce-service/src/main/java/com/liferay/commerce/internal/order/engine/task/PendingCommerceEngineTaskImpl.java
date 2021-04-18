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
import com.liferay.commerce.constants.CommercePaymentConstants;
import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.engine.task.CommerceEngineTaskRegistry;
import com.liferay.commerce.model.CommerceOrder;
import com.liferay.commerce.order.CommerceOrderValidatorRegistry;
import com.liferay.commerce.payment.method.CommercePaymentMethod;
import com.liferay.commerce.payment.method.CommercePaymentMethodRegistry;
import com.liferay.commerce.service.CommerceOrderService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowHandlerRegistryUtil;

import java.util.HashMap;
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
		"commerce.engine.task.key=" + PendingCommerceEngineTaskImpl.KEY,
		"commerce.engine.task.priority:Integer=" + PendingCommerceEngineTaskImpl.PRIORITY,
		"commerce.engine.task.type=" + PendingCommerceEngineTaskImpl.TYPE
	},
	service = CommerceEngineTask.class
)
public class PendingCommerceEngineTaskImpl implements CommerceEngineTask {

	public static final String KEY = "pending";

	public static final int PRIORITY = 30;

	public static final String TYPE = "order-engine";

	@Override
	public boolean evaluate(Map<String, Object> context) {
		CommerceOrder commerceOrder = (CommerceOrder)context.get(
			"_commerceOrder");

		CommercePaymentMethod commercePaymentMethod =
			_commercePaymentMethodRegistry.getCommercePaymentMethod(
				commerceOrder.getCommercePaymentMethodKey());

		if (commercePaymentMethod == null) {
			return true;
		}

		if ((commerceOrder.getPaymentStatus() ==
				CommerceOrderConstants.PAYMENT_STATUS_PAID) ||
			(commercePaymentMethod.getPaymentType() ==
				CommercePaymentConstants.
					COMMERCE_PAYMENT_METHOD_TYPE_OFFLINE)) {

			try {
				return _commerceOrderValidatorRegistry.isValid(
					null, commerceOrder);
			}
			catch (PortalException portalException) {
				_log.error(portalException, portalException);
			}
		}

		return false;
	}

	@Override
	public void execute(Map<String, Object> context) throws Exception {
		long userId = (Long)context.get("_userId");

		CommerceOrder commerceOrder = (CommerceOrder)context.get(
			"_commerceOrder");

		commerceOrder.setOrderStatus(
			CommerceOrderConstants.ORDER_STATUS_PENDING);

		commerceOrder = _commerceOrderService.updateCommerceOrder(
			commerceOrder);

		if (isWorkflowEnabled(context)) {
			commerceOrder.setStatus(WorkflowConstants.STATUS_PENDING);

			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setScopeGroupId(commerceOrder.getGroupId());
			serviceContext.setUserId(userId);
			serviceContext.setWorkflowAction(WorkflowConstants.ACTION_PUBLISH);

			commerceOrder = WorkflowHandlerRegistryUtil.startWorkflowInstance(
				commerceOrder.getCompanyId(), commerceOrder.getScopeGroupId(),
				userId, CommerceOrder.class.getName(),
				commerceOrder.getCommerceOrderId(), commerceOrder,
				serviceContext, new HashMap<>());
		}

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
				CommerceOrderConstants.ORDER_STATUS_PENDING));
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
		CommerceOrder commerceOrder = (CommerceOrder)context.get(
			"_commerceOrder");

		WorkflowDefinitionLink workflowDefinitionLink =
			_workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
				commerceOrder.getCompanyId(), commerceOrder.getGroupId(),
				CommerceOrder.class.getName(), 0,
				CommerceOrderConstants.TYPE_PK_FULFILLMENT, true);

		if (workflowDefinitionLink != null) {
			return true;
		}

		return false;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		PendingCommerceEngineTaskImpl.class);

	@Reference
	private CommerceEngineTaskRegistry _commerceEngineTaskRegistry;

	@Reference
	private CommerceOrderService _commerceOrderService;

	@Reference
	private CommerceOrderValidatorRegistry _commerceOrderValidatorRegistry;

	@Reference
	private CommercePaymentMethodRegistry _commercePaymentMethodRegistry;

	@Reference
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

}