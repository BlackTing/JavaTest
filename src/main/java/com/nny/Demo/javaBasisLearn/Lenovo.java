package com.nny.Demo.javaBasisLearn;

/**
 * 实现接口
 */
public class Lenovo implements Advertisement {

    @Override
    public void showAdvertisement() {
        System.out.println("联想的广告");
    }

    @Override
    public String getName() {
        return "联想";
    }
}
