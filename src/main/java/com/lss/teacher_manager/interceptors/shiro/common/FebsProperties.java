package com.lss.teacher_manager.interceptors.shiro.common;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Configuration
@ConfigurationProperties(prefix = "febs")
@Component
public class FebsProperties {
    private ShiroProperties shiro = new ShiroProperties();
    private boolean openAopLog = true;
}
