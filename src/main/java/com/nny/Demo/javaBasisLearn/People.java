package com.nny.Demo.javaBasisLearn;

/**
 * 类与对象
 */

public class People {

    /**
     * 类变量
     * 私有的类变量，不能被在其他类中创建的对象操作
     */
    private static int i;

    private int a;

    /**
     * final和static的顺序不固定
     * 必须指定初始值
     */
    private final static int J = 5;

    //静态代码块
    static{
        i = 1;
        System.out.println("i="+i);
    }


    //构造代码块，会被插入到构造方法的前面
    //如果有一个构造方法调用了其他构造方法，就不会插入到这个构造方法前面，避免重复插入
    {
        a = 5;
    }

    //无参构造方法
    public People(){
        System.out.println("a="+a);
    }

    //有参构造方法
    public People(int a){

        this(); //必须在第一句
        this.a += a;
        System.out.println("有参构造中的a="+this.a);
    }


    /**
     * 实例方法
     *
     * this关键字
     */
    protected void f1(){
        int a = this.a + this.i;
        System.out.println(a);
        this.f2();
    }

    /**
     * 类方法
     * 私有的类方法，不能被在其他类中创建的对象操作
     */
    private static void f2(){
        int a = People.i;
        System.out.println(a);
    }

    /**
     * 方法重载
     * 方法名称相同，参数必须不同，不管方法类型是否相同
     */

    private float f3(int a){
        return 0;
    }


//    private double f3(int a){
//        return 0;
//    }

    private float f3(int a,int b){
        return 0;
    }

    public void f4(final int a,People p){
        //不能修改变量a的值
        //a = 5;

        final People people = new People(a);

        //不能修改
        //people = new People();

        //可以修改对象的属性
        people.setA(20);

        /**
         * 并不会改变方法外的变量
         * 形参是引用变量，传递的是引用的值
         * 形参是基本类型的变量，值传递，传递的是拷贝的值
         */
        p = people;
    }

    /**
     * @param a
     * @param x 可变参数
     */
    public void f5(double a,int ...x){
        System.out.println(x.length);
        for(int i : x){
            System.out.println(i);
        }

    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
