package com.nny.Demo.common;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class TEstBOot {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Testconif.class);
        Object hello = context.getBean("hello");
        System.out.println(hello.getClass());
    }


}
