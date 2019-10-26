package com.nny.Demo.ReactiveLearn;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/asyncServlet", asyncSupported = true)
public class AsyncServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("开始执行servlet");

        //开启异步上下文 相当于一个可监听对象
        AsyncContext asyncContext = request.startAsync();

        //异步上下文设置回调函数（监听器）
        asyncContext.addListener( //添加监听器
            new AsyncListener() {
                @Override
                public void onComplete(AsyncEvent asyncEvent) throws IOException {
                    ServletResponse response1 = asyncEvent.getSuppliedResponse(); //向客户端返回响应
                    response1.setContentType("text/html;charset=UTF-8");
                    response1.getWriter().println("complete回调函数返回输出");

                    System.out.println("complete回调函数完成");
                }
                @Override
                public void onTimeout(AsyncEvent asyncEvent) throws IOException {}
                @Override
                public void onError(AsyncEvent asyncEvent) throws IOException {}
                @Override
                public void onStartAsync(AsyncEvent asyncEvent) throws IOException {}
            }
        );

        //开启新的工作线程，释放servlet处理请求线程，工作完后回调异步上下文的监听器
        new Thread(
            () -> {
                try {
                    ServletResponse response1 = asyncContext.getResponse();
                    response1.setContentType("text/html;charset=UTF-8");
                    response1.getWriter().println("异步工作线程返回输出");//向客户端返回响应

                    System.out.println("工作线程完成");

                    //触发回调函数onComplete() 通知监听器
                    asyncContext.complete();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        ).start();

        System.out.println("释放servlet线程");
    }
}
