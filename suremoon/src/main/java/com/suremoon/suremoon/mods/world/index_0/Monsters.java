package com.suremoon.suremoon.mods.world.index_0;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.UnitRemItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.netabout.MessageUtil;
import com.suremoon.suremoon.consts.units;

public class Monsters {
    public static void Join(WorldItf world) {
        PointF center = new PointF(2500.0, 2500.0);
        UnitItf monster = world.productUnit(units.blueclub);
        monster.setSize(250, 250);
        monster.getAttribute().setHp(99999999);
        monster.setPos(center);
        monster.setUnitRem(new UnitRemItf() {
            @Override
            public void underAttack(UnitItf unitItf, UnitItf unitItf1, double v) {
                PlayerItf player = (PlayerItf) unitItf1;
                player.addMessage(MessageUtil.userChatMessage("yes you did it."));
            }

            @Override
            public void doCalc(UnitItf unitItf, WorldItf worldItf, WorldMgrItf worldMgrItf) {

            }

            @Override
            public void interactive(UnitItf self, PlayerItf playerItf, WorldItf world, WorldMgrItf worldMgr, Object input) {

            }
        });
        world.addCalcUnit(monster);
    }
}
