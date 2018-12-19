package com.thread;

import com.poi.utils.JdbcTemplateUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.jdbc.core.JdbcTemplate;

public class AnalysisXianghaMenuWithThread {
  public static void main(String[] args){
    long start = System.currentTimeMillis();
    //String menuUrl = "http://xml.xiangha.com/xiangha/vivo/caipu/data1.xml";
    String htmlUrl = "http://xml.xiangha.com/xiangha/vivo/caipu/sitemap.xml";
    String sql = "insert ignore into t_xiangha_menu (loc,lastmod,source_id,title,name,description,web_url,app_url,image,thumbnail_url,"
        + "cooking_tips,difficulty,instructions_count,instructions,author_name,author_url,date_published,aggregate_rating,view_count,brand,"
        + "logo,show_url,m_show_url) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) on duplicate key update loc=?,lastmod=?,source_id=?,"
        + "title=?,name=?,description=?,web_url=?,app_url=?,image=?,thumbnail_url=?,cooking_tips=?,difficulty=?,instructions_count=?,"
        + "instructions=?,author_name=?,author_url=?,date_published=?,aggregate_rating=?,view_count=?,brand=?,logo=?,show_url=?,m_show_url=?";
    JdbcTemplate jdbcTemplate = JdbcTemplateUtils.getJdbcTemplate();
    //String filePath = "E:\\tuhu香哈\\xianghaMenuHtml.xml";
    //DownLoadByUrl.downLoadByUrl(url,filePath);
    SAXReader saxReader = new SAXReader();
    Document document = null;
    try {
      document = saxReader.read(htmlUrl);
      List<Node> nodes = document.selectNodes("sitemapindex/sitemap/loc");
      List<String> urlList = new ArrayList<>(600);
      for(Node node : nodes){
        urlList.add(node.getText());
      }
      Thread thread1 = new Thread(()-> analysis(urlList.subList(0,100),sql));
      Thread thread2 = new Thread(()-> analysis(urlList.subList(100,200),sql));
      Thread thread3 = new Thread(()-> analysis(urlList.subList(200,300),sql));
      Thread thread4 = new Thread(()-> analysis(urlList.subList(300,400),sql));
      Thread thread5 = new Thread(()-> analysis(urlList.subList(400,500),sql));
      Thread thread6 = new Thread(()-> analysis(urlList.subList(500,urlList.size()),sql));

      thread1.start();
      thread2.start();
      thread3.start();
      thread4.start();
      thread5.start();
      thread6.start();

      //System.out.println(urlList.size());

    } catch (DocumentException e) {
      e.printStackTrace();
    }

    long end = System.currentTimeMillis();
    System.out.println("运行时长：" + (end - start) + "ms");

  }

  public static void analysis(List<String> urlList,String sql){
    JdbcTemplate jdbcTemplate = JdbcTemplateUtils.getJdbcTemplate();
    Document document = null;
    SAXReader saxReader = new SAXReader();
    for(String url : urlList){
      try {
        document = saxReader.read(url);
      } catch (DocumentException e) {
        e.printStackTrace();
      }
      List<Node> nodes1 = document.selectNodes("urlset/url");
      for(Node node : nodes1){
        List<String> list = new ArrayList<>();
        list.add(node.selectSingleNode("loc").getText());
        list.add(node.selectSingleNode("lastmod").getText());

        Node node1 = node.selectSingleNode("data/display");
        list.add(node1.selectSingleNode("id").getText());
        list.add(node1.selectSingleNode("title").getText());
        list.add(node1.selectSingleNode("name").getText());
        list.add(node1.selectSingleNode("description").getText());
        list.add(node1.selectSingleNode("weburl").getText());
        list.add(node1.selectSingleNode("appurl").getText());
        list.add(node1.selectSingleNode("image").getText());
        list.add(node1.selectSingleNode("thumbnailUrl").getText());
        list.add(node1.selectSingleNode("cookingTips").getText());
        list.add(node1.selectSingleNode("difficulty").getText());
        list.add(node1.selectSingleNode("instructionsCount").getText());

        List<Node> nodes2 = node1.selectNodes("instructions");
        StringBuffer stringBuffer = new StringBuffer();
        for(Node node2 : nodes2){
          stringBuffer.append("description:").append(node2.selectSingleNode("description").getText());
          stringBuffer.append("image:").append(node2.selectSingleNode("image").getText());
          stringBuffer.append("index:").append(node2.selectSingleNode("index").getText());
        }
        list.add(stringBuffer.toString());
        list.add(node1.selectSingleNode("author/name").getText());
        list.add(node1.selectSingleNode("author/url").getText());
        list.add(node1.selectSingleNode("datePublished").getText());
        list.add(node1.selectSingleNode("aggregateRating/ratingValue").getText());
        list.add(node1.selectSingleNode("aggregateRating/viewCount").getText());
        list.add(node1.selectSingleNode("provider/brand").getText());
        list.add(node1.selectSingleNode("provider/logo").getText());
        list.add(node1.selectSingleNode("provider/showurl").getText());
        list.add(node1.selectSingleNode("provider/mShowurl").getText());

        jdbcTemplate.update(sql, ArrayUtils.addAll(list.toArray(),list.toArray()));
      }
    }
  }


}
