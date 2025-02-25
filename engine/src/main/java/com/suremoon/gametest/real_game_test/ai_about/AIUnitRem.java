package com.suremoon.gametest.real_game_test.ai_about;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.UnitRemItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.EffectItf;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;
import com.suremoon.game.kernel.initer.state_init.StateInfManager;
import com.suremoon.game.netabout.MessageUtil;

import java.util.Random;

public class AIUnitRem implements UnitRemItf {
    public UnitItf attacker;
    long last;

    @Override
    public void underAttack(UnitItf owner, UnitItf attacker, double hurt) {
        this.attacker = attacker;
    }

    @Override
    public void doCalc(UnitItf unit, WorldItf world, WorldMgrItf worldMgr) {
        GameMapItf gm = world.getGameMap();
        if (attacker != null && System.currentTimeMillis() - last >= 1000) {
            if (unit.getState().getAGType() != IDManager.getID("attack")) {
                unit.setState(StateInfManager.getSM().productState("attack"));
            }
            last = System.currentTimeMillis();
            EffectItf ne = world.productEffect(IDManager.getID("FlowerFire"), unit);
            ne.setPutPos(attacker.getFootPos());
            if (gm.getEffectMgr().size() < 5000) {
                gm.getEffectMgr().addEffect(ne);
            }
            if (attacker.isDie()) {
                attacker = null;
            }
        } else if (unit.getState().getAGType() != IDManager.getID("walk")) {
            PointF nowP = unit.getFootPos();
            Random rand = GameConfiger.gc.getRandom();
            nowP.AddAs(new PointF(rand.nextInt() % 500, rand.nextInt() % 500));
            unit.acceptCmd(
                    world.productCommand(IDManager.getID("CmdWalk"), nowP.toPoint(), -1).setAppendCmd(true));
        }
    }

    @Override
    public void interactive(UnitItf self, PlayerItf playerItf, WorldItf world, WorldMgrItf worldMgr, Object s) {
        playerItf.addMessage(MessageUtil.userChatMessage("瑕疵必报！"));
    }
}
