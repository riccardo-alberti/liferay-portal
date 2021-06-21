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

package com.liferay.commerce.pricing.web.internal.model;

import com.liferay.commerce.frontend.model.LabelField;

/**
 * @author Marco Leo
 * @author Alessio Antonio Rendina
 */
public class PriceCommerceEngineTask {

	public PriceCommerceEngineTask(
		String description, String key, String condition, LabelField status,
		String type) {

		_description = description;
		_key = key;
		_condition = condition;
		_status = status;
		_type = type;
	}

	public String getCondition() {
		return _condition;
	}

	public String getDescription() {
		return _description;
	}

	public String getKey() {
		return _key;
	}

	public LabelField getStatus() {
		return _status;
	}

	public String getType() {
		return _type;
	}

	private final String _condition;
	private final String _description;
	private final String _key;
	private final LabelField _status;
	private final String _type;

}