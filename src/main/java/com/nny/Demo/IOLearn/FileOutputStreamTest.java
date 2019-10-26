package com.nny.Demo.IOLearn;

import java.io.*;

public class FileOutputStreamTest {
    public static void main(String[] args){
        File file1 = new File("f:\\file.txt");
        File file2 = new File("f:\\write.txt");
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try{
            fileInputStream = new FileInputStream(file1);
            fileOutputStream = new FileOutputStream(file2);
            int b;
            while((b=fileInputStream.read())!=-1){
                fileOutputStream.write(b);
            }
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if(fileOutputStream!=null){
                    fileOutputStream.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
