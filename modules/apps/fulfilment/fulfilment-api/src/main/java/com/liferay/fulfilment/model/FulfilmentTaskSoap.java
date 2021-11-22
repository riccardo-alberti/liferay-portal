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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.fulfilment.service.http.FulfilmentTaskServiceSoap}.
 *
 * @author Riccardo Alberti
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class FulfilmentTaskSoap implements Serializable {

	public static FulfilmentTaskSoap toSoapModel(FulfilmentTask model) {
		FulfilmentTaskSoap soapModel = new FulfilmentTaskSoap();

		soapModel.setMvccVersion(model.getMvccVersion());
		soapModel.setFulfilmentTaskId(model.getFulfilmentTaskId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCorrelationId(model.getCorrelationId());
		soapModel.setEndDate(model.getEndDate());
		soapModel.setFulfilmentRequestId(model.getFulfilmentRequestId());
		soapModel.setIndex(model.getIndex());
		soapModel.setInputParameters(model.getInputParameters());
		soapModel.setOutputParameters(model.getOutputParameters());
		soapModel.setStartDate(model.getStartDate());
		soapModel.setType(model.getType());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static FulfilmentTaskSoap[] toSoapModels(FulfilmentTask[] models) {
		FulfilmentTaskSoap[] soapModels = new FulfilmentTaskSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FulfilmentTaskSoap[][] toSoapModels(
		FulfilmentTask[][] models) {

		FulfilmentTaskSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels =
				new FulfilmentTaskSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FulfilmentTaskSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FulfilmentTaskSoap[] toSoapModels(
		List<FulfilmentTask> models) {

		List<FulfilmentTaskSoap> soapModels = new ArrayList<FulfilmentTaskSoap>(
			models.size());

		for (FulfilmentTask model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FulfilmentTaskSoap[soapModels.size()]);
	}

	public FulfilmentTaskSoap() {
	}

	public long getPrimaryKey() {
		return _fulfilmentTaskId;
	}

	public void setPrimaryKey(long pk) {
		setFulfilmentTaskId(pk);
	}

	public long getMvccVersion() {
		return _mvccVersion;
	}

	public void setMvccVersion(long mvccVersion) {
		_mvccVersion = mvccVersion;
	}

	public long getFulfilmentTaskId() {
		return _fulfilmentTaskId;
	}

	public void setFulfilmentTaskId(long fulfilmentTaskId) {
		_fulfilmentTaskId = fulfilmentTaskId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getCorrelationId() {
		return _correlationId;
	}

	public void setCorrelationId(long correlationId) {
		_correlationId = correlationId;
	}

	public Date getEndDate() {
		return _endDate;
	}

	public void setEndDate(Date endDate) {
		_endDate = endDate;
	}

	public long getFulfilmentRequestId() {
		return _fulfilmentRequestId;
	}

	public void setFulfilmentRequestId(long fulfilmentRequestId) {
		_fulfilmentRequestId = fulfilmentRequestId;
	}

	public long getIndex() {
		return _index;
	}

	public void setIndex(long index) {
		_index = index;
	}

	public String getInputParameters() {
		return _inputParameters;
	}

	public void setInputParameters(String inputParameters) {
		_inputParameters = inputParameters;
	}

	public String getOutputParameters() {
		return _outputParameters;
	}

	public void setOutputParameters(String outputParameters) {
		_outputParameters = outputParameters;
	}

	public Date getStartDate() {
		return _startDate;
	}

	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}

	public String getType() {
		return _type;
	}

	public void setType(String type) {
		_type = type;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	private long _mvccVersion;
	private long _fulfilmentTaskId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _correlationId;
	private Date _endDate;
	private long _fulfilmentRequestId;
	private long _index;
	private String _inputParameters;
	private String _outputParameters;
	private Date _startDate;
	private String _type;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;

}