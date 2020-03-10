package com.nny.Demo.ReflectionLearn;

/**
 * 反射
 * 检索和获取方法的修饰符
 */

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import static java.lang.System.out;

public class MethodModifierSpy {

    private static int count;

    /**
     * 同步方法
     */
    private static synchronized void inc() { count++; }

    private static synchronized int cnt() { return count; }

    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);//类的全限定名

            Method[] allMethods = c.getDeclaredMethods();

            for (Method m : allMethods) {

                String a = m.getName();
                if (!a.equals(args[1])) {//成员方法的名字
                    continue;
                }

                /**
                 *
                 */
                String b = m.toGenericString();

                int modifiers = m.getModifiers();

                String d = Modifier.toString(modifiers);

                boolean e = m.isSynthetic();

                boolean f = m.isVarArgs();

                boolean g = m.isBridge();

                out.format("%s%n", b);

                out.format("  Modifiers:  %s%n", d);

                out.format("  [ synthetic=%-5b var_args=%-5b bridge=%-5b ]%n",
                        e, f, g);
                inc();
            }

            out.format("%d matching overload%s found%n", cnt(), (cnt() == 1 ? "" : "s"));
        }
        catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}
