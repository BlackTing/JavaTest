package com.nny.Demo.ThreadTest;

import java.util.concurrent.CountDownLatch;

/**
 * 在选手线程中
 * 首先，选手在起跑线上等待主裁判鸣枪：调用begin.await()
 *
 * 在主线程中
 * 首先，主裁判宣布比赛开始：System.out.println("the race begin")
 * 然后，主裁判鸣枪：调用begin.countDown()唤醒等待的选手线程
 *
 *
 * 在主线程中
 * 首先，主裁判等待2位选手到达终点：end.await()
 *
 * 在选手线程中
 * 首先，选手到达终点：System.out.println(Thread.currentThread().getName() + " arrived !")
 * 然后，通知主裁判宣布比赛结束：调用end.countDown()
 */
public class CountDownLearn {

    public static void main(String[] args) {
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(2);

        for(int i=0; i<2; i++){
            Thread thread = new Thread(new Player(begin,end));
            thread.start();
        }

        try{
            System.out.println("the race begin");
            begin.countDown();
            end.await();
            System.out.println("the race end");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}


/**
 * 选手
 */
class Player implements Runnable{

    private CountDownLatch begin;

    private CountDownLatch end;

    Player(CountDownLatch begin, CountDownLatch end){
        this.begin = begin;
        this.end = end;
    }

    public void run() {

        try {
            begin.await();
            System.out.println(Thread.currentThread().getName() + " arrived !");;
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

