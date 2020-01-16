package com.ws.nav_server.demo;

import javax.jws.WebService;

/*
  Author:Mike
  创建时间：2019/11/3
  描述：
*/
@WebService
public class WeatherServiceImpl implements WeatherService {
    @Override
    public String getWeatherInfoByCityName(String cityName) {
        return "获取"+cityName+"的天气信息！";
    }
}
