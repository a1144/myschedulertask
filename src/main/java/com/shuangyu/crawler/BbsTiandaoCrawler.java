package com.shuangyu.crawler;


import com.shuangyu.model.BbsTiandaoEmailSummary;
import com.shuangyu.util.HtmlUtil;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BbsTiandaoCrawler {
    /**
     * @Description: 多玩论坛热帖抓取
     * @param:
     * @return: java.lang.Object
     * @Date: 2018/11/23
     */
    public static List<BbsTiandaoEmailSummary> topPostCrawler() {
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
            //System.out.println(link);
        }
        bbsTiandaoEmailSummaryList = getSummary(detailUrl);
        return bbsTiandaoEmailSummaryList;
    }

    public static void main(String[] args) {


       /* for (BbsTiandaoEmailSummary bbsTiandaoEmailSummary : topPostCrawler()) {
            System.out.println(bbsTiandaoEmailSummary);
        }*/
       topPostCrawler();

    }

    /**
    * @Description: 开始爬取每个热帖数据
    * @param: urlList 热帖链接
    * @return: java.util.List<com.shuangyu.model.BbsTiandaoEmailSummary>
    * @Date: 2018/11/24
    */
    public static List<BbsTiandaoEmailSummary> getSummary(List<String> urlList) {
        List<BbsTiandaoEmailSummary> bbsTiandaoEmailSummaryList = new ArrayList<>();
        for (String url : urlList) {
            BbsTiandaoEmailSummary bbsTiandaoEmailSummary = new BbsTiandaoEmailSummary();
            Document document = null;
            document = HtmlUtil.getHtmlTextByUrl(url);
            //System.out.println(document);
            //System.out.println(document.getElementsByClass("postlist").first());
            Element authorContent = document.getElementById("postlist").getElementsByTag("div").first();

            String author = authorContent.getElementsByClass("authi").first().getElementsByTag("a").first().ownText();
            //System.out.println(author);
            String createTime = authorContent.getElementsByTag("table").get(2).getElementsByClass("authi").last().
                    getElementsByTag("em").first().getElementsByTag("span").attr("title");
            //System.out.println(createTime);

            String title = authorContent.getElementById("thread_subject").ownText();
            //System.out.println(title);

            String type = authorContent.getElementsByClass("ts z").first().getElementsByTag("a").first().text();
            //System.out.println(type);

            String content = authorContent.getElementsByClass("t_f").first().text();
            System.out.println(content);

            bbsTiandaoEmailSummary.setType(type);
            bbsTiandaoEmailSummary.setTitle(title);
            bbsTiandaoEmailSummary.setAuthor(author);
            bbsTiandaoEmailSummary.setLink(url);
            try {
                bbsTiandaoEmailSummary.setCreateTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(createTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            bbsTiandaoEmailSummaryList.add(bbsTiandaoEmailSummary);
        }

        return bbsTiandaoEmailSummaryList;
    }

}
