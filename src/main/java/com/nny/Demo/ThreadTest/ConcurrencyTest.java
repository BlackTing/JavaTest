package com.nny.Demo.ThreadTest;

public class ConcurrencyTest {
    private static final long count = 10000001;
    public static void main(String[] args) throws InterruptedException{
        concurrency(); //并发
        serial(); //串行

    }
    private static void concurrency() throws InterruptedException{
        long start = System.currentTimeMillis();
        Thread thread = new Thread(new Runnable(){
            public void run(){
                int a = 0;
                for(long i=0;i<count;i++){
                    a += 5;
                }
            }
        });
        thread.start();
        int b = 0;
        for(long i = 0;i<count;i++){ //在主线程中进行
            b--;
        }
        thread.join(); //调用线程等待该线程完成后，才能继续用下运行
        long time = System.currentTimeMillis() - start;
        System.out.println("concurrency :" + time+"ms,b="+b);
    }
    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for(long i = 0;i<count;i++){
            a+=5;
        }
        int b = 0;
        for(long i = 0;i<count;i++){
            b--;
        }
        long time = System.currentTimeMillis() - start;
        System.out.println("serial :" + time+"ms,b="+b);
    }
}
