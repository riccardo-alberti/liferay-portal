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

package com.liferay.commerce.internal.search;

import com.liferay.commerce.model.CommerceOrderType;
import com.liferay.commerce.service.CommerceOrderTypeLocalService;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.BaseIndexer;
import com.liferay.portal.kernel.search.BooleanQuery;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.IndexWriterHelper;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.Summary;
import com.liferay.portal.kernel.search.filter.BooleanFilter;
import com.liferay.portal.kernel.util.GetterUtil;

import java.util.Locale;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(enabled = false, immediate = true, service = Indexer.class)
public class CommerceOrderTypeIndexer extends BaseIndexer<CommerceOrderType> {

	public static final String CLASS_NAME = CommerceOrderType.class.getName();

	public CommerceOrderTypeIndexer() {
		setDefaultSelectedFieldNames(
			Field.COMPANY_ID, Field.ENTRY_CLASS_NAME, Field.ENTRY_CLASS_PK,
			Field.NAME);
		setFilterSearch(true);
		setPermissionAware(true);
	}

	@Override
	public String getClassName() {
		return CLASS_NAME;
	}

	@Override
	public void postProcessSearchQuery(
			BooleanQuery searchQuery, BooleanFilter fullQueryBooleanFilter,
			SearchContext searchContext)
		throws Exception {

		addSearchLocalizedTerm(
			searchQuery, searchContext, Field.DESCRIPTION, false);
		addSearchTerm(searchQuery, searchContext, Field.ENTRY_CLASS_PK, false);
		addSearchTerm(searchQuery, searchContext, Field.NAME, false);
		addSearchLocalizedTerm(searchQuery, searchContext, Field.NAME, false);
		addSearchTerm(searchQuery, searchContext, Field.USER_NAME, false);
	}

	@Override
	protected void doDelete(CommerceOrderType commerceOrderType)
		throws Exception {

		deleteDocument(
			commerceOrderType.getCompanyId(),
			commerceOrderType.getCommerceOrderTypeId());
	}

	@Override
	protected Document doGetDocument(CommerceOrderType commerceOrderType)
		throws Exception {

		if (_log.isDebugEnabled()) {
			_log.debug("Indexing pricing class " + commerceOrderType);
		}

		Document document = getBaseModelDocument(CLASS_NAME, commerceOrderType);

		document.addText(Field.NAME, commerceOrderType.getName());

		return document;
	}

	@Override
	protected Summary doGetSummary(
		Document document, Locale locale, String snippet,
		PortletRequest portletRequest, PortletResponse portletResponse) {

		Summary summary = createSummary(document, Field.NAME, Field.NAME);

		summary.setMaxContentLength(200);

		return summary;
	}

	@Override
	protected void doReindex(CommerceOrderType commerceOrderType)
		throws Exception {

		_indexWriterHelper.updateDocument(
			getSearchEngineId(), commerceOrderType.getCompanyId(),
			getDocument(commerceOrderType), isCommitImmediately());
	}

	@Override
	protected void doReindex(String className, long classPK) throws Exception {
		doReindex(_commerceOrderTypeLocalService.getCommerceOrderType(classPK));
	}

	@Override
	protected void doReindex(String[] ids) throws Exception {
		long companyId = GetterUtil.getLong(ids[0]);

		reindexCommerceOrderTypees(companyId);
	}

	protected void reindexCommerceOrderTypees(long companyId)
		throws PortalException {

		final IndexableActionableDynamicQuery indexableActionableDynamicQuery =
			_commerceOrderTypeLocalService.getIndexableActionableDynamicQuery();

		indexableActionableDynamicQuery.setCompanyId(companyId);
		indexableActionableDynamicQuery.setPerformActionMethod(
			(CommerceOrderType commerceOrderType) -> {
				try {
					indexableActionableDynamicQuery.addDocuments(
						getDocument(commerceOrderType));
				}
				catch (PortalException portalException) {
					if (_log.isWarnEnabled()) {
						long commerceOrderTypeId =
							commerceOrderType.getCommerceOrderTypeId();

						_log.warn(
							"Unable to index commerce order type " +
								commerceOrderTypeId,
							portalException);
					}
				}
			});
		indexableActionableDynamicQuery.setSearchEngineId(getSearchEngineId());

		indexableActionableDynamicQuery.performActions();
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceOrderTypeIndexer.class);

	@Reference
	private CommerceOrderTypeLocalService _commerceOrderTypeLocalService;

	@Reference
	private IndexWriterHelper _indexWriterHelper;

}