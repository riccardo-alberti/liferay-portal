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

package com.liferay.fulfilment.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FulfilmentRequest}.
 * </p>
 *
 * @author Riccardo Alberti
 * @see FulfilmentRequest
 * @generated
 */
public class FulfilmentRequestWrapper
	extends BaseModelWrapper<FulfilmentRequest>
	implements FulfilmentRequest, ModelWrapper<FulfilmentRequest> {

	public FulfilmentRequestWrapper(FulfilmentRequest fulfilmentRequest) {
		super(fulfilmentRequest);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put("fulfilmentRequestId", getFulfilmentRequestId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("endDate", getEndDate());
		attributes.put(
			"originalFulfilmentRequest", getOriginalFulfilmentRequest());
		attributes.put("inputParameters", getInputParameters());
		attributes.put("outputParameters", getOutputParameters());
		attributes.put("replyTo", getReplyTo());
		attributes.put("startDate", getStartDate());
		attributes.put("type", getType());
		attributes.put(
			"workflowDefinitionLinkId", getWorkflowDefinitionLinkId());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String externalReferenceCode = (String)attributes.get(
			"externalReferenceCode");

		if (externalReferenceCode != null) {
			setExternalReferenceCode(externalReferenceCode);
		}

		Long fulfilmentRequestId = (Long)attributes.get("fulfilmentRequestId");

		if (fulfilmentRequestId != null) {
			setFulfilmentRequestId(fulfilmentRequestId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		String originalFulfilmentRequest = (String)attributes.get(
			"originalFulfilmentRequest");

		if (originalFulfilmentRequest != null) {
			setOriginalFulfilmentRequest(originalFulfilmentRequest);
		}

		String inputParameters = (String)attributes.get("inputParameters");

		if (inputParameters != null) {
			setInputParameters(inputParameters);
		}

		String outputParameters = (String)attributes.get("outputParameters");

		if (outputParameters != null) {
			setOutputParameters(outputParameters);
		}

		String replyTo = (String)attributes.get("replyTo");

		if (replyTo != null) {
			setReplyTo(replyTo);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Long workflowDefinitionLinkId = (Long)attributes.get(
			"workflowDefinitionLinkId");

		if (workflowDefinitionLinkId != null) {
			setWorkflowDefinitionLinkId(workflowDefinitionLinkId);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	@Override
	public FulfilmentRequest cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this fulfilment request.
	 *
	 * @return the company ID of this fulfilment request
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this fulfilment request.
	 *
	 * @return the create date of this fulfilment request
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the end date of this fulfilment request.
	 *
	 * @return the end date of this fulfilment request
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the external reference code of this fulfilment request.
	 *
	 * @return the external reference code of this fulfilment request
	 */
	@Override
	public String getExternalReferenceCode() {
		return model.getExternalReferenceCode();
	}

	/**
	 * Returns the fulfilment request ID of this fulfilment request.
	 *
	 * @return the fulfilment request ID of this fulfilment request
	 */
	@Override
	public long getFulfilmentRequestId() {
		return model.getFulfilmentRequestId();
	}

	/**
	 * Returns the input parameters of this fulfilment request.
	 *
	 * @return the input parameters of this fulfilment request
	 */
	@Override
	public String getInputParameters() {
		return model.getInputParameters();
	}

	/**
	 * Returns the modified date of this fulfilment request.
	 *
	 * @return the modified date of this fulfilment request
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this fulfilment request.
	 *
	 * @return the mvcc version of this fulfilment request
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the original fulfilment request of this fulfilment request.
	 *
	 * @return the original fulfilment request of this fulfilment request
	 */
	@Override
	public String getOriginalFulfilmentRequest() {
		return model.getOriginalFulfilmentRequest();
	}

	/**
	 * Returns the output parameters of this fulfilment request.
	 *
	 * @return the output parameters of this fulfilment request
	 */
	@Override
	public String getOutputParameters() {
		return model.getOutputParameters();
	}

	/**
	 * Returns the primary key of this fulfilment request.
	 *
	 * @return the primary key of this fulfilment request
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the reply to of this fulfilment request.
	 *
	 * @return the reply to of this fulfilment request
	 */
	@Override
	public String getReplyTo() {
		return model.getReplyTo();
	}

	/**
	 * Returns the start date of this fulfilment request.
	 *
	 * @return the start date of this fulfilment request
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the status of this fulfilment request.
	 *
	 * @return the status of this fulfilment request
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the status by user ID of this fulfilment request.
	 *
	 * @return the status by user ID of this fulfilment request
	 */
	@Override
	public long getStatusByUserId() {
		return model.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this fulfilment request.
	 *
	 * @return the status by user name of this fulfilment request
	 */
	@Override
	public String getStatusByUserName() {
		return model.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this fulfilment request.
	 *
	 * @return the status by user uuid of this fulfilment request
	 */
	@Override
	public String getStatusByUserUuid() {
		return model.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this fulfilment request.
	 *
	 * @return the status date of this fulfilment request
	 */
	@Override
	public Date getStatusDate() {
		return model.getStatusDate();
	}

	/**
	 * Returns the type of this fulfilment request.
	 *
	 * @return the type of this fulfilment request
	 */
	@Override
	public String getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this fulfilment request.
	 *
	 * @return the user ID of this fulfilment request
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this fulfilment request.
	 *
	 * @return the user name of this fulfilment request
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this fulfilment request.
	 *
	 * @return the user uuid of this fulfilment request
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the workflow definition link ID of this fulfilment request.
	 *
	 * @return the workflow definition link ID of this fulfilment request
	 */
	@Override
	public long getWorkflowDefinitionLinkId() {
		return model.getWorkflowDefinitionLinkId();
	}

	/**
	 * Returns <code>true</code> if this fulfilment request is approved.
	 *
	 * @return <code>true</code> if this fulfilment request is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return model.isApproved();
	}

	/**
	 * Returns <code>true</code> if this fulfilment request is denied.
	 *
	 * @return <code>true</code> if this fulfilment request is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return model.isDenied();
	}

	/**
	 * Returns <code>true</code> if this fulfilment request is a draft.
	 *
	 * @return <code>true</code> if this fulfilment request is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return model.isDraft();
	}

	/**
	 * Returns <code>true</code> if this fulfilment request is expired.
	 *
	 * @return <code>true</code> if this fulfilment request is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return model.isExpired();
	}

	/**
	 * Returns <code>true</code> if this fulfilment request is inactive.
	 *
	 * @return <code>true</code> if this fulfilment request is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return model.isInactive();
	}

	/**
	 * Returns <code>true</code> if this fulfilment request is incomplete.
	 *
	 * @return <code>true</code> if this fulfilment request is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return model.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this fulfilment request is pending.
	 *
	 * @return <code>true</code> if this fulfilment request is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return model.isPending();
	}

	/**
	 * Returns <code>true</code> if this fulfilment request is scheduled.
	 *
	 * @return <code>true</code> if this fulfilment request is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return model.isScheduled();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this fulfilment request.
	 *
	 * @param companyId the company ID of this fulfilment request
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this fulfilment request.
	 *
	 * @param createDate the create date of this fulfilment request
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the end date of this fulfilment request.
	 *
	 * @param endDate the end date of this fulfilment request
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the external reference code of this fulfilment request.
	 *
	 * @param externalReferenceCode the external reference code of this fulfilment request
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		model.setExternalReferenceCode(externalReferenceCode);
	}

	/**
	 * Sets the fulfilment request ID of this fulfilment request.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID of this fulfilment request
	 */
	@Override
	public void setFulfilmentRequestId(long fulfilmentRequestId) {
		model.setFulfilmentRequestId(fulfilmentRequestId);
	}

	/**
	 * Sets the input parameters of this fulfilment request.
	 *
	 * @param inputParameters the input parameters of this fulfilment request
	 */
	@Override
	public void setInputParameters(String inputParameters) {
		model.setInputParameters(inputParameters);
	}

	/**
	 * Sets the modified date of this fulfilment request.
	 *
	 * @param modifiedDate the modified date of this fulfilment request
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this fulfilment request.
	 *
	 * @param mvccVersion the mvcc version of this fulfilment request
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the original fulfilment request of this fulfilment request.
	 *
	 * @param originalFulfilmentRequest the original fulfilment request of this fulfilment request
	 */
	@Override
	public void setOriginalFulfilmentRequest(String originalFulfilmentRequest) {
		model.setOriginalFulfilmentRequest(originalFulfilmentRequest);
	}

	/**
	 * Sets the output parameters of this fulfilment request.
	 *
	 * @param outputParameters the output parameters of this fulfilment request
	 */
	@Override
	public void setOutputParameters(String outputParameters) {
		model.setOutputParameters(outputParameters);
	}

	/**
	 * Sets the primary key of this fulfilment request.
	 *
	 * @param primaryKey the primary key of this fulfilment request
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the reply to of this fulfilment request.
	 *
	 * @param replyTo the reply to of this fulfilment request
	 */
	@Override
	public void setReplyTo(String replyTo) {
		model.setReplyTo(replyTo);
	}

	/**
	 * Sets the start date of this fulfilment request.
	 *
	 * @param startDate the start date of this fulfilment request
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the status of this fulfilment request.
	 *
	 * @param status the status of this fulfilment request
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this fulfilment request.
	 *
	 * @param statusByUserId the status by user ID of this fulfilment request
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		model.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this fulfilment request.
	 *
	 * @param statusByUserName the status by user name of this fulfilment request
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		model.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this fulfilment request.
	 *
	 * @param statusByUserUuid the status by user uuid of this fulfilment request
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		model.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this fulfilment request.
	 *
	 * @param statusDate the status date of this fulfilment request
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		model.setStatusDate(statusDate);
	}

	/**
	 * Sets the type of this fulfilment request.
	 *
	 * @param type the type of this fulfilment request
	 */
	@Override
	public void setType(String type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this fulfilment request.
	 *
	 * @param userId the user ID of this fulfilment request
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this fulfilment request.
	 *
	 * @param userName the user name of this fulfilment request
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this fulfilment request.
	 *
	 * @param userUuid the user uuid of this fulfilment request
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the workflow definition link ID of this fulfilment request.
	 *
	 * @param workflowDefinitionLinkId the workflow definition link ID of this fulfilment request
	 */
	@Override
	public void setWorkflowDefinitionLinkId(long workflowDefinitionLinkId) {
		model.setWorkflowDefinitionLinkId(workflowDefinitionLinkId);
	}

	@Override
	protected FulfilmentRequestWrapper wrap(
		FulfilmentRequest fulfilmentRequest) {

		return new FulfilmentRequestWrapper(fulfilmentRequest);
	}

}