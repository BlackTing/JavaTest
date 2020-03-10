package com.nny.Demo.ThreadTest;

/**
 * 学习Thread类的常用方法
 * 1. start()
 * 2. sleep()
 * 3. join()
 * 4. interrupted()
 * 5. yield()
 */
public class ThreadLearn {
    public static void main(String[] args){
        joinLearn();
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

    /**
     * t.join()方法会阻塞调用它的线程，等待线程t运行终止，此线程再继续运行
     */
    public static void joinLearn(){

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("Thread1 start");

                try{
                    Thread.sleep(1000*5);
                }
                catch(InterruptedException e){}

                System.out.println("Thread1 stop");
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {

                System.out.println("Thread2 start");

                try {
                    thread1.join();
                }
                catch(InterruptedException e){
                    System.out.println("thread2 被中断");
                }

                System.out.println("Thread2 stop");
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                //中断被阻塞的线程
                thread2.interrupt();
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

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

        Thread t = new Thread(new InterruptThread());

        t.start();

        boolean b = t.isInterrupted();
        System.out.println("检查线程启动后是否为挂起状态：" + b);

        // 让子线程挂起
        t.interrupt();

        b = t.isInterrupted();
        System.out.println("检查线程挂起后是否为挂起状态：" + b);
    }

    static class InterruptThread implements Runnable{

        @Override
        public void run() {

            try {

                Thread.sleep(1000 * 10);


            }catch(InterruptedException e){

                System.out.println("抛出异常了");
                //抛出异常前的中断状态是true，抛出异常后的中断状态是false

            }

            while(true){

            }

        }
    }
}
