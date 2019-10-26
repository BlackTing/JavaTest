package com.nny.Demo.ThreadTest;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class MutiThread {
    public static void main(String[] args){
        //获取java线程管理MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false,false);
        //遍历线程信息，仅打印线程ID和线程名称信息
        for(ThreadInfo threadInfo : threadInfos){
            System.out.println("["+threadInfo.getThreadId()+"] "+threadInfo.getThreadName());
        }
    }
}
/**
 * 输出：
 * [1] main
 * [2] Reference Handler
 * [3] Finalizer
 * [4] Signal Dispatcher
 * [5] Attach Listener
 * [11] Common-Cleaner
 * [12] Monitor Ctrl-Break
 */
