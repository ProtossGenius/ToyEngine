package com.suremoon.game.kernel.actions;

import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.CommandItf;

/**
 * Created by Water Moon on 2018/4/19.
 */
public class InnerJarCmdAction implements CmdActionItf {
    CmdActionItf ca = Null;

    public InnerJarCmdAction(CmdActionItf ca) {
        this.ca = ca;
    }

    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
        return ca.actionDo(cmd, world, worldMgr);
    }

    @Override
    public void preventLambda() {
    }
}
