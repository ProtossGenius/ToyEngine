package com.suremoon.game.kernel.initer.unit_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.initer.InitListItf;
import com.suremoon.game.kernel.initer.Initer;
import com.suremoon.game.kernel.initer.Progress;

/**
 * Created by Water Moon on 2018/3/5.
 */
public class UnitListIniter implements InitListItf {
    @Override
    public void init(Progress progress) throws Exception {
        loadList("configs/unit_config", ".xml", name -> new UnitIniter(name).init(progress), progress);
    }
}
