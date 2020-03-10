package com.nny.Demo.SingletonLearn;

/**
 * 懒汉模式
 */
public class Singleton2 {

    //1.类变量
    private static Singleton2 single;

    //2.私有的构造方法
    private Singleton2(){}

    //3.类方法，返回实例
    public static Singleton2 getInstance(){

        //4.双重检查，如果实例不为空，不需要加锁而是直接返回实例
        //7.或许初始化并没有完成，导致返回一个未初始化完成的single
        if(single == null){

            //5.同步代码块，对class对象加锁
            synchronized(Singleton2.class){

                //6.这句话必须要有，获取锁之后再次判断，防止已经被之前获取锁的线程实例化过了
                if(single == null){

                    //8.初始化未完成，问题出现在这个语句
                    single = new Singleton2();
                }

            }

        }

        return single;

    }
}
