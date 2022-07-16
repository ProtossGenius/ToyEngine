package com.suremoon.game.door.mods;

import com.suremoon.game.door.kernel.WorldItf;

public interface WorldModItf {
    void Do(WorldItf gr);
    String ModName();
}
