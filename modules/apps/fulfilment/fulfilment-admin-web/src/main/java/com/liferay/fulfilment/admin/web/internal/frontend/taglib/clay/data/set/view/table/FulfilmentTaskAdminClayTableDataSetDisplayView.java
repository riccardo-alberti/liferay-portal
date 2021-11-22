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

package com.liferay.fulfilment.admin.web.internal.frontend.taglib.clay.data.set.view.table;

import com.liferay.frontend.taglib.clay.data.set.ClayDataSetDisplayView;
import com.liferay.frontend.taglib.clay.data.set.view.table.BaseTableClayDataSetDisplayView;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchema;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchemaBuilder;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchemaBuilderFactory;
import com.liferay.frontend.taglib.clay.data.set.view.table.ClayTableSchemaField;
import com.liferay.fulfilment.admin.web.internal.frontend.constants.FulfilmentDataSetConstants;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	immediate = true,
	property = "clay.data.set.display.name=" + FulfilmentDataSetConstants.FULFILMENT_TASK_DATA_SET_KEY,
	service = ClayDataSetDisplayView.class
)
public class FulfilmentTaskAdminClayTableDataSetDisplayView
	extends BaseTableClayDataSetDisplayView {

	@Override
	public ClayTableSchema getClayTableSchema() {
		ClayTableSchemaBuilder clayTableSchemaBuilder =
			_clayTableSchemaBuilderFactory.create();

		ClayTableSchemaField nameClayTableSchemaField =
			clayTableSchemaBuilder.addClayTableSchemaField("index", "task-id");

		nameClayTableSchemaField.setContentRenderer("actionLink");

		clayTableSchemaBuilder.addClayTableSchemaField("type", "type");

		ClayTableSchemaField startDateClayTableSchemaField =
			clayTableSchemaBuilder.addClayTableSchemaField(
				"startDate", "start-date");

		startDateClayTableSchemaField.setContentRenderer("date");
		startDateClayTableSchemaField.setSortable(true);

		ClayTableSchemaField endDateClayTableSchemaField =
			clayTableSchemaBuilder.addClayTableSchemaField(
				"endDate", "end-date");

		endDateClayTableSchemaField.setContentRenderer("date");
		endDateClayTableSchemaField.setSortable(true);

		ClayTableSchemaField statusClayTableSchemaField =
			clayTableSchemaBuilder.addClayTableSchemaField(
				"workflowStatusInfo", "status");

		statusClayTableSchemaField.setContentRenderer("status");

		return clayTableSchemaBuilder.build();
	}

	@Reference
	private ClayTableSchemaBuilderFactory _clayTableSchemaBuilderFactory;

}