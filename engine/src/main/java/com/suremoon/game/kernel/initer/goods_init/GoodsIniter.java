package com.suremoon.game.kernel.initer.goods_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.ag_pc_client.resource.image.PicAreaArray;
import com.suremoon.game.ag_pc_client.resource.image.xml_init.ResXmlAnalysis;
import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.infos.UnitInformation;
import com.suremoon.game.kernel.initer.Initer;
import com.suremoon.game.kernel.initer.unit_init.UnitInfManager;

import java.awt.*;

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
        AGSAdapter shower = new AGSAdapter();
        shower.setIsGoods(true);
//        XmlAnalysisFactory xaf = XmlAnalysisFactory.xaf;
        int width = Integer.parseInt(ci.getConfig("Width").getValue()),
                height = Integer.parseInt(ci.getConfig("Height").getValue());
        var transColor = ci.getConfig("trans_color").getValue();
        String fx = ci.getConfig("FootPos").getValue("x"), fy = ci.getConfig("FootPos").getValue("y");
        String name = ci.getConfig("name").getValue();
        PointF footPos = new PointF(Double.parseDouble(fx), Double.parseDouble(fy));
        ci.open("States");
        ConfigInf[] stateCfgs = ci.listConfigs();

        var resMap = shower.getResList();

        var saMap = shower.getState2action();
        saMap.put(GoodsStatus.GROUND_INACT, "ground_inact");
        saMap.put(GoodsStatus.GROUND_ACT, "ground_act");
        saMap.put(GoodsStatus.BAG_INACT, "bag_inact");
        saMap.put(GoodsStatus.BAG_ACT, "bag_act");
        for (int i = 0; i < stateCfgs.length; ++i) {
            if (stateCfgs[i] == null) continue;
            String stateName = stateCfgs[i].getValue("stateName");
            String resource = stateCfgs[i].getValue("resource");
            // TODO： 现在只支持图片将来支持其他格式
            resMap.put(stateName, new PicAreaArray(new Color(ResXmlAnalysis.HexToInt(transColor)), resource));
        }

        UnitInfManager uim = UnitInfManager.UIM;
        uim.putUnitInf("goods/" + name, new UnitInformation(shower, name, width, height, new String[]{}, footPos, saMap));
    }

    @Override
    public String getXmlPath() {
        return "./configs/goods_config/" + name + ".xml";
    }
}
