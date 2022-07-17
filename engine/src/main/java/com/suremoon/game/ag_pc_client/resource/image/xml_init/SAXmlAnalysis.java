package com.suremoon.game.ag_pc_client.resource.image.xml_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.ag_pc_client.resource.image.stand_ires.StandAnimation;
import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;
import com.suremoon.game.door.client.PicAreaArrayItf;

import java.util.Map;

/**
 * Created by Water Moon on 2018/4/11.
 */
public class SAXmlAnalysis extends ResXmlAnalysis{
    @Override
    public void analysis(ConfigInf conf, Map<String, PicAreaArrayItf> res, String base_path) throws Exception {
        StandAnimation sa = new StandAnimation(conf);
        res.put("Effect", sa.getPicAraArray());
    }

    @Override
    public String getName() {
        return "SA";
    }
}
