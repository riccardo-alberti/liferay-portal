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

package com.liferay.headless.fulfilment.admin.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.headless.fulfilment.admin.client.dto.v1_0.Task;
import com.liferay.headless.fulfilment.admin.client.http.HttpInvoker;
import com.liferay.headless.fulfilment.admin.client.pagination.Page;
import com.liferay.headless.fulfilment.admin.client.pagination.Pagination;
import com.liferay.headless.fulfilment.admin.client.resource.v1_0.TaskResource;
import com.liferay.headless.fulfilment.admin.client.serdes.v1_0.TaskSerDes;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.time.DateUtils;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Riccardo Alberti
 * @generated
 */
@Generated("")
public abstract class BaseTaskResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_taskResource.setContextCompany(testCompany);

		TaskResource.Builder builder = TaskResource.builder();

		taskResource = builder.authentication(
			"test@liferay.com", "test"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Task task1 = randomTask();

		String json = objectMapper.writeValueAsString(task1);

		Task task2 = TaskSerDes.toDTO(json);

		Assert.assertTrue(equals(task1, task2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Task task = randomTask();

		String json1 = objectMapper.writeValueAsString(task);
		String json2 = TaskSerDes.toJSON(task);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Task task = randomTask();

		task.setType(regex);

		String json = TaskSerDes.toJSON(task);

		Assert.assertFalse(json.contains(regex));

		task = TaskSerDes.toDTO(json);

		Assert.assertEquals(regex, task.getType());
	}

	@Test
	public void testGetRequestByExternalReferenceCodeTasksPage()
		throws Exception {

		String externalReferenceCode =
			testGetRequestByExternalReferenceCodeTasksPage_getExternalReferenceCode();
		String irrelevantExternalReferenceCode =
			testGetRequestByExternalReferenceCodeTasksPage_getIrrelevantExternalReferenceCode();

		Page<Task> page =
			taskResource.getRequestByExternalReferenceCodeTasksPage(
				externalReferenceCode, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantExternalReferenceCode != null) {
			Task irrelevantTask =
				testGetRequestByExternalReferenceCodeTasksPage_addTask(
					irrelevantExternalReferenceCode, randomIrrelevantTask());

			page = taskResource.getRequestByExternalReferenceCodeTasksPage(
				irrelevantExternalReferenceCode, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantTask), (List<Task>)page.getItems());
			assertValid(page);
		}

		Task task1 = testGetRequestByExternalReferenceCodeTasksPage_addTask(
			externalReferenceCode, randomTask());

		Task task2 = testGetRequestByExternalReferenceCodeTasksPage_addTask(
			externalReferenceCode, randomTask());

		page = taskResource.getRequestByExternalReferenceCodeTasksPage(
			externalReferenceCode, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(task1, task2), (List<Task>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetRequestByExternalReferenceCodeTasksPageWithPagination()
		throws Exception {

		String externalReferenceCode =
			testGetRequestByExternalReferenceCodeTasksPage_getExternalReferenceCode();

		Task task1 = testGetRequestByExternalReferenceCodeTasksPage_addTask(
			externalReferenceCode, randomTask());

		Task task2 = testGetRequestByExternalReferenceCodeTasksPage_addTask(
			externalReferenceCode, randomTask());

		Task task3 = testGetRequestByExternalReferenceCodeTasksPage_addTask(
			externalReferenceCode, randomTask());

		Page<Task> page1 =
			taskResource.getRequestByExternalReferenceCodeTasksPage(
				externalReferenceCode, Pagination.of(1, 2));

		List<Task> tasks1 = (List<Task>)page1.getItems();

		Assert.assertEquals(tasks1.toString(), 2, tasks1.size());

		Page<Task> page2 =
			taskResource.getRequestByExternalReferenceCodeTasksPage(
				externalReferenceCode, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Task> tasks2 = (List<Task>)page2.getItems();

		Assert.assertEquals(tasks2.toString(), 1, tasks2.size());

		Page<Task> page3 =
			taskResource.getRequestByExternalReferenceCodeTasksPage(
				externalReferenceCode, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(task1, task2, task3), (List<Task>)page3.getItems());
	}

	protected Task testGetRequestByExternalReferenceCodeTasksPage_addTask(
			String externalReferenceCode, Task task)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetRequestByExternalReferenceCodeTasksPage_getExternalReferenceCode()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected String
			testGetRequestByExternalReferenceCodeTasksPage_getIrrelevantExternalReferenceCode()
		throws Exception {

		return null;
	}

	@Test
	public void testGetRequestIdTasksPage() throws Exception {
		Long id = testGetRequestIdTasksPage_getId();
		Long irrelevantId = testGetRequestIdTasksPage_getIrrelevantId();

		Page<Task> page = taskResource.getRequestIdTasksPage(
			id, Pagination.of(1, 10));

		Assert.assertEquals(0, page.getTotalCount());

		if (irrelevantId != null) {
			Task irrelevantTask = testGetRequestIdTasksPage_addTask(
				irrelevantId, randomIrrelevantTask());

			page = taskResource.getRequestIdTasksPage(
				irrelevantId, Pagination.of(1, 2));

			Assert.assertEquals(1, page.getTotalCount());

			assertEquals(
				Arrays.asList(irrelevantTask), (List<Task>)page.getItems());
			assertValid(page);
		}

		Task task1 = testGetRequestIdTasksPage_addTask(id, randomTask());

		Task task2 = testGetRequestIdTasksPage_addTask(id, randomTask());

		page = taskResource.getRequestIdTasksPage(id, Pagination.of(1, 10));

		Assert.assertEquals(2, page.getTotalCount());

		assertEqualsIgnoringOrder(
			Arrays.asList(task1, task2), (List<Task>)page.getItems());
		assertValid(page);
	}

	@Test
	public void testGetRequestIdTasksPageWithPagination() throws Exception {
		Long id = testGetRequestIdTasksPage_getId();

		Task task1 = testGetRequestIdTasksPage_addTask(id, randomTask());

		Task task2 = testGetRequestIdTasksPage_addTask(id, randomTask());

		Task task3 = testGetRequestIdTasksPage_addTask(id, randomTask());

		Page<Task> page1 = taskResource.getRequestIdTasksPage(
			id, Pagination.of(1, 2));

		List<Task> tasks1 = (List<Task>)page1.getItems();

		Assert.assertEquals(tasks1.toString(), 2, tasks1.size());

		Page<Task> page2 = taskResource.getRequestIdTasksPage(
			id, Pagination.of(2, 2));

		Assert.assertEquals(3, page2.getTotalCount());

		List<Task> tasks2 = (List<Task>)page2.getItems();

		Assert.assertEquals(tasks2.toString(), 1, tasks2.size());

		Page<Task> page3 = taskResource.getRequestIdTasksPage(
			id, Pagination.of(1, 3));

		assertEqualsIgnoringOrder(
			Arrays.asList(task1, task2, task3), (List<Task>)page3.getItems());
	}

	protected Task testGetRequestIdTasksPage_addTask(Long id, Task task)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetRequestIdTasksPage_getId() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Long testGetRequestIdTasksPage_getIrrelevantId()
		throws Exception {

		return null;
	}

	protected Task testGraphQLTask_addTask() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(Task task, List<Task> tasks) {
		boolean contains = false;

		for (Task item : tasks) {
			if (equals(task, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(tasks + " does not contain " + task, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Task task1, Task task2) {
		Assert.assertTrue(
			task1 + " does not equal " + task2, equals(task1, task2));
	}

	protected void assertEquals(List<Task> tasks1, List<Task> tasks2) {
		Assert.assertEquals(tasks1.size(), tasks2.size());

		for (int i = 0; i < tasks1.size(); i++) {
			Task task1 = tasks1.get(i);
			Task task2 = tasks2.get(i);

			assertEquals(task1, task2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Task> tasks1, List<Task> tasks2) {

		Assert.assertEquals(tasks1.size(), tasks2.size());

		for (Task task1 : tasks1) {
			boolean contains = false;

			for (Task task2 : tasks2) {
				if (equals(task1, task2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(tasks2 + " does not contain " + task1, contains);
		}
	}

	protected void assertValid(Task task) throws Exception {
		boolean valid = true;

		if (task.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (task.getActions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (task.getCreateDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("endDate", additionalAssertFieldName)) {
				if (task.getEndDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("index", additionalAssertFieldName)) {
				if (task.getIndex() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("inputParameters", additionalAssertFieldName)) {
				if (task.getInputParameters() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("outputParameters", additionalAssertFieldName)) {
				if (task.getOutputParameters() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("requestId", additionalAssertFieldName)) {
				if (task.getRequestId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("startDate", additionalAssertFieldName)) {
				if (task.getStartDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (task.getType() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"workflowStatusInfo", additionalAssertFieldName)) {

				if (task.getWorkflowStatusInfo() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<Task> page) {
		boolean valid = false;

		java.util.Collection<Task> tasks = page.getItems();

		int size = tasks.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(
					com.liferay.headless.fulfilment.admin.dto.v1_0.Task.
						class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(
			java.lang.reflect.Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(Task task1, Task task2) {
		if (task1 == task2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (!equals((Map)task1.getActions(), (Map)task2.getActions())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						task1.getCreateDate(), task2.getCreateDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("endDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						task1.getEndDate(), task2.getEndDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(task1.getId(), task2.getId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("index", additionalAssertFieldName)) {
				if (!Objects.deepEquals(task1.getIndex(), task2.getIndex())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("inputParameters", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						task1.getInputParameters(),
						task2.getInputParameters())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("outputParameters", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						task1.getOutputParameters(),
						task2.getOutputParameters())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("requestId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						task1.getRequestId(), task2.getRequestId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("startDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						task1.getStartDate(), task2.getStartDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (!Objects.deepEquals(task1.getType(), task2.getType())) {
					return false;
				}

				continue;
			}

			if (Objects.equals(
					"workflowStatusInfo", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						task1.getWorkflowStatusInfo(),
						task2.getWorkflowStatusInfo())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected java.lang.reflect.Field[] getDeclaredFields(Class clazz)
		throws Exception {

		Stream<java.lang.reflect.Field> stream = Stream.of(
			ReflectionUtil.getDeclaredFields(clazz));

		return stream.filter(
			field -> !field.isSynthetic()
		).toArray(
			java.lang.reflect.Field[]::new
		);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_taskResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_taskResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		java.util.Collection<EntityField> entityFields = getEntityFields();

		Stream<EntityField> stream = entityFields.stream();

		return stream.filter(
			entityField ->
				Objects.equals(entityField.getType(), type) &&
				!ArrayUtil.contains(
					getIgnoredEntityFieldNames(), entityField.getName())
		).collect(
			Collectors.toList()
		);
	}

	protected String getFilterString(
		EntityField entityField, String operator, Task task) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("actions")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("createDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(task.getCreateDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(task.getCreateDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(task.getCreateDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("endDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(task.getEndDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(task.getEndDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(task.getEndDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("index")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("inputParameters")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("outputParameters")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("requestId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("startDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(task.getStartDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(task.getStartDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(task.getStartDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("type")) {
			sb.append("'");
			sb.append(String.valueOf(task.getType()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("workflowStatusInfo")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected Task randomTask() throws Exception {
		return new Task() {
			{
				createDate = RandomTestUtil.nextDate();
				endDate = RandomTestUtil.nextDate();
				id = RandomTestUtil.randomLong();
				index = RandomTestUtil.randomLong();
				requestId = RandomTestUtil.randomLong();
				startDate = RandomTestUtil.nextDate();
				type = StringUtil.toLowerCase(RandomTestUtil.randomString());
			}
		};
	}

	protected Task randomIrrelevantTask() throws Exception {
		Task randomIrrelevantTask = randomTask();

		return randomIrrelevantTask;
	}

	protected Task randomPatchTask() throws Exception {
		return randomTask();
	}

	protected TaskResource taskResource;
	protected Group irrelevantGroup;
	protected Company testCompany;
	protected Group testGroup;

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final com.liferay.portal.kernel.log.Log _log =
		LogFactoryUtil.getLog(BaseTaskResourceTestCase.class);

	private static BeanUtilsBean _beanUtilsBean = new BeanUtilsBean() {

		@Override
		public void copyProperty(Object bean, String name, Object value)
			throws IllegalAccessException, InvocationTargetException {

			if (value != null) {
				super.copyProperty(bean, name, value);
			}
		}

	};
	private static DateFormat _dateFormat;

	@Inject
	private com.liferay.headless.fulfilment.admin.resource.v1_0.TaskResource
		_taskResource;

}