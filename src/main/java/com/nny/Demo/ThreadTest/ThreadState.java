package com.nny.Demo.ThreadTest;

public class ThreadState {
    public static void main(String[] args){
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        /**
         * "TimeWaitingThread" #14 prio=5 os_prio=0 tid=0x0000023715e4c800 nid=0x4424 waiting on condition  [0x000000c22aeff000]
         *    java.lang.Thread.State: TIMED_WAITING (sleeping)
         */

        new Thread(new Waiting(),"WaitingThread").start();
        /**
         * "WaitingThread" #15 prio=5 os_prio=0 tid=0x0000023715e4f800 nid=0x5b38 in Object.wait()  [0x000000c22affe000]
         *    java.lang.Thread.State: WAITING (on object monitor)
         */

        //使用两个Blocked()线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(),"BlockedThread-1").start();
        /**
         * "BlockedThread-1" #16 prio=5 os_prio=0 tid=0x0000023715e4b000 nid=0x4a28 waiting on condition  [0x000000c22b0fe000]
         *    java.lang.Thread.State: TIMED_WAITING (sleeping)
         */

        new Thread(new Blocked(),"BlockedThread-2").start();
        /**
         * "BlockedThread-2" #17 prio=5 os_prio=0 tid=0x0000023715e4b800 nid=0x448 waiting for monitor entry  [0x000000c22b1ff000]
         *    java.lang.Thread.State: BLOCKED (on object monitor)
         */
    }
    //该线程不断地进行睡眠
    static class TimeWaiting implements Runnable{
        public void run(){
            while(true){
                SleepUtils.second(100);
            }
        }
    }
    //该线程在Waiting.class实例上等待
    static class Waiting implements Runnable{
        public void run(){
            while(true){
                synchronized(Waiting.class){
                    try{
                        Waiting.class.wait();//能让当前线程阻塞，并且当前线程必须拥有此对象的monitor（即锁）
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    //该线程在Blocked.class实例上加锁后，不会释放该锁
    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized(Blocked.class){
                while(true){
                    SleepUtils.second(100);
                }
            }
        }
    }
}
