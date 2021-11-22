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

package com.liferay.fulfilment.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.fulfilment.model.FulfilmentRequest;
import com.liferay.fulfilment.service.FulfilmentRequestLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.configuration.test.util.ConfigurationTestUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.WorkflowInstanceLink;
import com.liferay.portal.kernel.service.WorkflowDefinitionLinkLocalService;
import com.liferay.portal.kernel.service.WorkflowInstanceLinkLocalService;
import com.liferay.portal.kernel.settings.SettingsFactory;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DataGuard;
import com.liferay.portal.kernel.test.rule.Sync;
import com.liferay.portal.kernel.test.rule.SynchronousDestinationTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.test.util.ServiceContextTestUtil;
import com.liferay.portal.kernel.test.util.TestPropsValues;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.workflow.WorkflowDefinition;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.kernel.workflow.WorkflowInstance;
import com.liferay.portal.kernel.workflow.WorkflowInstanceManager;
import com.liferay.portal.rules.engine.RulesEngine;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PermissionCheckerMethodTestRule;

import java.io.InputStream;

import java.util.Dictionary;
import java.util.Hashtable;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Riccardo Alberti
 */
@DataGuard(scope = DataGuard.Scope.NONE)
@RunWith(Arquillian.class)
@Sync
public class FulfilmentTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new AggregateTestRule(
			new LiferayIntegrationTestRule(),
			PermissionCheckerMethodTestRule.INSTANCE,
			SynchronousDestinationTestRule.INSTANCE);

	@Before
	public void setUp() throws Exception {
		_user = TestPropsValues.getUser();

		_workflowDefinition = _saveWorkflowDefinition();

		_rule = read("test.drl");

		_rule = _rule.replaceAll(
			"##workflowDefinitionName##", _workflowDefinition.getName());

		_properties = new Hashtable<>();

		_properties.put("logicRule", _rule);

		ConfigurationTestUtil.saveConfiguration(_PID, _properties);
	}

	@After
	public void tearDown() throws Exception {
		_properties.put("logicRule", StringPool.BLANK);

		ConfigurationTestUtil.saveConfiguration(_PID, _properties);
	}

	@Test
	public void testWorkflow() throws Exception {
		String content = _workflowDefinition.getContent();

		_workflowDefinition =
			_workflowDefinitionManager.deployWorkflowDefinition(
				TestPropsValues.getCompanyId(), _workflowDefinition.getUserId(),
				_workflowDefinition.getTitle(), _workflowDefinition.getName(),
				content.getBytes());

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestLocalService.addFulfilmentRequest(
				RandomTestUtil.randomString(), _user.getUserId(),
				StringPool.BLANK, null, "internal", "test",
				ServiceContextTestUtil.getServiceContext());

		fulfilmentRequest =
			_fulfilmentRequestLocalService.startWorkflowInstance(
				_user.getUserId(), fulfilmentRequest,
				ServiceContextTestUtil.getServiceContext());

		WorkflowInstanceLink workflowInstanceLink =
			_workflowInstanceLinkLocalService.fetchWorkflowInstanceLink(
				TestPropsValues.getCompanyId(), 0,
				FulfilmentRequest.class.getName(),
				fulfilmentRequest.getFulfilmentRequestId());

		WorkflowInstance workflowInstance =
			_workflowInstanceManager.getWorkflowInstance(
				TestPropsValues.getCompanyId(),
				workflowInstanceLink.getWorkflowInstanceId());

		Assert.assertTrue(workflowInstance.isComplete());
	}

	@Test
	public void testWorkflowWhenDefinitionIsNotDeployed() throws Exception {
		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestLocalService.addFulfilmentRequest(
				RandomTestUtil.randomString(), _user.getUserId(),
				StringPool.BLANK, null, "internal", "test",
				ServiceContextTestUtil.getServiceContext());

		fulfilmentRequest =
			_fulfilmentRequestLocalService.startWorkflowInstance(
				_user.getUserId(), fulfilmentRequest,
				ServiceContextTestUtil.getServiceContext());

		WorkflowInstanceLink workflowInstanceLink =
			_workflowInstanceLinkLocalService.fetchWorkflowInstanceLink(
				TestPropsValues.getCompanyId(), 0,
				FulfilmentRequest.class.getName(),
				fulfilmentRequest.getFulfilmentRequestId());

		Assert.assertNull(workflowInstanceLink);
	}

	@Test
	public void testWorkflowWhenLinkIsAlreadyPresent() throws Exception {
		String content = _workflowDefinition.getContent();

		_workflowDefinition =
			_workflowDefinitionManager.deployWorkflowDefinition(
				TestPropsValues.getCompanyId(), _workflowDefinition.getUserId(),
				_workflowDefinition.getTitle(), _workflowDefinition.getName(),
				content.getBytes());

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestLocalService.addFulfilmentRequest(
				RandomTestUtil.randomString(), _user.getUserId(),
				StringPool.BLANK, null, "internal", "test",
				ServiceContextTestUtil.getServiceContext());

		fulfilmentRequest =
			_fulfilmentRequestLocalService.startWorkflowInstance(
				_user.getUserId(), fulfilmentRequest,
				ServiceContextTestUtil.getServiceContext());

		WorkflowInstanceLink workflowInstanceLink =
			_workflowInstanceLinkLocalService.fetchWorkflowInstanceLink(
				TestPropsValues.getCompanyId(), 0,
				FulfilmentRequest.class.getName(),
				fulfilmentRequest.getFulfilmentRequestId());

		WorkflowInstance workflowInstance =
			_workflowInstanceManager.getWorkflowInstance(
				TestPropsValues.getCompanyId(),
				workflowInstanceLink.getWorkflowInstanceId());

		Assert.assertTrue(workflowInstance.isComplete());

		fulfilmentRequest = _fulfilmentRequestLocalService.addFulfilmentRequest(
			RandomTestUtil.randomString(), _user.getUserId(), StringPool.BLANK,
			null, "internal", "test",
			ServiceContextTestUtil.getServiceContext());

		fulfilmentRequest =
			_fulfilmentRequestLocalService.startWorkflowInstance(
				_user.getUserId(), fulfilmentRequest,
				ServiceContextTestUtil.getServiceContext());

		workflowInstanceLink =
			_workflowInstanceLinkLocalService.fetchWorkflowInstanceLink(
				TestPropsValues.getCompanyId(), 0,
				FulfilmentRequest.class.getName(),
				fulfilmentRequest.getFulfilmentRequestId());

		workflowInstance = _workflowInstanceManager.getWorkflowInstance(
			TestPropsValues.getCompanyId(),
			workflowInstanceLink.getWorkflowInstanceId());

		Assert.assertTrue(workflowInstance.isComplete());
	}

	@Test
	public void testWorkflowWhenRulesNameNoMatch() throws Exception {
		String content = _workflowDefinition.getContent();

		_workflowDefinition =
			_workflowDefinitionManager.deployWorkflowDefinition(
				TestPropsValues.getCompanyId(), _workflowDefinition.getUserId(),
				_workflowDefinition.getTitle(), _workflowDefinition.getName(),
				content.getBytes());

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestLocalService.addFulfilmentRequest(
				RandomTestUtil.randomString(), _user.getUserId(),
				StringPool.BLANK, null, "internal", "no-match",
				ServiceContextTestUtil.getServiceContext());

		fulfilmentRequest =
			_fulfilmentRequestLocalService.startWorkflowInstance(
				_user.getUserId(), fulfilmentRequest,
				ServiceContextTestUtil.getServiceContext());

		WorkflowInstanceLink workflowInstanceLink =
			_workflowInstanceLinkLocalService.fetchWorkflowInstanceLink(
				TestPropsValues.getCompanyId(), 0,
				FulfilmentRequest.class.getName(),
				fulfilmentRequest.getFulfilmentRequestId());

		Assert.assertNull(workflowInstanceLink);
	}

	@Test
	public void testWorkflowWhenRulesVersionNoMatch() throws Exception {
		String content = _workflowDefinition.getContent();

		_workflowDefinition =
			_workflowDefinitionManager.deployWorkflowDefinition(
				TestPropsValues.getCompanyId(), _workflowDefinition.getUserId(),
				_workflowDefinition.getTitle(), _workflowDefinition.getName(),
				content.getBytes());

		FulfilmentRequest fulfilmentRequest =
			_fulfilmentRequestLocalService.addFulfilmentRequest(
				RandomTestUtil.randomString(), _user.getUserId(),
				StringPool.BLANK, null, "internal", "wrong-version",
				ServiceContextTestUtil.getServiceContext());

		fulfilmentRequest =
			_fulfilmentRequestLocalService.startWorkflowInstance(
				_user.getUserId(), fulfilmentRequest,
				ServiceContextTestUtil.getServiceContext());

		WorkflowInstanceLink workflowInstanceLink =
			_workflowInstanceLinkLocalService.fetchWorkflowInstanceLink(
				TestPropsValues.getCompanyId(), 0,
				FulfilmentRequest.class.getName(),
				fulfilmentRequest.getFulfilmentRequestId());

		Assert.assertNull(workflowInstanceLink);
	}

	protected InputStream getResourceInputStream(String name) {
		Class<?> clazz = getClass();

		ClassLoader classLoader = clazz.getClassLoader();

		return classLoader.getResourceAsStream(
			"com/liferay/fulfilment/test/dependencies/" + name);
	}

	protected String read(String fileName) throws Exception {
		Class<?> clazz = getClass();

		return StringUtil.read(
			clazz.getClassLoader(),
			"com/liferay/fulfilment/test/dependencies/" + fileName);
	}

	private WorkflowDefinition _saveWorkflowDefinition() throws Exception {
		InputStream inputStream = getResourceInputStream(
			"sample-workflow-definition.xml");

		byte[] content = FileUtil.getBytes(inputStream);

		return _saveWorkflowDefinition(StringUtil.randomId(), content);
	}

	private WorkflowDefinition _saveWorkflowDefinition(
			String title, byte[] bytes)
		throws Exception {

		return _workflowDefinitionManager.saveWorkflowDefinition(
			TestPropsValues.getCompanyId(), TestPropsValues.getUserId(), title,
			StringUtil.randomId(), bytes);
	}

	private static final String _PID =
		"com.liferay.fulfilment.configuration.FulfilmentRequestConfiguration";

	@Inject
	private FulfilmentRequestLocalService _fulfilmentRequestLocalService;

	private Dictionary<String, Object> _properties;
	private String _rule;

	@Inject
	private RulesEngine _rulesEngine;

	@Inject
	private SettingsFactory _settingsFactory;

	private User _user;
	private WorkflowDefinition _workflowDefinition;

	@Inject
	private WorkflowDefinitionLinkLocalService
		_workflowDefinitionLinkLocalService;

	@Inject
	private WorkflowDefinitionManager _workflowDefinitionManager;

	@Inject
	private WorkflowInstanceLinkLocalService _workflowInstanceLinkLocalService;

	@Inject
	private WorkflowInstanceManager _workflowInstanceManager;

}