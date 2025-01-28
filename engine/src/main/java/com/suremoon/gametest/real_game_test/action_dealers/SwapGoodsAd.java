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
        var bag = player.getBag();
        if (outRange(targetPoint.x, bag.size())) {
            player.addMessage("error in CmdSwapGoods" + targetPoint.x);
            return true;
        }
        var goods = bag.get(targetPoint.x);
        if (outRange(targetPoint.y, bag.size())) {
            player.getBag().set(targetPoint.x, null);
            goods.setPos(player.getPos());
            world.addUnit(goods);
            return true;
        }
        var targetGoods = bag.get(targetPoint.y);
        bag.set(targetPoint.x, targetGoods);
        bag.set(targetPoint.y, goods);
        return true;
    }

    private static boolean outRange(int index, int size) {
        return index < 0 || index >= size;
    }

    @Override
    public void preventLambda() {
    }
}
