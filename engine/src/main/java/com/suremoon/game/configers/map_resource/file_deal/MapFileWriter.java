package com.suremoon.game.configers.map_resource.file_deal;

import com.suremoon.game.door.code_tools.Pair;
import com.suremoon.game.door.infos.MapInformation;
import com.suremoon.game.door.tools.CJDeal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

/** Created by Water Moon on 2018/5/24. */
public class MapFileWriter {
  FileOutputStream fos;
  String filePath;

  public MapFileWriter(String filePath, MapInformation mi) throws IOException {
    this(filePath);
    write(mi);
  }

  public MapFileWriter(String filePath) {
    this.filePath = filePath;
  }

  public void write(MapInformation mi) throws IOException {
    File f = new File(filePath);
    f.getParentFile().mkdirs();
    fos = new FileOutputStream(filePath);
    fos.write(CJDeal.int2byte(0x775A521)); // write ============== check
    fos.write(getb64Str(mi.getVersion())); // write ============== version
    Map<Integer, String> confList = mi.gettConfs();
    fos.write(CJDeal.int2byte(confList.size())); // write terrain config Num.
    for (Map.Entry<Integer, String> itor :
        confList.entrySet()) { // write ============== terrain config
      fos.write(CJDeal.int2byte(itor.getKey()));
      fos.write(getb64Str(itor.getValue()));
    }
    fos.write(CJDeal.int2byte(mi.getCols())); // write ============== cols
    fos.write(CJDeal.int2byte(mi.getRows())); // write ============== rows
    fos.write(CJDeal.int2byte(mi.getTwidth())); // write ============== width
    fos.write(CJDeal.int2byte(mi.getTheight())); // write ============== height

    Pair<Integer, Integer> tmap[][] = mi.getTmap();
    for (int i = 0; i < mi.getCols(); i++) {
      for (int j = 0; j < mi.getRows(); j++) {
        Pair<Integer, Integer> it = tmap[i][j];
        fos.write(CJDeal.int2byte(it.getKey())); // write ==================== typeId
        fos.write(CJDeal.int2byte(it.getValue())); // write ==================== terrain type
      }
    }
    fos.flush();
    fos.close();
  }

  byte[] getb64Str(String str) {
    byte[] tmp = CJDeal.string2bytes(str);
    byte[] res = new byte[64];
    int ptr = 0;
    for (int i = 4; i < tmp.length; ++i) {
      res[ptr++] = tmp[i];
    }
    return res;
  }
}
