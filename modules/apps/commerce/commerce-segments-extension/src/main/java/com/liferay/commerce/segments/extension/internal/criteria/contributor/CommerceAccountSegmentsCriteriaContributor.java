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

package com.liferay.commerce.segments.extension.internal.criteria.contributor;

import com.liferay.commerce.account.model.CommerceAccount;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.segments.extension.internal.odata.entity.CommerceAccountEntityModel;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.segments.criteria.Criteria;
import com.liferay.segments.criteria.contributor.SegmentsCriteriaContributor;
import com.liferay.segments.field.Field;
import com.liferay.segments.field.customizer.SegmentsFieldCustomizer;
import com.liferay.segments.field.customizer.SegmentsFieldCustomizerRegistry;
import com.liferay.segments.odata.retriever.ODataRetriever;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Stefano Puzzuoli
 */
@Component(
	enabled = false, immediate = true,
	property = {
		"segments.criteria.contributor.key=" + CommerceAccountSegmentsCriteriaContributor.KEY,
		"segments.criteria.contributor.model.class.name=com.liferay.portal.kernel.model.User",
		"segments.criteria.contributor.priority:Integer=80"
	},
	service = SegmentsCriteriaContributor.class
)
public class CommerceAccountSegmentsCriteriaContributor
	implements SegmentsCriteriaContributor {

	public static final String KEY = "commerce-account";

	@Override
	public void contribute(
		Criteria criteria, String filterString,
		Criteria.Conjunction conjunction) {

		criteria.addCriterion(getKey(), getType(), filterString, conjunction);

		long companyId = CompanyThreadLocal.getCompanyId();

		String newFilterString = null;

		try {
			StringBundler sb = new StringBundler();

			List<CommerceAccount> commerceAccounts = _oDataRetriever.getResults(
				companyId, filterString, LocaleUtil.getDefault(),
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (int i = 0; i < commerceAccounts.size(); i++) {
				CommerceAccount commerceAccount = commerceAccounts.get(i);

				List<CommerceAccountUserRel> commerceAccountUserRels =
					commerceAccount.getCommerceAccountUserRels();

				int j = 0;

				for (CommerceAccountUserRel commerceAccountUserRel :
						commerceAccountUserRels) {

					sb.append("(userId eq '");
					sb.append(
						commerceAccountUserRel.getCommerceAccountUserId());
					sb.append("')");

					if (j < (commerceAccountUserRels.size() - 1)) {
						sb.append(" or ");
					}

					j += 1;
				}

				if (i < (commerceAccounts.size() - 1)) {
					sb.append(" or ");
				}
			}

			newFilterString = sb.toString();
		}
		catch (PortalException portalException) {
			_log.error(
				StringBundler.concat(
					"Unable to evaluate criteria ", criteria, " with filter ",
					filterString, " and conjunction ", conjunction.getValue()),
				portalException);
		}

		if (Validator.isNull(newFilterString)) {
			newFilterString = "(userId eq '0')";
		}

		criteria.addFilter(getType(), newFilterString, conjunction);
	}

	@Override
	public EntityModel getEntityModel() {
		return _entityModel;
	}

	@Override
	public String getEntityName() {
		return CommerceAccountEntityModel.NAME;
	}

	@Override
	public List<Field> getFields(PortletRequest portletRequest) {
		Optional<SegmentsFieldCustomizer> segmentsFieldCustomizerOptional =
			_segmentsFieldCustomizerRegistry.getSegmentsFieldCustomizerOptional(
				_entityModel.getName(), "commerceAccountId");

		if (!segmentsFieldCustomizerOptional.isPresent()) {
			return Collections.emptyList();
		}

		SegmentsFieldCustomizer segmentsFieldCustomizer =
			segmentsFieldCustomizerOptional.get();

		return Collections.singletonList(
			new Field(
				"commerceAccountId",
				LanguageUtil.get(_portal.getLocale(portletRequest), "account"),
				"id", Collections.emptyList(),
				segmentsFieldCustomizer.getSelectEntity(portletRequest)));
	}

	@Override
	public String getKey() {
		return KEY;
	}

	@Override
	public Criteria.Type getType() {
		return Criteria.Type.MODEL;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		CommerceAccountSegmentsCriteriaContributor.class);

	private static final EntityModel _entityModel =
		new CommerceAccountEntityModel();

	@Reference(
		target = "(model.class.name=com.liferay.commerce.account.model.CommerceAccount)"
	)
	private ODataRetriever<CommerceAccount> _oDataRetriever;

	@Reference
	private Portal _portal;

	@Reference
	private SegmentsFieldCustomizerRegistry _segmentsFieldCustomizerRegistry;

}