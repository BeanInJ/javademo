package com.beaninj.demo.util;

import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CreatEsIndex {
    @Autowired
    @Qualifier("restHighLevelClient")
    private RestHighLevelClient rhc;

    public boolean check(String name) throws IOException {
        GetIndexRequest t = new GetIndexRequest(name);
        return rhc.indices().exists(t, RequestOptions.DEFAULT);
    }

    public void creat(String name) throws IOException {
        // 创建索引请求
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(name);
        // 发起请求，获取响应内容
        CreateIndexResponse createIndexResponse = rhc.indices().create(createIndexRequest, RequestOptions.DEFAULT);

        System.out.println(createIndexResponse.index() + "索引创建成功");

    }

    /**
     * 检查索引是否存在，不存在就创建
     *
     * @param name 传入的索引名
     * @throws IOException io异常
     */
    public void checkAndCreat(String name) throws IOException {
        if (!check(name)) {
            creat(name);
        } else {
            System.out.println(name + ":索引已经存在，无需再创建");
        }
    }
}
