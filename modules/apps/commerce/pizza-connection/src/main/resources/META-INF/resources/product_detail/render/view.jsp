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
CommerceContext commerceContext = (CommerceContext)request.getAttribute(CommerceWebKeys.COMMERCE_CONTEXT);

CommerceAccount commerceAccount = commerceContext.getCommerceAccount();
CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

CPContentHelper cpContentHelper = (CPContentHelper)request.getAttribute(CPContentWebKeys.CP_CONTENT_HELPER);

CPCatalogEntry cpCatalogEntry = cpContentHelper.getCPCatalogEntry(request);

CPSku cpSku = cpContentHelper.getDefaultCPSku(cpCatalogEntry);

long cpDefinitionId = cpCatalogEntry.getCPDefinitionId();
%>

<div class="mb-5 product-detail" id="<portlet:namespace /><%= cpDefinitionId %>ProductContent">
	<div class="row">
		<div class="col-12 col-md-6">
			<commerce-ui:gallery
				CPDefinitionId="<%= cpDefinitionId %>"
				namespace="<%= liferayPortletResponse.getNamespace() %>"
			/>
		</div>

		<div class="col-12 col-md-6">
			<header>
				<div>
					<liferay-ratings:ratings className="<%= CPDefinition.class.getName() %>" classPK="<%= cpDefinitionId %>" />
				</div>


				<h2 style="display: inline-block;" class="product-header-title"><%= HtmlUtil.escape(cpCatalogEntry.getName()) %></h2>
			</header>

			<p class="mt-3 product-description"><%= HtmlUtil.escape(cpCatalogEntry.getShortDescription()) %></p>

			<div class="product-detail-options">
				<form data-senna-off="true" name="fm">
					<%= cpContentHelper.renderOptions(renderRequest, renderResponse) %>
				</form>

				<liferay-portlet:actionURL name="/cp_content_web/check_cp_instance" portletName="com_liferay_commerce_product_content_web_internal_portlet_CPContentPortlet" var="checkCPInstanceURL">
					<portlet:param name="cpDefinitionId" value="<%= String.valueOf(cpDefinitionId) %>" />
				</liferay-portlet:actionURL>

				<liferay-frontend:component
					context='<%=
						HashMapBuilder.<String, Object>put(
							"actionURL", checkCPInstanceURL
						).put(
							"cpDefinitionId", cpDefinitionId
						).put(
							"namespace", liferayPortletResponse.getNamespace()
						).build()
					%>'
					module="product_detail/render/js/AAA"
				/>
			</div>

			<div class="mt-3 price-container row">
				<div class="col col-lg-9 col-xl-6">
					<commerce-ui:price
						CPCatalogEntry="<%= cpCatalogEntry %>"
						namespace="<%= liferayPortletResponse.getNamespace() %>"
					/>
				</div>
			</div>

			<c:if test="<%= cpSku != null %>">
				<liferay-commerce:tier-price
					CPInstanceId="<%= cpSku.getCPInstanceId() %>"
					taglibQuantityInputId='<%= liferayPortletResponse.getNamespace() + cpDefinitionId + "Quantity" %>'
				/>
			</c:if>

			<%
			int minOrderQuantity = cpContentHelper.getMinOrderQuantity(cpDefinitionId);
			%>

			<c:if test="<%= minOrderQuantity > CPDefinitionInventoryConstants.DEFAULT_MIN_ORDER_QUANTITY %>">
				<span class="min-quantity-per-order">
					<liferay-ui:message arguments="<%= minOrderQuantity %>" key="minimum-quantity-per-order-x" />
				</span>
			</c:if>

			<div class="align-items-center d-flex mt-3 product-detail-actions">
				<commerce-ui:add-to-cart
					alignment="left"
					CPCatalogEntry="<%= cpCatalogEntry %>"
					inline="<%= true %>"
					namespace="<%= liferayPortletResponse.getNamespace() %>"
					size="lg"
					skuOptions="[]"
				/>
			</div>
		</div>
	</div>
</div>