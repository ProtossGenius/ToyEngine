package com.suremoon.game.kernel.initer.effect_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.initer.InitListItf;
import com.suremoon.game.kernel.initer.Initer;
import com.suremoon.game.kernel.initer.Progress;

import java.io.File;

/**
 * Created by Water Moon on 2018/4/11.
 */
public class EffectListIniter implements InitListItf {
    @Override
    public void init(Progress progress) throws Exception {
        loadList("configs/effect_config", ".xml", name -> new EffectIniter(name).init(progress), progress);
    }
}
