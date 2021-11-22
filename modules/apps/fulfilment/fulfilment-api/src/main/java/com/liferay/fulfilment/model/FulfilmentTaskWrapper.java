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
 * This class is a wrapper for {@link FulfilmentTask}.
 * </p>
 *
 * @author Riccardo Alberti
 * @see FulfilmentTask
 * @generated
 */
public class FulfilmentTaskWrapper
	extends BaseModelWrapper<FulfilmentTask>
	implements FulfilmentTask, ModelWrapper<FulfilmentTask> {

	public FulfilmentTaskWrapper(FulfilmentTask fulfilmentTask) {
		super(fulfilmentTask);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("fulfilmentTaskId", getFulfilmentTaskId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("correlationId", getCorrelationId());
		attributes.put("endDate", getEndDate());
		attributes.put("fulfilmentRequestId", getFulfilmentRequestId());
		attributes.put("index", getIndex());
		attributes.put("inputParameters", getInputParameters());
		attributes.put("outputParameters", getOutputParameters());
		attributes.put("startDate", getStartDate());
		attributes.put("type", getType());
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

		Long fulfilmentTaskId = (Long)attributes.get("fulfilmentTaskId");

		if (fulfilmentTaskId != null) {
			setFulfilmentTaskId(fulfilmentTaskId);
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

		Long correlationId = (Long)attributes.get("correlationId");

		if (correlationId != null) {
			setCorrelationId(correlationId);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Long fulfilmentRequestId = (Long)attributes.get("fulfilmentRequestId");

		if (fulfilmentRequestId != null) {
			setFulfilmentRequestId(fulfilmentRequestId);
		}

		Long index = (Long)attributes.get("index");

		if (index != null) {
			setIndex(index);
		}

		String inputParameters = (String)attributes.get("inputParameters");

		if (inputParameters != null) {
			setInputParameters(inputParameters);
		}

		String outputParameters = (String)attributes.get("outputParameters");

		if (outputParameters != null) {
			setOutputParameters(outputParameters);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
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
	public FulfilmentTask cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this fulfilment task.
	 *
	 * @return the company ID of this fulfilment task
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the correlation ID of this fulfilment task.
	 *
	 * @return the correlation ID of this fulfilment task
	 */
	@Override
	public long getCorrelationId() {
		return model.getCorrelationId();
	}

	/**
	 * Returns the create date of this fulfilment task.
	 *
	 * @return the create date of this fulfilment task
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the end date of this fulfilment task.
	 *
	 * @return the end date of this fulfilment task
	 */
	@Override
	public Date getEndDate() {
		return model.getEndDate();
	}

	/**
	 * Returns the fulfilment request ID of this fulfilment task.
	 *
	 * @return the fulfilment request ID of this fulfilment task
	 */
	@Override
	public long getFulfilmentRequestId() {
		return model.getFulfilmentRequestId();
	}

	/**
	 * Returns the fulfilment task ID of this fulfilment task.
	 *
	 * @return the fulfilment task ID of this fulfilment task
	 */
	@Override
	public long getFulfilmentTaskId() {
		return model.getFulfilmentTaskId();
	}

	/**
	 * Returns the index of this fulfilment task.
	 *
	 * @return the index of this fulfilment task
	 */
	@Override
	public long getIndex() {
		return model.getIndex();
	}

	/**
	 * Returns the input parameters of this fulfilment task.
	 *
	 * @return the input parameters of this fulfilment task
	 */
	@Override
	public String getInputParameters() {
		return model.getInputParameters();
	}

	/**
	 * Returns the modified date of this fulfilment task.
	 *
	 * @return the modified date of this fulfilment task
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this fulfilment task.
	 *
	 * @return the mvcc version of this fulfilment task
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the output parameters of this fulfilment task.
	 *
	 * @return the output parameters of this fulfilment task
	 */
	@Override
	public String getOutputParameters() {
		return model.getOutputParameters();
	}

	/**
	 * Returns the primary key of this fulfilment task.
	 *
	 * @return the primary key of this fulfilment task
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the start date of this fulfilment task.
	 *
	 * @return the start date of this fulfilment task
	 */
	@Override
	public Date getStartDate() {
		return model.getStartDate();
	}

	/**
	 * Returns the status of this fulfilment task.
	 *
	 * @return the status of this fulfilment task
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the status by user ID of this fulfilment task.
	 *
	 * @return the status by user ID of this fulfilment task
	 */
	@Override
	public long getStatusByUserId() {
		return model.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this fulfilment task.
	 *
	 * @return the status by user name of this fulfilment task
	 */
	@Override
	public String getStatusByUserName() {
		return model.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this fulfilment task.
	 *
	 * @return the status by user uuid of this fulfilment task
	 */
	@Override
	public String getStatusByUserUuid() {
		return model.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this fulfilment task.
	 *
	 * @return the status date of this fulfilment task
	 */
	@Override
	public Date getStatusDate() {
		return model.getStatusDate();
	}

	/**
	 * Returns the type of this fulfilment task.
	 *
	 * @return the type of this fulfilment task
	 */
	@Override
	public String getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this fulfilment task.
	 *
	 * @return the user ID of this fulfilment task
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this fulfilment task.
	 *
	 * @return the user name of this fulfilment task
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this fulfilment task.
	 *
	 * @return the user uuid of this fulfilment task
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns <code>true</code> if this fulfilment task is approved.
	 *
	 * @return <code>true</code> if this fulfilment task is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return model.isApproved();
	}

	/**
	 * Returns <code>true</code> if this fulfilment task is denied.
	 *
	 * @return <code>true</code> if this fulfilment task is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return model.isDenied();
	}

	/**
	 * Returns <code>true</code> if this fulfilment task is a draft.
	 *
	 * @return <code>true</code> if this fulfilment task is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return model.isDraft();
	}

	/**
	 * Returns <code>true</code> if this fulfilment task is expired.
	 *
	 * @return <code>true</code> if this fulfilment task is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return model.isExpired();
	}

	/**
	 * Returns <code>true</code> if this fulfilment task is inactive.
	 *
	 * @return <code>true</code> if this fulfilment task is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return model.isInactive();
	}

	/**
	 * Returns <code>true</code> if this fulfilment task is incomplete.
	 *
	 * @return <code>true</code> if this fulfilment task is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return model.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this fulfilment task is pending.
	 *
	 * @return <code>true</code> if this fulfilment task is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return model.isPending();
	}

	/**
	 * Returns <code>true</code> if this fulfilment task is scheduled.
	 *
	 * @return <code>true</code> if this fulfilment task is scheduled; <code>false</code> otherwise
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
	 * Sets the company ID of this fulfilment task.
	 *
	 * @param companyId the company ID of this fulfilment task
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the correlation ID of this fulfilment task.
	 *
	 * @param correlationId the correlation ID of this fulfilment task
	 */
	@Override
	public void setCorrelationId(long correlationId) {
		model.setCorrelationId(correlationId);
	}

	/**
	 * Sets the create date of this fulfilment task.
	 *
	 * @param createDate the create date of this fulfilment task
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the end date of this fulfilment task.
	 *
	 * @param endDate the end date of this fulfilment task
	 */
	@Override
	public void setEndDate(Date endDate) {
		model.setEndDate(endDate);
	}

	/**
	 * Sets the fulfilment request ID of this fulfilment task.
	 *
	 * @param fulfilmentRequestId the fulfilment request ID of this fulfilment task
	 */
	@Override
	public void setFulfilmentRequestId(long fulfilmentRequestId) {
		model.setFulfilmentRequestId(fulfilmentRequestId);
	}

	/**
	 * Sets the fulfilment task ID of this fulfilment task.
	 *
	 * @param fulfilmentTaskId the fulfilment task ID of this fulfilment task
	 */
	@Override
	public void setFulfilmentTaskId(long fulfilmentTaskId) {
		model.setFulfilmentTaskId(fulfilmentTaskId);
	}

	/**
	 * Sets the index of this fulfilment task.
	 *
	 * @param index the index of this fulfilment task
	 */
	@Override
	public void setIndex(long index) {
		model.setIndex(index);
	}

	/**
	 * Sets the input parameters of this fulfilment task.
	 *
	 * @param inputParameters the input parameters of this fulfilment task
	 */
	@Override
	public void setInputParameters(String inputParameters) {
		model.setInputParameters(inputParameters);
	}

	/**
	 * Sets the modified date of this fulfilment task.
	 *
	 * @param modifiedDate the modified date of this fulfilment task
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this fulfilment task.
	 *
	 * @param mvccVersion the mvcc version of this fulfilment task
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the output parameters of this fulfilment task.
	 *
	 * @param outputParameters the output parameters of this fulfilment task
	 */
	@Override
	public void setOutputParameters(String outputParameters) {
		model.setOutputParameters(outputParameters);
	}

	/**
	 * Sets the primary key of this fulfilment task.
	 *
	 * @param primaryKey the primary key of this fulfilment task
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the start date of this fulfilment task.
	 *
	 * @param startDate the start date of this fulfilment task
	 */
	@Override
	public void setStartDate(Date startDate) {
		model.setStartDate(startDate);
	}

	/**
	 * Sets the status of this fulfilment task.
	 *
	 * @param status the status of this fulfilment task
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this fulfilment task.
	 *
	 * @param statusByUserId the status by user ID of this fulfilment task
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		model.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this fulfilment task.
	 *
	 * @param statusByUserName the status by user name of this fulfilment task
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		model.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this fulfilment task.
	 *
	 * @param statusByUserUuid the status by user uuid of this fulfilment task
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		model.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this fulfilment task.
	 *
	 * @param statusDate the status date of this fulfilment task
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		model.setStatusDate(statusDate);
	}

	/**
	 * Sets the type of this fulfilment task.
	 *
	 * @param type the type of this fulfilment task
	 */
	@Override
	public void setType(String type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this fulfilment task.
	 *
	 * @param userId the user ID of this fulfilment task
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this fulfilment task.
	 *
	 * @param userName the user name of this fulfilment task
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this fulfilment task.
	 *
	 * @param userUuid the user uuid of this fulfilment task
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected FulfilmentTaskWrapper wrap(FulfilmentTask fulfilmentTask) {
		return new FulfilmentTaskWrapper(fulfilmentTask);
	}

}