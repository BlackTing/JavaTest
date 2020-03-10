package com.nny.Demo.javaBasisLearn;

/**
 * 继承
 */
public class Son extends Father {

    /**
     * 隐藏了父类的成员变量a
     * 因为成员变量与父类的成员变量重名，不考虑数据类型是否相同
     */
    public int a = 10;

    public double c = 5.6;

    private int f;

    public Son(){
        System.out.println("son构造方法,a="+this.a);
    }

    private Son(int a){
        this();
        System.out.println("son带参的构造方法a="+this.a);
    }

    //重写方法
    public synchronized int getA(){
        return 20;
    }

    public int getB(){
        return super.b;
    }

    public static void f2(){
        System.out.println("son的类方法");
    }

    //访问父类的友好方法
    public void f3(){
        f1();
        c = 5;
        d = 5;
    }

    protected void s5(){

    }

    private void s6(){
        System.out.println("ha");
    }




}
