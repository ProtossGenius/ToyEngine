package com.suremoon.game.configers.map_resource.file_deal;

import com.suremoon.game.door.infos.MapInformation;
import com.suremoon.game.door.code_tools.Pair;
import com.suremoon.game.door.tools.CJDeal;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Water Moon on 2018/5/24.
 */
public class MapFileReader {
    String fileName;
    FileInputStream fis;
    MapInformation mi;
    Pair<Integer, Integer> tmap[][], adapters[];
    public MapFileReader(String fileName) throws IOException {
        long pos = 0;
        this.fileName = fileName;
        fis = new FileInputStream(fileName);
        byte[] tmpInt = new byte[4];
        fis.read(tmpInt);//read ============== check
        pos += 4;
        if(CJDeal.byte2int(tmpInt) != 0x775A521){
            throw new IOException("not map file type.");
        }
        mi = new MapInformation();
        byte[] tmp64b = new byte[64];
        fis.read(tmp64b);//read ============== version
        pos += 64;
        mi.setVersion(getJString(tmp64b));
        fis.read(tmpInt);//read ============== terrain config Num
        pos += 4;
        int terrainNums = CJDeal.byte2int(tmpInt);
        Map<Integer, String> list = mi.gettConfs();
        for (int i = 0; i < terrainNums; i++) {////read ============== terrainConfig Id List
            int tId;
            String terrainName;
            fis.read(tmpInt);
            fis.read(tmp64b);
            pos += 68;
            tId = CJDeal.byte2int(tmpInt);
            terrainName = getJString(tmp64b);
            list.put(tId, terrainName);
        }
        int cols, rows;
        fis.read(tmpInt);//read ============== cols
        mi.setCols(cols = CJDeal.byte2int(tmpInt));
        fis.read(tmpInt);//read ============== rows
        mi.setRows(rows = CJDeal.byte2int(tmpInt));
        fis.read(tmpInt);//read ============== width
        mi.setTwidth(CJDeal.byte2int(tmpInt));
        fis.read(tmpInt);//read ============== height
        mi.setTheight(CJDeal.byte2int(tmpInt));

        tmap = new Pair[cols][rows];
        pos += 16;
        mi.setTerrainStart(pos);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                int tid, ttype;
                fis.read(tmpInt);//read ============== typeId
                tid = CJDeal.byte2int(tmpInt);
                fis.read(tmpInt);//read ============== terrain type
                ttype = CJDeal.byte2int(tmpInt);
                pos += 8;
                tmap[i][j] = new Pair<>(tid, ttype);
            }
        }
        mi.setTmap(tmap);
        fis.read(tmpInt);// ================== read num of terrain adapters.
    }

    String getJString(byte[] inp) throws UnsupportedEncodingException {
        return CJDeal.getJString(inp);
    }

    public MapInformation getMi() {
        return mi;
    }

    public Pair<Integer, Integer>[][] getTmap() {
        return tmap;
    }

    public Pair<Integer, Integer>[] getAdapters() {
        return adapters;
    }
}
