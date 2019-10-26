package com.nny.Demo.ThreadTest.ThreadPoolTest;

public class MyTask implements Runnable {
    private int taskNum; //任务序号
    public MyTask(int num){
        this.taskNum = num;
    }
    public void run(){
        System.out.println("正在执行task "+taskNum);
        try{
            Thread.currentThread().sleep(4000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("task "+taskNum+"执行完毕");
    }
}
