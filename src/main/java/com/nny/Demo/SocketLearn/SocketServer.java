package com.nny.Demo.SocketLearn;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 * 服务器端程序和客户端程序是一对一的关系
 * 2019.3.5
 */

public class SocketServer {
    public static void main(String[] args)throws IOException{
        int port = 7000; //端口号
        ServerSocket serverSocket = new ServerSocket(port); //在端口上创建一个服务器套接字
        Socket socket = serverSocket.accept(); //监听来自客户端的连接
        DataInputStream dataInputStream = new DataInputStream(
                new BufferedInputStream(socket.getInputStream())
        );
        DataOutputStream dataOutputStream = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream())
        );
        do{
            double length = dataInputStream.readDouble();
            System.out.println("服务器端收到的边长数据为：" + length);
            double result = length * length;
            dataOutputStream.writeDouble(result);
            dataOutputStream.flush();
        }while(dataInputStream.readInt() != 0);
        socket.close();
        serverSocket.close();
    }
}
