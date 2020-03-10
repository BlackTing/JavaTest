package com.nny.Demo.javaBasisLearn;

public class ObjectLearn {

    /**
     * ==
     * "=="相等判断符用于比较基本数据类型和引用类型数据。
     * 当比较基本数据类型的时候比较的是数值，当比较引用类型数据时比较的是引用(指针)。
     * 用"=="判断两个引用数据类型是否相等的时候，实际上是在判断两个引用是否指向同一个对象。
     *
     * hashcode()
     * 不同的对象可能会生成相同的hashcode值.如果两个对象的hashcode值不等，则必定是两个不同的对象.
     *
     * equals()
     * 类对象的equals方法实现了对对象尽可能区别的等价关系;也就是说，对于任何非空的参考值x和y，当且仅当x和y指向同一个对象时，该方法返回true (x == y的值为true)。
     * 注意，通常需要在重写hashCode方法时重写该方法，以便维护hashCode方法的常规契约，该契约规定相等的对象必须具有相等的散列代码.
     *
     * 也就是说对于两个对象，如果调用equals方法得到的结果为true，则两个对象的hashcode值必定相等；
     *
     * 　　如果equals方法得到的结果为false，则两个对象的hashcode值不一定不同；
     *
     * 　　如果两个对象的hashcode值不等，则equals方法得到的结果必定为false；
     *
     * 　　如果两个对象的hashcode值相等，则equals方法得到的结果未知。
     */

    /**
     * toString()
     *
     * class Object的toString方法返回一个字符串，该字符串由对象是其实例的类的名称、at符号字符“@”和对象的哈希码的无符号十六进制表示形式组成。
     * 换句话说，这个方法返回一个字符串等于:
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     *
     */
}
