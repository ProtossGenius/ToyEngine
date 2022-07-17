package com.suremoon.game.kernel.actions;

import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.tools.MSLoger;
import com.suremoon.game.door.units_itf.CommandItf;

/** Created by Water Moon on 2018/4/19. */
public class InnerJarCmdAction implements CmdActionItf {
  CmdActionItf ca = Null;

  public InnerJarCmdAction(String className) {
    try {
      Class<?> clz = Class.forName(className);
      if (clz != null) {
        ca = (CmdActionItf) clz.newInstance();
      } else {
        MSLoger.writeFLog("In CA_InnerJar: can't found class " + className);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public boolean actionDo(CommandItf cmd, WorldItf world, WorldMgrItf worldMgr) {
    return ca.actionDo(cmd, world, worldMgr);
  }

  @Override
  public void preventLambda() {}
}
