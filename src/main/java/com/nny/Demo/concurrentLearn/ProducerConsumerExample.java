package com.nny.Demo.concurrentLearn;

/**
 * 并发
 * 被监视的块
 * 生产者与消费者
 */
public class ProducerConsumerExample {

    public static void main(String[] args) {

        Drop drop = new Drop();

        (new Thread(new Producer(drop))).
                start();

        (new Thread(new Consumer(drop))).
                start();
    }
}
