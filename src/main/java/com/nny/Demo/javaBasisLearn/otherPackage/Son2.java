package com.nny.Demo.javaBasisLearn.otherPackage;

import com.nny.Demo.javaBasisLearn.Father;

/**
 * 继承
 * 与父类不同包的子类
 */
public class Son2 extends Father {


    void f3(){

        //不能继承父类的友好方法
        //f1();

        //不能继承父类的友好变量
        //d = 5;

        //继承父类的protected成员变量
        c = 5;
    }
}
