package com.suremoon.game.door.client;

import java.awt.*;

public interface SMImageItf {

  void show(Graphics gp, int x, int y, int width, int height, int trans);
  default void show(Graphics gp, int x, int y, int width, int height) {
    show(gp, x, y, width, height, 0xff);
  }

  default void show(Graphics gp, Rectangle rect) {
    show(gp, rect.x, rect.y, rect.width, rect.height);
  }
}
