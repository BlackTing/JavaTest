package com.nny.Demo.ThreadTest;

public class DeadLockDemo {
    private static String A = "A";
    private static String B = "B";
    public static void main(String[] args){
        new DeadLockDemo().deadLock();

    }
    private void deadLock(){
        Thread t1 = new Thread(new Runnable(){
            public void run(){
                synchronized(A){
                    try{
                        Thread.currentThread().sleep(2000);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                    synchronized(B){
                        System.out.println("1");
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable(){
            public void run(){
                synchronized(B){
                    synchronized(A){
                        System.out.println("2");
                    }
                }
            }
        });
        t1.start();
        t2.start();
    }
    /**
     * LinkedTransferQueue
     * 队列中的头部节点
     * private transient final PaddedAtomicReference<QNode> head; //内部类类型
     * 队列中的尾部节点
     * private transient final PaddedAtomicReference<QNode> tail;
     * static final class PaddedAtomicReference<T> extends AtomicReference <T>{
     *     //使用很多4个字节的引用追加到64个字节
     *     Object p0,p1,p2,p3,p4,p5,p6,p7,p8,p9,pa,pb,pc,pd,pe; //将共享变量由4字节追加到64字节 15*4=60；一个对象的引用占4个字节
     *     PaddedAtomicReference(T r){
     *         super(r);
     *     }
     * }
     * public class AtomicReference<V> implements java.io.Serializable{
     *     private volatile V value;
     *     //省略其他代码
     * }
     */
}
