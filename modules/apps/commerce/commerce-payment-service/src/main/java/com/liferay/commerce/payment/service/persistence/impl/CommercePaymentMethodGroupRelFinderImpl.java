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

package com.liferay.commerce.payment.service.persistence.impl;

import com.liferay.commerce.payment.model.CommercePaymentMethodGroupRel;
import com.liferay.commerce.payment.model.impl.CommercePaymentMethodGroupRelImpl;
import com.liferay.commerce.payment.service.persistence.CommercePaymentMethodGroupRelFinder;
import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.Type;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.security.permission.InlineSQLHelperUtil;
import com.liferay.portal.spring.extender.service.ServiceReference;

import java.util.Iterator;
import java.util.List;

/**
 * @author Riccardo Alberti
 */
public class CommercePaymentMethodGroupRelFinderImpl
	extends CommercePaymentMethodGroupRelFinderBaseImpl
	implements CommercePaymentMethodGroupRelFinder {

	public static final String COUNT_BY_G_A =
		CommercePaymentMethodGroupRelFinder.class.getName() + ".countByG_A";

	public static final String FIND_BY_G_A =
		CommercePaymentMethodGroupRelFinder.class.getName() + ".findByG_A";

	@Override
	public int countByG_A(long groupId, boolean active) {
		return countByG_A(groupId, active, false);
	}

	@Override
	public int countByG_A(
		long groupId, boolean active, boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), COUNT_BY_G_A);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CommercePaymentMethodGroupRel.class.getName(),
					"CommercePaymentMethodGroupRel.CPaymentMethodGroupRelId",
					null, null, new long[] {0}, null);
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addScalar(COUNT_COLUMN_NAME, Type.INTEGER);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);
			queryPos.add(active);

			Iterator<Integer> iterator = sqlQuery.iterate();

			if (iterator.hasNext()) {
				Integer sum = iterator.next();

				if (sum != null) {
					return sum.intValue();
				}
			}

			return 0;
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	public List<CommercePaymentMethodGroupRel> findByG_A(
		long groupId, boolean active) {

		return findByG_A(groupId, active, false);
	}

	@Override
	public List<CommercePaymentMethodGroupRel> findByG_A(
		long groupId, boolean active, boolean inlineSQLHelper) {

		Session session = null;

		try {
			session = openSession();

			String sql = _customSQL.get(getClass(), FIND_BY_G_A);

			if (inlineSQLHelper) {
				sql = InlineSQLHelperUtil.replacePermissionCheck(
					sql, CommercePaymentMethodGroupRel.class.getName(),
					"CommercePaymentMethodGroupRel.CPaymentMethodGroupRelId",
					null, null, new long[] {0}, null);
			}

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity(
				CommercePaymentMethodGroupRelImpl.TABLE_NAME,
				CommercePaymentMethodGroupRelImpl.class);

			QueryPos queryPos = QueryPos.getInstance(sqlQuery);

			queryPos.add(groupId);
			queryPos.add(active);

			return (List<CommercePaymentMethodGroupRel>)QueryUtil.list(
				sqlQuery, getDialect(), QueryUtil.ALL_POS, QueryUtil.ALL_POS);
		}
		catch (Exception exception) {
			throw new SystemException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@ServiceReference(type = CustomSQL.class)
	private CustomSQL _customSQL;

}