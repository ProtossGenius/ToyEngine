package com.suremoon.game.kernel.initer.state_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.initer.InitListItf;
import com.suremoon.game.kernel.initer.Initer;
import com.suremoon.game.kernel.initer.Progress;

/**
 * Created by Water Moon on 2018/3/5.
 */
public class StateListIniter extends Initer implements InitListItf {

    @Override
    protected void do_init(ConfigInf ci) throws Exception{
        ci.open("States");
        ConfigInf cis[] = ci.listConfigs();
        StateInfManager sm = StateInfManager.sm;
        sm.resize(cis.length);
        for(int i = 0; i < cis.length; ++i){
            String name = cis[i].getValue("name");
            String bool = cis[i].getValue("isLoop");
            sm.put(name, bool);
            IDManager.getID(name);
        }
    }

    @Override
    public String getXmlPath() {
        return "configs/states.xml";
    }
}
