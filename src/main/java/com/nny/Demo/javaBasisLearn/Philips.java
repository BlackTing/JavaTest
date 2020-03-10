package com.nny.Demo.javaBasisLearn;

/**
 * 实现接口
 */
public class Philips implements Advertisement {

    /**
     * 重写接口中的方法
     * 必须加上public，不然就降低了访问权限
     */
    @Override
    public void showAdvertisement() {
        System.out.println("飞利浦的广告词"+i);
    }

    @Override
    public String getName() {
        return "飞利浦";
    }
}
