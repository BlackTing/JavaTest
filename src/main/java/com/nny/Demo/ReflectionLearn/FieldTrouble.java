package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.Field;

/**
 * 反射
 * 字段
 * 故障排除:IllegalArgumentException
 */
public class FieldTrouble {

    private Integer val;

    public static void main(String... args) {

        FieldTrouble ft = new FieldTrouble();

        try {
            Class<?> c = ft.getClass();

            Field f = c.getDeclaredField("val");

            boolean a = Integer.class.isAssignableFrom(int.class); //false

            System.out.println(a);

            //f.setInt(ft, 42);
            //java.lang.IllegalArgumentException:
            // Can not set java.lang.Integer field com.nny.Demo.ReflectionLearn.FieldTrouble.val to (int)42

            f.set(ft,new Integer(42));

            Object b = f.get(ft);

            System.out.println(b);


        }
        catch (NoSuchFieldException x) {
            x.printStackTrace();
        }
        catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}
