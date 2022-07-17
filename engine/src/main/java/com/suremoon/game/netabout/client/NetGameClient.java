package com.suremoon.game.netabout.client;

import com.suremoon.game.door.client.ScreenInfoUpdateItf;
import com.suremoon.game.door.kernel.GameScreenItf;
import com.suremoon.game.door.netabout.message.MsgScreenAsk;
import com.suremoon.game.door.netabout.message.MsgScreenInfo;
import com.suremoon.game.door.netabout.message.MsgUnit;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;
import com.suremoon.game.door.tools.PieceRun;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.netabout.Player;
import com.suremoon.game.netabout.predef;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

public class NetGameClient extends Thread {
  boolean running = false;
  Socket socket;
  GameScreenItf screen;
  ConcurrentLinkedQueue<CommandItf> msgQueue = new ConcurrentLinkedQueue();

  public ConcurrentLinkedQueue<CommandItf> getMsgQueue() {
    return msgQueue;
  }

  InputStream is;
  OutputStream os;
  Player player;
  ScreenInfoUpdateItf updater;

  public void setGameScreen(GameScreenItf screen) {
    this.screen = screen;
  }

  public void setUpdater(ScreenInfoUpdateItf updater) {
    this.updater = updater;
  }

  public NetGameClient(String host, int port) throws IOException {
    socket = new Socket(host, port);
    is = socket.getInputStream();
    os = new BufferedOutputStream(socket.getOutputStream());
  }

  @Override
  public void run() {
    super.run();
    new Thread(
            () -> {
              byte mLen[] = new byte[4];
              while (running) {
                try {
                  is.read(mLen);
                  byte[] bytes = new byte[CJDeal.byte2int(mLen)];
                  is.read(bytes);
                  MsgScreenInfo screenInfo = new MsgScreenInfo(new ByteStream(bytes));
                  if (screenInfo.getShowInfo()[0].length == 1) {
                    UnitItf u = new Unit((MsgUnit) screenInfo.getShowInfo()[0][0]);
                    this.player.setPutPos(u.getFootPos());
                  }

                  updater.update(screenInfo);
                } catch (IOException e) {
                  e.printStackTrace();
                  break;
                }
              }
            })
        .start();
    while (running) {
      PieceRun.DoPeaceRun(
          15,
          () -> {
            try {
              while (!msgQueue.isEmpty()) {
                byte[] cmdBytes = msgQueue.poll().toMessage().toBytes();
                byte[] msg = CJDeal.ByteArrayConnect(new byte[] {predef.CommandReq}, cmdBytes);
                os.write(CJDeal.int2byte(msg.length));
                os.write(msg);
                os.flush();
              }
              byte[] msg =
                  CJDeal.ByteArrayConnect(
                      new byte[] {predef.ScreenReq}, new MsgScreenAsk(screen).toBytes());
              os.write(CJDeal.int2byte(msg.length));
              os.write(msg);
              os.flush();
            } catch (IOException e) {
              e.printStackTrace();
              this.running = false;
            }
          });
    }
  }

  void write(byte[] msg) throws IOException {
    os.write(CJDeal.int2byte(msg.length));
    os.write(msg);
    os.flush();
  }

  public Player login(String unitType, String showName) throws IOException {
    if (running) {
      return null;
    }
    this.running = true;
    byte[] v1 = unitType.getBytes();
    byte[] v2 = showName.getBytes();
    byte[] msg =
        CJDeal.ByteArrayConnect(
            new byte[] {predef.LoginReq},
            CJDeal.int2byte(v1.length),
            v1,
            CJDeal.int2byte(v2.length),
            v2);
    write(msg);
    byte[] bytes = new byte[4];
    is.read(bytes);
    bytes = new byte[CJDeal.byte2int(bytes)];
    is.read(bytes);
    return this.player = new Player(new MsgUnit(new ByteStream(bytes)));
  }
}
