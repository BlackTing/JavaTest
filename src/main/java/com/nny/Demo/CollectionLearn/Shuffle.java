package com.nny.Demo.CollectionLearn;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * List接口
 * 位置访问
 * 重排列表
 */
public class Shuffle {

    public static void main(String[] args){
        List<String> list = Arrays.asList(args);

        Collections.shuffle(list);

        System.out.println(list);

    }
}
