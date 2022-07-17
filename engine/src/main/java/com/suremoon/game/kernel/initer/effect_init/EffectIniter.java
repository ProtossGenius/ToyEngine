package com.suremoon.game.kernel.initer.effect_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.infos.EffectInformation;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.kernel.actions.InnerJarEffectAction;
import com.suremoon.game.door.kernel.EffectActionItf;
import com.suremoon.game.kernel.initer.Initer;
import com.suremoon.game.ag_pc_client.resource.image.xml_init.XmlAnalysisFactory;
import com.suremoon.game.kernel.initer.Progress;

/**
 * Created by Water Moon on 2018/4/11.
 */
public class EffectIniter extends Initer{
    String eName;
    public EffectIniter(String name) {
        this.eName = name;
    }

    @Override
    protected void do_init(ConfigInf ci) throws Exception {//
        AGSAdapter shower = new AGSAdapter();
        String resPath = ci.getConfig("ResPath").getValue();
        String fx = ci.getConfig("FootPos").getValue("x"), fy = ci.getConfig("FootPos").getValue("y");
        PointF footPos = new PointF(Double.parseDouble(fx), Double.parseDouble(fy));
        XmlAnalysisFactory xaf = XmlAnalysisFactory.xaf;
        xaf.Init(shower.getResList(), resPath);
        int width = Integer.parseInt(ci.getConfig("Width").getValue()), height = Integer.parseInt(ci.getConfig("Height").getValue());
        String isLoop = ci.getConfig("IsLoop").getValue().toLowerCase();
        String eaInf = ci.getConfig("EffectActionInf").getValue().trim();
        String cInterval = ci.getConfig("Interval").getValue();
        int interval =  60;
        if (cInterval != ""){
            interval = Integer.parseInt(cInterval);
        }
        EffectActionItf ea = EffectActionItf.Null;
        if (!eaInf.equals("")){
            ea = new InnerJarEffectAction(eaInf);
        }
        EffectInformation ei = new EffectInformation(shower, ea,  width, height, interval, footPos,isLoop.equals("true"));
        EffectInfManager.EIM.putEffectInf(eName, ei);
    }

    @Override
    public String getXmlPath() {
        return "configs/effect_config/" + eName + ".xml";
    }
}
