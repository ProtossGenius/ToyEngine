package com.suremoon.game.door.kernel;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.units_itf.UnitItf;

public interface ImpactCheckerItf {
    boolean UnitMovable(UnitItf unit, GameMapItf gameMap , PointF aims);
}
