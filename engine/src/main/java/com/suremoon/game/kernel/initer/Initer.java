package com.suremoon.game.kernel.initer;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.interfaces.config.ConfigInf;

/** Created by Water Moon on 2018/3/5. */
public abstract class Initer implements InitItf {
  protected final EasyConfig open(String xml) throws Exception {
    return new EasyConfig(xml);
  }

  protected abstract void do_init(ConfigInf ci) throws Exception;

  public final void init(String cfgPath, Progress progress) throws Exception {
    do_init(open(cfgPath));
  }

  protected abstract String getXmlPath();

  public final void init(Progress progress) throws Exception {
    init(getXmlPath(), progress);
  }
}
