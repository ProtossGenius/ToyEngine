package com.suremoon.game.kernel.game_run;

import com.springmoon.sm_form.config.EasyConfig;
import com.springmoon.sm_form.interfaces.config.ConfigInf;
import com.suremoon.game.door.code_tools.JarLoader;
import com.suremoon.game.door.error.ErrorDeal;
import com.suremoon.game.door.kernel.PlayerInitItf;
import com.suremoon.game.door.kernel.Status;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.mods.WorldMgrModItf;
import com.suremoon.game.kernel.data.units.time_tools.GameTimer;
import com.suremoon.game.kernel.initer.InitManager;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class WorldMgr implements WorldMgrItf {

  static final int OneWorldInitFail = 1;

  private GameTimer gt = GameTimer.getGt();
  public static int PIECE_TIME = 15;
  private Status status = Status.Ready;
  private HashMap<String, Integer> worldMap = new HashMap<>();
  private ArrayList<WorldItf> worldList;
  private LinkedList<WorldMgrModItf> modItfs = new LinkedList<>();
  private PlayerInitItf playerInitItf = PlayerInitItf.Default;

  private Runnable afterLoadModAction = () -> {};

  public WorldMgr(String cfgPath) throws Exception {
    InitManager.init();
    if (!new File(cfgPath).exists()) {
      throw new FileNotFoundException(cfgPath);
    }
    EasyConfig ec = new EasyConfig(cfgPath);

    ConfigInf worldsCfg = ec.getConfig("WorldList");
    if (worldsCfg == null) {
      throw new Exception("In WorldManager WorldList's size = 0");
    }

    ConfigInf[] list = worldsCfg.listConfigs();
    worldList = new ArrayList<>();
    for (int i = 0; i < list.length; ++i) {
      ConfigInf it = list[i];
      String path = it.getValue();
      if (path == "") {
        continue;
      }

      int worldIndex = this.worldList.size();
      WorldItf oneWorld = new World(this, worldIndex, path);

      this.worldMap.put(path, this.worldList.size());
      this.worldList.add(oneWorld);
    }
    loadModCfg(ec.getConfig("ModList"));
  }

  private void loadModCfg(ConfigInf ec) throws Exception {
    if (ec == null) {
      return;
    }

    try {
      ConfigInf[] list = ec.listConfigs();
      for (ConfigInf it : list) {
        if (it == null) {
          continue;
        }
        String line = it.getValue().trim();
        if (line == "" || line.startsWith("#")) {
          continue;
        }
        JarLoader.NewClass(
            line,
            (ins) -> {
              if (ins instanceof WorldMgrModItf) {
                modItfs.add((WorldMgrModItf) ins);
              } else {
                ErrorDeal.putError(
                    "In WorldManager.loadModCfg, class " + line + " not a WorldMgrModItf");
              }
            });
      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    status = Status.Running;
    for (WorldMgrModItf mod : modItfs) {
      System.out.println(
          "[WorldManager] Loading Mod ["
              + mod.ModName()
              + "], Class Name is : <"
              + mod.getClass().getName()
              + ">  ........");
      mod.Do(this);
      System.out.println("[WorldManager] Load Mod " + mod.ModName() + " Success");
    }
    afterLoadModAction.run();
    for (WorldItf it : worldList) {
      if (it == null) continue;
      it.run();
    }

    while (status != Status.Stop) {
      try {
        gt.Update(System.currentTimeMillis());
        Thread.sleep(PIECE_TIME);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public Status getStatus() {
    return status;
  }

  @Override
  public WorldItf getWorld(String path) {
    return worldList.get(worldMap.get(path));
  }

  @Override
  public WorldItf getWorld(int index) {
    return worldList.get(index);
  }

  @Override
  public void setPlayerInitItf(PlayerInitItf playerInitItf) {
    this.playerInitItf = playerInitItf;
  }

  @Override
  public PlayerInitItf getPlayerInitItf() {
    return playerInitItf;
  }

  @Override
  public void setAfterLoadModAction(Runnable afterLoadModAction) {
    if (afterLoadModAction == null) {
      afterLoadModAction = () -> {};
    }
    this.afterLoadModAction = afterLoadModAction;
  }
}
