package com.nny.Demo.ReflectionLearn;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.nny.Demo.ReflectionLearn.E.A;

/**
 * 反射api
 */

public class Lreflect {

    public static void main(String[] args){
        f5();
    }

    /**
     * 依据对象的实例,获取Class对象
     */
    public static void f0(){

        Class a = "foo".getClass();

        Class b = A.getClass();

        byte[] bytes = new byte[1024];
        Class c = bytes.getClass(); // class [B

        Set<String> s = new HashSet<String>();
        Class d = s.getClass();//class java.util.HashSet

        Class e = d.getClass();

        f4();


    }

    /**
     * 使用.class语法,获取Class对象
     */
    public static void f1(){

        Class a = boolean.class;

        Class b = PrintStream.class;

        Class c = int[][].class;//[[I

        Class d = String[][].class;//[[Ljava.lang.String;

        Class e = boolean[][].class;//[[Z

        System.out.println(e);

    }

    /**
     * 使用类的全限定名,获取Class对象
     */
    public static void f2(){

        Class a = null;
        Class b = null;
        Class g = null;
        Class j = null;
        try{
            a = Class.forName("java.io.PrintStream");
            b = Class.forName("[[Ljava.lang.String;");//L 分号
            g = Class.forName("[D");
            //j = Class.forName("java.lang.String[][]");//抛出异常 这个类的名字不能用于Class.forName()方法
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }

        /**
         * 获取类对象的名字
         */
        String c = boolean.class.getName(); //boolean

        String d = b.getName();//[[Ljava.lang.String;

        String e = int[][].class.getName();//[[I

        String f = a.getName();//java.io.PrintStream

        String h = a.getCanonicalName();//java.io.PrintStream

        String i = b.getCanonicalName();//java.lang.String[][]

        String k = boolean.class.getCanonicalName();//boolean

        String l = int[].class.getName();//[I

        String m = boolean[][].class.getName();//[[Z

        System.out.println(m);
    }

    /**
     * 包装类的TYPE字段的值是：（这个包装类封装的基本类型的）Class变量
     */
    public static void f3(){

        Class a = Double.TYPE;//double

        double.class.equals(a);//true

        Class b = Void.TYPE;//void

        System.out.println(b);
    }

    /**
     * 返回Class对象的一些实例方法
     */
    public static void f4(){

        Class a = ArrayList.class.getSuperclass();//java.util.AbstractList

        Class[] b = Character.class.getClasses();

        Class[] c = Character.class.getDeclaredClasses();

        for(Class i : c){
            System.out.println(i.toGenericString());//private static class java.lang.Character$CharacterCache
            System.out.println("getName():");
            System.out.println(i.getName());//java.lang.Character$CharacterCache
        }

        Field d = null;
        try{
            d = System.class.getField("out");
        }catch(NoSuchFieldException e){}

        Class e = d.getDeclaringClass();//java.lang.System

        Class f = Character.Subset.class.getDeclaringClass();//class java.lang.Character

        Class k = Character.class.getDeclaringClass();//null

        String j = f.getName();//java.lang.Character

        Class g = Thread.State.class.getEnclosingClass();

        Class h = Character.Subset.class.getEnclosingClass();//java.lang.Character

        Class i= E.class.getEnclosingClass();//null

        //System.out.println(k);

    }

    /**
     * 每种类型只生成一个类对象
     */
    public static void f5(){
        Class a = String.class;

        Class b = String.class;

        boolean c = a.equals(b);//true,也就是a b两个变量的引用相等。

        System.out.println();
    }



}

enum E{A,B}
