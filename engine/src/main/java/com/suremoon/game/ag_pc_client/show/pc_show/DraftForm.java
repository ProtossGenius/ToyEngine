package com.suremoon.game.ag_pc_client.show.pc_show;

import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;
import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;
import javax.swing.*;

/** Created by Water Moon on 2018/3/2. */
public class DraftForm extends JFrame {
  public DraftForm() {
    bi =
        new BufferedImage(
            GameConfiger.gc.DESIGN_SCREEN_WIDTH,
            GameConfiger.gc.DESIGN_SCREEN_HEIGHT,
            BufferedImage.TYPE_INT_ARGB);
    cache = bi.getGraphics();
  }

  BufferedImage bi;
  Graphics cache;
  protected AGSAdapter agsAdapter;
  protected String name;
  protected int intervalTime = 60;
  protected PointF direct = PointF.DIRECTION_ZERO;
  protected volatile boolean running = true;

  public void close() {
    running = false;
    setVisible(false);
  }

  public void draw(Consumer<Graphics> onUpdate) {

    setVisible(true);

    while (running) {
      long now = System.currentTimeMillis();
      cache.clearRect(
          0, 0, GameConfiger.gc.DESIGN_SCREEN_WIDTH, GameConfiger.gc.DESIGN_SCREEN_HEIGHT);
      onUpdate.accept(cache);
      getGraphics().drawImage(bi, 0, 0, getWidth(), getHeight(), null);
      try {
        Thread.sleep(System.currentTimeMillis() - now + 15);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public AGSAdapter getAgsAdapter() {
    return agsAdapter;
  }

  public void setAgsAdapter(AGSAdapter agsAdapter, String name) {
    this.agsAdapter = agsAdapter;
    this.name = name;
  }

  public void setPicAreaArray(PicAreaArray paa) {
    AGSAdapter agsAdapter = new AGSAdapter();
    agsAdapter.getResList().put(name = "Resource", paa);
    setAgsAdapter(agsAdapter, name);
  }

  public void setIntervalTime(int intervalTime) {
    this.intervalTime = intervalTime;
  }

  public void setDirect(PointF direct) {
    this.direct = direct;
  }
}
