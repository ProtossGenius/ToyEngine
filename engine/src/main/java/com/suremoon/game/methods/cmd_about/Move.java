package com.suremoon.game.methods.cmd_about;

import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.kernel.data.units.State;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.initer.state_init.StateInfManager;

public class Move {
    private static boolean do_move(GameMapItf gm, UnitItf u, long passedTime, PointF tpf){
        double speed = u.getAttribute().getSpd();

        if (passedTime == 0) return false;
//        System.out.println(u.getPos() + ", " + tpf);
        double distance = PointF.getDistance(u.getFootPos(), tpf);
        PointF dct = PointF.getDirection(u.getFootPos(), tpf);
        u.setDirect(dct);
        double moveDistance = speed * passedTime / 1000;
        if (distance <= moveDistance) {
            if (!gm.getImpactChecker().UnitMovable(u, gm, tpf)) {
                //impact deal
                return true;
            }
            u.setPutPos(tpf);
            return true;
        }
        PointF direct = PointF.getDirection(u.getFootPos(), tpf);

        direct.SinMultipyAs(moveDistance);
        if (!gm.getImpactChecker().UnitMovable(u, gm, u.getFootPos().Add(direct))) {
            //impact deal
            return true;
        }
//        u.getPos().AddAs(direct);//should never use this way.
        u.moveAdd(direct);
        return false;

    }
    // return : is move finish.
    public static boolean move(GameMapItf gm, UnitItf u, long passedTime, PointF tpf) {
        if(passedTime > 25)passedTime = 25;
        return do_move(gm, u, passedTime, tpf);
    }
}
