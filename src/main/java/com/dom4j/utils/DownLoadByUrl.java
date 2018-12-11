package com.dom4j.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownLoadByUrl {
  public static void downLoadByUrl(String urlPath,String filePath){
    try {
      //Document document = Jsoup.connect("http://xml.xiangha.com/xiangha/vivo/caipu/fenlei.xml").get();
      URL url = new URL(urlPath);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      DataInputStream in = new DataInputStream(connection.getInputStream());
      DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath));
      byte[] buffer = new byte[4096];
      int count = 0;
      while((count = in.read(buffer)) > 0){
        out.write(buffer,0,count);
      }
      out.close();
      in.close();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
