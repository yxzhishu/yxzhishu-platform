package com.yxzhishu.platform.app.dictionary;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = YxzhishuPlatformAppDictionaryAutoConfiguration.class)
public class YxzhishuPlatformAppDictionaryAutoConfiguration {


    @PostConstruct
    public void init() {
        log.info("dictionary模块自动加载成功...");
    }

}
