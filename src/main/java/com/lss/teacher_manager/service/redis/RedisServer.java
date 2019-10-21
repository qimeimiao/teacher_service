package com.lss.teacher_manager.service.redis;

public interface RedisServer {
	
	boolean set(String key, String value);
	
	String get(String key);
	
	void expier(String key, long time);
	
	boolean delete(String key);
}
