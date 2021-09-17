package com.mk.pattern.pay.strategy;

import com.mk.pattern.pay.UserPayService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Classname ParticularlyVipPayService
 * @Created by Michael
 * @Date 2021/9/18
 * @Description TODO
 */
@Component
public class SuperVipPayService implements UserPayService, InitializingBean {
  public BigDecimal quote(BigDecimal orderPrice) {
    orderPrice = orderPrice.multiply(new BigDecimal(0.8));
    System.out.println("超级会员商品价格为:" + orderPrice.doubleValue());
    return orderPrice;
  }

  public void afterPropertiesSet() throws Exception {
    UserPayServiceStrategyFactory.register("superVip", this);
  }
}
