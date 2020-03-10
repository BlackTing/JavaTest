package com.nny.Demo.concurrentLearn;

/**
 * 并发
 * 活性
 * 死锁
 */
public class DeadLock {

    static class Friend{

        private final String name;

        public Friend(String name){
            this.name = name;
        }

        public String getName(){
            return name;
        }

        public synchronized void bow(Friend bower) {//获得this对象的锁
            System.out.format("%s:%s"+" has bowed to me!%n",this.name,bower.getName());
            bower.bowback(this);//获得bower对象的锁
        }

        public synchronized void bowback(Friend bower){
            System.out.format("%s:%s"+" has bowed back to me!%n",this.name,bower.getName());
        }
    }

    public static void main(String[] args) throws Exception{
        final Friend alphonse = new Friend("Alphonse");

        final Friend gaston = new Friend("Gaston");

        new Thread(new Runnable(){
            public void run(){
                alphonse.bow(gaston);//先获得alphonse对象的锁，再获得gaston对象的锁
            }
        }).start();

        //Thread.sleep(2000); //可以保证第一个线程先获取两个锁再开始第二个线程，就可以避免死锁

        new Thread(new Runnable() {
            @Override
            public void run() {
                gaston.bow(alphonse);//先获得gaston对象的锁，再获得alphonse对象的锁
            }
        }).start();
    }
    /**
     * 阿尔封斯和加斯顿是朋友。一个严格的礼貌原则是，当你向朋友鞠躬时，你必须一直鞠躬，直到你的朋友有机会还礼。
     * 不幸的是，这条规则并不能解释两个朋友同时向对方鞠躬的可能性。
     */
}
