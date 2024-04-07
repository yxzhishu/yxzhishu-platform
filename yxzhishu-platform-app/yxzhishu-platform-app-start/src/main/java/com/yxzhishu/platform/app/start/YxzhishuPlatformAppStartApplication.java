package com.yxzhishu.platform.app.start;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@Slf4j
@SpringBootApplication
public class YxzhishuPlatformAppStartApplication {

    public static void main(String[] args) {
        SpringApplication.run(YxzhishuPlatformAppStartApplication.class, args);
        log.info("项目启动成功！");
    }

}
