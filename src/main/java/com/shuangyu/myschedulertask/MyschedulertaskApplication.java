package com.shuangyu.myschedulertask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com")
@EnableScheduling
public class MyschedulertaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyschedulertaskApplication.class, args);
    }
}
