/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.lambda.rest.internal.graphql.mutation.v1_0;

import com.liferay.object.lambda.rest.dto.v1_0.LambdaRequest;
import com.liferay.object.lambda.rest.dto.v1_0.LambdaResponse;
import com.liferay.object.lambda.rest.resource.v1_0.LambdaResponseResource;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

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
public class Mutation {

	public static void setLambdaResponseResourceComponentServiceObjects(
		ComponentServiceObjects<LambdaResponseResource>
			lambdaResponseResourceComponentServiceObjects) {

		_lambdaResponseResourceComponentServiceObjects =
			lambdaResponseResourceComponentServiceObjects;
	}

	@GraphQLField
	public java.util.Collection<LambdaResponse> createPerformIdPage(
			@GraphQLName("id") Long id, @GraphQLName("pageSize") int pageSize,
			@GraphQLName("page") int page,
			@GraphQLName("lambdaRequest") LambdaRequest lambdaRequest)
		throws Exception {

		return _applyComponentServiceObjects(
			_lambdaResponseResourceComponentServiceObjects,
			this::_populateResourceContext,
			lambdaResponseResource -> {
				Page paginationPage = lambdaResponseResource.postPerformIdPage(
					id, Pagination.of(page, pageSize), lambdaRequest);

				return paginationPage.getItems();
			});
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

	private <T, E1 extends Throwable, E2 extends Throwable> void
			_applyVoidComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeConsumer<T, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			unsafeFunction.accept(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(
			LambdaResponseResource lambdaResponseResource)
		throws Exception {

		lambdaResponseResource.setContextAcceptLanguage(_acceptLanguage);
		lambdaResponseResource.setContextCompany(_company);
		lambdaResponseResource.setContextHttpServletRequest(
			_httpServletRequest);
		lambdaResponseResource.setContextHttpServletResponse(
			_httpServletResponse);
		lambdaResponseResource.setContextUriInfo(_uriInfo);
		lambdaResponseResource.setContextUser(_user);
		lambdaResponseResource.setGroupLocalService(_groupLocalService);
		lambdaResponseResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<LambdaResponseResource>
		_lambdaResponseResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}