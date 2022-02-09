package cm.mk.tool.util.string;

import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname StringFactory
 * @Created by Michael
 * @Date 2022/2/9
 * @Description TODO
 */
public class StringFactory {
  private static Map<String, Object> classMap = new HashMap<>();

  public static void register(String key, Object entity) {
    if (StringUtils.isEmpty(key) || entity == null) {
      return;
    }

    classMap.put(key, entity);
  }

  public static String toString(String key) {
    Object entity = classMap.get(key);

    if (entity == null) {
      return "";
    }

    String result = "";
    try {
      Method m = entity.getClass().getMethod("toString");
      if (m == null) {
        return "";
      }

      result = (String) m.invoke(entity);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    }

    return result;
  }

}
