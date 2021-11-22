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

package com.liferay.fulfilment.fact;

import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.util.FulfilmentParametersUtil;

import java.io.Serializable;

import java.util.Map;

/**
 * @author Riccardo Alberti
 */
public class FulfilmentRequestFact {

	public FulfilmentRequestFact(FulfilmentRequest fulfilmentRequest) {
		_fulfilmentRequestId = fulfilmentRequest.getFulfilmentRequestId();
		_type = fulfilmentRequest.getType();

		_parameters = FulfilmentParametersUtil.deserializeParameters(
			fulfilmentRequest.getInputParameters());

		_workflowDefinitionVersion = -1;
	}

	public long getFulfilmentRequestId() {
		return _fulfilmentRequestId;
	}

	public Serializable getParameter(String parameterName) {
		return _parameters.get(parameterName);
	}

	public String getType() {
		return _type;
	}

	public String getWorkflowDefinitionName() {
		return _workflowDefinitionName;
	}

	public int getWorkflowDefinitionVersion() {
		return _workflowDefinitionVersion;
	}

	public void setWorkflowDefinitionName(String workflowDefinitionName) {
		_workflowDefinitionName = workflowDefinitionName;
	}

	public void setWorkflowDefinitionVersion(int workflowDefinitionVersion) {
		_workflowDefinitionVersion = workflowDefinitionVersion;
	}

	private final long _fulfilmentRequestId;
	private final Map<String, Serializable> _parameters;
	private final String _type;
	private String _workflowDefinitionName;
	private int _workflowDefinitionVersion;

}