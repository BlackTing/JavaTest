package com.nny.Demo.javaBasisLearn.otherPackage;

public class C {

    public static void main(String[] args){
        Son2 son2 = new Son2();

        //访问子类继承的公共变量
        System.out.println(son2.a);

        //不能访问子类继承的受保护的变量
        //因为当前类虽然和子类在一个包，但是子类和父类不在同一个包，也就是说当前类和父类不在同一个包
        //System.out.println(son2.c);
    }
}
