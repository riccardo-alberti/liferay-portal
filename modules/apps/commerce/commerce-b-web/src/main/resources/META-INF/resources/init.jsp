<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/adaptive-media-image" prefix="liferay-adaptive-media" %><%@
taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/commerce" prefix="liferay-commerce" %><%@
taglib uri="http://liferay.com/tld/commerce-cart" prefix="liferay-commerce-cart" %><%@
taglib uri="http://liferay.com/tld/commerce-ui" prefix="commerce-ui" %><%@
taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/frontend-data-set" prefix="frontend-data-set" %><%@
taglib uri="http://liferay.com/tld/item-selector" prefix="liferay-item-selector" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/template" prefix="liferay-template" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%@ page import="com.liferay.account.constants.AccountActionKeys" %><%@
page import="com.liferay.account.constants.AccountConstants" %><%@
page import="com.liferay.account.exception.AccountEntryStatusException" %><%@
page import="com.liferay.account.exception.AccountEntryTypeException" %><%@
page import="com.liferay.account.model.AccountEntry" %><%@
page import="com.liferay.commerce.cart.content.web.internal.display.context.CommerceCartContentDisplayContext" %><%@
page import="com.liferay.commerce.cart.content.web.internal.display.context.CommerceCartContentMiniDisplayContext" %><%@
page import="com.liferay.commerce.cart.content.web.internal.display.context.CommerceCartContentTotalDisplayContext" %><%@
page import="com.liferay.commerce.cart.content.web.internal.portlet.CommerceCartContentMiniPortlet" %><%@
page import="com.liferay.commerce.cart.content.web.internal.portlet.CommerceCartContentPortlet" %><%@
page import="com.liferay.commerce.cart.content.web.internal.portlet.CommerceCartContentTotalPortlet" %><%@
page import="com.liferay.commerce.catalog.web.internal.constants.CommerceCatalogFDSNames" %><%@
page import="com.liferay.commerce.catalog.web.internal.constants.CommerceCatalogScreenNavigationConstants" %><%@
page import="com.liferay.commerce.catalog.web.internal.display.context.CommerceCatalogDisplayContext" %><%@
page import="com.liferay.commerce.channel.web.internal.constants.CommerceChannelFDSNames" %><%@
page import="com.liferay.commerce.channel.web.internal.constants.CommerceChannelScreenNavigationConstants" %><%@
page import="com.liferay.commerce.channel.web.internal.display.context.CommerceChannelAccountEntryQualifiersDisplayContext" %><%@
page import="com.liferay.commerce.channel.web.internal.display.context.CommerceChannelCountryDisplayContext" %><%@
page import="com.liferay.commerce.channel.web.internal.display.context.CommerceChannelDisplayContext" %><%@
page import="com.liferay.commerce.channel.web.internal.display.context.SiteCommerceChannelTypeDisplayContext" %><%@
page import="com.liferay.commerce.checkout.web.internal.display.context.BaseAddressCheckoutStepDisplayContext" %><%@
page import="com.liferay.commerce.checkout.web.internal.display.context.CheckoutDisplayContext" %><%@
page import="com.liferay.commerce.checkout.web.internal.display.context.OrderConfirmationCheckoutStepDisplayContext" %><%@
page import="com.liferay.commerce.checkout.web.internal.display.context.OrderSummaryCheckoutStepDisplayContext" %><%@
page import="com.liferay.commerce.checkout.web.internal.display.context.PaymentMethodCheckoutStepDisplayContext" %><%@
page import="com.liferay.commerce.checkout.web.internal.display.context.PaymentProcessCheckoutStepDisplayContext" %><%@
page import="com.liferay.commerce.checkout.web.internal.display.context.ShippingMethodCheckoutStepDisplayContext" %><%@
page import="com.liferay.commerce.checkout.web.internal.display.context.TermCommerceCheckoutStepDisplayContext" %><%@
page import="com.liferay.commerce.client.extension.web.internal.util.CommerceClientExtensionWebKeys" %><%@
page import="com.liferay.commerce.constants.CommerceCheckoutWebKeys" %><%@
page import="com.liferay.commerce.constants.CommerceDefinitionTermConstants" %><%@
page import="com.liferay.commerce.constants.CommerceOrderActionKeys" %><%@
page import="com.liferay.commerce.constants.CommerceOrderConstants" %><%@
page import="com.liferay.commerce.constants.CommerceOrderPaymentConstants" %><%@
page import="com.liferay.commerce.constants.CommercePriceConstants" %><%@
page import="com.liferay.commerce.constants.CommerceWebKeys" %><%@
page import="com.liferay.commerce.context.CommerceContext" %><%@
page import="com.liferay.commerce.currency.configuration.CommerceCurrencyConfiguration" %><%@
page import="com.liferay.commerce.currency.exception.CommerceCurrencyCodeException" %><%@
page import="com.liferay.commerce.currency.exception.CommerceCurrencyFractionDigitsException" %><%@
page import="com.liferay.commerce.currency.exception.CommerceCurrencyNameException" %><%@
page import="com.liferay.commerce.currency.model.CommerceCurrency" %><%@
page import="com.liferay.commerce.currency.model.CommerceMoney" %><%@
page import="com.liferay.commerce.currency.web.internal.constants.CommerceCurrencyFDSNames" %><%@
page import="com.liferay.commerce.currency.web.internal.constants.CommerceCurrencyScreenNavigationConstants" %><%@
page import="com.liferay.commerce.currency.web.internal.display.context.CommerceChannelAccountEntryRelDisplayContext" %><%@
page import="com.liferay.commerce.currency.web.internal.display.context.CommerceCurrenciesDisplayContext" %><%@
page import="com.liferay.commerce.currency.web.internal.display.context.CommerceCurrenciesManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.discount.CommerceDiscountValue" %><%@
page import="com.liferay.commerce.discount.exception.CommerceDiscountCouponCodeException" %><%@
page import="com.liferay.commerce.discount.exception.CommerceDiscountLimitationTimesException" %><%@
page import="com.liferay.commerce.discount.exception.CommerceDiscountValidatorException" %><%@
page import="com.liferay.commerce.discount.exception.NoSuchDiscountException" %><%@
page import="com.liferay.commerce.exception.CommerceAddressCityException" %><%@
page import="com.liferay.commerce.exception.CommerceAddressCountryException" %><%@
page import="com.liferay.commerce.exception.CommerceAddressNameException" %><%@
page import="com.liferay.commerce.exception.CommerceAddressStreetException" %><%@
page import="com.liferay.commerce.exception.CommerceAddressZipException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderBillingAddressException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderDefaultBillingAddressException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderGuestCheckoutException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderNoteContentException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderPaymentMethodException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderShippingAddressException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderShippingAndBillingException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderShippingMethodException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderValidatorException" %><%@
page import="com.liferay.commerce.health.status.CommerceHealthStatus" %><%@
page import="com.liferay.commerce.health.status.web.internal.display.context.CommerceHealthStatusDisplayContext" %><%@
page import="com.liferay.commerce.inventory.exception.DuplicateCommerceInventoryWarehouseItemException" %><%@
page import="com.liferay.commerce.inventory.exception.MVCCException" %><%@
page import="com.liferay.commerce.inventory.method.CommerceInventoryMethod" %><%@
page import="com.liferay.commerce.inventory.model.CommerceInventoryReplenishmentItem" %><%@
page import="com.liferay.commerce.inventory.model.CommerceInventoryWarehouse" %><%@
page import="com.liferay.commerce.inventory.model.CommerceInventoryWarehouseItem" %><%@
page import="com.liferay.commerce.inventory.web.internal.constants.CommerceInventoryFDSNames" %><%@
page import="com.liferay.commerce.inventory.web.internal.constants.CommerceInventoryScreenNavigationConstants" %><%@
page import="com.liferay.commerce.inventory.web.internal.display.context.CommerceInventoryDisplayContext" %><%@
page import="com.liferay.commerce.item.selector.web.internal.display.context.CommercePriceListItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.item.selector.web.internal.display.context.CommercePriceListItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.item.selector.web.internal.display.context.SimpleSiteItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.item.selector.web.internal.display.context.SimpleSiteItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.model.CommerceAddress" %><%@
page import="com.liferay.commerce.model.CommerceOrder" %><%@
page import="com.liferay.commerce.model.CommerceOrderItem" %><%@
page import="com.liferay.commerce.model.CommerceOrderNote" %><%@
page import="com.liferay.commerce.model.CommerceOrderPayment" %><%@
page import="com.liferay.commerce.model.CommerceShippingMethod" %><%@
page import="com.liferay.commerce.model.CommerceShippingOption" %><%@
page import="com.liferay.commerce.notification.exception.CommerceNotificationTemplateFromException" %><%@
page import="com.liferay.commerce.notification.exception.CommerceNotificationTemplateNameException" %><%@
page import="com.liferay.commerce.notification.exception.CommerceNotificationTemplateTypeException" %><%@
page import="com.liferay.commerce.notification.model.CommerceNotificationTemplate" %><%@
page import="com.liferay.commerce.notification.type.CommerceNotificationType" %><%@
page import="com.liferay.commerce.notification.web.internal.constants.CommerceNotificationFDSNames" %><%@
page import="com.liferay.commerce.notification.web.internal.display.context.CommerceNotificationQueueEntriesDisplayContext" %><%@
page import="com.liferay.commerce.notification.web.internal.display.context.CommerceNotificationTemplatesDisplayContext" %><%@
page import="com.liferay.commerce.order.CommerceOrderValidatorResult" %><%@
page import="com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel" %><%@
page import="com.liferay.commerce.price.CommerceOrderPrice" %><%@
page import="com.liferay.commerce.price.CommerceProductPrice" %><%@
page import="com.liferay.commerce.price.list.constants.CommercePriceListConstants" %><%@
page import="com.liferay.commerce.price.list.exception.NoSuchPriceListException" %><%@
page import="com.liferay.commerce.price.list.model.CommercePriceList" %><%@
page import="com.liferay.commerce.pricing.constants.CommercePricingConstants" %><%@
page import="com.liferay.commerce.product.channel.CommerceChannelType" %><%@
page import="com.liferay.commerce.product.constants.CommerceChannelAccountEntryRelConstants" %><%@
page import="com.liferay.commerce.product.constants.CommerceChannelConstants" %><%@
page import="com.liferay.commerce.product.content.constants.CPContentWebKeys" %><%@
page import="com.liferay.commerce.product.content.helper.CPContentHelper" %><%@
page import="com.liferay.commerce.product.exception.CPInstanceUnitOfMeasureKeyException" %><%@
page import="com.liferay.commerce.product.exception.CommerceCatalogProductsException" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCommerceCatalogExternalReferenceCodeException" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCommerceChannelAccountEntryIdException" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCommerceChannelAccountEntryRelException" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCommerceChannelExternalReferenceCodeException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCPInstanceUnitOfMeasureException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCatalogException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchChannelException" %><%@
page import="com.liferay.commerce.product.model.CPDefinition" %><%@
page import="com.liferay.commerce.product.model.CPInstance" %><%@
page import="com.liferay.commerce.product.model.CPTaxCategory" %><%@
page import="com.liferay.commerce.product.model.CommerceCatalog" %><%@
page import="com.liferay.commerce.product.model.CommerceChannel" %><%@
page import="com.liferay.commerce.product.model.CommerceChannelAccountEntryRel" %><%@
page import="com.liferay.commerce.term.model.CommerceTermEntry" %><%@
page import="com.liferay.commerce.util.CommerceCheckoutStep" %><%@
page import="com.liferay.document.library.kernel.exception.FileExtensionException" %><%@
page import="com.liferay.document.library.kernel.exception.InvalidFileException" %><%@
page import="com.liferay.document.library.kernel.exception.NoSuchFileEntryException" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.petra.string.StringUtil" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Country" %><%@
page import="com.liferay.portal.kernel.model.Group" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.model.WorkflowDefinitionLink" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder" %><%@
page import="com.liferay.portal.kernel.repository.model.FileEntry" %><%@
page import="com.liferay.portal.kernel.sanitizer.SanitizerUtil" %><%@
page import="com.liferay.portal.kernel.security.auth.PrincipalException" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.util.BigDecimalUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.KeyValuePair" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.PropsUtil" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowDefinition" %>

<%@ page import="java.math.BigDecimal" %><%@
page import="java.math.RoundingMode" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Date" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.Iterator" %><%@
page import="java.util.List" %><%@
page import="java.util.Locale" %><%@
page import="java.util.Map" %><%@
page import="java.util.Objects" %><%@
page import="java.util.Set" %><%@
page import="java.util.StringJoiner" %>

<%@ page import="javax.portlet.PortletURL" %>

<liferay-frontend:defineObjects />

<liferay-theme:defineObjects />

<portlet:defineObjects />

<%
String languageId = LanguageUtil.getLanguageId(locale);
%>