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
import com.liferay.headless.fulfilment.admin.client.serdes.v1_0.RequestSerDes;

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
public class Request implements Cloneable, Serializable {

	public static Request toDTO(String json) {
		return RequestSerDes.toDTO(json);
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

	public String getExternalReferenceCode() {
		return externalReferenceCode;
	}

	public void setExternalReferenceCode(String externalReferenceCode) {
		this.externalReferenceCode = externalReferenceCode;
	}

	public void setExternalReferenceCode(
		UnsafeSupplier<String, Exception> externalReferenceCodeUnsafeSupplier) {

		try {
			externalReferenceCode = externalReferenceCodeUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String externalReferenceCode;

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

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public void setReplyTo(
		UnsafeSupplier<String, Exception> replyToUnsafeSupplier) {

		try {
			replyTo = replyToUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String replyTo;

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

	public Task[] getTasks() {
		return tasks;
	}

	public void setTasks(Task[] tasks) {
		this.tasks = tasks;
	}

	public void setTasks(
		UnsafeSupplier<Task[], Exception> tasksUnsafeSupplier) {

		try {
			tasks = tasksUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Task[] tasks;

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

	public String getWorkflowDefinition() {
		return workflowDefinition;
	}

	public void setWorkflowDefinition(String workflowDefinition) {
		this.workflowDefinition = workflowDefinition;
	}

	public void setWorkflowDefinition(
		UnsafeSupplier<String, Exception> workflowDefinitionUnsafeSupplier) {

		try {
			workflowDefinition = workflowDefinitionUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected String workflowDefinition;

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
	public Request clone() throws CloneNotSupportedException {
		return (Request)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Request)) {
			return false;
		}

		Request request = (Request)object;

		return Objects.equals(toString(), request.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return RequestSerDes.toJSON(this);
	}

}