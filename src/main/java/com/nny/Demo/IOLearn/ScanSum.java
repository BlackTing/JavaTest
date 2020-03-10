package com.nny.Demo.IOLearn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * 基本I/O
 * IO流
 * 扫描
 * 累加文本文件中的double值
 */
public class ScanSum {
    public static void main(String[] args) throws IOException {
        Scanner s =null;
        double sum = 0;

        try{
            s = new Scanner(new BufferedReader(new FileReader("usnumbers.txt")));

            /**
             * 指定语言环境，扫描器用于分隔标记
             */
            s.useLocale(Locale.US);

            while(s.hasNext()){
                if(s.hasNextDouble()){
                    sum += s.nextDouble();
                }
                else{
                    s.next();
                }
            }
        }
        finally{
            if(s != null){
                s.close();
            }
        }

        System.out.println(sum);
    }
}
