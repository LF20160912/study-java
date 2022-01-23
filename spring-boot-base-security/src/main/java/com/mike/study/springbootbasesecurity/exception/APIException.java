package com.mike.study.springbootbasesecurity.exception;

/**
 * @Classname APIException
 * @Created by Michael
 * @Date 2022/1/22
 * @Description API Exception
 */
public class APIException extends RuntimeException{

  String msg;

  public APIException(String message) {
    super(message);
    this.msg =  message;
  }
}
