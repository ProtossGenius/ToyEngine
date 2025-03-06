package com.suremoon.suremoon.mods.world_mgr;

import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.mods.WorldMgrModItf;
import com.suremoon.suremoon.initers.PlayerInfoInit;

public class WorldMgrMod implements WorldMgrModItf {
    @Override
    public void Do(WorldMgrItf worldMgrItf) {
        worldMgrItf.setPlayerInitItf(new PlayerInfoInit());
    }

    @Override
    public String ModName() {
        return null;
    }
}
