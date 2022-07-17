package com.suremoon.game.configers.unit_resource.resource;

import com.springmoon.sm_form.config.EasyConfig;
import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;
import com.suremoon.game.ag_pc_client.resource.image.xml_init.ResXmlAnalysis;
import com.suremoon.game.ag_pc_client.resource.image.xml_init.XmlAnalysisFactory;
import com.suremoon.game.door.client.PicAreaArrayItf;

import java.util.Map;

import static com.suremoon.game.kernel.linux_use.PathDeal.getLinuxFile;

/**
 * Created by Water Moon on 2017/12/1.
 */
public class UnitResInit {

    public static void Init(Map<String, PicAreaArrayItf> res, String xml_path) throws Exception{
        String base_path = getLinuxFile(xml_path).getParent();
        EasyConfig ec = new EasyConfig(xml_path);
        String type = ((EasyConfig)ec.getConfig("Type")).getValue();
        XmlAnalysisFactory xaf = XmlAnalysisFactory.getXAF();
        ResXmlAnalysis rxa = xaf.getRXA(type);
        rxa.analysis(ec, res, base_path);
    }
    static int toInt(String num){
        return Integer.parseInt(num.replaceAll("^0[x|X]", ""), 16);
    }
}
