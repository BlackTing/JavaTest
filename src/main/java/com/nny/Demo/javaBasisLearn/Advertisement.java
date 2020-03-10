package com.nny.Demo.javaBasisLearn;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * 接口
 */
public interface Advertisement {

    int i = 1;
    List list = new ArrayList();


    public abstract void showAdvertisement();//可以省略public abstract
    String getName();

    //default
    default boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return false;
    }
}
