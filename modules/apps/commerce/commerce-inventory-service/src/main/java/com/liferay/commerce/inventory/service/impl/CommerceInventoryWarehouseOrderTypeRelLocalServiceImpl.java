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

package com.liferay.commerce.inventory.service.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouse;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseOrderTypeRel;
import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseOrderTypeRelTable;
import com.liferay.commerce.inventory.service.base.CommerceInventoryWarehouseOrderTypeRelLocalServiceBaseImpl;
import com.liferay.commerce.model.CommerceOrderTypeTable;
import com.liferay.expando.kernel.service.ExpandoRowLocalService;
import com.liferay.petra.sql.dsl.DSLFunctionFactoryUtil;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.query.FromStep;
import com.liferay.petra.sql.dsl.query.GroupByStep;
import com.liferay.petra.sql.dsl.query.JoinStep;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.List;

/**
 * @author Luca Pellizzon
 */
public class CommerceInventoryWarehouseOrderTypeRelLocalServiceImpl
	extends CommerceInventoryWarehouseOrderTypeRelLocalServiceBaseImpl {

	@Override
	public CommerceInventoryWarehouseOrderTypeRel
			addCommerceInventoryWarehouseOrderTypeRel(
				long userId, long commerceInventoryWarehouseId,
				long commerceOrderTypeId, int priority,
				ServiceContext serviceContext)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel =
				commerceInventoryWarehouseOrderTypeRelPersistence.create(
					counterLocalService.increment());

		commerceInventoryWarehouseOrderTypeRel.setCompanyId(
			user.getCompanyId());
		commerceInventoryWarehouseOrderTypeRel.setUserId(user.getUserId());
		commerceInventoryWarehouseOrderTypeRel.setUserName(user.getFullName());
		commerceInventoryWarehouseOrderTypeRel.setCommerceInventoryWarehouseId(
			commerceInventoryWarehouseId);
		commerceInventoryWarehouseOrderTypeRel.setCommerceOrderTypeId(
			commerceOrderTypeId);
		commerceInventoryWarehouseOrderTypeRel.setPriority(priority);
		commerceInventoryWarehouseOrderTypeRel.setExpandoBridgeAttributes(
			serviceContext);

		commerceInventoryWarehouseOrderTypeRel =
			commerceInventoryWarehouseOrderTypeRelPersistence.update(
				commerceInventoryWarehouseOrderTypeRel);

		reindexCommerceInventoryWarehouse(commerceInventoryWarehouseId);

		return commerceInventoryWarehouseOrderTypeRel;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceInventoryWarehouseOrderTypeRel
			deleteCommerceInventoryWarehouseOrderTypeRel(
				CommerceInventoryWarehouseOrderTypeRel
					commerceInventoryWarehouseOrderTypeRel)
		throws PortalException {

		commerceInventoryWarehouseOrderTypeRelPersistence.remove(
			commerceInventoryWarehouseOrderTypeRel);

		_expandoRowLocalService.deleteRows(
			commerceInventoryWarehouseOrderTypeRel.
				getCommerceInventoryWarehouseOrderTypeRelId());

		reindexCommerceInventoryWarehouse(
			commerceInventoryWarehouseOrderTypeRel.
				getCommerceInventoryWarehouseId());

		return commerceInventoryWarehouseOrderTypeRel;
	}

	@Override
	public CommerceInventoryWarehouseOrderTypeRel
			deleteCommerceInventoryWarehouseOrderTypeRel(
				long commerceInventoryWarehouseOrderTypeRelId)
		throws PortalException {

		CommerceInventoryWarehouseOrderTypeRel
			commerceInventoryWarehouseOrderTypeRel =
				commerceInventoryWarehouseOrderTypeRelPersistence.
					findByPrimaryKey(commerceInventoryWarehouseOrderTypeRelId);

		return commerceInventoryWarehouseOrderTypeRelLocalService.
			deleteCommerceInventoryWarehouseOrderTypeRel(
				commerceInventoryWarehouseOrderTypeRel);
	}

	@Override
	public void deleteCommerceInventoryWarehouseOrderTypeRels(
		long commerceInventoryWarehouseId) {

		commerceInventoryWarehouseOrderTypeRelPersistence.
			removeByCommerceInventoryWarehouseId(commerceInventoryWarehouseId);
	}

	@Override
	public CommerceInventoryWarehouseOrderTypeRel
		fetchCommerceInventoryWarehouseOrderTypeRel(
			long commerceInventoryWarehouseId, long commerceOrderTypeId) {

		return commerceInventoryWarehouseOrderTypeRelPersistence.
			fetchByCIW_COTI(commerceInventoryWarehouseId, commerceOrderTypeId);
	}

	@Override
	public CommerceInventoryWarehouseOrderTypeRel
			getCommerceInventoryWarehouseOrderTypeRel(
				long commerceInventoryWarehouseOrderTypeRelId)
		throws PortalException {

		return commerceInventoryWarehouseOrderTypeRelPersistence.
			findByPrimaryKey(commerceInventoryWarehouseOrderTypeRelId);
	}

	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel>
		getCommerceInventoryWarehouseOrderTypeRels(
			long commerceInventoryWarehouseId) {

		return commerceInventoryWarehouseOrderTypeRelPersistence.
			findByCommerceInventoryWarehouseId(commerceInventoryWarehouseId);
	}

	@Override
	public List<CommerceInventoryWarehouseOrderTypeRel>
			getCommerceInventoryWarehouseOrderTypeRels(
				long commerceInventoryWarehouseId, String name, int start,
				int end,
				OrderByComparator<CommerceInventoryWarehouseOrderTypeRel>
					orderByComparator)
		throws PortalException {

		return dslQuery(
			_getGroupByStep(
				DSLQueryFactoryUtil.selectDistinct(
					CommerceInventoryWarehouseOrderTypeRelTable.INSTANCE),
				commerceInventoryWarehouseId, name
			).orderBy(
				CommerceInventoryWarehouseOrderTypeRelTable.INSTANCE,
				orderByComparator
			).limit(
				start, end
			));
	}

	@Override
	public int getCommerceInventoryWarehouseOrderTypeRelsCount(
			long commerceInventoryWarehouseId, String name)
		throws PortalException {

		return dslQueryCount(
			_getGroupByStep(
				DSLQueryFactoryUtil.countDistinct(
					CommerceInventoryWarehouseOrderTypeRelTable.INSTANCE.
						commerceInventoryWarehouseOrderTypeRelId),
				commerceInventoryWarehouseId, name));
	}

	protected void reindexCommerceInventoryWarehouse(
			long commerceInventoryWarehouseId)
		throws PortalException {

		Indexer<CommerceInventoryWarehouse> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(
				CommerceInventoryWarehouse.class);

		indexer.reindex(
			CommerceInventoryWarehouse.class.getName(),
			commerceInventoryWarehouseId);
	}

	private GroupByStep _getGroupByStep(
			FromStep fromStep, Long commerceInventoryWarehouseId,
			String keywords)
		throws PortalException {

		JoinStep joinStep = fromStep.from(
			CommerceInventoryWarehouseOrderTypeRelTable.INSTANCE
		).innerJoinON(
			CommerceOrderTypeTable.INSTANCE,
			CommerceOrderTypeTable.INSTANCE.commerceOrderTypeId.eq(
				CommerceInventoryWarehouseOrderTypeRelTable.INSTANCE.
					commerceOrderTypeId)
		);

		return joinStep.where(
			() -> {
				Predicate predicate =
					CommerceInventoryWarehouseOrderTypeRelTable.INSTANCE.
						commerceInventoryWarehouseId.eq(
							commerceInventoryWarehouseId);

				if (Validator.isNotNull(keywords)) {
					predicate = predicate.and(
						Predicate.withParentheses(
							_customSQL.getKeywordsPredicate(
								DSLFunctionFactoryUtil.lower(
									CommerceOrderTypeTable.INSTANCE.name),
								_customSQL.keywords(keywords, true))));
				}

				return predicate;
			});
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

	@ServiceReference(type = ExpandoRowLocalService.class)
	private ExpandoRowLocalService _expandoRowLocalService;

}