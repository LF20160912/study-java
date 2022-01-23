package com.mike.study.dynamicschedule.Utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Classname SpringContextUtils
 * @Created by Michael
 * @Date 2021/10/20
 * @Description Spring context util clas
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
  private static ApplicationContext applicationContext;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    SpringContextUtils.applicationContext = applicationContext;
  }

  public static Object getBean(String name) {
    return applicationContext.getBean(name);
  }

  public static <T> T getBean(Class<T> requireType) {
    return applicationContext.getBean(requireType);
  }

  public static <T> T getBean(String name, Class<T> requireType) {
    return applicationContext.getBean(name, requireType);
  }

  public static boolean containBean(String name) {
    return applicationContext.containsBean(name);
  }

  public static boolean isSingleton(String name) {
    return applicationContext.isSingleton(name);
  }

  public static Class<? extends Object> getType(String name) {
    return applicationContext.getType(name);
  }
}
