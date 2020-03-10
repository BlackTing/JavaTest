package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.*;
import java.util.function.*;
import static java.lang.System.out;

/**
 * 反射
 * 成员中的方法
 * 获取方法的参数名称
 */
public class MethodParameterSpy {

    private static final String  fmt = "%24s: %s%n";

    <E extends RuntimeException> void genericThrow() throws E {}

    public static void printClassConstructors(Class c) {

        Constructor[] allConstructors = c.getConstructors();

        out.format(fmt, "Number of constructors", allConstructors.length);

        for (Constructor currentConstructor : allConstructors) {
            printConstructor(currentConstructor);
        }
        Constructor[] allDeclConst = c.getDeclaredConstructors();
        out.format(fmt, "Number of declared constructors",
                allDeclConst.length);
        for (Constructor currentDeclConst : allDeclConst) {
            printConstructor(currentDeclConst);
        }
    }

    public static void printClassMethods(Class c) {

        Method[] allMethods = c.getDeclaredMethods();

        out.format(fmt, "Number of methods", allMethods.length);

        for (Method m : allMethods) {
            printMethod(m);
        }
    }

    public static void printConstructor(Constructor c) {
        String a = c.toGenericString();

        /**
         *
         */
        Parameter[] params = c.getParameters();

        out.format("%s%n", a);
        out.format(fmt, "Number of parameters", params.length);

        for (int i = 0; i < params.length; i++) {
            printParameter(params[i]);
        }
    }

    public static void printMethod(Method m) {
        out.format("%s%n", m.toGenericString());
        out.format(fmt, "Return type", m.getReturnType());
        out.format(fmt, "Generic return type", m.getGenericReturnType());

        Parameter[] params = m.getParameters();
        for (int i = 0; i < params.length; i++) {
            printParameter(params[i]);
        }
    }

    public static void printParameter(Parameter p) {

        Class a = p.getType();

        String b = p.getName();

        int c = p.getModifiers();

        boolean d = p.isImplicit();

        boolean e = p.isNamePresent();

        boolean f = p.isSynthetic();

        out.format(fmt, "Parameter class", a);
        out.format(fmt, "Parameter name", b);
        out.format(fmt, "Modifiers", c);
        out.format(fmt, "Is implicit?", d);
        out.format(fmt, "Is name present?", e);
        out.format(fmt, "Is synthetic?", f);
    }

    public static void main(String... args) {

        try {
            Class c = Class.forName(args[0]);

            printClassConstructors(c);
            printClassMethods(c);
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}
