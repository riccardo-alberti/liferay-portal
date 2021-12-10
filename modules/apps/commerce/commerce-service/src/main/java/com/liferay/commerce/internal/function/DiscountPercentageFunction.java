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

package com.liferay.commerce.internal.function;

import net.objecthunter.exp4j.function.Function;

/**
 * @author Riccardo Alberti
 */
public class DiscountPercentageFunction extends Function {

	public DiscountPercentageFunction(String name) {
		super(name);
	}

	public DiscountPercentageFunction(String name, int numArguments) {
		super(name, numArguments);
	}

	@Override
	public double apply(double... args) {
		double value = args[0] * (args[1] / 100);

		if (args[2] <= 0) {
			return value;
		}

		return (value < args[2]) ? value : args[2];
	}

}