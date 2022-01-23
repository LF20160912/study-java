package com.mike.study.dynamicschedule.task;

import java.util.concurrent.ScheduledFuture;

/**
 * @Classname ScheduledTask
 * @Created by Michael
 * @Date 2021/10/20
 * @Description 调度任务
 */
public final class ScheduledTask {
  volatile ScheduledFuture<?> future;

  /**
   * 取消定时任务
   */
  public void cancel() {
    ScheduledFuture<?> future = this.future;
    if (future != null) {
      future.cancel(true);
    }
  }
}
