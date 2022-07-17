package com.suremoon.game.ag_pc_client.show.showable_rect.control_item;

import com.suremoon.game.ag_pc_client.mkids.MKInpDeal;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GRCtrlItem extends CIRect {
  protected Component parent;
  protected MKInpDeal mkid;

  @Override
  public void keyTyped(KeyEvent e) {}

  @Override
  public void keyPressed(KeyEvent e) {}

  @Override
  public void keyReleased(KeyEvent e) {}

  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}

  @Override
  public void mouseDragged(MouseEvent e) {}

  @Override
  public void mouseMoved(MouseEvent e) {}

  public void setMkInpDeal(MKInpDeal mkid) {
    this.mkid = mkid;
  }

  public void setParent(Component parent) {
    this.parent = parent;
  }

  public void setCursor(int cursor) {
    if (parent != null) {
      parent.setCursor(Cursor.getPredefinedCursor(cursor));
    }
  }

  public void setCursor(Cursor cursor) {
    if (parent != null) {
      parent.setCursor(cursor);
    }
  }
}
