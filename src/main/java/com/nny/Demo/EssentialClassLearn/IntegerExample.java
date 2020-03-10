package com.nny.Demo.EssentialClassLearn;

public class IntegerExample {
    public static void main(String[] args){
        int a = 1<<30;
        int b = Integer.numberOfLeadingZeros(a); //1

        int c = 1<<2;
        int d = Integer.numberOfLeadingZeros(c);//29

        System.out.println(d);
    }
}
