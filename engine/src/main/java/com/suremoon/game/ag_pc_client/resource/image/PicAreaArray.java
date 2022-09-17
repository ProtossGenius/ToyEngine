package com.suremoon.game.ag_pc_client.resource.image;

import com.suremoon.game.door.client.PicAreaArrayItf;
import com.suremoon.game.door.client.SMImageItf;
import java.awt.*;

/** Created by Water Moon on 2017/11/29. */
public class PicAreaArray implements PicAreaArrayItf {
  public SMImageItf[] pa;

  public PicAreaArray(String[] pan) {
    this(pan, null);
  }

  public PicAreaArray(SMImageItf[] smImageItf) {
    this.pa = smImageItf;
  }

  public PicAreaArray(SMImage smi, int rows, int cols) {
    this(smi, rows, cols, rows * cols);
  }

  public PicAreaArray(SMImage smi, int rows, int cols, int size) {
    this(smi, rows, cols, size, 0, 0);
  }

  public PicAreaArray(SMImage smi, int rows, int cols, int size, int row, int col) {
    if (size < 0) size = -size;
    int start_index = col + cols * row;
    if (size + start_index >= rows * cols) {
      size = rows * cols - start_index - 1;
    }
    pa = new SMImageItf[size];
    for (int i = 0; i < pa.length; ++i) {
      pa[i] = smi.subArea(rows, cols, (start_index + i) % cols, (start_index + i) / cols);
    }
  }

  public PicAreaArray(String pic, int rows, int cols, int size) {
    this(ImageFactory.getSMImage(pic), rows, cols, size);
  }

  public PicAreaArray(String pic, int rows, int cols) {
    this(pic, rows, cols, rows * cols);
  }

  public PicAreaArray(String pic, int rows, int cols, int size, int row, int col) {
    this(ImageFactory.getSMImage(pic), rows, cols, size, row, col);
  }

  public PicAreaArray(String[] pan, Color transe) {
    pa = new SMImage[pan.length];
    for (int i = 0; i < pa.length; i++) {
      SMImage smi = ImageFactory.getSMImage(pan[i]);
      if (transe != null) smi.setTransparent(transe);
      pa[i] = smi;
    }
  }

  @Override
  public SMImageItf getPicArea(int index) {
    if (index < 0) index *= -1;
    if (index >= pa.length) index %= pa.length;
    return pa[index];
  }

  @Override
  public int getLength() {
    return pa.length;
  }
}
