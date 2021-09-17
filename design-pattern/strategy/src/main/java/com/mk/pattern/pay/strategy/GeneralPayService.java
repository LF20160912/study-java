package com.mk.pattern.pay.strategy;

import com.mk.pattern.pay.UserPayService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Classname ParticularlyVipPayService
 * @Created by Michael
 * @Date 2021/9/18
 * @Description TODO
 */
@Service
public class GeneralPayService implements UserPayService, InitializingBean {
  public BigDecimal quote(BigDecimal orderPrice) {
    System.out.println("非会员商品价格为:" + orderPrice.doubleValue());
    return orderPrice;
  }

  public void afterPropertiesSet() throws Exception {
    UserPayServiceStrategyFactory.register("general", this);
  }
}
