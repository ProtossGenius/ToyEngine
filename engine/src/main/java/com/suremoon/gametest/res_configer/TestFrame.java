package com.suremoon.gametest.res_configer;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.*;

/**
 * 文件管理功能、进度条功能、打开文件功能、复选按钮、单选按钮 、
 * 下拉菜单、列表菜单、表格演示等功能
 * @author LzwGlory
 * @version 2014/2/18
 * */
public class TestFrame extends JFrame {

    public TestFrame() {

        MenuTest menuTest = new MenuTest();// 上面的文件菜单

        LeftPanel leftPanel = new LeftPanel(); // 左面面板

        RightPanel rightPanel = new RightPanel();// 右面面板

        BottomPanel bottomPanel = new BottomPanel();// 底部面板

        CenterPanel centerPanel = new CenterPanel();// 中间的面板

        Container c = this.getContentPane();// 获得整个frame的面板

        this.setJMenuBar(menuTest); // 给这个frame添加菜单项

        c.add(leftPanel, BorderLayout.WEST);// 然后通过边界布局

        c.add(rightPanel, BorderLayout.EAST);

        c.add(centerPanel, BorderLayout.CENTER);

        c.add(bottomPanel, BorderLayout.SOUTH);

        this.addWindowListener(new WindowAdapter() {

            public void WindowClosing(WindowEvent e) {

                dispose();

                System.exit(0);

            }

        });

        setSize(700, 500);

        setTitle("Swing 组件大全简体版");

        setLocationRelativeTo(null);

        setUndecorated(true);

        // setLocation(200,150);

        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);

    }

    class MenuTest extends JMenuBar {// 继承菜单Bar

        private JDialog aboutDialog;

        public MenuTest() {

            JMenu fileMenu = new JMenu("文件");// 实例化一个“文件”的菜单

            JMenuItem exitMenuItem = new JMenuItem("退出", KeyEvent.VK_E); // 退出菜单项

            JMenuItem aboutMenuItem = new JMenuItem("关于..", KeyEvent.VK_A); // 关于菜单项

            fileMenu.add(exitMenuItem); // 把这些菜单项放到‘文件’菜单项中

            fileMenu.add(aboutMenuItem);

            this.add(fileMenu);// 添加到这个jframe里面

            aboutDialog = new JDialog(); // 再实例化一个Dialog

            initAboutDialog();// 初始化这个dialog

            exitMenuItem.addActionListener(new ActionListener() {// 给退出菜单项添加一个退出时间

                public void actionPerformed(ActionEvent e) {// 退出添加监听事件

                    dispose();

                    System.exit(0);

                }

            });

            aboutMenuItem.addActionListener(new ActionListener() {// 关于添加监听事件

                public void actionPerformed(ActionEvent e) {

                    aboutDialog.show();

                }

            });

        }

        public void initAboutDialog() { // 初始化dialog

            aboutDialog.setTitle("关于");// 设置dialog的标题

            Container con = aboutDialog.getContentPane();// 获得dialog整个的容器

            Icon icon = new ImageIcon("images/LxGLory.gif");

            JLabel aboutLabel = new JLabel("<html><b><font size=5>"
                    + "<center>Swing!" + "<br>", icon, JLabel.CENTER);

            con.add(aboutLabel, BorderLayout.CENTER);// 设置边界布局 为居中

            aboutDialog.setSize(450, 225);// 设置dialog的大小

            aboutDialog.setLocationRelativeTo(null);// 默认屏幕居中

        }

    }

    class LeftPanel extends JPanel {

        private int i = 0;

        public LeftPanel() {

            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");// 默认输的根节点

            DefaultMutableTreeNode child = new DefaultMutableTreeNode("Child");

            DefaultMutableTreeNode select = new DefaultMutableTreeNode("select");

            DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("" + i);

            root.add(child); // 往根节点下添加叶子节点

            root.add(select);

            child.add(child1);

            JTree tree = new JTree(root);

            tree.getSelectionModel().setSelectionMode(
                    TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

            tree.setRowHeight(20);// 设置整个树的节点20行高

            tree.addTreeSelectionListener(new TreeSelectionListener() {// 给每个节点添加监听事件

                public void valueChanged(TreeSelectionEvent e) {// 就是在那每选择项的下面添加一个节点

                    JTree tree = (JTree) e.getSource();

                    DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree
                            .getLastSelectedPathComponent();

                    i++;

                    selectNode.add(new DefaultMutableTreeNode("" + i));

                }

            });

            tree.setPreferredSize(new Dimension(100, 300));// 设置默认宽高

            JScrollPane scrollPane = new JScrollPane(tree);// 给树添加滚动面板

            this.add(scrollPane); // 然后把滚动面板加到jframe上

        }

    }

    class BottomPanel extends JPanel {// 继承面板 实现一个进度条的面板

        private JProgressBar pb;// 声明一个进度条的bar

        public BottomPanel() {

            pb = new JProgressBar();

            pb.setPreferredSize(new Dimension(680, 20));// 设置初始化宽高

            Timer time = new Timer(1, new ActionListener() {

                int counter = 0;

                public void actionPerformed(ActionEvent e) {

                    counter++;

                    pb.setValue(counter);

                    Timer t = (Timer) e.getSource();

                    if (counter == pb.getMaximum()) {

                        t.stop();

                        counter = 0;

                        t.start();

                    }

                }

            });

            time.start();

            pb.setStringPainted(true);

            pb.setMinimum(0);

            pb.setMaximum(1000);

            pb.setBackground(Color.white);

            pb.setForeground(Color.red);

            this.add(pb);

        }

        public void setProcessBar(BoundedRangeModel rangeModel) {

            pb.setModel(rangeModel);

        }

    }

    class RightPanel extends JPanel { // 继承一个面板

        public RightPanel() {

            this.setLayout(new GridLayout(8, 1));// 设置布局采用网格不去

            JCheckBox checkBox = new JCheckBox("复选按钮");// 实例化一个复选按钮

            JButton button = new JButton("打开文件");// 实例化一个打开文件

            button.addActionListener(new ActionListener() { // 给这个文件添加一个事件

                public void actionPerformed(ActionEvent e) {

                    JFileChooser file = new JFileChooser();// 实例化一个文件选择

                    int resule = file.showOpenDialog(new JPanel());

                    if (resule == file.APPROVE_OPTION) {

                        String fileName = file.getSelectedFile().getName();

                        String dir = file.getSelectedFile().getName();

                        JOptionPane.showConfirmDialog(null, dir + "\\"
                                + fileName, "选择的文件", JOptionPane.YES_OPTION);// 显示一个confirm的窗口

                    }

                }

            });

            JToggleButton toggleButton = new JToggleButton("双胎按钮");

            ButtonGroup buttonGroup = new ButtonGroup();// 实例化一个按钮组

            JRadioButton radioButton1 = new JRadioButton("单选按钮1", false);// 实例化一个单选按钮

            JRadioButton radioButton2 = new JRadioButton("单选按钮2", false);

            JComboBox comboBox = new JComboBox(); // 定义一个下拉菜单

            comboBox.addItem("程序员");
            comboBox.addItem("程序员2");
            comboBox.addItem("分析员");
            comboBox.addActionListener(new ActionListener() {//添加一个多选框的事件选择哪个弹出哪个的值

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    JComboBox comboBox = (JComboBox) e.getSource();
                    String mes=comboBox.getSelectedItem().toString();
                    JOptionPane.showMessageDialog(null,mes, "消息框",
                            JOptionPane.YES_OPTION);
                }
            });

            DefaultListModel litem = new DefaultListModel();// 实例化一个列表

            litem.addElement("香蕉");

            litem.addElement("水果");

            JList list = new JList(litem);

            list.addListSelectionListener(new ListSelectionListener() {

                public void valueChanged(ListSelectionEvent e) {

                    JList l = (JList) e.getSource();

                    Object s = l.getSelectedValue();

                    JOptionPane.showMessageDialog(null, s, "消息框",
                            JOptionPane.YES_OPTION);

                }

            });

            buttonGroup.add(radioButton1);

            buttonGroup.add(radioButton2);

            add(button);

            add(toggleButton);

            add(checkBox);

            add(radioButton1);

            add(radioButton2);

            add(comboBox);

            add(list);

            this.setBorder(new EtchedBorder(EtchedBorder.LOWERED,
                    Color.LIGHT_GRAY, Color.blue));// 设置边框的样式

        }

    }

    class CenterPanel extends JPanel {

        public CenterPanel() {

            JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);//实例化一个tab切换的面板

            JTextField textField = new JTextField("文本域，点击打开<文件按钮>可选择文件");//实例化一个文本编辑字段然后设置默认值

            textField.setActionCommand("textField");

            JTextPane textPane = new JTextPane();//设置一个文本编辑面板

            textPane.setCursor(new Cursor(Cursor.TEXT_CURSOR));

            textPane.setText("编辑器，试着点击文本区，试着拉动分隔条。");

            textPane.addMouseListener(new MouseAdapter() {//添加一个鼠标按下事件

                public void mousePressed(MouseEvent e) {

                    JTextPane textPane = (JTextPane) e.getSource();

                    textPane.setText("编辑器点击命令成功");

                }

            });

            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                    textField, textPane);//实例化一个文本分割面板把 文本编辑字段 和 文本编辑面板分割 开了

            JTable table = new JTable(10, 10);//设置一个表格10*10

            JPanel pane = new JPanel();

            pane.add(table.getTableHeader(), BorderLayout.NORTH);

            pane.add(table);

            tab.addTab("文本演示", splitPane);//添加切换的面板

            tab.addTab("表格演示", pane);

            tab.setPreferredSize(new Dimension(500, 600));

            this.add(tab);

            this.setEnabled(true);

        }

    }

    public static void main(String args[]) {
        new TestFrame();
    }

}