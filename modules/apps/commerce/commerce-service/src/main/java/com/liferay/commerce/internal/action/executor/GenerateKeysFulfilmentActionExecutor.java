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

package com.liferay.commerce.internal.action.executor;

import com.liferay.commerce.discount.constants.CommerceDiscountConstants;
import com.liferay.commerce.discount.model.CommerceDiscount;
import com.liferay.commerce.discount.model.CommerceDiscountAccountRelTable;
import com.liferay.commerce.discount.model.CommerceDiscountCommerceAccountGroupRelTable;
import com.liferay.commerce.discount.model.CommerceDiscountOrderTypeRelTable;
import com.liferay.commerce.discount.model.CommerceDiscountRel;
import com.liferay.commerce.discount.model.CommerceDiscountTable;
import com.liferay.commerce.discount.service.CommerceDiscountLocalService;
import com.liferay.commerce.discount.service.CommerceDiscountRelLocalService;
import com.liferay.commerce.price.list.model.CommercePriceEntry;
import com.liferay.commerce.price.list.model.CommercePriceList;
import com.liferay.commerce.price.list.model.CommercePriceListAccountRelTable;
import com.liferay.commerce.price.list.model.CommercePriceListChannelRelTable;
import com.liferay.commerce.price.list.model.CommercePriceListCommerceAccountGroupRelTable;
import com.liferay.commerce.price.list.model.CommercePriceListOrderTypeRelTable;
import com.liferay.commerce.price.list.model.CommercePriceListTable;
import com.liferay.commerce.price.list.model.CommerceTierPriceEntry;
import com.liferay.commerce.price.list.service.CommercePriceEntryLocalService;
import com.liferay.commerce.price.list.service.CommercePriceListLocalService;
import com.liferay.commerce.price.list.service.CommerceTierPriceEntryLocalService;
import com.liferay.commerce.pricing.constants.CommercePriceModifierConstants;
import com.liferay.commerce.pricing.model.CommercePriceModifier;
import com.liferay.commerce.pricing.model.CommercePriceModifierRel;
import com.liferay.commerce.pricing.model.CommercePricingClass;
import com.liferay.commerce.pricing.model.CommercePricingClassCPDefinitionRel;
import com.liferay.commerce.pricing.service.CommercePriceModifierLocalService;
import com.liferay.commerce.pricing.service.CommercePriceModifierRelLocalService;
import com.liferay.commerce.pricing.service.CommercePricingClassCPDefinitionRelLocalService;
import com.liferay.commerce.product.model.CPDefinition;
import com.liferay.commerce.product.model.CPInstance;
import com.liferay.commerce.product.model.CommerceChannelRelTable;
import com.liferay.commerce.product.service.CPInstanceLocalService;
import com.liferay.fulfilment.action.executor.BaseFulfilmentActionExecutor;
import com.liferay.fulfilment.constants.FulfilmentConstants;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutor;
import com.liferay.portal.workflow.kaleo.runtime.action.executor.ActionExecutorException;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false, immediate = true,
	property = "com.liferay.portal.workflow.kaleo.runtime.action.executor.language=java",
	service = ActionExecutor.class
)
public class GenerateKeysFulfilmentActionExecutor
	extends BaseFulfilmentActionExecutor {

	@Override
	protected int doExecute(
			Map<String, Serializable> inputParameters,
			Map<String, Serializable> outputParameters)
		throws ActionExecutorException {

		try {
			outputParameters.put(
				"keys",
				_generateKeys(
					GetterUtil.getLong(inputParameters.get("companyId")),
					GetterUtil.getString(inputParameters.get("className")),
					GetterUtil.getLong(inputParameters.get("classPK"))));
		}
		catch (PortalException portalException) {
			_log.error(portalException, portalException);

			throw new ActionExecutorException(portalException);
		}

		return FulfilmentConstants.STATUS_COMPLETED;
	}

	private void _addKeys(
		long cpInstanceId, List<Object[]> results, HashSet<String> keys) {

		for (Object[] result : results) {
			StringBundler sb = new StringBundler();

			sb.append(StringPool.SLASH);

			for (Object key : result) {
				if (key != null) {
					sb.append(key);
				}

				sb.append(StringPool.SLASH);
			}

			sb.append(cpInstanceId);

			keys.add(sb.toString());
		}
	}

	private void _addKeysForCommerceDiscountRels(
			CommerceDiscount commerceDiscount, HashSet<String> keys)
		throws PortalException {

		List<CPInstance> cpInstances = new ArrayList<>();

		if (Objects.equals(
				commerceDiscount.getTarget(),
				CommerceDiscountConstants.TARGET_SKUS)) {

			List<CommerceDiscountRel> commerceDiscountRels =
				_commerceDiscountRelLocalService.
					getCPInstancesByCommerceDiscountId(
						commerceDiscount.getCommerceDiscountId(),
						StringPool.BLANK, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (CommerceDiscountRel commerceDiscountRel :
					commerceDiscountRels) {

				cpInstances.add(
					_cpInstanceLocalService.getCPInstance(
						commerceDiscountRel.getClassPK()));
			}
		}
		else if (Objects.equals(
					commerceDiscount.getTarget(),
					CommerceDiscountConstants.TARGET_CATEGORIES)) {

			// TODO

		}
		else if (Objects.equals(
					commerceDiscount.getTarget(),
					CommerceDiscountConstants.TARGET_PRODUCT_GROUPS)) {

			List<CommerceDiscountRel> commerceDiscountRels =
				_commerceDiscountRelLocalService.
					getCommercePricingClassesByCommerceDiscountId(
						commerceDiscount.getCommerceDiscountId(),
						StringPool.BLANK, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (CommerceDiscountRel commerceDiscountRel :
					commerceDiscountRels) {

				List<CommercePricingClassCPDefinitionRel>
					commercePricingClassCPDefinitionRels =
						_commercePricingClassCPDefinitionRelLocalService.
							getCommercePricingClassCPDefinitionRels(
								commerceDiscountRel.getClassPK());

				for (CommercePricingClassCPDefinitionRel
						commercePricingClassCPDefinitionRel :
							commercePricingClassCPDefinitionRels) {

					cpInstances.addAll(
						_cpInstanceLocalService.getCPDefinitionInstances(
							commercePricingClassCPDefinitionRel.
								getCPDefinitionId(),
							WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
							QueryUtil.ALL_POS, null));
				}
			}
		}
		else if (Objects.equals(
					commerceDiscount.getTarget(),
					CommerceDiscountConstants.TARGET_PRODUCTS)) {

			List<CommerceDiscountRel> commerceDiscountRels =
				_commerceDiscountRelLocalService.
					getCPDefinitionsByCommerceDiscountId(
						commerceDiscount.getCommerceDiscountId(),
						StringPool.BLANK, "en_US", QueryUtil.ALL_POS,
						QueryUtil.ALL_POS);

			for (CommerceDiscountRel commerceDiscountRel :
					commerceDiscountRels) {

				cpInstances.addAll(
					_cpInstanceLocalService.getCPDefinitionInstances(
						commerceDiscountRel.getClassPK(),
						WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null));
			}
		}

		for (CPInstance cpInstance : cpInstances) {
			_addKeysForCommerceDiscounts(
				cpInstance.getCPInstanceId(),
				commerceDiscount.getCommerceDiscountId(), keys);
		}
	}

	private void _addKeysForCommerceDiscounts(
		long cpInstanceId, long commerceDiscountId, HashSet<String> keys) {

		List<Object[]> results = _commerceDiscountLocalService.dslQuery(
			DSLQueryFactoryUtil.selectDistinct(
				CommerceDiscountAccountRelTable.INSTANCE.commerceAccountId,
				CommerceDiscountCommerceAccountGroupRelTable.INSTANCE.
					commerceAccountGroupId,
				CommerceChannelRelTable.INSTANCE.commerceChannelId,
				CommerceDiscountOrderTypeRelTable.INSTANCE.commerceOrderTypeId
			).from(
				CommerceDiscountTable.INSTANCE
			).leftJoinOn(
				CommerceDiscountAccountRelTable.INSTANCE,
				CommerceDiscountAccountRelTable.INSTANCE.commerceDiscountId.eq(
					CommerceDiscountTable.INSTANCE.commerceDiscountId)
			).leftJoinOn(
				CommerceDiscountCommerceAccountGroupRelTable.INSTANCE,
				CommerceDiscountCommerceAccountGroupRelTable.INSTANCE.
					commerceDiscountId.eq(
						CommerceDiscountTable.INSTANCE.commerceDiscountId)
			).leftJoinOn(
				CommerceChannelRelTable.INSTANCE,
				CommerceChannelRelTable.INSTANCE.classPK.eq(
					CommerceDiscountTable.INSTANCE.commerceDiscountId
				).and(
					CommerceChannelRelTable.INSTANCE.classNameId.eq(
						_classNameLocalService.getClassNameId(
							CommerceDiscount.class.getName()))
				)
			).leftJoinOn(
				CommerceDiscountOrderTypeRelTable.INSTANCE,
				CommerceDiscountOrderTypeRelTable.INSTANCE.commerceDiscountId.
					eq(CommerceDiscountTable.INSTANCE.commerceDiscountId)
			).where(
				CommerceDiscountTable.INSTANCE.commerceDiscountId.eq(
					commerceDiscountId)
			));

		_addKeys(cpInstanceId, results, keys);
	}

	private void _addKeysForCommercePriceEntry(
			CommercePriceEntry commercePriceEntry, HashSet<String> keys)
		throws PortalException {

		CPInstance cpInstance = commercePriceEntry.getCPInstance();

		_addKeysForCommercePriceLists(
			cpInstance.getCPInstanceId(),
			commercePriceEntry.getCommercePriceListId(), keys);
	}

	private void _addKeysForCommercePriceLists(
		long cpInstanceId, long commercePriceListId, HashSet<String> keys) {

		List<Object[]> results = _commercePriceListLocalService.dslQuery(
			DSLQueryFactoryUtil.selectDistinct(
				CommercePriceListAccountRelTable.INSTANCE.commerceAccountId,
				CommercePriceListCommerceAccountGroupRelTable.INSTANCE.
					commerceAccountGroupId,
				CommercePriceListChannelRelTable.INSTANCE.commerceChannelId,
				CommercePriceListOrderTypeRelTable.INSTANCE.commerceOrderTypeId
			).from(
				CommercePriceListTable.INSTANCE
			).leftJoinOn(
				CommercePriceListAccountRelTable.INSTANCE,
				CommercePriceListAccountRelTable.INSTANCE.commercePriceListId.
					eq(CommercePriceListTable.INSTANCE.commercePriceListId)
			).leftJoinOn(
				CommercePriceListCommerceAccountGroupRelTable.INSTANCE,
				CommercePriceListCommerceAccountGroupRelTable.INSTANCE.
					commercePriceListId.eq(
						CommercePriceListTable.INSTANCE.commercePriceListId)
			).leftJoinOn(
				CommercePriceListChannelRelTable.INSTANCE,
				CommercePriceListChannelRelTable.INSTANCE.commercePriceListId.
					eq(CommercePriceListTable.INSTANCE.commercePriceListId)
			).leftJoinOn(
				CommercePriceListOrderTypeRelTable.INSTANCE,
				CommercePriceListOrderTypeRelTable.INSTANCE.commercePriceListId.
					eq(CommercePriceListTable.INSTANCE.commercePriceListId)
			).where(
				CommercePriceListTable.INSTANCE.commercePriceListId.eq(
					commercePriceListId)
			));

		_addKeys(cpInstanceId, results, keys);
	}

	private void _addKeysForCommercePriceModifier(
			CommercePriceModifier commercePriceModifier, HashSet<String> keys)
		throws PortalException {

		List<CPInstance> cpInstances = new ArrayList<>();

		if (Objects.equals(
				commercePriceModifier.getTarget(),
				CommercePriceModifierConstants.TARGET_CATALOG)) {

			cpInstances = _cpInstanceLocalService.getCPInstances(
				commercePriceModifier.getGroupId(),
				WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);
		}
		else if (Objects.equals(
					commercePriceModifier.getTarget(),
					CommercePriceModifierConstants.TARGET_CATEGORIES)) {

			// TODO

		}
		else if (Objects.equals(
					commercePriceModifier.getTarget(),
					CommercePriceModifierConstants.TARGET_PRODUCT_GROUPS)) {

			List<CommercePriceModifierRel> commercePriceModifierRels =
				_commercePriceModifierRelLocalService.
					getCommercePriceModifierRels(
						commercePriceModifier.getCommercePriceModifierId(),
						CommercePricingClass.class.getName());

			for (CommercePriceModifierRel commercePriceModifierRel :
					commercePriceModifierRels) {

				List<CommercePricingClassCPDefinitionRel>
					commercePricingClassCPDefinitionRels =
						_commercePricingClassCPDefinitionRelLocalService.
							getCommercePricingClassCPDefinitionRels(
								commercePriceModifierRel.getClassPK());

				for (CommercePricingClassCPDefinitionRel
						commercePricingClassCPDefinitionRel :
							commercePricingClassCPDefinitionRels) {

					cpInstances.addAll(
						_cpInstanceLocalService.getCPDefinitionInstances(
							commercePricingClassCPDefinitionRel.
								getCPDefinitionId(),
							WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
							QueryUtil.ALL_POS, null));
				}
			}
		}
		else if (Objects.equals(
					commercePriceModifier.getTarget(),
					CommercePriceModifierConstants.TARGET_PRODUCTS)) {

			List<CommercePriceModifierRel> commercePriceModifierRels =
				_commercePriceModifierRelLocalService.
					getCommercePriceModifierRels(
						commercePriceModifier.getCommercePriceModifierId(),
						CPDefinition.class.getName());

			for (CommercePriceModifierRel commercePriceModifierRel :
					commercePriceModifierRels) {

				cpInstances.addAll(
					_cpInstanceLocalService.getCPDefinitionInstances(
						commercePriceModifierRel.getClassPK(),
						WorkflowConstants.STATUS_ANY, QueryUtil.ALL_POS,
						QueryUtil.ALL_POS, null));
			}
		}

		for (CPInstance cpInstance : cpInstances) {
			_addKeysForCommercePriceLists(
				cpInstance.getCPInstanceId(),
				commercePriceModifier.getCommercePriceListId(), keys);
		}
	}

	private HashSet<String> _generateKeys(
			long companyId, String className, long classPK)
		throws PortalException {

		HashSet<String> keys = new HashSet<>();

		if (Objects.equals(className, CommerceDiscount.class.getName())) {
			_addKeysForCommerceDiscountRels(
				_commerceDiscountLocalService.getCommerceDiscount(classPK),
				keys);
		}
		else if (Objects.equals(
					className, CommerceDiscountRel.class.getName())) {

			CommerceDiscountRel commerceDiscountRel =
				_commerceDiscountRelLocalService.getCommerceDiscountRel(
					classPK);

			_addKeysForCommerceDiscountRels(
				commerceDiscountRel.getCommerceDiscount(), keys);
		}
		else if (Objects.equals(
					className, CommercePriceEntry.class.getName())) {

			_addKeysForCommercePriceEntry(
				_commercePriceEntryLocalService.getCommercePriceEntry(classPK),
				keys);
		}
		else if (Objects.equals(className, CommercePriceList.class.getName())) {
			List<CommercePriceEntry> commercePriceEntries =
				_commercePriceEntryLocalService.getCommercePriceEntries(
					classPK, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (CommercePriceEntry commercePriceEntry : commercePriceEntries) {
				_addKeysForCommercePriceEntry(commercePriceEntry, keys);
			}
		}
		else if (Objects.equals(
					className, CommercePriceModifier.class.getName())) {

			CommercePriceModifier commercePriceModifier =
				_commercePriceModifierLocalService.getCommercePriceModifier(
					classPK);

			_addKeysForCommercePriceModifier(commercePriceModifier, keys);
		}
		else if (Objects.equals(
					className, CommerceTierPriceEntry.class.getName())) {

			CommerceTierPriceEntry commerceTierPriceEntry =
				_commerceTierPriceEntryLocalService.getCommerceTierPriceEntry(
					classPK);

			_addKeysForCommercePriceEntry(
				commerceTierPriceEntry.getCommercePriceEntry(), keys);
		}
		else {
			List<CommercePriceList> commercePriceLists =
				_commercePriceListLocalService.getCommercePriceLists(
					companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (CommercePriceList commercePriceList : commercePriceLists) {
				List<CommercePriceEntry> commercePriceEntries =
					_commercePriceEntryLocalService.getCommercePriceEntries(
						commercePriceList.getCommercePriceListId(),
						QueryUtil.ALL_POS, QueryUtil.ALL_POS);

				for (CommercePriceEntry commercePriceEntry :
						commercePriceEntries) {

					_addKeysForCommercePriceEntry(commercePriceEntry, keys);
				}
			}

			List<CommerceDiscount> commerceDiscounts =
				_commerceDiscountLocalService.getCommerceDiscounts(
					QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (CommerceDiscount commerceDiscount : commerceDiscounts) {
				_addKeysForCommerceDiscountRels(commerceDiscount, keys);
			}
		}

		// TODO Options, Tax

		return keys;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		GenerateKeysFulfilmentActionExecutor.class);

	@Reference
	private ClassNameLocalService _classNameLocalService;

	@Reference
	private CommerceDiscountLocalService _commerceDiscountLocalService;

	@Reference
	private CommerceDiscountRelLocalService _commerceDiscountRelLocalService;

	@Reference
	private CommercePriceEntryLocalService _commercePriceEntryLocalService;

	@Reference
	private CommercePriceListLocalService _commercePriceListLocalService;

	@Reference
	private CommercePriceModifierLocalService
		_commercePriceModifierLocalService;

	@Reference
	private CommercePriceModifierRelLocalService
		_commercePriceModifierRelLocalService;

	@Reference
	private CommercePricingClassCPDefinitionRelLocalService
		_commercePricingClassCPDefinitionRelLocalService;

	@Reference
	private CommerceTierPriceEntryLocalService
		_commerceTierPriceEntryLocalService;

	@Reference
	private CPInstanceLocalService _cpInstanceLocalService;

}