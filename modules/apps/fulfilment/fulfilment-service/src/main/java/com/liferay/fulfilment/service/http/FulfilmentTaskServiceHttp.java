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

import com.liferay.fulfilment.service.FulfilmentTaskServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>FulfilmentTaskServiceUtil</code> service
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
 * @see FulfilmentTaskServiceSoap
 * @generated
 */
public class FulfilmentTaskServiceHttp {

	public static com.liferay.fulfilment.model.FulfilmentTask getFulfilmentTask(
			HttpPrincipal httpPrincipal, long fulfilmentTaskId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FulfilmentTaskServiceUtil.class, "getFulfilmentTask",
				_getFulfilmentTaskParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fulfilmentTaskId);

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

			return (com.liferay.fulfilment.model.FulfilmentTask)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List<com.liferay.fulfilment.model.FulfilmentTask>
			getFulfilmentTasks(
				HttpPrincipal httpPrincipal, long fulfilmentRequestId,
				int start, int end)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FulfilmentTaskServiceUtil.class, "getFulfilmentTasks",
				_getFulfilmentTasksParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, fulfilmentRequestId, start, end);

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

			return (java.util.List<com.liferay.fulfilment.model.FulfilmentTask>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getFulfilmentTasksCount(
			HttpPrincipal httpPrincipal, long fulfilmentRequestId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				FulfilmentTaskServiceUtil.class, "getFulfilmentTasksCount",
				_getFulfilmentTasksCountParameterTypes2);

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

			return ((Integer)returnObj).intValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(
		FulfilmentTaskServiceHttp.class);

	private static final Class<?>[] _getFulfilmentTaskParameterTypes0 =
		new Class[] {long.class};
	private static final Class<?>[] _getFulfilmentTasksParameterTypes1 =
		new Class[] {long.class, int.class, int.class};
	private static final Class<?>[] _getFulfilmentTasksCountParameterTypes2 =
		new Class[] {long.class};

}