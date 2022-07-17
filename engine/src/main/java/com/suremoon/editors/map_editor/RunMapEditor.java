package com.suremoon.editors.map_editor;

import com.suremoon.game.configers.map_resource.frames.MapEditor;
import java.awt.*;
import javax.swing.*;

public class RunMapEditor extends JFrame {
  JTextField t_cols = new JTextField("50"), t_rows = new JTextField("50");
  JLabel l_cols = new JLabel("cols: "), l_rows = new JLabel("rows: ");
  JButton b_openEditor = new JButton("open");

  public RunMapEditor() {
    super();
    setBounds(150, 110, 600, 600);
    setLayout(null);
    add(l_cols, 50, 50, 100, 50);
    add(l_rows, 50, 150, 100, 50);
    add(t_cols, 150, 50, 100, 50);
    add(t_rows, 150, 150, 100, 50);
    add(b_openEditor, 250, 250, 100, 50);
    b_openEditor.addActionListener(
        e -> {
          new Thread(
                  () -> {
                    try {
                      MapEditor me =
                          new MapEditor(toNumber(t_cols.getText()), toNumber(t_rows.getText()));
                      me.setVisible(true);
                      me.showLoop();
                    } catch (Exception exception) {
                      exception.printStackTrace();
                    }
                  })
              .start();
        });
    setResizable(false);
  }

  static int toNumber(String str) {
    int val = 50;
    try {
      val = Integer.parseInt(str);
    } catch (NumberFormatException e) {
      val = 50;
    }

    return val;
  }

  private void add(Component comp, int x, int y, int width, int height) {
    comp.setBounds(x, y, width, height);
    this.add(comp);
  }

  public static void main(String[] args) throws Exception {
    new RunMapEditor().setVisible(true);
  }
}
