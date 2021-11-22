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

package com.liferay.fulfilment.constants;

/**
 * @author Riccardo Alberti
 */
public class FulfilmentConstants {

	public static final String ACTION_EXECUTOR_EXCEPTION = "__$_exception";

	public static final String ACTION_EXECUTOR_FULFILMENT_REQUEST_ID =
		"__$_fulfilmentRequestId";

	public static final String ACTION_EXECUTOR_KEYWORDS_PREFIX = "__$_";

	public static final String ACTION_EXECUTOR_STATUS = "__$_status";

	public static final String FULFILMENT_REQUEST_RESOURCE_NAME =
		"com.liferay.fulfilment";

	public static final String REPLY_TO_INTERNAL = "internal";

	public static final int STATUS_COMPLETED = 0;

	public static final int STATUS_EXPIRED = -2;

	public static final int STATUS_FAILED = -1;

	public static final int STATUS_INCOMPLETE = 1;

	public static final int STATUS_PENDING = 2;

	public static final int STATUS_SCHEDULED = 3;

	public static final int STATUS_STARTED = 4;

	public static String getStatusLabel(int status) {
		if (status == STATUS_COMPLETED) {
			return "completed";
		}
		else if (status == STATUS_EXPIRED) {
			return "expired";
		}
		else if (status == STATUS_FAILED) {
			return "failed";
		}
		else if (status == STATUS_INCOMPLETE) {
			return "incomplete";
		}
		else if (status == STATUS_PENDING) {
			return "pending";
		}
		else if (status == STATUS_SCHEDULED) {
			return "scheduled";
		}
		else if (status == STATUS_STARTED) {
			return "started";
		}

		return "any";
	}

}