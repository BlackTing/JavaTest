package com.nny.Demo.concurrentLearn;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Random;

/**
 * 并发
 * 高级并发对象
 * Lock对象
 * 使用Lock对象解决了前面的死锁问题
 */

public class Safelock {

    static class Friend {
        private final String name;

        /**
         * 定义一个锁对象
         */
        private final Lock lock = new ReentrantLock();

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        /**
         * 如果两个锁都占用到了，返回true
         * 否则返回false
         * 与同步的方式相比，先尝试获取两个锁，而不是先只获取一个锁
         * @param bower
         * @return
         */
        public boolean impendingBow(Friend bower) {
            Boolean myLock = false;
            Boolean yourLock = false;
            try {
                myLock = lock.tryLock();
                yourLock = bower.lock.tryLock();
            }
            finally {
                if (! (myLock && yourLock)) {
                    if (myLock) {
                        lock.unlock();
                    }
                    if (yourLock) {
                        bower.lock.unlock();
                    }
                }
            }
            return myLock && yourLock;
        }

        /**
         * 本人接收到朋友bower的鞠躬
         * @param bower
         */
        public void bow(Friend bower) {
            if (impendingBow(bower)) {
                try {
                    System.out.format("%s: %s has bowed to me!%n", this.name, bower.getName());

                    bower.bowBack(this);
                } finally {
                    lock.unlock();
                    bower.lock.unlock();
                }
            }
            else {
                System.out.format("%s: %s started to bow to me, but saw that I was already bowing to him.%n",
                        this.name, bower.getName());
            }
        }

        /**
         * 朋友bower接收到本人的鞠躬
         * 这里就不用再去获取锁了，因为前面已经先获取到锁了
         * @param bower
         */
        public void bowBack(Friend bower) {
            System.out.format("%s: %s has bowed back to me!%n", this.name, bower.getName());
        }
    }

    /**
     * 任务：本人循环地向朋友bower鞠躬
     * 每次鞠躬之间休眠一段时间
     */
    static class BowLoop implements Runnable {
        private Friend bower;
        private Friend bowee;

        public BowLoop(Friend bower, Friend bowee) {
            this.bower = bower;
            this.bowee = bowee;
        }

        public void run() {
            Random random = new Random();
            for (;;) {
                try {
                    Thread.sleep(random.nextInt(100));
                }
                catch (InterruptedException e) {}

                bowee.bow(bower);
            }
        }
    }


    public static void main(String[] args) {

        final Friend alphonse = new Friend("Alphonse");
        final Friend gaston = new Friend("Gaston");

        /**
         * 两个朋友分别向彼此鞠躬
         */
        new Thread(new BowLoop(alphonse, gaston)).start();

        new Thread(new BowLoop(gaston, alphonse)).start();
    }

    /**
     * Gaston: Alphonse has bowed to me! : 线程1获取到两个锁
     * Alphonse: Gaston started to bow to me, but saw that I was already bowing to him.:线程1占有着两个锁，线程2退出获取锁的尝试
     * Alphonse: Gaston has bowed back to me!：线程1占有着两个锁，完成任务，并释放两个锁
     *
     * Gaston: Alphonse has bowed to me!：线程1再次获取两个锁
     * Alphonse: Gaston has bowed back to me!：线程1占有着两个锁，完成任务，并释放两个锁
     *
     * Alphonse: Gaston has bowed to me!：线程2获取到两个锁
     * Gaston: Alphonse has bowed back to me!：线程2占有着两个锁，完成任务，并释放两个锁
     *
     * Alphonse: Gaston has bowed to me!：线程2再次获取两个锁
     * Gaston: Alphonse has bowed back to me!：线程2占有着两个锁，完成任务，并释放两个锁
     */
}
