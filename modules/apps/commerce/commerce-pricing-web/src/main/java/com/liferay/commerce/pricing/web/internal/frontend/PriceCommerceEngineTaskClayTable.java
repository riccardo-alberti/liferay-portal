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

package com.liferay.commerce.pricing.web.internal.frontend;

import com.liferay.commerce.engine.task.CommerceEngineTask;
import com.liferay.commerce.engine.task.CommerceEngineTaskRegistry;
import com.liferay.commerce.frontend.model.LabelField;
import com.liferay.commerce.pricing.web.internal.model.PriceCommerceEngineTask;
import com.liferay.commerce.product.model.CommerceChannel;
import com.liferay.commerce.product.service.CommerceChannelService;
import com.liferay.frontend.taglib.clay.data.Filter;
import com.liferay.frontend.taglib.clay.data.Pagination;
import com.liferay.frontend.taglib.clay.data.set.ClayDataSetActionProvider;
import com.liferay.frontend.taglib.clay.data.set.ClayDataSetDisplayView;
import com.liferay.frontend.taglib.clay.data.set.provider.ClayDataSetDataProvider;
import com.liferay.frontend.taglib.clay.data.set.view.table.BaseTableClayDataSetDisplayView;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchema;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchemaBuilder;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchemaBuilderFactory;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchemaField;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemListBuilder;
import com.liferay.petra.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.PortletProvider;
import com.liferay.portal.kernel.portlet.PortletProviderUtil;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"clay.data.provider.key=" + PriceCommerceEngineTaskClayTable.NAME,
		"clay.data.set.display.name=" + PriceCommerceEngineTaskClayTable.NAME
	},
	service = {
		ClayDataSetActionProvider.class, ClayDataSetDataProvider.class,
		ClayDataSetDisplayView.class
	}
)
public class PriceCommerceEngineTaskClayTable
	extends BaseTableClayDataSetDisplayView
	implements ClayDataSetActionProvider,
			   ClayDataSetDataProvider<PriceCommerceEngineTask> {

	public static final String NAME = "price-commerce-engine-tasks";

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.create();

		ClayTableSchemaField nameField =
			clayTableSchemaBuilder.addClayTableSchemaField("key", "key");

		nameField.setContentRenderer("actionLink");

		clayTableSchemaBuilder.addClayTableSchemaField(
			"condition", "condition");

		clayTableSchemaBuilder.addClayTableSchemaField("type", "type");

		ClayTableSchemaField statusField =
			clayTableSchemaBuilder.addClayTableSchemaField("status", "status");

		statusField.setContentRenderer("label");

		return clayTableSchemaBuilder.build();
	}

	@Override
	public List<DropdownItem> getDropdownItems(
			HttpServletRequest httpServletRequest, long groupId, Object model)
		throws PortalException {

		PriceCommerceEngineTask priceCommerceEngineTask =
			(PriceCommerceEngineTask)model;

		return DropdownItemListBuilder.add(
			dropdownItem -> {
				dropdownItem.setHref(
					PortletURLBuilder.create(
						PortletProviderUtil.getPortletURL(
							httpServletRequest,
							CommerceEngineTask.class.getName(),
							PortletProvider.Action.EDIT)
					).setParameter(
						"commerceChannelId",
						ParamUtil.getLong(
							httpServletRequest, "commerceChannelId")
					).setParameter(
						"commerceEngineTaskKey",
						priceCommerceEngineTask.getKey()
					).setParameter(
						"commerceEngineTaskType",
						priceCommerceEngineTask.getType()
					).setWindowState(
						LiferayWindowState.POP_UP
					).buildString());

				dropdownItem.setLabel(
					LanguageUtil.get(httpServletRequest, "edit"));
				dropdownItem.setTarget("sidePanel");
			}
		).build();
	}

	@Override
	public List<PriceCommerceEngineTask> getItems(
			HttpServletRequest httpServletRequest, Filter filter,
			Pagination pagination, Sort sort)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay)httpServletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		long commerceChannelId = ParamUtil.getLong(
			httpServletRequest, "commerceChannelId");

		CommerceChannel commerceChannel =
			_commerceChannelService.getCommerceChannel(commerceChannelId);

		List<CommerceEngineTask> commerceEngineTasks =
			_commerceEngineTaskRegistry.getCommerceEngineTasks(
				"product-pricing");

		List<CommerceEngineTask> orderPricingCommerceEngineTasks =
			_commerceEngineTaskRegistry.getCommerceEngineTasks("order-pricing");

		commerceEngineTasks.addAll(orderPricingCommerceEngineTasks);

		List<PriceCommerceEngineTask> priceCommerceEngineTasks =
			new ArrayList<>();

		for (CommerceEngineTask commerceEngineTask : commerceEngineTasks) {
			priceCommerceEngineTasks.add(
				new PriceCommerceEngineTask(
					commerceEngineTask.getDescription(themeDisplay.getLocale()),
					commerceEngineTask.getKey(),
					commerceEngineTask.getEvaluateCondition(
						commerceChannel.getGroupId()),
					_getLabelField(
						commerceEngineTask.isActive(
							commerceChannel.getGroupId()),
						themeDisplay.getLocale()),
					commerceEngineTask.getType()));
		}

		return priceCommerceEngineTasks;
	}

	@Override
	public int getItemsCount(
			HttpServletRequest httpServletRequest, Filter filter)
		throws PortalException {

		List<CommerceEngineTask> commerceEngineTasks =
			_commerceEngineTaskRegistry.getCommerceEngineTasks(
				"product-pricing");

		List<CommerceEngineTask> orderPricingCommerceEngineTasks =
			_commerceEngineTaskRegistry.getCommerceEngineTasks("order-pricing");

		commerceEngineTasks.addAll(orderPricingCommerceEngineTasks);

		return commerceEngineTasks.size();
	}

	private LabelField _getLabelField(boolean success, Locale locale) {
		if (success) {
			return new LabelField(
				"success", LanguageUtil.get(locale, "active"));
		}

		return new LabelField("danger", LanguageUtil.get(locale, "inactive"));
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

	@Reference
	private CommerceChannelService _commerceChannelService;

	@Reference
	private CommerceEngineTaskRegistry _commerceEngineTaskRegistry;

}