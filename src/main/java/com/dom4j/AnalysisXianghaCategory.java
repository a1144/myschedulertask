package com.dom4j;

import com.poi.utils.JdbcTemplateUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.ArrayUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.springframework.jdbc.core.JdbcTemplate;

public class AnalysisXianghaCategory {
  public static void main(String[] args){
    long start = System.currentTimeMillis();
    JdbcTemplate jdbcTemplate = JdbcTemplateUtils.getJdbcTemplate();
    String urlPath = "http://xml.xiangha.com/xiangha/vivo/caipu/fenlei.xml";
    String filePath = "E:\\tuhu香哈\\xianghaCategory.xml";
    //DownLoadByUrl.downLoadByUrl(urlPath,filePath);
    String sql = "insert ignore into t_xiangha_category (lastmod,name,title,web_url,app_url,image,thumbnail_url,"
        + "related_recipe_count,view_count,brand,logo,show_url,m_show_url) values (?,?,?,?,?,?,?,?,?,?,?,?,?) on "
        + "duplicate key update lastmod=?,name=?,title=?,web_url=?,app_url=?,image=?,thumbnail_url=?,related_recipe_count=?,"
        + "view_count=?,brand=?,logo=?,show_url=?,m_show_url=?";
    SAXReader saxReader = new SAXReader();
    //File file = new File(filePath);
    Document document = null;

    try {
      document = saxReader.read(urlPath);
      //Element root = document.getRootElement();
      List<Node> itemList = document.selectNodes("urlset/item");
      //List<List<String>> listList = new ArrayList<>(1200);
      for(Node node : itemList){
        List<String> list1 = new ArrayList<>();
        list1.add(node.selectSingleNode("lastmod").getText());
        list1.add(node.selectSingleNode("display/name").getText());
        list1.add(node.selectSingleNode("display/title").getText());
        list1.add(node.selectSingleNode("display/weburl").getText());
        list1.add(node.selectSingleNode("display/appurl").getText());
        list1.add(node.selectSingleNode("display/image").getText());
        list1.add(node.selectSingleNode("display/thumbnailUrl").getText());
        list1.add(node.selectSingleNode("display/relatedRecipeCount").getText());
        list1.add(node.selectSingleNode("display/viewCount").getText());
        list1.add(node.selectSingleNode("display/provider/brand").getText());
        list1.add(node.selectSingleNode("display/provider/logo").getText());
        list1.add(node.selectSingleNode("display/provider/showurl").getText());
        list1.add(node.selectSingleNode("display/provider/mShowurl").getText());
        jdbcTemplate.update(sql, ArrayUtils.addAll(list1.toArray(),list1.toArray()));
        //listList.add(list1);
      }
      long end = System.currentTimeMillis();
      System.out.println("运行时长：" + (end - start) + "ms");





      /*List<Map<String,String>> list = new ArrayList<>(1200);
      parserNode(root);
      System.out.println(list.size());*/
    } catch (DocumentException e) {
      e.printStackTrace();
    }

  }
/**
* @Description: 遍历所有节点
* @param: root 
* @return: void 
* @Date: 2018/12/12 
*/ 
  public static void parserNode(Element root){
    Map<String,String> map = new HashMap<>();
    List<String> list = new ArrayList<>();
    System.out.println(root.getName() + " : " + root.getTextTrim());
    map.put(root.getName(),root.getTextTrim());
    List<Attribute> attributeList = root.attributes();
    for(Attribute attribute : attributeList){
      String name = attribute.getName();
      String value = attribute.getValue();
      System.out.println(name + "=" + value);
    }
    List<Element> elementList = root.elements();
    for(Element e : elementList){
      parserNode(e);
    }

  }


}
