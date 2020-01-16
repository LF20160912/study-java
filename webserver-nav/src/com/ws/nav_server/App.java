package com.ws.nav_server;

import com.ws.nav_server.demo.WeatherServiceImpl;

import javax.xml.ws.Endpoint;

/*
  Author:Mike
  创建时间：2019/11/3
  描述：
*/
public class App {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8090/weatherinfo", new WeatherServiceImpl());
        System.out.println("发布成功！");
    }
}
