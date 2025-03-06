package com.suremoon.suremoon.mods.cmd_actions;

import com.suremoon.game.door.configs.CommandReg;
import com.suremoon.game.door.infos.UnitInformation;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.CommandItf;

@CommandReg("CmdChangeSelect")
public class CmdChangeSelect implements CmdActionItf {
    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
        int targetType = cmd.getTarget();
        UnitInformation unitInf = world.getUnitInf(targetType);
        if(unitInf == null){
            System.out.println("can't get this unit.");
            return true;
        }
        cmd.getOwner().setAGType(targetType);
        return true;
    }

    @Override
    public void preventLambda() {

    }
}
