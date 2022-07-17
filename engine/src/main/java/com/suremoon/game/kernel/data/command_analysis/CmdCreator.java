package com.suremoon.game.kernel.data.command_analysis;

import com.suremoon.game.door.client.CmdCreatorItf;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.kernel.initer.cmd_init.CmdInfManager;

import java.awt.*;

/**
 * Created by Water Moon on 2018/3/16.
 */
public class CmdCreator implements CmdCreatorItf {
    int type = -1;

    public CmdCreator() {

    }

    public CmdCreator(int type) {
        this.type = type;
    }

    @Override
    public CommandItf productCommand(int target, Point lastPoint) {
        if (type != -1) {
            return CmdInfManager.CIM.productCommand(type, lastPoint, target);
        }
        return null;
    }
}
