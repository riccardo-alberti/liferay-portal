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

package com.liferay.headless.fulfilment.admin.internal.graphql.query.v1_0;

import com.liferay.headless.fulfilment.admin.dto.v1_0.Request;
import com.liferay.headless.fulfilment.admin.dto.v1_0.Task;
import com.liferay.headless.fulfilment.admin.resource.v1_0.RequestResource;
import com.liferay.headless.fulfilment.admin.resource.v1_0.TaskResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLTypeExtension;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Riccardo Alberti
 * @generated
 */
@Generated("")
public class Query {

	public static void setRequestResourceComponentServiceObjects(
		ComponentServiceObjects<RequestResource>
			requestResourceComponentServiceObjects) {

		_requestResourceComponentServiceObjects =
			requestResourceComponentServiceObjects;
	}

	public static void setTaskResourceComponentServiceObjects(
		ComponentServiceObjects<TaskResource>
			taskResourceComponentServiceObjects) {

		_taskResourceComponentServiceObjects =
			taskResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {requests(filter: ___, page: ___, pageSize: ___, search: ___, sorts: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public RequestPage requests(
			@GraphQLName("search") String search,
			@GraphQLName("filter") String filterString,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("sort") String sortsString)
		throws Exception {

		return _applyComponentServiceObjects(
			_requestResourceComponentServiceObjects,
			this::_populateResourceContext,
			requestResource -> new RequestPage(
				requestResource.getRequestsPage(
					search,
					_filterBiFunction.apply(requestResource, filterString),
					Pagination.of(page, pageSize),
					_sortsBiFunction.apply(requestResource, sortsString))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {requestByExternalReferenceCode(externalReferenceCode: ___){actions, createDate, endDate, externalReferenceCode, id, inputParameters, outputParameters, replyTo, startDate, tasks, type, workflowDefinition, workflowStatusInfo}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Request requestByExternalReferenceCode(
			@GraphQLName("externalReferenceCode") String externalReferenceCode)
		throws Exception {

		return _applyComponentServiceObjects(
			_requestResourceComponentServiceObjects,
			this::_populateResourceContext,
			requestResource ->
				requestResource.getRequestByExternalReferenceCode(
					externalReferenceCode));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {request(id: ___){actions, createDate, endDate, externalReferenceCode, id, inputParameters, outputParameters, replyTo, startDate, tasks, type, workflowDefinition, workflowStatusInfo}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Request request(@GraphQLName("id") Long id) throws Exception {
		return _applyComponentServiceObjects(
			_requestResourceComponentServiceObjects,
			this::_populateResourceContext,
			requestResource -> requestResource.getRequest(id));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {requestByExternalReferenceCodeTasks(externalReferenceCode: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TaskPage requestByExternalReferenceCodeTasks(
			@GraphQLName("externalReferenceCode") String externalReferenceCode,
			@GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_taskResourceComponentServiceObjects,
			this::_populateResourceContext,
			taskResource -> new TaskPage(
				taskResource.getRequestByExternalReferenceCodeTasksPage(
					externalReferenceCode, Pagination.of(page, pageSize))));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {requestIdTasks(id: ___, page: ___, pageSize: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public TaskPage requestIdTasks(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page)
		throws Exception {

		return _applyComponentServiceObjects(
			_taskResourceComponentServiceObjects,
			this::_populateResourceContext,
			taskResource -> new TaskPage(
				taskResource.getRequestIdTasksPage(
					id, Pagination.of(page, pageSize))));
	}

	@GraphQLTypeExtension(Request.class)
	public class GetRequestByExternalReferenceCodeTasksPageTypeExtension {

		public GetRequestByExternalReferenceCodeTasksPageTypeExtension(
			Request request) {

			_request = request;
		}

		@GraphQLField
		public TaskPage byExternalReferenceCodeTasks(
				@GraphQLName("pageSize") int pageSize,
				@GraphQLName("page") int page)
			throws Exception {

			return _applyComponentServiceObjects(
				_taskResourceComponentServiceObjects,
				Query.this::_populateResourceContext,
				taskResource -> new TaskPage(
					taskResource.getRequestByExternalReferenceCodeTasksPage(
						_request.getExternalReferenceCode(),
						Pagination.of(page, pageSize))));
		}

		private Request _request;

	}

	@GraphQLName("RequestPage")
	public class RequestPage {

		public RequestPage(Page requestPage) {
			actions = requestPage.getActions();

			items = requestPage.getItems();
			lastPage = requestPage.getLastPage();
			page = requestPage.getPage();
			pageSize = requestPage.getPageSize();
			totalCount = requestPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Request> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("TaskPage")
	public class TaskPage {

		public TaskPage(Page taskPage) {
			actions = taskPage.getActions();

			items = taskPage.getItems();
			lastPage = taskPage.getLastPage();
			page = taskPage.getPage();
			pageSize = taskPage.getPageSize();
			totalCount = taskPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map> actions;

		@GraphQLField
		protected java.util.Collection<Task> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(RequestResource requestResource)
		throws Exception {

		requestResource.setContextAcceptLanguage(_acceptLanguage);
		requestResource.setContextCompany(_company);
		requestResource.setContextHttpServletRequest(_httpServletRequest);
		requestResource.setContextHttpServletResponse(_httpServletResponse);
		requestResource.setContextUriInfo(_uriInfo);
		requestResource.setContextUser(_user);
		requestResource.setGroupLocalService(_groupLocalService);
		requestResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(TaskResource taskResource)
		throws Exception {

		taskResource.setContextAcceptLanguage(_acceptLanguage);
		taskResource.setContextCompany(_company);
		taskResource.setContextHttpServletRequest(_httpServletRequest);
		taskResource.setContextHttpServletResponse(_httpServletResponse);
		taskResource.setContextUriInfo(_uriInfo);
		taskResource.setContextUser(_user);
		taskResource.setGroupLocalService(_groupLocalService);
		taskResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<RequestResource>
		_requestResourceComponentServiceObjects;
	private static ComponentServiceObjects<TaskResource>
		_taskResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}