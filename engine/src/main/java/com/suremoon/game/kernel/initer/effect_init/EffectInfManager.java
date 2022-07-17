package com.suremoon.game.kernel.initer.effect_init;

import com.suremoon.game.door.error.ErrorDeal;
import com.suremoon.game.door.factorys.EffectFactory;
import com.suremoon.game.door.infos.EffectInformation;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.units.Effect;
import java.util.HashMap;

/** Created by Water Moon on 2018/4/10. */
public class EffectInfManager implements EffectFactory {
  public static final EffectInfManager EIM = new EffectInfManager();

  public static final EffectInfManager getEIM() {
    return EIM;
  }

  static Integer effectGid = 0;
  HashMap<Integer, EffectInformation> eihm;

  protected EffectInfManager() {
    eihm = new HashMap<>();
  }

  @Override
  public void putEffectInf(int type, EffectInformation ei) {
    eihm.put(type, ei);
  }

  @Override
  public EffectInformation getEffectInf(int type) {
    return eihm.get(type);
  }

  @Override
  public Effect productEffect(int type, UnitItf unit) {
    EffectInformation ei = EffectInfManager.EIM.getEffectInf(type);
    if (ei == null) {
      ErrorDeal.putError(
          "EffectType not exist, whose type is:"
              + type
              + "id in IdManager is: "
              + IDManager.getName(type));
      return null;
    }
    int gid;
    synchronized (effectGid) {
      gid = effectGid++;
    }
    Effect e = new Effect(System.currentTimeMillis(), gid);

    e.setFootPosPro(ei.getFootPos());
    e.setSize(ei.getWidth(), ei.getHeight());
    e.setEffectType(type);
    e.setEffectPutter(unit);
    e.setEffectAction(ei.getEffectAction());
    return e;
  }
}
