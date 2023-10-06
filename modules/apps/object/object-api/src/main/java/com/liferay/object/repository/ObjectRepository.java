/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.repository;

import com.liferay.object.repository.entity.ObjectEntity;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Riccardo Alberti
 */
public interface ObjectRepository<T extends ObjectEntity> {

	public List<T> getObjectEntities(
			long groupId, long companyId, long userId, String search, int start,
			int end, String queryName, Object... parameters)
		throws PortalException;

	public int getObjectEntitiesCount(
			long groupId, long companyId, long userId, String search,
			String queryName, Object... parameters)
		throws PortalException;

	public T getObjectEntityById(long objectEntryId) throws PortalException;

}