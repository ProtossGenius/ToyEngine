package com.suremoon.game.door.mods;

import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.server.ServerLoaderItf;

public interface ServerModItf {
    void Do(WorldMgrItf mgr, ServerLoaderItf loader);
}
