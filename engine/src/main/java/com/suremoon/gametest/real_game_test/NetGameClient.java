package com.suremoon.gametest.real_game_test;

import com.suremoon.game.ag_pc_client.show.pc_show.ClientStartup;
import com.suremoon.game.kernel.initer.InitManager;

public class NetGameClient {

  public static void main(String[] args) throws Exception {
    InitManager.init();
    ClientStartup.networkClient(
        "127.0.0.1", 7777, "configs/world_mgr_config/my_world.xml", "T_red_knight", "ItsMe", null);
  }
}
