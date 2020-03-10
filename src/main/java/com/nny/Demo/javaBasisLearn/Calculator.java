package com.nny.Demo.javaBasisLearn;

/**
 * 嵌套类
 *
 * 拉姆达表达式
 */
public class Calculator {

    /**
     *成员接口
     */
    interface IntegerMath{
        int operation(int a,int b);
    }

    public int operateBinary(int a,int b,IntegerMath op){
        return op.operation(a,b);
    }

    public static void main(String... args){

        Calculator myApp = new Calculator();

        /**
         * 拉姆达表达式，在局部变量声明中使用
         */
        IntegerMath addition = (a,b) -> a+b;

        IntegerMath subtraction = (a,b) -> a-b;

        int a = myApp.operateBinary(40,2,addition);

        int b = myApp.operateBinary(40,2,subtraction);

    }

}
