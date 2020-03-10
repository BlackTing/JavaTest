package com.nny.Demo.concurrentLearn;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ThreadLocal演示
 *
 * 生成每个线程的唯一标识符。
 * 线程的id在第一次调用threadId .get()时被分配，并且在随后的调用中保持不变。
 */
public class ThreadId {

    private static final AtomicInteger nextId = new AtomicInteger(0);

    /**
     * 匿名类，继承ThreadLocal类
     *
     * 声明线程局部变量
     */
    private static final ThreadLocal<Integer> threadId =
            new ThreadLocal<Integer>() {
                @Override protected Integer initialValue() {
                    return nextId.getAndIncrement();
                }
            };

    /**
     * 获取线程局部变量的值
     * @return
     */
    public static int get() {
        return threadId.get();
    }


}