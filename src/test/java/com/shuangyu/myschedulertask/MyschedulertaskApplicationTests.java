package com.shuangyu.myschedulertask;

import com.shuangyu.crawler.BbsTiandaoCrawler;
import com.shuangyu.model.BbsTiandaoEmailDetail;
import com.shuangyu.model.BbsTiandaoEmailSummary;
import com.shuangyu.service.BbsTiandaoEmailDetailService;
import com.shuangyu.service.BbsTiandaoEmailSummaryService;
import com.shuangyu.service.MailService;
import com.shuangyu.util.HtmlUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MyschedulertaskApplicationTests {
    private  final Logger log= LoggerFactory.getLogger(HtmlUtil.class);
    @Autowired
    private BbsTiandaoEmailSummaryService bbsTiandaoEmailSummaryService;
    @Autowired
    private BbsTiandaoEmailDetailService bbsTiandaoEmailDetailService;
    @Autowired
    private MailService mailService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSave(){
        long start = System.currentTimeMillis();
       /* BbsTiandaoEmailSummary bbsTiandaoEmailSummary = new BbsTiandaoEmailSummary();
        bbsTiandaoEmailSummary.setCreateTime(new Date());
        bbsTiandaoEmailSummary.setInsertTime(new Date());
        bbsTiandaoEmailSummary.setAuthor("白白白霜余");
        List<BbsTiandaoEmailSummary> bbsTiandaoEmailSummaryList = null;*/
        bbsTiandaoEmailSummaryService.saveBbsTianDaoEmailSummaryList(BbsTiandaoCrawler.topPostCrawler());
        long end = System.currentTimeMillis();
        System.out.println("运行时长：" + (end - start) + "ms");
    }

    @Test
    public void testSendSimpleMail(){
        mailService.sendSimpleMail("m17854282392@163.com","测试简单邮件","测试test。。。。");
    }

    @Test
    public void testSendHtmlMail(){
        String imgPath = "https://img-blog.csdn.net/20180815211525656?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTAzMzQ0Mw==";
        String content = "<html>\n" +
            "<body>\n" +
            "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
            "<img src=\'" + imgPath + "\'></img>"+
            "</body>\n" +
            "</html>";
        mailService.sendHtmlMail("m17854282392@163.com","测试简单html邮件",content);
    }

    @Test
    public void testSendAttachmentsMail(){
        //String filePath = "E:\\testAttachmentsMail.PNG";
        String filePath = "E:\\testQiChaCha.txt";
        //失败
        //String filePath = "https://img-blog.csdn.net/20180815211525656?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3dlaXhpbl8zOTAzMzQ0Mw==";
        mailService.sendAttachmentsMail("m17854282392@163.com","带附件的邮件哦","测试一下",filePath);
    }

    @Test
    public void sendInlineResourceMail(){
        String rscId = "b001";
        String imgPath = "E:\\testAttachmentsMail.PNG";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        mailService.sendInlineResourceMail("m17854282392@163.com","有本地资源图片的邮件哦",content,imgPath,rscId);
    }



}
