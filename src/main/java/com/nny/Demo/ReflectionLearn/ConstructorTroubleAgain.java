package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.InvocationTargetException;
import static java.lang.System.out;

/**
 * 反射
 * 构造函数
 * 问题排查
 */
public class ConstructorTroubleAgain {

    public ConstructorTroubleAgain() {}

    public ConstructorTroubleAgain(Integer i) {}

    public ConstructorTroubleAgain(Object o) {
        out.format("Constructor passed Object%n");
    }

    public ConstructorTroubleAgain(String s) {
        out.format("Constructor passed String%n");
    }

    public static void main(String... args){

        String argType = "Object";

        try {
            Class<?> c = Class.forName("com.nny.Demo.ReflectionLearn.ConstructorTroubleAgain");

            if ("".equals(argType)) {

                Object o = c.getConstructor().newInstance("foo");// IllegalArgumentException: wrong number of arguments
            }
            else if ("int".equals(argType)) {

                Object o = c.getConstructor(int.class);// NoSuchMethodException - looking for int, have Integer
            }
            else if ("Object".equals(argType)) {
                // newInstance() does not perform method resolution
                Object o = c.getConstructor(Object.class).newInstance("foo");
            }
            else {
                assert false;
            }

        }
        catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}