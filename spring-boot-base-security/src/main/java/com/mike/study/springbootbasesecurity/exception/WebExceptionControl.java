package com.mike.study.springbootbasesecurity.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Classname WebExceptionControl
 * @Created by Michael
 * @Date 2022/1/22
 * @Description 全局异常处理
 */
@RestControllerAdvice
public class WebExceptionControl {

  @ExceptionHandler(APIException.class)
  public String APIExceptionHandler(APIException apiException){
    return apiException.getMessage();
  }

}
