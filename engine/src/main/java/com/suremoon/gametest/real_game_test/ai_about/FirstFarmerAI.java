package com.suremoon.gametest.real_game_test.ai_about;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.UnitRemItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.kernel.manager.UnitMgrItf;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;

import java.awt.*;

public class FirstFarmerAI implements UnitRemItf {

    long nextSpeak = 0;

    @Override
    public void underAttack(UnitItf owner, UnitItf unitItf, double v) {
        if (unitItf instanceof PlayerItf player) {
            player.addMessage("农夫说：小子，你竟敢打老子？");
        }
    }

    @Override
    public void doCalc(UnitItf unitItf, WorldItf worldItf, WorldMgrItf worldMgrItf) {
        if (nextSpeak > System.currentTimeMillis()) {
            return;
        }
        nextSpeak = System.currentTimeMillis() + 5000;
        UnitMgrItf unitMgr = worldItf.getGameMap().getUnitMgr();
        PointF pos = unitItf.getFootPos();
        unitMgr.unitsDo(
                new Rectangle(
                        pos.getIntX() - 500, pos.getIntY() - 500, pos.getIntX() + 500, pos.getIntY() + 500),
                unit -> {
                    if (unit instanceof PlayerItf player) {
                        player.addMessage("农夫大声喊：滚开，莫挨老子。");
                    }
                    return false;
                });
    }

    @Override
    public void interactive(UnitItf self, PlayerItf playerItf, WorldItf world, WorldMgrItf worldMgr, String s) {
        playerItf.addMessage("农夫妖娆的说：Kill me baby!");
    }
}
