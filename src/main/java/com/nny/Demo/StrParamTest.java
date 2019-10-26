package com.nny.Demo;

public class StrParamTest {
    public static void main(String[] args){
//        String[] strings = new String[]{"1","2"};
        String[] strings ={"1","2"};
        StrParamTest.sayHi(strings);
        StrParamTest.sayHi("A");
        StrParamTest.sayHi("O","P");
        StrParamTest.sayHi();
        StrParamTest.sayHi(null);
    }
    private static void sayHi(String... strings){
        System.out.println("-----------"+strings);
        if(strings != null){
            for(String string : strings){
                System.out.println(string);
            }
        }else{
            System.out.println("===========null");
        }
    }
}
