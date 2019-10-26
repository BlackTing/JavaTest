package com.nny.Demo.ThreadTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleHttpServer {

    // 处理HttpRequest的线程池
    static ThreadPool<HttpRequestHandler> threadPool = new DefaultThreadPool<HttpRequestHandler>(1);
    // SimpleHttpServer的根路径
    static String basePath;
    static ServerSocket serverSocket;
    //服务监听端口
    static int port = 1122;
    public static void setPort(int port){
        if(port > 0){
            SimpleHttpServer.port = port;
        }
    }
    public static void setBasePath(String basePath){
        if(basePath != null && new File(basePath).exists() && new File(basePath).isDirectory()){
            SimpleHttpServer.basePath = basePath;
        }
    }
    //启动simpleHttpServer
    public static void start() throws Exception{
        serverSocket = new ServerSocket(port);
        Socket socket = null;
        while((socket = serverSocket.accept()) != null){ //无限循环，而不是执行一次
            // 接收一个客户端Socket，生成一个HttpRequestHandler，放入线程池执行
            System.out.println("execute before .......................................");
            threadPool.execute(new HttpRequestHandler(socket)); //为什么访问一个index.html，接收到了4个任务，4个Socket
        }
        serverSocket.close();
    }

    static class HttpRequestHandler implements Runnable {
        private Socket socket;
        public HttpRequestHandler(Socket socket) {
            this.socket = socket;
        }
        @Override
        public void run() {
            System.out.println("working ....................");
            String line = null;
            BufferedReader br = null;
            BufferedReader reader = null;
            PrintWriter out = null;
            InputStream in = null;
            try{
                reader = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()) //从客户端发送给服务器端的数据流,将字节流向字符流的转换
                ); // 创建字符流缓冲区
                String header = reader.readLine(); // 读取一个文本行
//                System.out.println(".............................header:"+header);
                //由相对路径计算出绝对路径
                String filePath = basePath + header.split(" ")[1];
//                String filePath = basePath + "1.png";
                out = new PrintWriter(socket.getOutputStream()); //基于字符的输出流，可以输出字符或字符串整型等数据。输出的目标可以是磁盘文件、其他输出流
//                 如果请求资源的后缀为jpg或者ico，则读取资源并输出
                if (filePath.endsWith("png") || filePath.endsWith("ico")) {
                    in = new FileInputStream(filePath);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    int i = 0;
                    while ((i = in.read()) != -1) {
                        baos.write(i); //将指定的字节写入此字节数组输出流
                    }
                    byte[] array = baos.toByteArray(); // 创建一个新分配的字节数组。数组的大小和当前输出流的大小，内容是当前输出流的拷贝。
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: image/jpeg");
                    out.println("Content-Length: " + array.length);
                    out.println("");
                    socket.getOutputStream().write(array, 0, array.length); //输出流要输出的内容
                }else{
                    br = new BufferedReader(
                            new InputStreamReader(new
                            FileInputStream(filePath))
                    );
                    out = new PrintWriter(socket.getOutputStream());
                    out.println("HTTP/1.1 200 OK");
                    out.println("Server: Molly");
                    out.println("Content-Type: text/html; charset=UTF-8");
                    out.println("");
                    while ((line = br.readLine()) != null) {
                        out.println(line);
                    }
                }
                out.flush(); //刷新缓冲区
            }catch(Exception ex){
                out.println("HTTP/1.1 500");
                out.println("");
                out.flush();
            }finally{
                close(br, in, reader, out, socket);
            }


        }
        // 关闭流或者Socket
        private static void close(Closeable... closeables) {
            if (closeables != null) {
                for (Closeable closeable : closeables) {
                    try {
                        closeable.close();
                    } catch (Exception ex) {
                    }
                }
            }
        }
    }
}
