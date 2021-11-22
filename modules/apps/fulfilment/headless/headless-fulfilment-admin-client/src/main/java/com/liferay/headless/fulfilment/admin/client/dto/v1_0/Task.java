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

package com.liferay.headless.fulfilment.admin.client.dto.v1_0;

import com.liferay.headless.fulfilment.admin.client.function.UnsafeSupplier;
import com.liferay.headless.fulfilment.admin.client.serdes.v1_0.TaskSerDes;

import java.io.Serializable;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Generated;

/**
 * @author Riccardo Alberti
 * @generated
 */
@Generated("")
public class Task implements Cloneable, Serializable {

	public static Task toDTO(String json) {
		return TaskSerDes.toDTO(json);
	}

	public Map<String, Map<String, String>> getActions() {
		return actions;
	}

	public void setActions(Map<String, Map<String, String>> actions) {
		this.actions = actions;
	}

	public void setActions(
		UnsafeSupplier<Map<String, Map<String, String>>, Exception>
			actionsUnsafeSupplier) {

		try {
			actions = actionsUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Map<String, Map<String, String>> actions;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCreateDate(
		UnsafeSupplier<Date, Exception> createDateUnsafeSupplier) {

		try {
			createDate = createDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date createDate;

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setEndDate(
		UnsafeSupplier<Date, Exception> endDateUnsafeSupplier) {

		try {
			endDate = endDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date endDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setId(UnsafeSupplier<Long, Exception> idUnsafeSupplier) {
		try {
			id = idUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long id;

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public void setIndex(UnsafeSupplier<Long, Exception> indexUnsafeSupplier) {
		try {
			index = indexUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long index;

	public Parameter[] getInputParameters() {
		return inputParameters;
	}

	public void setInputParameters(Parameter[] inputParameters) {
		this.inputParameters = inputParameters;
	}

	public void setInputParameters(
		UnsafeSupplier<Parameter[], Exception> inputParametersUnsafeSupplier) {

		try {
			inputParameters = inputParametersUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Parameter[] inputParameters;

	public Parameter[] getOutputParameters() {
		return outputParameters;
	}

	public void setOutputParameters(Parameter[] outputParameters) {
		this.outputParameters = outputParameters;
	}

	public void setOutputParameters(
		UnsafeSupplier<Parameter[], Exception> outputParametersUnsafeSupplier) {

		try {
			outputParameters = outputParametersUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Parameter[] outputParameters;

	public Long getRequestId() {
		return requestId;
	}

	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	public void setRequestId(
		UnsafeSupplier<Long, Exception> requestIdUnsafeSupplier) {

		try {
			requestId = requestIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long requestId;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setStartDate(
		UnsafeSupplier<Date, Exception> startDateUnsafeSupplier) {

		try {
			startDate = startDateUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Date startDate;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setType(UnsafeSupplier<String, Exception> typeUnsafeSupplier) {
		try {
			type = typeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String type;

	public Status getWorkflowStatusInfo() {
		return workflowStatusInfo;
	}

	public void setWorkflowStatusInfo(Status workflowStatusInfo) {
		this.workflowStatusInfo = workflowStatusInfo;
	}

	public void setWorkflowStatusInfo(
		UnsafeSupplier<Status, Exception> workflowStatusInfoUnsafeSupplier) {

		try {
			workflowStatusInfo = workflowStatusInfoUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Status workflowStatusInfo;

	@Override
	public Task clone() throws CloneNotSupportedException {
		return (Task)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Task)) {
			return false;
		}

		Task task = (Task)object;

		return Objects.equals(toString(), task.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return TaskSerDes.toJSON(this);
	}

}