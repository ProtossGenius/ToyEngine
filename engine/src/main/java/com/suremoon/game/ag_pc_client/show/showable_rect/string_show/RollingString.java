package com.suremoon.game.ag_pc_client.show.showable_rect.string_show;

import java.awt.*;

public class RollingString {
  private StringShow stringShow;
  int yChange = 0;

  public RollingString(StringShow ss) {
    this.stringShow = ss;
  }

  public void insertString(String str) {
    synchronized (stringShow) {
      if (stringShow.size() == 0) {
        stringShow.insertString("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      }
      stringShow.insertString(str);
    }
  }

  public void drawOn(Graphics cache) {
    if (stringShow.drawOn(cache, yChange)) --yChange;
  }
}
