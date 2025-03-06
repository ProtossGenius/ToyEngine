package com.suremoon.suremoon.mods.cmd_actions;

import com.suremoon.game.door.configs.CommandReg;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.DieDo;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;

@CommandReg("CmdCreateMonster")
public class CmdCreateMonster implements CmdActionItf {
    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
        PlayerItf owner = (PlayerItf) cmd.getOwner();
        if(owner.getFootPos().getDistance(new PointF(cmd.getTargetPoint())) > 500){
            owner.addMessage(()->"召唤术：不在施法范围内。");
            return true;
        }
        UnitItf unit = world.productUnit(owner.getAGType());
        unit.setPutPos(cmd.getTargetPoint());
        unit.setDieDo(DieDo.Default);
        world.addCalcUnit(unit);
        return true;
    }

    @Override
    public void preventLambda() {

    }
}
