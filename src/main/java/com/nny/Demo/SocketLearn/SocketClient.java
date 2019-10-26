package com.nny.Demo.SocketLearn;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 客户端
 * 2019.3.5
 */
public class SocketClient {
    public static void main(String[] args) throws UnknownHostException,IOException{
        int port = 7000;
        String host = "localhost";
        Socket socket = new Socket(host,port); //创建一个套接字并将其连接到指定端口号
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;
        while (!flag){
            System.out.println("请输入正方形的边长");
            double length = scanner.nextDouble();
            dataOutputStream.writeDouble(length);
            dataOutputStream.flush();
            double area = dataInputStream.readDouble();
            System.out.println("服务器返回的计算面积为:" + area);
            while (true) {
                System.out.println("继续计算？(Y/N)");
                String str = scanner.next();
                if (str.equalsIgnoreCase("N")) {
                    dataOutputStream.writeInt(0);
                    dataOutputStream.flush();
                    flag = true;
                    break;
                } else if (str.equalsIgnoreCase("Y")) {
                    dataOutputStream.writeInt(1);
                    dataOutputStream.flush();
                    break;
                }
            }
        }
        socket.close();
    }
}
