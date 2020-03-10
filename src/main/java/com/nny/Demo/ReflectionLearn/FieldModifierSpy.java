package com.nny.Demo.ReflectionLearn;

import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 反射
 * 获得字段的修饰符
 */
enum Spy { BLACK , WHITE }

public class FieldModifierSpy {

    volatile int share;

    int instance;

    class Inner {}

    public static void main(String... args) {

        try {
            Class<?> c = Class.forName(args[0]);

            //这个整数表示想要查询的修饰符集合
            int searchMods = 0x0;

            for (int i = 1; i < args.length; i++) {
                searchMods |= modifierFromString(args[i]);
            }

            Field[] flds = c.getDeclaredFields();

            System.out.format("search modifiers: %s%n", Modifier.toString(searchMods));

            for (Field f : flds) {
                int foundMods = f.getModifiers();

                //过滤不想要的修饰符，留下想要的修饰符
                if ((foundMods & searchMods) == searchMods) {

                    //%-8s是什么？
                    System.out.format("%-8s[synthetic=%-5b,enum_constant=%-5b]%n", f.getName(), f.isSynthetic(), f.isEnumConstant());
                }
            }

            /**
             * 字段的注解
             */
        }
        catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

    private static int modifierFromString(String s) {

        int m = 0x0;

        //按位并运算，是一个累加1的过程。就像贪吃蛇，遇到一个1就吃掉一个1.
        //把0看做贪吃蛇的初始大小，Modidier中的每个修饰符看做每个不同的1。
        //equals()方法导致多个if条件互斥，所以为什么不直接返回Modifier类中的修饰符常量？

        if ("public".equals(s))           m |= Modifier.PUBLIC;

        else if ("protected".equals(s))   m |= Modifier.PROTECTED;

        else if ("private".equals(s))     m |= Modifier.PRIVATE;

        else if ("static".equals(s))      m |= Modifier.STATIC;

        else if ("final".equals(s))       m |= Modifier.FINAL;

        else if ("transient".equals(s))   m |= Modifier.TRANSIENT;

        else if ("volatile".equals(s))    m |= Modifier.VOLATILE;

        return m;
    }
}