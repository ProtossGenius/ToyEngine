package com.suremoon.game.kernel.data.units;

import com.suremoon.game.door.infos.CmdInformation;
import com.suremoon.game.door.kernel.*;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.netabout.message.MsgCommand;
import com.suremoon.game.door.units_itf.CommandItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.units.time_tools.GameTimer;
import com.suremoon.game.kernel.initer.cmd_init.CmdInfManager;
import java.awt.*;

/** Created by Water Moon on 2017/11/28. */
public class Command implements CommandItf {
  // command's state is unit's state, like move/attack/m-v/patrol/etc.
  private int agType;
  private long flagTime; // flag time is always be currentTimeMillis;
  UnitItf owner;
  protected int target;
  protected Point tp;
  private boolean appendCmd = false; // 是否是以追加的方式添加命令
  CmdActionItf cmdAction;
  // public Goods toGoods;

  public Command(int agType, Point tp, int target) {
    this(agType, null, tp, target);
  }

  public Command(int agType, UnitItf u, Point tp, int target) {
    this.agType = agType;
    this.owner = u;
    this.tp = tp;
    this.target = target;
    flagTime = System.currentTimeMillis();
  }

  public Command(MsgCommand mc) {
    super();
    agType = mc.getuType();
    CmdInformation ci = CmdInfManager.CIM.getCmdInf(mc.getuType());
    this.tp = mc.getPoint();
    this.target = mc.getTarget();
    setCmdAction(ci.getCmdAction());
    this.target = mc.getTarget();
    flagTime = System.currentTimeMillis();
    this.appendCmd = mc.getAppendCmd();
  }

  @Override
  public CommandItf setOwner(UnitItf u) {
    this.owner = u;
    return this;
  }

  @Override
  public int getAGType() {
    return agType;
  }

  @Override
  public void setAGType(int i) {}

  @Override
  public void updateFlagTime() {
    flagTime = GameTimer.getGt().getCurrentTime();
  }

  @Override
  public AGMessage toMessage() {
    return new MsgCommand(this);
  }

  @Override
  public int getTarget() {
    return target;
  }

  @Override
  public void setTarget(int i) {
    this.target = i;
  }

  @Override
  public void setTargetPoint(Point point) {
    this.tp = point;
  }

  @Override
  public Point getTargetPoint() {
    return tp;
  }

  @Override
  public void setFlagTime(long l) {
    this.flagTime = l;
  }

  @Override
  public long getFlagTime() {
    return this.flagTime;
  }

  @Override
  public void setCmdAction(CmdActionItf cmdAction) {
    this.cmdAction = cmdAction;
  }

  public CmdActionItf getCmdAction() {
    return this.cmdAction;
  }

  public boolean isAppendCmd() {
    return appendCmd;
  }

  @Override
  public UnitItf getOwner() {
    return this.owner;
  }

  public CommandItf setAppendCmd(boolean appendCmd) {
    this.appendCmd = appendCmd;
    return this;
  }
}
