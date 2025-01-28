package com.suremoon.game.door.kernel;

import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;

public interface UnitRemItf {
    UnitRemItf Null = new UnitRemNothing();

    void underAttack(UnitItf self, UnitItf attacker, double hurt);

    void doCalc(UnitItf self, WorldItf world, WorldMgrItf worldMgr);

    void interactive(UnitItf self, PlayerItf playerItf, WorldItf world, WorldMgrItf worldMgr, Object input);
}

class UnitRemNothing implements UnitRemItf {

    @Override
    public void underAttack(UnitItf owner, UnitItf attacker, double hurt) {
    }

    @Override
    public void doCalc(UnitItf unit, WorldItf world, WorldMgrItf worldMgr) {
    }

    @Override
    public void interactive(UnitItf self, PlayerItf playerItf, WorldItf world, WorldMgrItf worldMgr, Object input) {
    }
}
