package com.suremoon.game.door.mods;

import com.suremoon.game.door.kernel.WorldMgrItf;

public interface WorldMgrModItf {
    void Do(WorldMgrItf wm);
    String ModName();
}
