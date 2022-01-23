package com.mike.study.springbootbasesecurity.config;

import com.mike.study.springbootbasesecurity.interceptor.URLInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Classname WebMvcConfig
 * @Created by Michael
 * @Date 2022/1/22
 * @Description 将拦截器注入应用
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Bean
  public URLInterceptor urlIntercepter(){
    return new URLInterceptor();
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //权限拦截器
    registry.addInterceptor(urlIntercepter()).addPathPatterns("/**");
  }
}
