package com.lss.teacher_manager.service;

import com.alibaba.fastjson.JSONObject;
import com.lss.teacher_manager.pojo.user.ManagerUserDto;
import com.lss.teacher_manager.service.redis.RedisServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class BaseUserService {
    @Autowired
    RedisServiceImpl redisServer;


    public ManagerUserDto getCurrentManagerUser(){
        String token = (String) getHeader("token");
        String s = redisServer.get(token);
        ManagerUserDto user = JSONObject.parseObject(s, ManagerUserDto.class);
        return user;
    }




    public  Object  getHeader(String key){
        return getCurrentRequest().getHeader(key);
    }

    protected HttpServletRequest getCurrentRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
        return servletRequest;
    }
}
