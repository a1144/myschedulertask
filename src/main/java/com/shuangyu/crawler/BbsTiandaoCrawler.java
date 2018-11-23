package com.shuangyu.crawler;


import com.shuangyu.model.BbsTiandaoEmailSummary;
import com.shuangyu.util.HtmlUtil;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class BbsTiandaoCrawler {
    /**
     * @Description: 多玩论坛热帖抓取
     * @param:
     * @return: java.lang.Object
     * @Date: 2018/11/23
     */
    public static Object topPostCrawler() {
        long start = System.currentTimeMillis();
        String url = "http://bbs.duowan.com/forum-2400-1.html";
        List<String> detailUrl = new ArrayList<>();
        List<BbsTiandaoEmailSummary> bbsTiandaoEmailSummaryList = new ArrayList<>();
        Document document = HtmlUtil.getHtmlTextByUrl(url);
        Elements elements = document.getElementsByClass("module cl xl xl1");
        Element element = elements.get(0);
        for (Element element1 : element.getElementsByTag("a")) {
            String link = element1.attr("abs:href");
            detailUrl.add(link);
            System.out.println(link);
        }
        bbsTiandaoEmailSummaryList = getSummary(detailUrl);


        return null;
    }

    public static void main(String[] args) {
        topPostCrawler();
    }


    public static List<BbsTiandaoEmailSummary> getSummary(List<String> urlList) {

        for (String url : urlList) {
            Document document = null;
            document = HtmlUtil.getHtmlTextByUrl(url);
            //System.out.println(document);
            //System.out.println(document.getElementsByClass("postlist").first());
            Element authorContent = document.getElementById("postlist").getElementsByTag("div").first();

            String author = authorContent.getElementsByClass("authi").first().getElementsByTag("a").first().ownText();
            System.out.println(author);
            //System.out.println(authorContent);
            System.out.println(authorContent.getElementsByTag("table").get(0)/*.getElementsByClass("authi").first()*/);
            /*String createTime = authorContent.getElementsByTag("table").first().getElementsByClass("authi").get(1).
                    getElementsByTag("em").first().getElementsByTag("em").first().getElementsByTag("span").
                    attr("title");*/
            int i = 1/0;
            //System.out.println(createTime);

        }

        return null;
    }

}
