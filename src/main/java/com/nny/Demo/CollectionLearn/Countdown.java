package com.nny.Demo.CollectionLearn;

import java.util.*;

/**
 * 集合
 * 接口
 * Queue接口
 */
public class Countdown {
    public static void main(String[] args) throws InterruptedException {
        int time = 5;
        /**
         * LinkedList
         */
        LinkedList<Integer> queue = new LinkedList<Integer>();

        for (int i = time; i >= 0; i--)
            queue.add(i);//头部 5 4 3 2 1 0 尾部

        List a = heapSort(queue);
        System.out.println(a);

        while (!queue.isEmpty()) {
            System.out.println(queue.remove());//54321
            Thread.sleep(1000);
        }


    }

    /**
     * 表明优先级队列的排序属性
     * @param c
     * @param <E>
     * @return
     */
    static <E> List<E> heapSort(Collection<E> c) {
        /**
         * 优先级队列
         * 起到排序作用
         */
        PriorityQueue<E> queue = new PriorityQueue<E>(c);

        List<E> result = new ArrayList<E>();

        while (!queue.isEmpty()){
            E e = queue.remove();
            result.add(e);
        }


        return result;//0 1 2 3 4 5
    }
}
