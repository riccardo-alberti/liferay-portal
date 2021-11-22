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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>FulfilmentRequestServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Riccardo Alberti
 * @see FulfilmentRequestServiceSoap
 * @generated
 */
public class FulfilmentRequestServiceHttp {

	public static com.liferay.fulfilment.model.FulfilmentRequest
			addFulfilmentRequest(
				HttpPrincipal httpPrincipal, String externalReferenceCode,
				String originalFulfilmentRequest, String inputParameters,
				String replyTo, String type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FulfilmentRequestServiceUtil.class, "addFulfilmentRequest",
				_addFulfilmentRequestParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, externalReferenceCode, originalFulfilmentRequest,
				inputParameters, replyTo, type, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.fulfilment.model.FulfilmentRequest)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequest
			deleteFulfilmentRequest(
				HttpPrincipal httpPrincipal, long fulfilmentRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FulfilmentRequestServiceUtil.class, "deleteFulfilmentRequest",
				_deleteFulfilmentRequestParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fulfilmentRequestId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.fulfilment.model.FulfilmentRequest)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequest
			fetchByExternalReferenceCode(
				HttpPrincipal httpPrincipal, long companyId,
				String externalReferenceCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FulfilmentRequestServiceUtil.class,
				"fetchByExternalReferenceCode",
				_fetchByExternalReferenceCodeParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, companyId, externalReferenceCode);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.fulfilment.model.FulfilmentRequest)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequest
			fetchFulfilmentRequest(
				HttpPrincipal httpPrincipal, long fulfilmentRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FulfilmentRequestServiceUtil.class, "fetchFulfilmentRequest",
				_fetchFulfilmentRequestParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fulfilmentRequestId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.fulfilment.model.FulfilmentRequest)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequest
			getFulfilmentRequest(
				HttpPrincipal httpPrincipal, long fulfilmentRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FulfilmentRequestServiceUtil.class, "getFulfilmentRequest",
				_getFulfilmentRequestParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fulfilmentRequestId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.fulfilment.model.FulfilmentRequest)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequest
			startWorkflowInstance(
				HttpPrincipal httpPrincipal, long userId,
				com.liferay.fulfilment.model.FulfilmentRequest
					fulfilmentRequest,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FulfilmentRequestServiceUtil.class, "startWorkflowInstance",
				_startWorkflowInstanceParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, fulfilmentRequest, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.fulfilment.model.FulfilmentRequest)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static com.liferay.fulfilment.model.FulfilmentRequest
			updateFulfilmentRequest(
				HttpPrincipal httpPrincipal, long fulfilmentRequestId,
				String originalFulfilmentRequest, String inputParameters,
				String replyTo, String type,
				com.liferay.portal.kernel.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FulfilmentRequestServiceUtil.class, "updateFulfilmentRequest",
				_updateFulfilmentRequestParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fulfilmentRequestId, originalFulfilmentRequest,
				inputParameters, replyTo, type, serviceContext);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception exception) {
				if (exception instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						exception;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					exception);
			}

			return (com.liferay.fulfilment.model.FulfilmentRequest)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		FulfilmentRequestServiceHttp.class);

	private static final Class<?>[] _addFulfilmentRequestParameterTypes0 =
		new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _deleteFulfilmentRequestParameterTypes1 =
		new Class[] {long.class};
	private static final Class<?>[]
		_fetchByExternalReferenceCodeParameterTypes2 = new Class[] {
			long.class, String.class
		};
	private static final Class<?>[] _fetchFulfilmentRequestParameterTypes3 =
		new Class[] {long.class};
	private static final Class<?>[] _getFulfilmentRequestParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _startWorkflowInstanceParameterTypes5 =
		new Class[] {
			long.class, com.liferay.fulfilment.model.FulfilmentRequest.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};
	private static final Class<?>[] _updateFulfilmentRequestParameterTypes6 =
		new Class[] {
			long.class, String.class, String.class, String.class, String.class,
			com.liferay.portal.kernel.service.ServiceContext.class
		};

}