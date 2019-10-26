package com.nny.Demo.ThreadTest;

import java.util.concurrent.TimeUnit;

public class SleepUtils {
    public static final void second(long seconds){
        try{
            TimeUnit.SECONDS.sleep(seconds); //线程休眠
        }catch(InterruptedException e){

        }
    }
}
