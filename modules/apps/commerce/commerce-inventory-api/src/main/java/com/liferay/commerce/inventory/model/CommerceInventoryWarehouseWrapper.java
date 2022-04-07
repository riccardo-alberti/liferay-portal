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

package com.liferay.commerce.inventory.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CommerceInventoryWarehouse}.
 * </p>
 *
 * @author Luca Pellizzon
 * @see CommerceInventoryWarehouse
 * @generated
 */
public class CommerceInventoryWarehouseWrapper
	extends BaseModelWrapper<CommerceInventoryWarehouse>
	implements CommerceInventoryWarehouse,
			   ModelWrapper<CommerceInventoryWarehouse> {

	public CommerceInventoryWarehouseWrapper(
		CommerceInventoryWarehouse commerceInventoryWarehouse) {

		super(commerceInventoryWarehouse);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("externalReferenceCode", getExternalReferenceCode());
		attributes.put(
			"commerceInventoryWarehouseId", getCommerceInventoryWarehouseId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("description", getDescription());
		attributes.put("label", getLabel());
		attributes.put("active", isActive());
		attributes.put("street1", getStreet1());
		attributes.put("street2", getStreet2());
		attributes.put("street3", getStreet3());
		attributes.put("city", getCity());
		attributes.put("zip", getZip());
		attributes.put("commerceRegionCode", getCommerceRegionCode());
		attributes.put(
			"countryTwoLettersISOCode", getCountryTwoLettersISOCode());
		attributes.put("latitude", getLatitude());
		attributes.put("longitude", getLongitude());
		attributes.put("type", getType());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String externalReferenceCode = (String)attributes.get(
			"externalReferenceCode");

		if (externalReferenceCode != null) {
			setExternalReferenceCode(externalReferenceCode);
		}

		Long commerceInventoryWarehouseId = (Long)attributes.get(
			"commerceInventoryWarehouseId");

		if (commerceInventoryWarehouseId != null) {
			setCommerceInventoryWarehouseId(commerceInventoryWarehouseId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		String label = (String)attributes.get("label");

		if (label != null) {
			setLabel(label);
		}

		Boolean active = (Boolean)attributes.get("active");

		if (active != null) {
			setActive(active);
		}

		String street1 = (String)attributes.get("street1");

		if (street1 != null) {
			setStreet1(street1);
		}

		String street2 = (String)attributes.get("street2");

		if (street2 != null) {
			setStreet2(street2);
		}

		String street3 = (String)attributes.get("street3");

		if (street3 != null) {
			setStreet3(street3);
		}

		String city = (String)attributes.get("city");

		if (city != null) {
			setCity(city);
		}

		String zip = (String)attributes.get("zip");

		if (zip != null) {
			setZip(zip);
		}

		String commerceRegionCode = (String)attributes.get(
			"commerceRegionCode");

		if (commerceRegionCode != null) {
			setCommerceRegionCode(commerceRegionCode);
		}

		String countryTwoLettersISOCode = (String)attributes.get(
			"countryTwoLettersISOCode");

		if (countryTwoLettersISOCode != null) {
			setCountryTwoLettersISOCode(countryTwoLettersISOCode);
		}

		Double latitude = (Double)attributes.get("latitude");

		if (latitude != null) {
			setLatitude(latitude);
		}

		Double longitude = (Double)attributes.get("longitude");

		if (longitude != null) {
			setLongitude(longitude);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	@Override
	public CommerceInventoryWarehouse cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the active of this commerce inventory warehouse.
	 *
	 * @return the active of this commerce inventory warehouse
	 */
	@Override
	public boolean getActive() {
		return model.getActive();
	}

	@Override
	public String[] getAvailableLanguageIds() {
		return model.getAvailableLanguageIds();
	}

	/**
	 * Returns the city of this commerce inventory warehouse.
	 *
	 * @return the city of this commerce inventory warehouse
	 */
	@Override
	public String getCity() {
		return model.getCity();
	}

	/**
	 * Returns the commerce inventory warehouse ID of this commerce inventory warehouse.
	 *
	 * @return the commerce inventory warehouse ID of this commerce inventory warehouse
	 */
	@Override
	public long getCommerceInventoryWarehouseId() {
		return model.getCommerceInventoryWarehouseId();
	}

	@Override
	public java.util.List<CommerceInventoryWarehouseItem>
		getCommerceInventoryWarehouseItems() {

		return model.getCommerceInventoryWarehouseItems();
	}

	/**
	 * Returns the commerce region code of this commerce inventory warehouse.
	 *
	 * @return the commerce region code of this commerce inventory warehouse
	 */
	@Override
	public String getCommerceRegionCode() {
		return model.getCommerceRegionCode();
	}

	/**
	 * Returns the company ID of this commerce inventory warehouse.
	 *
	 * @return the company ID of this commerce inventory warehouse
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the country two letters iso code of this commerce inventory warehouse.
	 *
	 * @return the country two letters iso code of this commerce inventory warehouse
	 */
	@Override
	public String getCountryTwoLettersISOCode() {
		return model.getCountryTwoLettersISOCode();
	}

	/**
	 * Returns the create date of this commerce inventory warehouse.
	 *
	 * @return the create date of this commerce inventory warehouse
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	@Override
	public String getDefaultLanguageId() {
		return model.getDefaultLanguageId();
	}

	/**
	 * Returns the description of this commerce inventory warehouse.
	 *
	 * @return the description of this commerce inventory warehouse
	 */
	@Override
	public String getDescription() {
		return model.getDescription();
	}

	/**
	 * Returns the localized description of this commerce inventory warehouse in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized description of this commerce inventory warehouse
	 */
	@Override
	public String getDescription(java.util.Locale locale) {
		return model.getDescription(locale);
	}

	/**
	 * Returns the localized description of this commerce inventory warehouse in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this commerce inventory warehouse. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getDescription(java.util.Locale locale, boolean useDefault) {
		return model.getDescription(locale, useDefault);
	}

	/**
	 * Returns the localized description of this commerce inventory warehouse in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized description of this commerce inventory warehouse
	 */
	@Override
	public String getDescription(String languageId) {
		return model.getDescription(languageId);
	}

	/**
	 * Returns the localized description of this commerce inventory warehouse in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized description of this commerce inventory warehouse
	 */
	@Override
	public String getDescription(String languageId, boolean useDefault) {
		return model.getDescription(languageId, useDefault);
	}

	@Override
	public String getDescriptionCurrentLanguageId() {
		return model.getDescriptionCurrentLanguageId();
	}

	@Override
	public String getDescriptionCurrentValue() {
		return model.getDescriptionCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized descriptions of this commerce inventory warehouse.
	 *
	 * @return the locales and localized descriptions of this commerce inventory warehouse
	 */
	@Override
	public Map<java.util.Locale, String> getDescriptionMap() {
		return model.getDescriptionMap();
	}

	/**
	 * Returns the external reference code of this commerce inventory warehouse.
	 *
	 * @return the external reference code of this commerce inventory warehouse
	 */
	@Override
	public String getExternalReferenceCode() {
		return model.getExternalReferenceCode();
	}

	/**
	 * Returns the label of this commerce inventory warehouse.
	 *
	 * @return the label of this commerce inventory warehouse
	 */
	@Override
	public String getLabel() {
		return model.getLabel();
	}

	/**
	 * Returns the localized label of this commerce inventory warehouse in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param locale the locale of the language
	 * @return the localized label of this commerce inventory warehouse
	 */
	@Override
	public String getLabel(java.util.Locale locale) {
		return model.getLabel(locale);
	}

	/**
	 * Returns the localized label of this commerce inventory warehouse in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param locale the local of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized label of this commerce inventory warehouse. If <code>useDefault</code> is <code>false</code> and no localization exists for the requested language, an empty string will be returned.
	 */
	@Override
	public String getLabel(java.util.Locale locale, boolean useDefault) {
		return model.getLabel(locale, useDefault);
	}

	/**
	 * Returns the localized label of this commerce inventory warehouse in the language. Uses the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @return the localized label of this commerce inventory warehouse
	 */
	@Override
	public String getLabel(String languageId) {
		return model.getLabel(languageId);
	}

	/**
	 * Returns the localized label of this commerce inventory warehouse in the language, optionally using the default language if no localization exists for the requested language.
	 *
	 * @param languageId the ID of the language
	 * @param useDefault whether to use the default language if no localization exists for the requested language
	 * @return the localized label of this commerce inventory warehouse
	 */
	@Override
	public String getLabel(String languageId, boolean useDefault) {
		return model.getLabel(languageId, useDefault);
	}

	@Override
	public String getLabelCurrentLanguageId() {
		return model.getLabelCurrentLanguageId();
	}

	@Override
	public String getLabelCurrentValue() {
		return model.getLabelCurrentValue();
	}

	/**
	 * Returns a map of the locales and localized labels of this commerce inventory warehouse.
	 *
	 * @return the locales and localized labels of this commerce inventory warehouse
	 */
	@Override
	public Map<java.util.Locale, String> getLabelMap() {
		return model.getLabelMap();
	}

	/**
	 * Returns the latitude of this commerce inventory warehouse.
	 *
	 * @return the latitude of this commerce inventory warehouse
	 */
	@Override
	public double getLatitude() {
		return model.getLatitude();
	}

	/**
	 * Returns the longitude of this commerce inventory warehouse.
	 *
	 * @return the longitude of this commerce inventory warehouse
	 */
	@Override
	public double getLongitude() {
		return model.getLongitude();
	}

	/**
	 * Returns the modified date of this commerce inventory warehouse.
	 *
	 * @return the modified date of this commerce inventory warehouse
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this commerce inventory warehouse.
	 *
	 * @return the mvcc version of this commerce inventory warehouse
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the name of this commerce inventory warehouse.
	 *
	 * @return the name of this commerce inventory warehouse
	 */
	@Override
	public String getName() {
		return model.getName();
	}

	/**
	 * Returns the primary key of this commerce inventory warehouse.
	 *
	 * @return the primary key of this commerce inventory warehouse
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the status of this commerce inventory warehouse.
	 *
	 * @return the status of this commerce inventory warehouse
	 */
	@Override
	public int getStatus() {
		return model.getStatus();
	}

	/**
	 * Returns the status by user ID of this commerce inventory warehouse.
	 *
	 * @return the status by user ID of this commerce inventory warehouse
	 */
	@Override
	public long getStatusByUserId() {
		return model.getStatusByUserId();
	}

	/**
	 * Returns the status by user name of this commerce inventory warehouse.
	 *
	 * @return the status by user name of this commerce inventory warehouse
	 */
	@Override
	public String getStatusByUserName() {
		return model.getStatusByUserName();
	}

	/**
	 * Returns the status by user uuid of this commerce inventory warehouse.
	 *
	 * @return the status by user uuid of this commerce inventory warehouse
	 */
	@Override
	public String getStatusByUserUuid() {
		return model.getStatusByUserUuid();
	}

	/**
	 * Returns the status date of this commerce inventory warehouse.
	 *
	 * @return the status date of this commerce inventory warehouse
	 */
	@Override
	public Date getStatusDate() {
		return model.getStatusDate();
	}

	/**
	 * Returns the street1 of this commerce inventory warehouse.
	 *
	 * @return the street1 of this commerce inventory warehouse
	 */
	@Override
	public String getStreet1() {
		return model.getStreet1();
	}

	/**
	 * Returns the street2 of this commerce inventory warehouse.
	 *
	 * @return the street2 of this commerce inventory warehouse
	 */
	@Override
	public String getStreet2() {
		return model.getStreet2();
	}

	/**
	 * Returns the street3 of this commerce inventory warehouse.
	 *
	 * @return the street3 of this commerce inventory warehouse
	 */
	@Override
	public String getStreet3() {
		return model.getStreet3();
	}

	/**
	 * Returns the type of this commerce inventory warehouse.
	 *
	 * @return the type of this commerce inventory warehouse
	 */
	@Override
	public String getType() {
		return model.getType();
	}

	/**
	 * Returns the user ID of this commerce inventory warehouse.
	 *
	 * @return the user ID of this commerce inventory warehouse
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this commerce inventory warehouse.
	 *
	 * @return the user name of this commerce inventory warehouse
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this commerce inventory warehouse.
	 *
	 * @return the user uuid of this commerce inventory warehouse
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	/**
	 * Returns the zip of this commerce inventory warehouse.
	 *
	 * @return the zip of this commerce inventory warehouse
	 */
	@Override
	public String getZip() {
		return model.getZip();
	}

	/**
	 * Returns <code>true</code> if this commerce inventory warehouse is active.
	 *
	 * @return <code>true</code> if this commerce inventory warehouse is active; <code>false</code> otherwise
	 */
	@Override
	public boolean isActive() {
		return model.isActive();
	}

	/**
	 * Returns <code>true</code> if this commerce inventory warehouse is approved.
	 *
	 * @return <code>true</code> if this commerce inventory warehouse is approved; <code>false</code> otherwise
	 */
	@Override
	public boolean isApproved() {
		return model.isApproved();
	}

	/**
	 * Returns <code>true</code> if this commerce inventory warehouse is denied.
	 *
	 * @return <code>true</code> if this commerce inventory warehouse is denied; <code>false</code> otherwise
	 */
	@Override
	public boolean isDenied() {
		return model.isDenied();
	}

	/**
	 * Returns <code>true</code> if this commerce inventory warehouse is a draft.
	 *
	 * @return <code>true</code> if this commerce inventory warehouse is a draft; <code>false</code> otherwise
	 */
	@Override
	public boolean isDraft() {
		return model.isDraft();
	}

	/**
	 * Returns <code>true</code> if this commerce inventory warehouse is expired.
	 *
	 * @return <code>true</code> if this commerce inventory warehouse is expired; <code>false</code> otherwise
	 */
	@Override
	public boolean isExpired() {
		return model.isExpired();
	}

	@Override
	public boolean isGeolocated() {
		return model.isGeolocated();
	}

	/**
	 * Returns <code>true</code> if this commerce inventory warehouse is inactive.
	 *
	 * @return <code>true</code> if this commerce inventory warehouse is inactive; <code>false</code> otherwise
	 */
	@Override
	public boolean isInactive() {
		return model.isInactive();
	}

	/**
	 * Returns <code>true</code> if this commerce inventory warehouse is incomplete.
	 *
	 * @return <code>true</code> if this commerce inventory warehouse is incomplete; <code>false</code> otherwise
	 */
	@Override
	public boolean isIncomplete() {
		return model.isIncomplete();
	}

	/**
	 * Returns <code>true</code> if this commerce inventory warehouse is pending.
	 *
	 * @return <code>true</code> if this commerce inventory warehouse is pending; <code>false</code> otherwise
	 */
	@Override
	public boolean isPending() {
		return model.isPending();
	}

	/**
	 * Returns <code>true</code> if this commerce inventory warehouse is scheduled.
	 *
	 * @return <code>true</code> if this commerce inventory warehouse is scheduled; <code>false</code> otherwise
	 */
	@Override
	public boolean isScheduled() {
		return model.isScheduled();
	}

	@Override
	public void persist() {
		model.persist();
	}

	@Override
	public void prepareLocalizedFieldsForImport()
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport();
	}

	@Override
	public void prepareLocalizedFieldsForImport(
			java.util.Locale defaultImportLocale)
		throws com.liferay.portal.kernel.exception.LocaleException {

		model.prepareLocalizedFieldsForImport(defaultImportLocale);
	}

	/**
	 * Sets whether this commerce inventory warehouse is active.
	 *
	 * @param active the active of this commerce inventory warehouse
	 */
	@Override
	public void setActive(boolean active) {
		model.setActive(active);
	}

	/**
	 * Sets the city of this commerce inventory warehouse.
	 *
	 * @param city the city of this commerce inventory warehouse
	 */
	@Override
	public void setCity(String city) {
		model.setCity(city);
	}

	/**
	 * Sets the commerce inventory warehouse ID of this commerce inventory warehouse.
	 *
	 * @param commerceInventoryWarehouseId the commerce inventory warehouse ID of this commerce inventory warehouse
	 */
	@Override
	public void setCommerceInventoryWarehouseId(
		long commerceInventoryWarehouseId) {

		model.setCommerceInventoryWarehouseId(commerceInventoryWarehouseId);
	}

	/**
	 * Sets the commerce region code of this commerce inventory warehouse.
	 *
	 * @param commerceRegionCode the commerce region code of this commerce inventory warehouse
	 */
	@Override
	public void setCommerceRegionCode(String commerceRegionCode) {
		model.setCommerceRegionCode(commerceRegionCode);
	}

	/**
	 * Sets the company ID of this commerce inventory warehouse.
	 *
	 * @param companyId the company ID of this commerce inventory warehouse
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the country two letters iso code of this commerce inventory warehouse.
	 *
	 * @param countryTwoLettersISOCode the country two letters iso code of this commerce inventory warehouse
	 */
	@Override
	public void setCountryTwoLettersISOCode(String countryTwoLettersISOCode) {
		model.setCountryTwoLettersISOCode(countryTwoLettersISOCode);
	}

	/**
	 * Sets the create date of this commerce inventory warehouse.
	 *
	 * @param createDate the create date of this commerce inventory warehouse
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the description of this commerce inventory warehouse.
	 *
	 * @param description the description of this commerce inventory warehouse
	 */
	@Override
	public void setDescription(String description) {
		model.setDescription(description);
	}

	/**
	 * Sets the localized description of this commerce inventory warehouse in the language.
	 *
	 * @param description the localized description of this commerce inventory warehouse
	 * @param locale the locale of the language
	 */
	@Override
	public void setDescription(String description, java.util.Locale locale) {
		model.setDescription(description, locale);
	}

	/**
	 * Sets the localized description of this commerce inventory warehouse in the language, and sets the default locale.
	 *
	 * @param description the localized description of this commerce inventory warehouse
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescription(
		String description, java.util.Locale locale,
		java.util.Locale defaultLocale) {

		model.setDescription(description, locale, defaultLocale);
	}

	@Override
	public void setDescriptionCurrentLanguageId(String languageId) {
		model.setDescriptionCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized descriptions of this commerce inventory warehouse from the map of locales and localized descriptions.
	 *
	 * @param descriptionMap the locales and localized descriptions of this commerce inventory warehouse
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap) {

		model.setDescriptionMap(descriptionMap);
	}

	/**
	 * Sets the localized descriptions of this commerce inventory warehouse from the map of locales and localized descriptions, and sets the default locale.
	 *
	 * @param descriptionMap the locales and localized descriptions of this commerce inventory warehouse
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setDescriptionMap(
		Map<java.util.Locale, String> descriptionMap,
		java.util.Locale defaultLocale) {

		model.setDescriptionMap(descriptionMap, defaultLocale);
	}

	/**
	 * Sets the external reference code of this commerce inventory warehouse.
	 *
	 * @param externalReferenceCode the external reference code of this commerce inventory warehouse
	 */
	@Override
	public void setExternalReferenceCode(String externalReferenceCode) {
		model.setExternalReferenceCode(externalReferenceCode);
	}

	/**
	 * Sets the label of this commerce inventory warehouse.
	 *
	 * @param label the label of this commerce inventory warehouse
	 */
	@Override
	public void setLabel(String label) {
		model.setLabel(label);
	}

	/**
	 * Sets the localized label of this commerce inventory warehouse in the language.
	 *
	 * @param label the localized label of this commerce inventory warehouse
	 * @param locale the locale of the language
	 */
	@Override
	public void setLabel(String label, java.util.Locale locale) {
		model.setLabel(label, locale);
	}

	/**
	 * Sets the localized label of this commerce inventory warehouse in the language, and sets the default locale.
	 *
	 * @param label the localized label of this commerce inventory warehouse
	 * @param locale the locale of the language
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setLabel(
		String label, java.util.Locale locale, java.util.Locale defaultLocale) {

		model.setLabel(label, locale, defaultLocale);
	}

	@Override
	public void setLabelCurrentLanguageId(String languageId) {
		model.setLabelCurrentLanguageId(languageId);
	}

	/**
	 * Sets the localized labels of this commerce inventory warehouse from the map of locales and localized labels.
	 *
	 * @param labelMap the locales and localized labels of this commerce inventory warehouse
	 */
	@Override
	public void setLabelMap(Map<java.util.Locale, String> labelMap) {
		model.setLabelMap(labelMap);
	}

	/**
	 * Sets the localized labels of this commerce inventory warehouse from the map of locales and localized labels, and sets the default locale.
	 *
	 * @param labelMap the locales and localized labels of this commerce inventory warehouse
	 * @param defaultLocale the default locale
	 */
	@Override
	public void setLabelMap(
		Map<java.util.Locale, String> labelMap,
		java.util.Locale defaultLocale) {

		model.setLabelMap(labelMap, defaultLocale);
	}

	/**
	 * Sets the latitude of this commerce inventory warehouse.
	 *
	 * @param latitude the latitude of this commerce inventory warehouse
	 */
	@Override
	public void setLatitude(double latitude) {
		model.setLatitude(latitude);
	}

	/**
	 * Sets the longitude of this commerce inventory warehouse.
	 *
	 * @param longitude the longitude of this commerce inventory warehouse
	 */
	@Override
	public void setLongitude(double longitude) {
		model.setLongitude(longitude);
	}

	/**
	 * Sets the modified date of this commerce inventory warehouse.
	 *
	 * @param modifiedDate the modified date of this commerce inventory warehouse
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this commerce inventory warehouse.
	 *
	 * @param mvccVersion the mvcc version of this commerce inventory warehouse
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the name of this commerce inventory warehouse.
	 *
	 * @param name the name of this commerce inventory warehouse
	 */
	@Override
	public void setName(String name) {
		model.setName(name);
	}

	/**
	 * Sets the primary key of this commerce inventory warehouse.
	 *
	 * @param primaryKey the primary key of this commerce inventory warehouse
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the status of this commerce inventory warehouse.
	 *
	 * @param status the status of this commerce inventory warehouse
	 */
	@Override
	public void setStatus(int status) {
		model.setStatus(status);
	}

	/**
	 * Sets the status by user ID of this commerce inventory warehouse.
	 *
	 * @param statusByUserId the status by user ID of this commerce inventory warehouse
	 */
	@Override
	public void setStatusByUserId(long statusByUserId) {
		model.setStatusByUserId(statusByUserId);
	}

	/**
	 * Sets the status by user name of this commerce inventory warehouse.
	 *
	 * @param statusByUserName the status by user name of this commerce inventory warehouse
	 */
	@Override
	public void setStatusByUserName(String statusByUserName) {
		model.setStatusByUserName(statusByUserName);
	}

	/**
	 * Sets the status by user uuid of this commerce inventory warehouse.
	 *
	 * @param statusByUserUuid the status by user uuid of this commerce inventory warehouse
	 */
	@Override
	public void setStatusByUserUuid(String statusByUserUuid) {
		model.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	 * Sets the status date of this commerce inventory warehouse.
	 *
	 * @param statusDate the status date of this commerce inventory warehouse
	 */
	@Override
	public void setStatusDate(Date statusDate) {
		model.setStatusDate(statusDate);
	}

	/**
	 * Sets the street1 of this commerce inventory warehouse.
	 *
	 * @param street1 the street1 of this commerce inventory warehouse
	 */
	@Override
	public void setStreet1(String street1) {
		model.setStreet1(street1);
	}

	/**
	 * Sets the street2 of this commerce inventory warehouse.
	 *
	 * @param street2 the street2 of this commerce inventory warehouse
	 */
	@Override
	public void setStreet2(String street2) {
		model.setStreet2(street2);
	}

	/**
	 * Sets the street3 of this commerce inventory warehouse.
	 *
	 * @param street3 the street3 of this commerce inventory warehouse
	 */
	@Override
	public void setStreet3(String street3) {
		model.setStreet3(street3);
	}

	/**
	 * Sets the type of this commerce inventory warehouse.
	 *
	 * @param type the type of this commerce inventory warehouse
	 */
	@Override
	public void setType(String type) {
		model.setType(type);
	}

	/**
	 * Sets the user ID of this commerce inventory warehouse.
	 *
	 * @param userId the user ID of this commerce inventory warehouse
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this commerce inventory warehouse.
	 *
	 * @param userName the user name of this commerce inventory warehouse
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this commerce inventory warehouse.
	 *
	 * @param userUuid the user uuid of this commerce inventory warehouse
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	/**
	 * Sets the zip of this commerce inventory warehouse.
	 *
	 * @param zip the zip of this commerce inventory warehouse
	 */
	@Override
	public void setZip(String zip) {
		model.setZip(zip);
	}

	@Override
	protected CommerceInventoryWarehouseWrapper wrap(
		CommerceInventoryWarehouse commerceInventoryWarehouse) {

		return new CommerceInventoryWarehouseWrapper(
			commerceInventoryWarehouse);
	}

}