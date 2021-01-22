package com.beaninj.demo.util;

import com.beaninj.demo.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class HtmlParseUtil {
    public List<Content> parseJD(String keywords) throws IOException {
        String url = "https://search.jd.com/Search?keyword=" + keywords;
        Document html = Jsoup.parse(new URL(url), 30000);
        Element div = html.getElementById("J_goodsList");
        Elements lis = div.getElementsByTag("li");

        ArrayList<Content> goodsList = new ArrayList<>();
        for (Element li : lis) {
            String imgURL = li.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = li.getElementsByClass("p-price").eq(0).text();
            String title = li.getElementsByClass("p-name").eq(0).text();

            Content content = new Content();
            content.setImg(imgURL);
            content.setPrice(price);
            content.setTitle(title);
            goodsList.add(content);
        }
        return goodsList;
    }
}
