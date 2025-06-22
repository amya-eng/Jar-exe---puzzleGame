package ui;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    int[] numbers = new int[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    int k = 0;

    // 统计步数
    int counter = 0;

    String path = "/image/animal/animal3/";

    //创建选项下面的条目，放全局变量以便于监听
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");
    JMenuItem exchangeGirl = new JMenuItem("美女");
    JMenuItem exchangeAnimal = new JMenuItem("动物");
    JMenuItem exchangeSport = new JMenuItem("运动");


    JMenuItem accountItem = new JMenuItem("公众号");

    // 定义游戏主界面
    public GameJFrame() {
        //初始化界面
        initJFrame();          // 选中，ctrl+alt+M回车，改名。抽取逻辑函数

        //初始化菜单
        initMenu();

        // 打乱图片顺序
        shuffle(numbers);

        //加载图片
        initImage();     // alt+回车，选择第一个，创建private方法

        //当一个类非常特殊，以至于只能被使用一次时，使用匿名内部类来做
        

        //显示界面
        this.setVisible(true);
    }

    private boolean victory() {
        int temp = 1;
        for(int i = 0; i < numbers.length; i++) {
            if(numbers[i] != temp++) {
                return false;
            }
        }
        return true;
    }

    private void shuffle(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1); // 生成 0 到 i 之间的随机数
            // 交换 array[i] 和 array[j]
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    int move_img = 0;

    private void initImage() {

        this.getContentPane().removeAll(); // 移除所有旧组件（包括图片），统一后续刷新。

        // 前景图先写
        if(victory()) {
            JLabel label = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/win.png"))));
            label.setBounds(203, 283, 197,73);
            this.getContentPane().add(label);
        }

        JLabel stepCount = new JLabel("步数："+counter);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        k = 0;
        // JLabel添加的小细节：先加载的图片会放到上方，后加载的图片放下面。背景图片最后添加。
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                //创建一个图片ImageIcon对象    ctrl + N搜索ImageIcon类，然后ctrl+F12，查看所有方法
                //JLabel label = new JLabel(new ImageIcon("D:\\code\\puzzleGame\\image\\animal\\animal3\\" + numbers[k] + ".jpg"));  // 找不到16.jpg，添加空白图块
                JLabel label = new JLabel(
                        new ImageIcon(
                                Objects.requireNonNull(getClass().getResource(path + numbers[k] + ".jpg"))
                        )
                );
                //System.out.println(path + numbers[k] + ".jpg");
                if(numbers[k] == 16) {
                    move_img = k;

                    // 红色边框:设置为当前选中方块
                    label.setBorder(BorderFactory.createLineBorder(Color.RED, 50));
                    // 设置半透明背景（需要不透明开启）
                    label.setOpaque(true);
                    label.setBackground(new Color(255, 0, 0, 200)); // 红色，透明度80（取值范围 0-255）

                } // 记录空白方块在一维数组numbers里面的位置。


                //指定图片位置
                label.setBounds(105 * j+83, 105 * i+134, 105, 105);      // 所有图片往右下角移动，模拟居中;先按行排，再按列排
                //给图片添加边框
                label.setBorder(new BevelBorder(BevelBorder.LOWERED));   // 参数：让图片凹下去。
                //获取隐藏容器body
                this.getContentPane().add(label);
                k++;

            }
        }

        // 添加背景图片
        ImageIcon bg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/background.png")));
        JLabel background = new JLabel(bg);
        background.setBounds(40, 40, bg.getIconWidth(), bg.getIconHeight());
        this.getContentPane().add(background);

        this.getContentPane().repaint();  // 重新绘制界面
    }


    private void initMenu() {
        //初始化菜单
        //创建菜单对象
        JMenuBar menuBar = new JMenuBar();

        //创建选项对象 【功能】【关于我们】
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我们");

        JMenu exchageMenu = new JMenu("更换图片");


        // 给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        exchangeGirl.addActionListener(this);
        exchangeAnimal.addActionListener(this);
        exchangeSport.addActionListener(this);

        exchageMenu.add(exchangeGirl);
        exchageMenu.add(exchangeAnimal);
        exchageMenu.add(exchangeSport);

        functionJMenu.add(exchageMenu);  // JMenu嵌套JMenu
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);
        aboutJMenu.add(accountItem);

        menuBar.add(functionJMenu);
        menuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(menuBar);
    }

    private void initJFrame() {
        //设置界面宽高
        this.setSize(600, 680);
        //设置界面标题
        this.setTitle("拼图单机版v1.0");
        //设置界面置顶，点击新的窗口，但该窗口还是置前
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置默认关闭模式
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // ctrl B参数，可以发现接口的static变量==状态

        // 右键选择translate，对英文doc进行翻译

        // 添加键盘响应
        this.addKeyListener(this);
        //取消默认的居中放置，只有取消了才会按照XY轴形式添加组件
        this.setLayout(null);
    }

    @Override
    public void keyTyped(KeyEvent e) {                   // 用一个即可。

    }

    // 按住不松
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // 按住A不松，显示all.jpg
        if(code == 65) {
            this.getContentPane().removeAll();
            //加载all.jpg和背景图片
            JLabel all = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource(path+"all.jpg"))));
            all.setBounds(83,134, 420, 420);
            this.getContentPane().add(all);

            // 添加背景图片
            ImageIcon bg = new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/background.png")));
            JLabel background = new JLabel(bg);
            background.setBounds(40, 40, bg.getIconWidth(), bg.getIconHeight());
            this.getContentPane().add(background);

            this.getContentPane().repaint();  // 重新绘制界面

        }
    }

    // 上下左右移动
    @Override
    public void keyReleased(KeyEvent e) {
        if(victory()) {
            return;          // 防止游戏胜利后继续移动
        }
        int code = e.getKeyCode();  // 左：37, 右:39，上：38；下：40
        int tmp;
        // System.out.println(code);
        if(code == 37) {
            // 向左移动过
            if(move_img + 1 >= numbers.length || move_img % 4 == 3)               // 超出范围则不动||处于边界则不动
                return;
            tmp = numbers[move_img];
            numbers[move_img] = numbers[move_img + 1];
            numbers[move_img + 1] = tmp;
            counter++;
        } else if(code == 38) {
            // 上：将空白方块下方的方块与其对换
            if(move_img + 4 >= numbers.length)
                return;
            tmp = numbers[move_img];
            numbers[move_img] = numbers[move_img + 4];
            numbers[move_img + 4] = tmp;
            counter++;

        } else if(code == 39) {
            // 右
            if(move_img - 1 < 0 || move_img % 4 == 0)
                return;
            tmp = numbers[move_img];
            numbers[move_img] = numbers[move_img - 1];
            numbers[move_img - 1] = tmp;
            counter++;

        } else if(code == 40) {
            // 下
            if(move_img - 4 < 0)
                return;
            tmp = numbers[move_img];
            numbers[move_img] = numbers[move_img - 4];
            numbers[move_img - 4] = tmp;
            counter++;

        } else if(code == 87) {
            // 按住W，一件通关
            int temp = 1;
            for(int i = 0; i < numbers.length; i++) {
                numbers[i] = temp++;
            }

        }
        initImage();         // 重新加载图片
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的对象
        Object obj = e.getSource();
        if(obj == replayItem) {
            counter = 0;
            shuffle(numbers);
            initImage();
        }else if(obj == reLoginItem) {
            // 关闭当前界面
            this.setVisible(false);
            // 打开登录界面
            new LoginJFrame();

        }else if(obj == closeItem) {
            // 直接关闭虚拟机
            System.exit(0);
        }else if(obj == accountItem) {
            // 创建弹窗对象
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel(new ImageIcon(Objects.requireNonNull(getClass().getResource("/image/about.png"))));
            jLabel.setBounds(0, 0, 258, 258);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(344, 344);
            jDialog.setAlwaysOnTop(true);
            // 让弹框居中
            jDialog.setLocationRelativeTo(null);
            // 让弹框不关闭则无法操作其他界面
            jDialog.setModal(true);
            // 显示
            jDialog.setVisible(true);
        } else if(obj == exchangeGirl) {
            counter = 0;
            Random rand = new Random();
            int num = rand.nextInt(13) + 1;
            path = "/image/girl/girl" + num + "/";
            shuffle(numbers);
            System.out.println(path);
            initImage();
        } else if(obj == exchangeAnimal) {
            counter = 0;
            Random rand = new Random();
            int num = rand.nextInt(8) + 1;
            path = "/image/animal/animal" + num + "/";
            shuffle(numbers);
            System.out.println(path);
            initImage();
        } else if(obj == exchangeSport) {
            counter = 0;
            Random rand = new Random();
            int num = rand.nextInt(10) + 1;
            path = "/image/sport/sport" + num + "/";
            shuffle(numbers);
            System.out.println(path);
            initImage();
        }
    }
}
