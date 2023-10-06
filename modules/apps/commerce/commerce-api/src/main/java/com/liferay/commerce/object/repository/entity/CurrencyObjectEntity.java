/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.commerce.object.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import com.liferay.object.repository.entity.ObjectEntity;

import java.math.BigDecimal;

/**
 * @author Riccardo Alberti
 */
@JsonTypeName("C_Currency")
public class CurrencyObjectEntity implements ObjectEntity {

	public String getCode() {
		return _code;
	}

	public BigDecimal getExchangeRate() {
		return _exchangeRate;
	}

	public String getFormatPattern() {
		return _formatPattern;
	}

	public long getId() {
		return _id;
	}

	public int getMaximumDecimalPlaces() {
		return _maximumDecimalPlaces;
	}

	public int getMinimumDecimalPlaces() {
		return _minimumDecimalPlaces;
	}

	public String getName() {
		return _name;
	}

	public double getPriority() {
		return _priority;
	}

	public String getRoundingMode() {
		return _roundingMode;
	}

	public String getSymbol() {
		return _symbol;
	}

	public boolean isActive() {
		return _active;
	}

	public boolean isPrimary() {
		return _primary;
	}

	@JsonProperty("active")
	private boolean _active;

	@JsonProperty("code")
	private String _code;

	@JsonProperty("exchangeRate")
	private BigDecimal _exchangeRate;

	@JsonProperty("formatPattern")
	private String _formatPattern;

	@JsonProperty("c_currencyId")
	private long _id;

	@JsonProperty("maximumDecimalPlaces")
	private int _maximumDecimalPlaces;

	@JsonProperty("minimumDecimalPlaces")
	private int _minimumDecimalPlaces;

	@JsonProperty("name")
	private String _name;

	@JsonProperty("primary")
	private boolean _primary;

	@JsonProperty("priority")
	private double _priority;

	@JsonProperty("roundingMode")
	private String _roundingMode;

	@JsonProperty("symbol")
	private String _symbol;

}