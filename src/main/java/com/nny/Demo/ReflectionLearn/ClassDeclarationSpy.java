package com.nny.Demo.ReflectionLearn;

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;

/**
 * 反射
 * 获取类的修饰符
 */
public class ClassDeclarationSpy {

    public static void main(String[] args){
        try{
            Class<?> c = Class.forName(args[0]);//args[0]是执行main方法时赋的参数

            /**
             * 类的名字
             */
            System.out.printf("Class:%n  %s%n%n%n",c.getCanonicalName());//反正就是返回一个名字，也别管是什么名字了

            /**
             * 类的修饰符
             */
            System.out.printf("Modifiers:%n  %s%n", Modifier.toString(c.getModifiers()));//public abstract interface

            /**
             *  泛型类型
             */
            System.out.format("Type Parameters:%n");

            TypeVariable[] tv = c.getTypeParameters();

            if (tv.length != 0) {

                for (TypeVariable t : tv) {
                    System.out.format("%s ", t.getName());
                }

                System.out.format("%n%n");
            }

            /**
             * 实现的接口
             */
            System.out.format("Implemented Interfaces:%n");

            Type[] intfs = c.getGenericInterfaces();//包括上层实现的接口吗？

            if (intfs.length != 0) {
                for (Type intf : intfs) {
                    System.out.format("  %s%n", intf.toString());
                }
            }

            /**
             * 继承的所有祖先
             */
            System.out.format("%nInheritance Path:%n");

            List<Class> l = new ArrayList<Class>();
            printAncestor(c, l);
            if (l.size() != 0) {
                for (Class<?> cl : l)
                    System.out.format("  %s%n", cl.getCanonicalName());
            }

            /**
             * 类具有的注解
             */
            System.out.format("%nAnnotations:%n");

            Annotation[] ann = c.getAnnotations();
            if (ann.length != 0) {
                for (Annotation a : ann) {
                    System.out.format("  %s%n", a.toString());
                }
                System.out.format("%n");
            }
        }
        catch (ClassNotFoundException e){}
    }

    /**
     * 寻找所有祖先
     * @param c
     * @param l
     */
    private static void printAncestor(Class<?> c, List<Class> l) {
        Class<?> ancestor = c.getSuperclass(); //父类的类对象
        if (ancestor != null) {
            l.add(ancestor);
            printAncestor(ancestor, l);//寻找父类的父类
        }
    }
}
