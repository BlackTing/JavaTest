package com.nny.Demo.javaBasisLearn;

/**
 * 基本类型
 */
public class DataTypeLearn {

    public static void main(String[] args){
        m4();
    }

    public static void m4(){
        /**
         * int型常量：1236000 077 0x3ABC
         * a1是十进制
         * a2是八进制，用0开头
         * a3是十六进制，用0x开头，3*16的3次方+10*16的2次方+11*16+12
         * 打印的都是十进制表示
         */
        int a1 = 1236000;
        int a2 = 077;
        int a3 = 0x3ABC;

        System.out.println(a1+" "+a2+" "+a3); //1236000 63 15036

        /**
         * 整数类型 int,内存分配给4个字节，也就是32位
         * int x = 7
         * 内存分配：00000000 00000000 00000000 00000111
         * 最高位（左边的第一位）是符号位。正数使用原码表示，最高位是0.负数用补码表示，最高位是1.
         * int x = -8
         * 内存分配：11111111 11111111 11111111 11111000
         *
         * 原码 补码 反码的转换
         *
         * int最大值 01111111 11111111 11111111 11111111，2的31次方-1
         * int最小值 11111111 11111111 11111111 11111111，
         */
        /**
         * 整数类型 byte,内存分配给1个字节
         * Java中不存在byte型常量的表示法，但可以把一定范围内的int型常量赋值给byte型变量。
         */
        /**
         * 整数类型 short
         * 常量：和byte型类似，Java中也不存在short型常量的表示法
         * 变量：使用关键字short来声明short型变量，例如：short x=12,y=1234;
         * 对于short型变量，内存分配2个字节。
         * 如果需要强调一个整数是short型数据，可以使用强制转换运算的结果来表示，例如：(short)-12,(short)28;
         */
        /**
         * 整数类型 long
         * 常量：long型常量用后缀L来表示，例如108L（十进制）、07123L（八进制）、0x3ABCL(十六进制）
         * 变量：使用关键字long来声明long型变量。例如：
         * long width=12L,height=2005L,length;
         * 内存分配给8个字节。
         */

        /**
         * 自动装箱与拆箱
         */
        float f1 = 12;
        Float f2 = new Float(f1);
        Float f3 = new Float(12);
        //Float f4 = 12; 错误，自动装箱时类型要保持一致
        Float f5 = 12f;

        /**
         *
         */
        int a = -5;
        int b = 0;
        int c = b-a;//5
        System.out.println(c);

    }

    /**
     * long类型
     */
    public static void m3(){
        long a = 5l;
        long b = 5L;
        //long c = 2e40l; //2e40表示的就是浮点类型，不能作为整型常量

        System.out.println(b);
    }



    /**
     * 浮点类型 float
     * 常量：453.5439f 21379.987F 231.0f 2e40f(小数表示法） 2e40f(2*10的40次方，指数表示法）。
     * 需要注意的是，常量后面必须要有后缀f或F。
     *
     * 变量：使用关键字float来声明float型变量。例如：
     * float x=22.76f,tom=1234.987f,weight=1e-12F;
     * float变量在存储float型数据时保留8位有效数字，实际精度取决于具体数值。
     * 内存分配4个字节
     */
    /**
     * 浮点类型 double
     * 常量：double height=23.345,width=34.56D,length=1e12;
     * 内存分配8个字节
     */
    public static void m2(){

        float f = 2e3F; //2000.0
        float a = 2e-3f;//0.002
        float b = 2000f;//2000.0
        float x = 12345.123456789f; //12345.123,float变量只保留8位有效数字


        char i = 97;
        short h = (short)i;
        i = (char)h;
        byte q = (byte)i;
        i = (char)q;

        long j = (long)f;
        f = j;

        int k = (int)f;//float精度高，int精度低
        f = k;


        int c = 128;
        long d = 77777;
        double g = 3.14e-300;
        double result = f*g;

        g = 1234.123456789;
        c=(int)d;
        f=(float)g; //导致精度丢失

        int l = (int)5;//常量5是int类型
        int m = (int)5.0;//常量5.0是double类型

        System.out.println("result="+l);
    }

    /**
     * char类型
     */
    public static void m1(){

        char a = '\n';

        char d = 10;

        int c = '\n';//一个字符的数字表示

        short e = 'a';//97

        byte f = 'a';//97

        String b = "第一行\n第二行";//转义字符，起到换行效果

        System.out.println(f);

    }


}
