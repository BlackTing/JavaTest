package com.nny.Demo.javaBasisLearn;


import java.util.function.Consumer;

/**
 * 嵌套类
 * 拉姆达表达式
 * 访问封闭域的局部变量
 */
public class LambdaScopeTest {

    public int x = 0;

    /**
     * 内部类，非静态嵌套类
     */
    class FirstLevel {

        public int x = 1;

        void methodInFirstLevel(int x) {

            /**
             * 这个赋值语句会使方法参数变得not有效final
             */
            // x = 99;

            /**
             * 拉姆达表达式，在变量声明中使用
             * 形式参数的名称不能是x，因为不能隐藏，所以犯了变量重名的错误。
             */
            Consumer<Integer> myConsumer = (y) ->
            {

                /**
                 * 访问封闭域的参数，必须是final或有final
                 */
                System.out.println("x = " + x); // 23

                /**
                 * 访问自己的参数
                 */
                System.out.println("y = " + y);//23

                /**
                 * 访问内部类的成员变量，封闭类
                 */
                System.out.println("this.x = " + this.x);//1

                /**
                 * 访问多级封闭类的成员变量
                 */
                System.out.println("LambdaScopeTest.this.x = " + LambdaScopeTest.this.x);//0
            };

            myConsumer.accept(x);

        }
    }

    public static void main(String... args) {

        LambdaScopeTest st = new LambdaScopeTest();

        /**
         * 实例化内部类
         */
        LambdaScopeTest.FirstLevel fl = st.new FirstLevel();

        fl.methodInFirstLevel(23);
    }
}
