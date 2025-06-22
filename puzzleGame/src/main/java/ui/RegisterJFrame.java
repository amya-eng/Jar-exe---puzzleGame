package ui;

import domain.User;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class RegisterJFrame extends JFrame implements MouseListener {
        JButton login = new JButton();
        JButton register = new JButton();

        JTextField username = new JTextField();
        //JTextField password = new JTextField();
        JPasswordField password = new JPasswordField();
        public RegisterJFrame() {
            //初始化界面
            initJFrame();

            //在这个界面中添加内容
            initView();


            //让当前界面显示出来
            this.setVisible(true);
        }


        public void initJFrame() {
            this.setSize(488, 430);//设置宽高
            this.setTitle("拼图游戏 V1.0登录");//设置标题
            this.setDefaultCloseOperation(3);//设置关闭模式
            this.setLocationRelativeTo(null);//居中
            this.setAlwaysOnTop(true);//置顶
            this.setLayout(null);//取消内部默认布局
        }

        public void initView() {
            //1. 添加用户名文字
            JLabel usernameText = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/register/用户名.png"))));
            usernameText.setBounds(116, 135, 47, 17);
            this.getContentPane().add(usernameText);

            //2.添加用户名输入框

            username.setBounds(195, 134, 200, 30);
            this.getContentPane().add(username);

            //3.添加密码文字
            JLabel passwordText = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/register/密码.png"))));
            passwordText.setBounds(130, 195, 32, 16);
            this.getContentPane().add(passwordText);

            //4.密码输入框
            password.setBounds(195, 195, 200, 30);
            this.getContentPane().add(password);

            //5.添加登录按钮
            login.setBounds(123, 310, 128, 47);
            login.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/register/登录按钮.png"))));
            //去除按钮的边框
            login.setBorderPainted(false);
            //去除按钮的背景
            login.setContentAreaFilled(false);
            //给登录按钮绑定鼠标事件
            login.addMouseListener(this);
            this.getContentPane().add(login);

            //6.添加注册按钮
            register.setBounds(256, 310, 128, 47);
            register.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/register/注册按钮.png"))));
            //去除按钮的边框
            register.setBorderPainted(false);
            //去除按钮的背景
            register.setContentAreaFilled(false);
            //给注册按钮绑定鼠标事件
            register.addMouseListener(this);
            this.getContentPane().add(register);


            //7.添加背景图片
            JLabel background = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/register/background.png"))));
            background.setBounds(0, 0, 470, 390);
            this.getContentPane().add(background);
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Object obj = e.getSource();
            if (obj== register) {
                System.out.println("点击了注册按钮");
                //获取两个文本输入框中的内容
                String usernameInput = username.getText();
                String passwordInput = password.getText();

                //创建一个User对象
                User userInfo = new User(usernameInput, passwordInput);
                System.out.println("用户输入的用户名为" + usernameInput);
                System.out.println("用户输入的密码为" + passwordInput);
                if (usernameInput.length() == 0 || passwordInput.length() == 0) {
                    //校验用户名和密码是否为空
                    System.out.println("用户名或者密码为空");
                    //调用showJDialog方法并展示弹框
                    showJDialog("用户名或者密码为空");
                } else {
                    LoginJFrame.allUsers.add(new User(usernameInput, passwordInput));          // 往静态变量列表中添加名称-密码对
                }
            } else if (obj == login) {
                System.out.println("点击了登录按钮");
                this.setVisible(false);
                new LoginJFrame();
            }
        }

        public void showJDialog(String content) {
            //创建一个弹框对象
            JDialog jDialog = new JDialog();
            //给弹框设置大小
            jDialog.setSize(200, 150);
            //让弹框置顶
            jDialog.setAlwaysOnTop(true);
            //让弹框居中
            jDialog.setLocationRelativeTo(null);
            //弹框不关闭永远无法操作下面的界面
            jDialog.setModal(true);

            //创建Jlabel对象管理文字并添加到弹框当中
            JLabel warning = new JLabel(content);
            warning.setBounds(0, 0, 200, 150);
            jDialog.getContentPane().add(warning);

            //让弹框展示出来
            jDialog.setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource() == login) {
                login.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/register/登录按下.png"))));
            } else if (e.getSource() == register) {
                register.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/register/注册按下.png"))));
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (e.getSource() == login) {
                login.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/register/登录按钮.png"))));
            } else if (e.getSource() == register) {
                register.setIcon(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/register/注册按钮.png"))));
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

    }
}
