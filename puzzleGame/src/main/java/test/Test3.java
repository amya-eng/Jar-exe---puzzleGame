package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Test3 {
    public static void main(String[] args) {
        // 创建窗口，top居中，设置大小
        JFrame frame = new JFrame();
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("事件演示");
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JButton btn = new JButton("点击一下~");
        btn.setBounds(10, 10, 100, 30);
        btn.addActionListener(new ActionListener(){                     // 匿名类
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("按钮被点击。");    // 当事件触发时，在终端打印这句话。
            }
        });     // 动作监听：左键点击或者空格; 传递一个接口的实现类

        frame.getContentPane().add(btn);
        frame.setVisible(true);
    }
}
