package com.mike.study.dynamicschedule.demo;

import org.springframework.stereotype.Component;

/**
 * @Classname DemoTask
 * @Created by Michael
 * @Date 2021/10/l
 * @Description this is one demo for task
 */
@Component("demoTask")
public class DemoTask {

  public void testWithParams(String params){
    System.out.println("执行参数任务：" + params);
  }

  public void testNoParams(){
    System.out.println("执行无参任务");
  }
}
