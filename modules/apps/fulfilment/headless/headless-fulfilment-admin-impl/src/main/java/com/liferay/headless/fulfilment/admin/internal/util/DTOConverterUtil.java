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

package com.liferay.headless.fulfilment.admin.internal.util;

import com.liferay.headless.fulfilment.admin.dto.v1_0.Parameter;
import com.liferay.headless.fulfilment.admin.dto.v1_0.Status;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

/**
 * @author Riccardo Alberti
 */
public class DTOConverterUtil {

	public static Parameter[] toParameters(String parametersString)
		throws JSONException {

		JSONObject jsonObject = JSONFactoryUtil.createJSONObject(
			parametersString);

		Parameter[] parameters = new Parameter[jsonObject.length()];

		int i = 0;

		for (String key : jsonObject.keySet()) {
			parameters[i] = new Parameter();

			parameters[i].setKey(key);
			parameters[i].setValue(jsonObject.get(key));

			i++;
		}

		return parameters;
	}

	public static Status toStatus(
		String statusLabel, String statusLabelI18n, int statusCode) {

		return new Status() {
			{
				code = statusCode;
				label = statusLabel;
				label_i18n = statusLabelI18n;
			}
		};
	}

}