package com.suremoon.gametest.real_game_test.action_dealers;

import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.map.GameMap;
import com.suremoon.game.kernel.data.units.time_tools.GameTimer;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.methods.cmd_about.Move;

/**
 * Created by Water Moon on 2018/3/22.
 */
public class WalkAd implements CmdActionItf {
    @Override
    public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
        GameMapItf gm = world.getGameMap();
        long passedTime = GameTimer.getGt().getCurrentTime() - cmd.getFlagTime();
        UnitItf unit = cmd.getOwner();
        if(unit.getState().getAGType() != IDManager.getID("walk")){
            unit.setState(world.productState("walk"));
        }
        if(Move.move(gm, cmd.getOwner(), passedTime, new PointF(cmd.getTargetPoint()))){
            unit.setState(world.productState("paused"));
            return true;
        }
        return false;
    }

    @Override
    public void preventLambda() {}
}
