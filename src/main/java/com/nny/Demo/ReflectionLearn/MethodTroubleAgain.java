package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射
 * 成员方法
 * 故障排除：IllegalAccessException
 */

class AnotherClass {
    private void m() {}
}

public class MethodTroubleAgain {
    public static void main(String... args) {

        AnotherClass ac = new AnotherClass();

        try {
            Class<?> c = ac.getClass();

            Method m = c.getDeclaredMethod("m");

            /**
             *
             */
  	        m.setAccessible(true);      // solution

            Object o = m.invoke(ac);
            //java.lang.IllegalAccessException:
            // class com.nny.Demo.ReflectionLearn.MethodTroubleAgain cannot access
            // a member of class com.nny.Demo.ReflectionLearn.AnotherClass with modifiers "private"
        }
        catch (NoSuchMethodException x) {
            x.printStackTrace();
        }
        catch (InvocationTargetException x) {
            x.printStackTrace();
        }
        catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}
