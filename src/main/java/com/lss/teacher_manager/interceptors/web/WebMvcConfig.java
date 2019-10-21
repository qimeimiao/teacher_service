package com.lss.teacher_manager.interceptors.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;


@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor()).addPathPatterns("/api/**");
        registry.addInterceptor(new TokenInterceptor()).addPathPatterns("/api/**").
                excludePathPatterns("/api/user/get/smscoad", "/api/user/sms/registor",
                        "/api/user/accountLogin","/api/user/wxlogin/openid", "/api/user/wxlogin",
                        "/api/user/bindphone","/api/user/sendemail","/api/order/wxpay/callback",
                        "/api/project/public/list","/api/order/platform/data",
                        "/api/project/allprojecttype","/api/project/queryById",
                        "/api/common/config","/api/project/novice/exclusive",
                        "/api/project/team","/api/user/userinfoid","/api/user/resetpassword",
                        "/api/user/webpagelist","/api/user/webpageinfo","/api/common/config/value",
                        "/api/order/platform/data","/api/project/search/hot");

    }



    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }


    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }


    @Bean
    public MappingJackson2HttpMessageConverter messageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(getObjectMapper());
        return converter;
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //解决中文乱码
        converters.add(responseBodyConverter());
        //解决 添加解决中文乱码后 上述配置之后，返回json数据直接报错 500：no convertter for return value of type
        converters.add(messageConverter());
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
        String url = "D:\\zhongchoupng\\";
        registry.addResourceHandler("/**").addResourceLocations("file:D:\\zhongchou\\static\\");
        registry.addResourceHandler("/manage/**").addResourceLocations("file:D:\\yagu_manager\\dist\\");
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/", "file:" + url);
    }
}
