package com.mike.study.springbootbasesecurity.annotation;

/**
 * @Classname URLAnnotation
 * @Created by Michael
 * @Date 2022/1/23
 * @Description 权限操作类型
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface URLAnnotation {
  /**
   * 操作类型(type):添加,删除,修改,插入
   *
   */
  String type();
}
