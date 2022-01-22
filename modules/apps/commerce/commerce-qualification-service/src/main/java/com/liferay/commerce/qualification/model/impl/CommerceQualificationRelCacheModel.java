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

package com.liferay.commerce.qualification.model.impl;

import com.liferay.commerce.qualification.model.CommerceQualificationRel;
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
 * The cache model class for representing CommerceQualificationRel in entity cache.
 *
 * @author Riccardo Alberti
 * @generated
 */
public class CommerceQualificationRelCacheModel
	implements CacheModel<CommerceQualificationRel>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof CommerceQualificationRelCacheModel)) {
			return false;
		}

		CommerceQualificationRelCacheModel commerceQualificationRelCacheModel =
			(CommerceQualificationRelCacheModel)object;

		if ((commerceQualificationRelId ==
				commerceQualificationRelCacheModel.
					commerceQualificationRelId) &&
			(mvccVersion == commerceQualificationRelCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, commerceQualificationRelId);

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
		StringBundler sb = new StringBundler(23);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", commerceQualificationRelId=");
		sb.append(commerceQualificationRelId);
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
		sb.append(", sourceClassNameId=");
		sb.append(sourceClassNameId);
		sb.append(", sourceClassPK=");
		sb.append(sourceClassPK);
		sb.append(", targetClassNameId=");
		sb.append(targetClassNameId);
		sb.append(", targetClassPK=");
		sb.append(targetClassPK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public CommerceQualificationRel toEntityModel() {
		CommerceQualificationRelImpl commerceQualificationRelImpl =
			new CommerceQualificationRelImpl();

		commerceQualificationRelImpl.setMvccVersion(mvccVersion);
		commerceQualificationRelImpl.setCommerceQualificationRelId(
			commerceQualificationRelId);
		commerceQualificationRelImpl.setCompanyId(companyId);
		commerceQualificationRelImpl.setUserId(userId);

		if (userName == null) {
			commerceQualificationRelImpl.setUserName("");
		}
		else {
			commerceQualificationRelImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			commerceQualificationRelImpl.setCreateDate(null);
		}
		else {
			commerceQualificationRelImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			commerceQualificationRelImpl.setModifiedDate(null);
		}
		else {
			commerceQualificationRelImpl.setModifiedDate(
				new Date(modifiedDate));
		}

		commerceQualificationRelImpl.setSourceClassNameId(sourceClassNameId);
		commerceQualificationRelImpl.setSourceClassPK(sourceClassPK);
		commerceQualificationRelImpl.setTargetClassNameId(targetClassNameId);
		commerceQualificationRelImpl.setTargetClassPK(targetClassPK);

		commerceQualificationRelImpl.resetOriginalValues();

		return commerceQualificationRelImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();

		commerceQualificationRelId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		sourceClassNameId = objectInput.readLong();

		sourceClassPK = objectInput.readLong();

		targetClassNameId = objectInput.readLong();

		targetClassPK = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(commerceQualificationRelId);

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

		objectOutput.writeLong(sourceClassNameId);

		objectOutput.writeLong(sourceClassPK);

		objectOutput.writeLong(targetClassNameId);

		objectOutput.writeLong(targetClassPK);
	}

	public long mvccVersion;
	public long commerceQualificationRelId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long sourceClassNameId;
	public long sourceClassPK;
	public long targetClassNameId;
	public long targetClassPK;

}