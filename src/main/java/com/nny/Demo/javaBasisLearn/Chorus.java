package com.nny.Demo.javaBasisLearn;

import com.nny.Demo.javaBasisLearn.otherPackage.C;

/**
 * 泛型类，实现泛型接口
 * @param <K>
 * @param <V>
 */
public class Chorus<K,V> implements Computer<K,V>{

    @Override
    public void makeChorus(K k,V v) {
        k.toString();
        v.toString();
    }

    public static void main(String args[]){
        歌手 g = new 歌手();
        乐器 y = new 乐器();

        Chorus<歌手,乐器> model = new Chorus<>();
        model.makeChorus(g,y);

        Chorus chorus = new Chorus();
        chorus.makeChorus(g,y);
    }
}

class 乐器{

    //重写Object类的方法
    @Override
    public String toString() {
        System.out.println("乐器演奏！");
        return "";
    }
}

class 歌手{

    @Override
    public String toString() {
        System.out.println("歌手唱歌！");
        return "";
    }
}