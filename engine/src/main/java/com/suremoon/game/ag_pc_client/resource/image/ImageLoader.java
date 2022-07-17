package com.suremoon.game.ag_pc_client.resource.image;

import com.suremoon.game.kernel.linux_use.PathDeal;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/** Created by Water Moon on 2017/8/28. */
class ImageLoader {
  static Graphics2D g = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB).createGraphics();

  public SMImage getSMImage(String path) {
    return new SMImage(getImage(path));
  }

  private BufferedImage getImage(String path) {
    BufferedImage ret = null;
    try {
      File f = PathDeal.getLinuxFile(path);
      ret = ImageIO.read(f);
      init(ret);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return ret;
  }

  public void init(Image img) {
    g.drawImage(img, 0, 0, null);
  }
}
