package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.Method;

/**
 * 反射
 * 成员方法
 * 故障排除：IllegalArguementException
 */

public class MethodTroubleToo {

    public void ping() { System.out.format("PONG!%n"); }

    public static void main(String... args) {
        try {
            MethodTroubleToo mtt = new MethodTroubleToo();

            Method m = MethodTroubleToo.class.getMethod("ping");

            //int a = Integer.parseInt(args[0]);

            switch(2) {
                case 0:
                    m.invoke(mtt);                 // works
                    break;

                case 1:
                    m.invoke(mtt, null);           // works (expect compiler warning)
                    break;

                case 2:
                    Object arg2 = null;
                    m.invoke(mtt, arg2);           // java.lang.IllegalArgumentException: wrong number of arguments
                    break;

                case 3:
                    /**
                     * new Object[0] 代表一个空数组
                     */
                    m.invoke(mtt, new Object[0]);  // works
                    break;

                case 4:
                    Object arg4 = new Object[0];
                    m.invoke(mtt, arg4);           // IllegalArgumentException
                    break;

                default:
                    System.out.format("Test not found%n");
            }
        }
        catch (Exception x) {
            x.printStackTrace();
        }
    }
}
