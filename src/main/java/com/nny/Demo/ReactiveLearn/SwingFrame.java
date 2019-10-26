package com.nny.Demo.ReactiveLearn;

import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SwingFrame {
    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setBounds(200,200,400,400);

        jFrame.addMouseListener( //添加监听器对象，也就相当于添加观察者
            new MouseAdapter() { //新建一个监听器对象
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    System.out.println("鼠标点击事件");
                }
            }
        );

        jFrame.addFocusListener(
            new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    super.focusGained(e);
                    System.out.println("焦点事件");
                }
            }
        );
    }
}
