package com.suremoon.suremoon.forms;

import com.suremoon.game.ag_pc_client.show.pc_show.ClientStartup;
import com.suremoon.game.ag_pc_client.show.pc_show.DraftForm;
import com.suremoon.game.door.attribute.ComplexAttribute;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.kernel.initer.InitListItf;
import com.suremoon.game.kernel.initer.InitManager;
import com.suremoon.game.kernel.initer.Progress;
import com.suremoon.suremoon.mods.analysiser.ClientInputAnalysis;

import javax.swing.*;
import java.awt.*;

public class PlayerSetter extends JFrame {
  private final JLabel l_playerType = new JLabel("玩家类型");
  private final JAutoCompleteComboBox acc_playerType = new JAutoCompleteComboBox();
  private final JLabel l_playerName = new JLabel("角色名称");
  private final JTextField t_playerName = new JTextField("头号玩家");
  private final JButton b_startGame = new JButton("开始游戏");

  public PlayerSetter() throws Exception {
    setSize(600, 600);
    setLayout(null);
    setLocationRelativeTo(null);
    l_playerType.setBounds(30, 100, 100, 30);
    add(l_playerType);
    acc_playerType.setBounds(130, 100, 300, 30);
    add(acc_playerType);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    new InitListItf() {
      @Override
      public void init(Progress progress) throws Exception {
        loadList("./configs/unit_config", ".xml", acc_playerType::addItem, progress);
      }
    }.init(Progress.NIL);
    try {
      acc_playerType.setSelectedItem("T_red_knight");
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      JOptionPane.showConfirmDialog(null, "未能正确初始化角色", "错误", JOptionPane.YES_OPTION);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setVisible(false);
    }
    l_playerName.setBounds(30, 150, 100, 30);
    add(l_playerName);
    t_playerName.setBounds(130, 150, 300, 30);
    add(t_playerName);
    b_startGame.setBounds(150, 200, 100, 50);
    add(b_startGame);
    b_startGame.addActionListener(
        e -> {
          new Thread(
                  () -> {
                    try {
                      ClientStartup.singleClient(
                          "./configs/world_mgr_config/my_world.xml",
                          acc_playerType.getText(),
                          t_playerName.getText(),
                          ClientInputAnalysis::defaultCmdAnalysis);
                    } catch (Exception exception) {
                      exception.printStackTrace();
                      JOptionPane.showConfirmDialog(
                          null, "运行失败，请联系开发人员。", "错误", JOptionPane.YES_OPTION); // 显示一个confirm的窗口
                      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                      this.setVisible(false);
                    }
                  })
              .start();
        });
  }

  public static void main(String[] args) throws Exception {
    DraftForm draftForm = new DraftForm();
    draftForm.setTitle("Loading resource");
    draftForm.setBounds(100, 100, 800, 800);
    Progress mainProgress = new Progress();
    Unit u = new Unit(0);
    mainProgress.setCallback(
        (total, current) -> {
          if (total == 0) {
            System.out.println("total = 0");
            return;
          }

          u.getAttribute().setBasicHp(total);
          u.getAttribute().setHp(current);
        },
        () -> {
          draftForm.close();
          try {
            PlayerSetter setter = new PlayerSetter();
            setter.setVisible(true);
          } catch (Exception e) {
            e.printStackTrace();
          }
        });
    Progress subProgress = new Progress();
    subProgress.setCallback(
        (total, current) -> {
          if (total == 0) {
            System.out.println("is 0");
            return;
          }

          u.getAttribute().setBasicMp(total);
          u.getAttribute().setMp(current);
        },
        () -> {});
    new Thread(
            () -> {
              try {
                InitManager.init(mainProgress, subProgress);
              } catch (Exception e) {
                e.printStackTrace();
              }
            })
        .start();

    draftForm.draw(
        graphics -> {
          ComplexAttribute attribute = u.getAttribute();
          graphics.setColor(Color.BLUE);
          graphics.fillRect(0, 100, (int) (600 * attribute.getHp() / attribute.getMaxHp()), 100);
          graphics.setColor(Color.GREEN);
          graphics.fillRect(0, 300, (int) (600 * attribute.getMp() / attribute.getMaxMp()), 100);
        });
  }
}
