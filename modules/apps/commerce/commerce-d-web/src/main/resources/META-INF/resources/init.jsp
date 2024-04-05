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
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/frontend-data-set" prefix="frontend-data-set" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.account.model.AccountEntry" %><%@
page import="com.liferay.commerce.constants.CommerceOrderPaymentConstants" %><%@
page import="com.liferay.commerce.constants.CommerceSubscriptionEntryConstants" %><%@
page import="com.liferay.commerce.constants.CommerceTaxScreenNavigationConstants" %><%@
page import="com.liferay.commerce.exception.CommerceGeocoderException" %><%@
page import="com.liferay.commerce.exception.CommerceShippingMethodNameException" %><%@
page import="com.liferay.commerce.exception.CommerceSubscriptionEntryNextIterationDateException" %><%@
page import="com.liferay.commerce.exception.CommerceSubscriptionEntrySubscriptionStatusException" %><%@
page import="com.liferay.commerce.exception.NoSuchSubscriptionEntryException" %><%@
page import="com.liferay.commerce.inventory.exception.CommerceInventoryWarehouseActiveException" %><%@
page import="com.liferay.commerce.inventory.exception.CommerceInventoryWarehouseNameException" %><%@
page import="com.liferay.commerce.inventory.exception.DuplicateCommerceInventoryWarehouseExternalReferenceCodeException" %><%@
page import="com.liferay.commerce.inventory.exception.MVCCException" %><%@
page import="com.liferay.commerce.inventory.exception.NoSuchInventoryWarehouseException" %><%@
page import="com.liferay.commerce.inventory.model.CommerceInventoryWarehouse" %><%@
page import="com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem" %><%@
page import="com.liferay.commerce.model.CommerceAddress" %><%@
page import="com.liferay.commerce.model.CommerceOrder" %><%@
page import="com.liferay.commerce.model.CommerceOrderItem" %><%@
page import="com.liferay.commerce.model.CommerceShipment" %><%@
page import="com.liferay.commerce.model.CommerceShippingMethod" %><%@
page import="com.liferay.commerce.model.CommerceSubscriptionEntry" %><%@
page import="com.liferay.commerce.product.constants.CPMeasurementUnitConstants" %><%@
page import="com.liferay.commerce.product.constants.CommerceChannelAccountEntryRelConstants" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCommerceChannelAccountEntryRelException" %><%@
page import="com.liferay.commerce.product.model.CPDefinition" %><%@
page import="com.liferay.commerce.product.model.CPInstance" %><%@
page import="com.liferay.commerce.product.model.CPTaxCategory" %><%@
page import="com.liferay.commerce.product.model.CProduct" %><%@
page import="com.liferay.commerce.product.model.CommerceChannel" %><%@
page import="com.liferay.commerce.product.model.CommerceChannelAccountEntryRel" %><%@
page import="com.liferay.commerce.product.util.CPSubscriptionType" %><%@
page import="com.liferay.commerce.product.util.CPSubscriptionTypeJSPContributor" %><%@
page import="com.liferay.commerce.shipment.content.web.internal.display.context.CommerceShipmentContentDisplayContext" %><%@
page import="com.liferay.commerce.shipping.engine.fixed.exception.CommerceShippingFixedOptionKeyException" %><%@
page import="com.liferay.commerce.shipping.engine.fixed.exception.DuplicateCommerceShippingFixedOptionQualifierException" %><%@
page import="com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOption" %><%@
page import="com.liferay.commerce.shipping.engine.fixed.model.CommerceShippingFixedOptionRel" %><%@
page import="com.liferay.commerce.shipping.engine.fixed.web.internal.constants.CommerceShippingFixedOptionFDSNames" %><%@
page import="com.liferay.commerce.shipping.engine.fixed.web.internal.constants.CommerceShippingFixedOptionScreenNavigationConstants" %><%@
page import="com.liferay.commerce.shipping.engine.fixed.web.internal.display.context.CommerceShippingFixedOptionQualifiersDisplayContext" %><%@
page import="com.liferay.commerce.shipping.engine.fixed.web.internal.display.context.CommerceShippingFixedOptionRelsDisplayContext" %><%@
page import="com.liferay.commerce.shipping.engine.fixed.web.internal.display.context.CommerceShippingFixedOptionsDisplayContext" %><%@
page import="com.liferay.commerce.shipping.web.internal.constants.CommerceShippingFDSNames" %><%@
page import="com.liferay.commerce.shipping.web.internal.constants.CommerceShippingScreenNavigationConstants" %><%@
page import="com.liferay.commerce.shipping.web.internal.display.context.CommerceShippingMethodsDisplayContext" %><%@
page import="com.liferay.commerce.subscription.web.internal.constants.CommerceSubscriptionEntryScreenNavigationConstants" %><%@
page import="com.liferay.commerce.subscription.web.internal.constants.CommerceSubscriptionFDSNames" %><%@
page import="com.liferay.commerce.subscription.web.internal.display.context.CPDefinitionSubscriptionInfoDisplayContext" %><%@
page import="com.liferay.commerce.subscription.web.internal.display.context.CPInstanceSubscriptionInfoDisplayContext" %><%@
page import="com.liferay.commerce.subscription.web.internal.display.context.CommerceSubscriptionContentDisplayContext" %><%@
page import="com.liferay.commerce.subscription.web.internal.display.context.CommerceSubscriptionEntryDisplayContext" %><%@
page import="com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRate" %><%@
page import="com.liferay.commerce.tax.engine.fixed.model.CommerceTaxFixedRateAddressRel" %><%@
page import="com.liferay.commerce.tax.engine.fixed.web.internal.constants.CommerceTaxRateSettingFDSNames" %><%@
page import="com.liferay.commerce.tax.engine.fixed.web.internal.display.context.CommerceTaxFixedRateAddressRelsDisplayContext" %><%@
page import="com.liferay.commerce.tax.engine.fixed.web.internal.display.context.CommerceTaxFixedRatesDisplayContext" %><%@
page import="com.liferay.commerce.tax.exception.CommerceTaxMethodNameException" %><%@
page import="com.liferay.commerce.tax.model.CommerceTaxMethod" %><%@
page import="com.liferay.commerce.tax.web.internal.display.context.CommerceTaxMethodsDisplayContext" %><%@
page import="com.liferay.commerce.term.constants.CommerceTermEntryConstants" %><%@
page import="com.liferay.commerce.term.entry.type.CommerceTermEntryType" %><%@
page import="com.liferay.commerce.term.exception.CommerceTermEntryExpirationDateException" %><%@
page import="com.liferay.commerce.term.exception.CommerceTermEntryNameException" %><%@
page import="com.liferay.commerce.term.exception.CommerceTermEntryPriorityException" %><%@
page import="com.liferay.commerce.term.exception.CommerceTermEntryTypeException" %><%@
page import="com.liferay.commerce.term.exception.DuplicateCommerceTermEntryExternalReferenceCodeException" %><%@
page import="com.liferay.commerce.term.exception.DuplicateCommerceTermEntryRelException" %><%@
page import="com.liferay.commerce.term.exception.NoSuchTermEntryException" %><%@
page import="com.liferay.commerce.term.model.CommerceTermEntry" %><%@
page import="com.liferay.commerce.term.web.internal.display.context.CommerceChannelAccountEntryRelDisplayContext" %><%@
page import="com.liferay.commerce.term.web.internal.display.context.CommerceTermEntryDisplayContext" %><%@
page import="com.liferay.commerce.term.web.internal.display.context.CommerceTermEntryQualifiersDisplayContext" %><%@
page import="com.liferay.commerce.term.web.internal.entry.constants.CommerceTermEntryFDSNames" %><%@
page import="com.liferay.commerce.term.web.internal.entry.constants.CommerceTermEntryScreenNavigationEntryConstants" %><%@
page import="com.liferay.commerce.warehouse.web.internal.constants.CommerceInventoryWarehouseFDSNames" %><%@
page import="com.liferay.commerce.warehouse.web.internal.constants.CommerceInventoryWarehouseScreenNavigationConstants" %><%@
page import="com.liferay.commerce.warehouse.web.internal.display.context.CommerceInventoryWarehouseItemsDisplayContext" %><%@
page import="com.liferay.commerce.warehouse.web.internal.display.context.CommerceInventoryWarehouseQualifiersDisplayContext" %><%@
page import="com.liferay.commerce.warehouse.web.internal.display.context.CommerceInventoryWarehousesDisplayContext" %><%@
page import="com.liferay.commerce.wish.list.exception.CommerceWishListNameException" %><%@
page import="com.liferay.commerce.wish.list.exception.NoSuchWishListException" %><%@
page import="com.liferay.commerce.wish.list.exception.NoSuchWishListItemException" %><%@
page import="com.liferay.commerce.wish.list.model.CommerceWishList" %><%@
page import="com.liferay.commerce.wish.list.model.CommerceWishListItem" %><%@
page import="com.liferay.commerce.wish.list.web.internal.display.context.CommerceWishListDisplayContext" %><%@
page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItem" %><%@
page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.DropdownItemList" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Country" %><%@
page import="com.liferay.portal.kernel.model.Region" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.KeyValuePair" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.liferay.taglib.servlet.PipingServletResponseFactory" %>

<%@ page import="java.math.BigDecimal" %>

<%@ page import="java.util.Calendar" %><%@
page import="java.util.Date" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.List" %><%@
page import="java.util.Map" %><%@
page import="java.util.Objects" %><%@
page import="java.util.StringJoiner" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String languageId = LanguageUtil.getLanguageId(locale);

String redirect = ParamUtil.getString(request, "redirect");
%>