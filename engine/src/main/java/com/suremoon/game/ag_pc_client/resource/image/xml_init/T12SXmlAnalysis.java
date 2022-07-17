package com.suremoon.game.ag_pc_client.resource.image.xml_init;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.ag_pc_client.resource.image.ImageFactory;
import com.suremoon.game.ag_pc_client.resource.image.PicArea;
import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;
import com.suremoon.game.ag_pc_client.resource.image.SMImage;
import com.suremoon.game.door.client.PicAreaArrayItf;
import java.util.Map;

/** Created by Water Moon on 2018/3/1. */
public class T12SXmlAnalysis extends ResXmlAnalysis {
  @Override
  public void analysis(ConfigInf conf, Map<String, PicAreaArrayItf> res, String base_path)
      throws Exception {
    String resName = ((EasyConfig) conf.getConfig("ResName")).getValue();
    String Length = ((EasyConfig) conf.getConfig("Length")).getValue();
    int n = Integer.parseInt(Length);
    SMImage smi = ImageFactory.getSMImage(base_path + "\\" + resName);
    for (int i = 0; i < 4; ++i) {
      for (int j = 0; j < 3; ++j) {
        PicArea pas[] = new PicArea[n];
        for (int l = 0; l < n; ++l) {
          pas[l] = new PicArea(smi, 4, 3 * n, i, j + l * 3);
        }
        PicAreaArray paa = new PicAreaArray(pas);
        res.put("Terrain" + Integer.toString(i) + Integer.toString(j), paa);
      }
    }
  }

  @Override
  public String getName() {
    return "T12S";
  }
}
