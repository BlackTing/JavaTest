package com.nny.Demo.CollectionLearn;

import java.util.ArrayDeque;
import java.util.Iterator;

/**
 * 集合
 * 实现
 * Deque实现
 * ArrayDeque
 */
public class ArrayDequeSample {

    public static void main(String[] args) {

        ArrayDeque<String> aDeque = new ArrayDeque<> (5);

        /**
         * 添加
         */
        aDeque.addFirst("tea");
        aDeque.addFirst("milk");
        aDeque.addFirst("coffee");
        aDeque.addLast("sugar");

        /**
         * 向前遍历
         */
        for( Iterator itr =  aDeque.iterator(); itr.hasNext(); ) {
            System.out.println(itr.next());
        }
        System.out.println();

        aDeque.addFirst("juice");
        aDeque.addLast("honey");

        /**
         * 检索
         */
        System.out.println("First Element : " + aDeque.getFirst());
        System.out.println("Last Element : " + aDeque.getLast());

        /**
         * 删除
         */
        System.out.println("First Element(Removed):"+aDeque.removeFirst());
        System.out.println("Last Element Removed:"+aDeque.removeLast());

        /**
         * 从头部弹出
         */
        System.out.println("%nPopped Element : " + aDeque.pop());

        System.out.println("%n Size of Array Deque: " + aDeque.size());

    }
}
