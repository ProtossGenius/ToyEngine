package com.suremoon.game.door.kernel;

import com.suremoon.game.door.units_itf.UnitItf;

// DieDo when unit die, what should do.
public interface DieDo {
    void Do(UnitItf it, WorldItf world, WorldMgrItf worldMgr);
    DieDo Default = new DieClearAfter30s();

}

class DieClearAfter30s implements DieDo{

    @Override
    public void Do(UnitItf it, WorldItf world, WorldMgrItf worldMgr) {
        if (it.isDie() && it.getState().getPassedTime() >= 3000L) {
          it.setDrop(true);
          world.getGameMap().getUnitMgr().removeUnit(it);
        }
    }
}
