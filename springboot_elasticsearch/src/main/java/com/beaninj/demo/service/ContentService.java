package com.beaninj.demo.service;

import com.alibaba.fastjson.JSON;
import com.beaninj.demo.pojo.Content;
import com.beaninj.demo.util.CreatEsIndex;
import com.beaninj.demo.util.HtmlParseUtil;
import org.apache.lucene.util.QueryBuilder;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class ContentService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    @Autowired
    private HtmlParseUtil htmlParseUtil;
    @Autowired
    private CreatEsIndex creatEsIndex;

    /**
     * @param keywords 查询的关键词
     * @return 返回是否插入数据成功
     * @throws IOException IO异常
     */
    public Boolean parseContent(String keywords) throws IOException {
        // 1、解析京东页面，返回imgurl、price、title
        List<Content> contents = htmlParseUtil.parseJD(keywords);
        // 2、创建索引
        creatEsIndex.checkAndCreat("jd_goods");
        // 3、imgurl、price、title批量插入到es中
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for (Content content : contents) {
            bulkRequest.add(new IndexRequest("jd_goods")
                    .source(JSON.toJSONString(content), XContentType.JSON));
        }
        BulkResponse bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        // 返回是否插入数据成功
        return !bulk.hasFailures();
    }

    public List<Map<String, Object>> searchPage(String keyword, int pageNo, int pageSize) throws IOException {
        if (pageNo <= 1) {
            pageNo = 1;
        }
        // 条件搜索
        SearchRequest jd_goods = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);
        // 精准匹配
        TermQueryBuilder title = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(title);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 执行搜素
        jd_goods.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(jd_goods, RequestOptions.DEFAULT);
        // 解析结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            list.add(hit.getSourceAsMap());
        }
        return list;
    }

    public List<Map<String, Object>> searchPageHigh(String keyword, int pageNo, int pageSize) throws IOException {
        if (pageNo <= 1) {
            pageNo = 1;
        }
        // 条件搜索
        SearchRequest jd_goods = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 分页
        searchSourceBuilder.from(pageNo);
        searchSourceBuilder.size(pageSize);
        // 精准匹配
        TermQueryBuilder title = QueryBuilders.termQuery("title", keyword);
        searchSourceBuilder.query(title);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        // 查询时将高亮加入
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.requireFieldMatch(false);   //关闭多个高亮显示
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        // 执行搜素
        jd_goods.source(searchSourceBuilder);
        SearchResponse searchResponse = restHighLevelClient.search(jd_goods, RequestOptions.DEFAULT);
        // 解析结果
        ArrayList<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit hit : searchResponse.getHits().getHits()) {
            // 解析高亮字段
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            HighlightField title1 = highlightFields.get("title");
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();    //这儿得到的是原来的结果
            // 将原来的字段替换为高亮字段
            if (title1 != null) {
                Text[] fragments = title1.fragments();
                String new_title1 = "";
                for (Text f : fragments) {
                    new_title1 += f;
                }
                sourceAsMap.put("title", new_title1);  // 替换
            }
            list.add(hit.getSourceAsMap());
        }
        return list;
    }
}
