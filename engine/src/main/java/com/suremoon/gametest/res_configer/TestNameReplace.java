package com.suremoon.gametest.res_configer;

import com.suremoon.game.configers.unit_resource.tools.NameReplace;
import java.io.File;

/** Created by Water Moon on 2018/1/15. */
public class TestNameReplace {
  public static void main(String[] args) {
    NameReplace np =
        new NameReplace(
            new File("F:\\BaiduYunDownload\\www.reinerstilesets.de\\Human_Unzip"),
            "l剈ft",
            "Walking");
    np.replace();
  }
}
