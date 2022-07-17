package com.suremoon.game.kernel.initer.cmd_init;

import com.suremoon.game.door.factorys.CommandFactory;
import com.suremoon.game.door.infos.CmdInformation;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.kernel.data.units.Command;
import java.awt.*;
import java.util.HashMap;

/** Created by Water Moon on 2018/4/19. */
public class CmdInfManager implements CommandFactory {
  public static final CmdInfManager CIM = new CmdInfManager();

  public static final CmdInfManager getCIM() {
    return CIM;
  }

  HashMap<Integer, CmdInformation> cihm;

  protected CmdInfManager() {
    cihm = new HashMap<>();
  }

  @Override
  public void putCmdInf(int type, CmdInformation cmdIfm) {
    cihm.put(type, cmdIfm);
  }

  @Override
  public CmdInformation getCmdInf(int type) {
    return cihm.get(type);
  }

  @Override
  public CommandItf productCommand(int type, Point tp, int target) {
    CmdInformation cmi = getCmdInf(type);
    Command agc = new Command(type, tp, target);
    agc.setCmdAction(cmi.getCmdAction());
    return agc;
  }
}
