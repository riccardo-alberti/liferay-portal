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
CommerceQualificationRelDisplayContext commerceQualificationRelDisplayContext = (CommerceQualificationRelDisplayContext)request.getAttribute("view.jsp-commerceQualificationRelDisplayContext");

SourceCommerceQualificationRelEntity sourceCommerceQualificationRelEntity = commerceQualificationRelDisplayContext.getSourceCommerceQualificationRelEntity();
%>

<portlet:actionURL name="/commerce_qualification_rel/edit_commerce_qualification_rel" var="editCommerceQualificationRelActionURL" />

<aui:form action="<%= editCommerceQualificationRelActionURL %>" cssClass="pt-4" method="post" name="fm">

	<%
	for (String targetClassName : sourceCommerceQualificationRelEntity.getAvailableTargetClassNames()) {
		TargetCommerceQualificationRelEntity targetCommerceQualificationRelEntity = commerceQualificationRelDisplayContext.getTargetCommerceQualificationRelEntity(targetClassName);
	%>

		<div class="row">
			<div class="col-12">
				<commerce-ui:panel
					bodyClasses="flex-fill"
					collapsed="<%= false %>"
					collapsible="<%= false %>"
					title='<%= LanguageUtil.get(request, targetCommerceQualificationRelEntity.getName() + "-eligibility") %>'
				>
				</commerce-ui:panel>
			</div>
		</div>

	<%
	}
	%>

</aui:form>