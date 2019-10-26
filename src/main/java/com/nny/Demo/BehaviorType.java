package com.nny.Demo;

/**
 * 行为类型
 */
public enum BehaviorType {

    // 1 指导
    point(6,"指导",AnalysisOriginalData.Teacher),

    // 2 板书
    blackboard(5,"板书",AnalysisOriginalData.Teacher),

    // 3 讲授
    teach(4,"讲授",AnalysisOriginalData.Teacher),

    // 4 演示
    demonstration(3,"演示",AnalysisOriginalData.Teacher),

    // 5 巡视
    inspection(2,"巡视",AnalysisOriginalData.Teacher),

    // 6 提问
    bend(1,"提问",AnalysisOriginalData.Teacher),

    // 7 举手
    hands_up(6,"举手",AnalysisOriginalData.Student),

    // 8 书写
    write(5,"书写",AnalysisOriginalData.Student),

    // 9阅读
    read(4,"阅读",AnalysisOriginalData.Student),

    // 10 讨论
    discuss(3,"讨论",AnalysisOriginalData.Student),

    // 11 听讲
    listen(2,"听讲",AnalysisOriginalData.Student),

    // 12 起立
    stand_up(1,"起立",AnalysisOriginalData.Student);

    //在byte中的位置 1-6
    private int value;
    private String name;
    private int type;

    BehaviorType(int index,String name,int type){
        this.value = index;
        this.name = name;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
