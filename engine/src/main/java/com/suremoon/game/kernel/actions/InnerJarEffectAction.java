package com.suremoon.game.kernel.actions;

import com.suremoon.game.door.kernel.EffectActionItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.EffectItf;

/** Created by Water Moon on 2018/4/18. */
public class InnerJarEffectAction implements EffectActionItf {
  EffectActionItf ea = Null;

  public InnerJarEffectAction(String className) {
    try {
      Class<?> clz = Class.forName(className);
      ea = (EffectActionItf) clz.newInstance();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void effectDo(EffectItf effect, WorldItf world, WorldMgrItf wm) {
    ea.effectDo(effect, world, wm);
  }

  @Override
  public void preventLambda() {}
}
