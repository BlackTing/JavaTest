package com.nny.Demo.test;

import java.util.Random;

public class Person {

    public static void main(String[] args) {
        System.out.println(User.s2);
    }
}

class User {

    public static final String s2 = new Random().toString();

    {
        System.out.println("User.class");
    }
}
