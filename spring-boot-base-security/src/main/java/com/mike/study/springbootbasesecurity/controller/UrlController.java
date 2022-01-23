package com.mike.study.springbootbasesecurity.controller;

import com.mike.study.springbootbasesecurity.annotation.URLAnnotation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname UrlController
 * @Created by Michael
 * @Date 2022/1/22
 * @Description Url 访问控制器
 */@RestController
public class UrlController {
   @URLAnnotation(type = "select")
  @RequestMapping(value = "/url1")
  public String url1() {
    return "恭喜你！拥有访问权限";
  }


  @URLAnnotation(type = "delete")
  @RequestMapping(value = "/url2")
  public String url2() {
    return "恭喜你！拥有访问权限";
  }

  @URLAnnotation(type = "insert")
  @RequestMapping(value = "/url3")
  public String url3() {
    return "恭喜你！拥有访问权限";
  }

  @URLAnnotation(type = "update")
  @RequestMapping(value = "/url4")
  public String url4() {
    return "恭喜你！拥有访问权限";
  }

  @RequestMapping(value = "/url5")
  public String url5() {
    return "恭喜你！拥有访问权限";
  }

  @RequestMapping(value = "/url6")
  public String url6() {
    return "恭喜你！拥有访问权限";
  }

  @RequestMapping(value = "/url7")
  public String url7() {
    return "恭喜你！拥有访问权限";
  }
}
