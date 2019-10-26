package com.nny.Demo.ThreadTest;

/**
 * 学习Thread类的常用方法
 * 1. start()
 * 2. sleep()
 */
public class ThreadLearn {
    public static void main(String[] args){
        interruptLearn();
    }

    public static void startLearn(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<1000;i++){
                    System.out.println(Thread.currentThread().getName()+"-run:"+i);
                }
            }
        });
        t.start();
        for(int i=0;i<1000;i++){
            System.out.println(Thread.currentThread()+"-main:"+i);
        }
    }

    public static void sleepLearn(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<10;i++){
                    try{
                        Thread.sleep(5*1000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+":"+i);
                }
            }
        });

        thread.start();
    }

    public static void joinLearn(){
        for(int i=0;i<10;i++){
            if(i==2){
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for(int i=10;i<20;i++){
                            System.out.println(Thread.currentThread().getName()+":"+i);
                        }
                    }
                });
                thread.start();//开始在前，加入在后
                try{
                    thread.join();
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
    }

    public static void yieldLearn(){
        // 子线程
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 10; i < 20; i++) {
                    if(i > 15) {
                        // 让当前线程释放cpu的使用权
                        Thread.yield();
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + i);
                }
            }
        });
        t.start();
        // 主线程
        for(int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": " + i);
        }
    }

    public static void interruptLearn(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }});
        // 启动线程
        t.start();

        boolean b = t.isInterrupted();
        System.out.println("检查线程启动后是否为挂起状态：" + b);

        // 让子线程挂起
        t.interrupt();

        b = t.isInterrupted();
        System.out.println("检查线程挂起后是否为挂起状态：" + b);
    }
}
