<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
FulfilmentRequestDisplayContext fulfilmentRequestDisplayContext = (FulfilmentRequestDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

FulfilmentRequest fulfilmentRequest = fulfilmentRequestDisplayContext.getFulfilmentRequest();
%>

<portlet:actionURL name="/fulfilment_request/edit_fulfilment_request" var="editFulfilmentRequestActionURL" />

<div class="container pt-4">
	<div class="card">
		<h4 class="card-header"><%= LanguageUtil.get(request, "details") %></h4>

		<div class="card-body">
			<div class="align-items-center row">
				<div class="col-md-6">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="request-id" /></dt>
						<dd><%= fulfilmentRequest.getFulfilmentRequestId() %></dd>
					</dl>
				</div>

				<div class="col-md-6">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="type" /></dt>
						<dd><%= LanguageUtil.get(request, fulfilmentRequest.getType()) %></dd>
					</dl>
				</div>
			</div>

			<div class="align-items-center row">
				<div class="col-md-6">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="workflow" /></dt>
						<dd><%= LanguageUtil.get(request, fulfilmentRequestDisplayContext.getWorkflowDefinition()) %></dd>
					</dl>
				</div>

				<div class="col-md-6">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="status" /></dt>
						<dd><%= LanguageUtil.get(request, FulfilmentConstants.getStatusLabel(fulfilmentRequest.getStatus())) %></dd>
					</dl>
				</div>
			</div>

			<div class="align-items-center row">
				<div class="col-md-6">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="start-date" /></dt>
						<dd><%= fulfilmentRequest.getStartDate() %></dd>
					</dl>
				</div>

				<div class="col-md-6">
					<dl class="commerce-list">
						<dt><liferay-ui:message key="end-date" /></dt>
						<dd><%= fulfilmentRequest.getEndDate() %></dd>
					</dl>
				</div>
			</div>
		</div>
	</div>

	<clay:headless-data-set-display
		apiURL="<%= fulfilmentRequestDisplayContext.getFulfilmentTaskApiUrl() %>"
		formName="fm"
		id="<%= FulfilmentDataSetConstants.FULFILMENT_TASK_DATA_SET_KEY %>"
		itemsPerPage="<%= 10 %>"
		namespace="<%= liferayPortletResponse.getNamespace() %>"
		pageNumber="<%= 1 %>"
		portletURL="<%= fulfilmentRequestDisplayContext.getPortletURL() %>"
		style="stacked"
	/>
</div>