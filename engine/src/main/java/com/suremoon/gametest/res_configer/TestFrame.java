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
 * �ļ������ܡ����������ܡ����ļ����ܡ���ѡ��ť����ѡ��ť ��
 * �����˵����б�˵��������ʾ�ȹ���
 * @author LzwGlory
 * @version 2014/2/18
 * */
public class TestFrame extends JFrame {

    public TestFrame() {

        MenuTest menuTest = new MenuTest();// ������ļ��˵�

        LeftPanel leftPanel = new LeftPanel(); // �������

        RightPanel rightPanel = new RightPanel();// �������

        BottomPanel bottomPanel = new BottomPanel();// �ײ����

        CenterPanel centerPanel = new CenterPanel();// �м�����

        Container c = this.getContentPane();// �������frame�����

        this.setJMenuBar(menuTest); // �����frame��Ӳ˵���

        c.add(leftPanel, BorderLayout.WEST);// Ȼ��ͨ���߽粼��

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

        setTitle("Swing �����ȫ�����");

        setLocationRelativeTo(null);

        setUndecorated(true);

        // setLocation(200,150);

        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setVisible(true);

    }

    class MenuTest extends JMenuBar {// �̳в˵�Bar

        private JDialog aboutDialog;

        public MenuTest() {

            JMenu fileMenu = new JMenu("�ļ�");// ʵ����һ�����ļ����Ĳ˵�

            JMenuItem exitMenuItem = new JMenuItem("�˳�", KeyEvent.VK_E); // �˳��˵���

            JMenuItem aboutMenuItem = new JMenuItem("����..", KeyEvent.VK_A); // ���ڲ˵���

            fileMenu.add(exitMenuItem); // ����Щ�˵���ŵ����ļ����˵�����

            fileMenu.add(aboutMenuItem);

            this.add(fileMenu);// ��ӵ����jframe����

            aboutDialog = new JDialog(); // ��ʵ����һ��Dialog

            initAboutDialog();// ��ʼ�����dialog

            exitMenuItem.addActionListener(new ActionListener() {// ���˳��˵������һ���˳�ʱ��

                public void actionPerformed(ActionEvent e) {// �˳���Ӽ����¼�

                    dispose();

                    System.exit(0);

                }

            });

            aboutMenuItem.addActionListener(new ActionListener() {// ������Ӽ����¼�

                public void actionPerformed(ActionEvent e) {

                    aboutDialog.show();

                }

            });

        }

        public void initAboutDialog() { // ��ʼ��dialog

            aboutDialog.setTitle("����");// ����dialog�ı���

            Container con = aboutDialog.getContentPane();// ���dialog����������

            Icon icon = new ImageIcon("images/LxGLory.gif");

            JLabel aboutLabel = new JLabel("<html><b><font size=5>"
                    + "<center>Swing!" + "<br>", icon, JLabel.CENTER);

            con.add(aboutLabel, BorderLayout.CENTER);// ���ñ߽粼�� Ϊ����

            aboutDialog.setSize(450, 225);// ����dialog�Ĵ�С

            aboutDialog.setLocationRelativeTo(null);// Ĭ����Ļ����

        }

    }

    class LeftPanel extends JPanel {

        private int i = 0;

        public LeftPanel() {

            DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");// Ĭ����ĸ��ڵ�

            DefaultMutableTreeNode child = new DefaultMutableTreeNode("Child");

            DefaultMutableTreeNode select = new DefaultMutableTreeNode("select");

            DefaultMutableTreeNode child1 = new DefaultMutableTreeNode("" + i);

            root.add(child); // �����ڵ������Ҷ�ӽڵ�

            root.add(select);

            child.add(child1);

            JTree tree = new JTree(root);

            tree.getSelectionModel().setSelectionMode(
                    TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

            tree.setRowHeight(20);// �����������Ľڵ�20�и�

            tree.addTreeSelectionListener(new TreeSelectionListener() {// ��ÿ���ڵ���Ӽ����¼�

                public void valueChanged(TreeSelectionEvent e) {// ��������ÿѡ������������һ���ڵ�

                    JTree tree = (JTree) e.getSource();

                    DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree
                            .getLastSelectedPathComponent();

                    i++;

                    selectNode.add(new DefaultMutableTreeNode("" + i));

                }

            });

            tree.setPreferredSize(new Dimension(100, 300));// ����Ĭ�Ͽ��

            JScrollPane scrollPane = new JScrollPane(tree);// ������ӹ������

            this.add(scrollPane); // Ȼ��ѹ������ӵ�jframe��

        }

    }

    class BottomPanel extends JPanel {// �̳���� ʵ��һ�������������

        private JProgressBar pb;// ����һ����������bar

        public BottomPanel() {

            pb = new JProgressBar();

            pb.setPreferredSize(new Dimension(680, 20));// ���ó�ʼ�����

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

    class RightPanel extends JPanel { // �̳�һ�����

        public RightPanel() {

            this.setLayout(new GridLayout(8, 1));// ���ò��ֲ�������ȥ

            JCheckBox checkBox = new JCheckBox("��ѡ��ť");// ʵ����һ����ѡ��ť

            JButton button = new JButton("���ļ�");// ʵ����һ�����ļ�

            button.addActionListener(new ActionListener() { // ������ļ����һ���¼�

                public void actionPerformed(ActionEvent e) {

                    JFileChooser file = new JFileChooser();// ʵ����һ���ļ�ѡ��

                    int resule = file.showOpenDialog(new JPanel());

                    if (resule == file.APPROVE_OPTION) {

                        String fileName = file.getSelectedFile().getName();

                        String dir = file.getSelectedFile().getName();

                        JOptionPane.showConfirmDialog(null, dir + "\\"
                                + fileName, "ѡ����ļ�", JOptionPane.YES_OPTION);// ��ʾһ��confirm�Ĵ���

                    }

                }

            });

            JToggleButton toggleButton = new JToggleButton("˫̥��ť");

            ButtonGroup buttonGroup = new ButtonGroup();// ʵ����һ����ť��

            JRadioButton radioButton1 = new JRadioButton("��ѡ��ť1", false);// ʵ����һ����ѡ��ť

            JRadioButton radioButton2 = new JRadioButton("��ѡ��ť2", false);

            JComboBox comboBox = new JComboBox(); // ����һ�������˵�

            comboBox.addItem("����Ա");
            comboBox.addItem("����Ա2");
            comboBox.addItem("����Ա");
            comboBox.addActionListener(new ActionListener() {//���һ����ѡ����¼�ѡ���ĸ������ĸ���ֵ

                @Override
                public void actionPerformed(ActionEvent e) {
                    // TODO Auto-generated method stub
                    JComboBox comboBox = (JComboBox) e.getSource();
                    String mes=comboBox.getSelectedItem().toString();
                    JOptionPane.showMessageDialog(null,mes, "��Ϣ��",
                            JOptionPane.YES_OPTION);
                }
            });

            DefaultListModel litem = new DefaultListModel();// ʵ����һ���б�

            litem.addElement("�㽶");

            litem.addElement("ˮ��");

            JList list = new JList(litem);

            list.addListSelectionListener(new ListSelectionListener() {

                public void valueChanged(ListSelectionEvent e) {

                    JList l = (JList) e.getSource();

                    Object s = l.getSelectedValue();

                    JOptionPane.showMessageDialog(null, s, "��Ϣ��",
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
                    Color.LIGHT_GRAY, Color.blue));// ���ñ߿����ʽ

        }

    }

    class CenterPanel extends JPanel {

        public CenterPanel() {

            JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);//ʵ����һ��tab�л������

            JTextField textField = new JTextField("�ı��򣬵����<�ļ���ť>��ѡ���ļ�");//ʵ����һ���ı��༭�ֶ�Ȼ������Ĭ��ֵ

            textField.setActionCommand("textField");

            JTextPane textPane = new JTextPane();//����һ���ı��༭���

            textPane.setCursor(new Cursor(Cursor.TEXT_CURSOR));

            textPane.setText("�༭�������ŵ���ı��������������ָ�����");

            textPane.addMouseListener(new MouseAdapter() {//���һ����갴���¼�

                public void mousePressed(MouseEvent e) {

                    JTextPane textPane = (JTextPane) e.getSource();

                    textPane.setText("�༭���������ɹ�");

                }

            });

            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                    textField, textPane);//ʵ����һ���ı��ָ����� �ı��༭�ֶ� �� �ı��༭���ָ� ����

            JTable table = new JTable(10, 10);//����һ�����10*10

            JPanel pane = new JPanel();

            pane.add(table.getTableHeader(), BorderLayout.NORTH);

            pane.add(table);

            tab.addTab("�ı���ʾ", splitPane);//����л������

            tab.addTab("�����ʾ", pane);

            tab.setPreferredSize(new Dimension(500, 600));

            this.add(tab);

            this.setEnabled(true);

        }

    }

    public static void main(String args[]) {
        new TestFrame();
    }

}