package com.suremoon.game.kernel.initer.effect_init;

import com.suremoon.game.kernel.initer.InitListItf;
import com.suremoon.game.kernel.initer.Progress;

/** Created by Water Moon on 2018/4/11. */
public class EffectListIniter implements InitListItf {
  @Override
  public void init(Progress progress) throws Exception {
    loadList(
        "configs/effect_config", ".xml", name -> new EffectIniter(name).init(progress), progress);
  }
}
