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
SelectCommerceAccountGroupsDisplayContext selectCommerceAccountGroupsDisplayContext = (SelectCommerceAccountGroupsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<clay:management-toolbar
	displayContext='<%= (SelectCommerceAccountGroupsManagementToolbarDisplayContext)request.getAttribute("selectCommerceAccountGroupsManagementToolbarDisplayContext") %>'
/>

<aui:form cssClass="container-fluid container-fluid-max-xl" name="fm">
	<liferay-ui:search-container
		id="<%= selectCommerceAccountGroupsDisplayContext.getSearchContainerId() %>"
		searchContainer="<%= selectCommerceAccountGroupsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.account.model.CommerceAccountGroup"
			escapedModel="<%= true %>"
			keyProperty="commerceAccountGroupId"
			modelVar="commerceAccountGroup"
		>

			<%
			row.setData(
				HashMapBuilder.<String, Object>put(
					"id", commerceAccountGroup.getCommerceAccountGroupId()
				).put(
					"name", commerceAccountGroup.getName()
				).build());
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content table-title"
				name="id"
				orderable="<%= true %>"
				property="commerceAccountGroupId"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content table-title"
				name="name"
				orderable="<%= true %>"
				property="name"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= selectCommerceAccountGroupsDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<liferay-util:include page="/field/select_js.jsp" servletContext="<%= application %>">
	<liferay-util:param name="displayStyle" value="<%= selectCommerceAccountGroupsDisplayContext.getDisplayStyle() %>"/>
	<liferay-util:param name="searchContainerId" value="selectSegmentsEntryCommerceAccountGroups"/>
	<liferay-util:param name="selectEventName" value="<%= selectCommerceAccountGroupsDisplayContext.getEventName() %>"/>
</liferay-util:include>