package com.suremoon.game.ag_pc_client.controller;

import java.awt.*;
import java.awt.event.*;

public class SMMouse implements MouseListener, MouseMotionListener, MouseWheelListener {
  int mouseKey[] = new int[10]; // because button1 = 1-->button3 = 3. the size should be 5
  Point lastPos;
  boolean isInArea = true;

  public SMMouse() {
    for (int i = 0; i < mouseKey.length; i++) {
      mouseKey[i] = 0;
    }
    lastPos = new Point(0, 0);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    lastPos = e.getPoint();
    isInArea = true;
  }

  @Override
  public void mousePressed(MouseEvent e) {
    lastPos = e.getPoint();
    mouseKey[e.getButton()] = 1;
    isInArea = true;
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    lastPos = e.getPoint();
    mouseKey[e.getButton()] = 0;
    isInArea = true;
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    lastPos = e.getPoint();
    isInArea = true;
  }

  @Override
  public void mouseExited(MouseEvent e) {
    lastPos = e.getPoint();
    isInArea = false;
  }

  @Override
  public void mouseDragged(MouseEvent e) {
    lastPos = e.getPoint();
    isInArea = true;
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    lastPos = e.getPoint();
    isInArea = true;
  }

  @Override
  public void mouseWheelMoved(MouseWheelEvent e) {
    lastPos = e.getPoint();
    mouseKey[e.getButton()] = e.getWheelRotation();
    // TODO about this should be update.
  }
}
