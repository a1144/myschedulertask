package com.shuangyu.service.impl;

import com.shuangyu.crawler.BbsTiandaoCrawler;
import com.shuangyu.model.BbsTiandaoEmailSummary;
import com.shuangyu.repository.BbsTiandaoEmailRepository;
import com.shuangyu.service.BbsTiandaoEmailSummaryService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsTiandaoEmailSummaryServiceImpl implements BbsTiandaoEmailSummaryService {
    @Autowired
    private BbsTiandaoEmailRepository bbsTiandaoEmailRepository;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public List<BbsTiandaoEmailSummary> listBbsTiandaoEmailSummary() {
        return null;
    }

    @Override
    public BbsTiandaoEmailSummary findBbsTiandaoEmailSummaryById() {
        //return bbsTiandaoEmailRepository.findById(1L);
        return null;
    }

    @Override
    public void saveBbsTianDaoEmailSummaryList(List<BbsTiandaoEmailSummary> bbsTiandaoEmailSummaryList) {
        rabbitTemplate.convertAndSend("bbsTopicExchange","topic.duowan.tiandao.email",bbsTiandaoEmailSummaryList);
    }

    @Override
    public void delete(long id) {

    }

    //以下任务每隔6秒执行一次
    @Scheduled(fixedRate = 6000)
    public void sendBbsTiandaoEmailSummary(){
        this.saveBbsTianDaoEmailSummaryList(BbsTiandaoCrawler.topPostCrawler());
    }

}
