package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.Method;

/**
 * 反射
 * 成员方法
 * 故障排除：类型擦除导致NoSuchMethodException
 *
 * @param <T>
 */

public class MethodTrouble<T>  {

    public void lookup(T t) {}

    public void find(Integer i) {}

    public static void main(String... args) {
        try {
            String mName = args[0];//

            Class cArg = Class.forName(args[1]);//

            Class<?> c = (new MethodTrouble<Integer>()).getClass();//

            Method m = c.getMethod(mName, cArg);
            //lookup java.lang.Integer
            //java.lang.NoSuchMethodException: com.nny.Demo.ReflectionLearn.MethodTrouble.lookup(java.lang.Integer)

            //lookup java.lang.Object
            //public void com.nny.Demo.ReflectionLearn.MethodTrouble.lookup(T)

            System.out.format("Found:%n  %s%n", m.toGenericString());
        }
        catch (NoSuchMethodException x) {
            x.printStackTrace();
        }
        catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}
