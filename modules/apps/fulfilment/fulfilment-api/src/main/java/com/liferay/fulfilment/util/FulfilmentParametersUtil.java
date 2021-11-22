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

package com.liferay.fulfilment.util;

import com.liferay.portal.kernel.json.JSONDeserializer;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONSerializer;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Riccardo Alberti
 */
public class FulfilmentParametersUtil {

	public static Map<String, Serializable> deserializeParameters(
		String parameters) {

		if (Validator.isBlank(parameters)) {
			return new HashMap<>();
		}

		JSONDeserializer<Object> jsonDeserializer =
			JSONFactoryUtil.createJSONDeserializer();

		return (Map<String, Serializable>)jsonDeserializer.deserialize(
			parameters);
	}

	public static String serializeParameters(
		Map<String, Serializable> parameters) {

		JSONSerializer jsonSerializer = JSONFactoryUtil.createJSONSerializer();

		return jsonSerializer.serialize(parameters);
	}

}