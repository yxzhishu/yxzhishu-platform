package com.yxzhishu.platform.app.temporary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = YxzhishuPlatformAppTemporaryAutoConfiguration.class)
public class YxzhishuPlatformAppTemporaryAutoConfiguration {

    @PostConstruct
    public void init() {
        log.info("temporary模块自动加载成功...");
    }

}
