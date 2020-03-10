package com.nny.Demo.ThreadTest;

import java.util.concurrent.TimeUnit;

/**
 * 学习Thread类的实例方法join（）
 */
public class Join {

    public static void main(String[] args) throws Exception {

        Thread previous = Thread.currentThread();

        for (int i = 0; i < 10; i++) {
            // 每个线程拥有前一个线程的引用，需要等待前一个线程终止，才能从等待中返回
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }
        //thread0 wait for main,thread1 wait for thread0,thread2 wait for thread1...

        //main thread 休眠5s
        TimeUnit.SECONDS.sleep(5);

        System.out.println(Thread.currentThread().getName() + " terminate.");
    }

    static class Domino implements Runnable {

        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        public void run() {

            try {
                thread.join();
            } catch (InterruptedException e) {
            }

            System.out.println(Thread.currentThread().getName() + " terminate.");
        }
    }

}
