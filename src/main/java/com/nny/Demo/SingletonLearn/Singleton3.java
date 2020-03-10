package com.nny.Demo.SingletonLearn;

/**
 * 阅读了《Java并发编程艺术》中的类的延迟初始化一节
 * 对懒汉模式提出质疑:
 * 有问题的语句： single = new Singleton2()
 *
 * 这个语句的执行分为3部分：
 * memory = allocate(); // 1：分配对象的内存空间
 * ctorInstance(memory); // 2：初始化对象
 * instance = memory; // 3：设置instance指向刚分配的内存地址
 *
 * 2和3可能会重排序，当发生重排序时，其他线程在双重检查的第一道检查时会得到instance不为空的结果，于是错误就发生了。
 *
 *
 */
public class Singleton3 {
}
