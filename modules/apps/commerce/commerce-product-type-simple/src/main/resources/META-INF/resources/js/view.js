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

export default function ({
	authToken,
	checkCPInstanceURL,
	cpDefinitionId,
	namespace,
	viewAttachmentURL,
}) {
	const thumbElements = window.document.querySelectorAll('.thumb');

	Array.from(thumbElements).forEach((thumbElement) => {
		thumbElement.addEventListener('click', (event) => {
			window.document
				.getElementById(`${namespace}full-image`)
				.setAttribute(
					'src',
					event.currentTarget.getAttribute('data-url')
				);
		});
	});

	var productContent = new Liferay.Portlet.ProductContent({
		checkCPInstanceActionURL: `${checkCPInstanceURL}`,
		cpDefinitionId: `${cpDefinitionId}`,
		fullImageSelector: `#${namespace}full-image`,
		namespace: `${namespace}`,
		productContentAuthToken: authToken,
		productContentSelector: `#${namespace}${cpDefinitionId}ProductContent`,
		thumbsContainerSelector: `#${namespace}thumbs-container`,
		viewAttachmentURL: viewAttachmentURL.toString(),
	});

	Liferay.component(
		`${namespace}${cpDefinitionId}ProductContent`,
		productContent
	);
}
