package com.mk.pattern.pay;

import com.mk.pattern.pay.strategy.GeneralPayService;
import com.mk.pattern.pay.strategy.ParticularlyVipPayService;
import com.mk.pattern.pay.strategy.SuperVipPayService;
import com.mk.pattern.pay.strategy.UserPayServiceStrategyFactory;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @Classname Test
 * @Created by Michael
 * @Date 2021/9/18
 * @Description TODO
 */
@Component
public class UserPayServiceTest {
  public static void main(String[] args) {
    //理论策略模式
   singStrategyPatternTest();
    System.out.println("////////////////////////////////////////////");
    //策略模式+工厂
//    strategyFactoryTest();
  }

  private static void singStrategyPatternTest() {
    BigDecimal goodPrice = new BigDecimal(10);
    //超级会员
    UserPayService strategy = new SuperVipPayService();
    BigDecimal quote = strategy.quote(goodPrice);
    System.out.println("超级会员商品价格为:" + quote.doubleValue());

    //一般会员
    strategy = new ParticularlyVipPayService();
    quote = strategy.quote(goodPrice);
    System.out.println("一般会员商品价格为:" + quote.doubleValue());

    strategy = new GeneralPayService();
    quote = strategy.quote(goodPrice);
    System.out.println("普通消费者商品价格为:" + quote.doubleValue());
  }

  @Test
  void strategyFactoryTest() {
    String userType = "superVip";
    BigDecimal total = new BigDecimal(50);
    UserPayService payService = UserPayServiceStrategyFactory.getByUserType(userType);
    BigDecimal actualTotal = payService.quote(total);
  }
}
