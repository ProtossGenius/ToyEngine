package com.suremoon.gametest.real_game_test.action_dealers;

import com.suremoon.game.door.configs.CommandReg;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.units.time_tools.GameTimer;
import com.suremoon.game.methods.cmd_about.Move;

@CommandReg("CmdInteractive")
public class InteractiveAD implements CmdActionItf {
    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgrItf) {
        int unitId = cmd.getTarget();
        UnitItf unit = cmd.getOwner();
        if (!(unit instanceof PlayerItf player)) {
            return true;
        }

        GameMapItf gm = world.getGameMap();

        if (unitId != -1 && unitId != unit.getGid()) {
            UnitItf targetUnit = gm.getUnitMgr().getUnit(unitId);
            if (targetUnit != null
                    && targetUnit.getFootPos().getDistance(cmd.getOwner().getFootPos()) < 120) {
                targetUnit.getUnitRem().interactive(targetUnit, player, world, worldMgrItf, "");

                return true;
            }
        }

        long passedTime = GameTimer.getGt().getCurrentTime() - cmd.getFlagTime();
        if (unit.getState().getAGType() != IDManager.getID("walk")) {
            unit.setState(world.productState("walk"));
        }
        if (Move.move(gm, cmd.getOwner(), passedTime, new PointF(cmd.getTargetPoint()))) {
            unit.setState(world.productState("paused"));
            return true;
        }

        return false;
    }

    @Override
    public void preventLambda() {
    }
}
