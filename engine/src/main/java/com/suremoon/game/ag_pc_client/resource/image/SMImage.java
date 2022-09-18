package com.suremoon.game.ag_pc_client.resource.image;

import com.suremoon.game.door.client.SMImageItf;
import java.awt.*;
import java.awt.image.*;

/** Created by Water Moon on 2017/8/28. */
public class SMImage implements SMImageItf {
  public Image getImg(int trans) {
    if (trans == 0xff) return img;
    BufferedImage tImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
    for (int i = 0; i < img.getWidth(); ++i) {
      for (int j = 0; j < img.getHeight(); ++j) {
        int rgb = img.getRGB(i, j);
        if ((rgb | 0x00ffffff) == 0x00ffffff) continue;
        tImg.setRGB(i, j, trans << 24 | (rgb & 0x00ffffff));
        // Mark the alpha bits as zero - transparent
      }
    }
    return tImg;
  }

  public int getImageWidth() {
    return image_width;
  }

  public int getImageHeight() {
    return image_height;
  }

  protected BufferedImage img;
  protected int image_width;
  protected int image_height;

  SMImage() {
    this.img = null;
    this.image_height = 0;
    this.image_width = 0;
  }

  public SMImage(BufferedImage bi) {
    image_width = bi.getWidth();
    image_height = bi.getHeight();
    img = bi;
  }

  public void setTransparent(Color transparent) {
    img = makeColorTransparent(img, transparent);
  }

  public SMImage(BufferedImage bi, Color transparent) {
    image_width = bi.getWidth();
    image_height = bi.getHeight();
    img = bi;
    setTransparent(transparent);
  }

  public static int color_range = 15;

  public static boolean same(int a, int b) {
    return a > b ? a - b <= color_range : b - a <= color_range;
  }

  public static BufferedImage makeColorTransparent(BufferedImage im, final Color color) {
    if (im == null) {
      return null;
    }

    BufferedImage img =
        new BufferedImage(im.getWidth(), im.getHeight(), BufferedImage.TYPE_INT_ARGB);
    for (int i = 0; i < im.getWidth(); ++i) {
      for (int j = 0; j < im.getHeight(); ++j) {
        int rgb = im.getRGB(i, j);
        if (ColorSame(new Color(rgb), color)) {
          img.setRGB(i, j, 0x00FFFFFF);
          // Mark the alpha bits as zero - transparent
        } else {
          // nothing to do
          img.setRGB(i, j, rgb);
        }
      }
    }

    return img;
  }

  public static boolean ColorSame(Color a, Color b) {
    //        return a.equals(b);
    return NumberSame(a.getRed(), b.getRed(), color_range)
        && NumberSame(a.getBlue(), b.getBlue(), color_range)
        && NumberSame(a.getGreen(), b.getGreen(), color_range);
  }

  public static boolean NumberSame(int a, int b, int c) {
    return a > b ? a - b <= c : b - a <= c;
  }

  @Override
  public void show(Graphics gp, int x, int y, int width, int height, int trans) {
    gp.drawImage(
        getImg(trans),
        x,
        y,
        x + width,
        y + height,
        0,
        0,
        getImageHeight(),
        getImageWidth(),
        null);
  }

  public SMImageItf subArea(int rows, int cols, int row, int col) {
    int width = getImageWidth() / cols;
    int height = getImageHeight() / rows;
    if (row >= rows) row = rows - 1;
    if (col >= cols) col = cols - 1;
    Point start = new Point(col * width, row * height);
    Point end = new Point((col + 1) * width, (row + 1) * height);

    return subArea(start, end);
  }

  public SMImageItf subArea(Point start, Point end) {
    end = new Point(Math.min(end.x, getImageWidth()), Math.min(end.y, getImageHeight()));
    BufferedImage bi = new BufferedImage(end.x - start.x, end.y - start.y, BufferedImage.TYPE_4BYTE_ABGR);
    for (int i = start.x; i < end.x; ++i) {
      for (int j = start.y; j < end.y; ++j) {
        bi.setRGB(i-start.x, j - start.y, img.getRGB(i, j));
      }
    }
    return new SMImage(bi);
  }
}
