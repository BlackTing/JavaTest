package com.nny.Demo.javaBasisLearn;

import com.nny.Demo.javaBasisLearn.otherPackage.Son2;
import org.springframework.lang.NonNull;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.Scanner;

public class D {

    /**
     * 类型注解
     */
    @NonNull String str = null;

    public static void main(String args[]) {
        m2();
    }

    public static void m14(){
    }

    /**
     * 循环语句
     */
    public static void m13(){

        for(int i=1;i<10;i++){
            if(i%2 ==0){
                continue;
            }
            System.out.println(i);
            if(i == 7){
                break;
            }
        }//1 3 5 7

        Season[] seasons = Season.values();

        for(Season s : seasons){
            System.out.println(s);
        }//依次输出枚举类型中的常量值

        System.out.println(seasons.toString());//[Lcom.nny.Demo.javaBasisLearn.Season;@2328c243
        System.out.println(seasons);//[Lcom.nny.Demo.javaBasisLearn.Season;@2328c243
    }

    /**
     * 开关语句
     */
    public static void m12(){
        int x=96,y=0;
        Season season = Season.summer;

        switch("aaa"){//字符串类型
            case "aaa":
                System.out.println("aaa");
        }

        switch(x+y){
            case 1:
                System.out.println(1);
            case 'a':
                System.out.println('a');
            case 3:
                System.out.println(97);
                break;
            default:
                System.out.println("啥都不是！");
        }

        switch(season){//枚举类型
            case spring:
                System.out.println(season+"春天真好！");
            case summer:
                System.out.println("夏天真好！");
            case autumn:
                System.out.println("秋天真好！");
            case winter: {
                System.out.println("这是冬天，多说一句！");
                System.out.println("冬天真好！");
            }
            //case summer://常量值不能重复
            default:
                System.out.println("啥都不是！");
        }

    }

    /**
     * 运算符
     */
    public static void m11(){
        int a = 6/2;//3

        int b = 6%4;//2

        int c = 5-2*3;//-1

        int d = c++;//-1

        d = ++b;//3

        double e = 5.0/2+10;//按double型计算，计算结果是double类型

        float f = 5.0f/2+10;//按float型计算

        long g = 12l+100+'a';//按long型计算

        int h = (byte)10+'a';//按int型计算

        int i = 5/2;//2 按int型计算

        byte j = (short)10+'a';

        boolean k = 1+3 > 2+5;//false

        boolean l = false == 1+3 > 2+5;//true

        k = l = false;

        /**
         * 位运算
         */
        double m = 5;
        double n = 6;
        short o = (short)((int)m&(int)n);//4

        short p = (short)5;
        short q = (short)7;
        short r = (short)(p&q);
        short s = (short)5&(short)7;

        byte t = 1;
        byte u = 2;
        byte v = (byte)(t&u);//0
        byte w = (byte)1&(byte)2;

        long x = 1;
        long y = 2;
        int z = (int)(x&y);

        //取反
        w = ~2;//-3
        w = (byte)~t;

        boolean a1 = true|false;//true
        //boolean a2 = ~a1; //~不能用于Boolean型数据

        //右移
        int e1 = 17>>1;

        //负数移位
        int f1 = -1 >>> 28;//15
        int g1 = -1 >> 28;//-1
        int h1 = 0xfffff800;
        int i1 = h1 >>4;//-128
        int j1 = h1 >>>4;

        /**
         * 打印数字的2进制表示
         */
        StringBuilder builder = new StringBuilder();
        for(int l1 = 31;l1>=0;l1--){
            int m1 = (-1>>>l1)&1;
            builder.append(m1+" ");
        }
        String n1 = builder.toString();

        boolean k1 = (j1 == 0x0fffff80);//true

        Father b1 = new Son();
        boolean c1 = b1 instanceof Father;
        boolean d1 = b1 instanceof Son;

        System.out.println(n1);
    }

    /**
     * 数组
     */
    public static void m10(){

        //对象数组
        Father[] fathers = new Father[5];
        System.out.println(fathers); //[Lcom.nny.Demo.javaBasisLearn.Father;@45ff54e6
        fathers[0] = new Father();
        System.out.println(fathers);//给数组中的元素赋值前后没有变化
        System.out.println(fathers[0]); //com.nny.Demo.javaBasisLearn.Father@bebdb06

        //声明二维数组
        int[] []a;
        int[][] b;

        //数组的初始化
        int[] c = {1,2,3,4,5};
        int[] d = new int[5];
        int[] q = new int[]{1,2,3};

        //数组输出
        String r = Arrays.toString(q);//[1, 2, 3]

        /**
         * 数组复制
         */
        System.arraycopy(c,1,d,0,4);
        String f = Arrays.toString(d);//[2,3,0,0,0]

        int[] e = Arrays.copyOfRange(c,1,3);
        String g = Arrays.toString(e);//[2,3]

        int[] h = Arrays.copyOfRange(c,1,8);
        String i = Arrays.toString(h);//[2,3,4,5,0,0,0] 长度是8-1=7

        int[] l = Arrays.copyOfRange(c,5,8);
        String m = Arrays.toString(l);//[0,0,0]

        int[] j = Arrays.copyOf(c,8);
        String k = Arrays.toString(j);//[1,2,3,4,5,0,0,0] 长度是8

        //排序
        int n[] = {2,5,7,4,3,1,5};
        Arrays.sort(n,1,6);
        String o = Arrays.toString(n);//[2,1,3,4,5,7,5]

        Arrays.sort(n);//[1,2,3,4,5,5,7]

        //查找
        int p = Arrays.binarySearch(n,3);//2

        System.out.println(f);

    }

    /**
     * 从命令行输入和输出
     */
    public static void m9(){
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        while(x != 0){
            System.out.println(x);
            x = scanner.nextInt();//阻塞在这里
        }

        System.out.printf("%d,%f\n",12,23.78);//包含格式控制部分的数据输出方法
        System.out.println(12+","+23.78);
        System.out.printf("%d\n",12,18); //12 错误示范
        System.out.printf("%f",12.0);//把int常量输出，报错
        System.out.printf("%2.2f%s\n",12345.0,"hhh");//12345.00hhh
        System.out.printf("%10.2f%s\n",12345.0,"hhh");//空空12345.00hhh
        System.out.printf("%3d%n  %s%n%n",3,"hhh");//空空3换行空空hhh

    }

    /**
     * 断言语句
     */
    public static void m8(int n){

        assert n > 0 : "必须大于0";

        System.out.println(n);
    }

    /**
     * 内部类
     */
    public static void m7(){
        RedCowFarm farm = new RedCowFarm("红牛农场");
        farm.showCowMess();
        farm.redCow.speak();

        /**
         * 静态嵌套类，可以在外部直接实例化。
         */
        //RedCowFarm.RedCow cow = new RedCowFarm.RedCow();

        /**
         * 通过封装类的实例，来实例化内部类。内部类是非静态嵌套类。
         */
        RedCowFarm.RedCow redCow = farm.new RedCow(3,3);

        redCow.speak();//我生活在红牛农场

        /**
         * 私有的内部类，在外部无法访问
         */
        DataStructure dataStructure = new DataStructure();

        //dataStructure.new EvenIterator();
    }

    /**
     * 接口与多态
     */
    public static void m6(){
        AdvertismentBoard board = new AdvertismentBoard();
        board.createBoard(new Philips());
        board.createBoard(new Lenovo());
    }

    /**
     * 抽象类与多态
     */
    public static void m5(){
        Simulator simulator = new Simulator();
        simulator.playSound(new Cat());
        simulator.playSound(new Dog());
    }

    /**
     * 上转型对象
     */
    public static void m4(){
        Father father = new Son();

        //子类没有继承的成员变量，上转型对象也不具有
        //father.e;

        //上转型对象具有被隐藏的变量，而不是子类声明的变量
        System.out.println(father.c);

        //上转型对象不具有子类声明的变量
        //System.out.println(father.f);

        //调用子类重写的方法，子类重写的方法中操作的是子类声明的成员变量
        System.out.println(father.getA());


        /**
         * 对于f4()方法，Son2不能继承，但是上转型对象可以操作
         */
        Father father1 = new Son2();
        father1.f4();

        Son2 son = new Son2();
        //son.f4();
    }

    /**
     * 测试final关键字修饰形参和局部变量
     */
    public static void m3(){
        final People people = new People(5);
        people.f4(5,people);
        System.out.println(people.getA());
    }

    /**
     * 继承
     * 方法重写
     */
    public static void m2(){
        Father father = new Son();
        /**
         * 子类重写了方法
         * 此处调用的是子类自己定义的方法
         */
        System.out.println(father.getA());

    }

    /**
     * 继承
     * 访问继承的受保护成员
     */
    public static void m1(){
        Son2 son2 = new Son2();

        /**
         * 访问子类继承的受保护变量
         * 因为D类和受保护的变量被声明的父类Father在同一个包中，所以可以访问
         */
        System.out.println(son2.c);
    }
}
