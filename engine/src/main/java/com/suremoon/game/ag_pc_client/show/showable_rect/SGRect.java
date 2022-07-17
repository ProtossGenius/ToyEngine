package com.suremoon.game.ag_pc_client.show.showable_rect;

import com.suremoon.game.ag_pc_client.resource.image.PicArea;
import com.suremoon.game.ag_pc_client.resource.image.SMImage;
import com.suremoon.game.door.gometry.GRect;
import java.awt.*;
import java.awt.image.BufferedImage;

/** Created by Water Moon on 2018/3/18. */
@Deprecated
public class SGRect extends GRect {
  SMImage smi;
  Graphics gp;

  public Graphics getGraphics() {
    if (gp == null) gp = productGraphics();
    return gp;
  }

  protected Graphics productGraphics() {
    BufferedImage bi = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
    smi = new SMImage(bi);
    return bi.getGraphics();
  }

  /**
   * 按照缩放的方法将所有的部分全部显示在画板上
   *
   * @param gp
   */
  public void show(Graphics gp) {
    gp.drawImage(smi.getImg(), getX(), getY(), getWidth(), getHeight(), null);
  }

  /**
   * 部分绘制
   *
   * @param gp 画板
   * @param rect 部分区域。
   */
  public void show(Graphics gp, Rectangle rect) {
    PicArea pa = new PicArea(smi, rect);
    pa.show(gp, toRect());
  }

  public SMImage getSmi() {
    return smi;
  }

  public void setSmi(SMImage smi) {
    this.smi = smi;
  }

  public Graphics getGp() {
    return gp;
  }

  public void setGp(Graphics gp) {
    this.gp = gp;
  }
}
