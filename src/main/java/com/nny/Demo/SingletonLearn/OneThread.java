package com.nny.Demo.SingletonLearn;

public class OneThread implements Runnable{

    private Singleton1[] singles;
    private int i;

    public OneThread(Singleton1[] singles,int i){
        this.singles = singles;
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println(System.currentTimeMillis() + ":" + Singleton1.getInstance());
    }
}
