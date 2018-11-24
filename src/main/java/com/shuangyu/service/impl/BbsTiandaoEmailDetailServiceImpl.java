package com.shuangyu.service.impl;

import com.shuangyu.model.BbsTiandaoEmailDetail;
import com.shuangyu.service.BbsTiandaoEmailDetailService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BbsTiandaoEmailDetailServiceImpl implements BbsTiandaoEmailDetailService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Override
    public List<BbsTiandaoEmailDetail> listBbsTiandaoEmailDetail() {
        return null;
    }

    @Override
    public BbsTiandaoEmailDetail findBbsTiandaoEmailDetailId() {
        return null;
    }

    @Override
    public void save(BbsTiandaoEmailDetail bbsTiandaoEmailDetail) {
        rabbitTemplate.convertAndSend("bbsTopicExchange","topic.duowan.tiandao.email",bbsTiandaoEmailDetail);
    }

    @Override
    public void delete(long id) {

    }
}
