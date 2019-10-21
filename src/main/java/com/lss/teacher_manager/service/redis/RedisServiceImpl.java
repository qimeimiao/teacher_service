package com.lss.teacher_manager.service.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class RedisServiceImpl implements RedisServer {
	
	@Autowired
	private RedisTemplate<String, ?> redisTemlate;
	
	
	@Override
	 public boolean set(String key, String value) {
		return redisTemlate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemlate.getStringSerializer();
				connection.set(serializer.serialize(key), serializer.serialize(value));
				return true;
			}
		});
	}

	@Override
	public String get(String key) {
		return redisTemlate.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemlate.getStringSerializer();
				byte[] bs = connection.get(serializer.serialize(key));
				return serializer.deserialize(bs);
			}
		});
	}

	@Override
	public void expier(String key, long time) {
		redisTemlate.expire(key, time, TimeUnit.SECONDS);
		
	}

	@Override
	public boolean delete(String key) {
		return redisTemlate.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = redisTemlate.getStringSerializer();
				connection.del(serializer.serialize(key));
				return true;
			}
		});
	}
}
