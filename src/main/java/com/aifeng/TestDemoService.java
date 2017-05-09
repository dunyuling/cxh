package com.aifeng;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDemoService {

    private ClassPathXmlApplicationContext context;
    private boolean running = false;

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }

    public void setContext(ClassPathXmlApplicationContext context) {
        this.context = context;
    }

    public TestDemoService() {
        start();
    }

    public void start() {
        context = new ClassPathXmlApplicationContext("schedule-PropertiesConfig-example.xml");
        setRunning(true);
    }

    public void destroy() {
        if (context != null) {
            context.destroy();
            setRunning(false);
        }
    }

/*
    public static void main(String[] args) {
		new ClassPathXmlApplicationContext("schedule-basicUsageCron-example.xml");
		new ClassPathXmlApplicationContext("schedule-basicUsageFixedDelay-example.xml");
		new ClassPathXmlApplicationContext("schedule-xmlConfig-example.xml");
		new ClassPathXmlApplicationContext("schedule-PropertiesConfig-example.xml");
    }
*/
}
