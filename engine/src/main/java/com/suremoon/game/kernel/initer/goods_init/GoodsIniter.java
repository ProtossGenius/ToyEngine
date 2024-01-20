package com.suremoon.game.kernel.initer.goods_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.kernel.initer.Initer;

/**
 * Created by Water Moon on 2018/3/5.
 */
public class GoodsIniter extends Initer {
    String name;

    public GoodsIniter(String name) {
        this.name = name;
    }

    @Override
    protected void do_init(ConfigInf ci) throws Exception {
//        AGSAdapter shower = new AGSAdapter();
//        XmlAnalysisFactory xaf = XmlAnalysisFactory.xaf;
//        int width = Integer.parseInt(ci.getConfig("Width").getValue()),
//                height = Integer.parseInt(ci.getConfig("Height").getValue());
//        String fx = ci.getConfig("FootPos").getValue("x"), fy = ci.getConfig("FootPos").getValue("y");
//        String name = ci.getConfig("name").getValue();
//        PointF footPos = new PointF(Double.parseDouble(fx), Double.parseDouble(fy));
//        ci.open("States");
//        ConfigInf[] stateCfgs = ci.listConfigs();
//
//
//        for (int i = 0; i < stateCfgs.length; ++i) {
//            if (stateCfgs[i] == null) continue;
//            String stateName = stateCfgs[i].getValue("stateName");
//            String resource = stateCfgs[i].getValue("resource");
//            if (resource.endsWith(".xml")) {
//
//            }
//        }
//
//        GoodsInfManager uim = GoodsInfManager.UIM;
//        uim.putUnitInf(name, uim);
    }

    @Override
    public String getXmlPath() {
        return "./configs/goods_config/" + name + ".xml";
    }
}
