package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import static java.lang.System.out;

/**
 * 反射
 * 构造方法
 * 检索修饰符
 */

public class ConstructorAccess {
    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);//

            Constructor[] allConstructors = c.getDeclaredConstructors();

            for (Constructor ctor : allConstructors) {

                int searchMod = modifierFromString(args[1]);//

                int a = ctor.getModifiers();

                int mods = accessModifiers(a);

//                if (searchMod == mods) {
                    out.format("%s%n", ctor.toGenericString());
//                    out.format("  [ synthetic=%-5b var_args=%-5b ]%n", ctor.isSynthetic(), ctor.isVarArgs());
//                }
            }
        }
        catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

    private static int accessModifiers(int m) {
        return m & (Modifier.PUBLIC | Modifier.PRIVATE | Modifier.PROTECTED);
    }

    private static int modifierFromString(String s) {
        if ("public".equals(s))               return Modifier.PUBLIC;
        else if ("protected".equals(s))       return Modifier.PROTECTED;
        else if ("private".equals(s))         return Modifier.PRIVATE;
        else if ("package-private".equals(s)) return 0;
        else return -1;
    }
}
