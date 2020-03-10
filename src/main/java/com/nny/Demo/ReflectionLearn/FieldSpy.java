package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 反射
 *
 * 有一个Field实例可用，获得字段的类型
 *
 * 提示：通过命令行提示符传参运行
 */
public class FieldSpy<T> {

    public boolean[][] b = {{ false, false }, { true, true } };//class [[Z //class [[Z

    public String name  = "Alice";//class java.lang.String //class java.lang.String

    public List<Integer> list;//Type: interface java.util.List GenericType: java.util.List<java.lang.Integer>

    public T val;//Type: class java.lang.Object GenericType: T

    public static void main(String... args) {

        try {
            Class<?> c = Class.forName(args[0]);

            Field f = c.getField(args[1]);

            System.out.format("Type: %s%n", f.getType());

            System.out.format("GenericType: %s%n", f.getGenericType());

        }
        catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
        catch (NoSuchFieldException x) {
            x.printStackTrace();
        }
    }
}
