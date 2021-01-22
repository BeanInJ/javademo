package com.beaninj.demo.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class HtmlUtil {
    public static void main(String[] args) throws IOException {
        // 连接上加上编码 https://search.jd.com/Search?keyword=java&enc=utf-8
        String url = "https://search.jd.com/Search?keyword=java";
        // 解析网页
        Document document = Jsoup.parse(new URL(url), 30000);
        // s所有你在js中可以使用的方法，这里都可以用,例：getElementById("")
        Element div = document.getElementById("J_goodsList");
        // div.html() 返回整个div的标签内容
        // 接下来获取所有li元素
        Elements lis = div.getElementsByTag("li");

        for (Element li : lis) {
            // 第一次直接获取图片url的时候，发现获取到的是空的
            // 原因：图片比较多的网站，图片是延迟加载的
            // 通过观察我们发现，图片是通过source-data-lazy-img加载的
            String imgURL = li.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = li.getElementsByClass("p-price").eq(0).text();
            String title = li.getElementsByClass("p-name").eq(0).text();
            System.out.println(imgURL);
        }

    }
//    public List<Object> parseJD(String keywords){
//
//    }
}
