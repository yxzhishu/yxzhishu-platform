package com.yxzhishu.platform.app.system;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Configuration
@ComponentScan(basePackageClasses = YxzhishuPlatformAppSystemAutoConfiguration.class)
//分模块导致mapper扫描不到
@MapperScan(basePackages = {"com.yxzhishu.platform.app.system.mapper"})
public class YxzhishuPlatformAppSystemAutoConfiguration {

    @PostConstruct
    public void init() {
        log.info("system模块自动加载成功...");
    }

}
