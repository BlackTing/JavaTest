package com.nny.Demo.javaBasisLearn;

/**
 * 嵌套类
 * 匿名类
 */
public class HelloWorldAnonymousClasses {

    public String a = "a";

    interface HelloWorld {
        public void greet();
        public void greetSomeone(String someone);
    }

    public void sayHello() {

        String b = "b";

        /**
         * 局部类，实现接口
         */
        class EnglishGreeting implements HelloWorld {

            String name = "world";

            public void greet() {
                greetSomeone("world");
            }

            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Hello " + name);
            }
        }

        HelloWorld englishGreeting = new EnglishGreeting();

        /**
         * 匿名类，实现接口
         */
        HelloWorld frenchGreeting = new HelloWorld() {
            /**
             * 声明
             */
            String name = "tout le monde";

            /**
             * 语句，不允许
             */
            //System.out.println("");

            /**
             * 声明
             */
            public void greet() {
                greetSomeone("tout le monde");
            }

            String a = "c";

            String b = "c";

            public void greetSomeone(String someone) {
                name = someone;
                System.out.println("Salut " + name);

                /**
                 * 捕获封闭类的成员
                 */
                System.out.println(a);//c

                /**
                 * 捕获封闭块的局部变量
                 *
                 * 隐藏
                 *
                 * 封闭块被隐藏的局部变量有办法访问吗？
                 */
                System.out.println(b);//c
                System.out.println(this.a);//c
                System.out.println(HelloWorldAnonymousClasses.this.a);//a
            }

        };


        englishGreeting.greet();

        frenchGreeting.greet();

    }

    public static void main(String... args) {

        HelloWorldAnonymousClasses myApp = new HelloWorldAnonymousClasses();

        myApp.sayHello();
    }
}
