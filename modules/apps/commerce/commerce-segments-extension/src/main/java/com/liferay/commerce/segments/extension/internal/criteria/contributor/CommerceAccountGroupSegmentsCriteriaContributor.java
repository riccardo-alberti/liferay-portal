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
import com.liferay.commerce.account.model.CommerceAccountGroup;
import com.liferay.commerce.account.model.CommerceAccountGroupCommerceAccountRel;
import com.liferay.commerce.account.model.CommerceAccountUserRel;
import com.liferay.commerce.account.service.CommerceAccountGroupCommerceAccountRelLocalService;
import com.liferay.commerce.account.service.CommerceAccountLocalService;
import com.liferay.commerce.segments.extension.internal.odata.entity.CommerceAccountGroupEntityModel;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.segments.criteria.Criteria;
import com.liferay.segments.criteria.contributor.SegmentsCriteriaContributor;
import com.liferay.segments.field.Field;
import com.liferay.segments.field.customizer.SegmentsFieldCustomizer;
import com.liferay.segments.field.customizer.SegmentsFieldCustomizerRegistry;
import com.liferay.segments.odata.retriever.ODataRetriever;

import java.util.ArrayList;
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
		"segments.criteria.contributor.key=" + CommerceAccountGroupSegmentsCriteriaContributor.KEY,
		"segments.criteria.contributor.model.class.name=com.liferay.portal.kernel.model.User",
		"segments.criteria.contributor.priority:Integer=90"
	},
	service = SegmentsCriteriaContributor.class
)
public class CommerceAccountGroupSegmentsCriteriaContributor
	implements SegmentsCriteriaContributor {

	public static final String KEY = "commerce-account-group";

	@Override
	public void contribute(
		Criteria criteria, String filterString,
		Criteria.Conjunction conjunction) {

		criteria.addCriterion(getKey(), getType(), filterString, conjunction);

		long companyId = CompanyThreadLocal.getCompanyId();
		String newFilterString = null;

		try {
			StringBundler sb = new StringBundler();

			List<CommerceAccountGroup> commerceAccountGroups =
				_oDataRetriever.getResults(
					companyId, filterString, LocaleUtil.getDefault(),
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (CommerceAccountGroup commerceAccountGroup :
					commerceAccountGroups) {

				List<CommerceAccountGroupCommerceAccountRel>
					commerceAccountGroupCommerceAccountRels =
						_commerceAccountGroupCommerceAccountRelLocalService.
							getCommerceAccountGroupCommerceAccountRels(
								commerceAccountGroup.
									getCommerceAccountGroupId(),
								QueryUtil.ALL_POS, QueryUtil.ALL_POS);

				List<CommerceAccount> commerceAccounts = new ArrayList<>();

				for (CommerceAccountGroupCommerceAccountRel
						commerceAccountGroupCommerceAccountRel :
							commerceAccountGroupCommerceAccountRels) {

					CommerceAccount commerceAccount =
						_commerceAccountLocalService.fetchCommerceAccount(
							commerceAccountGroupCommerceAccountRel.
								getCommerceAccountId());

					if (commerceAccount == null) {
						continue;
					}

					commerceAccounts.add(commerceAccount);
				}

				for (int j = 0; j < commerceAccounts.size(); j++) {
					CommerceAccount commerceAccount = commerceAccounts.get(j);

					List<CommerceAccountUserRel> commerceAccountUserRels =
						commerceAccount.getCommerceAccountUserRels();

					int k = 0;

					for (CommerceAccountUserRel commerceAccountUserRel :
							commerceAccountUserRels) {

						sb.append("(userId eq '");
						sb.append(
							commerceAccountUserRel.getCommerceAccountUserId());
						sb.append("')");

						if (k < (commerceAccountUserRels.size() - 1)) {
							sb.append(" or ");
						}

						k += 1;
					}

					final String currentFilterString = sb.toString();

					if (!currentFilterString.isEmpty() &&
						!currentFilterString.endsWith(" or ") &&
						(j < (commerceAccounts.size() - 1))) {

						sb.append(" or ");
					}
				}

				final String orEnding = " or ";
				newFilterString = sb.toString();

				if (newFilterString.endsWith(orEnding)) {
					newFilterString = newFilterString.substring(
						0, newFilterString.length() - orEnding.length());
				}
			}
		}
		catch (PortalException portalException) {
			_log.error(
				com.liferay.petra.string.StringBundler.concat(
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
		return CommerceAccountGroupEntityModel.NAME;
	}

	@Override
	public List<Field> getFields(PortletRequest portletRequest) {
		Optional<SegmentsFieldCustomizer> segmentsFieldCustomizerOptional =
			_segmentsFieldCustomizerRegistry.getSegmentsFieldCustomizerOptional(
				_entityModel.getName(), "commerceAccountGroupId");

		if (!segmentsFieldCustomizerOptional.isPresent()) {
			return Collections.emptyList();
		}

		SegmentsFieldCustomizer segmentsFieldCustomizer =
			segmentsFieldCustomizerOptional.get();

		return Collections.singletonList(
			new Field(
				"commerceAccountGroupId",
				LanguageUtil.get(
					_portal.getLocale(portletRequest), "account-group"),
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
		CommerceAccountGroupSegmentsCriteriaContributor.class);

	private static final EntityModel _entityModel =
		new CommerceAccountGroupEntityModel();

	@Reference
	private CommerceAccountGroupCommerceAccountRelLocalService
		_commerceAccountGroupCommerceAccountRelLocalService;

	@Reference
	private CommerceAccountLocalService _commerceAccountLocalService;

	@Reference(
		target = "(model.class.name=com.liferay.commerce.account.model.CommerceAccountGroup)"
	)
	private ODataRetriever<CommerceAccountGroup> _oDataRetriever;

	@Reference
	private Portal _portal;

	@Reference
	private SegmentsFieldCustomizerRegistry _segmentsFieldCustomizerRegistry;

}