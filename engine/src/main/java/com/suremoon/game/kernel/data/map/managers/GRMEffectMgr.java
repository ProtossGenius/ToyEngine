package com.suremoon.game.kernel.data.map.managers;

import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.kernel.manager.EffectMgrItf;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.units_itf.EffectItf;
import com.suremoon.game.kernel.data.units.Effect;
import com.suremoon.game.kernel.grect_manager.GRectManager;
import java.awt.*;
import java.util.List;

public class GRMEffectMgr implements EffectMgrItf {
  GRectManager grm;
  byte[] lock = new byte[0];

  public GRMEffectMgr(Dimension mapSize, Dimension blockSize) {
    grm = new GRectManager(mapSize, blockSize);
  }

  @Override
  public EffectItf[] getEffects() {
    List<GRectItf> rects = grm.getAllGRects();
    int lns = rects.size();
    Effect[] res = new Effect[lns];
    for (int i = 0; i < lns; i++) {
      res[i] = ((Effect) rects.get(i));
    }
    return res;
  }

  @Override
  public void removeEffect(EffectItf e) {
    grm.delGRect(e);
  }

  @Override
  public void addEffect(EffectItf e) {
    grm.addGRect(e);
  }

  @Override
  public AGMessage[] getShowers(Rectangle screenRect) {
    List<GRectItf> rects = grm.getGRects(screenRect);
    int lns = rects.size();
    AGMessage[] res = new AGMessage[lns];
    for (int i = 0; i < lns; i++) {
      res[i] = ((Effect) rects.get(i)).toMessage();
    }
    return res;
  }

  @Override
  public int size() {
    return grm.size();
  }
}
