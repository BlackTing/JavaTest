package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.Method;

/**
 * 反射
 * 使用反射时的典型错误
 */
public class ClassWarning {
    void m() {
        try {
            Class c = ClassWarning.class;
            Method m = c.getMethod("m");  // warning

        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        }
    }
}
