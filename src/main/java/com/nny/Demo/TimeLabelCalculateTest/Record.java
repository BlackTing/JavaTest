package com.nny.Demo.TimeLabelCalculateTest;
/**
 * date 2018
 * writer liting
 * content 聊天室中，计时指标由后端来记录和计算计时时长的辅助存储类
 */
public class Record {
    private String labelId;//指标id
    private int state;//指标状态 0：开始计时 1：暂停计时
    private String lastStartTime; //上次开始计时时间
    private int duration; //上次开始计时时的累计时长
    private String userId;
    private String roomId;

    public int getDuration() {
        return duration;
    }

    public int getState() {
        return state;
    }

    public String getLabelId() {
        return labelId;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getLastStartTime() {
        return lastStartTime;
    }

    public void setLastStartTime(String lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomId() {
        return roomId;
    }
}
