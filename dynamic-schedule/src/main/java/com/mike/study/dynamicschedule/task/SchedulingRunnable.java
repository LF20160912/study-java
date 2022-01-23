package com.mike.study.dynamicschedule.task;

import com.mike.study.dynamicschedule.Utils.SpringContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;


/**
 * @Classname SchedulingRunnable
 * @Created by Michael
 * @Date 2021/10/20
 * @Description the executor of the schedule
 */
public class SchedulingRunnable implements Runnable {
  private static final Logger logger = LoggerFactory.getLogger(SchedulingRunnable.class);

  private String beanName;
  private String methodName;
  private String params;

  public SchedulingRunnable(String beanName, String methodName) {
    this.beanName = beanName;
    this.methodName = methodName;
  }

  public SchedulingRunnable(String beanName, String methodName, String params) {
    this.beanName = beanName;
    this.methodName = methodName;
    this.params = params;
  }

  @Override
  public void run() {
    logger.info("定时任务开始执行：-- bean:{},方法：{},参数:{}", beanName, methodName, params);
    long startTime = System.currentTimeMillis();
    try {
      Object target = SpringContextUtils.getBean(beanName);

      Method method;

      if (StringUtils.isEmpty(params)) {
        method = target.getClass().getDeclaredMethod(methodName);
        ReflectionUtils.makeAccessible(method);
        method.invoke(target);
      } else {
        method = target.getClass().getDeclaredMethod(methodName, String.class);
        ReflectionUtils.makeAccessible(method);
        method.invoke(target, params);
      }

    } catch (Exception ex) {
      logger.error(String.format("定时任务执行异常 - bean：%s，方法：%s，参数：%s ",
              beanName, methodName, params), ex);
    }

    long times = System.currentTimeMillis() - startTime;
    logger.info("定时任务执行结束 - bean：{}，方法：{}，参数：{}，耗时：{} 毫秒", beanName,
            methodName, params, times);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;

    if (obj == null || getClass() != obj.getClass()) return false;

    SchedulingRunnable that = (SchedulingRunnable) obj;

    if (params == null) {
      return beanName.equals(that.beanName) &&
              methodName.equals(that.methodName) &&
              that.params == null;
    }
    return beanName.equals(that.beanName) &&
            methodName.equals(that.methodName) &&
            params.equals(that.params);
  }

  @Override
  public int hashCode() {
    if (params == null) {
      return Objects.hash(beanName, methodName);
    }
    return Objects.hash(beanName, methodName, params);
  }
}

