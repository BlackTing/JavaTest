package com.nny.Demo.concurrentLearn;

import com.nny.Demo.ThreadTest.Interrupted;

/**
 * 并发
 * 创建Thread对象
 * sleep方法
 * 支持中断
 */
public class SleepMessages {

    public static void main(String args[]) throws InterruptedException{
        //m1();

        Thread thread = new Thread(new SR());

        thread.interrupt();
    }

    public static void m1() throws InterruptedException{
        String[] importantInfo = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        for(int i=0; i<importantInfo.length; i++){
            /**
             * 当前线程挂起执行4000毫秒
             * 当前线程是main线程
             */
            Thread.sleep(4000);
            System.out.println(importantInfo[i]);
        }
    }
}

class SR implements Runnable{

    @Override
    public void run() {
        String[] importantInfo = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };

        for(int i=0; i<importantInfo.length; i++){
            /**
             * 支持中断
             */
            try{
                Thread.sleep(4000);
            }
            catch (InterruptedException e){
                return;
            }

            System.out.println(importantInfo[i]);
        }
    }
}

