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

package com.liferay.headless.fulfilment.admin.client.serdes.v1_0;

import com.liferay.headless.fulfilment.admin.client.dto.v1_0.Parameter;
import com.liferay.headless.fulfilment.admin.client.dto.v1_0.Request;
import com.liferay.headless.fulfilment.admin.client.dto.v1_0.Task;
import com.liferay.headless.fulfilment.admin.client.json.BaseJSONParser;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import javax.annotation.Generated;

/**
 * @author Riccardo Alberti
 * @generated
 */
@Generated("")
public class RequestSerDes {

	public static Request toDTO(String json) {
		RequestJSONParser requestJSONParser = new RequestJSONParser();

		return requestJSONParser.parseToDTO(json);
	}

	public static Request[] toDTOs(String json) {
		RequestJSONParser requestJSONParser = new RequestJSONParser();

		return requestJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Request request) {
		if (request == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (request.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(request.getActions()));
		}

		if (request.getCreateDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(request.getCreateDate()));

			sb.append("\"");
		}

		if (request.getEndDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"endDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(request.getEndDate()));

			sb.append("\"");
		}

		if (request.getExternalReferenceCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"externalReferenceCode\": ");

			sb.append("\"");

			sb.append(_escape(request.getExternalReferenceCode()));

			sb.append("\"");
		}

		if (request.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(request.getId());
		}

		if (request.getInputParameters() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"inputParameters\": ");

			sb.append("[");

			for (int i = 0; i < request.getInputParameters().length; i++) {
				sb.append(String.valueOf(request.getInputParameters()[i]));

				if ((i + 1) < request.getInputParameters().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (request.getOutputParameters() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"outputParameters\": ");

			sb.append("[");

			for (int i = 0; i < request.getOutputParameters().length; i++) {
				sb.append(String.valueOf(request.getOutputParameters()[i]));

				if ((i + 1) < request.getOutputParameters().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (request.getReplyTo() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"replyTo\": ");

			sb.append("\"");

			sb.append(_escape(request.getReplyTo()));

			sb.append("\"");
		}

		if (request.getStartDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"startDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(request.getStartDate()));

			sb.append("\"");
		}

		if (request.getTasks() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"tasks\": ");

			sb.append("[");

			for (int i = 0; i < request.getTasks().length; i++) {
				sb.append(String.valueOf(request.getTasks()[i]));

				if ((i + 1) < request.getTasks().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (request.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(request.getType()));

			sb.append("\"");
		}

		if (request.getWorkflowDefinition() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workflowDefinition\": ");

			sb.append("\"");

			sb.append(_escape(request.getWorkflowDefinition()));

			sb.append("\"");
		}

		if (request.getWorkflowStatusInfo() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workflowStatusInfo\": ");

			sb.append(String.valueOf(request.getWorkflowStatusInfo()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		RequestJSONParser requestJSONParser = new RequestJSONParser();

		return requestJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Request request) {
		if (request == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (request.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put("actions", String.valueOf(request.getActions()));
		}

		if (request.getCreateDate() == null) {
			map.put("createDate", null);
		}
		else {
			map.put(
				"createDate",
				liferayToJSONDateFormat.format(request.getCreateDate()));
		}

		if (request.getEndDate() == null) {
			map.put("endDate", null);
		}
		else {
			map.put(
				"endDate",
				liferayToJSONDateFormat.format(request.getEndDate()));
		}

		if (request.getExternalReferenceCode() == null) {
			map.put("externalReferenceCode", null);
		}
		else {
			map.put(
				"externalReferenceCode",
				String.valueOf(request.getExternalReferenceCode()));
		}

		if (request.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(request.getId()));
		}

		if (request.getInputParameters() == null) {
			map.put("inputParameters", null);
		}
		else {
			map.put(
				"inputParameters",
				String.valueOf(request.getInputParameters()));
		}

		if (request.getOutputParameters() == null) {
			map.put("outputParameters", null);
		}
		else {
			map.put(
				"outputParameters",
				String.valueOf(request.getOutputParameters()));
		}

		if (request.getReplyTo() == null) {
			map.put("replyTo", null);
		}
		else {
			map.put("replyTo", String.valueOf(request.getReplyTo()));
		}

		if (request.getStartDate() == null) {
			map.put("startDate", null);
		}
		else {
			map.put(
				"startDate",
				liferayToJSONDateFormat.format(request.getStartDate()));
		}

		if (request.getTasks() == null) {
			map.put("tasks", null);
		}
		else {
			map.put("tasks", String.valueOf(request.getTasks()));
		}

		if (request.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(request.getType()));
		}

		if (request.getWorkflowDefinition() == null) {
			map.put("workflowDefinition", null);
		}
		else {
			map.put(
				"workflowDefinition",
				String.valueOf(request.getWorkflowDefinition()));
		}

		if (request.getWorkflowStatusInfo() == null) {
			map.put("workflowStatusInfo", null);
		}
		else {
			map.put(
				"workflowStatusInfo",
				String.valueOf(request.getWorkflowStatusInfo()));
		}

		return map;
	}

	public static class RequestJSONParser extends BaseJSONParser<Request> {

		@Override
		protected Request createDTO() {
			return new Request();
		}

		@Override
		protected Request[] createDTOArray(int size) {
			return new Request[size];
		}

		@Override
		protected void setField(
			Request request, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					request.setActions(
						(Map)RequestSerDes.toMap((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				if (jsonParserFieldValue != null) {
					request.setCreateDate(toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "endDate")) {
				if (jsonParserFieldValue != null) {
					request.setEndDate(toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "externalReferenceCode")) {

				if (jsonParserFieldValue != null) {
					request.setExternalReferenceCode(
						(String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					request.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "inputParameters")) {
				if (jsonParserFieldValue != null) {
					request.setInputParameters(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> ParameterSerDes.toDTO((String)object)
						).toArray(
							size -> new Parameter[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "outputParameters")) {
				if (jsonParserFieldValue != null) {
					request.setOutputParameters(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> ParameterSerDes.toDTO((String)object)
						).toArray(
							size -> new Parameter[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "replyTo")) {
				if (jsonParserFieldValue != null) {
					request.setReplyTo((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "startDate")) {
				if (jsonParserFieldValue != null) {
					request.setStartDate(toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "tasks")) {
				if (jsonParserFieldValue != null) {
					request.setTasks(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> TaskSerDes.toDTO((String)object)
						).toArray(
							size -> new Task[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					request.setType((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "workflowDefinition")) {

				if (jsonParserFieldValue != null) {
					request.setWorkflowDefinition((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "workflowStatusInfo")) {

				if (jsonParserFieldValue != null) {
					request.setWorkflowStatusInfo(
						StatusSerDes.toDTO((String)jsonParserFieldValue));
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}