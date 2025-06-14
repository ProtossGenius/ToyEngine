package com.suremoon.suremoon.mods.analysiser;

import com.suremoon.game.door.client.CmdAnalysisItf;
import com.suremoon.game.door.client.CmdCreatorItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.kernel.initer.cmd_init.CmdInfManager;
import com.suremoon.suremoon.consts.units;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ClientInputAnalysis {
    static final int[] TargetList= new int[]{units.blueclub, units.T_lumberjack, units.T_red_knight };
    public static void defaultCmdAnalysis(CmdAnalysisItf ca){
        ca.addState(new int[] {-MouseEvent.BUTTON3}, "CmdInteractive");
        ca.addState(new int[] {KeyEvent.VK_M, -MouseEvent.BUTTON1}, "CmdWalk");
        ca.addState(new int[] {KeyEvent.VK_A}, "CmdAttack");

        ca.addState(new int[]{KeyEvent.VK_Q}, "CmdCreateMonster");
    }
}
