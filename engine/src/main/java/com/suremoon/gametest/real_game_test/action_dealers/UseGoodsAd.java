package com.suremoon.gametest.real_game_test.action_dealers;

import com.suremoon.game.door.configs.CommandReg;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.GoodsUseDetail;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.PlayerItf;

@CommandReg("CmdUseGoods")
public class UseGoodsAd implements CmdActionItf {
    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
        PlayerItf player = (PlayerItf) cmd.getOwner();
        var index = player.getSelectedIndex();
        if (outRange(index, player.getBag().size())) {
            return true;
        }
        var goods = player.getBag().get(index);
        if (goods == null) {
            return true;
        }

        goods.getUnitRem().interactive(goods, player, world, worldMgr, new GoodsUseDetail(index, (char) cmd.getTarget(), cmd.getTargetPoint()));

        return true;
    }

    private static boolean outRange(int index, int size) {
        return index < 0 || index >= size;
    }

    @Override
    public void preventLambda() {
    }
}
