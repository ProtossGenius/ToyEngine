package com.suremoon.gametest.real_game_test;

import com.suremoon.game.ag_pc_client.show.pc_show.ClientStartup;
import com.suremoon.game.door.client.ActionData;
import com.suremoon.game.door.client.CmdAnalysisItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.data.command_analysis.CmdAnalysis;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.kernel.initer.InitManager;
import com.suremoon.game.kernel.initer.unit_init.UnitInfManager;
import com.suremoon.game.netabout.Player;

/**
 * Created by Water Moon on 2018/2/8.
 */
public class Run3AGame {

    public static void main(String[] args) throws Exception {
        InitManager.init();
        ClientStartup.singleClient("configs/world_mgr_config/my_world.xml", "T_red_knight", "头号玩家", null);
    }
}
