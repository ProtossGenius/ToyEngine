package com.suremoon.gametest.real_game_test.diedos;

import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.kernel.DieDo;
import com.suremoon.game.door.units_itf.UnitItf;

public class PlayerDieDo implements DieDo {
    @Override
    public void Do(UnitItf it, WorldItf world, WorldMgrItf worldMgr) {
        //Ê¬Ìå´¦Àí
        if (it.isDie() && it.getState().getPassedTime() >= 3000) {
            it.relive();
        }
    }

    public static PlayerDieDo Instance = new PlayerDieDo();
}
