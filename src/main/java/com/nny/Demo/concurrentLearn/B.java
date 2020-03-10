package com.nny.Demo.concurrentLearn;
/**
 * ThreadLocal类
 * get方法演示
 */

/**
 * 多线程的共享数据
 */
class A{
    /**
     * ThreadLocal子类
     */
    private static ThreadLocal<String> b = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            Thread c = Thread.currentThread();
            return c.getName();
        }
    };

    public String get(){
        return b.get();
    }
}

/**
 * 可执行任务类
 */
public class B implements Runnable{
    private A a;

    public B(A a){
        this.a = a;
    }

    public void run(){
        /**
         * 访问线程局部变量
         * 得到当前线程拥有的此线程局部变量的副本
         */
        String c = a.get();
        System.out.println(c);
    }
}
