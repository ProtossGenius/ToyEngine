package com.suremoon.game.configers.unit_resource.tools;

import java.io.File;

/** Created by Water Moon on 2018/1/15. */
public class NameReplace {
  protected String o_str, n_str;
  protected File file;

  public NameReplace(File f, String OldStr, String NewStr) {
    this.o_str = OldStr;
    this.n_str = NewStr;
    this.file = f;
  }

  public void replace() {
    replaceF(file);
  }

  protected void replaceF(File f) {
    if (!f.isDirectory()) {
      String name = f.getName();
      name = name.replace(o_str, n_str);
      f.renameTo(new File(f.getParent() + "/" + name));
      return;
    }
    File[] fls = f.listFiles();
    for (int i = 0; i < fls.length; ++i) {
      replaceF(fls[i]);
    }
  }
}
