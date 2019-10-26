package com.nny.Demo.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestConfiguration.class);

        Object hello = context.getBean("hello");
        System.out.println(hello);
    }
}
