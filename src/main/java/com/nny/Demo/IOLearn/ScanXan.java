package com.nny.Demo.IOLearn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * 基本I/O
 * I/O流
 * 扫描和格式化
 */
public class ScanXan {
    public static void main(String[] args)  throws IOException {
        m2();

    }

    /**
     * 扫描
     * @throws IOException
     */
    public static void m1() throws IOException{
        Scanner s = null;

        try{
            s = new Scanner(new BufferedReader(new FileReader("xanadu.txt")));

            while(s.hasNext()){
                System.out.println(s.next());
            }
        }
        finally{
            if(s != null){
                s.close();
            }
        }
    }

    /**
     * 格式化
     * format方法
     */
    public static void m2() {
        int i = 2;
        double r = Math.sqrt(i);

        System.out.format("The square root of %d is %f.%n", i, r);
    }
}
