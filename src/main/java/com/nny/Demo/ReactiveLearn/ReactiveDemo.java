package com.nny.Demo.ReactiveLearn;

import java.util.Observable;


public class ReactiveDemo {
    public static void main(String[] args){
        //可观察对象
        MyObservable observable = new MyObservable();

        //添加观察者
        observable.addObserver((o, arg) -> {
            System.out.println(Thread.currentThread()+" 观察者1处理事件:" + arg.toString());
        });

        observable.addObserver((o, arg) -> {
            System.out.println(Thread.currentThread()+"观察者2处理事件:" + arg.toString());
        });

        observable.addObserver((o, arg) -> {
            System.out.println(Thread.currentThread()+"观察者3处理事件:" + arg.toString());
//            while(true){
//                System.out.println("haha");
//            }
        });

        observable.setChanged();

        System.out.println(Thread.currentThread()+"事件响应开始处理");

        //发布事件通知观察者
        observable.notifyObservers("事件@@"); //改变程序执行顺序，执行完成后会跳转回来

        System.out.println(Thread.currentThread()+"事件响应处理结束"); //三个观察者的执行顺序正好和添加顺序相反，很像栈
    }
    static class MyObservable extends Observable {
        @Override
        public void setChanged(){
            super.setChanged();
        }
    }
}

