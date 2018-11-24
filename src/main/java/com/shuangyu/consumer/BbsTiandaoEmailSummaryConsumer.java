package com.shuangyu.consumer;

import com.shuangyu.model.BbsTiandaoEmailDetail;
import com.shuangyu.model.BbsTiandaoEmailSummary;
//import com.shuangyu.repository.BbsTiandaoEmailDetailRepository;
import com.shuangyu.repository.BbsTiandaoEmailRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@RabbitListener(queues = "bbsQueue")
public class BbsTiandaoEmailSummaryConsumer {
    @Autowired
    private BbsTiandaoEmailRepository bbsTiandaoEmailRepository;
   /* @Autowired
    private BbsTiandaoEmailDetailRepository bbsTiandaoEmailDetailRepository;*/

    @RabbitHandler
    public void process(List<BbsTiandaoEmailSummary> bbsTiandaoEmailSummaryList){
        for(BbsTiandaoEmailSummary bbsTiandaoEmailSummary : bbsTiandaoEmailSummaryList){
            bbsTiandaoEmailSummary.setInsertTime(new Date());
            bbsTiandaoEmailRepository.save(bbsTiandaoEmailSummary);
        }
    }

    /*@RabbitHandler
    public void process(BbsTiandaoEmailDetail bbsTiandaoEmailDetail){
        bbsTiandaoEmailDetailRepository.save(bbsTiandaoEmailDetail);
    }*/
}
