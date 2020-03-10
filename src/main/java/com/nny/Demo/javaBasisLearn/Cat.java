package com.nny.Demo.javaBasisLearn;

/**
 * 继承抽象父类
 */
public class Cat extends Animal{

    public void cry(){
        System.out.println("喵喵！");
    }

    @Override
    public String getAnimalName() {
        return "猫";
    }
}
