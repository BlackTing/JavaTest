package com.nny.Demo.IOLearn;

import java.io.*;

/**
 * 基本I/O
 * I/O流
 * 字符流
 * 面向行的I/O
 * 2020年2月19号
 */
public class CopyLines {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = null;
        PrintWriter writer = null;

        try{
            reader = new BufferedReader(new FileReader("xanadu.txt"));
            writer = new PrintWriter(new FileWriter("characteroutput.txt"));

            String l;

            while((l = reader.readLine()) != null){
                /**
                 * 为什么输出的是乱码？这里的l不是Unicode编码吗？
                 */
                System.out.println(l);
                writer.println(l);
            }
        }
        finally{
            if(reader != null){
                reader.close();
            }
            if(writer != null){
                writer.close();
            }
        }

    }
}
