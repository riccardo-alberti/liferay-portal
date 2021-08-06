/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {CLOSE_MODAL} from 'commerce-frontend-js/utilities/eventsDefinitions'
import {AdminOrderAPI} from 'commerce-frontend-js/ServiceProvider/index'
import {createPortletURL} from 'frontend-js-web';

export default ({defaultLanguageId, editCommerceOrderTypePortletURL, namespace}) => {
	const CommerceOrderTypeResource = AdminOrderAPI('v1');

	const form = document.getElementById(`${namespace}fm`);

	form.addEventListener(
		"submit", (event) => {
			event.preventDefault();

			const description = form.querySelector('#description').value;
			const name = form.querySelector('#name').value;

			const orderTypeData = {
				description: {[defaultLanguageId]: description},
				name: {[defaultLanguageId]: name},
			};

			return CommerceOrderTypeResource.addOrderType(orderTypeData)
				.then((payload) => {
					const redirectURL = createPortletURL(editCommerceOrderTypePortletURL);

					redirectURL.searchParams.append('commerceOrderTypeId', payload.id);
					redirectURL.searchParams.append('p_auth', Liferay.authToken);

					window.parent.Liferay.fire(CLOSE_MODAL, {
						redirectURL: redirectURL.toString(),
						successNotification: {
							showSuccessNotification: true,
							message: Liferay.language.get('your-request-completed-successfully'),
						},
					});
				})
				.catch((error) => {
					return Promise.reject(error);
				});
		}
	)
}
