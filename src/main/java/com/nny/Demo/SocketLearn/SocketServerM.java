package com.nny.Demo.SocketLearn;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务器端
 * 使用多线程机制
 * 2019.3.5
 */

/**
 * IO DataInputStream
 * IO BufferedInputStream
 * net Socket
 * net ServerSocket
 */
public class SocketServerM {
    public static void main(String[] args)throws IOException{
        int port = 7000;
        int clientNo = 1;
        ServerSocket serverSocket = new ServerSocket(port);
        ExecutorService executorService = Executors.newCachedThreadPool();
        try{
            while(true){
                Socket socket = serverSocket.accept();
                executorService.execute(new SingleServer(socket,clientNo)); //方法参数是Runnable对象
                clientNo++;
            }
        }finally{
            serverSocket.close();
        }
    }
}
class SingleServer implements Runnable{
    private Socket socket;
    private int clientNo;
    public SingleServer(Socket socket,int clientNo){
        this.socket = socket;
        this.clientNo = clientNo;
    }
    public void run(){
        try{
            DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream())); //字节输入流
            DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream())); //字节输出流
            do{
                double length = dataInputStream.readDouble();
                System.out.println("从客户端" + clientNo + "接收到的边长数据为：" + length);
                double result = length * length;
                dataOutputStream.writeDouble(result);
                dataOutputStream.flush(); //字节输出流需要吗
            }while(dataInputStream.readInt() != 0);
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            System.out.println("与客户端" + clientNo + "通信结束");
            try{
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
