package cm.mk.tool.util;

import cm.mk.tool.util.string.IntTostring;
import cm.mk.tool.util.string.StringFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class UtilApplication {

  public static void main(String[] args) {
    SpringApplication.run(UtilApplication.class, args);
    IntTostring currentEnty= new IntTostring();
    String result = StringFactory.toString(int.class.getName());
    System.out.println(result);
  }

}
