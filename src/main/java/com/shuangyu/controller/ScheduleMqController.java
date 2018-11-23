package com.shuangyu.controller;

import com.shuangyu.producer.ScheduleSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduleRabbitmq")
public class ScheduleMqController {
    @Autowired
    private ScheduleSender scheduleSender;

    @RequestMapping(value = "/send" ,method = RequestMethod.GET)
    public void sendMessage(){
        scheduleSender.send();
    }


}
