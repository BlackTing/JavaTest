package com.nny.Demo.javaBasisLearn;

/**
 * 外嵌类
 */
public class RedCowFarm {

    //友好变量
    String farmName;
    public RedCow redCow; //使用内部类声明对象

    public RedCowFarm(){}

    public RedCowFarm(String s){
        farmName = s;
        /**
         * 在封装类中实例化内部类
         */
        redCow = new RedCow(150,112,3342);
    }

    public void showCowMess(){
        redCow.speak();
    }

    /**
     * 内部类
     * 内部类的声明
     */
    class RedCow{
        /**
         * 静态的常量变量
         */
        static final String cowName = "红牛";
        int height,weight,price;

        RedCow(int h,int w,int p){
            height = h;
            weight = w;
            price = p;
        }

        RedCow(int h,int w){
            height = h;
            weight = w;
        }

        void speak(){
            System.out.println("我生活在"+farmName);
        }
    }

}
