package com.nny.Demo.CollectionLearn;

/**
 * Map接口
 * 多值映射
 * 把用同样的字母组成的词分为一组
 */

import java.util.*;

public class Anagrams {

    public static void main(String[] args) {

        int minGroupSize = Integer.parseInt("0");

        Map<String, List<String>> m = new HashMap<String, List<String>>();

        Scanner s = new Scanner(System.in);

        while (s.hasNext()) {

            String word = s.next();

            if(word.equals("-1")){
                break;
            }

            String alpha = alphabetize(word);//作为key

            List<String> l = m.get(alpha);

            if (l == null)
                m.put(alpha, l=new ArrayList<String>());

            l.add(word);
        }

        Collection<List<String>> a = m.values();

        for (List<String> l : a)
            if (l.size() >= minGroupSize)
                System.out.println(l.size() + ": " + l);
    }

    /**
     * 依字母顺序排列
     */
    public static String alphabetize(String s) {

        char[] a = s.toCharArray();

        Arrays.sort(a);

        return new String(a);
    }
}