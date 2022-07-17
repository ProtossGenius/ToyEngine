package com.suremoon.game.ag_pc_client.show.adapters.unit;

import com.suremoon.game.ag_pc_client.show.UnitMoreShow;
import com.suremoon.game.kernel.data.units.Unit;
import java.awt.*;

/** Created by Water Moon on 2018/3/15. */
public class BPShowAdapter {
  UnitMoreShow bp = new UnitMoreShow();

  public void show(Graphics gp, Unit unit, Point focusPoint) {
    bp.show(gp, unit, focusPoint);
  }
}
