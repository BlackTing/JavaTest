package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射
 * 成员方法
 * 故障排除：InvocationTargetException
 */

public class MethodTroubleReturns {

    private void drinkMe(int liters) {
        if (liters < 0)
            throw new IllegalArgumentException("I can't drink a negative amount of liquid");
    }

    public static void main(String... args) {
        try {

            MethodTroubleReturns mtr  = new MethodTroubleReturns();

            Class<?> c = mtr.getClass();

            Method m = c.getDeclaredMethod("drinkMe", int.class);

            m.invoke(mtr, -1);
        }
        catch (InvocationTargetException x) {
            Throwable cause = x.getCause();
            System.err.format("drinkMe() failed: %s%n", cause.getMessage());
        }
        catch (Exception x) {
            x.printStackTrace();
        }
    }
}
