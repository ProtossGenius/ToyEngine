package com.suremoon.gametest.real_game_test.action_dealers;

import com.suremoon.game.door.configs.CommandReg;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.PlayerItf;

@CommandReg("CmdSelectGoods")
public class SelectGoodsAd implements CmdActionItf {
    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
        PlayerItf player = (PlayerItf) cmd.getOwner();
        var index = cmd.getTarget();
        if (outRange(index, 10)) {
            return true;
        }
        player.setSelectedIndex(index);
        return true;
    }

    private static boolean outRange(int index, int size) {
        return index < 0 || index >= size;
    }

    @Override
    public void preventLambda() {
    }
}
