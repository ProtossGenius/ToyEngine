package com.suremoon.game.ag_pc_client.resource.image;

import com.suremoon.game.door.client.PicAreaArrayItf;
import java.awt.*;

/** Created by Water Moon on 2017/11/29. */
public class PicAreaArray implements PicAreaArrayItf {
  public PicArea[] pa;

  public PicAreaArray(PicArea[] pa) {
    this.pa = pa;
  }

  public PicAreaArray(String[] pan) {
    this(pan, null);
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
    pa = new PicArea[size];
    for (int i = 0; i < pa.length; ++i) {
      pa[i] = new PicArea(smi, rows, cols, (start_index + i) % cols, (start_index + i) / cols);
    }
  }

  public PicAreaArray(PicArea ipa, int rows, int cols, int size, int row, int col) {
    if (size < 0) size = -size;
    int start_index = col + cols * row;
    if (size + start_index >= rows * cols) {
      size = rows * cols - start_index - 1;
    }
    pa = new PicArea[size];
    for (int i = 0; i < pa.length; ++i) {
      pa[i] =
          new PicArea(ipa.smImage, rows, cols, (start_index + i) % cols, (start_index + i) / cols);
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
    pa = new PicArea[pan.length];
    for (int i = 0; i < pa.length; i++) {
      SMImage smi = ImageFactory.getSMImage(pan[i]);
      if (transe != null) smi.setTransparent(transe);
      pa[i] = new PicArea(smi);
    }
  }

  @Override
  public PicArea getPicArea(int index) {
    if (index < 0) index *= -1;
    if (index >= pa.length) index %= pa.length;
    return pa[index];
  }

  @Override
  public int getLength() {
    return pa.length;
  }
}
