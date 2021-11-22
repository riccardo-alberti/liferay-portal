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
public class TaskSerDes {

	public static Task toDTO(String json) {
		TaskJSONParser taskJSONParser = new TaskJSONParser();

		return taskJSONParser.parseToDTO(json);
	}

	public static Task[] toDTOs(String json) {
		TaskJSONParser taskJSONParser = new TaskJSONParser();

		return taskJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Task task) {
		if (task == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (task.getActions() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"actions\": ");

			sb.append(_toJSON(task.getActions()));
		}

		if (task.getCreateDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"createDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(task.getCreateDate()));

			sb.append("\"");
		}

		if (task.getEndDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"endDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(task.getEndDate()));

			sb.append("\"");
		}

		if (task.getId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"id\": ");

			sb.append(task.getId());
		}

		if (task.getIndex() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"index\": ");

			sb.append(task.getIndex());
		}

		if (task.getInputParameters() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"inputParameters\": ");

			sb.append("[");

			for (int i = 0; i < task.getInputParameters().length; i++) {
				sb.append(String.valueOf(task.getInputParameters()[i]));

				if ((i + 1) < task.getInputParameters().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (task.getOutputParameters() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"outputParameters\": ");

			sb.append("[");

			for (int i = 0; i < task.getOutputParameters().length; i++) {
				sb.append(String.valueOf(task.getOutputParameters()[i]));

				if ((i + 1) < task.getOutputParameters().length) {
					sb.append(", ");
				}
			}

			sb.append("]");
		}

		if (task.getRequestId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"requestId\": ");

			sb.append(task.getRequestId());
		}

		if (task.getStartDate() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"startDate\": ");

			sb.append("\"");

			sb.append(liferayToJSONDateFormat.format(task.getStartDate()));

			sb.append("\"");
		}

		if (task.getType() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"type\": ");

			sb.append("\"");

			sb.append(_escape(task.getType()));

			sb.append("\"");
		}

		if (task.getWorkflowStatusInfo() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"workflowStatusInfo\": ");

			sb.append(String.valueOf(task.getWorkflowStatusInfo()));
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		TaskJSONParser taskJSONParser = new TaskJSONParser();

		return taskJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Task task) {
		if (task == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		DateFormat liferayToJSONDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");

		if (task.getActions() == null) {
			map.put("actions", null);
		}
		else {
			map.put("actions", String.valueOf(task.getActions()));
		}

		if (task.getCreateDate() == null) {
			map.put("createDate", null);
		}
		else {
			map.put(
				"createDate",
				liferayToJSONDateFormat.format(task.getCreateDate()));
		}

		if (task.getEndDate() == null) {
			map.put("endDate", null);
		}
		else {
			map.put(
				"endDate", liferayToJSONDateFormat.format(task.getEndDate()));
		}

		if (task.getId() == null) {
			map.put("id", null);
		}
		else {
			map.put("id", String.valueOf(task.getId()));
		}

		if (task.getIndex() == null) {
			map.put("index", null);
		}
		else {
			map.put("index", String.valueOf(task.getIndex()));
		}

		if (task.getInputParameters() == null) {
			map.put("inputParameters", null);
		}
		else {
			map.put(
				"inputParameters", String.valueOf(task.getInputParameters()));
		}

		if (task.getOutputParameters() == null) {
			map.put("outputParameters", null);
		}
		else {
			map.put(
				"outputParameters", String.valueOf(task.getOutputParameters()));
		}

		if (task.getRequestId() == null) {
			map.put("requestId", null);
		}
		else {
			map.put("requestId", String.valueOf(task.getRequestId()));
		}

		if (task.getStartDate() == null) {
			map.put("startDate", null);
		}
		else {
			map.put(
				"startDate",
				liferayToJSONDateFormat.format(task.getStartDate()));
		}

		if (task.getType() == null) {
			map.put("type", null);
		}
		else {
			map.put("type", String.valueOf(task.getType()));
		}

		if (task.getWorkflowStatusInfo() == null) {
			map.put("workflowStatusInfo", null);
		}
		else {
			map.put(
				"workflowStatusInfo",
				String.valueOf(task.getWorkflowStatusInfo()));
		}

		return map;
	}

	public static class TaskJSONParser extends BaseJSONParser<Task> {

		@Override
		protected Task createDTO() {
			return new Task();
		}

		@Override
		protected Task[] createDTOArray(int size) {
			return new Task[size];
		}

		@Override
		protected void setField(
			Task task, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "actions")) {
				if (jsonParserFieldValue != null) {
					task.setActions(
						(Map)TaskSerDes.toMap((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "createDate")) {
				if (jsonParserFieldValue != null) {
					task.setCreateDate(toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "endDate")) {
				if (jsonParserFieldValue != null) {
					task.setEndDate(toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "id")) {
				if (jsonParserFieldValue != null) {
					task.setId(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "index")) {
				if (jsonParserFieldValue != null) {
					task.setIndex(Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "inputParameters")) {
				if (jsonParserFieldValue != null) {
					task.setInputParameters(
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
					task.setOutputParameters(
						Stream.of(
							toStrings((Object[])jsonParserFieldValue)
						).map(
							object -> ParameterSerDes.toDTO((String)object)
						).toArray(
							size -> new Parameter[size]
						));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "requestId")) {
				if (jsonParserFieldValue != null) {
					task.setRequestId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "startDate")) {
				if (jsonParserFieldValue != null) {
					task.setStartDate(toDate((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "type")) {
				if (jsonParserFieldValue != null) {
					task.setType((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(
						jsonParserFieldName, "workflowStatusInfo")) {

				if (jsonParserFieldValue != null) {
					task.setWorkflowStatusInfo(
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