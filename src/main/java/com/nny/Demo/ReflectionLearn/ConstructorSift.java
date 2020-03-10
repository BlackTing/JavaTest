package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import static java.lang.System.out;

/**
 * 反射
 * 构造函数
 * 查找构造函数
 */
public class ConstructorSift {
    public static void main(String... args) {
        try {
            Class<?> cArg = Class.forName(args[1]);//

            Class<?> c = Class.forName(args[0]);//

            Constructor[] allConstructors = c.getDeclaredConstructors();

            for (Constructor ctor : allConstructors) {
                /**
                 *
                 */
                Class<?>[] pType  = ctor.getParameterTypes();

                for (int i = 0; i < pType.length; i++) {

                    /**
                     *
                     */
                    if (pType[i].equals(cArg)) {

                        out.format("%s%n", ctor.toGenericString());

                        Type[] gpType = ctor.getGenericParameterTypes();

                        for (int j = 0; j < gpType.length; j++) {

                            char ch = (pType[j].equals(cArg) ? '*' : ' ');

                            out.format("%7c%s[%d]: %s%n", ch, "GenericParameterType", j, gpType[j]);
                        }
                        break;
                    }
                }
            }
        }
        catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }
}