package com.poi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShape;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTMarker;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class TuhuImport {

  public static void main(String[] args) {
    TuhuDaoImpl dao = new TuhuDaoImpl();
    List<List<String>> bigList = new ArrayList<>();
    try {
      File xlsFile = new File("E:/tuhu/途虎养车&vivo浏览器搜索-v3(1).xlsx");
      Workbook workbook = WorkbookFactory.create(xlsFile);
      int sheetCount = workbook.getNumberOfSheets();
      for (int i = 0; i < sheetCount; i++) {
        Sheet sheet = workbook.getSheetAt(i);
        int rows = sheet.getLastRowNum() + 1;
        Row tmp = sheet.getRow(0);
        if (tmp == null) {
          continue;
        }
        Map<String,PictureData> mapList = null;
        mapList = getPictures2((XSSFSheet) sheet);
        printImg(mapList);


        int cols = tmp.getPhysicalNumberOfCells();
        //不读取第一行表头
        for (int row = 1; row < rows; row++) {
          Row r = sheet.getRow(row);
          List<String> smailList = new ArrayList<>();
          for (int col = 0; col < cols; col++){
            if(r.getCell(col) == null || "".equals(r.getCell(col))){
              continue;
            }
            //System.out.printf(r.getCell(col).getStringCellValue() + "           ");
            smailList.add(r.getCell(col).getStringCellValue());
          }
          smailList.add(getBase64("D:\\img\\pic" + row + "-2.jpg"));
          //System.out.println();
          bigList.add(smailList);
        }

      }
      dao.insertOrUpdate(bigList);

    } catch (IOException e) {
      e.printStackTrace();
    } catch (InvalidFormatException e) {
      e.printStackTrace();
    }

  }
  /**
   * 获取图片和位置 (xlsx)
   * @param sheet
   * @return
   * @throws IOException
   */
  public static Map<String, PictureData> getPictures2 (XSSFSheet sheet) throws IOException {
    Map<String, PictureData> map = new HashMap<String, PictureData>();
    List<POIXMLDocumentPart> list = sheet.getRelations();
    for (POIXMLDocumentPart part : list) {
      if (part instanceof XSSFDrawing) {
        XSSFDrawing drawing = (XSSFDrawing) part;
        List<XSSFShape> shapes = drawing.getShapes();
        for (XSSFShape shape : shapes) {
          XSSFPicture picture = (XSSFPicture) shape;
          XSSFClientAnchor anchor = picture.getPreferredSize();
          CTMarker marker = anchor.getFrom();
          String key = marker.getRow() + "-" + marker.getCol();
          map.put(key, picture.getPictureData());
        }
      }
    }
    return map;
  }

  /**
  * @Description:图片写出
  * @param: sheetList
  * @return: void
  * @Date: 2018/12/11
  */
  public static void printImg(Map<String, PictureData> sheetList) throws IOException {

    //for (Map<String, PictureData> map : sheetList) {
    Object key[] = sheetList.keySet().toArray();
    for (int i = 0; i < sheetList.size(); i++) {
      // 获取图片流
      PictureData pic = sheetList.get(key[i]);
      // 获取图片索引
      String picName = key[i].toString();
      // 获取图片格式
      String ext = pic.suggestFileExtension();

      byte[] data = pic.getData();

      //图片保存路径
      FileOutputStream out = new FileOutputStream("D:/img/pic" + picName + "." + "jpg");
      //FileOutputStream out = new FileOutputStream("D:\\img\\pic" + i + "." + "jpg");
      out.write(data);
      out.close();
    }
    // }

  }

  /**
  * @Description: base64编码
  * @param: path
  * @return: java.lang.String
  * @Date: 2018/12/11
  */
  public static String getBase64(String path){
    InputStream inputStream = null;
    byte[] data = null;
    try {
      inputStream = new FileInputStream(path);
      data = new byte[inputStream.available()];
      inputStream.read(data);
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    BASE64Encoder encoder = new BASE64Encoder();
    return encoder.encode(data);
  }

  public static boolean base64Decode(String imgStr,String path){
    BASE64Decoder decoder = new BASE64Decoder();
    try {
      byte[] b = decoder.decodeBuffer(imgStr);
      for(int i = 0;i<b.length;i++){
        if(b[i] < 0){
          b[i] += 256;
        }
      }
      OutputStream out = new FileOutputStream(path);
      out.write(b);
      out.flush();
      out.close();
      return true;
    } catch (IOException e) {
      e.printStackTrace();
      return false;
    }

  }


}
