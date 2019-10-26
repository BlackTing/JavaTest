package com.nny.Demo.ThreadTest.ThreadPoolTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,10,200,TimeUnit.MILLISECONDS,new ArrayBlockingQueue<Runnable>(5));
        for(int i = 0 ; i < 15; i++){ //当i的范围超出15，比如16，就会抛出拒绝执行任务的异常
            MyTask myTask = new MyTask(i);
            executor.execute(myTask); //把任务加入线程池去执行
            System.out.println("线程池中线程数目："+executor.getPoolSize()+
                            "，队列中等待执行的任务数目："+ executor.getQueue().size()+
                    "，已执行完的任务数目："+executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}
