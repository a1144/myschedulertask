package com.shuangyu.myschedulertask.schedulerTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

//@Component
public class SchedulerTask1 {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    //以下任务每隔6秒执行一次
    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime(){
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }

}
