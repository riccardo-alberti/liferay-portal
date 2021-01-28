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
SelectCommerceAccountsDisplayContext selectCommerceAccountsDisplayContext = (SelectCommerceAccountsDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);
%>

<clay:management-toolbar
	displayContext='<%= (SelectCommerceAccountsManagementToolbarDisplayContext)request.getAttribute("selectCommerceAccountsManagementToolbarDisplayContext") %>'
/>

<aui:form cssClass="container-fluid container-fluid-max-xl" name="fm">
	<liferay-ui:search-container
		id="<%= selectCommerceAccountsDisplayContext.getSearchContainerId() %>"
		searchContainer="<%= selectCommerceAccountsDisplayContext.getSearchContainer() %>"
	>
		<liferay-ui:search-container-row
			className="com.liferay.commerce.account.model.CommerceAccount"
			escapedModel="<%= true %>"
			keyProperty="commerceAccountId"
			modelVar="commerceAccount"
		>

			<%
			row.setData(
				HashMapBuilder.<String, Object>put(
					"id", commerceAccount.getCommerceAccountId()
				).put(
					"name", commerceAccount.getName()
				).build());
			%>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content table-title"
				name="id"
				orderable="<%= true %>"
				property="commerceAccountId"
			/>

			<liferay-ui:search-container-column-text
				cssClass="table-cell-content table-title"
				name="name"
				orderable="<%= true %>"
				property="name"
			/>
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator
			displayStyle="<%= selectCommerceAccountsDisplayContext.getDisplayStyle() %>"
			markupView="lexicon"
		/>
	</liferay-ui:search-container>
</aui:form>

<liferay-util:include page="/field/select_js.jsp" servletContext="<%= application %>">
	<liferay-util:param name="displayStyle" value="<%= selectCommerceAccountsDisplayContext.getDisplayStyle() %>"/>
	<liferay-util:param name="searchContainerId" value="selectSegmentsEntryCommerceAccounts"/>
	<liferay-util:param name="selectEventName" value="<%= selectCommerceAccountsDisplayContext.getEventName() %>"/>
</liferay-util:include>