package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import static java.lang.System.out;

/**
 * 反射
 * 构造函数
 * 创建新的类的实例
 */
class EmailAliases {

    private Set<String> aliases;

    private EmailAliases(HashMap<String, String> h) {
        aliases = h.keySet();
    }

    public void printKeys() {
        out.format("Mail keys:%n");

        for (String k : aliases)
            out.format("  %s%n", k);
    }
}

public class RestoreAliases {

    private static Map<String, String> defaultAliases = new HashMap<String, String>();

    static {
        defaultAliases.put("Duke", "duke@i-love-java");
        defaultAliases.put("Fang", "fang@evil-jealous-twin");
    }

    public static void main(String... args) {

        try {

            boolean d = HashMap.class == defaultAliases.getClass();//true，由于类型擦除

            System.out.println(d);

            Constructor ctor = EmailAliases.class.getDeclaredConstructor(HashMap.class);

            ctor.setAccessible(true);

            /**
             *
             */
            EmailAliases email = (EmailAliases)ctor.newInstance(defaultAliases);

            email.printKeys();

        }
        catch (InstantiationException x) {
            x.printStackTrace();
        }
        catch (IllegalAccessException x) {
            x.printStackTrace();
        }
        catch (InvocationTargetException x) {
            x.printStackTrace();
        }
        catch (NoSuchMethodException x) {
            x.printStackTrace();
        }
    }
}
