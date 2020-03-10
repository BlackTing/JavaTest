package com.nny.Demo.concurrentLearn;

/**
 * 并发
 * 被监视块
 * 作为多线程的共享数据
 */

public class Drop {

    //生产者发送给消费者的消息
    private String message;

    //true时消费者等待生产者发送消息，false时生产者等待消费者获取消息
    private boolean empty = true;

    //取消息
    public synchronized String take() {

        //等待，直到消息可用
        while (empty) {
            try {
                wait();
            }
            catch (InterruptedException e) {}
        }

        // 改变状态
        empty = true;

        //通知生产者状态已经改变
        notifyAll();

        return message;
    }

    //放消息
    public synchronized void put(String message) {

        //等待，直到消息被获取
        while (!empty) {
            try {
                wait();
            }
            catch (InterruptedException e) {}
        }

        empty = false;

        this.message = message;

        notifyAll();
    }
}
