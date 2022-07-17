package com.suremoon.game.configers.unit_resource.frame;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.config.exceptions.NoSuchConfigException;
import com.springmoon.sm_form.config.exceptions.NotLeafException;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.configers.JPicture;
import com.suremoon.game.configers.unit_resource.resource.UnitResInit;
import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.gometry.GRect;
import com.suremoon.game.door.gometry.PointF;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import org.xml.sax.SAXException;

/** Created by Water Moon on 2018/1/16. */
public class UnitEditor extends JFrame {
  JPicture preView = new JPicture();
  JLabel lal = new JLabel("动作列表"),
      ldl = new JLabel("方向列表"),
      lfpx = new JLabel("脚位置-X"),
      lfpy = new JLabel("脚位置-Y"),
      luname = new JLabel("单位名称"),
      lsize = new JLabel("规格"),
      lwidth = new JLabel("宽度"),
      lheight = new JLabel("高度"),
      lsname = new JLabel("显示名"),
      lsca = new JLabel("可添加的状态"),
      laca = new JLabel("可添加的动作");
  JTextField footPosX = new JTextField(),
      footPosY = new JTextField(),
      width = new JTextField("100"),
      height = new JTextField("100"),
      sname = new JTextField(),
      uName = new JTextField();
  DefaultTableModel dtm = new DefaultTableModel();
  JTable stateActionTable = new JTable(dtm);
  JButton openResConfig = new JButton("打开资源配置文件"),
      openUnitConfig = new JButton("打开单位配置文件"),
      saveConfig = new JButton("保存配置"),
      checkIsUsed = new JButton("检测名称是否被占用."),
      addSA = new JButton("添加"),
      deleteSA = new JButton("删除");
  DefaultComboBoxModel sca = new DefaultComboBoxModel(),
      aca = new DefaultComboBoxModel(); // state can add  & action can add 's model
  JComboBox actionList = new JComboBox(),
      directList = new JComboBox(),
      stateCanAdd = new JComboBox(sca),
      actionCanAdd = new JComboBox(aca);
  public static final String directs[] = {"e", "w", "s", "n", "se", "sw", "ne", "nw"};
  // another value need
  String[] actions, tabletitle = new String[] {"state", "action"};
  Map<String, String> sas = new HashMap<>();
  AGSAdapter agsAdapter = new AGSAdapter() {};
  String resConfig;
  PointF footAt = new PointF(0, 0);
  Point footPos;
  long intervaltime = 60;
  ArrayList<String> states = new ArrayList<>();

  public UnitEditor() {
    super("Unit Editor");
    setLayout(null);
    setResizable(false);
    InitStates();
    InitComponent();
    for (int i = 0; i < directs.length; ++i) {
      directList.addItem(directs[i]);
    }
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    footPosX.setText("0.0000");
    footPosY.setText("0.0000");
  }

  protected void InitStates() {
    try {
      ConfigInf ci = new EasyConfig("configs/states.xml");
      ci.open("States");
      ConfigInf[] sts = ci.listConfigs();
      for (int i = 0; i < sts.length; ++i) {
        states.add(sts[i].getValue("name"));
      }
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SAXException e) {
      e.printStackTrace();
    } catch (TransformerConfigurationException e) {
      e.printStackTrace();
    } catch (NoSuchConfigException e) {
      e.printStackTrace();
    } catch (NotLeafException e) {
      e.printStackTrace();
    }
  }

  protected void InitComponent() {
    InitBound();
    InitActions();
  }

  protected void InitBound() {
    setBounds(100, 100, 767, 430);
    preView.setBounds(12, 9, 247, 194);
    add(preView);
    lal.setBounds(292, 9, 217, 23);
    add(lal);
    ldl.setBounds(292, 77, 217, 23);
    add(ldl);
    lfpx.setBounds(292, 155, 87, 21);
    add(lfpx);
    lfpy.setBounds(292, 182, 87, 21);
    add(lfpy);
    luname.setBounds(552, 119, 176, 25);
    add(luname);
    lsname.setBounds(552, 243, 54, 19);
    add(lsname);
    lsize.setBounds(552, 275, 69, 23);
    add(lsize);
    lwidth.setBounds(552, 304, 54, 22);
    add(lwidth);
    lheight.setBounds(552, 334, 54, 21);
    add(lheight);
    lsca.setBounds(12, 326, 207, 26);
    add(lsca);
    laca.setBounds(238, 326, 179, 25);
    add(laca);
    footPosX.setBounds(396, 155, 113, 21);
    add(footPosX);
    footPosY.setBounds(396, 182, 113, 21);
    add(footPosY);
    sname.setBounds(624, 244, 104, 19);
    add(sname);
    width.setBounds(624, 304, 103, 21);
    add(width);
    height.setBounds(624, 334, 104, 21);
    add(height);
    uName.setBounds(553, 150, 175, 26);
    add(uName);
    stateActionTable.setBounds(12, 209, 497, 106);
    JScrollPane jsp = new JScrollPane(stateActionTable);
    jsp.setBounds(12, 209, 497, 106);
    add(jsp);
    openResConfig.setBounds(552, 9, 175, 23);
    add(openResConfig);
    openUnitConfig.setBounds(552, 38, 175, 23);
    add(openUnitConfig);
    saveConfig.setBounds(552, 67, 175, 23);
    add(saveConfig);
    checkIsUsed.setBounds(553, 187, 175, 25);
    add(checkIsUsed);
    addSA.setBounds(434, 353, 70, 27);
    add(addSA);
    deleteSA.setBounds(436, 326, 67, 24);
    add(deleteSA);
    actionList.setBounds(292, 38, 217, 23);
    add(actionList);
    directList.setBounds(292, 106, 217, 23);
    add(directList);
    stateCanAdd.setBounds(12, 353, 207, 27);
    add(stateCanAdd);
    actionCanAdd.setBounds(238, 353, 179, 27);
    add(actionCanAdd);
    tableRefresh();
  }

  protected void InitActions() {
    // open resource config
    openResConfig.addActionListener(
        (ActionEvent e) -> {
          JFileChooser jfc = new JFileChooser(new File("./resource"));
          FileNameExtensionFilter filter = new FileNameExtensionFilter("配置文件 *.xml", "xml");
          jfc.setFileFilter(filter);
          int resoult = jfc.showOpenDialog(UnitEditor.this);
          if (resoult == jfc.APPROVE_OPTION) {
            File configFile = jfc.getSelectedFile();
            openResConfig(configFile);
            uName.setText(configFile.getParentFile().getName());
          }
        });

    // open unit config
    openUnitConfig.addActionListener(
        (ActionEvent e) -> {
          JFileChooser jfc = new JFileChooser(new File("./configs/unit_config"));
          FileNameExtensionFilter filter = new FileNameExtensionFilter("配置文件 *.xml", "xml");
          jfc.setFileFilter(filter);
          int resoult = jfc.showOpenDialog(UnitEditor.this);
          if (resoult == jfc.APPROVE_OPTION) {
            File configFile = jfc.getSelectedFile();
            openUnitConfig(configFile);
          }
        });
    // save config
    saveConfig.addActionListener(
        (ActionEvent e) -> {
          saveConfig();
        });
    // add State-Action
    addSA.addActionListener(
        (ActionEvent e) -> {
          addStateAction();
        });
    // check is unitName exists
    checkIsUsed.addActionListener(
        (ActionEvent e) -> {
          boolean isExisted = checkIsNameExist();
          if (isExisted) {
            JOptionPane.showConfirmDialog(
                null, "The name has existed.", "Error", JOptionPane.YES_OPTION); // 显示一个confirm的窗口
          } else {
            JOptionPane.showConfirmDialog(
                null, "名字未被占用.", "消息", JOptionPane.YES_OPTION); // 显示一个confirm的窗口
          }
        });
    // delete
    deleteSA.addActionListener(
        (ActionEvent e) -> {
          int row = stateActionTable.getSelectedRow();
          if (row == -1) {
            return;
          }
          String state = (String) dtm.getValueAt(row, 0);
          sca.addElement(state);
          dtm.removeRow(row);
          sas.remove(state);
        });
    preView.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            footPos = e.getPoint();
            footAt =
                new PointF(
                    1.0 * footPos.x / preView.getWidth(), 1.0 * footPos.y / preView.getHeight());
            footPosX.setText(Double.toString(footAt.getX()).substring(0, 6));
            footPosY.setText(Double.toString(footAt.getY()).substring(0, 6));
          }
        });

    // state init
    for (String state : states) {
      sca.addElement(state);
    }
  }

  protected void PreViewShow(long passedtime) {
    if (agsAdapter != null && actionList.getSelectedIndex() != -1) {
      int id = directList.getSelectedIndex();
      String actionName = ((ActionNameStr) actionList.getSelectedItem()).getStr();
      preView.getGraphics().fillRect(0, 0, preView.getWidth(), preView.getHeight());
      agsAdapter.show(
          preView.getGraphics(),
          actionName,
          new GRect(0, 0, preView.getWidth(), preView.getHeight()),
          AGSAdapter.directPointList[id],
          passedtime,
          intervaltime,
          true,
          new Point(0, 0));
      Graphics gp = preView.getGraphics();
      gp.setColor(Color.cyan);
      gp.drawLine(
          0,
          (int) (preView.getHeight() * footAt.getY()),
          preView.getWidth(),
          (int) (preView.getHeight() * footAt.getY()));
      gp.drawLine(
          (int) (preView.getWidth() * footAt.getX()),
          0,
          (int) (preView.getWidth() * footAt.getX()),
          preView.getHeight());

      preView.refresh();
    }
  }

  public void showLoop() throws InterruptedException {
    long startTime = System.currentTimeMillis();
    while (true) {
      long currentTime = System.currentTimeMillis();
      PreViewShow(currentTime - startTime);
      Thread.sleep(15);
    }
  }

  protected void openResConfig(File f) {
    resConfig = f.toString();
    String basePath = new File("").getAbsolutePath();
    if (resConfig.startsWith(basePath)) {
      resConfig = resConfig.substring(basePath.length() + 1);
    }
    try {
      UnitResInit.Init(agsAdapter.getResList(), resConfig);
      EasyConfig ec = new EasyConfig(f.toString());
      ec.open("Actions");
      ConfigInf[] ci = ec.listConfigs();
      actions = new String[ci.length];
      for (int i = 0; i < ci.length; ++i) {
        actions[i] = ci[i].getValue("name");
        aca.addElement(new ActionNameStr(actions[i]));
        actionList.addItem(new ActionNameStr(actions[i]));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void openUnitConfig(File f) {
    try {
      String name = f.getName().split("\\.")[0];
      uName.setText(name);
      EasyConfig ec = new EasyConfig(f.toString());
      resConfig = ec.getConfig("ResFile").getValue();
      footPosX.setText(ec.getConfig("FootPos").getValue("x"));
      footPosY.setText(ec.getConfig("FootPos").getValue("y"));
      sname.setText(ec.getConfig("ShowName").getValue());
      footAt = new PointF(Double.valueOf(footPosX.getText()), Double.valueOf(footPosY.getText()));
      openResConfig(new File(resConfig));
      // todo: deal with grow
      ec.open("States");
      ConfigInf[] states = ec.listConfigs();
      sas.clear();
      if (states != null) {
        for (int i = 0; i < states.length; ++i) {
          String an = states[i].getValue("actionName"), sn = states[i].getValue("stateName");
          addStateAction(an, sn);
          sca.removeElement(sn);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  protected void saveConfig() {
    boolean isExist = checkIsNameExist();
    if (isExist) {
      int res =
          JOptionPane.showConfirmDialog(
              null, "文件已经存在，是否替换？.", "警告", JOptionPane.YES_NO_OPTION); // 显示一个confirm的窗口
      if (res == JOptionPane.NO_OPTION) {
        JOptionPane.showConfirmDialog(
            null, "放弃保存当前设置.", "信息", JOptionPane.YES_OPTION); // 显示一个confirm的窗口
        return;
      }
    }
    File f = new File("configs/unit_config/" + uName.getText() + ".xml");
    String twresConfig = resConfig;
    File fileParent = f.getParentFile();
    if (!fileParent.exists()) {
      fileParent.mkdir();
    }
    try {
      FileWriter fw = new FileWriter(f);
      fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n\n<Root>\n");
      fw.write("\t<ResFile>" + twresConfig + "</ResFile>\n");
      fw.write("\t<FootPos x=\"" + footPosX.getText() + "\" y=\"" + footPosY.getText() + "\"/>\n");
      fw.write("\t<ShowName>" + sname.getText() + "</ShowName>\n");
      fw.write("\t<Width>" + width.getText() + "</Width>\n");
      fw.write("\t<Height>" + height.getText() + "</Height>\n");
      // todo: deal with grow and another
      fw.write("\t<States>\n");
      for (Map.Entry<String, String> it : sas.entrySet()) {
        fw.write(
            "\t\t<State stateName=\""
                + it.getKey()
                + "\" actionName=\""
                + it.getValue()
                + "\" />\n");
      }
      fw.write("\t</States>\n");
      fw.write("</Root>");
      fw.flush();
      fw.close();
    } catch (Exception e) {
      e.printStackTrace();
      JOptionPane.showConfirmDialog(
          null, "保存过程中发生了异常，异常信息为：" + e.toString(), "错误", JOptionPane.YES_OPTION); // 显示一个confirm的窗口
    }
    JOptionPane.showConfirmDialog(
        null, "当前单位的配置信息保存成功.", "成功", JOptionPane.YES_OPTION); // 显示一个confirm的窗口
  }

  protected void addStateAction() {
    if (stateCanAdd.getSelectedIndex() == -1 || actionCanAdd.getSelectedIndex() == -1) {
      return;
    }
    String action_name = ((ActionNameStr) actionCanAdd.getSelectedItem()).getStr();
    String state_name = stateCanAdd.getSelectedItem().toString();
    int state_num = stateCanAdd.getSelectedIndex();
    sca.removeElementAt(state_num);
    addStateAction(action_name, state_name);
  }

  protected void addStateAction(String action_name, String state_name) {
    sas.put(state_name, action_name);
    tableRefresh();
  }

  protected boolean checkIsNameExist() {
    File f = new File("configs/unit_config/" + uName.getText() + ".xml");
    return f.exists();
  }

  protected void tableRefresh() {
    synchronized (sas) {
      String[][] res = new String[sas.size()][2];
      int i = 0;
      for (Map.Entry<String, String> it : sas.entrySet()) {
        res[i][0] = it.getKey();
        res[i][1] = it.getValue();
        i++;
      }
      dtm.setDataVector(res, tabletitle);
    }
  }

  class ActionNameStr {
    String str, tstr;

    public ActionNameStr(String str) {
      this.str = str;
      String[] lst = str.split("\\\\");
      tstr = lst[lst.length - 1];
    }

    public String getStr() {
      return str;
    }

    @Override
    public String toString() {
      return tstr;
    }

    @Override
    public boolean equals(Object obj) {
      if (obj instanceof String) {
        return str.equals(obj);
      } else if (obj instanceof ActionNameStr) {
        return str.equals(((ActionNameStr) obj).getStr());
      }
      return super.equals(obj);
    }
  }
}
