package com.suremoon.game.configers.unit_resource.show;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.configers.unit_resource.resource.UnitResInit;
import com.suremoon.game.door.client.AGSAdapter;
import java.util.TreeMap;

/** Created by Water Moon on 2018/1/27. */
public class AGSAdapterInit {

  public static AGSAdapter AGSAdapterInitByUConf(String config) throws Exception {
    AGSAdapter agsAdapter = new AGSAdapter();
    EasyConfig ec = new EasyConfig(config);
    String resConfig = ec.getConfig("ResFile").getValue();
    UnitResInit.Init(agsAdapter.getResList(), resConfig);
    ec.open("States");
    ConfigInf[] states = ec.listConfigs();
    TreeMap<String, String> ts = agsAdapter.getState2action();
    if (states != null) {
      for (int i = 0; i < states.length; ++i) {
        String an = states[i].getValue("actionName"), sn = states[i].getValue("stateName");
        ts.put(sn, an);
      }
    }
    return agsAdapter;
  }
}
