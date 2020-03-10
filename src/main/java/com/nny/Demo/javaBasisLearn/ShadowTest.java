package com.nny.Demo.javaBasisLearn;

/**
 * 内部类
 * 隐藏声明
 */
public class ShadowTest {

    public int x = 0;//该声明被隐藏

    /**
     * 内部类
     */
    class FirstLevel {

        public int x = 1;//与外部域有相同名称的类型声明

        void methodInFirstLevel(int x) {

            System.out.println("x = " + x);//23

            System.out.println("this.x = " + this.x);//1

            System.out.println("ShadowTest.this.x = " + ShadowTest.this.x);//0
        }
    }

    public static void main(String... args) {

        ShadowTest st = new ShadowTest();

        ShadowTest.FirstLevel fl = st.new FirstLevel();

        fl.methodInFirstLevel(23);
    }
}
