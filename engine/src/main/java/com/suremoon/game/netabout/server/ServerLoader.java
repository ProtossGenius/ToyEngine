package com.suremoon.game.netabout.server;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.ag_pc_client.resource.image.ImageFactory;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.server.ServerLoaderItf;
import com.suremoon.game.door.server.ServerItf;
import com.suremoon.game.door.mods.ServerModItf;
import com.suremoon.game.door.code_tools.JarLoader;
import com.suremoon.game.kernel.game_run.WorldMgr;

public class ServerLoader implements ServerLoaderItf {
    private int port;
    private WorldMgrItf worldMgr;
    ServerItf serverItf;
    public ServerLoader(String cfg) throws Exception {
        ConfigInf eCfg = new EasyConfig(cfg);
        String worldMgrCfg = eCfg.getConfig("WorldMgr").getValue();
        this.port = Integer.parseInt(eCfg.getConfig("Port").getValue());
        this.worldMgr = new WorldMgr(worldMgrCfg);
        this.worldMgr.setAfterLoadModAction(()->{
            try {
                serverItf = new DftServer();
                ConfigInf[] modList = eCfg.getConfig("ModList").listConfigs();
                for (ConfigInf it : modList){
                    String className = it.getValue().trim();
                    JarLoader.NewClass(className, (ins)->{
                        if (ins instanceof ServerModItf){
                            ((ServerModItf) ins).Do(this.worldMgr, this);
                        }else {
                            System.err.println("Load ServerMode Fail, " + className + " not a ServerModItf.");
                        }
                    });
                }
            }catch (Exception e){
                e.printStackTrace();
                System.exit(-1);
            }
        });
        new Thread(worldMgr).start();


    }

    @Override
    public void setServerItf(ServerItf serverItf){
        this.serverItf = serverItf;
    }

    @Override
    public void run() {
        serverItf.run(port, worldMgr);
    }
    /**
     * @param args
     * if length == 0, use default;
     * if length == 1, server config
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        ImageFactory.DoNotUseImageLoader();
        String cfg = "./configs/server_config/server.xml";
        if (args.length == 1){
            cfg = args[0];
        }

        new ServerLoader(cfg).run();
    }
}
