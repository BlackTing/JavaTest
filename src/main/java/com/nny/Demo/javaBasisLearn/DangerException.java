package com.nny.Demo.javaBasisLearn;

/**
 * 自定义异常类
 */
public class DangerException extends Exception{
    final String message = "超重";

    @Override
    public String getMessage() {
        return message;
    }
}
