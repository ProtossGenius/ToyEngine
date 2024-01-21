package com.suremoon.game.ag_pc_client.resource.image.xml_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.door.client.PicAreaArrayItf;

import java.util.Map;

/**
 * Created by Water Moon on 2018/2/26.
 */
public abstract class ResXmlAnalysis {
    {
        XmlAnalysisFactory.xaf.putAnalysis(getName(), this);
    }

    public static int HexToInt(String num) {
        return Integer.parseInt(num.replaceAll("^0[x|X]", ""), 16);
    }

    public abstract void analysis(ConfigInf conf, Map<String, PicAreaArrayItf> res, String base_path)
            throws Exception;

    public abstract String getName();
}
