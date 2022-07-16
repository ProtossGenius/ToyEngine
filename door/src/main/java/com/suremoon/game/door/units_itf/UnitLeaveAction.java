package com.suremoon.game.door.units_itf;

import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;

public interface UnitLeaveAction {
    void AfterLeave(UnitItf unit, WorldItf world, WorldMgrItf worldMgr);
    UnitLeaveAction Null = (a, b, c)->{};
}
