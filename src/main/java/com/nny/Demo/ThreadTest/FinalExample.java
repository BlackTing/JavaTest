package com.nny.Demo.ThreadTest;

/**
 * java并发编程的艺术 3.6.1 final域的重排序规则
 */
public class FinalExample { //
    int i;//
    final int j;//
    static FinalExample obj;//
    public FinalExample(){
        i=1;//
        j=2; //1.在构造方法内对一个final域的写入
    }
    public static void writer(){
        obj = new FinalExample();//
    }
    public static void reader(){
        FinalExample object = obj;//2.把被构造对象的引用复制给一个引用变量；初次读对象引用
        int a = object.i;//
        int b = object.j;//3.初次读这个final域
    }
}
