package com.nny.Demo.javaBasisLearn;

/**
 * 抽象类与多态
 */
public class Simulator {

    /**
     * @param animal 方法参数的类型是抽象父类
     */
    public void playSound(Animal animal){
        System.out.println("播放"+animal.getAnimalName()+"的叫声：");
        animal.cry();
    }
}
