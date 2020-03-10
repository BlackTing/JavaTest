package com.nny.Demo.CollectionLearn;

import java.util.*;

public class E {

    public static void main(String[] args){
        List<String> a = new ArrayList<>();
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
        List<String> b = new ArrayList<>();
        b.add("e");
        b.add("f");
        b.add("g");
        b.add("h");

        m5();
    }

    /**
     * 便利的实现
     * Arrays.asList
     */
    public static void m5(){
        String[] a = {"a","b","c","d"};

        List<String> b = Arrays.asList(a);

        String c = b.get(0);

        System.out.println(c);//a

        b.add("e");//UnsupportedOperationException

    }

    /**
     * Comparable.compareTo(T o)
     */
    public static void m4(){
        Integer a = 5;
        Integer b = 10;
        int c = a.compareTo(b);//-1
        System.out.println(c);
    }

    public static void m3(){

        Name nameArray[] = {
                new Name("John", "Smith"),
                new Name("Karl", "Ng"),
                new Name("Jeff", "Smith"),
                new Name("Tom", "Rich")
        };

        List<Name> names = Arrays.asList(nameArray);

        /**
         * 内部会调用集合元素的compareTo方法
         */
        Collections.sort(names);

        System.out.println(names);

    }

    /**
     * List.subList
     */
    public static void m2(List b){

        List<String> c = new ArrayList(b);
        int d = c.subList(1,3).indexOf("f");//0
        c.subList(1,3).clear();//e,h

        List<String> f = new ArrayList<>(b);
        List<String> e = dealHand(f,2);//[g, h]

        /**
         * subList的subList操作
         */
        List<String> g = new ArrayList<>(b);
        List<String> h = g.subList(1,4);//f,g,h
        h.subList(1,3).clear();//f

        System.out.println(g);
    }

    public static <E> List<E> dealHand(List<E> deck,int n){
        int deckSize = deck.size();
        List<E> handView = deck.subList(deckSize-n,deckSize);
        List<E> hand = new ArrayList<E>(handView);
        handView.clear();
        return hand;
    }

    /**
     * ListIterator的add,remove操作
     */
    public static <E> void replace(List<E> list, E val, List<? extends E> newVals) {
        for (ListIterator<E> it = list.listIterator(); it.hasNext(); ){
            if (val == null ? it.next() == null : val.equals(it.next())) {
                it.remove();
                for (E e : newVals)
                    it.add(e);
            }
        }
    }

    /**
     * ListIterator迭代与游标位置
     */
    public static void m1(){
        String[] a = {"a","b","c","d"};

        List<String> list = Arrays.asList(a);

        ListIterator<String> b = list.listIterator(list.size());//此时游标的位置索引是4.

        while(b.hasPrevious()){

            int c = b.previousIndex();//3

            //String g = b.next(); //NoSuchElementException

            int f = b.nextIndex();//4

            String d = b.previous();//d

            int e = b.previousIndex();//2

            int h = b.nextIndex();//3

            //System.out.println(h);

            break;
        }

        ListIterator<String> i = list.listIterator();//此时，游标的索引位置是0

        while(b.hasNext()){
            int j = i.previousIndex();//-1
            int k = i.nextIndex();//0

            String l = i.next();//a

            int m = i.previousIndex();//0
            int n = i.nextIndex();//1

            System.out.println(n);
            break;
        }
    }

}
