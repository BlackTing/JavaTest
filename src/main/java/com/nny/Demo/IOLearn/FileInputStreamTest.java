package com.nny.Demo.IOLearn;

import java.io.*;

public class FileInputStreamTest {

    public static void main(String[] args){
        File file = new File("f:\\file.txt");
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] b = new byte[10];
            int i = 0;
//            while(i != -1){
                i = fileInputStream.read(b);
                System.out.println(i);
//            }
            System.out.println(b[0]);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
