package com.nny.Demo.TimeLabelCalculateTest;

import java.util.ArrayList;
import java.util.List;
/**
 * date 2018
 * writer liting
 * content 聊天室中，计时指标由后端来记录和计算计时时长
 */
public class CountTime {

    /**
     * 暂停或者开始计时指标的计时
     * @param labelId
     * @param sendTime
     * @param state
     * @param userId
     * @param roomId
     */
    public void changeState(String labelId,String sendTime,int state,String userId,String roomId){
        Record record = new Record();
        int duration = 0;
        List<Record> records = new ArrayList<>();
        for(Record one:records){
            if(labelId.equals(one.getLabelId())){
                record = one;
            }
        }
        record.setState(state);
        if(state == 1){ //计时关闭
//            duration = record.getDuration()+(sendTime-record.getLastStartTime());
        }else if(state == 0){ //计时开始
            duration = record.getDuration();
            record.setLastStartTime(sendTime);
        }
        record.setDuration(duration);
    }

    public int countDuration(String labelId,String currentTime,String userId,String roomId){
        Record record = new Record();
        List<Record> records = new ArrayList<>();
        for(Record one:records){
            if(labelId.equals(one.getLabelId())){
                record = one;
            }
        }
        int duration = 0;
        if(record.getState() == 0){ //计时开始状态
//            duration = record.getDuration()+(currentTime-record.getLastStartTime());
        }else if(record.getState() == 1){ //计时关闭状态
            duration = record.getDuration();
        }
        return duration;
    }
}
