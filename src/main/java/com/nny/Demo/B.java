package com.nny.Demo;


import com.nny.Demo.project.AnalysisOriginalData;
import com.nny.Demo.DataStructureLearn.LinkStackLearn;
import com.nny.Demo.DataStructureLearn.QueueLearn;
import com.nny.Demo.SingletonLearn.Singleton2;
import com.nny.Demo.ThreadTest.VolatileFeaturesExample;
import com.nny.Demo.domain.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.WritableResource;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class B {

    public static VolatileFeaturesExample volatileFeaturesExample = new VolatileFeaturesExample();
    public static int name =0;
    public static List<Thread> threadList = new ArrayList<>();

    public static final List list = new ArrayList();

    public static int next = 1;//下个小岛在b中的空间

    public static void main0(String args[]) {

    }

    /**
     * 子线程准备就绪：start()
     * 子线程等待所有子线程准备就绪：latch.await()
     *
     * 主线程唤醒200次，所有子线程同时被唤醒： latch.countDown()
     *
     * 所有子线程同时执行：Singleton1 instance = Singleton1.getInstance()
     */
    public static void singletonTest() {
        int count = 200;
        final CountDownLatch latch = new CountDownLatch(count);

        for(int i = 0; i < count;i++){

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        //等待
                        latch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Singleton2 instance = Singleton2.getInstance();
                    System.out.println(System.currentTimeMillis() + ":" + instance);
                }
            }).start();

            latch.countDown();
        }
    }

    /**
     * 逻辑运算符
     */
    public static void logicalOperator(){
        logicalOperator0();
    }
    public static void logicalOperator0(){
        /**
         * &&和&的区别
         * &&，当条件1=false,则不再执行条件2
         * &，当条件1=false,也执行条件2
         */
        int i = 3;
        int j = 3;
        int x = 3;
        boolean a = i==2 && j++ == 3;
        boolean b = i==2 & x++ == 3;
        System.out.println(j+" "+x);//j=3 x=4
    }

    public static void logicalOperator1(){
        /**
         * ||与|的区别
         * 和 '&&与&的区别'很类似
         */
        int i = 3;
        int j = 3;
        int x = 3;
        boolean a = i==3 || j++ == 3;
        boolean b = i==3 | x++ == 3;
        System.out.println(j+" "+x);//j=3 x=4
    }

    /**
     * 二进制运算
     */
    public static void binaryCount(){
        /**
         * 交运算
         *   0000 0111
         * & 0000 0011
         * = 0000 0011
         */
        byte a = 7;
        byte b = 3;
        int c = a&b; //3
    }

    //小岛

    /**
     * 思路：
     * 逐行遍历
     * 依据以下规则对原矩阵进行转换，只需维护当前行与当前行的上一行的转换结果
     * 规则：
     * 如果是1，表示墙，转换为-1；
     * 如果是0，分两种情况，第一种：通过判左侧、上侧、右侧（借助递归进行判断），发现为有效0，转换为小岛的序号（依据小岛的序号，可以在存储小岛面积的数组中找到该小岛的大小），
     *                  第二种，通过判左侧、上侧、右侧，发现为无效0，表示洞，转换为-2。
     * 以上即为规则。
     * 总的来说，依据上一行的转换结果，来生成当前行的转换结果，生成当前行的转换结果的同时，更新上一行的转换结果。在此过程中，需要通过以下操作维护小岛的面积：
     * 1、记录可能的小岛；
     * 2、增大小岛面积；
     * 3、合并小岛；
     * 4、删除假的小岛。
     * 最后，在记录着所有小岛面积的数组中，得到第二岛的面积并输出。
     *
     * @param r
     * @return
     */

    /**
     *时间复杂度
     * T(n)=O(n)
     *
     * 元素个数为n
     * 逐行遍历数组，每行遍历中，左侧和上侧比较时间复杂度是1，右侧比较需要递归，但递归的同时也是在继续进行列遍历，所以对于一个元素而言，总的还是比较3次，
     * 不过在删除小岛或小岛合并时，有一些赋值操作，赋值操作的时间复杂度是1.求第二小岛面积时，遍历b数组的next元素，一个元素被比较两次，每次比较的时间复杂度是1，
     * 所以，时间复杂度是线性级别的。
     *
     */

    /**
     *
     *空间复杂度
     * O（n)
     *
     * 需要：
     * 1、两个与r[]数组同宽度的数组
     * 2、递归调用的栈空间，深度不超过r[]数组的宽度
     * 3、存储小岛大小的b数组，小于元素个数的一半
     * 总的来说，是线性级别的。
     *
     */
    public static int count2(int[][] r){

        int h = r.length; //行数
        int l = r[0].length; //列数

        if(h<3 || l<3)
            return 0;

        int[] h1 = new int[l];//用于存储上一行的转换结果
        int[] h2 = new int[l];//本行


        int a = (h*l-2*l-2*(h-1))/2+2;//对可能的小岛个数做了一个优化
        int[] b = new int[a];//b中每个元素代表一个小岛的大小

        //初始化h1
        for(int y=0; y<l; y++){
            if(r[0][y] == 1)
                h1[y] = -1;//墙
            else
                h1[y] = -2;//洞
        }

        for(int x=1;x<h;x++){//第二行开始，逐行遍历

            //更新h2
            for(int y=0;y<l;y++) {

                if (r[x][y] == 1) {
                    h2[y] = -1;
                }
                else {
                    if(x == h-1){//最后一行单独处理

                        //更新h1部分
                        if (h1[y] > 0)
                            b[h1[y]] = 0;//清空小岛大小

                    }
                    else {
                        if (y == 0 || y == l - 1) //边0
                            h2[y] = -2;
                        else {
                            if (h1[y] == -2) //上面是洞
                                h2[y] = -2;
                            else if (h2[y-1] == -2) { //左面是洞

                                h2[y] = -2;

                                //更新h1部分
                                if (h1[y] > 0) {

                                    int e = h1[y];
                                    b[e] = 0;//清空小岛大小，即删除小岛

                                    int d = y;
                                    while (h1[d] > 0) {
                                        h1[d] = -2;
                                        d++;
                                    }
                                }
                            }
                            else //上面左面都不是洞，那就得判断右边是不是洞
                                y = count3(r, x, y, h1, h2, b);//返回y，意思是y以前的列都转换好了，继续转换y之后的列
                        }
                    }
                }
            }

            int[] temp = h1;
            h1 = h2;
            h2 = temp;

        }

        //下面开始求次大的小岛的面积

        int m1 = b[1];//最大
        int m2 = b[2];//次大

        for(int i=2; i<next; i++){ //next表示：出现的可能的小岛的总个数+1
            if(b[i] > m1){
                m2 = m1;
                m1 = b[i];
            }
            else if(b[i] > m2)
                m2 = b[i];
        }

        if(m2 != 0)
            return m2;
        else
            return m1;
    }

    public static int count3(int[][] r,int x,int y,int[] l1,int[] l2,int[] b){

        int a = y;//表示已经转换好的最后一个列号

        if(y == r[0].length-1)//y是最后一列
            l2[y] = -2;
        else if (r[x][y+1] == 0 && l1[y+1] == -2) {//后一列的上一行是洞
            l2[y+1] = -2;
            l2[y] = -2;
            a = y+1;
        }
        else if (r[x][y+1] == 0 && l1[y+1] != -2) {

            a = count3(r, x,y+1,l1,l2,b);//进行递归
            int e = 0;

            if(l2[y+1] == -2){

                l2[y] = -2;

                if(l1[y] > 0){
                    l1[y] = -2;
                    b[l1[y]] = 0;//清空小岛大小
                }

            }
            else if(l2[y+1] == -1){

                if(l1[y] > 0) {
                    l2[y] = l1[y];
                    b[l1[y]]++; //增加小岛面积
                }
                else{
                    l2[y] = next;//记录新的小岛
                    b[next]++;
                    next++;
                }
            }
            else{

                l2[y] = l2[y+1];

                if(l1[y] < 0){
                    l2[y] = l2[y+1];
                    e = l2[y];
                    b[e]++;
                }
                else {
                    if(l1[y] == l2[y+1])
                        b[l2[y+1]]++;
                    else{ //合并小岛
                        e = l2[y];
                        b[e] = b[e] + b[l1[y]] + 1;
                        b[l1[y]] = 0;
                        int d = y;
                        int c = l1[y];
                        while(l1[d] == c){
                            l1[d] = l2[y];
                            d--;
                        }

                    }
                }
            }
        }
        else {// if (r[x][y+1] == 1)
            l2[y+1] = -1;
            if(l1[y] < 0){
                l2[y] = next;
                b[next]++;
                next++;
            }
            else{
                l2[y] = l1[y];
                b[l1[y]]++;
            }
            a = y+1;
        }
        return a;
    }



    //炒股票

    /**
     * 由于最多可以交易两次，第一次的卖出看做一个分隔点，来分隔两次交易，从而出现两个区间
     * 对于每个分隔点，求两个区间的最大利润，求和就是此分隔点时的最大利润。
     * 遍历分隔点，得到每个分隔点的最大利润，比较得出利润最高值。
     * @param r
     * @param k
     * @return
     */
    /**
     * 没有理解K的含义，所以按照k=1计算的。
     */
    public static int count1(int[] r,int k){
        int[][] sum = new int[r.length][2];
        int i=0;//区间1的最小值下标
        int j=1;//区间1的游标
        int x=r.length-1;//区间2的最大值下标
        int y=x-1;//区间2的游标
        int max=0;
        int result = 0;//最大利润

        while(j < r.length){
            if(r[j] > r[i]){ //刷新最大利润
                max = r[j]-r[i] > max ? r[j]-r[i] : max;
            }
            else{
                i = j;
            }
            sum[j][0] = max;
            j++;
        }

        while(y > 1){
            if(r[y] < r[x]){//刷新最大利润
                max = r[x]-r[y] > max ? r[x]-r[y] : max;
            }
            else{
                x = y;
            }
            sum[y-1][1] = max;
            y--;
        }

        for(int o=1;o<r.length;o++){
            result = sum[o][0]+sum[o][1] > result ? sum[o][0]+sum[o][1] : result;
        }
        return result;
    }

    /**
     * 遍历一次数组，找出每个分隔点时，区间1的最大利润。
     *
     * 再遍历一次数组，找出每个分隔点时，区间2的最大利润。
     *
     * 时间复杂度是O(n).
     *
     * 利用一个二维数组存储每个分隔点时，区间1和区间2的最大利润。
     *
     * 空间复杂度是O(n).
     */






    /**
     * 紧凑、自包含（不是很理解，就是说包含用户信息，减少数据库查询了）
     * 实际上就是一个字符串
     * header.payload.signature
     * {
     *     "typ":"JWT" 标识这是一个JWT字符串
     *     "alg":"HS256"  说明这个JWT签发时所用的签名和摘要算法
     * }
     * 上面部分经过Base64Url编码就是header部分
     * 得到签名的过程 1、把header和payload对应的json结构进行base64Url编码之后得到的两个串用英文句号拼接起来
     *  2、试用指定的签名算法生成签名（HS256需要使用密钥）
     *  验证过程
     *  第一步，接收方对JWT的完整性进行验证（签名验证）
     *  1、把header做base64url解码，得到签名算法
     *  2、使用算法对header和payload做一次签名，比较是否与JWT本身第三个串相同
     *  （重点是，做签名时用到了密钥，密钥只有双方知道）
     *  下一步，payload的claim验证
     *
     */
    public static void learnJWT0(){

    }

    public void inputStreamTest1() throws Exception{
        File file = new File("E:\\txt0.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        int len = fileInputStream.available();
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        byte[] bytes = new byte[len];
        int readBytes = 0;
        while (readBytes < len) {
            int oncebytes = fileInputStream.read(bytes, readBytes, len - readBytes);

            if (oncebytes == -1)
                break;
            byteArrayOut.write(bytes, readBytes, oncebytes);
            readBytes += oncebytes;
        }

        String result = byteArrayOut.toString().replaceAll("\\s*", "");
        System.out.println(result.length());
        AnalysisOriginalData analysisOriginalData = new AnalysisOriginalData();
        analysisOriginalData.behaviorStartAndEndSecond(result);
        Map<Integer,List<Integer>> map = analysisOriginalData.getMap();
        List<Integer> list = map.get(2);
        System.out.println(list);
    }

    public static void analysis(AnalysisOriginalData analysisOriginalData){
//        String initialData = "0010 00 00 0010 001000000010 001000000010 001000000010 001000000010";//"0010 0000 0010 000000000010 001000000010 001000000010 001000000010";

        StringBuffer a = new StringBuffer();
//        for(int i = 0; i < 60*4 ; i++){
//            a.append("001000000010");
//        }
        a.append("001000100000");
        a.append("001000010000");
        a.append("001000010000");
        String initialData = a.toString(); //老师行为是teach 2,学生行为是听讲 10
//        System.out.println(initialData);

        analysisOriginalData.behaviorStartAndEndSecond(initialData);



//        double[] focusDegreeInMin = analysisOriginalData.focusDegreeInMin;
//        analysisOriginalData.print(focusDegreeInMin);

    }

    public static void countInt(){
        int minutes = (int)Math.ceil(6/60d);
        System.out.println(minutes);
    }

    public static void binaryOperation2(){
        byte b = Byte.parseByte("00110101",2);
        byte a = Byte.parseByte("00100101",2);
        byte r = (byte)(b^a);
        if(r != 0) {
            int index = 1;
            boolean is1 = ( ((r>>(index-1))&1) == 1 );
            if(is1){
                //结束点
            }
            else{
                //开始点
            }
        }
    }

    public static void binaryOperation1(){

        int timeHour = 10; //视频时长s

        StringBuffer a = new StringBuffer();
        for(int i = 0; i < 10 ; i++){
            a.append("111111111110");
        }
        String b = a.toString();

        int length = b.length();

        //把String转化成多个byte,byte用什么集合类存储？用数组

        //byte个数*2
        int byteSum = length/12;
        System.out.println("byteSum:"+byteSum);

        char[] chars = b.toCharArray();
        byte[] bytes = new byte[byteSum];

//        int i = 0; //老师行为
        int i =  6; //学生行为
        int num = 0;

        //写入byte
        while(i < length){
//            String str = b.substring(i,i+6); //加快速度 0-5 6-11 12-17
            String str = b.substring(i,i+6); // 缩小空间 0-5 12-17

            StringBuffer buffer = new StringBuffer("00");
            buffer.append(str);

            String string = buffer.toString();

            byte b1 = Byte.parseByte(string,2);
            bytes[num] = b1;

            i = i+12;
            num++;
        }

        for(int x = 0 ; x < byteSum ; x++){
            System.out.println(bytes[x]);
        }


//        byte b1 = Byte.parseByte("00111110",2);
//        char c1 = '1';
//        System.out.println(b1);
//        byte standard1 = Byte.parseByte("00100000",2);
//        byte standard2 = Byte.parseByte("00010000",2);
//
//        byte action1s = Byte.parseByte("00111000",2);
//        byte action2s = Byte.parseByte("00110011",2);
//
//        byte result = (byte)(action1s & 0xff);
//        System.out.println(result);
    }

    public static void binaryOperation0(byte b,int index){
//        byte b = Byte.parseByte("00110101",2);
//        int index = 3;
        int r = (b>>(index-1))&1;
        System.out.println(r);
    }

    public void test(){
        //开启50个线程，进行+1操作
        for(int i=0;i<50;i++){
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    volatileFeaturesExample.getAndIncrement();
                }
            });
            threadList.add(thread);
        }

        for(Thread thread : threadList){
            thread.setName("thread:"+(name++));
        }

        for(Thread thread : threadList){
            thread.start();
        }

        /**
         * 结果是33
         */
        System.out.println(volatileFeaturesExample.get());
    }

    public static void subStr(){
        String a = "12345,678";
        int index = a.indexOf(",");
        System.out.println(a.substring(0,5));
    }

    /**
     * 精度
     */
    public static void leanrnFloat1(){
    }

    public static void learnFloat0(){
        float a = 4f;
        float b = 3f;
        float c = a/b;
        System.out.println(c);//1.3333334

        float d = 68f;
        float e = 1005f;
        float f = d/e;
        System.out.println(f); //0.067661695 除不尽

        float g = 6.6f;
        float h = 1.3f;
        float i = g+h;
        System.out.println(i); //7.8999996 失真

        float j = 6f;
        float k = 2f;
        float l = j+k;
        System.out.println(l); // 8.0

        double m = 6.6;
        double n = 1.3;
        double o = m+n;
        System.out.println(o); //7.8999999999999995 失真

        float p = 7.9f;
        System.out.println(p); //7.9

        float q = 0.2f/0.1f;
        System.out.println(q); //2.0
    }

    public static void precision2(){
        //1
        System.out.println(4f); //4.0

        //2
        System.out.println((float)2.1*(float)2.1); //4.4099994
        System.out.println(2.1d*2.1d);

        //3
        System.out.println(2.22d*4); //8.88
        System.out.println(3.333d*2);//6.666

        //4
        System.out.println(3.333d*2.0d);//6.665999889373779

        float a = 2.1f;
        float b = a * 2.1f;
        System.out.println(b);
        //float*double
        System.out.println(a*2.0d);//6.665999889373779

        String ss = String.format("%8.3f", 11.12);   //1.13
        System.out.println(ss);

    }

    public static void precision1(){
        BigDecimal num1 = new BigDecimal(0.005);
        BigDecimal num2 = new BigDecimal(1000000);
        BigDecimal num3 = new BigDecimal(-1000000);
        //尽量用字符串的形式初始化
        BigDecimal num12 = new BigDecimal("0.005");
        BigDecimal num22 = new BigDecimal("1000000");
        BigDecimal num32 = new BigDecimal("-1000000");
        //加法
        System.out.println();
        BigDecimal result1 = num1.add(num2);

        BigDecimal result12 = num12.add(num22);

        //减法
        BigDecimal result2 = num1.subtract(num2);
        BigDecimal result22 = num12.subtract(num22);

        //乘法
        BigDecimal result3 = num1.multiply(num2);
        BigDecimal result32 = num12.multiply(num22);

        //绝对值
        BigDecimal result4 = num3.abs();
        BigDecimal result42 = num32.abs();

        //除法
        BigDecimal result5 = num2.divide(num1,20,BigDecimal.ROUND_HALF_UP);
        BigDecimal result52 = num22.divide(num12,20,BigDecimal.ROUND_HALF_UP);
        System.out.println(result52);
    }

    /**
     * 折半查找 非递归实现
     * @param a
     * @param low
     * @param high
     * @param key
     * @return
     */
    public static Integer binarySearch(int[] a,int low,int high,int key){
        while(low <= high){
            int mid = (low+high)/2;
            if(key == a[mid])
                return mid;
            else if(key < a[mid])
                high = mid-1;
            else if(key > a[mid])
                low = mid+1;
        }
        return null;
    }

    /**
     * 折半查找 递归实现
     * @param a
     * @param low
     * @param high
     * @param key
     * @return
     */
    public static Integer binarySearchOfRecursion(int[] a,int low,int high,int key){
        if(low > high)
            return null;
        int mid = (low+high)/2;
        if(key == a[mid])
            return mid;
        else if(key < a[mid])
            return binarySearchOfRecursion(a,low,(mid-1),key);
        else
            return binarySearchOfRecursion(a,(mid+1),high,key);
    }

    public static void searchOnLinearStructure(){
        int[] a = {0,1,2,3,4,5,6,7,8,9};
        Integer result = binarySearch(a,0,9,2);
        System.out.println("result:"+result);
    }

    public static void createRandom5(){
        int[] num = {8,9,10,11,12,13,14,15};
        Random random = new Random();
        int userNum = num[random.nextInt(num.length)];
        System.out.println(userNum);
    }

    public static void createRandom4(){
        for (int i = 0; i <= 10; i++)
        {
            String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
            Random rand = new Random();
            StringBuffer flag = new StringBuffer();
            for (int j = 0; j < 6; j++)
            {
                flag.append(sources.charAt(rand.nextInt(10)) ); //数组长度
            }
            System.out.println(flag.toString());
        }
    }

    public static void createRandom3(){
        for (int i = 0; i <= 10; i++)
        {
            int flag = new Random().nextInt(999999); //生成一个大于等于0，小于999999的正整数
            if (flag < 100000)
            {
                flag += 100000;
            }
            System.out.println(flag);
        }
    }

    public static void createRandom2(){
        Random random = new Random();
        String result="";
        for (int i=0;i<6;i++)
        {
            result+=random.nextInt(10);
        }
        System.out.println(result);
    }

    /**
     * 生成10个随机数，每个随机数是6位
     */
    public static void createRandom1(){
        for(int j = 0; j< 10; j++){
            System.out.println((int)((Math.random()*9+1)*100000));
        }
    }


    public static void createRandom0(){
        for (int i = 0; i <= 10; i++) {

            //重点
            int intFlag = (int)(Math.random() * 1000000);

            String flag = String.valueOf(intFlag);
            if (flag.length() == 6)
            {
                System.out.println(intFlag);
            }
            else
            {
                intFlag = intFlag + 100000;
                System.out.println(intFlag);
            }
        }
    }

    /**
     * 生成10个随机数，每个随机数是6位
     */
    public static void createRandom(){
        createRandom1();
    }

    private static void ee(){
        //        String str = "abcd123";
//        byte[] bytes = str.getBytes();

        User user = new User("li",10);

        byte[] userbytes = serialize(user);

        System.out.println("userbytes.length:"+userbytes.length);

        for(byte one : userbytes){
            System.out.print(one+" ");
        }
    }

    private static byte[] serialize(Object object) {
        if (object == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] bytes = null;
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            bytes = baos.toByteArray();
        }
        /*catch (Exception e) {
            throw new IllegalArgumentException("Non-serializable object", e);
        }*/ catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) oos.close();
                if (baos != null) baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }



    /**
     * 反序列化
     *
     * @param bytes
     * @return
     */
    public Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try {
            if (bytes != null) {
                bais = new ByteArrayInputStream(bytes);
                ois = new ObjectInputStream(bais);
                bais.close();
                ois.close();
                return ois.readObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int power2(int x,int n){
        int time = 0;
        if(n == 0)
            return 0;
        else{
            int y = 1;
            while(n > 1){
                time++;
                y = y*x*x;
                n = n-2;
            }
            if(n == 1){
                y = y*x;
            }
            System.out.println("次数："+time);
            return y;
        }
    }

    /**
     * 求x的n次幂
     * 算法复杂度是n/2
     * @param x
     * @param n
     * @return
     */
    public static int power1(int x,int n){
        int time = 0;
        if(n == 0)
            return 0;
        else{
            int y = 1;
            while(n > 1){
                time++;
                y = y*x*x;
                n = n-2;
            }
            if(n == 1){
                y = y*x;
            }
            System.out.println("次数："+time);
            return y;
        }
    }

    public static void testBoolean(){
        int action = 1;
        boolean start = (action == 0 ? true:false);
        System.out.println(start);
    }

    public static void ifAndElse(){
        if (false)
            System.out.println(1);
        else
            System.out.println(0);
    }

    public static void testNull(){
        String a = "aaa";
        boolean b = (null != a);
        System.out.println(b);
    }

    public static void stringTolong(){
        String dtime = "934811";
        long time = Long.valueOf(dtime);
        System.out.println(time);
    }

    public static void time(){
        System.out.println(10000/10/10);
    }

    /**
     * array length
     */
    public static void array1(){
        String[] a = new String[5];
        int length = a.length; //a.length=5,不表示数组中有5个元素
        System.out.println(length);
    }

    /**
     * 2019.8.28
     * java stream 遍历修改集合map中的value值
     */
    public static void DealMapByStream(){
        Map<String,String> map = new HashMap<>();
        map.put("1","str");
        Map<String,String> map1 = map
                .entrySet()
                .stream()
                .collect(
                        Collectors.toMap(entry -> entry.getKey(),entry -> "new:"+entry.getValue())
                );
        map1
                .entrySet()
                .stream()
                .forEach(
                        entry ->System.out.println(entry.getKey()+" "+entry.getValue())
                );
    }

    /**
     * 6.3.2019
     * java stream流编程
     */
    public static void stream1(){
        List<Person> personList = new ArrayList<>();
        personList.stream()
                .filter(item -> item.getAge() == 70)
                .limit(10)
                .filter(item -> item.getName().startsWith("王"))
                .map(item -> item.getName())
                .collect(Collectors.toList());
    }

    class Person{
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }


//    public static void phoneCheck(){
//        String all = "ff";
//        String real = "^1\\d{10}$";
//        boolean result = all.matches(real);
//        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
//        Matcher m = p.matcher(all);
//        boolean result1 = m.find();
//        System.out.println(result1);
//    }
//
//    public static void fileTest(){
//        File file = new File("F://img/549.jpg"); //已存在的文件
//        File file1 = new File("F://img"); //已存在的目录
//        File file2 = new File("F://jhlkjh/kjh/1.txt"); //未存在的目录
//        String name = file.getName(); //文件名称；549.jpg
//        long length = file.length(); //表示文件大小；单位字节；27175
//        File one = file.getAbsoluteFile();//文件的绝对路径
//        String two = file.getParent(); //文件的父路径;F:\img
//        File three = file.getParentFile();
//
//        boolean four = file.isFile(); //是否是普通文件；true
//        boolean five = three.isDirectory(); //是否是目录；true
//        try{
//            boolean six = file.createNewFile(); //当且仅当具有该名称的文件尚不存在时，原子地创建一个由该抽象路径名命名的新的空文件;false
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//        File[] seven = file1.listFiles(); //返回一个抽象路径名数组，表示由该抽象路径名表示的目录中的文件;549.jpg
//        File[] eight = file.listFiles(); //抛空指针
//        String[] nine = file1.list(); //返回一个字符串数组，命名由此抽象路径名表示的目录中的文件和目录;549.jpg
//        for(int i=0;i<nine.length;i++){
//        }
//        boolean ten = file.mkdirs(); //false
//        boolean eleven = file1.mkdirs(); //false
//        boolean twelve = file2.mkdirs(); //创建由此抽象路径名命名的目录，包括任何必需但不存在的父目录。可创建多层文件包;true
//        boolean thirteen = file2.mkdir();
//        System.out.println(file1.getName());
//    }
//
    public static void reduceFunctiontest3() {
        List<Integer> numList = Arrays.asList(1, 2, 3, 4, 5, 6);
        ArrayList<String> result = numList.stream().reduce(new ArrayList<String>(), (a, b) -> {
            a.add("element-" + Integer.toString(b));
            return a;
        }, (a, b) -> null);
        System.out.println(result);
    }

    public static void reduceFunctiontest2() {
        List<Integer> numList = Arrays.asList(Integer.MAX_VALUE, Integer.MAX_VALUE);
        long result = numList
                .stream()
                .reduce(
                        0L,
                        (a, b) -> a + b,
                        (a, b) -> 0L
                );
        System.out.println(result);
    }

    public static void reduceFunctiontest1(){
        List<Integer> numList = Arrays.asList(1,2,3,4,5);
        int result = numList.stream().reduce(5,(a,b) ->  a + b );
        System.out.println(result);
    }

    public static void reduceFunctiontest0(){
        List<Integer> numList = Arrays.asList(1,2,3,4,5);
        int result = numList
                .stream()
                .reduce(
                        (a,b) -> {
                            System.out.println("a=" + a + ",b=" + b);
                            return a + b;
                        })
                .get();
    }

    public static void demoReduce(){

        //取出out
        PrintStream out=System.out;
        //实现Predicate接口并且指定行为：传入的参数中是否包含字符串"a"
        //后续可以调用该接口的test方法做筛选判断
        Predicate<String> predicate= x->x.contains("a");

        out.println("单个参数的reduce方法->接收BinaryOperator函数返回一个Optional<T>类型\n" +
                "实际上，该方法此时的表现为将该序列(Stream流内的类型)\n" +
                "的第一个元素与该流后续所有元素做2合计算'比如：(a[0]+a[1])+a[3]'\n" +
                "执行完函数后获得一个Optional<T>类型（可选的，任意的）后调用get()方法进行取值");
        Stream<Integer> stream2=Stream.of(1,2,3);
        //输出结果为6
        out.println(stream2
                .reduce((x,y)->x*y)
                .get());

        out.println("两个参数1reduce方法实际上只是多了一个初始化的值‘T’\n" +
                "，第二个参数与单参方法一致,该重载方法返回类型为‘T’，指通过BinaryOperator进行计算后\n" +
                "返回一个类型与初始化参数的类型一致的值:::重点->双参函数与单参的计算流程不同：\n" +
                "该函数是将初始化参数与流内所有的元素逐个进行二和运算，\n" +
                "下面实例为将所有包含'a'的元素拼接在初始化参数的后面");
        Stream<String> stream3=Stream.of("as1","a12","nmm1","cc2","ac3","ab4");
        //输出结果为[valueMain]：as1a12ac3ab4
        out.println(
                stream3.reduce(
                "[valueMain]：",
                (x,y)->{
                    if(predicate.test(y))
                        return x.concat(y);
                    else
                        return x;
                })
        );

        out.println("初始化参数为一个集合，将流内所有符合条件的元素筛选出来加入到该初始参数中");
        Stream<String> stream=Stream.of("as","ai","nmm","cc","ac","ab");
        //输出结果为：as ai ac ab
        stream
                .reduce(
                        new ArrayList<String>(),
                        (x,y)->{
                            if (predicate.test(y))
                                x.add(y);
                            return x;
                            },
                        (x,y)->x)
                .forEach(System.out::println);

        out.println("并行(parallel)的影响下，第三个参数才会生效\n" +
                "该状态下理解为第二个函数根据流内数据的个数分为多线程去处理每个值与首参U的2合计算\n" +
                "首参U分别与流内每个值计算完毕后，由第三个参数对这些值做出整合(该参数要求实现\n" +
                "BinaryOperator接口并给出一个单值计算的行为),即，\n" +
                "该接口内三个参数为同一类型，并作出操作(T x,T y)->{return ?(T)}");
        Stream<Integer> stream1=Stream.of(1,2,3);
        //输出结果为：1716   (10+1)*(10+2)*(10+3)
        out.println(stream1
                .parallel()
                .reduce(10,
                        (x,y)->x+y,
                        (x,y)->x*y
                )
        );

        out.println("非并行情况下的第三个参数BinaryOperator " +
                "combiner(合成器)\n会对第二个参数BiFunction accumulator(累加器) 产生什么影响？\n" +
                "会对该函数运行结果产生什么影响？\n" +
                "答案是：第三个参数无效。\n" +
                "符合预期的说法：非并行情况下第三个参数根本就不需要，不会对该函数产生任何影响\n" +
                "Debug:该函数执行完第二个参数后直接停止运行，根本没有访问到第三个参数去执行");
        Stream<Integer> stream4=Stream.of(1,2,3);
        //输出结果为16     10+1+2+3
        out.println(stream4
                .reduce(
                        10,
                        (x,y)->x+y,
                        (x,y)->x*y
                )
        );
    }

    public static void urlTest() throws Exception{
        URL url =new URL("http://www.runoob.com/html/html-tutorial.html");
        System.out.println("URL 是 " +url.toString());
        System.out.println("协议是" +url.getProtocol());
        System.out.println("文件名是" +url.getFile());
        System.out.println("主机是" +url.getHost());
        System.out.println("路径是" +url.getPath());
        System.out.println("端口号是" +url.getPort());
        System.out.println("默认端口号是" +url.getDefaultPort());
    }

    //字节数组转String
    public static void test31(){
        byte[] b = {1,2,3,4,5};
        System.out.println(Arrays.toString(b));
    }

    public static void test30(){
        Object o = "woshi  ";
        String a = ((String) o).replace(" ","");
        System.out.println((String)o);
        System.out.println(a);
    }

    //测试栈 3.19.2019
    public static void test29(){
        LinkStackLearn stack = new LinkStackLearn();
        stack.push("hello");
        stack.push("world");
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.peek());
    }

    //测试队列 3.19.2019
    public static void test28(){
        QueueLearn queue = new QueueLearn();
        queue.enqueue("第一个");
        queue.enqueue("第二个");
        queue.enqueue("第三个");
        queue.enqueue("第四个");
        queue.dequeue();
        queue.print();
    }

    public static void  test27(){
        // String.intern()
        String str7 = new String("物联网");
        System.out.println("str7.intern() == str7:" + (str7.intern() == str7));
    }
    private static void test26() {
        TestI testI = System.out::println;
        testI.print("hello");
    }

    public static void test25(){
        //测试 ListIterator add()方法
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        ListIterator listIterator = list.listIterator();
        while(listIterator.hasNext()){
            String o = (String)listIterator.next();
            listIterator.add("0.5");
            break;
        }
        System.out.println(list);
    }

    public static void test24(){
        //测试 ListIterator add()方法
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        ListIterator listIterator = list.listIterator();
        listIterator.add("000"); //游标后面 插入元素
        System.out.println(list);
        //插入后
        while(listIterator.hasNext()){
            String o = (String)listIterator.next();
            System.out.println(o); //读取的第一个元素仍然是 111
            break;
        }
    }

    public static void test23(){
        List<String> list1 = new LinkedList<String>(Arrays.asList(new String[] { "a", "b", "c" }));
        ListIterator<String> listIterator1 = list1.listIterator();
        listIterator1.add("D");
//        listIterator1.add("E");
        System.out.println(list1);//[D, E, a, b, c]
    }

    public static String test22() {
        //测试三目表达式 a>b?a:b
        int hours = (1000 / 60) / 60;
        int minutes = (1000 / 60) % 60;
        int seconds = 1000 % 60;
        return hours + ":" + (minutes >= 10 ? minutes : ("0" + minutes)) + ":" + (seconds >= 10 ? seconds : ("0" + seconds));
    }
    public static void test21() {
        //测试list.listIterator(int index)方法
        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        ListIterator listIterator = list.listIterator(5);
        while (listIterator.hasNext()) {
            int a = (int) listIterator.next();
            System.out.println(a);
        }
    }

    public static void test20(){
        try{
            String filePath = "F:/IdeaProjects/javaTest/src/main/resources/file.txt";
            WritableResource res1 = new PathResource(filePath);//得到文件
            OutputStream stream1 = res1.getOutputStream();//从内存写入文件：欢迎光临
            stream1.write("欢迎光临".getBytes());
            stream1.close();

            ClassPathResource res2 = new ClassPathResource("file.txt");//文件需要放在resources文件夹下，不能放在java文件夹下
            InputStream ins2 = res2.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int i;
            while((i=ins2.read()) != -1){
                baos.write(i);
            }
            System.out.println(baos.toString());

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void test19(){
        ClassLoader loader = Thread.currentThread().getContextClassLoader();//app类加载器
        System.out.println("current loader:"+loader);
        System.out.println("parent loader:"+loader.getParent());
        System.out.println("grandparent loader:"+loader.getParent().getParent());
        /**
         * current loader:jdk.internal.loader.ClassLoaders$AppClassLoader@4629104a
         * parent loader:jdk.internal.loader.ClassLoaders$PlatformClassLoader@b684286
         * grandparent loader:null
         */
    }

    public static void test18(){
        String startTime = "2018-11-28T10:58:17.000Z";
        if(!startTime.matches("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}") && !startTime.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d{3}Z")){
            System.out.println(false);
        }
        System.out.println(startTime.replace("-","/")); // 2018/11/28T10:58:17.000Z
        System.out.println(startTime); // 2018-11-28T10:58:17.000Z
    }

    public static String UTCStringtoDefaultString(String UTCString) {
        try{
//            UTCString = UTCString.replace("Z", " UTC");
//            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");
            SimpleDateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
            SimpleDateFormat defaultFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = utcFormat.parse(UTCString);
            return defaultFormat.format(date);
        } catch(ParseException pe) {
            pe.printStackTrace();
            return null;
        }
    }
    public static void test17(){
        //initialize({DemoApplication.class});
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = sdf1.parse("2018-01-22T09:12:43.083Z");//拿到Date对象
            String str = sdf2.format(date);//输出格式：2017-01-22 09:28:33
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void test16(){
        StringBuffer place = new StringBuffer();
        String test = null;
        System.out.println(place.toString()==null);
    }
    public static void test15() {
        String name = "  ";
        char[] word = name.replaceAll(" ", "").toCharArray();
        System.out.println(word.length);
    }

    public static void test14(){
        List list = new ArrayList();
        a(list);
        System.out.println(list.size());
        String a = "";
        b(a);
        System.out.println(a);
    }

    public static void a(List list){
        list.add("123");
    }
    public static void b(String a){
        a = "123";
    }

    public static void test13(){
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        System.out.println(list.contains("111"));
//        Iterator<String> it = list.iterator();
//        for(String one:list){
//            System.out.println(one);
//        }
    }
    public static void test12(){
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        Iterator<String> it = list.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
    }
    public static void test11(){
        Date date = new Date();
        System.out.println(date.toString());
    }
    public static void test10(){
        List<String> list = Arrays.asList(new String[] {"aa", "bb", "cc", "dd", "ee"});
        Collections.reverse(list);
        for (String string : list) {
            System.out.println(string);
        }
    }

    public final static String[] word ={
            "a","b","c","d","e","f",
            "g","h","j","k","m","n",
            "p","q","r","s","t","u",
            "v","w","x","y","z",
            "A","B","C","D","E","F",
            "G","H","J","K","M","N",
            "P","Q","R","S","T","U",
            "V","W","X","Y","Z"
    };

    public static String[] num ={
            "2","3","4","5","6","7","8","9"
    };
    /**
     * 随机生成密码
     */
    public static String randomAccount(int roleType){
        StringBuffer stringBuffer = new StringBuffer();
        if(roleType == 1){
            stringBuffer.append("b");
        }
        else if(roleType == 2){
            stringBuffer.append("s");
        }
        else if(roleType == 3){
            stringBuffer.append("t");
        }
        else if(roleType == 4){
            stringBuffer.append("g");
        }
        Random random = new Random();
        int length = 6;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(num[random.nextInt(num.length)]);
        }
        return stringBuffer.toString();
    }
    public static String randomPassword(){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random(new Date().getTime());
        int length = 6;
        for (int i = 0; i < length; i++) {
            stringBuffer.append(num[random.nextInt(num.length)]);
        }
        return stringBuffer.toString();
    }
    public static String randomPassword1(){
        StringBuffer stringBuffer=new StringBuffer();
        System.out.println("getTime()="+new Date().getTime());
        //以当前时间生成random
        Random random = new Random(new Date().getTime());
        boolean flag=false;
        //设置默认密码的长度为1+6位
        int length = random.nextInt(8) + 6;
        System.out.println("length="+length);
//        for (int i = 0; i < length; i++) {
//            if(flag){
//                stringBuffer.append(num[random.nextInt(num.length)]);
//            }else{
//                stringBuffer.append(word[random.nextInt(word.length)]);
//            }
//            flag=!flag;
//        }
        return stringBuffer.toString();
    }

    public static void test9(){
//        List<String> list = Arrays.asList("111","222","333","444");
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        for(String one:list){
            if(one.equals("222")){
                list.remove(one);
            }
        }
        System.out.println(list.size());
    }
    public static void test8(){
        List<String> list = new ArrayList<>();
        list.add("111");
        list.add("222");
        list.add("333");
        list.add("444");
        System.out.println(list.size());
        if (null != list && list.size() > 0) {
            Iterator<String> it = list.iterator();
            while(it.hasNext()){
                String x = it.next();
               if (x.equals("222")) {
                    it.remove(); //移除该对象
               }
            }
        }
        System.out.println(list.size());
    }
    public static void test7(){
        long dateLong = new Date().getTime();//毫秒
        Date date = new Date(dateLong);
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = f.format(date);
        System.out.println(dateStr);
    }

    public static void test6(){
        String dateStr = "2018-09-27 10:58:00";
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = f.parse(dateStr);
        }catch (Exception e){

        }
        long dateLong = date.getTime();
        System.out.println(dateLong);
//        long time1 = new Date().getTime();
//        long time2 =0;
//        System.out.println("diff:"+(time1-time2));
//        long shutTime = 50*60000;
//        System.out.println("shutTime:"+shutTime);
    }
    public static void test5(){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time2 = 0;
        try{
            time2 = f.parse("2018-09-25 15:00:00").getTime();
        }catch (Exception e){

        }
        long endTime = (30+30)*60000;
        System.out.println(f.format(new Date(time2+endTime)));
    }
    public static void test4(){
        List<String> list = new ArrayList();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() < o2.length()){
                    return -1;
                }else if(o1.length() > o2.length()){
                    return 1;
                }
                return 0;
            }
        });
        for(String a:list){
            System.out.println(a);
        }
    }
    public static void test3(){
        String[] heihei = {"one","two","three","four","five"};
        int a = 0;
        System.out.println(heihei[a++]+",等于heihei[0]");
        System.out.println(a);
    }
    public static void test2(){
        List<String> lists = new ArrayList();
        Object one = lists.add("one");
        Object two = lists.add("one");
        System.out.println(two);
    }
    public static void test1(){
        List<String> lists = new ArrayList();
        lists.add("one");
        C c = new C();
        c.setLists(lists);
        lists.add("two");
        System.out.println(c.getLists().size());

    }

    static class C{
        private List<String> lists;
        public List<String> getLists() {
            return lists;
        }
        public void setLists(List<String> lists) {
            this.lists = lists;
        }
    }

}

interface TestI {
    void print(String string);
}
