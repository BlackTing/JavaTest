package com.nny.Demo.SingletonLearn;

// 饿汉式单例
public class Singleton1 {

    private static Singleton1 single = new Singleton1();

    // 私有构造
    public Singleton1() {}

    // 静态工厂方法
    public static Singleton1 getInstance() {
        return single;
    }
}
/**
 * 为什么说饿汉模式不会有并发问题？
 *
 * 因为在类加载时，初始化single
 */
