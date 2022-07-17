package com.suremoon.game.ag_pc_client.resource.image.xml_init;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;
import com.suremoon.game.ag_pc_client.resource.image.res_type.NADNResourceIniter;
import com.suremoon.game.door.client.PicAreaArrayItf;

import java.awt.*;
import java.util.Map;

/**
 * Created by Water Moon on 2018/2/26.
 */
public class NADNXmlAnalysis extends ResXmlAnalysis {

    @Override
    public void analysis(ConfigInf conf, Map<String, PicAreaArrayItf> res, String base_path) throws Exception{
        Color trans = new Color(HexToInt(( (((EasyConfig) conf.getConfig("trans_color"))).getValue())));
        String ext = ((EasyConfig)conf.getConfig("Ext")).getValue();
        conf.open("Actions");
        ConfigInf cis[] =  conf.listConfigs();
        for(ConfigInf ci:cis){
            String name =ci.getValue("name"), action_nums = ci.getValue("action_nums"), directs = ci.getValue("directs"), numlength = ci.getValue("numlength");
            NADNResourceIniter.initResource(base_path, trans, Integer.valueOf(directs), res, Integer.valueOf(numlength), name, Integer.valueOf(action_nums), ext);
        }
    }

    @Override
    public String getName() {
        return "NADN";
    }
}
