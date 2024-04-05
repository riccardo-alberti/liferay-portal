<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/commerce-ui" prefix="commerce-ui" %><%@
taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/frontend-data-set" prefix="frontend-data-set" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/template" prefix="liferay-template" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.account.model.AccountEntry" %><%@
page import="com.liferay.account.model.AccountGroup" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountGroupAccountItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountGroupAccountItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountGroupItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountGroupItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.account.item.selector.web.internal.display.context.CommerceAccountItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.account.web.internal.constants.CommerceAccountFDSNames" %><%@
page import="com.liferay.commerce.account.web.internal.display.context.AccountAddressQualifiersDisplayContext" %><%@
page import="com.liferay.commerce.account.web.internal.display.context.CommerceAccountDisplayContext" %><%@
page import="com.liferay.commerce.address.content.web.internal.display.context.CommerceAddressDisplayContext" %><%@
page import="com.liferay.commerce.address.content.web.internal.portlet.CommerceAddressContentPortlet" %><%@
page import="com.liferay.commerce.address.web.internal.constants.CommerceAddressFDSNames" %><%@
page import="com.liferay.commerce.address.web.internal.display.context.CommerceChannelAccountEntryRelDisplayContext" %><%@
page import="com.liferay.commerce.address.web.internal.display.context.CountryCommerceChannelDisplayContext" %><%@
page import="com.liferay.commerce.availability.estimate.web.internal.display.context.CommerceAvailabilityEstimateDisplayContext" %><%@
page import="com.liferay.commerce.availability.estimate.web.internal.display.context.CommerceAvailabilityEstimateManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.constants.CommerceAccountWebKeys" %><%@
page import="com.liferay.commerce.constants.CommerceAddressConstants" %><%@
page import="com.liferay.commerce.constants.CommerceWebKeys" %><%@
page import="com.liferay.commerce.context.CommerceContext" %><%@
page import="com.liferay.commerce.exception.CommerceAddressCityException" %><%@
page import="com.liferay.commerce.exception.CommerceAddressCountryException" %><%@
page import="com.liferay.commerce.exception.CommerceAddressStreetException" %><%@
page import="com.liferay.commerce.exception.CommerceAddressZipException" %><%@
page import="com.liferay.commerce.exception.NoSuchAddressException" %><%@
page import="com.liferay.commerce.model.CommerceAddress" %><%@
page import="com.liferay.commerce.model.CommerceAvailabilityEstimate" %><%@
page import="com.liferay.commerce.model.CommerceShippingOptionAccountEntryRel" %><%@
page import="com.liferay.commerce.product.constants.CommerceChannelAccountEntryRelConstants" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCommerceChannelAccountEntryRelException" %><%@
page import="com.liferay.commerce.product.model.CommerceChannel" %><%@
page import="com.liferay.commerce.product.model.CommerceChannelAccountEntryRel" %><%@
page import="com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOption" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Address" %><%@
page import="com.liferay.portal.kernel.model.Country" %><%@
page import="com.liferay.portal.kernel.model.Region" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder" %><%@
page import="com.liferay.portal.kernel.util.ArrayUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %>

<%@ page import="java.util.List" %><%@
page import="java.util.Map" %><%@
page import="java.util.Objects" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String languageId = LanguageUtil.getLanguageId(locale);
%>