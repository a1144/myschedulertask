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

}
