package com.nny.Demo.ThreadTest.LearnScheduledThreadPoolExecutor;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPoolTest {
    public static void main(String[] args) {
        //创建大小为5的线程池
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 3; i++) {
            Task worker = new Task("task-"+i);
            scheduledThreadPool.scheduleAtFixedRate(worker,0,5, TimeUnit.SECONDS);
        }
        try {
            System.out.println("主线程开始睡眠");
            Thread.sleep(10000);
            System.out.println("主线程结束睡眠");
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Shutting down executor...");
        //关闭线程池
        scheduledThreadPool.shutdown();
        boolean isDone = false;
        //等待线程池终止
        do{
            try {
                isDone = scheduledThreadPool.awaitTermination(1, TimeUnit.DAYS);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (!isDone);
        System.out.println("Finished all threads");
    }
}
class Task implements Runnable{
    private String name;
    public Task(String name){
        this.name = name;
    }

    public void run(){
        System.out.println("name = " + name + ", startTime = " + new Date());
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("name = " + name + ", endTime = " + new Date());
    }
}
