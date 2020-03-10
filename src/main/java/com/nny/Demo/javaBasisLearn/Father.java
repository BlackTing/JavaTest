package com.nny.Demo.javaBasisLearn;

/**
 * 继承
 */
public class Father {

    public int a = 5;

    public static int b = 5;

    protected int c = 5;

    //友好变量
    int d;

    //私有变量
    private int e;

    public Father(){
        System.out.println("father构造方法");
    }

    public Father(int a){
        System.out.println("father带参的构造方法");
    }

    public int getA(){
        return this.a;
    }

    //父类中的友好方法
    void f1(){
        System.out.println("友好方法");
    }

    public static void f2(){
        System.out.println("father的类方法");
    }

    //友好方法，不同包中的子类不能继承
    void f4(){
    }

    /**
     * final修饰的方法，不能被子类重写
     */
    public final void oneMethod(){
        System.out.println("father's method");
    }
}
