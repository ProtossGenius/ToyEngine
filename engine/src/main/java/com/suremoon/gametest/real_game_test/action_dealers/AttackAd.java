package com.suremoon.gametest.real_game_test.action_dealers;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.*;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.initer.state_init.StateInfManager;
import java.awt.*;

/** Created by Water Moon on 2018/4/4. */
public class AttackAd implements CmdActionItf {
  final int UNIT_MAX_WIDTH = 300;

  @Override
  public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
    GameMapItf gm = world.getGameMap();
    UnitItf p = cmd.getOwner();
    if (p.getState().getAGType() != IDManager.getID("attack")) {
      p.setState(StateInfManager.sm.productState("attack"));
    }
    int idx = (int) p.getState().getPassedTime() / p.getIntervalTime();
    int length = 5;
    //        int length =
    // ui.getShower().resList.get(ui.getShower().getState2action().get(IDManager.getName(p.getState().getAGType())) + " e").getLength();
    //        if(idx >= length){
    //            p.setState(StateInfManager.sm.productState("paused"));
    //            cmd.remove();
    //            return;
    //        }
    if (idx % length != length * 3 / 4) {
      return false;
    }

    Point attackDirect = cmd.getTargetPoint();
    PointF direct = p.getFootPos().getDirection(new PointF(attackDirect));
    if (!direct.equals(PointF.DIRECTION_ZERO)) {
      p.setDirect(direct);
    }
    Rectangle impactArea =
        new Rectangle(
            (int) (p.getFootPos().X - UNIT_MAX_WIDTH),
            (int) (p.getFootPos().Y - UNIT_MAX_WIDTH),
            UNIT_MAX_WIDTH * 2,
            UNIT_MAX_WIDTH * 2);
    gm.getUnitMgr()
        .unitsDo(
            impactArea,
            unit -> {
              if (unit == p) return true;
              if (getDistance(p, unit) <= p.getWidth() / 2.0 * 1.5) {
                if (PointF.getDistance(
                        p.getDirect(), p.getFootPos().getDirection(unit.getFootPos()))
                    <= 1.732 / 2.0) {
                  double before = unit.getAttribute().getHp();
                  unit.underAttack(p, p.getAttribute().getMetal(), 0);
                  if (unit.isDie()) {
                    p.BeHeal((before - unit.getAttribute().getHp()) * 10);
                  } else {
                    p.BeHeal((before - unit.getAttribute().getHp()));
                  }
                }
              }
              return true;
            });

    return false;
  }

  public double getDistance(GRectItf u1, GRectItf u2) {
    PointF p1 = u1.getFootPos(), p2 = u2.getFootPos();
    return p1.getDistance(p2);
  }

  @Override
  public void preventLambda() {}
}
