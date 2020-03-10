package com.nny.Demo.IOLearn;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 输入输出
 * I/O流
 * 字符流
 * 文件输入输出流
 * 2020年2月19号
 */
public class CopyCharacters {
    public static void main(String[] args) throws Exception{
        FileReader reader = null;
        FileWriter writer = null;

        try{
            /**
             * 文件名
             * 如果name只包含文件名，表示在项目根目录下的文件,即项目所在绝对地址/javaTest/characteroutput.txt
             * 否则，就要包含绝对路径。(/Users/litingyang/Desktop/)
             *
             * 文件
             * FileReader接收的必须是可用的文件
             * FileWriter接收的可以是不存在的文件（但是可以由程序进行创建），也可以是已存在的文件。
             */
            reader = new FileReader("xanadu.txt");
            writer = new FileWriter("characteroutput.txt");

            int a;

            while((a = reader.read()) != -1){
                /**
                 * 输出的内容为什么不可读？
                 */
                System.out.print((char)a);
                writer.write(a);
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
