package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.Field;

/**
 * 反射
 * 字段
 * 故障排除:IllegalAccessException
 */
public class FieldTroubleToo {

    public final boolean b = true;

    private boolean a = false;//a可以直接设置值,那是因为在FieldTroubleToo类的内部

    public static void main(String... args) {

        FieldTroubleToo ft = new FieldTroubleToo();

        try {
            Class<?> c = ft.getClass();

            Field f = c.getDeclaredField("b");

 	        //f.setAccessible(true);  // solution

            f.getBoolean(ft);//可以获取值

            f.setBoolean(ft, Boolean.FALSE);
            //java.lang.IllegalAccessException:
            // Can not set final boolean field com.nny.Demo.ReflectionLearn.FieldTroubleToo.b to (boolean)false
        }
        catch (NoSuchFieldException x) {
            x.printStackTrace();
        }
        catch (IllegalArgumentException x) {
            x.printStackTrace();
        }
        catch (IllegalAccessException x) {
            x.printStackTrace();
        }
    }
}
