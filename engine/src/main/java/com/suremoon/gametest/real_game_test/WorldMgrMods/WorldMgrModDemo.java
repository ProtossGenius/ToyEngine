package com.suremoon.gametest.real_game_test.WorldMgrMods;

import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.mods.WorldMgrModItf;

public class WorldMgrModDemo implements WorldMgrModItf {
  @Override
  public void Do(WorldMgrItf wm) {}

  @Override
  public String ModName() {
    return "WorldMgrModDemo";
  }
}
