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
public class ParticularlyVipPayService implements UserPayService, InitializingBean {
  public BigDecimal quote(BigDecimal orderPrice) {
    if(orderPrice.compareTo(new BigDecimal(30)) > 0){
      orderPrice = orderPrice.multiply(new BigDecimal(0.7)) ;
    }
    System.out.println("普通会员商品价格为:" + orderPrice.doubleValue());
    return orderPrice;
  }

  public void afterPropertiesSet() throws Exception {
    UserPayServiceStrategyFactory.register("particularly", this);
  }
}
