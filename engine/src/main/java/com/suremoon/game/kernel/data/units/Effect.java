package com.suremoon.game.kernel.data.units;

import com.suremoon.game.door.attribute.ElementPriorities;
import com.suremoon.game.door.gometry.GRect;
import com.suremoon.game.door.kernel.*;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.netabout.message.MsgEffect;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;
import com.suremoon.game.door.units_itf.EffectItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.units.time_tools.GameTimer;

/** Created by Water Moon on 2017/12/19. */
public class Effect extends GRect implements EffectItf {
  protected long start_time, current_time;
  protected UnitItf effectPutter;
  int effectType;
  int gid = 0;
  ElementPriorities hurt = new ElementPriorities();
  EffectActionItf ea;

  public Effect(MsgEffect msg) {
    this.start_time = 0;
    this.current_time = msg.getPTime();
    this.effectType = msg.getEffectType();
    this.setPos(msg.getPos_x(), msg.getPos_y());
    this.setSize(msg.getWidth(), msg.getHeight());
    this.gid = msg.getGid();
  }

  public Effect(long time, int gid) {
    current_time = start_time = time;
    this.gid = gid;
  }

  @Override
  public void doCalc(WorldItf world, WorldMgrItf wm) {
    super.doCalc(world, wm);
    current_time = GameTimer.getGt().getCurrentTime();
    ea.effectDo(this, world, wm);
  }

  @Override
  public AGMessage toMessage() {
    return new MsgEffect(this);
  }

  @Override
  public UnitItf getEffectPutter() {
    return effectPutter;
  }

  @Override
  public void setEffectPutter(UnitItf effectPutter) {
    this.effectPutter = effectPutter;
  }

  @Override
  public int getAGType() {
    return effectType;
  }

  @Override
  public void setAGType(int i) {
    this.effectType = i;
  }

  @Override
  public void setEffectType(int effectType) {
    this.effectType = effectType;
  }

  @Override
  public int getPassedTime() {
    return (int) (current_time - start_time);
  }

  @Override
  public void setEffectAction(EffectActionItf ea) {
    this.ea = ea;
  }

  @Override
  public ElementPriorities getHurt() {
    return hurt;
  }

  @Override
  public void setHurt(ElementPriorities hurt) {
    this.hurt = hurt;
  }

  @Override
  public int getGid() {
    return gid;
  }

  @Override
  public void parseFromBytes(ByteStream byteStream) {
    super.parseFromBytes(byteStream);
    this.current_time = System.currentTimeMillis();
    this.start_time = System.currentTimeMillis() - byteStream.getLong();
  }

  @Override
  public byte[] encodeToBytes() {
    return CJDeal.ByteArrayConnect(super.encodeToBytes());
  }
}
