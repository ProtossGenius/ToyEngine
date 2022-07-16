package com.suremoon.game.door.factorys;

import com.suremoon.game.door.infos.CmdInformation;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.CommandItf;

import java.awt.*;

public interface CommandFactory {
    void putCmdInf(int type, CmdInformation cmdIfm);

    default void putCmdInf(String cmdName, CmdInformation cmdIfm){
        putCmdInf(IDManager.getID(cmdName), cmdIfm);
    }
    CmdInformation getCmdInf(int type);

    default CmdInformation getCmdInf(String cmdName){
        return getCmdInf(IDManager.getID(cmdName));
    }
    CommandItf productCommand(int type, Point tp, int target);
}
