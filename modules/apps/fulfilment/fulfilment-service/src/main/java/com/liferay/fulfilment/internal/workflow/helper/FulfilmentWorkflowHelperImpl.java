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

package com.liferay.fulfilment.internal.workflow.helper;

import com.liferay.fulfilment.configuration.FulfilmentRequestConfiguration;
import com.liferay.fulfilment.fact.FulfilmentRequestFact;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.FulfilmentRequestLocalService;
import com.liferay.fulfilment.workflow.helper.FulfilmentWorkflowHelper;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.WorkflowDefinitionLink;
import com.liferay.portal.kernel.resource.StringResourceRetriever;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.WorkflowException;
import com.liferay.portal.rules.engine.Fact;
import com.liferay.portal.rules.engine.Query;
import com.liferay.portal.rules.engine.RulesEngine;
import com.liferay.portal.rules.engine.RulesResourceRetriever;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Riccardo Alberti
 */
@Component(
	configurationPid = "com.liferay.fulfilment.configuration.FulfilmentRequestConfiguration",
	service = FulfilmentWorkflowHelper.class
)
public class FulfilmentWorkflowHelperImpl implements FulfilmentWorkflowHelper {

	@Override
	public WorkflowDefinitionLink getWorkflowDefinitionLink(
			long companyId, long groupId, long classPK)
		throws PortalException {

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestLocalService.getFulfilmentRequest(classPK);

		if (fulfilmentRequest.getWorkflowDefinitionLinkId() > 0) {
			return _workflowDefinitionLinkLocalService.
				getWorkflowDefinitionLink(
					fulfilmentRequest.getWorkflowDefinitionLinkId());
		}

		if (Validator.isBlank(_fulfilmentRequestConfiguration.logicRule())) {
			return null;
		}

		FulfilmentRequestFact fulfilmentRequestFact = new FulfilmentRequestFact(
			fulfilmentRequest);

		_rulesEngine.execute(
			new RulesResourceRetriever(
				new StringResourceRetriever(
					_fulfilmentRequestConfiguration.logicRule())),
			Arrays.asList(
				new Fact<FulfilmentRequestFact>(
					"request", fulfilmentRequestFact)),
			Query.createStandardQuery());

		if (Validator.isBlank(
				fulfilmentRequestFact.getWorkflowDefinitionName())) {

			return null;
		}

		WorkflowDefinitionLink workflowDefinitionLink =
			_getWorkflowDefinitionLink(
				companyId, fulfilmentRequestFact.getWorkflowDefinitionName(),
				fulfilmentRequestFact.getWorkflowDefinitionVersion());

		if (workflowDefinitionLink == null) {
			workflowDefinitionLink = _updateWorkflowDefinitionLink(
				companyId, fulfilmentRequest.getUserId(),
				fulfilmentRequestFact.getWorkflowDefinitionName(),
				fulfilmentRequestFact.getWorkflowDefinitionVersion());
		}

		if (workflowDefinitionLink != null) {
			_fulfilmentRequestLocalService.updateWorkflowDefinitionLink(
				classPK, workflowDefinitionLink.getWorkflowDefinitionLinkId());
		}

		return workflowDefinitionLink;
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_fulfilmentRequestConfiguration = ConfigurableUtil.createConfigurable(
			FulfilmentRequestConfiguration.class, properties);
	}

	private WorkflowDefinitionLink _getWorkflowDefinitionLink(
			long companyId, String workflowDefinitionName,
			int workflowDefinitionVersion)
		throws PortalException {

		List<WorkflowDefinitionLink> workflowDefinitionLinks =
			_workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLinks(
				companyId, 0, FulfilmentRequest.class.getName(), 0);

		for (WorkflowDefinitionLink workflowDefinitionLink :
				workflowDefinitionLinks) {

			if (Objects.equals(
					workflowDefinitionName,
					workflowDefinitionLink.getWorkflowDefinitionName())) {

				if (workflowDefinitionVersion < 0) {
					WorkflowDefinition latestWorkflowDefinition =
						_workflowDefinitionManager.getLatestWorkflowDefinition(
							companyId, workflowDefinitionName);

					workflowDefinitionVersion =
						latestWorkflowDefinition.getVersion();
				}

				if (workflowDefinitionVersion ==
						workflowDefinitionLink.getWorkflowDefinitionVersion()) {

					return workflowDefinitionLink;
				}
			}
		}

		return null;
	}

	private WorkflowDefinitionLink _updateWorkflowDefinitionLink(
			long companyId, long userId, String workflowDefinitionName,
			int workflowDefinitionVersion)
		throws PortalException {

		try {
			WorkflowDefinition workflowDefinition =
				_workflowDefinitionManager.getLatestWorkflowDefinition(
					companyId, workflowDefinitionName);

			if (workflowDefinitionVersion >= 0) {
				workflowDefinition =
					_workflowDefinitionManager.getWorkflowDefinition(
						companyId, workflowDefinitionName,
						workflowDefinitionVersion);
			}
			else {
				workflowDefinitionVersion = workflowDefinition.getVersion();
			}

			if (!workflowDefinition.isActive()) {
				return null;
			}
		}
		catch (WorkflowException workflowException) {
			if (_log.isInfoEnabled()) {
				_log.info(
					StringBundler.concat(
						"No workflow definition exists with name ",
						workflowDefinitionName, " and version ",
						workflowDefinitionVersion),
					workflowException);
			}

			return null;
		}

		List<ObjectValuePair<Long, String>> workflowDefinitionOVPs =
			new ArrayList<>(1);

		long typePK = System.nanoTime();

		workflowDefinitionOVPs.add(
			new ObjectValuePair<>(
				typePK,
				workflowDefinitionName + StringPool.AT +
					workflowDefinitionVersion));

		_workflowDefinitionLinkLocalService.updateWorkflowDefinitionLinks(
			userId, companyId, 0, FulfilmentRequest.class.getName(), 0,
			workflowDefinitionOVPs);

		return _workflowDefinitionLinkLocalService.fetchWorkflowDefinitionLink(
			companyId, 0, FulfilmentRequest.class.getName(), 0, typePK);
	}

	private static final Log _log = LogFactoryUtil.getLog(
		FulfilmentWorkflowHelperImpl.class);

	private volatile FulfilmentRequestConfiguration
		_fulfilmentRequestConfiguration;

	@Reference
	private FulfilmentRequestLocalService _fulfilmentRequestLocalService;

	@Reference
	private RulesEngine _rulesEngine;

	@Reference
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

	@Reference
	private WorkflowDefinitionManager _workflowDefinitionManager;

}