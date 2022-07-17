package com.suremoon.game.kernel.data.units;

import com.suremoon.game.door.units_itf.BuffItf;
import com.suremoon.game.door.units_itf.UnitItf;

/** Created by Water Moon on 2017/11/28. */
public abstract class Buff implements BuffItf {
  protected boolean alive = true;
  protected UnitItf unit;

  public Buff() {}

  @Override
  public BuffItf setOwner(UnitItf unitItf) {
    this.unit = unitItf;
    return this;
  }

  @Override
  public boolean isAlive() {
    return alive;
  }
}
