package com.suremoon.gametest.res_configer;

import com.suremoon.game.ag_pc_client.resource.image.SMImage;
import com.suremoon.game.configers.JPicture;
import com.suremoon.game.configers.unit_resource.resource.UnitResInit;
import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.gometry.GRect;
import com.suremoon.game.door.gometry.PointF;
import java.awt.*;
import javax.swing.*;

/** Created by Water Moon on 2018/1/13. */
public class TestURI {
  public static void main(String[] args) throws Exception {
    AGSAdapter agsAdapter = new AGSAdapter();
    UnitResInit.Init(agsAdapter.getResList(), "resources/T_lumberjack/config.xml");
    JFrame jf = new JFrame("test uri");
    jf.setVisible(true);
    jf.setBounds(100, 100, 500, 600);
    jf.setLayout(null);
    JPicture jp = new JPicture();
    jp.setBounds(0, 0, 500, 500);
    jf.add(jp);
    long start = System.currentTimeMillis();

    while (true) {
      SMImage smi;
      jp.getGraphics().fillRect(0, 0, 500, 500);
      agsAdapter.show(
          jp.getGraphics(),
          "lumberjack with axe 96x bitmaps\\talking",
          new GRect(0, 0, 500, 500),
          new PointF(0, 1),
          System.currentTimeMillis() - start,
          50,
          true,
          new Point(0, 0));
      jp.refresh();
      Thread.sleep(15);
    }
  }
}
