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

package com.liferay.fulfilment.model.impl;

import com.liferay.fulfilment.model.FulfilmentTask;
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
 * The cache model class for representing FulfilmentTask in entity cache.
 *
 * @author Riccardo Alberti
 * @generated
 */
public class FulfilmentTaskCacheModel
	implements CacheModel<FulfilmentTask>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FulfilmentTaskCacheModel)) {
			return false;
		}

		FulfilmentTaskCacheModel fulfilmentTaskCacheModel =
			(FulfilmentTaskCacheModel)object;

		if ((fulfilmentTaskId == fulfilmentTaskCacheModel.fulfilmentTaskId) &&
			(mvccVersion == fulfilmentTaskCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, fulfilmentTaskId);

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
		StringBundler sb = new StringBundler(39);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", fulfilmentTaskId=");
		sb.append(fulfilmentTaskId);
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
		sb.append(", correlationId=");
		sb.append(correlationId);
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", fulfilmentRequestId=");
		sb.append(fulfilmentRequestId);
		sb.append(", index=");
		sb.append(index);
		sb.append(", inputParameters=");
		sb.append(inputParameters);
		sb.append(", outputParameters=");
		sb.append(outputParameters);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", type=");
		sb.append(type);
		sb.append(", status=");
		sb.append(status);
		sb.append(", statusByUserId=");
		sb.append(statusByUserId);
		sb.append(", statusByUserName=");
		sb.append(statusByUserName);
		sb.append(", statusDate=");
		sb.append(statusDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public FulfilmentTask toEntityModel() {
		FulfilmentTaskImpl fulfilmentTaskImpl = new FulfilmentTaskImpl();

		fulfilmentTaskImpl.setMvccVersion(mvccVersion);
		fulfilmentTaskImpl.setFulfilmentTaskId(fulfilmentTaskId);
		fulfilmentTaskImpl.setCompanyId(companyId);
		fulfilmentTaskImpl.setUserId(userId);

		if (userName == null) {
			fulfilmentTaskImpl.setUserName("");
		}
		else {
			fulfilmentTaskImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			fulfilmentTaskImpl.setCreateDate(null);
		}
		else {
			fulfilmentTaskImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			fulfilmentTaskImpl.setModifiedDate(null);
		}
		else {
			fulfilmentTaskImpl.setModifiedDate(new Date(modifiedDate));
		}

		fulfilmentTaskImpl.setCorrelationId(correlationId);

		if (endDate == Long.MIN_VALUE) {
			fulfilmentTaskImpl.setEndDate(null);
		}
		else {
			fulfilmentTaskImpl.setEndDate(new Date(endDate));
		}

		fulfilmentTaskImpl.setFulfilmentRequestId(fulfilmentRequestId);
		fulfilmentTaskImpl.setIndex(index);

		if (inputParameters == null) {
			fulfilmentTaskImpl.setInputParameters("");
		}
		else {
			fulfilmentTaskImpl.setInputParameters(inputParameters);
		}

		if (outputParameters == null) {
			fulfilmentTaskImpl.setOutputParameters("");
		}
		else {
			fulfilmentTaskImpl.setOutputParameters(outputParameters);
		}

		if (startDate == Long.MIN_VALUE) {
			fulfilmentTaskImpl.setStartDate(null);
		}
		else {
			fulfilmentTaskImpl.setStartDate(new Date(startDate));
		}

		if (type == null) {
			fulfilmentTaskImpl.setType("");
		}
		else {
			fulfilmentTaskImpl.setType(type);
		}

		fulfilmentTaskImpl.setStatus(status);
		fulfilmentTaskImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			fulfilmentTaskImpl.setStatusByUserName("");
		}
		else {
			fulfilmentTaskImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			fulfilmentTaskImpl.setStatusDate(null);
		}
		else {
			fulfilmentTaskImpl.setStatusDate(new Date(statusDate));
		}

		fulfilmentTaskImpl.resetOriginalValues();

		return fulfilmentTaskImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();

		fulfilmentTaskId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();

		correlationId = objectInput.readLong();
		endDate = objectInput.readLong();

		fulfilmentRequestId = objectInput.readLong();

		index = objectInput.readLong();
		inputParameters = (String)objectInput.readObject();
		outputParameters = (String)objectInput.readObject();
		startDate = objectInput.readLong();
		type = objectInput.readUTF();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		objectOutput.writeLong(fulfilmentTaskId);

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

		objectOutput.writeLong(correlationId);
		objectOutput.writeLong(endDate);

		objectOutput.writeLong(fulfilmentRequestId);

		objectOutput.writeLong(index);

		if (inputParameters == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(inputParameters);
		}

		if (outputParameters == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(outputParameters);
		}

		objectOutput.writeLong(startDate);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeInt(status);

		objectOutput.writeLong(statusByUserId);

		if (statusByUserName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(statusByUserName);
		}

		objectOutput.writeLong(statusDate);
	}

	public long mvccVersion;
	public long fulfilmentTaskId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long correlationId;
	public long endDate;
	public long fulfilmentRequestId;
	public long index;
	public String inputParameters;
	public String outputParameters;
	public long startDate;
	public String type;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}