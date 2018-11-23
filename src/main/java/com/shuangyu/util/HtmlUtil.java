package com.shuangyu.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HtmlUtil {
    private static final Logger log= LoggerFactory.getLogger(HtmlUtil.class);
    public static Document getHtmlTextByUrl(String url){

        Document document = null;
        try{
            int random = (int)(Math.random()*3000);
            Thread.sleep(random);
            document = Jsoup.connect(url).timeout(3000).get();
        }catch (Exception e){
            try {
                int random = (int)(Math.random()*5000);
                Thread.sleep(random);
                document = Jsoup.connect(url).timeout(3000).get();
            }catch (Exception ex){
                ex.printStackTrace();
                log.error(e.getMessage());
            }
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return document;
    }

}
