package com.yxzhishu.platform.app.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = YxzhishuPlatformAppBaseAutoConfiguration.class)
public class YxzhishuPlatformAppBaseAutoConfiguration {

    @PostConstruct
    public void init() {
        log.info("base模块自动加载成功...");
    }

}
