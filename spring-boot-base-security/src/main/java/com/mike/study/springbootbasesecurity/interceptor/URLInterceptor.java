package com.mike.study.springbootbasesecurity.interceptor;

import com.mike.study.springbootbasesecurity.annotation.URLAnnotation;
import com.mike.study.springbootbasesecurity.config.Constant;
import com.mike.study.springbootbasesecurity.exception.APIException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Classname URLInterceptor
 * @Created by Michael
 * @Date 2022/1/22
 * @Description URL Interceptor
 */
@Component
public class URLInterceptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    String token = httpServletRequest.getParameter("token");
    String requestUri = httpServletRequest.getRequestURI();

    //1.根据token判断用户是否登录
    if (token == null) {
      throw new APIException("当前用户没用登录");
    }

    //2.登录成功后,获取用户访问URL的权限
    boolean hasPermission = allowAccessUrl(token, requestUri);
    if (!hasPermission) {
      throw new APIException("当前用户没有访问路径" + requestUri + "的权限");
    }

    //3.是否有操作权限
    hasPermission = allowOperation((HandlerMethod) handler, token);
    if (!hasPermission) {
      throw new APIException("当前用户没有操作的权限");
    }
    return true;
  }

  private boolean allowOperation(HandlerMethod handler, String token) {
    boolean hasPermission= false;
    String type = getOperateRight(handler);
    String[] operatePermission = Constant.rightPermission.get(Integer.valueOf(token));
    if(operatePermission!=null){
      hasPermission = Arrays.stream(operatePermission).anyMatch(opt -> opt.equals(type));
    }
    return hasPermission;
  }

  private String getOperateRight(HandlerMethod handler) {
    HandlerMethod handlerMethod = handler;
    Method method = handlerMethod.getMethod();
    URLAnnotation urlAnnotation = method.getAnnotation(URLAnnotation.class);
    if(urlAnnotation == null){
      throw new APIException("当前用户没有操作权限");
    }
    return urlAnnotation.type();
  }

  private boolean allowAccessUrl(String key, String requestUri) {
    String[] accessURL = Constant.uriPermission.get(Integer.valueOf(key));

    //用访问权限放行，否则拒绝访问
    boolean hasPermission =false;
    if(accessURL!=null) {
      hasPermission = Arrays.stream(accessURL).anyMatch(uri->uri.equals(requestUri));
    }
    return hasPermission;
  }


}
