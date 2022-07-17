package com.suremoon.game.kernel.initer.cmd_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.door.infos.CmdInformation;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.actions.InnerJarCmdAction;
import com.suremoon.game.kernel.initer.InitListItf;
import com.suremoon.game.kernel.initer.Initer;

/** Created by Water Moon on 2018/4/19. */
public class CmdListIniter extends Initer implements InitListItf {
  @Override
  protected void do_init(ConfigInf ci) throws Exception {
    ci.open("Commands");
    ConfigInf[] cis = ci.listConfigs();
    for (ConfigInf cmdCfg : cis) {
      if (cmdCfg == null) {
        continue;
      }
      String name = cmdCfg.getValue("name");
      String cmd_action = cmdCfg.getValue().trim();
      CmdActionItf ca = new InnerJarCmdAction(cmd_action);
      CmdInformation cmdIfm = new CmdInformation(ca, IDManager.getID(name));
      CmdInfManager.CIM.putCmdInf(IDManager.getID(name), cmdIfm);
    }
  }

  @Override
  public String getXmlPath() {
    return "configs/commands.xml";
  }
}
