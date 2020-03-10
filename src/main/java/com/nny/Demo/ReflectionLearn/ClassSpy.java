package com.nny.Demo.ReflectionLearn;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

/**
 * 反射
 * 查询类中的成员（字段、方法、构造方法）
 */
public class ClassSpy {

    public static void main(String... args) {
        try {
            Class<?> c = Class.forName(args[0]);
            System.out.format("Class:%n  %s%n%n", c.getCanonicalName());

            /**
             * 获得类所在的包
             */
            Package p = c.getPackage();
            System.out.format("Package:%n  %s%n%n",
                    (p != null ? p.getName() : "-- No Package --"));

            /**
             * 获得类的成员：字段 方法 构造器
             */
            for (int i = 1; i < args.length; i++) {

                switch (ClassMember.valueOf(args[i])) {

                    case CONSTRUCTOR:
                        printMembers(c.getConstructors(), "Constructors");

                        printMembers(c.getDeclaredConstructors(),"DeclaredConstructors");

                        //当参数是Son时做的测试
//                        try {
//                            System.out.printf("DeclaredConstructor%n  %s%n", c.getDeclaredConstructor().toGenericString());
//                            //public com.nny.Demo.javaBasisLearn.Son()
//
//                            System.out.printf("DeclaredConstructor%n  %s%n", c.getDeclaredConstructor(int.class).toGenericString());
//                            //private com.nny.Demo.javaBasisLearn.Son(int)
//
//                            System.out.printf("Constructor%n  %s%n", c.getConstructor().toGenericString());
//                            // public com.nny.Demo.javaBasisLearn.Son()
//
//                            //System.out.printf("Constructor%n  %s%n", c.getConstructor(int.class).toGenericString());
//                            //java.lang.NoSuchMethodException: com.nny.Demo.javaBasisLearn.Son.<init>(int)
//
//                        }
//                        catch(NoSuchMethodException e){
//                            e.printStackTrace();
//                        }
                        break;

                    case FIELD:
                        printMembers(c.getFields(), "Fields");

                        printMembers(c.getDeclaredFields(),"DeclaredFields");

                        //当参数是Son时做的测试
//                        try{
//                            System.out.printf("Field%n  %s%n",c.getField("c").toGenericString());
//                            //public double com.nny.Demo.javaBasisLearn.Son.c
//
//                            System.out.printf("Field%n  %s%n",c.getField("a"));
//                            //public int com.nny.Demo.javaBasisLearn.Son.a
//
//                            //System.out.printf("Field%n  %s%n",c.getField("f"));
//                            //java.lang.NoSuchFieldException: f
//
//                            System.out.printf("DeclaredField%n  %s%n",c.getDeclaredField("f"));
//                            //private int com.nny.Demo.javaBasisLearn.Son.f
//
//                            //System.out.printf("DeclaredField%n  %s%n",c.getDeclaredField("b"));
//                            //java.lang.NoSuchFieldException: b
//
//                        }
//                        catch(NoSuchFieldException e){
//                            e.printStackTrace();
//                        }
                        break;

                    case METHOD:

                        printMembers(c.getMethods(), "Methods");
                        System.out.println();
                        printMembers(c.getDeclaredMethods(),"DeclaredMethods");

                        /**
                         * 下面是当参数为ClassMember时做的测试
                         */
                        try {
                            System.out.printf("%nsearchMethod%n  %s%n",c.getMethod("wait",long.class).toGenericString());

                            System.out.printf("%nsearchMethod%n  %s%n", c.getMethod("values").toGenericString());
                            //public static com.nny.Demo.ReflectionLearn.ClassMember[] com.nny.Demo.ReflectionLearn.ClassMember.values()

                            System.out.printf("%nsearchDeclaredMethod%n  %s%n", c.getDeclaredMethod("values").toGenericString());
                            //public static com.nny.Demo.ReflectionLearn.ClassMember[] com.nny.Demo.ReflectionLearn.ClassMember.values()

                            System.out.printf("%nsearchDeclaredMethod%n  %s%n", c.getDeclaredMethod("wait",long.class).toGenericString());
                            //java.lang.NoSuchMethodException: com.nny.Demo.ReflectionLearn.ClassMember.wait(long)

                        }
                        catch(NoSuchMethodException e){
                            e.printStackTrace();
                        }
                        System.out.println();
                        break;

                    case CLASS:
                        printClasses(c);
                        break;

                    case ALL:
                        printMembers(c.getConstructors(), "Constuctors");
                        printMembers(c.getFields(), "Fields");
                        printMembers(c.getMethods(), "Methods");
                        printClasses(c);
                        break;
                    default:
                        assert false;
                }


            }

        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        }
    }

    /**
     * 打印成员集合
     * @param mbrs
     * @param s
     */
    private static void printMembers(Member[] mbrs, String s) {
        System.out.format("%s:%n", s);

        for (Member mbr : mbrs) {

            if (mbr instanceof Field) {
                Field f = (Field) mbr;
                System.out.format("  %s%n", f.toGenericString());
            }
            else if (mbr instanceof Constructor) {
                System.out.format("  %s%n", ((Constructor) mbr).toGenericString());
            }
            else if (mbr instanceof Method) {
                System.out.format("  %s%n", ((Method) mbr).toGenericString());
            }
        }

        System.out.format("%n");
    }

    /**
     * 打印内部类集合
     * @param c
     */
    private static void printClasses(Class<?> c) {
        System.out.format("Member Classes:%n");

        Class<?>[] clss = c.getClasses();//

        for (Class<?> cls : clss)
            System.out.format("  %s%n", cls.getCanonicalName());

        System.out.format("%n");
    }
}

enum ClassMember { CONSTRUCTOR, FIELD, METHOD, CLASS, ALL }


