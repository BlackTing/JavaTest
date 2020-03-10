package com.nny.Demo.javaBasisLearn;

/**
 * 接口与多态
 */
public class AdvertismentBoard {
    /**
     *
     * @param advertisement 参数是接口变量
     */
    public void createBoard(Advertisement advertisement){
        System.out.println("显示"+advertisement.getName()+"家的广告");//接口回调
        advertisement.showAdvertisement();
    }
}
