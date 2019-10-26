package com.nny.Demo.ThreadTest;

public class SimpleHttpServerTest {
    public static void main(String args[]) throws Exception{
        SimpleHttpServer server = new SimpleHttpServer();
        server.setBasePath("F:\\");
        server.start();
    }
}
