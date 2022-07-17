package com.suremoon.editors.unit_editor;

import com.suremoon.game.configers.unit_resource.frame.UnitEditor;

/** Created by Water Moon on 2018/1/16. */
public class RunUnitEditor {
  public static void main(String[] args) throws InterruptedException {
    UnitEditor ue = new UnitEditor();
    ue.setVisible(true);
    ue.showLoop();
  }
}
