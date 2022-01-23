package com.mike.study.dynamicschedule.task;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname CronTaskRegistar
 * @Created by Michael
 * @Date 2021/10/21
 * @Description the register of task
 */
public class CronTaskRegistar implements DisposableBean {
  private final Map<Runnable, ScheduledTask> scheduledTasks =
          new ConcurrentHashMap<>(16);

  @Autowired
  TaskScheduler taskScheduler;

  public TaskScheduler getTaskScheduler() {
    return taskScheduler;
  }

  public void addCronTask(Runnable task, String cronExpression) {
    addCronTask(new CronTask(task, cronExpression));
  }

  public void addCronTask(CronTask cronTask) {
    if (cronTask == null) {
      return;
    }

    Runnable task = cronTask.getRunnable();

    if (scheduledTasks.containsKey(task)) {
      removeCronTask(task);
    }

    this.scheduledTasks.put(task, scheduledCronTask(cronTask));
  }

  public void removeCronTask(Runnable task) {
    ScheduledTask scheduledTask = this.scheduledTasks.remove(task);
    if (scheduledTask != null) {
      scheduledTask.cancel();
    }
  }

  public ScheduledTask scheduledCronTask(CronTask cronTask) {
    ScheduledTask scheduledTask = new ScheduledTask();

    return scheduledTask;
  }

  @Override
  public void destroy() throws Exception {
    for (ScheduledTask task : this.scheduledTasks.values()) {
      task.cancel();
    }
    this.scheduledTasks.clear();
  }
}
