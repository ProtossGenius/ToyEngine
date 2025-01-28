package com.suremoon.game.kernel.initer.cmd_init;

import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.door.configs.CommandReg;
import com.suremoon.game.door.configs.ConfigScanner;
import com.suremoon.game.door.infos.CmdInformation;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.initer.InitListItf;
import com.suremoon.game.kernel.initer.Initer;

/**
 * Created by Water Moon on 2018/4/19.
 */
public class CmdListIniter extends Initer implements InitListItf {
    @Override
    protected void do_init(ConfigInf ci) throws Exception {
        ci.open("Packages");
        ConfigInf[] cis = ci.listConfigs();
        for (ConfigInf cmdCfg : cis) {
            if (cmdCfg == null) {
                continue;
            }
            String packageName = cmdCfg.getValue().trim();
            ConfigScanner.findAnnotatedClasses(CommandReg.class, (anno, claxx) -> {
                var name = anno.value();
                CmdActionItf cmd_action = null;
                try {
                    cmd_action = (CmdActionItf) claxx.newInstance();
                } catch (InstantiationException e) {
                    throw new RuntimeException(e);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                CmdInformation cmdIfm = new CmdInformation(cmd_action, IDManager.getID(name));
                CmdInfManager.CIM.putCmdInf(IDManager.getID(name), cmdIfm);
            }, packageName);
        }
    }

    @Override
    public String getXmlPath() {
        return "configs/commands.xml";
    }
}
