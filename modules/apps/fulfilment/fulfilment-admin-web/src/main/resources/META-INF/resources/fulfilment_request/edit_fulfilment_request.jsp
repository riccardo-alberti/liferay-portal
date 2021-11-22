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

portletDisplay.setShowBackIcon(true);

if (Validator.isNull(redirect)) {
	portletDisplay.setURLBack(String.valueOf(renderResponse.createRenderURL()));
}
else {
	portletDisplay.setURLBack(redirect);
}
%>

<liferay-frontend:screen-navigation
	containerWrapperCssClass="container"
	key="<%= FulfilmentRequestScreenNavigationEntryConstants.SCREEN_NAVIGATION_KEY_FULFILMENT_REQUEST_GENERAL %>"
	modelBean="<%= fulfilmentRequest %>"
	portletURL="<%= currentURLObj %>"
/>