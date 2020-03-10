package com.nny.Demo.CollectionLearn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 接口
 * Set接口
 * 把输入的参数添加到新的Set对象中，去除重复元素
 */
public class FindDups {

    public static void main(String[] args){

        /**
         * 使用聚合操作
         */
        Set<String> distinctWords = Arrays.asList(args).stream()
                .collect(Collectors.toSet());//来自于Set的哪个实现类型？

        System.out.println(distinctWords.size()+
                " distinct words:" +
                distinctWords);//4 distinct words: [left, came, saw, i]
        /**
         * 使用for-each结构
         */
        Set<String> s = new HashSet<String>();

        for(String a : args)
            s.add(a);

        System.out.println(s.size()+" distinct words: "+s);//4 distinct words: [left, came, saw, i]

        /**
         * Set的toString方法应该被重写了
         */
        System.out.println(s.size()+" distinct words: "+s.toString());//4 distinct words: [left, came, saw, i]

        /**
         * 按字母顺序排序元素
         */
        Set<String> d = new TreeSet<String>();

        for(String a : args)
            d.add(a);

        System.out.println(d.size()+" distinct words: "+d);//4 distinct words: [came, i, left, saw]

        /**
         *
         */
        boolean a = d.remove("i");//true
        boolean b = d.remove("a");//false
        System.out.println("a "+a+" b "+b);


    }
}
