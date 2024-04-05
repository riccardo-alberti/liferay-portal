<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>

<%@ taglib uri="http://liferay.com/tld/adaptive-media-image" prefix="liferay-adaptive-media" %><%@
taglib uri="http://liferay.com/tld/asset" prefix="liferay-asset" %><%@
taglib uri="http://liferay.com/tld/aui" prefix="aui" %><%@
taglib uri="http://liferay.com/tld/clay" prefix="clay" %><%@
taglib uri="http://liferay.com/tld/commerce" prefix="liferay-commerce" %><%@
taglib uri="http://liferay.com/tld/commerce-product" prefix="liferay-commerce-product" %><%@
taglib uri="http://liferay.com/tld/commerce-ui" prefix="commerce-ui" %><%@
taglib uri="http://liferay.com/tld/expando" prefix="liferay-expando" %><%@
taglib uri="http://liferay.com/tld/frontend" prefix="liferay-frontend" %><%@
taglib uri="http://liferay.com/tld/frontend-data-set" prefix="frontend-data-set" %><%@
taglib uri="http://liferay.com/tld/item-selector" prefix="liferay-item-selector" %><%@
taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %><%@
taglib uri="http://liferay.com/tld/react" prefix="react" %><%@
taglib uri="http://liferay.com/tld/security" prefix="liferay-security" %><%@
taglib uri="http://liferay.com/tld/template" prefix="liferay-template" %><%@
taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %><%@
taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %><%@
taglib uri="http://liferay.com/tld/user" prefix="liferay-user" %><%@
taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

taglib uri="http://liferay.com/tld/ddm" prefix="liferay-ddm" %><%@ page import="com.liferay.account.constants.AccountConstants" %><%@
page import="com.liferay.account.exception.NoSuchEntryException" %><%@
page import="com.liferay.account.model.AccountEntry" %><%@
page import="com.liferay.asset.kernel.exception.DuplicateQueryRuleException" %><%@
page import="com.liferay.asset.kernel.exception.NoSuchCategoryException" %><%@
page import="com.liferay.asset.kernel.model.AssetCategory" %><%@
page import="com.liferay.asset.kernel.model.AssetRenderer" %><%@
page import="com.liferay.asset.kernel.model.AssetVocabulary" %><%@
page import="com.liferay.asset.kernel.model.AssetVocabularyConstants" %><%@
page import="com.liferay.commerce.constants.CPDefinitionInventoryConstants" %><%@
page import="com.liferay.commerce.constants.CommerceOrderActionKeys" %><%@
page import="com.liferay.commerce.constants.CommerceOrderPaymentConstants" %><%@
page import="com.liferay.commerce.constants.CommercePaymentEntryConstants" %><%@
page import="com.liferay.commerce.constants.CommercePortletKeys" %><%@
page import="com.liferay.commerce.constants.CommercePriceConstants" %><%@
page import="com.liferay.commerce.constants.CommerceShipmentFDSNames" %><%@
page import="com.liferay.commerce.constants.CommerceWebKeys" %><%@
page import="com.liferay.commerce.context.CommerceContext" %><%@
page import="com.liferay.commerce.currency.model.CommerceCurrency" %><%@
page import="com.liferay.commerce.currency.model.CommerceMoney" %><%@
page import="com.liferay.commerce.discount.CommerceDiscountValue" %><%@
page import="com.liferay.commerce.exception.CommerceOrderAccountLimitException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderBillingAddressException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderImporterTypeException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderItemRequestedDeliveryDateException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderNoteContentException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderPaymentMethodException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderPurchaseOrderNumberException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderRequestedDeliveryDateException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderShippingAddressException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderShippingMethodException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderStatusException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderTypeExpirationDateException" %><%@
page import="com.liferay.commerce.exception.CommerceOrderValidatorException" %><%@
page import="com.liferay.commerce.exception.DuplicateCommerceOrderExternalReferenceCodeException" %><%@
page import="com.liferay.commerce.exception.DuplicateCommerceOrderTypeExternalReferenceCodeException" %><%@
page import="com.liferay.commerce.exception.NoSuchOrderException" %><%@
page import="com.liferay.commerce.exception.NoSuchOrderNoteException" %><%@
page import="com.liferay.commerce.inventory.CPDefinitionInventoryEngine" %><%@
page import="com.liferay.commerce.media.CommerceMediaResolverUtil" %><%@
page import="com.liferay.commerce.model.CPDAvailabilityEstimate" %><%@
page import="com.liferay.commerce.model.CPDefinitionInventory" %><%@
page import="com.liferay.commerce.model.CommerceAddress" %><%@
page import="com.liferay.commerce.model.CommerceAvailabilityEstimate" %><%@
page import="com.liferay.commerce.model.CommerceOrder" %><%@
page import="com.liferay.commerce.model.CommerceOrderItem" %><%@
page import="com.liferay.commerce.model.CommerceOrderNote" %><%@
page import="com.liferay.commerce.model.CommerceOrderType" %><%@
page import="com.liferay.commerce.model.CommerceShipment" %><%@
page import="com.liferay.commerce.notification.model.CommerceNotificationQueueEntry" %><%@
page import="com.liferay.commerce.order.CommerceOrderValidatorResult" %><%@
page import="com.liferay.commerce.order.content.web.internal.constants.CommerceOrderFDSNames" %><%@
page import="com.liferay.commerce.order.content.web.internal.display.context.CommerceOrderContentDisplayContext" %><%@
page import="com.liferay.commerce.order.content.web.internal.importer.type.CSVCommerceOrderImporterTypeImpl" %><%@
page import="com.liferay.commerce.order.content.web.internal.portlet.CommerceOpenOrderContentPortlet" %><%@
page import="com.liferay.commerce.order.content.web.internal.portlet.CommerceOrderContentPortlet" %><%@
page import="com.liferay.commerce.order.importer.type.CommerceOrderImporterType" %><%@
page import="com.liferay.commerce.order.rule.constants.COREntryConstants" %><%@
page import="com.liferay.commerce.order.rule.entry.type.COREntryType" %><%@
page import="com.liferay.commerce.order.rule.entry.type.COREntryTypeJSPContributor" %><%@
page import="com.liferay.commerce.order.rule.exception.COREntryExpirationDateException" %><%@
page import="com.liferay.commerce.order.rule.exception.NoSuchCOREntryException" %><%@
page import="com.liferay.commerce.order.rule.model.COREntry" %><%@
page import="com.liferay.commerce.order.rule.web.internal.constants.COREntryFDSNames" %><%@
page import="com.liferay.commerce.order.rule.web.internal.display.context.COREntryDisplayContext" %><%@
page import="com.liferay.commerce.order.rule.web.internal.display.context.COREntryQualifiersDisplayContext" %><%@
page import="com.liferay.commerce.order.rule.web.internal.entry.constants.COREntryScreenNavigationEntryConstants" %><%@
page import="com.liferay.commerce.order.web.internal.constants.CommerceOrderFDSNames1" %><%@
page import="com.liferay.commerce.order.web.internal.constants.CommerceOrderScreenNavigationConstants" %><%@
page import="com.liferay.commerce.order.web.internal.constants.CommerceOrderTypeScreenNavigationConstants" %><%@
page import="com.liferay.commerce.order.web.internal.constants.CommerceReturnFDSNames" %><%@
page import="com.liferay.commerce.order.web.internal.display.context.CommerceOrderEditDisplayContext" %><%@
page import="com.liferay.commerce.order.web.internal.display.context.CommerceOrderListDisplayContext" %><%@
page import="com.liferay.commerce.order.web.internal.display.context.CommerceOrderNoteEditDisplayContext" %><%@
page import="com.liferay.commerce.order.web.internal.display.context.CommerceOrderTypeDisplayContext" %><%@
page import="com.liferay.commerce.order.web.internal.display.context.CommerceOrderTypeQualifiersDisplayContext" %><%@
page import="com.liferay.commerce.order.web.internal.display.context.CommerceReturnListDisplayContext" %><%@
page import="com.liferay.commerce.order.web.internal.security.permission.resource.CommerceOrderPermission" %><%@
page import="com.liferay.commerce.payment.constants.CommercePaymentScreenNavigationConstants" %><%@
page import="com.liferay.commerce.payment.entry.CommercePaymentEntryRefundType" %><%@
page import="com.liferay.commerce.payment.exception.CommercePaymentEntryAmountException" %><%@
page import="com.liferay.commerce.payment.exception.CommercePaymentEntryPaymentIntegrationTypeException" %><%@
page import="com.liferay.commerce.payment.exception.CommercePaymentEntryPaymentStatusException" %><%@
page import="com.liferay.commerce.payment.exception.CommercePaymentEntryReasonKeyException" %><%@
page import="com.liferay.commerce.payment.exception.CommercePaymentMethodGroupRelNameException" %><%@
page import="com.liferay.commerce.payment.exception.DuplicateCommercePaymentMethodGroupRelQualifierException" %><%@
page import="com.liferay.commerce.payment.exception.NoSuchPaymentEntryException" %><%@
page import="com.liferay.commerce.payment.model.CommercePaymentEntry" %><%@
page import="com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel" %><%@
page import="com.liferay.commerce.payment.web.internal.constants.CommercePaymentMethodGroupRelFDSNames" %><%@
page import="com.liferay.commerce.payment.web.internal.constants.CommercePaymentsFDSNames" %><%@
page import="com.liferay.commerce.payment.web.internal.display.context.CommerceChannelAccountEntryRelDisplayContext" %><%@
page import="com.liferay.commerce.payment.web.internal.display.context.CommercePaymentEntryDisplayContext" %><%@
page import="com.liferay.commerce.payment.web.internal.display.context.CommercePaymentMethodGroupRelQualifiersDisplayContext" %><%@
page import="com.liferay.commerce.payment.web.internal.display.context.CommercePaymentMethodGroupRelsDisplayContext" %><%@
page import="com.liferay.commerce.payment.web.internal.display.context.FunctionCommercePaymentIntegrationConfigurationDisplayContext" %><%@
page import="com.liferay.commerce.price.CommerceOrderPrice" %><%@
page import="com.liferay.commerce.pricing.constants.CommercePricingConstants" %><%@
page import="com.liferay.commerce.pricing.exception.CommerceUndefinedBasePriceListException" %><%@
page import="com.liferay.commerce.product.asset.categories.navigation.web.internal.portlet.CPAssetCategoriesNavigationPortlet" %><%@
page import="com.liferay.commerce.product.asset.categories.web.internal.constants.CommerceProductAssetCategoriesFDSNames" %><%@
page import="com.liferay.commerce.product.asset.categories.web.internal.display.context.CategoryCPAttachmentFileEntriesDisplayContext" %><%@
page import="com.liferay.commerce.product.asset.categories.web.internal.display.context.CategoryCPAttachmentFileEntriesManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.product.asset.categories.web.internal.display.context.CategoryCPDisplayLayoutDisplayContext" %><%@
page import="com.liferay.commerce.product.asset.categories.web.internal.servlet.taglib.ui.constants.CategoryCPAttachmentFormNavigatorConstants" %><%@
page import="com.liferay.commerce.product.catalog.CPCatalogEntry" %><%@
page import="com.liferay.commerce.product.catalog.CPSku" %><%@
page import="com.liferay.commerce.product.constants.CPAttachmentFileEntryConstants" %><%@
page import="com.liferay.commerce.product.constants.CPConstants" %><%@
page import="com.liferay.commerce.product.constants.CPInstanceConstants" %><%@
page import="com.liferay.commerce.product.constants.CPMeasurementUnitConstants" %><%@
page import="com.liferay.commerce.product.constants.CPPortletKeys" %><%@
page import="com.liferay.commerce.product.constants.CPWebKeys" %><%@
page import="com.liferay.commerce.product.content.category.web.internal.display.context.CPCategoryContentDisplayContext" %><%@
page import="com.liferay.commerce.product.content.category.web.internal.portlet.CPCategoryContentPortlet" %><%@
page import="com.liferay.commerce.product.content.constants.CPContentWebKeys" %><%@
page import="com.liferay.commerce.product.content.helper.CPCompareContentHelper" %><%@
page import="com.liferay.commerce.product.content.helper.CPContentHelper" %><%@
page import="com.liferay.commerce.product.content.helper.CPContentSkuOptionsHelper" %><%@
page import="com.liferay.commerce.product.content.render.CPContentRenderer" %><%@
page import="com.liferay.commerce.product.content.render.list.CPContentListRenderer" %><%@
page import="com.liferay.commerce.product.content.render.list.entry.CPContentListEntryRenderer" %><%@
page import="com.liferay.commerce.product.content.search.web.internal.constants.CPSearchResultsConstants" %><%@
page import="com.liferay.commerce.product.content.search.web.internal.display.context.CPOptionsSearchFacetDisplayContext" %><%@
page import="com.liferay.commerce.product.content.search.web.internal.display.context.CPPriceRangeFacetsDisplayContext" %><%@
page import="com.liferay.commerce.product.content.search.web.internal.display.context.CPSearchResultsDisplayContext" %><%@
page import="com.liferay.commerce.product.content.search.web.internal.display.context.CPSpecificationOptionFacetsDisplayContext" %><%@
page import="com.liferay.commerce.product.content.search.web.internal.display.context.CPSpecificationOptionsSearchFacetDisplayContext" %><%@
page import="com.liferay.commerce.product.content.search.web.internal.display.context.CPSpecificationOptionsSearchFacetTermDisplayContext" %><%@
page import="com.liferay.commerce.product.content.search.web.internal.portlet.CPSearchResultsPortlet" %><%@
page import="com.liferay.commerce.product.content.util.CPMedia" %><%@
page import="com.liferay.commerce.product.content.web.internal.constants.CPCompareContentConstants" %><%@
page import="com.liferay.commerce.product.content.web.internal.constants.CPCompareContentMiniConstants" %><%@
page import="com.liferay.commerce.product.content.web.internal.constants.CPContentFDSNames" %><%@
page import="com.liferay.commerce.product.content.web.internal.constants.CPContentPortletConstants" %><%@
page import="com.liferay.commerce.product.content.web.internal.constants.CPPublisherConstants" %><%@
page import="com.liferay.commerce.product.content.web.internal.display.context.CPCompareContentDisplayContext" %><%@
page import="com.liferay.commerce.product.content.web.internal.display.context.CPCompareContentMiniDisplayContext" %><%@
page import="com.liferay.commerce.product.content.web.internal.display.context.CPContentConfigurationDisplayContext" %><%@
page import="com.liferay.commerce.product.content.web.internal.display.context.CPPublisherConfigurationDisplayContext" %><%@
page import="com.liferay.commerce.product.content.web.internal.display.context.CPPublisherDisplayContext" %><%@
page import="com.liferay.commerce.product.content.web.internal.portlet.CPCompareContentMiniPortlet" %><%@
page import="com.liferay.commerce.product.content.web.internal.portlet.CPCompareContentPortlet" %><%@
page import="com.liferay.commerce.product.content.web.internal.portlet.CPContentPortlet" %><%@
page import="com.liferay.commerce.product.content.web.internal.portlet.CPPublisherPortlet" %><%@
page import="com.liferay.commerce.product.data.source.CPDataSource" %><%@
page import="com.liferay.commerce.product.data.source.CPDataSourceResult" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.constants.CommerceProductFDSNames" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.display.context.CPAttachmentFileEntriesDisplayContext" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.display.context.CPDefinitionConfigurationDisplayContext" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.display.context.CPDefinitionDisplayLayoutDisplayContext" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.display.context.CPDefinitionLinkDisplayContext" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.display.context.CPDefinitionOptionRelDisplayContext" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.display.context.CPDefinitionOptionValueRelDisplayContext" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.display.context.CPDefinitionSpecificationOptionValueDisplayContext" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.display.context.CPDefinitionsDisplayContext" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.display.context.CPInstanceDisplayContext" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.display.context.CPInstanceUnitOfMeasureDisplayContext" %><%@
page import="com.liferay.commerce.product.definitions.web.internal.security.permission.resource.CommerceCatalogPermission" %><%@
page import="com.liferay.commerce.product.exception.CPAttachmentFileEntryExpirationDateException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionExpirationDateException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionIgnoreSKUCombinationsException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionLinkExpirationDateException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionMetaDescriptionException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionMetaKeywordsException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionMetaTitleException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionNameDefaultLanguageException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionOptionRelPriceTypeException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionOptionSKUContributorException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionOptionValueRelCPInstanceException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionOptionValueRelKeyException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionOptionValueRelPriceException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionOptionValueRelQuantityException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionProductTypeNameException" %><%@
page import="com.liferay.commerce.product.exception.CPDefinitionSpecificationOptionValueKeyException" %><%@
page import="com.liferay.commerce.product.exception.CPDisplayLayoutEntryException" %><%@
page import="com.liferay.commerce.product.exception.CPDisplayLayoutEntryUuidException" %><%@
page import="com.liferay.commerce.product.exception.CPInstanceJsonException" %><%@
page import="com.liferay.commerce.product.exception.CPInstanceMaxPriceValueException" %><%@
page import="com.liferay.commerce.product.exception.CPInstanceReplacementCPInstanceUuidException" %><%@
page import="com.liferay.commerce.product.exception.CPInstanceSkuException" %><%@
page import="com.liferay.commerce.product.exception.CPInstanceUnitOfMeasureIncrementalOrderQuantityException" %><%@
page import="com.liferay.commerce.product.exception.CPInstanceUnitOfMeasureRateException" %><%@
page import="com.liferay.commerce.product.exception.CPOptionCategoryKeyException" %><%@
page import="com.liferay.commerce.product.exception.CPOptionKeyException" %><%@
page import="com.liferay.commerce.product.exception.CPOptionValueKeyException" %><%@
page import="com.liferay.commerce.product.exception.CPSpecificationOptionKeyException" %><%@
page import="com.liferay.commerce.product.exception.CPTaxCategoryNameException" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCPAttachmentFileEntryException" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCPInstanceException" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCPInstanceUnitOfMeasureKeyException" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCPMeasurementUnitKeyException" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCPOptionExternalReferenceCodeException" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCPTaxCategoryException" %><%@
page import="com.liferay.commerce.product.exception.DuplicateCProductExternalReferenceCodeException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCPAttachmentFileEntryException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCPDefinitionException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCPDefinitionLinkException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCPDefinitionOptionRelException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCPDefinitionOptionValueRelException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCPInstanceException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCPOptionCategoryException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCPOptionException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCPOptionValueException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCProductException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchCatalogException" %><%@
page import="com.liferay.commerce.product.exception.NoSuchSkuContributorCPDefinitionOptionRelException" %><%@
page import="com.liferay.commerce.product.item.selector.web.internal.display.context.CPDefinitionItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.product.item.selector.web.internal.display.context.CPDefinitionItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.product.item.selector.web.internal.display.context.CPOptionItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.product.item.selector.web.internal.display.context.CPOptionItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.product.item.selector.web.internal.display.context.CPSpecificationOptionItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.product.item.selector.web.internal.display.context.CPSpecificationOptionItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.product.item.selector.web.internal.display.context.CommerceChannelItemSelectorViewDisplayContext" %><%@
page import="com.liferay.commerce.product.item.selector.web.internal.display.context.CommerceChannelsItemSelectorViewManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.product.measurement.unit.web.internal.display.context.CPMeasurementUnitsDisplayContext" %><%@
page import="com.liferay.commerce.product.measurement.unit.web.internal.display.context.CPMeasurementUnitsManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.product.model.CPAttachmentFileEntry" %><%@
page import="com.liferay.commerce.product.model.CPDefinition" %><%@
page import="com.liferay.commerce.product.model.CPDefinitionLink" %><%@
page import="com.liferay.commerce.product.model.CPDefinitionOptionRel" %><%@
page import="com.liferay.commerce.product.model.CPDefinitionOptionValueRel" %><%@
page import="com.liferay.commerce.product.model.CPDefinitionSpecificationOptionValue" %><%@
page import="com.liferay.commerce.product.model.CPDisplayLayout" %><%@
page import="com.liferay.commerce.product.model.CPInstance" %><%@
page import="com.liferay.commerce.product.model.CPInstanceUnitOfMeasure" %><%@
page import="com.liferay.commerce.product.model.CPMeasurementUnit" %><%@
page import="com.liferay.commerce.product.model.CPOption" %><%@
page import="com.liferay.commerce.product.model.CPOptionCategory" %><%@
page import="com.liferay.commerce.product.model.CPOptionValue" %><%@
page import="com.liferay.commerce.product.model.CPSpecificationOption" %><%@
page import="com.liferay.commerce.product.model.CPTaxCategory" %><%@
page import="com.liferay.commerce.product.model.CProduct" %><%@
page import="com.liferay.commerce.product.model.CommerceCatalog" %><%@
page import="com.liferay.commerce.product.model.CommerceChannel" %><%@
page import="com.liferay.commerce.product.option.CommerceOptionType" %><%@
page import="com.liferay.commerce.product.options.web.internal.constants.CommerceOptionFDSNames" %><%@
page import="com.liferay.commerce.product.options.web.internal.display.context.CPOptionCategoryDisplayContext" %><%@
page import="com.liferay.commerce.product.options.web.internal.display.context.CPOptionCategoryManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.product.options.web.internal.display.context.CPOptionDisplayContext" %><%@
page import="com.liferay.commerce.product.options.web.internal.display.context.CPSpecificationOptionDisplayContext" %><%@
page import="com.liferay.commerce.product.options.web.internal.display.context.CPSpecificationOptionManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.product.options.web.internal.security.permission.resource.CPOptionCategoryPermission" %><%@
page import="com.liferay.commerce.product.options.web.internal.security.permission.resource.CPSpecificationOptionPermission" %><%@
page import="com.liferay.commerce.product.options.web.internal.servlet.taglib.ui.constants.CPOptionCategoryFormNavigatorConstants" %><%@
page import="com.liferay.commerce.product.options.web.internal.servlet.taglib.ui.constants.CPSpecificationOptionFormNavigatorConstants" %><%@
page import="com.liferay.commerce.product.service.CPDefinitionLocalServiceUtil" %><%@
page import="com.liferay.commerce.product.servlet.taglib.ui.constants.CPDefinitionScreenNavigationConstants" %><%@
page import="com.liferay.commerce.product.servlet.taglib.ui.constants.CPInstanceScreenNavigationConstants" %><%@
page import="com.liferay.commerce.product.subscription.type.web.internal.constants.CPSubscriptionTypeConstants" %><%@
page import="com.liferay.commerce.product.subscription.type.web.internal.display.context.MonthlyCPSubscriptionTypeDisplayContext" %><%@
page import="com.liferay.commerce.product.subscription.type.web.internal.display.context.WeeklyCPSubscriptionTypeDisplayContext" %><%@
page import="com.liferay.commerce.product.subscription.type.web.internal.display.context.YearlyCPSubscriptionTypeDisplayContext" %><%@
page import="com.liferay.commerce.product.tax.category.web.internal.display.context.CPTaxCategoryDisplayContext" %><%@
page import="com.liferay.commerce.product.tax.category.web.internal.display.context.CPTaxCategoryManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.product.type.CPType" %><%@
page import="com.liferay.commerce.product.type.grouped.constants.GroupedCPTypeConstants" %><%@
page import="com.liferay.commerce.product.type.grouped.constants.GroupedCPTypeWebKeys" %><%@
page import="com.liferay.commerce.product.type.grouped.exception.CPDefinitionGroupedEntryQuantityException" %><%@
page import="com.liferay.commerce.product.type.grouped.model.CPDefinitionGroupedEntry" %><%@
page import="com.liferay.commerce.product.type.grouped.util.GroupedCPTypeHelper" %><%@
page import="com.liferay.commerce.product.type.grouped.web.internal.display.context.CPDefinitionGroupedEntriesDisplayContext" %><%@
page import="com.liferay.commerce.product.type.grouped.web.internal.display.context.CPDefinitionGroupedManagementToolbarDisplayContext" %><%@
page import="com.liferay.commerce.product.type.virtual.constants.VirtualCPTypeConstants" %><%@
page import="com.liferay.commerce.product.type.virtual.constants.VirtualCPTypeWebKeys" %><%@
page import="com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingSampleException" %><%@
page import="com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingSampleFileEntryIdException" %><%@
page import="com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingSampleURLException" %><%@
page import="com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingTermsOfUseArticleResourcePKException" %><%@
page import="com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingTermsOfUseContentException" %><%@
page import="com.liferay.commerce.product.type.virtual.exception.CPDefinitionVirtualSettingTermsOfUseException" %><%@
page import="com.liferay.commerce.product.type.virtual.exception.NoSuchCPDefinitionVirtualSettingException" %><%@
page import="com.liferay.commerce.product.type.virtual.model.CPDVirtualSettingFileEntry" %><%@
page import="com.liferay.commerce.product.type.virtual.model.CPDefinitionVirtualSetting" %><%@
page import="com.liferay.commerce.product.type.virtual.order.constants.CommerceVirtualOrderActionKeys" %><%@
page import="com.liferay.commerce.product.type.virtual.order.content.web.internal.display.context.CommerceVirtualOrderItemContentDisplayContext" %><%@
page import="com.liferay.commerce.product.type.virtual.order.content.web.internal.portlet.CommerceVirtualOrderItemContentPortlet" %><%@
page import="com.liferay.commerce.product.type.virtual.order.exception.CommerceVirtualOrderItemException" %><%@
page import="com.liferay.commerce.product.type.virtual.order.exception.CommerceVirtualOrderItemFileEntryIdException" %><%@
page import="com.liferay.commerce.product.type.virtual.order.exception.CommerceVirtualOrderItemUrlException" %><%@
page import="com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItem" %><%@
page import="com.liferay.commerce.product.type.virtual.order.model.CommerceVirtualOrderItemFileEntry" %><%@
page import="com.liferay.commerce.product.type.virtual.util.VirtualCPTypeHelper" %><%@
page import="com.liferay.commerce.product.type.virtual.web.internal.constants.CPDefinitionVirtualSettingFDSNames" %><%@
page import="com.liferay.commerce.product.type.virtual.web.internal.display.context.CPDefinitionVirtualSettingDisplayContext" %><%@
page import="com.liferay.commerce.product.type.virtual.web.internal.display.context.CommerceVirtualOrderItemEditDisplayContext" %><%@
page import="com.liferay.commerce.product.type.virtual.web.internal.servlet.taglib.ui.constants.CPDefinitionVirtualSettingFormNavigatorConstants" %><%@
page import="com.liferay.commerce.stock.activity.CommerceLowStockActivity" %><%@
page import="com.liferay.commerce.term.constants.CommerceTermEntryConstants" %><%@
page import="com.liferay.commerce.term.model.CommerceTermEntry" %><%@
page import="com.liferay.commerce.util.CommerceUtil" %><%@
page import="com.liferay.document.library.kernel.exception.NoSuchFileEntryException" %><%@
page import="com.liferay.friendly.url.exception.FriendlyURLLengthException" %><%@
page import="com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem" %><%@
page import="com.liferay.info.collection.provider.RelatedInfoItemCollectionProvider" %><%@
page import="com.liferay.journal.model.JournalArticle" %><%@
page import="com.liferay.journal.model.JournalArticleDisplay" %><%@
page import="com.liferay.petra.string.StringBundler" %><%@
page import="com.liferay.petra.string.StringPool" %><%@
page import="com.liferay.portal.kernel.bean.BeanParamUtil" %><%@
page import="com.liferay.portal.kernel.bean.BeanPropertiesUtil" %><%@
page import="com.liferay.portal.kernel.dao.search.ResultRow" %><%@
page import="com.liferay.portal.kernel.dao.search.SearchContainer" %><%@
page import="com.liferay.portal.kernel.language.LanguageUtil" %><%@
page import="com.liferay.portal.kernel.model.Layout" %><%@
page import="com.liferay.portal.kernel.model.Portlet" %><%@
page import="com.liferay.portal.kernel.model.User" %><%@
page import="com.liferay.portal.kernel.portlet.LiferayWindowState" %><%@
page import="com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder" %><%@
page import="com.liferay.portal.kernel.repository.model.FileEntry" %><%@
page import="com.liferay.portal.kernel.search.facet.Facet" %><%@
page import="com.liferay.portal.kernel.search.facet.collector.FacetCollector" %><%@
page import="com.liferay.portal.kernel.search.facet.collector.TermCollector" %><%@
page import="com.liferay.portal.kernel.security.permission.ActionKeys" %><%@
page import="com.liferay.portal.kernel.service.PortletLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalServiceUtil" %><%@
page import="com.liferay.portal.kernel.service.permission.PortletPermissionUtil" %><%@
page import="com.liferay.portal.kernel.servlet.SessionErrors" %><%@
page import="com.liferay.portal.kernel.servlet.SessionMessages" %><%@
page import="com.liferay.portal.kernel.util.BigDecimalUtil" %><%@
page import="com.liferay.portal.kernel.util.CalendarFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.Constants" %><%@
page import="com.liferay.portal.kernel.util.FastDateFormatFactoryUtil" %><%@
page import="com.liferay.portal.kernel.util.GetterUtil" %><%@
page import="com.liferay.portal.kernel.util.HashMapBuilder" %><%@
page import="com.liferay.portal.kernel.util.HtmlUtil" %><%@
page import="com.liferay.portal.kernel.util.HttpComponentsUtil" %><%@
page import="com.liferay.portal.kernel.util.KeyValuePair" %><%@
page import="com.liferay.portal.kernel.util.LocaleUtil" %><%@
page import="com.liferay.portal.kernel.util.ParamUtil" %><%@
page import="com.liferay.portal.kernel.util.PortalUtil" %><%@
page import="com.liferay.portal.kernel.util.StringUtil" %><%@
page import="com.liferay.portal.kernel.util.Time" %><%@
page import="com.liferay.portal.kernel.util.Validator" %><%@
page import="com.liferay.portal.kernel.util.WebKeys" %><%@
page import="com.liferay.portal.kernel.workflow.WorkflowConstants" %><%@
page import="com.liferay.taglib.servlet.PipingServletResponseFactory" %><%@
page import="com.liferay.taglib.util.CustomAttributesUtil" %>

<%@ page import="java.math.BigDecimal" %>

<%@ page import="java.text.Format" %><%@
page import="java.text.NumberFormat" %>

<%@ page import="java.util.ArrayList" %><%@
page import="java.util.Arrays" %><%@
page import="java.util.Calendar" %><%@
page import="java.util.Collections" %><%@
page import="java.util.Date" %><%@
page import="java.util.HashMap" %><%@
page import="java.util.HashSet" %><%@
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
String redirect = ParamUtil.getString(request, "redirect");

String backURL = ParamUtil.getString(request, "backURL", redirect);

String languageId = LanguageUtil.getLanguageId(locale);

CommerceOrderContentDisplayContext commerceOrderContentDisplayContext = (CommerceOrderContentDisplayContext)request.getAttribute(WebKeys.PORTLET_DISPLAY_CONTEXT);

SearchContainer<CommerceOrder> commerceOrderSearchContainer = commerceOrderContentDisplayContext.getSearchContainer();

String lifecycle = (String)request.getAttribute(liferayPortletRequest.LIFECYCLE_PHASE);

String catalogURL = String.valueOf(PortalUtil.getControlPanelPortletURL(request, CPPortletKeys.CP_DEFINITIONS, lifecycle));
%>