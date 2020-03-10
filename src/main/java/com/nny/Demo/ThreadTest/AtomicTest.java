package com.nny.Demo.ThreadTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

    private AtomicInteger atomicI = new AtomicInteger(0);
    private int i = 0;

    public static void main(String[] args){
        final AtomicTest cas = new AtomicTest();
        List<Thread> ts = new ArrayList(600);

        long start = System.currentTimeMillis();

        for(int j = 0;j<100;j++){
            Thread t = new Thread(new Runnable(){
                public void run(){
                    for(int i = 0;i<10000;i++){
                        cas.count();
                        cas.safeCount();
                    }
                }
            });
            ts.add(t);
        }

        for(Thread t : ts){
            t.start();
        }

        //main线程等待所有线程执行完成
        for(Thread t : ts){
            try{
                t.join();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        System.out.println(cas.i);
        System.out.println(cas.atomicI.get());
        System.out.println(System.currentTimeMillis() - start);
    }

    //使用CAS实现线程安全计数器
    private void safeCount(){
        for(;;){ //无限循环
            int i = atomicI.get();//记录旧值
            boolean suc = atomicI.compareAndSet(i,++i); //CAS
            if(suc){
                break;
            }
        }
    }

    //非线程安全计数器
    private void count(){
        i++;
    }
}
