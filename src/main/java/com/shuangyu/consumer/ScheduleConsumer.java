package com.shuangyu.consumer;

import com.shuangyu.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "queue")
public class ScheduleConsumer {
    @RabbitHandler
    public void process(User user){
        System.out.println("consumer: " + user);
    }


}
