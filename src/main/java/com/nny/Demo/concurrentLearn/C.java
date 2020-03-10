package com.nny.Demo.concurrentLearn;

public class C {
    public static void main(String[] args){
        m1();
    }

    /**
     * ThreadLocal类
     * get方法
     */
    public static void m1(){
        A a = new A();
        Runnable runnable = new B(a);

        Thread one = new Thread(runnable);
        Thread two = new Thread(runnable);

        one.start();
        two.start();
    }
}
