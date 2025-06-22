// 测试MyJFrame类：
// 两种方法为窗口添加事件监听，一个匿名类方法，一个this实现接口所有抽象方法的方法
package test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyJFrame extends JFrame implements ActionListener {

    JButton btn1 = new JButton("1");
    JButton btn2 = new JButton("2");

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被操作的对象
        Object source = e.getSource();
        if(source == btn1) {
            btn1.setSize(100,200);
        }else if(source == btn2) {
            Random r = new Random();
            btn2.setLocation(r.nextInt(600),r.nextInt(600));
        }

    }

    public MyJFrame() {               // 构造方法
        // 创建窗口，top居中，设置大小
        this.setSize(600, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("事件演示");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setLayout(null);


        btn1.setBounds(0,0,100, 50);
        //给按钮添加事件
        btn1.addActionListener(this);
        // btn1.addMouseListener(this);则是对细分的鼠标动作进行响应：移入，按住，松开，移出。
        // 若添加键盘监听，则直接给整个窗口添加监听：this.addKeyListener(this);

        btn2.setBounds(200,200,100, 50);
        btn2.addActionListener(this);

        this.getContentPane().add(btn1);
        this.getContentPane().add(btn2);

        this.setVisible(true);

    }
}
