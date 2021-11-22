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

package com.liferay.fulfilment.service.http;

import com.liferay.fulfilment.service.FulfilmentRequestServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>FulfilmentRequestServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a <code>java.util.List</code>,
 * that is translated to an array of
 * <code>com.liferay.fulfilment.model.FulfilmentRequestSoap</code>. If the method in the
 * service utility returns a
 * <code>com.liferay.fulfilment.model.FulfilmentRequest</code>, that is translated to a
 * <code>com.liferay.fulfilment.model.FulfilmentRequestSoap</code>. Methods that SOAP
 * cannot safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Riccardo Alberti
 * @see FulfilmentRequestServiceHttp
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class FulfilmentRequestServiceSoap {

	public static com.liferay.fulfilment.model.FulfilmentRequestSoap
			addFulfilmentRequest(
				String externalReferenceCode, String originalFulfilmentRequest,
				String inputParameters, String replyTo, String type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.fulfilment.model.FulfilmentRequest returnValue =
				FulfilmentRequestServiceUtil.addFulfilmentRequest(
					externalReferenceCode, originalFulfilmentRequest,
					inputParameters, replyTo, type, serviceContext);

			return com.liferay.fulfilment.model.FulfilmentRequestSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequestSoap
			deleteFulfilmentRequest(long fulfilmentRequestId)
		throws RemoteException {

		try {
			com.liferay.fulfilment.model.FulfilmentRequest returnValue =
				FulfilmentRequestServiceUtil.deleteFulfilmentRequest(
					fulfilmentRequestId);

			return com.liferay.fulfilment.model.FulfilmentRequestSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequestSoap
			fetchByExternalReferenceCode(
				long companyId, String externalReferenceCode)
		throws RemoteException {

		try {
			com.liferay.fulfilment.model.FulfilmentRequest returnValue =
				FulfilmentRequestServiceUtil.fetchByExternalReferenceCode(
					companyId, externalReferenceCode);

			return com.liferay.fulfilment.model.FulfilmentRequestSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequestSoap
			fetchFulfilmentRequest(long fulfilmentRequestId)
		throws RemoteException {

		try {
			com.liferay.fulfilment.model.FulfilmentRequest returnValue =
				FulfilmentRequestServiceUtil.fetchFulfilmentRequest(
					fulfilmentRequestId);

			return com.liferay.fulfilment.model.FulfilmentRequestSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequestSoap
			getFulfilmentRequest(long fulfilmentRequestId)
		throws RemoteException {

		try {
			com.liferay.fulfilment.model.FulfilmentRequest returnValue =
				FulfilmentRequestServiceUtil.getFulfilmentRequest(
					fulfilmentRequestId);

			return com.liferay.fulfilment.model.FulfilmentRequestSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequestSoap
			startWorkflowInstance(
				long userId,
				com.liferay.fulfilment.model.FulfilmentRequestSoap
					fulfilmentRequest,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.fulfilment.model.FulfilmentRequest returnValue =
				FulfilmentRequestServiceUtil.startWorkflowInstance(
					userId,
					com.liferay.fulfilment.model.impl.
						FulfilmentRequestModelImpl.toModel(fulfilmentRequest),
					serviceContext);

			return com.liferay.fulfilment.model.FulfilmentRequestSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequestSoap
			updateFulfilmentRequest(
				long fulfilmentRequestId, String originalFulfilmentRequest,
				String inputParameters, String replyTo, String type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws RemoteException {

		try {
			com.liferay.fulfilment.model.FulfilmentRequest returnValue =
				FulfilmentRequestServiceUtil.updateFulfilmentRequest(
					fulfilmentRequestId, originalFulfilmentRequest,
					inputParameters, replyTo, type, serviceContext);

			return com.liferay.fulfilment.model.FulfilmentRequestSoap.
				toSoapModel(returnValue);
		}
		catch (Exception exception) {
			_log.error(exception, exception);

			throw new RemoteException(exception.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		FulfilmentRequestServiceSoap.class);

}