package com.mike.study.dynamicschedule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @Classname SchedulingConfig
 * @Created by Michael
 * @Date 2021/10/19
 * @Description the configuration of Schedule
 */
@Configuration
public class SchedulingConfig {
  @Bean
  public TaskScheduler taskScheduler(){
    ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    //定时任务执行线程核心线程数
    taskScheduler.setPoolSize(4);
    taskScheduler.setRemoveOnCancelPolicy(true);
    taskScheduler.setThreadNamePrefix("TaskSchedulerThreadPool-");
    return  taskScheduler;
  }
}
