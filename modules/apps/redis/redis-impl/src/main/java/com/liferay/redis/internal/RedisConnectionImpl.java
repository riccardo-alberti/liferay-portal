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

package com.liferay.redis.internal;

import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.io.ProtectedObjectInputStream;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.redis.RedisConnection;
import com.liferay.redis.configuration.RedisConnectionConfiguration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author Riccardo Alberti
 */
@Component(
	configurationPid = "com.liferay.redis.configuration.RedisConnectionConfiguration",
	immediate = true, service = RedisConnection.class
)
public class RedisConnectionImpl implements RedisConnection {

	@Override
	public Object get(String key) {
		try (Jedis jedis = _jedisPool.getResource()) {
			byte[] bytes = jedis.get(key.getBytes());

			if (bytes == null) {
				return null;
			}

			return _deserialize(bytes);
		}
	}

	@Override
	public Map<String, String> getMap(String key) {
		try (Jedis jedis = _jedisPool.getResource()) {
			return jedis.hgetAll(key);
		}
	}

	@Override
	public void set(String key, Object value) {
		try (Jedis jedis = _jedisPool.getResource()) {
			jedis.set(key.getBytes(), _serialize(value));
		}
	}

	@Override
	public void setMap(String key, Map<String, String> value) {
		try (Jedis jedis = _jedisPool.getResource()) {
			jedis.hset(key, value);
		}
	}

	@Activate
	protected void activate(Map<String, Object> properties) {
		_redisConnectionConfiguration = ConfigurableUtil.createConfigurable(
			RedisConnectionConfiguration.class, properties);

		modified(properties);
	}

	@Modified
	protected void modified(Map<String, Object> properties) {
		_jedisPool = new JedisPool(
			_redisConnectionConfiguration.host(),
			_redisConnectionConfiguration.port());
	}

	private Object _deserialize(byte[] bytes) {
		ByteArrayInputStream byteArrayInputStream = null;

		try {
			byteArrayInputStream = new ByteArrayInputStream(bytes);

			ProtectedObjectInputStream protectedObjectInputStream =
				new ProtectedObjectInputStream(byteArrayInputStream);

			return protectedObjectInputStream.readObject();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return null;
	}

	private byte[] _serialize(Object object) {
		ObjectOutputStream objectOutputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;

		try {
			byteArrayOutputStream = new ByteArrayOutputStream();

			objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

			objectOutputStream.writeObject(object);

			return byteArrayOutputStream.toByteArray();
		}
		catch (Exception exception) {
			_log.error(exception, exception);
		}

		return null;
	}

	private static final Log _log = LogFactoryUtil.getLog(
		RedisConnectionImpl.class);

	private volatile JedisPool _jedisPool;
	private volatile RedisConnectionConfiguration _redisConnectionConfiguration;

}