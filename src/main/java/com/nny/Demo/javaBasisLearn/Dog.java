package com.nny.Demo.javaBasisLearn;

/**
 * 继承抽象父类
 */
public class Dog extends Animal{

    /**
     * 重写抽象父类的方法
     */
    public void cry(){
        System.out.println("汪汪！");
    }

    public String getAnimalName(){
        return "狗";
    }
}
