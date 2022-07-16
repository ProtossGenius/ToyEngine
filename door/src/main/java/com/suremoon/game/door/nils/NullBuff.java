package com.suremoon.game.door.nils;

import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.BuffItf;
import com.suremoon.game.door.units_itf.UnitItf;

public class NullBuff implements BuffItf {
    @Override
    public void doCalc(WorldItf world, WorldMgrItf worldMgr) {

    }

    @Override
    public BuffItf setOwner(UnitItf u) {
        return this;
    }

    @Override
    public boolean isAlive() {
        return false;
    }
}
