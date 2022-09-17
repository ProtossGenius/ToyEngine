package com.suremoon.game.ag_pc_client.show.pc_show;

import static com.suremoon.game.door.client.ActionData.*;

import com.suremoon.game.ag_pc_client.mkids.CmdMKID;
import com.suremoon.game.ag_pc_client.mkids.ScreenControlMKID;
import com.suremoon.game.door.client.ActionData;
import com.suremoon.game.door.client.CmdAnalysisInitItf;
import com.suremoon.game.door.client.CmdAnalysisItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.manager.UnitMgrItf;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.netabout.message.MsgScreenInfo;
import com.suremoon.game.door.netabout.message.MsgUnit;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.tools.PieceRun;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.command_analysis.CmdAnalysis;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.kernel.game_run.WorldMgr;
import com.suremoon.game.kernel.initer.unit_init.UnitInfManager;
import com.suremoon.game.netabout.Player;
import com.suremoon.game.netabout.client.NetGameClient;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class ClientStartup {
  public static void defaultCmdAnalysis(CmdAnalysisItf ca) {
    ca.addState(new int[] {-MouseEvent.BUTTON3}, "CmdInteractive");
    ca.addState(new int[] {KeyEvent.VK_M, -MouseEvent.BUTTON1}, "CmdWalk");
    ca.addState(new int[] {KeyEvent.VK_A, -MouseEvent.BUTTON1}, "CmdAttack");
    ca.addState(
        new ActionData[] {
          new ActionData(0, KeyEvent.VK_A, VALUE_TYPE_PRESS),
          new ActionData(DATA_TYPE_MOUSE, MouseEvent.BUTTON1, VALUE_TYPE_PRESS),
          new ActionData(DATA_TYPE_MOUSE, MouseEvent.BUTTON1, VALUE_TYPE_REPLASE)
        },
        "CmdAttack");
    ca.addState(
        new ActionData[] {
          new ActionData(DATA_TYPE_KEY_BOARD, KeyEvent.VK_A, VALUE_TYPE_PRESS),
          new ActionData(DATA_TYPE_MOUSE, MouseEvent.BUTTON1, VALUE_TYPE_PRESS),
          new ActionData(DATA_TYPE_KEY_BOARD, KeyEvent.VK_A, VALUE_TYPE_REPLASE),
          new ActionData(DATA_TYPE_MOUSE, MouseEvent.BUTTON1, VALUE_TYPE_REPLASE)
        },
        "CmdAttack");

    ca.addState(new int[] {KeyEvent.VK_Q}, "CmdPutMagic");
    ca.addState(new int[] {KeyEvent.VK_T}, "CmdTrans");
  }

  private static void initCmdAnalysis(CmdAnalysisItf ca, CmdAnalysisInitItf initor) {
    if (initor == null) {
      initor = ClientStartup::defaultCmdAnalysis;
    }

    initor.Init(ca);
  }

  public static void singleClient(
      String worldMgrCfg, String playerType, String playerName, CmdAnalysisInitItf analysisClass)
      throws Exception {
    WorldMgr wm = new WorldMgr(worldMgrCfg);
    WorldItf world = wm.getWorld(0);
    if (world == null) {
      throw new Exception("no such world whose index = 0");
    }
    PlayerItf player =
        new Player((Unit) UnitInfManager.getUim().productUnit(IDManager.getID(playerType)));
    player.setTransparency(15);
    CmdAnalysisItf cmdAnalysis = new CmdAnalysis();
    initCmdAnalysis(cmdAnalysis, analysisClass);
    wm.setAfterLoadModAction(
        () -> {
          player.setShowName(playerName);
          wm.InitPlayer(player);
        });
    new Thread(wm).start();

    world.pushGRectToCalcQueue(player);
    UnitMgrItf unitMgr = world.getGameMap().getUnitMgr();
    unitMgr.addUnit(player);
    // 客户端配置，主要是控制相关
    AGForm agf = new AGForm(worldMgrCfg, 0);
    CmdMKID cmkid = new CmdMKID(agf, cmdAnalysis);
    cmkid.setOnCmd(player::acceptCmd);
    ScreenControlMKID scmkid = new ScreenControlMKID(cmkid, player, agf.getGameScreen());
    agf.setMKID(scmkid);

    agf.setVisible(true);
    new Thread(
            () -> {
              while (true) {
                PieceRun.DoPeaceRun(
                    15,
                    () -> {
                      player.getScreen().setFocusPoint(agf.getGameScreen().getFocusPoint());
                      AGMessage[][] msg = player.getScreen().getShowers();
                      msg[0] = new AGMessage[] {new MsgUnit(player)};
                      msg[3] = player.getMessages(10);
                      int index = 0;
                      WorldItf currentWorld = player.getWorld();
                      if (currentWorld != null) {
                        index = currentWorld.getWorldIndex();
                      }
                      agf.update(new MsgScreenInfo(index, msg));
                    });
              }
            })
        .start();
    agf.gameLoop();
  }

  public static void networkClient(
      String serverAddress,
      int port,
      String worldMgrCfg,
      String playerType,
      String showName,
      CmdAnalysisInitItf analysisClass)
      throws Exception {
    NetGameClient client = new NetGameClient(serverAddress, port);
    CmdAnalysisItf cmdAnalysis = new CmdAnalysis();
    initCmdAnalysis(cmdAnalysis, analysisClass);

    UnitItf unit = client.login(playerType, showName);
    AGForm agf = new AGForm(worldMgrCfg, 0);
    // 客户端配置，主要是控制相关
    CmdMKID cmkid = new CmdMKID(agf, cmdAnalysis);
    cmkid.setOnCmd(cmd -> client.getMsgQueue().add(cmd));
    ScreenControlMKID scmkid = new ScreenControlMKID(cmkid, unit, agf.getGameScreen());
    agf.setMKID(scmkid);
    agf.setVisible(true);
    client.setUpdater(agf);
    client.setGameScreen(agf.getGameScreen());
    client.start();
    agf.gameLoop();
  }
}
