package com.nny.Demo.CollectionLearn;

import java.util.HashSet;
import java.util.Set;

/**
 * Set接口
 * Set接口中的批量操作
 * 计算参数列表中只出现一次的字符串和多次出现的字符串
 */

public class FindDups2 {

    public static void main(String[] args){//i come i saw i left

        Set<String> uniques = new HashSet<String>();
        Set<String> dups = new HashSet<String>();

        for(String a : args){
            //返回false，意味着添加元素失败，意味着是重复元素
            if(!uniques.add(a)){
                dups.add(a);
            }
        }

        boolean a = uniques.removeAll(dups);

        System.out.println(a);//true
        System.out.println("Unique words: "+uniques);//Unique words: [left, saw, come]
        System.out.println("Duplicate words: "+dups);//Duplicate words: [i]
    }
}
