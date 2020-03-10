package com.nny.Demo.IOLearn;

import java.io.Console;
import java.util.Arrays;

/**
 * 来自命令行的I/O流
 * 控制台的安全密码输入
 */
public class Password {
    public static void main(String args[]){
        Console c = System.console();

        if(c == null){
            System.err.println("No Console");
            System.exit(1);
        }

        /**
         * 控制台的输入流的操作
         * 控制台是源，从控制台输入到内存
         */
        String login = c.readLine("Enter your login:");
        char[] oldPassword = c.readPassword("Enter your old password:");

        if(verify(login,oldPassword)){
            boolean noMatch;

            do{
                char[] newPassword1 = c.readPassword("Enter your new password:");
                char[] newPassword2 = c.readPassword("Enter your new password again:");

                noMatch = !Arrays.equals(newPassword1,newPassword2);

                if(noMatch){
                    /**
                     * console的输出流
                     * 目的地是控制台
                     */
                    c.format("Passwords don't match.Try again.%n");
                }
                else{
                    change(login,newPassword1);
                    c.format("Password for %s changed.%n",login);
                }

                Arrays.fill(newPassword1,' ');
                Arrays.fill(newPassword2,' ');

            }while(noMatch);
        }

    }

    static boolean verify(String login,char[] oldPassword){
        return true;
    }

    static void change(String login,char[] password){

    }


}
