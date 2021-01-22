package com.beaninj.demo;

import com.beaninj.demo.service.ContentService;
import com.beaninj.demo.util.CreatEsIndex;
import com.beaninj.demo.util.HtmlParseUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private HtmlParseUtil htmlParseUtil;
    @Autowired
    private CreatEsIndex creatEsIndex;
    @Autowired
    private ContentService contentService;

    @Test
    void contextLoads() throws IOException {
        htmlParseUtil.parseJD("java").forEach(System.out::println);
    }

    @Test
    void craatIndex() throws IOException {
        creatEsIndex.checkAndCreat("tset7");
    }

    @Test
    void testBuliEs() throws IOException {
        Boolean isExi = contentService.parseContent("java");
    }
}
