package com.ws.nav_client;

import com.ws.nav_server.demo.WeatherServiceImpl;
import com.ws.nav_server.demo.WeatherServiceImplService;

/*
  Author:Mike
  创建时间：2019/11/4
  描述：
*/
public class WeatherTest {
    public static void main(String[] args) {
        WeatherServiceImplService implService = new WeatherServiceImplService();
        WeatherServiceImpl weatherImpl = implService.getWeatherServiceImplPort();
        String weatherInfo = weatherImpl.getWeatherInfoByCityName("广州");
        System.out.println(weatherInfo);
    }
}
