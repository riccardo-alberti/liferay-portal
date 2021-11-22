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

import com.liferay.fulfilment.model.FulfilmentRequest;
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
 * The cache model class for representing FulfilmentRequest in entity cache.
 *
 * @author Riccardo Alberti
 * @generated
 */
public class FulfilmentRequestCacheModel
	implements CacheModel<FulfilmentRequest>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof FulfilmentRequestCacheModel)) {
			return false;
		}

		FulfilmentRequestCacheModel fulfilmentRequestCacheModel =
			(FulfilmentRequestCacheModel)object;

		if ((fulfilmentRequestId ==
				fulfilmentRequestCacheModel.fulfilmentRequestId) &&
			(mvccVersion == fulfilmentRequestCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, fulfilmentRequestId);

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
		StringBundler sb = new StringBundler(41);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", externalReferenceCode=");
		sb.append(externalReferenceCode);
		sb.append(", fulfilmentRequestId=");
		sb.append(fulfilmentRequestId);
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
		sb.append(", endDate=");
		sb.append(endDate);
		sb.append(", originalFulfilmentRequest=");
		sb.append(originalFulfilmentRequest);
		sb.append(", inputParameters=");
		sb.append(inputParameters);
		sb.append(", outputParameters=");
		sb.append(outputParameters);
		sb.append(", replyTo=");
		sb.append(replyTo);
		sb.append(", startDate=");
		sb.append(startDate);
		sb.append(", type=");
		sb.append(type);
		sb.append(", workflowDefinitionLinkId=");
		sb.append(workflowDefinitionLinkId);
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
	public FulfilmentRequest toEntityModel() {
		FulfilmentRequestImpl fulfilmentRequestImpl =
			new FulfilmentRequestImpl();

		fulfilmentRequestImpl.setMvccVersion(mvccVersion);

		if (externalReferenceCode == null) {
			fulfilmentRequestImpl.setExternalReferenceCode("");
		}
		else {
			fulfilmentRequestImpl.setExternalReferenceCode(
				externalReferenceCode);
		}

		fulfilmentRequestImpl.setFulfilmentRequestId(fulfilmentRequestId);
		fulfilmentRequestImpl.setCompanyId(companyId);
		fulfilmentRequestImpl.setUserId(userId);

		if (userName == null) {
			fulfilmentRequestImpl.setUserName("");
		}
		else {
			fulfilmentRequestImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			fulfilmentRequestImpl.setCreateDate(null);
		}
		else {
			fulfilmentRequestImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			fulfilmentRequestImpl.setModifiedDate(null);
		}
		else {
			fulfilmentRequestImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (endDate == Long.MIN_VALUE) {
			fulfilmentRequestImpl.setEndDate(null);
		}
		else {
			fulfilmentRequestImpl.setEndDate(new Date(endDate));
		}

		if (originalFulfilmentRequest == null) {
			fulfilmentRequestImpl.setOriginalFulfilmentRequest("");
		}
		else {
			fulfilmentRequestImpl.setOriginalFulfilmentRequest(
				originalFulfilmentRequest);
		}

		if (inputParameters == null) {
			fulfilmentRequestImpl.setInputParameters("");
		}
		else {
			fulfilmentRequestImpl.setInputParameters(inputParameters);
		}

		if (outputParameters == null) {
			fulfilmentRequestImpl.setOutputParameters("");
		}
		else {
			fulfilmentRequestImpl.setOutputParameters(outputParameters);
		}

		if (replyTo == null) {
			fulfilmentRequestImpl.setReplyTo("");
		}
		else {
			fulfilmentRequestImpl.setReplyTo(replyTo);
		}

		if (startDate == Long.MIN_VALUE) {
			fulfilmentRequestImpl.setStartDate(null);
		}
		else {
			fulfilmentRequestImpl.setStartDate(new Date(startDate));
		}

		if (type == null) {
			fulfilmentRequestImpl.setType("");
		}
		else {
			fulfilmentRequestImpl.setType(type);
		}

		fulfilmentRequestImpl.setWorkflowDefinitionLinkId(
			workflowDefinitionLinkId);
		fulfilmentRequestImpl.setStatus(status);
		fulfilmentRequestImpl.setStatusByUserId(statusByUserId);

		if (statusByUserName == null) {
			fulfilmentRequestImpl.setStatusByUserName("");
		}
		else {
			fulfilmentRequestImpl.setStatusByUserName(statusByUserName);
		}

		if (statusDate == Long.MIN_VALUE) {
			fulfilmentRequestImpl.setStatusDate(null);
		}
		else {
			fulfilmentRequestImpl.setStatusDate(new Date(statusDate));
		}

		fulfilmentRequestImpl.resetOriginalValues();

		return fulfilmentRequestImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput)
		throws ClassNotFoundException, IOException {

		mvccVersion = objectInput.readLong();
		externalReferenceCode = objectInput.readUTF();

		fulfilmentRequestId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		endDate = objectInput.readLong();
		originalFulfilmentRequest = (String)objectInput.readObject();
		inputParameters = (String)objectInput.readObject();
		outputParameters = (String)objectInput.readObject();
		replyTo = objectInput.readUTF();
		startDate = objectInput.readLong();
		type = objectInput.readUTF();

		workflowDefinitionLinkId = objectInput.readLong();

		status = objectInput.readInt();

		statusByUserId = objectInput.readLong();
		statusByUserName = objectInput.readUTF();
		statusDate = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (externalReferenceCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(externalReferenceCode);
		}

		objectOutput.writeLong(fulfilmentRequestId);

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
		objectOutput.writeLong(endDate);

		if (originalFulfilmentRequest == null) {
			objectOutput.writeObject("");
		}
		else {
			objectOutput.writeObject(originalFulfilmentRequest);
		}

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

		if (replyTo == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(replyTo);
		}

		objectOutput.writeLong(startDate);

		if (type == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(type);
		}

		objectOutput.writeLong(workflowDefinitionLinkId);

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
	public String externalReferenceCode;
	public long fulfilmentRequestId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long endDate;
	public String originalFulfilmentRequest;
	public String inputParameters;
	public String outputParameters;
	public String replyTo;
	public long startDate;
	public String type;
	public long workflowDefinitionLinkId;
	public int status;
	public long statusByUserId;
	public String statusByUserName;
	public long statusDate;

}