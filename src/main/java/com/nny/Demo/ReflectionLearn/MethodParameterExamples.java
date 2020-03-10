package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.*;
import java.util.function.*;
import static java.lang.System.out;

/**
 * 反射
 */
public class MethodParameterExamples {

    //非静态内部类
    public class InnerClass { }

    //内部枚举类
    enum Colors {
        RED, WHITE;
    }

    public static void main(String... args) {
        System.out.println("InnerClass:");
        MethodParameterSpy.printClassConstructors(InnerClass.class);

        System.out.println("enum Colors:");
        MethodParameterSpy.printClassConstructors(Colors.class);
        MethodParameterSpy.printClassMethods(Colors.class);
    }
}
