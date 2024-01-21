package com.suremoon.game.door.kernel;

import com.suremoon.game.door.units_itf.UnitItf;

// DieDo when unit die, what should do.
public interface DieDo {
    DieDo Default = new DieClearAfter30s();

    void Do(UnitItf it, WorldItf world, WorldMgrItf worldMgr);
}

class DieClearAfter30s implements DieDo {

    @Override
    public void Do(UnitItf it, WorldItf world, WorldMgrItf worldMgr) {
        if (it.isDie() && it.getState().getPassedTime() >= 3000L) {
            it.setDrop(true);
        }
    }
}
