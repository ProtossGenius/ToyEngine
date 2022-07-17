package com.suremoon.gametest;

import com.suremoon.game.configers.unit_resource.config_creater.CreateNADNConfig;
import java.io.File;

/** Created by Water Moon on 2018/4/9. */
public class HumanXmlInit {
  public static void main(String[] args) {
    String path = "F:\\BaiduYunDownload\\www.reinerstilesets.de\\Human_Unzip";
    File f = new File(path);
    File[] lf = f.listFiles();
    for (int i = 0; i < lf.length; ++i) {
      System.out.print("dealing " + (i + 1) + " of " + lf.length + "...");
      if (lf[i].isDirectory()) {
        try {
          new CreateNADNConfig(lf[i], 8).toXml();
        } catch (Exception e) {
          System.out.println(lf[i].getName());
        }
      }
      System.out.println("...");
    }
  }
}
