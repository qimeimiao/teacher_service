package com.lss.teacher_manager.interceptors.web;

import com.alibaba.fastjson.JSON;
import com.lss.teacher_manager.utils.LoggerUtils;
import com.lss.teacher_manager.utils.SysUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CommonInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-Requested-With,token,lang");
		response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		response.setHeader("Access-Control-Request-Methods", "GET,POST");
		String method = request.getMethod();
		if ("POST".equals(method)) {
		} else if ("GET".equals(method)) {
		} else {
			//LoggerUtils.log.debug("Method:"+method+" not allowed");
			return false;
		}
		Thread.currentThread().setName(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ SysUtils.REQUEST_COUNT.incrementAndGet());
		LoggerUtils.log.info("URL:" + request.getRequestURL()+",METHOD:"+method+",Params:" + JSON.toJSONString(request.getParameterMap()));
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
