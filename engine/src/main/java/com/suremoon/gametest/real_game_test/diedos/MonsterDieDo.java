package com.suremoon.gametest.real_game_test.diedos;

import com.suremoon.game.door.kernel.DieDo;
import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.kernel.manager.UnitMgrItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;
import com.suremoon.gametest.real_game_test.ai_about.AIUnitRem;

import java.util.Random;

public class MonsterDieDo implements DieDo {
    public static MonsterDieDo Instance = new MonsterDieDo();

    @Override
    public void Do(UnitItf it, WorldItf world, WorldMgrItf worldMgr) {
        GameMapItf gm = world.getGameMap();
        UnitMgrItf um = gm.getUnitMgr();
        // 尸体处理
        if (it.isDie() && it.getState().getPassedTime() >= 3000) {
            it.setDrop(true);
            for (int i = 0; i < 3000; i++) {
                if (um.size() >= 10000) break;
                Random rand = GameConfiger.gc.getRandom();
                UnitItf u2 = world.productUnit(IDManager.getID("T_lumberjack"));
                u2.setPos(
                        Math.abs(rand.nextInt() % gm.getMapWidth()),
                        Math.abs(rand.nextInt() % gm.getMapHeight()));
                u2.getAttribute().setSpd(150);
                u2.setTransparency(50);
                //        u2.addCmd(CmdInfManager.CIM.productCommand(IDManager.getID("CmdAttack"), u2, new
                // Point(100, 100), null, gr.getGm()));
                //
                // u2.setCmd(CmdInfManager.CIM.productCommand(IDManager.getID("CmdFlowerFire"), u2,
                // u2.getPos().toPoint(), null, gameMap));
                u2.setUnitRem(new AIUnitRem());
                um.addUnit(u2);
                u2.setDieDo(this);
            }
        }
    }
}
