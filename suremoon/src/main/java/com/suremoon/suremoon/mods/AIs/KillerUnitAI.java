package com.suremoon.suremoon.mods.AIs;

import com.suremoon.game.door.kernel.UnitRemItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.methods.cmd_about.Move;


public class KillerUnitAI implements UnitRemItf {

    private UnitItf target;

    public KillerUnitAI(UnitItf target) {
        this.target = target;
    }

    long lastTime = System.currentTimeMillis();
    long lastAttack = 0;

    @Override
    public void underAttack(UnitItf owner, UnitItf unitItf, double v) {
    }

    @Override
    public void doCalc(UnitItf unitItf, WorldItf worldItf, WorldMgrItf worldMgrItf) {
        Move.move(worldItf.getGameMap(), unitItf, System.currentTimeMillis() - lastTime, target.getFootPos());
        if (System.currentTimeMillis() - lastAttack > 3000) {
            unitItf.acceptCmd(worldItf.productCommand(IDManager.getID("CmdAttack"), target.getFootPos().toPoint(), -1).setAppendCmd(false));
            lastAttack = System.currentTimeMillis();
        }
        lastTime = System.currentTimeMillis();
    }

    @Override
    public void interactive(UnitItf self, PlayerItf playerItf, WorldItf world, WorldMgrItf worldMgr, Object input) {

    }

}
