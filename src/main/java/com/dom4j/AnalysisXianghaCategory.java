package com.dom4j;

import com.dom4j.utils.DownLoadByUrl;
import com.shuangyu.util.HtmlUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class AnalysisXianghaCategory {

  public static void main(String[] args){
    String urlPath = "http://xml.xiangha.com/xiangha/vivo/caipu/fenlei.xml";
    String filePath = "E:\\tuhu香哈\\xianghaCategory.xml";
    //DownLoadByUrl.downLoadByUrl(urlPath,filePath);

    SAXReader saxReader = new SAXReader();
    File file = new File(filePath);
    Document document = null;
    try {
      document = saxReader.read(file);
      Element root = document.getRootElement();
      parserNode(root);

    } catch (DocumentException e) {
      e.printStackTrace();
    }

  }

  public static void parserNode(Element root){
    System.out.println(root.getName() + " : " + root.getTextTrim());
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
