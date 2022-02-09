package cm.mk.tool.util.string;

/**
 * @Classname IntTostring
 * @Created by Michael
 * @Date 2022/2/9
 * @Description TODO
 */
public class IntTostring {
  public int value=10;
  private static IntTostring currentEnty= new IntTostring();
  static {
    StringFactory.register(int.class.getName(),currentEnty);
  }

  public String toString(){
    return "value:"+value;
  }
}
