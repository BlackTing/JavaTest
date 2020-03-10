package com.nny.Demo.javaBasisLearn;

/**
 * 匿名类
 */
public class MyClass{

    static int d;

    //匿名类的对象
    static Object o = new Object(){
        public void m(){}
    };

    static MyClass c = new MyClass(){
        public void m(){
            //使用外嵌类的成员变量
            d = 5;
        }
    };

    public MyClass(){}

    public MyClass(int d){
        this.d = d;
    }

    static Class a = o.getClass();//com.nny.Demo.javaBasisLearn.MyClass$1 匿名类

    static Class b = a.getDeclaringClass();//null

    static Class g = o.getClass().getEnclosingClass();//com.nny.Demo.javaBasisLearn.MyClass

    static Class h = c.getClass();//com.nny.Demo.javaBasisLearn.MyClass$2 匿名类
}
