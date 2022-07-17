package com.suremoon.game.kernel.initer.unit_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.ag_pc_client.resource.image.xml_init.XmlAnalysisFactory;
import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.infos.UnitInformation;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.kernel.initer.Initer;
import com.suremoon.game.kernel.initer.Progress;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Water Moon on 2018/3/5.
 */
public class UnitIniter extends Initer {
    String name;
    public UnitIniter(String name){
        this.name = name;
    }
    @Override
    protected void do_init(ConfigInf ci) throws Exception {
        String resFile = ci.getConfig("ResFile").getValue();
        AGSAdapter shower = new AGSAdapter();
        XmlAnalysisFactory xaf = XmlAnalysisFactory.xaf;
        xaf.Init(shower.getResList(), resFile);
        int width = Integer.parseInt(ci.getConfig("Width").getValue()), height = Integer.parseInt(ci.getConfig("Height").getValue());
        String fx = ci.getConfig("FootPos").getValue("x"), fy = ci.getConfig("FootPos").getValue("y");
        String showName = ci.getConfig("ShowName").getValue();
        PointF footPos = new PointF(Double.parseDouble(fx), Double.parseDouble(fy));
        ci.open("States");
        ConfigInf cis[] = ci.listConfigs();
        HashMap<String, String> saMap = new HashMap<>();
        Map state2action = shower.state2action;
        for(int i = 0; i < cis.length; ++i){
            if(cis[i] == null)continue;
            String stateName = cis[i].getValue("stateName");
            String actionName = cis[i].getValue("actionName");
            state2action.put(stateName, actionName);
            saMap.put(stateName, actionName);
        }
        String sptState[] = new String[saMap.size()];
        int ptr = 0;
        for(Map.Entry<String, String> it: saMap.entrySet()){
            sptState[ptr++] = it.getKey();
        }
        UnitInformation ui = new UnitInformation(shower, showName, width, height, sptState, footPos, saMap);
        UnitInfManager uim = UnitInfManager.UIM;
        uim.putUnitInf(name, ui);
    }

    @Override
    public String getXmlPath() {
        return "./configs/unit_config/" + name + ".xml";
    }

}
