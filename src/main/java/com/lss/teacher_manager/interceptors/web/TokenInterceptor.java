package com.lss.teacher_manager.interceptors.web;


import com.lss.teacher_manager.exception.BussinessException;
import com.lss.teacher_manager.service.redis.RedisServiceImpl;
import com.lss.teacher_manager.utils.LoggerUtils;
import com.lss.teacher_manager.utils.RedisUtil;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    RedisServiceImpl redisServer;

    @Value("${spring.redis.timeout}")
    long expireTokenTime;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        ApiOperation mPermissions = handlerMethod.getMethod().getAnnotation(ApiOperation.class);
        String notes = "";
        if (mPermissions != null) {
            LoggerUtils.log.info("实现了权限接口:" + mPermissions.notes());
            notes = mPermissions.notes();
        }
        String requestURL = request.getRequestURI();
        if (requestURL.contains("api-docs") || requestURL.contains("download")) {
            return true;
        }
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            LoggerUtils.log.error("request token required");
            throw new BussinessException(401, "用户信息过期");
            //return false;
        }
//        try {
//            Object o = redisServer.get(token);
//            if (o != null) {
//                UserDto userDto = JSONObject.parseObject(o.toString(), UserDto.class);
//                redisServer.expier(token, expireTokenTime);
//                AppSessionManager.put("loginUser", userDto);
//            } else {
//                throw new BussinessException(401, "用户信息过期");
//            }
//        } catch (Exception e) {
//            APIError.CUSTOM.set("登录错误");
//
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }

}
