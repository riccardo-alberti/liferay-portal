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

package com.liferay.commerce.qualification.service.impl;

import com.liferay.commerce.qualification.entity.CommerceQualificationRelEntity;
import com.liferay.commerce.qualification.entity.CommerceQualificationRelEntityRegistry;
import com.liferay.commerce.qualification.entity.SourceCommerceQualificationRelEntity;
import com.liferay.commerce.qualification.entity.TargetCommerceQualificationRelEntity;
import com.liferay.commerce.qualification.model.CommerceQualificationRel;
import com.liferay.commerce.qualification.model.CommerceQualificationRelTable;
import com.liferay.commerce.qualification.service.base.CommerceQualificationRelLocalServiceBaseImpl;
import com.liferay.petra.sql.dsl.DSLFunctionFactoryUtil;
import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.query.FromStep;
import com.liferay.petra.sql.dsl.query.GroupByStep;
import com.liferay.petra.sql.dsl.query.JoinStep;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.ClassName;
import com.liferay.portal.kernel.model.SystemEventConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.security.permission.InlineSQLHelper;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.systemevent.SystemEvent;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	enabled = false,
	property = "model.class.name=com.liferay.commerce.qualification.model.CommerceQualificationRel",
	service = AopService.class
)
public class CommerceQualificationRelLocalServiceImpl
	extends CommerceQualificationRelLocalServiceBaseImpl {

	@Override
	public CommerceQualificationRel addCommerceQualificationRel(
			long userId, String sourceClassName, long sourceClassPK,
			String targetClassName, long targetClassPK)
		throws PortalException {

		CommerceQualificationRel commerceQualificationRel =
			commerceQualificationRelPersistence.create(
				counterLocalService.increment());

		User user = userLocalService.getUser(userId);

		commerceQualificationRel.setCompanyId(user.getCompanyId());
		commerceQualificationRel.setUserId(user.getUserId());
		commerceQualificationRel.setUserName(user.getFullName());

		commerceQualificationRel.setSourceClassNameId(
			classNameLocalService.getClassNameId(sourceClassName));
		commerceQualificationRel.setSourceClassNameId(
			classNameLocalService.getClassNameId(targetClassName));
		commerceQualificationRel.setSourceClassPK(sourceClassPK);
		commerceQualificationRel.setSourceClassPK(targetClassPK);

		commerceQualificationRel = commerceQualificationRelPersistence.update(
			commerceQualificationRel);

		reindexSourceCommerceQualification(sourceClassName, sourceClassPK);

		return commerceQualificationRel;
	}

	@Override
	@SystemEvent(type = SystemEventConstants.TYPE_DELETE)
	public CommerceQualificationRel deleteCommerceQualificationRel(
			CommerceQualificationRel commerceQualificationRel)
		throws PortalException {

		commerceQualificationRelPersistence.remove(commerceQualificationRel);

		ClassName className = classNameLocalService.getClassName(
			commerceQualificationRel.getSourceClassNameId());

		reindexSourceCommerceQualification(
			className.getClassName(),
			commerceQualificationRel.getSourceClassPK());

		return commerceQualificationRel;
	}

	@Override
	public CommerceQualificationRel deleteCommerceQualificationRel(
			long commerceQualificationRelId)
		throws PortalException {

		CommerceQualificationRel commerceQualificationRel =
			commerceQualificationRelPersistence.findByPrimaryKey(
				commerceQualificationRelId);

		return commerceQualificationRelLocalService.
			deleteCommerceQualificationRel(commerceQualificationRel);
	}

	@Override
	public void deleteCommerceQualificationRels(
			String sourceClassName, long sourceClassPK)
		throws PortalException {

		List<CommerceQualificationRel> commerceQualificationRels =
			commerceQualificationRelPersistence.findByS_S(
				classNameLocalService.getClassNameId(sourceClassName),
				sourceClassPK);

		for (CommerceQualificationRel commerceQualificationRel :
				commerceQualificationRels) {

			commerceQualificationRelLocalService.deleteCommerceQualificationRel(
				commerceQualificationRel);
		}
	}

	@Override
	public void deleteCommerceQualificationRels(
			String sourceClassName, long sourceClassPK, String targetClassName)
		throws PortalException {

		List<CommerceQualificationRel> commerceQualificationRels =
			commerceQualificationRelPersistence.findByS_S_T(
				classNameLocalService.getClassNameId(sourceClassName),
				sourceClassPK,
				classNameLocalService.getClassNameId(targetClassName));

		for (CommerceQualificationRel commerceQualificationRel :
				commerceQualificationRels) {

			commerceQualificationRelLocalService.deleteCommerceQualificationRel(
				commerceQualificationRel);
		}
	}

	@Override
	public CommerceQualificationRel fetchCommerceQualificationRel(
		String sourceClassName, long sourceClassPK, String targetClassName,
		long targetClassPK) {

		return commerceQualificationRelPersistence.fetchByS_S_T_T(
			classNameLocalService.getClassNameId(sourceClassName),
			sourceClassPK,
			classNameLocalService.getClassNameId(targetClassName),
			targetClassPK);
	}

	@Override
	public List<CommerceQualificationRel> getCommerceQualificationRels(
		String sourceClassName, long sourceClassPK) {

		return commerceQualificationRelPersistence.findByS_S(
			classNameLocalService.getClassNameId(sourceClassName),
			sourceClassPK);
	}

	@Override
	public List<CommerceQualificationRel> getCommerceQualificationRels(
		String sourceClassName, long sourceClassPK, int start, int end,
		OrderByComparator<CommerceQualificationRel> orderByComparator) {

		return commerceQualificationRelPersistence.findByS_S(
			classNameLocalService.getClassNameId(sourceClassName),
			sourceClassPK, start, end, orderByComparator);
	}

	@Override
	public int getCommerceQualificationRelsCount(
		String sourceClassName, long sourceClassPK) {

		return commerceQualificationRelPersistence.countByS_S(
			classNameLocalService.getClassNameId(sourceClassName),
			sourceClassPK);
	}

	@Override
	public List<CommerceQualificationRel> getSourceCommerceQualificationRels(
		long companyId, String sourceClassName,
		Map<String, Long[]> targetFilters) {

		SourceCommerceQualificationRelEntity
			sourceCommerceQualificationRelEntity =
				(SourceCommerceQualificationRelEntity)
					_commerceQualificationRelEntityRegistry.
						getCommerceQualificationRelEntity(sourceClassName);

		return dslQuery(
			_getGroupByStep(
				companyId,
				DSLQueryFactoryUtil.selectDistinct(
					CommerceQualificationRelTable.INSTANCE),
				sourceCommerceQualificationRelEntity, targetFilters
			).orderBy(
				sourceCommerceQualificationRelEntity.getOrderByExpression()
			));
	}

	@Override
	public List<CommerceQualificationRel> getTargetCommerceQualificationRels(
		String keywords, String sourceClassName, long sourceClassPK,
		String targetClassName, int start, int end) {

		TargetCommerceQualificationRelEntity
			targetCommerceQualificationRelEntity =
				(TargetCommerceQualificationRelEntity)
					_commerceQualificationRelEntityRegistry.
						getCommerceQualificationRelEntity(targetClassName);

		return dslQuery(
			_getGroupByStep(
				DSLQueryFactoryUtil.selectDistinct(
					CommerceQualificationRelTable.INSTANCE),
				targetCommerceQualificationRelEntity, keywords, sourceClassName,
				sourceClassPK
			).orderBy(
				targetCommerceQualificationRelEntity.getOrderByExpression()
			).limit(
				start, end
			));
	}

	@Override
	public int getTargetCommerceQualificationRelsCount(
		String keywords, String sourceClassName, long sourceClassPK,
		String targetClassName) {

		TargetCommerceQualificationRelEntity
			targetCommerceQualificationRelEntity =
				(TargetCommerceQualificationRelEntity)
					_commerceQualificationRelEntityRegistry.
						getCommerceQualificationRelEntity(targetClassName);

		return dslQueryCount(
			_getGroupByStep(
				DSLQueryFactoryUtil.countDistinct(
					CommerceQualificationRelTable.INSTANCE.
						commerceQualificationRelId),
				targetCommerceQualificationRelEntity, keywords, sourceClassName,
				sourceClassPK));
	}

	protected void reindexSourceCommerceQualification(
			String sourceClassName, long sourceClassPK)
		throws PortalException {

		Indexer<?> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
			sourceClassName);

		indexer.reindex(sourceClassName, sourceClassPK);
	}

	private GroupByStep _getGroupByStep(
		FromStep fromStep,
		TargetCommerceQualificationRelEntity
			targetCommerceQualificationRelEntity,
		String keywords, String sourceClassName, Long sourceClassPK) {

		JoinStep joinStep = fromStep.from(
			CommerceQualificationRelTable.INSTANCE
		).innerJoinON(
			targetCommerceQualificationRelEntity.getTable(),
			CommerceQualificationRelTable.INSTANCE.targetClassPK.eq(
				targetCommerceQualificationRelEntity.getPrimaryKeyColumn()
			).and(
				CommerceQualificationRelTable.INSTANCE.targetClassNameId.eq(
					classNameLocalService.getClassNameId(
						targetCommerceQualificationRelEntity.
							getModelClassName()))
			)
		);

		return joinStep.where(
			() -> CommerceQualificationRelTable.INSTANCE.sourceClassNameId.eq(
				classNameLocalService.getClassNameId(sourceClassName)
			).and(
				CommerceQualificationRelTable.INSTANCE.sourceClassPK.eq(
					sourceClassPK)
			).and(
				targetCommerceQualificationRelEntity.getFilterPredicate()
			).and(
				_getPermissionWherePredicate(
					targetCommerceQualificationRelEntity)
			).and(
				() -> {
					if (Validator.isNotNull(keywords)) {
						return Predicate.withParentheses(
							_customSQL.getKeywordsPredicate(
								DSLFunctionFactoryUtil.lower(
									targetCommerceQualificationRelEntity.
										getKeywordsExpression()),
								_customSQL.keywords(keywords, true)));
					}

					return null;
				}
			));
	}

	private GroupByStep _getGroupByStep(
		long companyId, FromStep fromStep,
		SourceCommerceQualificationRelEntity
			sourceCommerceQualificationRelEntity,
		Map<String, Long[]> targetFilters) {

		JoinStep joinStep = fromStep.from(
			CommerceQualificationRelTable.INSTANCE
		).innerJoinON(
			sourceCommerceQualificationRelEntity.getTable(),
			CommerceQualificationRelTable.INSTANCE.sourceClassPK.eq(
				sourceCommerceQualificationRelEntity.getPrimaryKeyColumn())
		);

		Predicate predicate =
			sourceCommerceQualificationRelEntity.getFilterPredicate(companyId);

		String[] availableTargets =
			sourceCommerceQualificationRelEntity.getAvailableTargetClassNames();

		if (availableTargets == null) {
			return joinStep.where(predicate);
		}

		List<String> availableTargetList = Arrays.asList(availableTargets);

		for (Map.Entry<String, Long[]> targetFilter :
				targetFilters.entrySet()) {

			if (!availableTargetList.contains(targetFilter.getKey())) {
				continue;
			}

			CommerceQualificationRelTable selfLeftJoinTable =
				CommerceQualificationRelTable.INSTANCE.as(
					"selfLeftJoinTable" + targetFilter.getKey());

			joinStep = joinStep.leftJoinOn(
				selfLeftJoinTable,
				selfLeftJoinTable.INSTANCE.sourceClassPK.eq(
					sourceCommerceQualificationRelEntity.getPrimaryKeyColumn()
				).and(
					selfLeftJoinTable.INSTANCE.sourceClassNameId.eq(
						classNameLocalService.getClassNameId(
							sourceCommerceQualificationRelEntity.
								getModelClassName()))
				));

			predicate = predicate.and(
				() -> {
					Long[] targetFilterValue = targetFilter.getValue();

					if (targetFilterValue != null) {
						if (targetFilterValue.length == 0) {
							return selfLeftJoinTable.targetClassPK.in(
								new Long[] {0L});
						}

						return selfLeftJoinTable.targetClassPK.in(
							targetFilterValue);
					}

					return selfLeftJoinTable.commerceQualificationRelId.
						isNull();
				}
			).and(
				_getPermissionWherePredicate(
					sourceCommerceQualificationRelEntity)
			);
		}

		return joinStep.where(predicate);
	}

	private Predicate _getPermissionWherePredicate(
		CommerceQualificationRelEntity commerceQualificationRelEntity) {

		PermissionChecker permissionChecker =
			PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker != null) {
			User user = permissionChecker.getUser();

			return _inlineSQLHelper.getPermissionWherePredicate(
				commerceQualificationRelEntity.getModelClassName(),
				commerceQualificationRelEntity.getPrimaryKeyColumn(),
				user.getGroupIds());
		}

		return null;
	}

	@Reference
	private CommerceQualificationRelEntityRegistry
		_commerceQualificationRelEntityRegistry;

	@Reference
	private CustomSQL _customSQL;

	@Reference
	private InlineSQLHelper _inlineSQLHelper;

}