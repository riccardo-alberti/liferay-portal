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

package com.liferay.headless.fulfilment.admin.internal.resource.v1_0;

import com.liferay.fulfilment.exception.NoSuchRequestException;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.FulfilmentRequestService;
import com.liferay.fulfilment.util.FulfilmentParametersUtil;
import com.liferay.headless.fulfilment.admin.dto.v1_0.Parameter;
import com.liferay.headless.fulfilment.admin.dto.v1_0.Request;
import com.liferay.headless.fulfilment.admin.internal.dto.v1_0.converter.RequestDTOConverter;
import com.liferay.headless.fulfilment.admin.internal.odata.entity.v1_0.RequestEntityModel;
import com.liferay.headless.fulfilment.admin.resource.v1_0.RequestResource;
import com.liferay.headless.fulfilment.admin.util.ServiceContextHelper;
import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.vulcan.dto.converter.DTOConverterRegistry;
import com.liferay.portal.vulcan.dto.converter.DefaultDTOConverterContext;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;
import com.liferay.portal.vulcan.util.SearchUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Alberti
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/request.properties",
	scope = ServiceScope.PROTOTYPE, service = RequestResource.class
)
public class RequestResourceImpl extends BaseRequestResourceImpl {

	@Override
	public void deleteRequest(Long id) throws Exception {
		_fulfilmentRequestService.deleteFulfilmentRequest(id);
	}

	@Override
	public void deleteRequestByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (fulfilmentRequest == null) {
			throw new NoSuchRequestException(
				"Unable to find request with external reference code " +
					externalReferenceCode);
		}

		_fulfilmentRequestService.deleteFulfilmentRequest(
			fulfilmentRequest.getFulfilmentRequestId());
	}

	@Override
	public EntityModel getEntityModel(MultivaluedMap multivaluedMap)
		throws Exception {

		return _entityModel;
	}

	@Override
	public Request getRequest(Long id) throws Exception {
		return _toRequest(GetterUtil.getLong(id));
	}

	@Override
	public Request getRequestByExternalReferenceCode(
			String externalReferenceCode)
		throws Exception {

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (fulfilmentRequest == null) {
			throw new NoSuchRequestException(
				"Unable to find request with external reference code " +
					externalReferenceCode);
		}

		return _toRequest(fulfilmentRequest.getFulfilmentRequestId());
	}

	@Override
	public Page<Request> getRequestsPage(
			String search, Filter filter, Pagination pagination, Sort[] sorts)
		throws Exception {

		return SearchUtil.search(
			null, booleanQuery -> booleanQuery.getPreBooleanFilter(), filter,
			FulfilmentRequest.class.getName(), search, pagination,
			queryConfig -> queryConfig.setSelectedFieldNames(
				Field.ENTRY_CLASS_PK),
			new UnsafeConsumer() {

				public void accept(Object object) throws Exception {
					SearchContext searchContext = (SearchContext)object;

					searchContext.setAttribute(
						"status", WorkflowConstants.STATUS_ANY);
					searchContext.setCompanyId(contextCompany.getCompanyId());
				}

			},
			sorts,
			document -> _toRequest(
				GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK))));
	}

	@Override
	public Request patchRequest(Long id, Request request) throws Exception {
		return _toRequest(
			_updateRequest(
				_fulfilmentRequestService.getFulfilmentRequest(id), request));
	}

	@Override
	public Request patchRequestByExternalReferenceCode(
			String externalReferenceCode, Request request)
		throws Exception {

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestService.fetchByExternalReferenceCode(
				contextCompany.getCompanyId(), externalReferenceCode);

		if (fulfilmentRequest == null) {
			throw new NoSuchRequestException(
				"Unable to find request with external reference code " +
					externalReferenceCode);
		}

		return _toRequest(_updateRequest(fulfilmentRequest, request));
	}

	@Override
	public Request postRequest(Request request) throws Exception {
		FulfilmentRequest fulfilmentRequest = _addFulfilmentRequest(request);

		return _toRequest(fulfilmentRequest.getFulfilmentRequestId());
	}

	private FulfilmentRequest _addFulfilmentRequest(Request request)
		throws Exception {

		Map<String, Serializable> parametersMap = new HashMap<>();

		Parameter[] parameters = request.getInputParameters();

		for (Parameter parameter : parameters) {
			parametersMap.put(
				parameter.getKey(), (Serializable)parameter.getValue());
		}

		ServiceContext serviceContext =
			_serviceContextHelper.getServiceContext();

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestService.addFulfilmentRequest(
				request.getExternalReferenceCode(), request.toString(),
				GetterUtil.getString(
					FulfilmentParametersUtil.serializeParameters(
						parametersMap)),
				GetterUtil.getString(request.getReplyTo()),
				GetterUtil.getString(request.getType()), serviceContext);

		return _fulfilmentRequestService.startWorkflowInstance(
			contextUser.getUserId(), fulfilmentRequest, serviceContext);
	}

	private Map<String, Map<String, String>> _getActions(
			FulfilmentRequest fulfilmentRequest)
		throws Exception {

		return HashMapBuilder.<String, Map<String, String>>put(
			"delete",
			addAction(
				"DELETE", fulfilmentRequest.getFulfilmentRequestId(),
				"deleteRequest", _fulfilmentRequestModelResourcePermission)
		).put(
			"get",
			addAction(
				"VIEW", fulfilmentRequest.getFulfilmentRequestId(),
				"getRequest", _fulfilmentRequestModelResourcePermission)
		).put(
			"permissions",
			addAction(
				"PERMISSIONS", fulfilmentRequest.getFulfilmentRequestId(),
				"patchRequest", _fulfilmentRequestModelResourcePermission)
		).put(
			"update",
			addAction(
				"UPDATE", fulfilmentRequest.getFulfilmentRequestId(),
				"patchRequest", _fulfilmentRequestModelResourcePermission)
		).build();
	}

	private Request _toRequest(FulfilmentRequest fulfilmentRequest)
		throws Exception {

		return _toRequest(fulfilmentRequest.getFulfilmentRequestId());
	}

	private Request _toRequest(Long fulfilmentRequestId) throws Exception {
		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestService.getFulfilmentRequest(fulfilmentRequestId);

		return _requestDTOConverter.toDTO(
			new DefaultDTOConverterContext(
				contextAcceptLanguage.isAcceptAllLanguages(),
				_getActions(fulfilmentRequest), _dtoConverterRegistry,
				fulfilmentRequestId, contextAcceptLanguage.getPreferredLocale(),
				contextUriInfo, contextUser));
	}

	private FulfilmentRequest _updateRequest(
			FulfilmentRequest fulfilmentRequest, Request request)
		throws Exception {

		return _fulfilmentRequestService.updateFulfilmentRequest(
			fulfilmentRequest.getFulfilmentRequestId(), request.toString(),
			fulfilmentRequest.getInputParameters(),
			GetterUtil.getString(
				request.getReplyTo(), fulfilmentRequest.getReplyTo()),
			GetterUtil.getString(
				request.getType(), fulfilmentRequest.getType()),
			_serviceContextHelper.getServiceContext());
	}

	private static final EntityModel _entityModel = new RequestEntityModel();

	@Reference
	private DTOConverterRegistry _dtoConverterRegistry;

	@Reference(
		target = "(model.class.name=com.liferay.fulfilment.model.FulfilmentRequest)"
	)
	private ModelResourcePermission<FulfilmentRequest>
		_fulfilmentRequestModelResourcePermission;

	@Reference
	private FulfilmentRequestService _fulfilmentRequestService;

	@Reference
	private RequestDTOConverter _requestDTOConverter;

	@Reference
	private ServiceContextHelper _serviceContextHelper;

}