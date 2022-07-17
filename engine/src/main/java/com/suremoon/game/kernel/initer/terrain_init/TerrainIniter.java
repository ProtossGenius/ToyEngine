package com.suremoon.game.kernel.initer.terrain_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.door.error.ErrorDeal;
import com.suremoon.game.door.infos.TerrainInformation;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.data.map.walkable.InnerJarTerrainWalkable;
import com.suremoon.game.kernel.data.map.walkable.TerrainOutInWalkable;
import com.suremoon.game.door.kernel.TerrainWalkableItf;
import com.suremoon.game.kernel.initer.Initer;
import com.suremoon.game.ag_pc_client.resource.image.xml_init.XmlAnalysisFactory;
import com.suremoon.game.kernel.initer.Progress;
import net.sf.json.JSONObject;


/**
 * Created by Water Moon on 2018/3/5.
 */
public class TerrainIniter extends Initer{
    String name;
    public TerrainIniter(String name){
        this.name = name;
    }
    @Override
    protected void do_init(ConfigInf ci) throws Exception {
        AGSAdapter adapter = new AGSAdapter();
        String resFile = ci.getConfig("ResFile").getValue();
        XmlAnalysisFactory.getXAF().Init(adapter.getResList(), resFile);
        String walkableCfg = ci.getConfig("Walkable").getValue();
        String isDecorate = ci.getConfig("IsDecorate").getValue();
        String taCol = ci.getConfig("AverageColor").getValue();
        String sInterval = ci.getConfig("Interval") == null ? "70" : ci.getConfig("Interval").getValue();
        if(sInterval == null || sInterval == ""){
            sInterval = "70";
        }
        int interval = Integer.parseInt(sInterval);
        ci.open("Buffs");
        ConfigInf cis[] = ci.listConfigs();
        if(cis == null) cis = new ConfigInf[0];
        String buffs[] = new String[cis.length];
        for(int i = 0; i < cis.length; ++i){
            buffs[i] = cis[i].getValue();
        }
        JSONObject jo = JSONObject.fromObject(walkableCfg);
        TerrainWalkableItf walkable;
        String type = (String) jo.get("Type");
        switch (type){
            case "WalkableOI":
                walkable = new TerrainOutInWalkable(jo);
                break;
            case "WInnerJar":
                walkable = new InnerJarTerrainWalkable(jo);
                break;
            default:
                ErrorDeal.putError("Error happened in TerrainInformation: 未定义的类型发生于构造函数，类型为：" + type);
                walkable = new TerrainOutInWalkable();
        }
        TerrainInformation ti = new TerrainInformation(adapter, buffs, walkable, isDecorate.toLowerCase().equals("true"), taCol, interval);
        TerrainInfManager.getTIM().putTerrainInf(IDManager.getID(name), ti);
    }
    protected boolean BoolTrans(String inp){
        inp.toLowerCase();
        return inp.equals("true");
    }
    @Override
    public String getXmlPath() {
        return "configs/terrain_config/" + name + "T.xml";
    }


}
