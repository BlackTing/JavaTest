package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * 反射
 * 方法
 * 获得方法的类型信息（return parameter exception）
 */
public class MethodSpy {

    private static final String fmt = "%24s: %s%n";

    /**
     *
     * @param <E>
     * @throws E
     */
    <E extends RuntimeException> void genericThrow() throws E {}

    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);//类的全限定名

            /**
             *获得指定类对象的显式声明的方法集合
             */
            Method[] allMethods = c.getDeclaredMethods();

            for (Method m : allMethods) {
                if (!m.getName().equals(args[1])) {//方法名
                    continue;
                }

                String a = m.toGenericString();

                /**
                 * 获得指定方法的返回类型
                 */

                Class b = m.getReturnType();

                Type d = m.getGenericReturnType();

                /**
                 * 获得指定方法的所有参数的类型
                 */

                Class<?>[] pType  = m.getParameterTypes();

                Type[] gpType = m.getGenericParameterTypes();

                /**
                 * 获得指定方法抛出的所有异常的类型
                 */

                Class<?>[] xType  = m.getExceptionTypes();

                Type[] gxType = m.getGenericExceptionTypes();

                System.out.println(a);

                System.out.printf(fmt, "ReturnType", b);

                System.out.format(fmt, "GenericReturnType", d);

                for (int i = 0; i < pType.length; i++) {
                    System.out.format(fmt,"ParameterType", pType[i]);
                    System.out.format(fmt,"GenericParameterType", gpType[i]);
                }

                for (int i = 0; i < xType.length; i++) {
                    System.out.format(fmt,"ExceptionType", xType[i]);
                    System.out.format(fmt,"GenericExceptionType", gxType[i]);
                }
            }
        }
        catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

}
