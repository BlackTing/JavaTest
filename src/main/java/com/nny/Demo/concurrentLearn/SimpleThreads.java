package com.nny.Demo.concurrentLearn;

/**
 * 并发
 * Thread对象
 * 应用示例：new sleep interrupt join
 */
public class SimpleThreads {

    static void threadMessage(String message) {
        String threadName = Thread.currentThread().getName();

        System.out.format("%s: %s%n", threadName, message);
    }

    private static class MessageLoop implements Runnable {
        public void run() {
            String importantInfo[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };

            try {
                for (int i = 0; i < importantInfo.length; i++) {
                    // 暂停执行4秒
                    Thread.sleep(4000);
                    threadMessage(importantInfo[i]);
                }
            }
            catch (InterruptedException e) {
                threadMessage("I wasn't done!");
            }
        }
    }

    public static void main(String args[]) throws InterruptedException {

        // 中断MessageLoop线程前延迟的毫秒数
        long patience = 10*1000;

        if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }

        threadMessage("Starting MessageLoop thread");

        long startTime = System.currentTimeMillis();

        Thread t = new Thread(new MessageLoop());

        t.start();

        threadMessage("Waiting for MessageLoop thread to finish");

        //循环直到ThreadLoop线程退出
        while (t.isAlive()) {

            threadMessage("Still waiting...");

            //为MessageLoop线程完成执行等待最多2秒
            t.join(2000);

            if (((System.currentTimeMillis() - startTime) > patience) && t.isAlive()) {

                threadMessage("Tired of waiting!");

                t.interrupt();

                //应该不会太久了，无限期等待
                t.join();
            }
        }

        threadMessage("Finally!");
    }

    /**
     * main: Starting MessageLoop thread 开始执行MessageLoop线程
     * main: Waiting for MessageLoop thread to finish 主线程等待MessageLoop线程结束执行
     * main: Still waiting... 打印后join MessageLoop线程 2s
     * main: Still waiting...
     * Thread-0: Mares eat oats MessageLoop线程睡眠4s后打印
     * main: Still waiting...
     * main: Still waiting...
     * Thread-0: Does eat oats
     * main: Still waiting...
     * main: Tired of waiting! 主线程等累了，中断MessageLoop线程
     * Thread-0: I wasn't done! MessageLoop线程结束执行
     * main: Finally! 主线程结束执行
     */
}
