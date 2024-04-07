package com.yxzhishu.platform.app.start;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class YxzhishuPlatformAppStartApplicationTests {

    @Test
    void contextLoads() {
        List<String> a = new ArrayList<>();
        a.add("1");
        aaa(a);
        System.out.println(a);
    }
    void aaa(List<String> c){
        List<String> b = new ArrayList<>();
        b.add("123");
        c =b;
    }
}
