package com.suremoon.gametest.real_game_test.action_dealers;

import com.suremoon.game.door.configs.CommandReg;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.EffectItf;

@CommandReg("CmdFlowerFire")
public class FlowerFireAd implements CmdActionItf {
    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
        GameMapItf gm = world.getGameMap();
        EffectItf ne = world.productEffect(IDManager.getID("FlowerFire"), cmd.getOwner());
        ne.setFootPosPro(new PointF(0.5, 0.5));
        ne.setPutPos(cmd.getTargetPoint());
        gm.getEffectMgr().addEffect(ne);
        return true;
    }

    @Override
    public void preventLambda() {
    }
}
