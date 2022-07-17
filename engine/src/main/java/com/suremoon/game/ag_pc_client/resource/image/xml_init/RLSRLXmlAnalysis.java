package com.suremoon.game.ag_pc_client.resource.image.xml_init;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.ag_pc_client.resource.image.ImageFactory;
import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;
import com.suremoon.game.ag_pc_client.resource.image.SMImage;
import com.suremoon.game.door.client.PicAreaArrayItf;

import java.awt.*;
import java.util.Map;

/**
 * Created by Water Moon on 2018/3/4.
 */
public class RLSRLXmlAnalysis extends ResXmlAnalysis{
    @Override
    public void analysis(ConfigInf conf, Map<String, PicAreaArrayItf> res, String base_path) throws Exception {
        String resName = conf.getConfig("ResName").getValue();
        SMImage smi = ImageFactory.getSMImage(resName);
        Color trans = new Color(HexToInt(( (((EasyConfig) conf.getConfig("trans_color"))).getValue())));
        smi.setTransparent(trans);
        String sRows = conf.getConfig("Rows").getValue(), sCols  = conf.getConfig("Cols").getValue(), sSize = conf.getConfig("Size").getValue(),
                sRow = conf.getConfig("Row").getValue(), sCol = conf.getConfig("Col").getValue();
        int rows = Integer.parseInt(sRows), cols =Integer.parseInt(sCols), size = Integer.parseInt(sSize), row = Integer.parseInt(sRow), col = Integer.parseInt(sCol);
        PicAreaArray paa = new PicAreaArray(smi, rows, cols, size, row, col);
        res.put("Effect", paa);
    }

    @Override
    public String getName() {
        return "RLSRL";
    }
}
