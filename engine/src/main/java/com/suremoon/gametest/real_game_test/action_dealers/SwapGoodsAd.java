package com.suremoon.gametest.real_game_test.action_dealers;

import com.suremoon.game.door.configs.CommandReg;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.PlayerItf;

@CommandReg("CmdSwapGoods")
public class SwapGoodsAd implements CmdActionItf {
    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
        PlayerItf player = (PlayerItf) cmd.getOwner();
        var targetPoint = cmd.getTargetPoint();
        var begin = targetPoint.x;
        var end = targetPoint.y;
        player.getBagManager().swap(begin, end, world);
        return true;
    }

    @Override
    public void preventLambda() {
    }
}
