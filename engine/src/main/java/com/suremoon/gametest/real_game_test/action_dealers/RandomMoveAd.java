package com.suremoon.gametest.real_game_test.action_dealers;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;
import com.suremoon.game.kernel.initer.cmd_init.CmdInfManager;
import java.util.Random;

public class RandomMoveAd implements CmdActionItf {
  @Override
  public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
    UnitItf u = cmd.getOwner();
    PointF nowP = u.getFootPos();
    Random rand = GameConfiger.gc.getRandom();
    nowP.AddAs(new PointF(rand.nextInt() % 500, rand.nextInt() % 500));
    // if (state.getAGType() != IDManager.getID("paused")) {
    //     u.setState(StateInfManager.getSM().productState("paused"));
    //     cmd.remove();
    // }
    u.acceptCmd(
        CmdInfManager.CIM
            .productCommand(IDManager.getID("CmdWalk"), nowP.toPoint(), -1)
            .setAppendCmd(true));
    u.acceptCmd(
        CmdInfManager.CIM
            .productCommand(IDManager.getID("CmdRandMove"), nowP.toPoint(), -1)
            .setAppendCmd(true));
    return true;
  }

  @Override
  public void preventLambda() {}
}
