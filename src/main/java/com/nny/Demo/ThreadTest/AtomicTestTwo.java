package com.nny.Demo.ThreadTest;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTestTwo {

    //自增变量
    private static AtomicInteger race = new AtomicInteger(0);

    private static final int THREAD_COUNT = 20;

    public static void main(String[] args){

        Thread[] threads = new Thread[THREAD_COUNT];

        for(int i=0 ; i<threads.length ; i++){

            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {

                    for(int i=0 ; i<10000 ; i++){
                        race.incrementAndGet();
                    }
                }
            });

            threads[i].start();
        }

        while(Thread.activeCount()>2){//除了main线程还有其他线程活着
            // 测试时发现20个线程都执行完了，也还有2个线程，一个是main线程，另一个不知道是什么线程，所以改为>2
            Thread.yield();//main线程礼让CPU时间片
        }

        System.out.println(race);
    }

}
