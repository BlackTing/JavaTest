package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 反射
 * 方法调用
 * 调用具有可变参数的方法:main方法
 */

public class InvokeMain {

    public static void main(String... args) {//类型是String[]，对应的类对象是String[].class
        try {
            Class<?> c = Class.forName(args[0]);//

            /**
             *
             */
            //Class[] argTypes = new Class[] { String[].class };

            Class[] argTypes = { String[].class };

            //Method main = c.getDeclaredMethod("main", String[].class);

            Method main = c.getDeclaredMethod("main", argTypes);


            String[] mainArgs = Arrays.copyOfRange(args, 1, args.length);

            System.out.format("invoking %s.main()%n", c.getName());

            /**
             *
             */
            main.invoke(null, (Object)mainArgs);
        }
        catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
        catch (NoSuchMethodException x) {
            x.printStackTrace();
        }
        catch (IllegalAccessException x) {
            x.printStackTrace();
        }
        catch (InvocationTargetException x) {
            x.printStackTrace();
        }
    }
}
