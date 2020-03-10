package com.nny.Demo.ReflectionLearn;

import java.io.Console;
import java.nio.charset.Charset;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import static java.lang.System.out;

/**
 * 反射
 * 构造函数
 * 创建新的类的实例
 */

public class ConsoleCharset {

    public static void main(String... args) {

        Constructor[] ctors = Console.class.getDeclaredConstructors();

        Constructor ctor = null;

        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }

        try {

            ctor.setAccessible(true);

            /**
             *
             */
            Console c = (Console)ctor.newInstance();

            Field f = c.getClass().getDeclaredField("cs");

            f.setAccessible(true);

            Object a = f.get(c);

            Charset b = Charset.defaultCharset();

            out.format("Console charset         :  %s%n", a);

            out.format("Charset.defaultCharset():  %s%n",b);

        }
        catch (InstantiationException x) {
            x.printStackTrace();
        }
        catch (InvocationTargetException x) {
            x.printStackTrace();
        }
        catch (IllegalAccessException x) {
            x.printStackTrace();
        }
        catch (NoSuchFieldException x) {
            x.printStackTrace();
        }
    }
}
