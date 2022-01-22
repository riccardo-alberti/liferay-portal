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

package com.liferay.commerce.qualification.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CommerceQualificationRel}.
 * </p>
 *
 * @author Riccardo Alberti
 * @see CommerceQualificationRel
 * @generated
 */
public class CommerceQualificationRelWrapper
	extends BaseModelWrapper<CommerceQualificationRel>
	implements CommerceQualificationRel,
			   ModelWrapper<CommerceQualificationRel> {

	public CommerceQualificationRelWrapper(
		CommerceQualificationRel commerceQualificationRel) {

		super(commerceQualificationRel);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put(
			"commerceQualificationRelId", getCommerceQualificationRelId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("sourceClassNameId", getSourceClassNameId());
		attributes.put("sourceClassPK", getSourceClassPK());
		attributes.put("targetClassNameId", getTargetClassNameId());
		attributes.put("targetClassPK", getTargetClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		Long commerceQualificationRelId = (Long)attributes.get(
			"commerceQualificationRelId");

		if (commerceQualificationRelId != null) {
			setCommerceQualificationRelId(commerceQualificationRelId);
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

		Long sourceClassNameId = (Long)attributes.get("sourceClassNameId");

		if (sourceClassNameId != null) {
			setSourceClassNameId(sourceClassNameId);
		}

		Long sourceClassPK = (Long)attributes.get("sourceClassPK");

		if (sourceClassPK != null) {
			setSourceClassPK(sourceClassPK);
		}

		Long targetClassNameId = (Long)attributes.get("targetClassNameId");

		if (targetClassNameId != null) {
			setTargetClassNameId(targetClassNameId);
		}

		Long targetClassPK = (Long)attributes.get("targetClassPK");

		if (targetClassPK != null) {
			setTargetClassPK(targetClassPK);
		}
	}

	@Override
	public CommerceQualificationRel cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the commerce qualification rel ID of this commerce qualification rel.
	 *
	 * @return the commerce qualification rel ID of this commerce qualification rel
	 */
	@Override
	public long getCommerceQualificationRelId() {
		return model.getCommerceQualificationRelId();
	}

	/**
	 * Returns the company ID of this commerce qualification rel.
	 *
	 * @return the company ID of this commerce qualification rel
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this commerce qualification rel.
	 *
	 * @return the create date of this commerce qualification rel
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the modified date of this commerce qualification rel.
	 *
	 * @return the modified date of this commerce qualification rel
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this commerce qualification rel.
	 *
	 * @return the mvcc version of this commerce qualification rel
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this commerce qualification rel.
	 *
	 * @return the primary key of this commerce qualification rel
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the source class name ID of this commerce qualification rel.
	 *
	 * @return the source class name ID of this commerce qualification rel
	 */
	@Override
	public long getSourceClassNameId() {
		return model.getSourceClassNameId();
	}

	/**
	 * Returns the source class pk of this commerce qualification rel.
	 *
	 * @return the source class pk of this commerce qualification rel
	 */
	@Override
	public long getSourceClassPK() {
		return model.getSourceClassPK();
	}

	/**
	 * Returns the target class name ID of this commerce qualification rel.
	 *
	 * @return the target class name ID of this commerce qualification rel
	 */
	@Override
	public long getTargetClassNameId() {
		return model.getTargetClassNameId();
	}

	/**
	 * Returns the target class pk of this commerce qualification rel.
	 *
	 * @return the target class pk of this commerce qualification rel
	 */
	@Override
	public long getTargetClassPK() {
		return model.getTargetClassPK();
	}

	/**
	 * Returns the user ID of this commerce qualification rel.
	 *
	 * @return the user ID of this commerce qualification rel
	 */
	@Override
	public long getUserId() {
		return model.getUserId();
	}

	/**
	 * Returns the user name of this commerce qualification rel.
	 *
	 * @return the user name of this commerce qualification rel
	 */
	@Override
	public String getUserName() {
		return model.getUserName();
	}

	/**
	 * Returns the user uuid of this commerce qualification rel.
	 *
	 * @return the user uuid of this commerce qualification rel
	 */
	@Override
	public String getUserUuid() {
		return model.getUserUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the commerce qualification rel ID of this commerce qualification rel.
	 *
	 * @param commerceQualificationRelId the commerce qualification rel ID of this commerce qualification rel
	 */
	@Override
	public void setCommerceQualificationRelId(long commerceQualificationRelId) {
		model.setCommerceQualificationRelId(commerceQualificationRelId);
	}

	/**
	 * Sets the company ID of this commerce qualification rel.
	 *
	 * @param companyId the company ID of this commerce qualification rel
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this commerce qualification rel.
	 *
	 * @param createDate the create date of this commerce qualification rel
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the modified date of this commerce qualification rel.
	 *
	 * @param modifiedDate the modified date of this commerce qualification rel
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this commerce qualification rel.
	 *
	 * @param mvccVersion the mvcc version of this commerce qualification rel
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this commerce qualification rel.
	 *
	 * @param primaryKey the primary key of this commerce qualification rel
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the source class name ID of this commerce qualification rel.
	 *
	 * @param sourceClassNameId the source class name ID of this commerce qualification rel
	 */
	@Override
	public void setSourceClassNameId(long sourceClassNameId) {
		model.setSourceClassNameId(sourceClassNameId);
	}

	/**
	 * Sets the source class pk of this commerce qualification rel.
	 *
	 * @param sourceClassPK the source class pk of this commerce qualification rel
	 */
	@Override
	public void setSourceClassPK(long sourceClassPK) {
		model.setSourceClassPK(sourceClassPK);
	}

	/**
	 * Sets the target class name ID of this commerce qualification rel.
	 *
	 * @param targetClassNameId the target class name ID of this commerce qualification rel
	 */
	@Override
	public void setTargetClassNameId(long targetClassNameId) {
		model.setTargetClassNameId(targetClassNameId);
	}

	/**
	 * Sets the target class pk of this commerce qualification rel.
	 *
	 * @param targetClassPK the target class pk of this commerce qualification rel
	 */
	@Override
	public void setTargetClassPK(long targetClassPK) {
		model.setTargetClassPK(targetClassPK);
	}

	/**
	 * Sets the user ID of this commerce qualification rel.
	 *
	 * @param userId the user ID of this commerce qualification rel
	 */
	@Override
	public void setUserId(long userId) {
		model.setUserId(userId);
	}

	/**
	 * Sets the user name of this commerce qualification rel.
	 *
	 * @param userName the user name of this commerce qualification rel
	 */
	@Override
	public void setUserName(String userName) {
		model.setUserName(userName);
	}

	/**
	 * Sets the user uuid of this commerce qualification rel.
	 *
	 * @param userUuid the user uuid of this commerce qualification rel
	 */
	@Override
	public void setUserUuid(String userUuid) {
		model.setUserUuid(userUuid);
	}

	@Override
	protected CommerceQualificationRelWrapper wrap(
		CommerceQualificationRel commerceQualificationRel) {

		return new CommerceQualificationRelWrapper(commerceQualificationRel);
	}

}