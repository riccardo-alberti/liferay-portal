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

import com.liferay.headless.fulfilment.admin.client.dto.v1_0.Request;
import com.liferay.headless.fulfilment.admin.client.http.HttpInvoker;
import com.liferay.headless.fulfilment.admin.client.pagination.Page;
import com.liferay.headless.fulfilment.admin.client.pagination.Pagination;
import com.liferay.headless.fulfilment.admin.client.resource.v1_0.RequestResource;
import com.liferay.headless.fulfilment.admin.client.serdes.v1_0.RequestSerDes;
import com.liferay.petra.function.UnsafeTriConsumer;
import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONArray;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.search.test.util.SearchTestRule;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;

import java.lang.reflect.InvocationTargetException;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import org.apache.commons.beanutils.BeanUtils;
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
public abstract class BaseRequestResourceTestCase {

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

		_requestResource.setContextCompany(testCompany);

		RequestResource.Builder builder = RequestResource.builder();

		requestResource = builder.authentication(
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

		Request request1 = randomRequest();

		String json = objectMapper.writeValueAsString(request1);

		Request request2 = RequestSerDes.toDTO(json);

		Assert.assertTrue(equals(request1, request2));
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

		Request request = randomRequest();

		String json1 = objectMapper.writeValueAsString(request);
		String json2 = RequestSerDes.toJSON(request);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Request request = randomRequest();

		request.setExternalReferenceCode(regex);
		request.setReplyTo(regex);
		request.setType(regex);
		request.setWorkflowDefinition(regex);

		String json = RequestSerDes.toJSON(request);

		Assert.assertFalse(json.contains(regex));

		request = RequestSerDes.toDTO(json);

		Assert.assertEquals(regex, request.getExternalReferenceCode());
		Assert.assertEquals(regex, request.getReplyTo());
		Assert.assertEquals(regex, request.getType());
		Assert.assertEquals(regex, request.getWorkflowDefinition());
	}

	@Test
	public void testGetRequestsPage() throws Exception {
		Page<Request> page = requestResource.getRequestsPage(
			null, null, Pagination.of(1, 10), null);

		long totalCount = page.getTotalCount();

		Request request1 = testGetRequestsPage_addRequest(randomRequest());

		Request request2 = testGetRequestsPage_addRequest(randomRequest());

		page = requestResource.getRequestsPage(
			null, null, Pagination.of(1, 10), null);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(request1, (List<Request>)page.getItems());
		assertContains(request2, (List<Request>)page.getItems());
		assertValid(page);

		requestResource.deleteRequest(request1.getId());

		requestResource.deleteRequest(request2.getId());
	}

	@Test
	public void testGetRequestsPageWithFilterDateTimeEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.DATE_TIME);

		if (entityFields.isEmpty()) {
			return;
		}

		Request request1 = randomRequest();

		request1 = testGetRequestsPage_addRequest(request1);

		for (EntityField entityField : entityFields) {
			Page<Request> page = requestResource.getRequestsPage(
				null, getFilterString(entityField, "between", request1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(request1),
				(List<Request>)page.getItems());
		}
	}

	@Test
	public void testGetRequestsPageWithFilterStringEquals() throws Exception {
		List<EntityField> entityFields = getEntityFields(
			EntityField.Type.STRING);

		if (entityFields.isEmpty()) {
			return;
		}

		Request request1 = testGetRequestsPage_addRequest(randomRequest());

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Request request2 = testGetRequestsPage_addRequest(randomRequest());

		for (EntityField entityField : entityFields) {
			Page<Request> page = requestResource.getRequestsPage(
				null, getFilterString(entityField, "eq", request1),
				Pagination.of(1, 2), null);

			assertEquals(
				Collections.singletonList(request1),
				(List<Request>)page.getItems());
		}
	}

	@Test
	public void testGetRequestsPageWithPagination() throws Exception {
		Page<Request> totalPage = requestResource.getRequestsPage(
			null, null, null, null);

		int totalCount = GetterUtil.getInteger(totalPage.getTotalCount());

		Request request1 = testGetRequestsPage_addRequest(randomRequest());

		Request request2 = testGetRequestsPage_addRequest(randomRequest());

		Request request3 = testGetRequestsPage_addRequest(randomRequest());

		Page<Request> page1 = requestResource.getRequestsPage(
			null, null, Pagination.of(1, totalCount + 2), null);

		List<Request> requests1 = (List<Request>)page1.getItems();

		Assert.assertEquals(
			requests1.toString(), totalCount + 2, requests1.size());

		Page<Request> page2 = requestResource.getRequestsPage(
			null, null, Pagination.of(2, totalCount + 2), null);

		Assert.assertEquals(totalCount + 3, page2.getTotalCount());

		List<Request> requests2 = (List<Request>)page2.getItems();

		Assert.assertEquals(requests2.toString(), 1, requests2.size());

		Page<Request> page3 = requestResource.getRequestsPage(
			null, null, Pagination.of(1, totalCount + 3), null);

		assertContains(request1, (List<Request>)page3.getItems());
		assertContains(request2, (List<Request>)page3.getItems());
		assertContains(request3, (List<Request>)page3.getItems());
	}

	@Test
	public void testGetRequestsPageWithSortDateTime() throws Exception {
		testGetRequestsPageWithSort(
			EntityField.Type.DATE_TIME,
			(entityField, request1, request2) -> {
				BeanUtils.setProperty(
					request1, entityField.getName(),
					DateUtils.addMinutes(new Date(), -2));
			});
	}

	@Test
	public void testGetRequestsPageWithSortInteger() throws Exception {
		testGetRequestsPageWithSort(
			EntityField.Type.INTEGER,
			(entityField, request1, request2) -> {
				BeanUtils.setProperty(request1, entityField.getName(), 0);
				BeanUtils.setProperty(request2, entityField.getName(), 1);
			});
	}

	@Test
	public void testGetRequestsPageWithSortString() throws Exception {
		testGetRequestsPageWithSort(
			EntityField.Type.STRING,
			(entityField, request1, request2) -> {
				Class<?> clazz = request1.getClass();

				String entityFieldName = entityField.getName();

				java.lang.reflect.Method method = clazz.getMethod(
					"get" + StringUtil.upperCaseFirstLetter(entityFieldName));

				Class<?> returnType = method.getReturnType();

				if (returnType.isAssignableFrom(Map.class)) {
					BeanUtils.setProperty(
						request1, entityFieldName,
						Collections.singletonMap("Aaa", "Aaa"));
					BeanUtils.setProperty(
						request2, entityFieldName,
						Collections.singletonMap("Bbb", "Bbb"));
				}
				else if (entityFieldName.contains("email")) {
					BeanUtils.setProperty(
						request1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
					BeanUtils.setProperty(
						request2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()) +
									"@liferay.com");
				}
				else {
					BeanUtils.setProperty(
						request1, entityFieldName,
						"aaa" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
					BeanUtils.setProperty(
						request2, entityFieldName,
						"bbb" +
							StringUtil.toLowerCase(
								RandomTestUtil.randomString()));
				}
			});
	}

	protected void testGetRequestsPageWithSort(
			EntityField.Type type,
			UnsafeTriConsumer<EntityField, Request, Request, Exception>
				unsafeTriConsumer)
		throws Exception {

		List<EntityField> entityFields = getEntityFields(type);

		if (entityFields.isEmpty()) {
			return;
		}

		Request request1 = randomRequest();
		Request request2 = randomRequest();

		for (EntityField entityField : entityFields) {
			unsafeTriConsumer.accept(entityField, request1, request2);
		}

		request1 = testGetRequestsPage_addRequest(request1);

		request2 = testGetRequestsPage_addRequest(request2);

		for (EntityField entityField : entityFields) {
			Page<Request> ascPage = requestResource.getRequestsPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":asc");

			assertEquals(
				Arrays.asList(request1, request2),
				(List<Request>)ascPage.getItems());

			Page<Request> descPage = requestResource.getRequestsPage(
				null, null, Pagination.of(1, 2),
				entityField.getName() + ":desc");

			assertEquals(
				Arrays.asList(request2, request1),
				(List<Request>)descPage.getItems());
		}
	}

	protected Request testGetRequestsPage_addRequest(Request request)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetRequestsPage() throws Exception {
		GraphQLField graphQLField = new GraphQLField(
			"requests",
			new HashMap<String, Object>() {
				{
					put("page", 1);
					put("pageSize", 10);
				}
			},
			new GraphQLField("items", getGraphQLFields()),
			new GraphQLField("page"), new GraphQLField("totalCount"));

		JSONObject requestsJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/requests");

		long totalCount = requestsJSONObject.getLong("totalCount");

		Request request1 = testGraphQLRequest_addRequest();
		Request request2 = testGraphQLRequest_addRequest();

		requestsJSONObject = JSONUtil.getValueAsJSONObject(
			invokeGraphQLQuery(graphQLField), "JSONObject/data",
			"JSONObject/requests");

		Assert.assertEquals(
			totalCount + 2, requestsJSONObject.getLong("totalCount"));

		assertContains(
			request1,
			Arrays.asList(
				RequestSerDes.toDTOs(requestsJSONObject.getString("items"))));
		assertContains(
			request2,
			Arrays.asList(
				RequestSerDes.toDTOs(requestsJSONObject.getString("items"))));
	}

	@Test
	public void testPostRequest() throws Exception {
		Request randomRequest = randomRequest();

		Request postRequest = testPostRequest_addRequest(randomRequest);

		assertEquals(randomRequest, postRequest);
		assertValid(postRequest);
	}

	protected Request testPostRequest_addRequest(Request request)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteRequestByExternalReferenceCode() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		Request request = testDeleteRequestByExternalReferenceCode_addRequest();

		assertHttpResponseStatusCode(
			204,
			requestResource.deleteRequestByExternalReferenceCodeHttpResponse(
				request.getExternalReferenceCode()));

		assertHttpResponseStatusCode(
			404,
			requestResource.getRequestByExternalReferenceCodeHttpResponse(
				request.getExternalReferenceCode()));

		assertHttpResponseStatusCode(
			404,
			requestResource.getRequestByExternalReferenceCodeHttpResponse(
				request.getExternalReferenceCode()));
	}

	protected Request testDeleteRequestByExternalReferenceCode_addRequest()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGetRequestByExternalReferenceCode() throws Exception {
		Request postRequest =
			testGetRequestByExternalReferenceCode_addRequest();

		Request getRequest = requestResource.getRequestByExternalReferenceCode(
			postRequest.getExternalReferenceCode());

		assertEquals(postRequest, getRequest);
		assertValid(getRequest);
	}

	protected Request testGetRequestByExternalReferenceCode_addRequest()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetRequestByExternalReferenceCode()
		throws Exception {

		Request request = testGraphQLRequest_addRequest();

		Assert.assertTrue(
			equals(
				request,
				RequestSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"requestByExternalReferenceCode",
								new HashMap<String, Object>() {
									{
										put(
											"externalReferenceCode",
											"\"" +
												request.
													getExternalReferenceCode() +
														"\"");
									}
								},
								getGraphQLFields())),
						"JSONObject/data",
						"Object/requestByExternalReferenceCode"))));
	}

	@Test
	public void testGraphQLGetRequestByExternalReferenceCodeNotFound()
		throws Exception {

		String irrelevantExternalReferenceCode =
			"\"" + RandomTestUtil.randomString() + "\"";

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"requestByExternalReferenceCode",
						new HashMap<String, Object>() {
							{
								put(
									"externalReferenceCode",
									irrelevantExternalReferenceCode);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	@Test
	public void testPatchRequestByExternalReferenceCode() throws Exception {
		Request postRequest =
			testPatchRequestByExternalReferenceCode_addRequest();

		Request randomPatchRequest = randomPatchRequest();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Request patchRequest =
			requestResource.patchRequestByExternalReferenceCode(
				postRequest.getExternalReferenceCode(), randomPatchRequest);

		Request expectedPatchRequest = postRequest.clone();

		_beanUtilsBean.copyProperties(expectedPatchRequest, randomPatchRequest);

		Request getRequest = requestResource.getRequestByExternalReferenceCode(
			patchRequest.getExternalReferenceCode());

		assertEquals(expectedPatchRequest, getRequest);
		assertValid(getRequest);
	}

	protected Request testPatchRequestByExternalReferenceCode_addRequest()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteRequest() throws Exception {
		@SuppressWarnings("PMD.UnusedLocalVariable")
		Request request = testDeleteRequest_addRequest();

		assertHttpResponseStatusCode(
			204, requestResource.deleteRequestHttpResponse(request.getId()));

		assertHttpResponseStatusCode(
			404, requestResource.getRequestHttpResponse(request.getId()));

		assertHttpResponseStatusCode(
			404, requestResource.getRequestHttpResponse(request.getId()));
	}

	protected Request testDeleteRequest_addRequest() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLDeleteRequest() throws Exception {
		Request request = testGraphQLRequest_addRequest();

		Assert.assertTrue(
			JSONUtil.getValueAsBoolean(
				invokeGraphQLMutation(
					new GraphQLField(
						"deleteRequest",
						new HashMap<String, Object>() {
							{
								put("id", request.getId());
							}
						})),
				"JSONObject/data", "Object/deleteRequest"));

		JSONArray errorsJSONArray = JSONUtil.getValueAsJSONArray(
			invokeGraphQLQuery(
				new GraphQLField(
					"request",
					new HashMap<String, Object>() {
						{
							put("id", request.getId());
						}
					},
					new GraphQLField("id"))),
			"JSONArray/errors");

		Assert.assertTrue(errorsJSONArray.length() > 0);
	}

	@Test
	public void testGetRequest() throws Exception {
		Request postRequest = testGetRequest_addRequest();

		Request getRequest = requestResource.getRequest(postRequest.getId());

		assertEquals(postRequest, getRequest);
		assertValid(getRequest);
	}

	protected Request testGetRequest_addRequest() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetRequest() throws Exception {
		Request request = testGraphQLRequest_addRequest();

		Assert.assertTrue(
			equals(
				request,
				RequestSerDes.toDTO(
					JSONUtil.getValueAsString(
						invokeGraphQLQuery(
							new GraphQLField(
								"request",
								new HashMap<String, Object>() {
									{
										put("id", request.getId());
									}
								},
								getGraphQLFields())),
						"JSONObject/data", "Object/request"))));
	}

	@Test
	public void testGraphQLGetRequestNotFound() throws Exception {
		Long irrelevantId = RandomTestUtil.randomLong();

		Assert.assertEquals(
			"Not Found",
			JSONUtil.getValueAsString(
				invokeGraphQLQuery(
					new GraphQLField(
						"request",
						new HashMap<String, Object>() {
							{
								put("id", irrelevantId);
							}
						},
						getGraphQLFields())),
				"JSONArray/errors", "Object/0", "JSONObject/extensions",
				"Object/code"));
	}

	@Test
	public void testPatchRequest() throws Exception {
		Request postRequest = testPatchRequest_addRequest();

		Request randomPatchRequest = randomPatchRequest();

		@SuppressWarnings("PMD.UnusedLocalVariable")
		Request patchRequest = requestResource.patchRequest(
			postRequest.getId(), randomPatchRequest);

		Request expectedPatchRequest = postRequest.clone();

		_beanUtilsBean.copyProperties(expectedPatchRequest, randomPatchRequest);

		Request getRequest = requestResource.getRequest(patchRequest.getId());

		assertEquals(expectedPatchRequest, getRequest);
		assertValid(getRequest);
	}

	protected Request testPatchRequest_addRequest() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Rule
	public SearchTestRule searchTestRule = new SearchTestRule();

	protected Request testGraphQLRequest_addRequest() throws Exception {
		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected void assertContains(Request request, List<Request> requests) {
		boolean contains = false;

		for (Request item : requests) {
			if (equals(request, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(requests + " does not contain " + request, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Request request1, Request request2) {
		Assert.assertTrue(
			request1 + " does not equal " + request2,
			equals(request1, request2));
	}

	protected void assertEquals(
		List<Request> requests1, List<Request> requests2) {

		Assert.assertEquals(requests1.size(), requests2.size());

		for (int i = 0; i < requests1.size(); i++) {
			Request request1 = requests1.get(i);
			Request request2 = requests2.get(i);

			assertEquals(request1, request2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Request> requests1, List<Request> requests2) {

		Assert.assertEquals(requests1.size(), requests2.size());

		for (Request request1 : requests1) {
			boolean contains = false;

			for (Request request2 : requests2) {
				if (equals(request1, request2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				requests2 + " does not contain " + request1, contains);
		}
	}

	protected void assertValid(Request request) throws Exception {
		boolean valid = true;

		if (request.getId() == null) {
			valid = false;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (request.getActions() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (request.getCreateDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("endDate", additionalAssertFieldName)) {
				if (request.getEndDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (request.getExternalReferenceCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("inputParameters", additionalAssertFieldName)) {
				if (request.getInputParameters() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("outputParameters", additionalAssertFieldName)) {
				if (request.getOutputParameters() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("replyTo", additionalAssertFieldName)) {
				if (request.getReplyTo() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("startDate", additionalAssertFieldName)) {
				if (request.getStartDate() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("tasks", additionalAssertFieldName)) {
				if (request.getTasks() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (request.getType() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"workflowDefinition", additionalAssertFieldName)) {

				if (request.getWorkflowDefinition() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals(
					"workflowStatusInfo", additionalAssertFieldName)) {

				if (request.getWorkflowStatusInfo() == null) {
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

	protected void assertValid(Page<Request> page) {
		boolean valid = false;

		java.util.Collection<Request> requests = page.getItems();

		int size = requests.size();

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
					com.liferay.headless.fulfilment.admin.dto.v1_0.Request.
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

	protected boolean equals(Request request1, Request request2) {
		if (request1 == request2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("actions", additionalAssertFieldName)) {
				if (!equals(
						(Map)request1.getActions(),
						(Map)request2.getActions())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("createDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						request1.getCreateDate(), request2.getCreateDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("endDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						request1.getEndDate(), request2.getEndDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"externalReferenceCode", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						request1.getExternalReferenceCode(),
						request2.getExternalReferenceCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("id", additionalAssertFieldName)) {
				if (!Objects.deepEquals(request1.getId(), request2.getId())) {
					return false;
				}

				continue;
			}

			if (Objects.equals("inputParameters", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						request1.getInputParameters(),
						request2.getInputParameters())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("outputParameters", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						request1.getOutputParameters(),
						request2.getOutputParameters())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("replyTo", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						request1.getReplyTo(), request2.getReplyTo())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("startDate", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						request1.getStartDate(), request2.getStartDate())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("tasks", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						request1.getTasks(), request2.getTasks())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("type", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						request1.getType(), request2.getType())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"workflowDefinition", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						request1.getWorkflowDefinition(),
						request2.getWorkflowDefinition())) {

					return false;
				}

				continue;
			}

			if (Objects.equals(
					"workflowStatusInfo", additionalAssertFieldName)) {

				if (!Objects.deepEquals(
						request1.getWorkflowStatusInfo(),
						request2.getWorkflowStatusInfo())) {

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

		if (!(_requestResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_requestResource;

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
		EntityField entityField, String operator, Request request) {

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
						DateUtils.addSeconds(request.getCreateDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(request.getCreateDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(request.getCreateDate()));
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
						DateUtils.addSeconds(request.getEndDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(request.getEndDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(request.getEndDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("externalReferenceCode")) {
			sb.append("'");
			sb.append(String.valueOf(request.getExternalReferenceCode()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("id")) {
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

		if (entityFieldName.equals("replyTo")) {
			sb.append("'");
			sb.append(String.valueOf(request.getReplyTo()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("startDate")) {
			if (operator.equals("between")) {
				sb = new StringBundler();

				sb.append("(");
				sb.append(entityFieldName);
				sb.append(" gt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(request.getStartDate(), -2)));
				sb.append(" and ");
				sb.append(entityFieldName);
				sb.append(" lt ");
				sb.append(
					_dateFormat.format(
						DateUtils.addSeconds(request.getStartDate(), 2)));
				sb.append(")");
			}
			else {
				sb.append(entityFieldName);

				sb.append(" ");
				sb.append(operator);
				sb.append(" ");

				sb.append(_dateFormat.format(request.getStartDate()));
			}

			return sb.toString();
		}

		if (entityFieldName.equals("tasks")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("type")) {
			sb.append("'");
			sb.append(String.valueOf(request.getType()));
			sb.append("'");

			return sb.toString();
		}

		if (entityFieldName.equals("workflowDefinition")) {
			sb.append("'");
			sb.append(String.valueOf(request.getWorkflowDefinition()));
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

	protected Request randomRequest() throws Exception {
		return new Request() {
			{
				createDate = RandomTestUtil.nextDate();
				endDate = RandomTestUtil.nextDate();
				externalReferenceCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				id = RandomTestUtil.randomLong();
				replyTo = StringUtil.toLowerCase(RandomTestUtil.randomString());
				startDate = RandomTestUtil.nextDate();
				type = StringUtil.toLowerCase(RandomTestUtil.randomString());
				workflowDefinition = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
			}
		};
	}

	protected Request randomIrrelevantRequest() throws Exception {
		Request randomIrrelevantRequest = randomRequest();

		return randomIrrelevantRequest;
	}

	protected Request randomPatchRequest() throws Exception {
		return randomRequest();
	}

	protected RequestResource requestResource;
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
		LogFactoryUtil.getLog(BaseRequestResourceTestCase.class);

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
	private com.liferay.headless.fulfilment.admin.resource.v1_0.RequestResource
		_requestResource;

}