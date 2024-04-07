package com.yxzhishu.platform.app.start.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

/**
 * @classDesc:
 * @author: 王朔
 * @date: 2023/11/9 9:48
 * @remark:
 */
@Component
public class ConvertConfigure {
    @Bean
    public ObjectMapper jacksonObjectMapperConfig(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        //全局配置序列化返回json处理
        SimpleModule simpleModule = new SimpleModule();
        //json Long ==>String
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
