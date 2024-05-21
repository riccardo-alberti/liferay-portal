/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.object.lambda.rest.internal.resource.v1_0;

import com.liferay.object.lambda.rest.dto.v1_0.LambdaParameter;
import com.liferay.object.lambda.rest.dto.v1_0.LambdaRequest;
import com.liferay.object.lambda.rest.dto.v1_0.LambdaResponse;
import com.liferay.object.lambda.rest.resource.v1_0.LambdaResponseResource;
import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.model.ObjectEntry;
import com.liferay.object.scripting.executor.ObjectScriptingExecutor;
import com.liferay.object.service.ObjectDefinitionService;
import com.liferay.object.service.ObjectEntryService;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.vulcan.pagination.Page;
import com.liferay.portal.vulcan.pagination.Pagination;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import net.sf.jsqlparser.expression.Alias;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.SelectItem;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Riccardo Alberti
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/lambda-response.properties",
	scope = ServiceScope.PROTOTYPE, service = LambdaResponseResource.class
)
public class LambdaResponseResourceImpl extends BaseLambdaResponseResourceImpl {

	@Override
	public Page<LambdaResponse> postPerformIdPage(
			Long id, Pagination pagination, LambdaRequest lambdaRequest)
		throws Exception {

		ObjectDefinition objectDefinition =
			_objectDefinitionService.getObjectDefinitionByExternalReferenceCode(
				"L_LAMBDA", contextCompany.getCompanyId());

		ObjectEntry objectEntry = _objectEntryService.getObjectEntry(id);

		if (objectDefinition.getObjectDefinitionId() !=
				objectEntry.getObjectDefinitionId()) {

			throw new PortalException();
		}

		Map<String, Serializable> values = objectEntry.getValues();

		Boolean active = (Boolean)values.get("active");

		if (!active) {
			throw new PortalException(
				"No active query with id " + objectEntry.getObjectEntryId());
		}

		String body = (String)values.get("body");

		String lambdaType = (String)values.get("lambdaType");

		if (Objects.equals(lambdaType, "sql")) {
			return _getSQLLambdaResponsePage(body, lambdaRequest, pagination);
		}
		else if (Objects.equals(lambdaType, "function")) {
			return _getFunctionLambdaResponsePage(
				body, lambdaRequest, pagination);
		}

		throw new UnsupportedOperationException();
	}

	private Page<LambdaResponse> _getFunctionLambdaResponsePage(
			String body, LambdaRequest lambdaRequest, Pagination pagination)
		throws Exception {

		if (Validator.isBlank(body)) {
			return Page.of(Collections.emptyList());
		}

		LambdaResponse lambdaResponse = new LambdaResponse();

		lambdaResponse.setProperties(
			_objectScriptingExecutor.execute(
				_getInputObjects(lambdaRequest.getLambdaParameters()),
				_getOutputNames(lambdaRequest.getLambdaParameters()), body));

		return Page.of(Collections.singleton(lambdaResponse), pagination, 1);
	}

	private Map<String, Object> _getInputObjects(
		LambdaParameter[] lambdaParameters) {

		if (ArrayUtil.isEmpty(lambdaParameters)) {
			return Collections.emptyMap();
		}

		Map<String, Object> inputObjects = new HashMap<>();

		for (LambdaParameter lambdaParameter : lambdaParameters) {
			if (Objects.equals(
					lambdaParameter.getIo(), LambdaParameter.Io.IN)) {

				inputObjects.put(
					lambdaParameter.getName(), lambdaParameter.getValue());
			}
		}

		return inputObjects;
	}

	private LambdaResponse _getLambdaResponse(
			PlainSelect plainSelect, ResultSet resultSet)
		throws Exception {

		Map<String, Object> properties = new HashMap<>();

		for (SelectItem<?> selectItem : plainSelect.getSelectItems()) {
			String selectItemName = _getSelectItemName(selectItem);

			properties.put(selectItemName, resultSet.getObject(selectItemName));
		}

		LambdaResponse lambdaResponse = new LambdaResponse();

		lambdaResponse.setProperties(properties);

		return lambdaResponse;
	}

	private Set<String> _getOutputNames(LambdaParameter[] lambdaParameters) {
		if (ArrayUtil.isEmpty(lambdaParameters)) {
			return Collections.emptySet();
		}

		Set<String> outputNames = new HashSet<>();

		for (LambdaParameter lambdaParameter : lambdaParameters) {
			if (Objects.equals(
					lambdaParameter.getIo(), LambdaParameter.Io.OUT)) {

				outputNames.add(lambdaParameter.getName());
			}
		}

		return outputNames;
	}

	private String _getSelectItemName(SelectItem<?> selectItem) {
		Alias alias = selectItem.getAlias();

		if (alias != null) {
			return alias.getName();
		}

		return String.valueOf(selectItem.getExpression());
	}

	private Page<LambdaResponse> _getSQLLambdaResponsePage(
			String body, LambdaRequest lambdaRequest, Pagination pagination)
		throws Exception {

		PlainSelect plainSelect = (PlainSelect)CCJSqlParserUtil.parse(body);

		// TODO put the permission checks here before running the query

		Connection connection = DataAccess.getConnection();

		try (PreparedStatement preparedStatement = connection.prepareStatement(
				body)) {

			ParameterMetaData parameterMetaData =
				preparedStatement.getParameterMetaData();

			if (parameterMetaData.getParameterCount() > 0) {
				_setParameters(
					lambdaRequest.getLambdaParameters(), preparedStatement);
			}

			ResultSet resultSet = preparedStatement.executeQuery();

			int totalItems = 0;

			List<LambdaResponse> lambdaResponses = new ArrayList<>();

			while (resultSet.next()) {
				lambdaResponses.add(_getLambdaResponse(plainSelect, resultSet));
				totalItems = totalItems + 1;
			}

			return Page.of(lambdaResponses, pagination, totalItems);
		}
		catch (SQLException sqlException) {
			throw new SystemException(sqlException);
		}
	}

	private void _setParameters(
			LambdaParameter[] lambdaParameters,
			PreparedStatement preparedStatement)
		throws Exception {

		if (lambdaParameters == null) {
			return;
		}

		for (LambdaParameter lambdaParameter : lambdaParameters) {
			if (Objects.equals(
					lambdaParameter.getIo(), LambdaParameter.Io.IN)) {

				preparedStatement.setObject(
					lambdaParameter.getPosition(), lambdaParameter.getValue());
			}
		}
	}

	@Reference
	private ObjectDefinitionService _objectDefinitionService;

	@Reference
	private ObjectEntryService _objectEntryService;

	@Reference(target = "(scripting.language=groovy)")
	private ObjectScriptingExecutor _objectScriptingExecutor;

}