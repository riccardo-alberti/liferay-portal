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

package com.liferay.commerce.inventory.service.http;

import com.liferay.commerce.inventory.service.CommerceInventoryWarehouseOrderTypeRelServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CommerceInventoryWarehouseOrderTypeRelServiceUtil</code> service
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
 * @author Luca Pellizzon
 * @generated
 */
public class CommerceInventoryWarehouseOrderTypeRelServiceHttp {

	public static
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
					addCommerceInventoryWarehouseOrderTypeRel(
						HttpPrincipal httpPrincipal,
						long commerceInventoryWarehouseId,
						long commerceOrderTypeId, int priority,
						com.liferay.portal.kernel.service.ServiceContext
							serviceContext)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseOrderTypeRelServiceUtil.class,
				"addCommerceInventoryWarehouseOrderTypeRel",
				_addCommerceInventoryWarehouseOrderTypeRelParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceInventoryWarehouseId, commerceOrderTypeId,
				priority, serviceContext);

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

			return (com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseOrderTypeRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static void deleteCommerceInventoryWarehouseOrderTypeRel(
			HttpPrincipal httpPrincipal,
			long commerceInventoryWarehouseOrderTypeRelId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseOrderTypeRelServiceUtil.class,
				"deleteCommerceInventoryWarehouseOrderTypeRel",
				_deleteCommerceInventoryWarehouseOrderTypeRelParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceInventoryWarehouseOrderTypeRelId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
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
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
					fetchCommerceInventoryWarehouseOrderTypeRel(
						HttpPrincipal httpPrincipal,
						long commerceInventoryWarehouseId,
						long commerceOrderTypeId)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseOrderTypeRelServiceUtil.class,
				"fetchCommerceInventoryWarehouseOrderTypeRel",
				_fetchCommerceInventoryWarehouseOrderTypeRelParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceInventoryWarehouseId, commerceOrderTypeId);

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

			return (com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseOrderTypeRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static
		com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel
					getCommerceInventoryWarehouseOrderTypeRel(
						HttpPrincipal httpPrincipal,
						long commerceInventoryWarehouseOrderTypeRelId)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseOrderTypeRelServiceUtil.class,
				"getCommerceInventoryWarehouseOrderTypeRel",
				_getCommerceInventoryWarehouseOrderTypeRelParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceInventoryWarehouseOrderTypeRelId);

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

			return (com.liferay.commerce.inventory.model.
				CommerceInventoryWarehouseOrderTypeRel)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static java.util.List
		<com.liferay.commerce.inventory.model.
			CommerceInventoryWarehouseOrderTypeRel>
					getCommerceInventoryWarehouseOrderTypeRels(
						HttpPrincipal httpPrincipal,
						long commerceInventoryWarehouseId, String name,
						int start, int end,
						com.liferay.portal.kernel.util.OrderByComparator
							<com.liferay.commerce.inventory.model.
								CommerceInventoryWarehouseOrderTypeRel>
									orderByComparator)
				throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseOrderTypeRelServiceUtil.class,
				"getCommerceInventoryWarehouseOrderTypeRels",
				_getCommerceInventoryWarehouseOrderTypeRelsParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceInventoryWarehouseId, name, start, end,
				orderByComparator);

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

			return (java.util.List
				<com.liferay.commerce.inventory.model.
					CommerceInventoryWarehouseOrderTypeRel>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException
					systemException) {

			_log.error(systemException, systemException);

			throw systemException;
		}
	}

	public static int getCommerceInventoryWarehouseOrderTypeRelsCount(
			HttpPrincipal httpPrincipal, long commerceInventoryWarehouseId,
			String name)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CommerceInventoryWarehouseOrderTypeRelServiceUtil.class,
				"getCommerceInventoryWarehouseOrderTypeRelsCount",
				_getCommerceInventoryWarehouseOrderTypeRelsCountParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, commerceInventoryWarehouseId, name);

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
		CommerceInventoryWarehouseOrderTypeRelServiceHttp.class);

	private static final Class<?>[]
		_addCommerceInventoryWarehouseOrderTypeRelParameterTypes0 =
			new Class[] {
				long.class, long.class, int.class,
				com.liferay.portal.kernel.service.ServiceContext.class
			};
	private static final Class<?>[]
		_deleteCommerceInventoryWarehouseOrderTypeRelParameterTypes1 =
			new Class[] {long.class};
	private static final Class<?>[]
		_fetchCommerceInventoryWarehouseOrderTypeRelParameterTypes2 =
			new Class[] {long.class, long.class};
	private static final Class<?>[]
		_getCommerceInventoryWarehouseOrderTypeRelParameterTypes3 =
			new Class[] {long.class};
	private static final Class<?>[]
		_getCommerceInventoryWarehouseOrderTypeRelsParameterTypes4 =
			new Class[] {
				long.class, String.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class
			};
	private static final Class<?>[]
		_getCommerceInventoryWarehouseOrderTypeRelsCountParameterTypes5 =
			new Class[] {long.class, String.class};

}