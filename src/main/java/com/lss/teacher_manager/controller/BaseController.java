package com.lss.teacher_manager.controller;

import com.alibaba.fastjson.JSON;
import com.lss.teacher_manager.exception.BussinessException;
import com.lss.teacher_manager.mybatis.PageParameter;
import com.lss.teacher_manager.pojo.BaseResult;
import com.lss.teacher_manager.utils.LoggerUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseController {protected final Logger log = LoggerFactory.getLogger(getClass());

    private ThreadLocal<HttpServletRequest> request = new NamedThreadLocal<>("HttpServletRequest");

    private ThreadLocal<HttpServletResponse> response = new NamedThreadLocal<>("HttpServletResponse");


    public HttpServletRequest getRequest() {
        return request.get();
    }

    @ModelAttribute
    public void setRequest(HttpServletRequest request) {
        this.request.set(request);
    }


    protected final String getToken() {
        return getRequest().getHeader("token");
    }

    protected final Map<String, Object> getRequestHeader() {
        Map<String, Object> headers = new HashMap<>();
        headers.put("token", getToken());
        return headers;
    }

    public HttpServletResponse getResponse() {
        return response.get();
    }

    @ModelAttribute
    public void setResponse(HttpServletResponse response) {
        this.response.set(response);
    }

    protected String successResult() {
        BaseResult result = new BaseResult();
        result.setStatus(204);
        String str = JSON.toJSONString(result);
        return str;
    }


    protected String successResult(Object body) {
        String str = successResultNoLog(body);
        //LoggerUtils.log.debug("reponse:" + str);
        return str;
    }


    protected String successResultNoLog(Object body) {
        BaseResult result = new BaseResult();
        result.setBody(body);
        String str = JSON.toJSONString(result);
        return str;
    }


    protected String listResult(List<?> dataList, PageParameter page) {
        BaseResult result = new BaseResult();
        Map<String, Object> map = new HashMap<>();
        map.put("dataList", dataList);
        map.put("page", page);
        result.setBody(map);
        String str = JSON.toJSONString(result);
        //LoggerUtils.log.debug("reponse:" + str);
        return str;
    }

    protected String listResult(List<?> dataList) {
        BaseResult result = new BaseResult();
        Map<String, Object> map = new HashMap<>();
        map.put("dataList", dataList);
        result.setBody(map);
        String str = JSON.toJSONString(result);
        //LoggerUtils.log.debug("reponse:" + str);
        return str;
    }

    protected String newResult(int status, String message) {
        String str = JSON.toJSONString(new BaseResult(status, message));
        //LoggerUtils.log.debug("reponse:" + str);
        return str;
    }

    protected String newResult(int status, String message, Object body) {
        String str = JSON.toJSONString(new BaseResult(status, message, body));
        //LoggerUtils.log.debug("reponse:" + str);
        return str;
    }

    protected final <T extends Object> T getRequestBody(String requestBody, Class<T> t) {
        LoggerUtils.log.debug("requestBody:" + requestBody);
        try {
            return JSON.parseObject(requestBody, t);
        } catch (Exception e) {
            LoggerUtils.log.error("JSON.parseObject:", e);
            throw new BussinessException("not json",requestBody);
        }

    }

    protected final Map<String, Object> getRequestBody(String requestBody) {
        LoggerUtils.log.debug("requestBody:" + requestBody);
        try {
            return JSON.parseObject(requestBody);
        } catch (Exception e) {
            LoggerUtils.log.error("JSON.parseObject:", e);
            throw new BussinessException("not json",requestBody);
        }
    }

}

