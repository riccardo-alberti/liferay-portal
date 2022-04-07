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

package com.liferay.commerce.inventory.model.impl;

import com.liferay.commerce.inventory.model.CommerceInventoryWarehouseOrderTypeRel;
import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing CommerceInventoryWarehouseOrderTypeRel in entity cache.
 *
 * @author Luca Pellizzon
 * @generated
 */
public class CommerceInventoryWarehouseOrderTypeRelCacheModel
	implements CacheModel<CommerceInventoryWarehouseOrderTypeRel>,
			   Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof
				CommerceInventoryWarehouseOrderTypeRelCacheModel)) {

			return false;
		}

		CommerceInventoryWarehouseOrderTypeRelCacheModel
			commerceInventoryWarehouseOrderTypeRelCacheModel =
				(CommerceInventoryWarehouseOrderTypeRelCacheModel)object;

		if ((commerceInventoryWarehouseOrderTypeRelId ==
				commerceInventoryWarehouseOrderTypeRelCacheModel.
					commerceInventoryWarehouseOrderTypeRelId) &&
			(mvccVersion ==
				commerceInventoryWarehouseOrderTypeRelCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(
			0, commerceInventoryWarehouseOrderTypeRelId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(25);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", commerceInventoryWarehouseOrderTypeRelId=");
		sb.append(commerceInventoryWarehouseOrderTypeRelId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", commerceInventoryWarehouseId=");
		sb.append(commerceInventoryWarehouseId);
		sb.append(", commerceOrderTypeId=");
		sb.append(commerceOrderTypeId);
		sb.append(", priority=");
		sb.append(priority);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceInventoryWarehouseOrderTypeRel toEntityModel() {
		CommerceInventoryWarehouseOrderTypeRelImpl
			commerceInventoryWarehouseOrderTypeRelImpl =
				new CommerceInventoryWarehouseOrderTypeRelImpl();

		commerceInventoryWarehouseOrderTypeRelImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			commerceInventoryWarehouseOrderTypeRelImpl.setUuid("");
		}
		else {
			commerceInventoryWarehouseOrderTypeRelImpl.setUuid(uuid);
		}

		commerceInventoryWarehouseOrderTypeRelImpl.
			setCommerceInventoryWarehouseOrderTypeRelId(
				commerceInventoryWarehouseOrderTypeRelId);
		commerceInventoryWarehouseOrderTypeRelImpl.setCompanyId(companyId);
		commerceInventoryWarehouseOrderTypeRelImpl.setUserId(userId);

		if (userName == null) {
			commerceInventoryWarehouseOrderTypeRelImpl.setUserName("");
		}
		else {
			commerceInventoryWarehouseOrderTypeRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceInventoryWarehouseOrderTypeRelImpl.setCreateDate(null);
		}
		else {
			commerceInventoryWarehouseOrderTypeRelImpl.setCreateDate(
				new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceInventoryWarehouseOrderTypeRelImpl.setModifiedDate(null);
		}
		else {
			commerceInventoryWarehouseOrderTypeRelImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		commerceInventoryWarehouseOrderTypeRelImpl.
			setCommerceInventoryWarehouseId(commerceInventoryWarehouseId);
		commerceInventoryWarehouseOrderTypeRelImpl.setCommerceOrderTypeId(
			commerceOrderTypeId);
		commerceInventoryWarehouseOrderTypeRelImpl.setPriority(priority);

		if (lastPublishDate == Long.MIN_VALUE) {
			commerceInventoryWarehouseOrderTypeRelImpl.setLastPublishDate(null);
		}
		else {
			commerceInventoryWarehouseOrderTypeRelImpl.setLastPublishDate(
				new Date(lastPublishDate));
		}

		commerceInventoryWarehouseOrderTypeRelImpl.resetOriginalValues();

		return commerceInventoryWarehouseOrderTypeRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		commerceInventoryWarehouseOrderTypeRelId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		commerceInventoryWarehouseId = objectInput.readLong();

		commerceOrderTypeId = objectInput.readLong();

		priority = objectInput.readInt();
		lastPublishDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(commerceInventoryWarehouseOrderTypeRelId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		objectOutput.writeLong(commerceInventoryWarehouseId);

		objectOutput.writeLong(commerceOrderTypeId);

		objectOutput.writeInt(priority);
		objectOutput.writeLong(lastPublishDate);
	}

	public long mvccVersion;
	public String uuid;
	public long commerceInventoryWarehouseOrderTypeRelId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long commerceInventoryWarehouseId;
	public long commerceOrderTypeId;
	public int priority;
	public long lastPublishDate;

}