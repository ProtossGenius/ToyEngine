package com.suremoon.suremoon.mods.cmd_actions;

import com.suremoon.game.door.configs.CommandReg;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.units.time_tools.GameTimer;
import com.suremoon.game.methods.cmd_about.Move;

@CommandReg("CmdChase")
public class CmdChase implements CmdActionItf {
    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgrItf) {
        int unitId = cmd.getTarget();
        UnitItf unit = cmd.getOwner();
        if(!(unit instanceof PlayerItf)){
            return true;
        }

        PlayerItf player = (PlayerItf)unit;
        GameMapItf gm = world.getGameMap();

        if(unitId == -1) {
            return true;
        }

        UnitItf targetUnit = gm.getUnitMgr().getUnit(unitId);
        if(targetUnit != null && targetUnit.getFootPos().getDistance(cmd.getOwner().getFootPos()) < 50){
            targetUnit.getUnitRem().interactive(targetUnit, player, world, worldMgrItf, "");
            return false;
        }

        long passedTime = GameTimer.getGt().getCurrentTime() - cmd.getFlagTime();
        Move.move(gm, cmd.getOwner(), passedTime, new PointF(cmd.getTargetPoint()));
        return false;
    }

    @Override
    public void preventLambda() {

    }
}
