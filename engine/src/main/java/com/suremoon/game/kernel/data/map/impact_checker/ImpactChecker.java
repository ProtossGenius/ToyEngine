package com.suremoon.game.kernel.data.map.impact_checker;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.ImpactCheckerItf;
import com.suremoon.game.door.kernel.TerrainItf;
import com.suremoon.game.door.kernel.manager.UnitDoItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.map.Terrain;
import java.awt.*;

/** Created by Water Moon on 2018/3/22. */
public class ImpactChecker implements ImpactCheckerItf {
  private CanMove canMove = new CanMove();

  ImpactChecker() {}

  class CanMove implements UnitDoItf {
    public CanMove() {}

    public void reset(UnitItf unit, Point toPos) {
      this.unit = unit;
      this.toPos = new PointF(toPos);
      canMove = true;
    }

    private UnitItf unit;
    private PointF toPos;
    private boolean canMove;

    @Override
    public boolean Do(UnitItf other) {
      if (other == unit) {
        return true;
      }
      double nowDistance = PointF.getDistance(other.getFootPos(), unit.getFootPos());
      double targetDistance = PointF.getDistance(other.getFootPos(), toPos);
      if (targetDistance < nowDistance
          && targetDistance
              < (double)
                  (Math.min(
                      (unit.getWidth() + other.getWidth()) / 6,
                      (unit.getHeight() + other.getHeight()) / 3))) {
        canMove = false;
        return false;
      }

      return true;
    }

    public boolean isCanMove() {
      return canMove;
    }
  }

  final int UNIT_MAX_WIDTH = 300;

  @Override
  public boolean UnitMovable(UnitItf unit, GameMapItf gameMap, PointF aims) {
    /** todo: terrain check line check. */
    Point toPos = aims.toPoint();
    TerrainItf t = gameMap.getTerrain(toPos.x / Terrain.width, toPos.y / Terrain.height);
    if (!t.walkAble(unit)) {
      return false;
    }
    if (toPos.x <= 0
        || toPos.x >= gameMap.getMapWidth()
        || toPos.y <= 0
        || toPos.y >= gameMap.getMapHeight()) {
      return false;
    }
    canMove.reset(unit, toPos);
    Rectangle impactArea =
        new Rectangle(
            (int) (unit.getFootPos().X - UNIT_MAX_WIDTH),
            (int) (unit.getFootPos().Y - UNIT_MAX_WIDTH),
            UNIT_MAX_WIDTH * 2,
            UNIT_MAX_WIDTH * 2);
    gameMap.getUnitMgr().unitsDo(impactArea, canMove);
    if (!canMove.isCanMove()) {
      return false;
    }
    //        //todo: line check.
    //
    return true;
  }
}
