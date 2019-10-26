package com.nny.Demo.ThreadTest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock {
    //静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer{

        //是否处于占用状态

        protected boolean isHeldExclusively(){
            return getState() == 1;
        }

        //当状态为0的时候获取锁,state=0 没有线程占用锁，state=1 有线程占用锁

        public boolean tryAcquire(int acquires){
            if(compareAndSetState(0,1)){ //原子性的修改state，当state为0时，改为1
                setExclusiveOwnerThread(Thread.currentThread()); //当前线程拥有这个锁
                return true;
            }
            return false;
        }

        //释放锁，将状态设置为0

        protected  boolean tryRelease(int releases){
            if(getState() == 0){
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null); //锁的拥有者置为null
            setState(0); //锁的同步状态置为未被获取
            return true;
        }
        //返回一个Condition,每个condition都包含了一个condition队列
        Condition newCondition(){
            return new ConditionObject();
        }
    }

    //仅需要将操作代理到Sync上即可

    private final Sync sync = new Sync();

    //获得锁

    public void lock(){

//        独占式获取同步器或者说同步状态
        sync.acquire(1); //调用tryAcquire()方法
        /**
         * 把当前线程加入到同步队列中:addWaiter(Node.EXCLUSIVE)
         * private Node addWaiter(Node mode){
         *      Node node = new Node(Thread.currentThread(),mode);
         *      //快速尝试在尾部添加
         *      Node pred = tail; // tail是类的成员变量
         *      if(pred != null){
         *          node.prev = pred; // predecessor 前驱节点
         *          if(compareAndSetTail(pred,node)){
         *              pred.next = node;
         *              return node;
         *          }
         *      }
         *      enq(node);
         *      return node;
         * }
         *
         * private Node enq(final Node node){
         *      for(;;){
         *          Node t = tail;
         *          if(t == null){ //musth initialize
         *              if(compareAndSetHead(new Node()))
         *                  tail = head;
         *          }else{
         *              node.prev = t;
         *              if(compareAndSetTail(t,node)){
         *                  t.next = node;
         *                  return t;
         *              }
         *          }
         *      }
         * }
         *
         * //同步队列中的节点（线程）自旋
         * final boolean acquireQueued(final Node node,int arg){
         *      boolean failed = true;
         *      try{
         *          boolean interrupted = false;
         *          for(;;){
         *              final Node p = node.predecessor();
         *              if(p == head && tryAcquire(arg)){
         *                  setHead(node);
         *                  p.next = null;
         *                  failed = false;
         *                  return interrupted;
         *              }
         *              if(shouldParkAfterFailedAcquire(p,node) && parkAndCheckInterrupt())
         *                  interrupted = true;
         *          }
         *      }finally{
         *          if(failed)
         *              cancelAcquire(node);
         *      }
         * }
         */
    }

//    超时的获取锁

    public boolean tryLock(){
//        独占的获取同步状态
        return sync.tryAcquire(1);
    }

//    释放锁

    public void unlock(){
        //独占的释放同步状态，调用tryRelease方法
        sync.release(1);
    }

//    获取等待通知组件，组件就是对象的意思，就是在哪个对象上加锁

    public Condition newCondition(){
        return sync.newCondition();
    }

    public boolean isLocked(){
//        同步状态是否被当前线程所独占
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
//        同步器是否有同步队列
        return sync.hasQueuedThreads();
    }
    public void lockInterruptibly()throws InterruptedException{
//        中断会让线程返回，因而离开所在的队列
        sync.acquireInterruptibly(1);
    }
    public boolean tryLock(long timeout, TimeUnit unit)throws InterruptedException{
//        独占，相应中断，超时限制
        return sync.tryAcquireNanos(1,unit.toNanos(timeout));
    }
}
/**
 * 共享式获取同步状态
 * public final void acquireShared(int arg){
 *     if(tryAcquireShared(arg) <0)
 *          doAcquireShared(arg);
 * }
 * private void doAcquireShared(int arg){
 *     final Node node = addWaiter(Node.SHARE);
 *     boolean failed = true;
 *     try{
 *         boolean interrupted = false;
 *         for(;;){
 *             final Node p = node.predecessor();
 *             if(p == head){
 *                 int r = tryAcquireShared(arg);
 *                 if(r >= 0){
 *                     setHeadAndPropagate(node,r);
 *                     p.next = null;
 *                     if(interrupted)
 *                          selfInterrupt();
 *                     failed = false;
 *                     return;
 *                 }
 *             }
 *             if(shouldParkAfterFailedAcquire(p,node) && parkAndCheckInterrupt())
 *                  interrupted = true;
 *         }
 *     }finally{
 *         if(failed){
 *             cancelAcquire(node);
 *         }
 *     }
 * }
 */
