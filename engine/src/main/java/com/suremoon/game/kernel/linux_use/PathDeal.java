package com.suremoon.game.kernel.linux_use;

import java.io.File;
import java.io.IOException;

public class PathDeal {
  public static String getLinuxPath(String path) throws IOException {
    return new File(path).getCanonicalPath().replaceAll("\\\\", "/");
  }

  public static File getLinuxFile(String path) throws IOException {
    return new File(getLinuxPath(path));
  }

  public static File getLinuxFile(File file) throws IOException {
    return getLinuxFile(file.getPath());
  }
}
