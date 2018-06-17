package com.treasureHunt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * @Description 类描述：多线程执行定时任务
 * @Author 创建人：linying
 * @Date 创建时间：2018/4/18 11:03
 * @Version 版本号：v1.0.0
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        //设定一个长度10的定时任务线程池
        scheduledTaskRegistrar.setScheduler(Executors.newScheduledThreadPool(10));
    }
}
