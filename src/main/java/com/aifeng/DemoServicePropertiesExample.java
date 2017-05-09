package com.aifeng;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

public class DemoServicePropertiesExample {

    @Scheduled(cron = "${cron.expression}")
    public void demoServiceMethod() {
        System.out.println("Method executed at every 5 seconds. Current time is :: " + new Date());
    }

}
