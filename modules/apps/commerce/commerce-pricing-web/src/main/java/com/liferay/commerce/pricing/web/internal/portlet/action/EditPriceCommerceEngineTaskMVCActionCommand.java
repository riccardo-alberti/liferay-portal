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

package com.liferay.commerce.pricing.web.internal.portlet.action;

import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.engine.task.CommerceEngineTaskRegistry;
import com.liferay.commerce.pricing.constants.CommercePricingPortletKeys;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.Optional;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"javax.portlet.name=" + CommercePricingPortletKeys.PRICE_COMMERCE_ENGINE_TASK,
		"mvc.command.name=/price_commerce_engine_task/edit_price_commerce_engine_task"
	},
	service = MVCActionCommand.class
)
public class EditPriceCommerceEngineTaskMVCActionCommand
	extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

		try {
			if (cmd.equals(Constants.UPDATE)) {
				updatePriceCommerceEngineTask(actionRequest);
			}
		}
		catch (Exception exception) {
			SessionErrors.add(actionRequest, exception.getClass());

			actionResponse.setRenderParameter("mvcPath", "/error.jsp");
		}
	}

	protected CommerceEngineTask updatePriceCommerceEngineTask(
			ActionRequest actionRequest)
		throws Exception {

		String commerceEngineTaskKey = ParamUtil.getString(
			actionRequest, "commerceEngineTaskKey");

		String commerceEngineTaskType = ParamUtil.getString(
			actionRequest, "commerceEngineTaskType");

		Optional<CommerceEngineTask> commerceEngineTaskOptional =
			_commerceEngineTaskRegistry.getCommerceEngineTask(
				commerceEngineTaskKey, commerceEngineTaskType);

		if (!commerceEngineTaskOptional.isPresent()) {
			return null;
		}

		long commerceChannelGroupId = ParamUtil.getLong(
			actionRequest, "commerceChannelGroupId");

		String condition = ParamUtil.getString(actionRequest, "condition");

		boolean active = ParamUtil.getBoolean(actionRequest, "active");

		CommerceEngineTask commerceEngineTask =
			commerceEngineTaskOptional.get();

		commerceEngineTask.updateEvaluateCondition(
			commerceChannelGroupId, condition);

		if (active != commerceEngineTask.isActive(commerceChannelGroupId)) {
			commerceEngineTask.updateStatus(commerceChannelGroupId);
		}

		return commerceEngineTask;
	}

	@Reference
	private CommerceEngineTaskRegistry _commerceEngineTaskRegistry;

}