package com.suremoon.game.kernel.initer.terrain_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.initer.InitListItf;
import com.suremoon.game.kernel.initer.Initer;
import com.suremoon.game.kernel.initer.Progress;

/**
 * Created by Water Moon on 2018/3/5.
 */
public class TerrainListIniter extends Initer implements InitListItf {
    @Override
    protected void do_init(ConfigInf ci) throws Exception {
        ci.open("Terrains");
        ConfigInf cis[] = ci.listConfigs();
        for(int i = 0; i < cis.length; ++i){
            String name = cis[i].getValue("name");
            new TerrainIniter(name).init(Progress.NIL);
            IDManager.getID(name);
        }
    }

    @Override
    public String getXmlPath() {
        return "configs/terrains.xml";
    }
}
