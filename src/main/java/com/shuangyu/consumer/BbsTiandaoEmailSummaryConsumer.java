package com.shuangyu.consumer;

import com.shuangyu.model.BbsTiandaoEmailSummary;
import com.shuangyu.repository.BbsTiandaoEmailRepository;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "bbsQueue")
public class BbsTiandaoEmailSummaryConsumer {
    @Autowired
    private BbsTiandaoEmailRepository bbsTiandaoEmailRepository;

    @RabbitHandler
    public void process(BbsTiandaoEmailSummary bbsTiandaoEmailSummary){
        bbsTiandaoEmailRepository.save(bbsTiandaoEmailSummary);
    }
}
