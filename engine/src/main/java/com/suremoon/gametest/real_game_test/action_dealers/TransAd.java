package com.suremoon.gametest.real_game_test.action_dealers;

import com.suremoon.game.door.configs.CommandReg;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.kernel.enums.LeaveStatus;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.StateItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.initer.state_init.StateInfManager;

@CommandReg("CmdTrans")
public class TransAd implements CmdActionItf {
    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
        UnitItf u = cmd.getOwner();
        if (u.getLeaveStatus() != LeaveStatus.Left) {
            u.setLeaveStatus(LeaveStatus.ReadyToLeave);
        }

        u.setLeaveAction(
                (unit, worldItf, worldMgrItf) -> {
                    unit.setLeaveStatus(LeaveStatus.None);
                    StateItf state = unit.getState();
                    if (state.getAGType() != IDManager.getID("paused")) {
                        unit.setState(StateInfManager.getSM().productState("paused"));
                    }
                    PointF tpf = new PointF(5000, 5050);
                    int target = 0;
                    if (worldItf.getWorldIndex() == 0) {
                        target = 1;
                        tpf = new PointF(100, 100);
                    }

                    unit.setPutPos(tpf);

                    WorldItf newWorld = worldMgrItf.getWorld(target);
                    newWorld.pushGRectToCalcQueue(unit);
                    newWorld.getGameMap().getUnitMgr().addUnit(unit);
                });

        return true;
    }

    @Override
    public void preventLambda() {
    }
}
