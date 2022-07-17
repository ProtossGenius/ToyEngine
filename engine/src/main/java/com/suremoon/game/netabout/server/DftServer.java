package com.suremoon.game.netabout.server;

import static com.suremoon.game.netabout.predef.*;

import com.suremoon.game.door.kernel.GameScreenItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.netabout.message.*;
import com.suremoon.game.door.server.ServerItf;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.netabout.Player;
import com.suremoon.gametest.real_game_test.diedos.PlayerDieDo;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DftServer implements ServerItf {
  private WorldMgrItf worldMgr;

  public DftServer() {}

  @Override
  public void run(int port, WorldMgrItf worldMgr) {
    this.worldMgr = worldMgr;
    try {
      ServerSocket ss = new ServerSocket(port);
      while (true) {
        Socket socket = ss.accept();
        new PlayerHandler(socket, worldMgr).start();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

class PlayerHandler extends Thread {
  private Player player;
  private WorldMgrItf worldMgr;
  Socket socket;
  InputStream in;
  OutputStream out;
  boolean isFirst = true;

  public PlayerHandler(Socket socket, WorldMgrItf worldMgr) throws IOException {
    this.socket = socket;
    this.in = new BufferedInputStream(socket.getInputStream());
    this.out = new BufferedOutputStream(socket.getOutputStream());
    this.worldMgr = worldMgr;
  }

  @Override
  public void run() {
    super.run();
    while (true) {
      try {
        this.forMessage();
      } catch (IOException e) {
        e.printStackTrace();
        try {
          socket.close();
        } catch (IOException ioException) {
          ioException.printStackTrace();
        }
        break;
      }
    }
    player.setDrop(true);
  }

  private void forMessage() throws IOException {
    byte[] tmp = new byte[4];
    in.read(tmp);
    int len = CJDeal.byte2int(tmp);
    tmp = new byte[len];
    in.read(tmp);
    ByteStream bs = new ByteStream(tmp);
    byte[] bts = bs.getBytes(1);
    if (isFirst && bts[0] != LoginReq) {
      System.out.println("[info] this first message not Login.");
      socket.close();
      return;
    }
    switch (bts[0]) {
      case LoginReq:
        { // login.
          this.isFirst = false;
          String UnitType = bs.getString();
          this.player = new Player(IDManager.getID(UnitType));
          WorldItf world = worldMgr.getWorld(0);
          world.InitUnit(player);
          String name = bs.getString();
          if (name.length() > 8) {
            name = name.substring(0, 8);
          }
          player.setShowName(name);
          player.setPos(5000, 5000);
          player.getAttribute().setSpd(500.0);
          player.setDieDo(PlayerDieDo.Instance);
          worldMgr.InitPlayer(player);
          world.pushGRectToCalcQueue(player);
          world.getGameMap().getUnitMgr().addUnit(player);
          byte[] pMsg = new MsgUnit(player).toBytes();
          out.write(CJDeal.int2byte(pMsg.length));
          out.write(pMsg);
          out.flush();
        }
        break;
      case CommandReq:
        { // receive cmd.
          player.acceptCmd(new MsgCommand(bs));
        }
        break;
      case ScreenReq:
        { // client ask screen.
          MsgScreenAsk mscr = new MsgScreenAsk(bs);
          GameScreenItf screen = player.getScreen();
          if (screen == null) {
            return;
          }
          screen.setFocusPoint(mscr.posX, mscr.posY);
          AGMessage[][] showers = screen.getShowers();
          showers[0][0] = new MsgUnit(player);
          showers[3] = player.getMessages(10);
          MsgScreenInfo screenInfo = new MsgScreenInfo(player.getWorld().getWorldIndex(), showers);
          bts = screenInfo.toBytes();
          out.write(CJDeal.int2byte(bts.length));
          out.write(bts);
        }
        break;
      default:
        System.out.println("don't support message type " + (int) bts[0]);
    }
  }
}
