package com.shuangyu.myschedulertask;

import com.shuangyu.model.BbsTiandaoEmailSummary;
import com.shuangyu.service.BbsTiandaoEmailSummaryService;
import com.shuangyu.util.HtmlUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyschedulertaskApplicationTests {
    private  final Logger log= LoggerFactory.getLogger(HtmlUtil.class);
    @Autowired
    private BbsTiandaoEmailSummaryService bbsTiandaoEmailSummaryService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSave(){
        BbsTiandaoEmailSummary bbsTiandaoEmailSummary = new BbsTiandaoEmailSummary();
        bbsTiandaoEmailSummary.setCreateTime(new Date());
        bbsTiandaoEmailSummary.setInsertTime(new Date());
        bbsTiandaoEmailSummary.setAuthor("白白白霜余");
        bbsTiandaoEmailSummaryService.save(bbsTiandaoEmailSummary);
    }

}
