package com.suremoon.gametest.real_game_test.action_dealers;

import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;

public class UseGoodsAd implements CmdActionItf {
    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
        UnitItf player = cmd.getOwner();
        var index = cmd.getTarget();
        var goods = player.getBag().get(index);
        goods.getUnitRem().interactive(goods, (PlayerItf) player, world, worldMgr, "{}");
        return true;
    }

    @Override
    public void preventLambda() {
    }
}
