package com.nny.Demo.ReflectionLearn;

/**
 * 反射
 * 使用反射会发生的典型错误。
 */
class Cls {
    private Cls() {}
}

public class ClassTrouble {

    public static void main(String... args) {

        try {
            Class<?> c = Class.forName("Cls");
            c.newInstance();  // InstantiationException

        } catch (InstantiationException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}