package com.mike.study.springbootbasesecurity.config;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname Constant
 * @Created by Michael
 * @Date 2022/1/22
 * @Description 权限管理
 */
public class Constant {
  /**
   * 资源地址访问权限
   */
  public static Map<Integer,String[]> uriPermission =new HashMap<>();
  static {
    //用户1所拥有的URL权限
    String[] frist = {"/url1", "/url2", "/url3", "/url4", "/url5", "/url6", "/url7"};
    //用户2所拥有的URL权限
    String[] second = {"/url1", "/url2", "/url3", "/url4", "/url5"};
    //用户3所拥有的URL权限
    String[] third = {"/url1", "/url2", "/url3"};
    uriPermission.put(1, frist);
    uriPermission.put(2, second);
    uriPermission.put(3, third);

  }

  /**
   * 操作权限管理
   */
  public static Map<Integer,String[]> rightPermission =new HashMap<>();
  static {
    //用户1所拥有的操作权限
    String[] first={"insert","delete","select","update"};
    //用户2所拥有的URL权限
    String[] second={"insert","select","update"};
    //用户3所拥有的URL权限
    String[] third={"select"};
    rightPermission.put(1,first);
    rightPermission.put(2,second);
    rightPermission.put(3,third);

  }
}
