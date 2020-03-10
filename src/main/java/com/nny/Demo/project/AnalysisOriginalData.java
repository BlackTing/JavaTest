package com.nny.Demo.project;

import com.nny.Demo.AI.BehaviorType;

import java.util.*;

public class AnalysisOriginalData {

    public static final int Teacher = 0;
    public static final int Student = 1;

    /**
     * 行为总个数
     */
    public static final int behaviorNum = BehaviorType.values().length;

    /**
     * 老师行为集合
     */
    public static List<BehaviorType> teacherTypes = new ArrayList<>();

    /**
     * 学生行为集合
     */
    public static List<BehaviorType> studentTypes = new ArrayList<>();

    static{
        for(BehaviorType type : BehaviorType.values()){
            if(type.getType() == Teacher){
                teacherTypes.add(type);
            }else{
                studentTypes.add(type);
            }
        }
    }

    public static final int No_TeacherBehavior = 6;

    private Map<Integer,List<Integer>> map;

    /**
     * 二进制位数
     */
    private int length;

    private int byteNum;

    /**
     * 视频分钟数
     */
    private int minutes;

    private byte byte1;

    private byte byte2;

    private byte[] bytes;

    private int[] forceDegrees;

    private double[] forceDegreeInSecond;

    /**
     * 每分钟关注度
     */
    private double[] focusDegreeInMin;

    public void init(String initialData){
        map = new HashMap<>(behaviorNum);
        for(BehaviorType type : BehaviorType.values()){
            map.put(type.ordinal(),new ArrayList<>());
        }

        length = initialData.length();
        byteNum = length/behaviorNum;
        minutes = (int)Math.ceil(byteNum/60d);

        forceDegrees = new int[byteNum];

        forceDegreeInSecond = new double[byteNum];

        focusDegreeInMin = new double[minutes];

        bytes = new byte[byteNum];
    }

    /**
     * 返回各行为的开始点、结束点
     */
    public void behaviorStartAndEndSecond(String initialData){

        init(initialData);

        insertIntoBytes(Teacher,initialData,length);

        byte1 = bytes[0];
        byte2 = bytes[byteNum-1];

        for(int i = 0 ; i<byteNum ; i++){
            byte a = bytes[i];

            if(i+1 < byteNum){
                byte b = bytes[i+1];
                writeStartAndEndSecond(a,b,i,teacherTypes);
            }
            writeTeacherBehavior(a,i);
        }

        completeFirstAndLastSecond(teacherTypes);

        //TODO 学生行为分析

        insertIntoBytes(Student,initialData,length);

        byte1 = bytes[0];
        byte2 = bytes[byteNum-1];

        for(int i = 0 ; i<byteNum ; i++){
            byte a = bytes[i];

            if(i+1 < byteNum){
                byte b = bytes[i+1];
                writeStartAndEndSecond(a,b,i,studentTypes);
            }

            writeForceDegreeOfSecond(a,i);
        }

        completeFirstAndLastSecond(studentTypes);

        writeForceDegreeOfMins();
    }

    public void insertIntoBytes(int flag,String initialData,int length){

        int i = 0; //index of String

        if(flag == Student)
            i = 6;

        int j = 0; //index of byte[]

        while(i < length){
            String behaviorOfARole = initialData.substring(i,i+6);

            StringBuffer stringBuffer = new StringBuffer("00");
            stringBuffer.append(behaviorOfARole);

            String abyteStr = stringBuffer.toString();
            byte abyte = Byte.parseByte(abyteStr,2);

            bytes[j] = abyte;

            i = i+12;
            j++;
        }

    }

    /**
     * 写入各行为的每次开始结束秒数
     * @param a
     * @param b
     * @param i
     * @param types
     */
    public void writeStartAndEndSecond(byte a,byte b,int i,List<BehaviorType> types){

        byte r = (byte)(b^a);//0011 1111
        if(r != 0){
            for(BehaviorType type : types){

                int index = type.getValue();

                boolean isdiff = ( ((r>>(index-1))&1) == 1 );

                if(isdiff){
                    boolean is1 = ( ((a>>(index-1))&1) == 1 );

                    int second;
                    if(is1){
                        //end
                        second = i+1;
                    }
                    else{
                        //start
                        second = i+2;
                    }

                    map.get(type.ordinal()).add(second);
                }
            }
        }
    }

    /**
     * 补足第一秒和最后一秒
     * @param types
     */
    public void completeFirstAndLastSecond(List<BehaviorType> types){

        for(BehaviorType type : types) {

            int index = type.getValue();

            boolean firstis1 = ( ((byte1>>(index-1))&1) == 1 );
            boolean lastis1 = (((byte2 >> (index -1)) & 1) == 1);

            int second;
            // 1 1 , 1 0
            if(firstis1) {
                // add a start
                second = 1;
                map.get(type.ordinal()).add(0,second);
            }

            // 1 1, 0 1
            if(lastis1) {
                second = byteNum;
                map.get(type.ordinal()).add(second);
            }

        }
    }

    /**
     * 记录每一秒的老师行为，前提：每秒只有一个老师行为
     * @param a
     * @param second
     */
    public void writeTeacherBehavior(byte a,int second){
        //0010 0000
        for(BehaviorType type : teacherTypes){
            int index = type.getValue();
            boolean is1 = (((a>>(index-1))&1) == 1);
            if(is1){
                forceDegrees[second] = type.ordinal();
                return;
            }
        }

        forceDegrees[second] = No_TeacherBehavior;
    }

    /**
     * 计算每秒的平均专注度
     * @param a
     * @param second
     */
    public void writeForceDegreeOfSecond(byte a,int second){

        int teacherType = forceDegrees[second];
        if( teacherType == No_TeacherBehavior)
            forceDegreeInSecond[second] = 0;
        else {
            List<Integer> list = new ArrayList<>();

            for (BehaviorType type : studentTypes) {
                int index = type.getValue();
                boolean is1 = (((a >> (index - 1)) & 1) == 1);
                if (is1) {
                    list.add(type.ordinal());
                }
            }

            if (list.size() == 0)
                forceDegreeInSecond[second] = 0;
            else{
                double d = list.stream()
                        .map(i -> i = getForceScore(teacherType,i)).mapToDouble(Integer::intValue).average().getAsDouble();
                forceDegreeInSecond[second] = d;
            }
        }
    }

    /**
     * 计算每分钟专注度
     */
    public void writeForceDegreeOfMins(){
        //1-60 61-120
        //0-59 60-119
        //1min 2
        //0    1
        for(int i=0;i<minutes;i++){
            double result = 0;
            int x = i*60;
            int y = (i+1)*60;
            int bound = y < byteNum ? y : byteNum;
            int num = bound-x;

            for(;x < bound;x++){
                result = result+forceDegreeInSecond[x];
            }
            focusDegreeInMin[i] = result/num;

            System.out.println("第"+i+"钟的平均值是"+result/num);
        }
    }

    /**
     * 返回老师、学生行为组合的专注度分数
     * @param teacherType
     * @param studentType
     * @return
     */
    public int getForceScore(int teacherType,int studentType){
//        System.out.println(teacherType+" "+studentType);
        studentType = studentType - 6;

        int[][] forceScores = {
            {90,90,30,100,70,50},
            {90,100,70,0,70,80},
            {90,40,20,0,100,0},
            {90,40,70,0,100,0},
            {90,100,100,90,10,10},
            {100,30,50,50,50,100}
        };
        return forceScores[teacherType][studentType];
    }


    public  void getResult(byte[] bytes,int byteNum,List<BehaviorType> types,Map<Integer,List<Integer>> map){
        for(int i = 0 ; (i+1)<byteNum ; i++){

            byte a = bytes[i];
            byte b = bytes[i+1];

//            byte r = (byte)(b^a);//0011 1111

//            if(r != 0){
//                for(BehaviorType type : types){
//
//                    int index = type.getValue();
//
//                    boolean isdiff = ( ((r>>(index-1))&1) == 1 );
//
//                    if(isdiff){
//                        boolean is1 = ( ((a>>(index-1))&1) == 1 );
//
//                        int second;
//                        if(is1){
//                            //end
//                            second = i+1;
//                        }
//                        else{
//                            //start
//                            second = i+2;
//                        }
//
//                        map.get(type.ordinal()).add(second);
//                    }
//                }
//            }
        }

//        byte byte1 = bytes[0];
//        byte byte2 = bytes[byteNum-1];
//
//
//        for(BehaviorType type : types) {
//
//            int index = type.getValue();
//
//
//            boolean firstis1 = ( ((byte1>>(index-1))&1) == 1 );
//            boolean lastis1 = (((byte2 >> (index -1)) & 1) == 1);
//
//            int second;
//            // 1 1 , 1 0
//            if(firstis1) {
//                // add a start
//                second = 1;
//                map.get(type.ordinal()).add(0,second);
//            }
//
//            // 1 1, 0 1
//            if(lastis1) {
//                second = byteNum;
//                map.get(type.ordinal()).add(second);
//            }
//
//        }

    }

    // 1 5, 7 40, 70 80, 100 120

//        int second0 = 0;
//        int second1 = 29;
//
//        for(int i = second0 ; (i+1)<second1 ; i++){
//
//
//        }

    public void print(double[] objects){
        for(int i=0;i<objects.length;i++){
            System.out.println(objects[i]);
        }
    }

    public Map<Integer,List<Integer>> getMap(){
        return this.map;
    }

    public int getMinutes(){
        return this.minutes;
    }

    public double[] getFocusDegreeInMin(){
        return this.focusDegreeInMin;
    }

}
