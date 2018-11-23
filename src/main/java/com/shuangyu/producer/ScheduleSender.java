package com.shuangyu.producer;

import com.shuangyu.model.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduleSender implements RabbitTemplate.ConfirmCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Scheduled(cron = "*/6 * * * * ?")
    public void send(){
        rabbitTemplate.setConfirmCallback(this);
        User user = new User();
        user.setName("白霜余");
        user.setAge(11);
        System.out.println("sender: " + user);
        CorrelationData correlationData = new CorrelationData(user.getName());
        System.out.println("correlationData: " + correlationData);
        this.rabbitTemplate.convertAndSend("exchange","topic.test",user,correlationData);
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        System.out.println("回调correlationData： " + correlationData);
    }
}
