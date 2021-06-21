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
PriceCommerceEngineTaskDisplayContext priceCommerceEngineTaskDisplayContext = (PriceCommerceEngineTaskDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

CommerceEngineTask commerceEngineTask = priceCommerceEngineTaskDisplayContext.getCommerceEngineTask();

CommerceChannel commerceChannel = priceCommerceEngineTaskDisplayContext.getCommerceChannel();
%>

<portlet:actionURL name="/price_commerce_engine_task/edit_price_commerce_engine_task" var="editPriceCommerceEngineTaskActionURL" />

<aui:form action="<%= editPriceCommerceEngineTaskActionURL %>" enctype="multipart/form-data" method="post" name="fm">
	<aui:input name="<%= Constants.CMD %>" type="hidden" value="<%= Constants.UPDATE %>" />
	<aui:input name="redirect" type="hidden" value="<%= currentURL %>" />
	<aui:input name="commerceChannelGroupId" type="hidden" value="<%= commerceChannel.getGroupId() %>" />
	<aui:input name="commerceEngineTaskKey" type="hidden" value="<%= commerceEngineTask.getKey() %>" />
	<aui:input name="commerceEngineTaskType" type="hidden" value="<%= commerceEngineTask.getType() %>" />

	<commerce-ui:panel>
		<aui:input autoFocus="<%= true %>" label="key" localized="<%= true %>" name="key" type="text" value="<%= commerceEngineTask.getKey() %>">
			<aui:validator name="required" />
		</aui:input>

		<aui:input label="description" name="description" type="textarea" value="<%= commerceEngineTask.getDescription(locale) %>" />
		<aui:input label="condition" name="condition" type="textarea" value="<%= priceCommerceEngineTaskDisplayContext.getCondition() %>" />

		<aui:input checked="<%= (commerceEngineTask == null) ? false : priceCommerceEngineTaskDisplayContext.isActive() %>" inlineLabel="right" labelCssClass="simple-toggle-switch" name="active" type="toggle-switch" />
	</commerce-ui:panel>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />
	</aui:button-row>
</aui:form>