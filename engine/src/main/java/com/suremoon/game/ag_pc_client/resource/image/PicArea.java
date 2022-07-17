package com.suremoon.game.ag_pc_client.resource.image;

import com.suremoon.game.door.client.PicAreaItf;
import java.awt.*;

/** Created by Water Moon on 2017/11/28. */
public class PicArea implements PicAreaItf {
  public SMImage smImage;
  public Point start, end;
  public int width, height;

  public PicArea(SMImage img, int rows, int cols, int row, int col) {
    smImage = img;
    width = smImage.getImageWidth() / cols;
    height = smImage.getImageHeight() / rows;
    if (row >= rows) row = rows - 1;
    if (col >= cols) col = cols - 1;
    start = new Point(col * width, row * height);
    end = new Point((col + 1) * width, (row + 1) * +height);
  }

  public PicArea(SMImage img, Point start, Point end) {
    smImage = img;
    this.start = start;
    this.end = end;
    if (end.x > smImage.getImageWidth()) end.x = smImage.getImageWidth();
    if (end.y > smImage.getImageHeight()) end.y = smImage.getImageHeight();
    width = end.x - start.x;
    height = end.y - start.y;
  }

  public PicArea(SMImage img, Rectangle rect) {
    this(img, rect.x, rect.y, rect.width, rect.height);
  }

  public PicArea(SMImage img) {
    smImage = img;
    this.start = new Point(0, 0);
    this.end = new Point(smImage.getImageWidth(), smImage.getImageHeight());
    width = end.x - start.x;
    height = end.y - start.y;
  }

  public PicArea(PicArea img, Point start, Point end) {
    smImage = img.smImage;
    this.start = new Point(start.x + img.start.x, start.y + img.start.y);
    this.end = new Point(end.x + img.end.x, end.y + img.end.y);
    if (end.x > img.end.x) end.x = img.end.x;
    if (end.y > img.end.y) end.y = img.end.y;
    width = end.x - start.x;
    height = end.y - start.y;
  }

  public PicArea(PicArea img, int rows, int cols, int x, int y) {
    smImage = img.smImage;
    width = img.width / cols;
    height = img.height / rows;
    if (x >= cols) x = cols - 1;
    if (y >= rows) y = rows - 1;
    start = new Point(x * width, y * height);
    end = new Point((x + 1) * +width, (y + 1) * height);
  }

  @Override
  public void show(Graphics gp, int x, int y, int width, int height) {
    gp.drawImage(
        smImage.getImg(), x, y, x + width, y + height, start.x, start.y, end.x, end.y, null);
  }
}
