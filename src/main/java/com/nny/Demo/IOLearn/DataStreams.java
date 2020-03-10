package com.nny.Demo.IOLearn;

import com.nny.Demo.DataStructureLearn.F;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.EOFException;

/**
 * I/O流
 * 数据流
 * 数据输入流和数据输出流
 */
public class DataStreams {

    static final String dataFile = "invoicedata.txt";

    static final double[] prices = { 19.99, 9.99, 15.99, 3.99, 4.99 };
    static final int[] units = { 12, 8, 13, 29, 50 };
    static final String[] descs = { "Java T-shirt",
            "Java Mug",
            "Duke Juggling Dolls",
            "Java Pin",
            "Java Key Chain" };

    public static void main(String[] args) throws IOException {


        DataOutputStream out = null;

        try {
            out = new DataOutputStream(new
                    BufferedOutputStream(new FileOutputStream(dataFile)));

            for (int i = 0; i < prices.length; i ++) {
                out.writeDouble(prices[i]);
                out.writeInt(units[i]);
                out.writeUTF(descs[i]);
            }
        } finally {
            out.close();
        }

        DataInputStream in = null;
        double total = 0.0;

        try {
            in = new DataInputStream(new
                    BufferedInputStream(new FileInputStream(dataFile)));

            double price;
            int unit;
            String desc;

            try {
                while (true) {
                    price = in.readDouble();
                    unit = in.readInt();
                    desc = in.readUTF();

                    System.out.format("You ordered %d units of %s at $%.2f%n", unit, desc, price);
                    total += unit * price;
                }
            }
            catch (EOFException e) { }

            System.out.format("For a TOTAL of: $%.2f%n", total);
        }
        finally {
            in.close();
        }

        /**
         * 测试用文件字节流读取数据输出流生成的文件
         */
        FileInputStream inputStream = null;

        try{
            inputStream = new FileInputStream("invoicedata.txt");
            int a;

            while((a = inputStream.read()) != -1){
                System.out.print((char)a);//字节流输出的是不可读字节
            }
        }
        finally{
            inputStream.close();
        }

    }
}
