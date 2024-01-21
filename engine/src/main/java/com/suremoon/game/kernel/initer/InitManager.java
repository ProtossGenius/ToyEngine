package com.suremoon.game.kernel.initer;

import com.suremoon.game.kernel.initer.cmd_init.CmdListIniter;
import com.suremoon.game.kernel.initer.effect_init.EffectListIniter;
import com.suremoon.game.kernel.initer.goods_init.GoodsListIniter;
import com.suremoon.game.kernel.initer.state_init.StateListIniter;
import com.suremoon.game.kernel.initer.terrain_init.TerrainListIniter;
import com.suremoon.game.kernel.initer.unit_init.UnitListIniter;

import java.util.ArrayList;

/**
 * Created by Water Moon on 2018/3/5. @InitManager decide xml init
 */
public class InitManager {
    static volatile boolean isInited = false;

    public static void init() throws Exception {
        init(new Progress(), new Progress());
    }

    public static void init(Progress mainProgress, Progress subProgress) throws Exception {
        if (isInited) return;
        isInited = true;
        ArrayList<InitListItf> iLst = new ArrayList<>();
        iLst.add(new StateListIniter());
        iLst.add(new UnitListIniter());
        iLst.add(new CmdListIniter());
        //        iLst.add(new BuffListIniter());
        iLst.add(new TerrainListIniter());
        iLst.add(new EffectListIniter());
        iLst.add(new GoodsListIniter());
        //        int i = 0;
        mainProgress.setTotal(iLst.size());
        for (InitListItf initer : iLst) {
            //            System.out.println(i++);
            subProgress.reset();
            initer.init(subProgress);
            mainProgress.done();
        }
        mainProgress.allDone();
        //        new StateDoInit().init();
    }
}
