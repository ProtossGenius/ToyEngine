package com.suremoon.game.kernel.data.map.algorithm;

import java.awt.*;

public class Path {
  private Path next;
  private Point pos;

  public Path(Point pos) {
    this.pos = pos;
  }

  public Path(Path next, Point pos) {
    this.next = next;
    this.pos = pos;
  }

  public Path getNext() {
    return next;
  }

  public void setNext(Path next) {
    this.next = next;
  }

  public Point getPos() {
    return pos;
  }
}
