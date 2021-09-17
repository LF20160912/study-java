package com.mk.pattern.pay.strategy;

import com.mk.pattern.pay.UserPayService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname UserPayServiceStrategyFactory
 * @Created by Michael
 * @Date 2021/9/18
 * @Description TODO
 */
public class UserPayServiceStrategyFactory {
  private static Map<String, UserPayService> services =
          new ConcurrentHashMap<String, UserPayService>();

  public static UserPayService getByUserType(String type) {
    return services.get(type);
  }

  public static void register(String userType, UserPayService userPayService) {
    if (userType == null) {
      throw new IllegalArgumentException("userType can't be null!");
    }
    services.put(userType, userPayService);
  }
}
