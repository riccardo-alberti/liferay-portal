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

import {AdminOrderAPI} from 'commerce-frontend-js/ServiceProvider/index'
import {UPDATE_DATASET_DISPLAY} from 'commerce-frontend-js/utilities/eventsDefinitions'
import itemFinder from 'commerce-frontend-js/components/item_finder/entry'

export default ({commerceOrderTypeId, datasetId, orderTypeExternalReferenceCode, rootPortletId, spritemap}) => {
	const CommerceOrderTypeChannelsResource = AdminOrderAPI('v1');

	function selectItem(channel) {
		const channelData = {
			channelExternalReferenceCode: channel.externalReferenceCode,
			channelId: channel.id,
			orderTypeExternalReferenceCode: orderTypeExternalReferenceCode,
			orderTypeId: commerceOrderTypeId,
		};

		return CommerceOrderTypeChannelsResource.addOrderTypeChannel(
			commerceOrderTypeId,
			channelData
		)
			.then(() => {
				Liferay.fire(UPDATE_DATASET_DISPLAY, {
					id: datasetId,
				});
			})
			.catch((error) => {
				return Promise.reject(error);
			});
	}

	itemFinder('itemFinder', 'item-finder-root-channel', {
		apiUrl: '/o/headless-commerce-admin-channel/v1.0/channels',
		getSelectedItems: Promise.resolve([]),
		inputPlaceholder: Liferay.language.get('find-a-channel'),
		itemSelectedMessage: Liferay.language.get('channel-selected'),
		linkedDatasetsId: [
			datasetId,
		],
		itemCreation: false,
		itemsKey: 'id',
		onItemSelected: selectItem,
		pageSize: 10,
		panelHeaderLabel: Liferay.language.get('add-channels'),
		portletId: rootPortletId,
		schema: [
			{
				fieldName: 'name',
			},
		],
		spritemap,
		titleLabel: Liferay.language.get('add-existing-channel'),
	});
}