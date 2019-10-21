package com.lss.teacher_manager.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public  void setExpire(String key, Object object, long time){
        redisTemplate.opsForValue().set( key, JSONObject.toJSONString(object),time, TimeUnit.SECONDS);
    }

    public   Object get(String key){
        return redisTemplate.opsForValue().get(key);

    }

    public   void removeKey(String key){
        redisTemplate.delete(key);
    }
}
