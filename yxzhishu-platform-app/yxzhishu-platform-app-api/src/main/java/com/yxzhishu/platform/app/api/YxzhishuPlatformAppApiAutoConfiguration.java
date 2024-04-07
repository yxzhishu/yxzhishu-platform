package com.yxzhishu.platform.app.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
@Slf4j
@Configuration
@ComponentScan(basePackageClasses = YxzhishuPlatformAppApiAutoConfiguration.class)
public class YxzhishuPlatformAppApiAutoConfiguration {

    @PostConstruct
    public void init() {
        log.info("api模块自动加载成功...");
    }

}
