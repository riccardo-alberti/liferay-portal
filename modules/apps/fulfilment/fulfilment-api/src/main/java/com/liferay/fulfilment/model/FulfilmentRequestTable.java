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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Clob;
import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;FulfilmentRequest&quot; database table.
 *
 * @author Riccardo Alberti
 * @see FulfilmentRequest
 * @generated
 */
public class FulfilmentRequestTable extends BaseTable<FulfilmentRequestTable> {

	public static final FulfilmentRequestTable INSTANCE =
		new FulfilmentRequestTable();

	public final Column<FulfilmentRequestTable, Long> mvccVersion =
		createColumn(
			"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<FulfilmentRequestTable, String> externalReferenceCode =
		createColumn(
			"externalReferenceCode", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Long> fulfilmentRequestId =
		createColumn(
			"fulfilmentRequestId", Long.class, Types.BIGINT,
			Column.FLAG_PRIMARY);
	public final Column<FulfilmentRequestTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Date> modifiedDate =
		createColumn(
			"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Date> endDate = createColumn(
		"endDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Clob>
		originalFulfilmentRequest = createColumn(
			"originalFulfilmentRequest", Clob.class, Types.CLOB,
			Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Clob> inputParameters =
		createColumn(
			"inputParameters", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Clob> outputParameters =
		createColumn(
			"outputParameters", Clob.class, Types.CLOB, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, String> replyTo = createColumn(
		"replyTo", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Date> startDate = createColumn(
		"startDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, String> type = createColumn(
		"type_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Long> workflowDefinitionLinkId =
		createColumn(
			"workflowDefinitionLinkId", Long.class, Types.BIGINT,
			Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Integer> status = createColumn(
		"status", Integer.class, Types.INTEGER, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Long> statusByUserId =
		createColumn(
			"statusByUserId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, String> statusByUserName =
		createColumn(
			"statusByUserName", String.class, Types.VARCHAR,
			Column.FLAG_DEFAULT);
	public final Column<FulfilmentRequestTable, Date> statusDate = createColumn(
		"statusDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);

	private FulfilmentRequestTable() {
		super("FulfilmentRequest", FulfilmentRequestTable::new);
	}

}