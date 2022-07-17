package com.suremoon.game.door.units_itf;

import com.suremoon.game.door.code_tools.JarLoader;
import com.suremoon.game.door.kernel.AGTypeInf;
import com.suremoon.game.door.kernel.CmdActionItf;
import com.suremoon.game.door.netabout.AGMessageable;
import com.suremoon.game.door.save_about.SerializeAble;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;
import java.awt.*;

public interface CommandItf extends AGMessageable, AGTypeInf, SerializeAble {

  /**
   * 设置命令的拥有者
   *
   * @param u 拥有者
   * @return 自身
   */
  CommandItf setOwner(UnitItf u);

  /**
   * @return 是否是追加的命令
   */
  boolean isAppendCmd();

  UnitItf getOwner();

  Point getTargetPoint();

  void setFlagTime(long time);

  default void updateFlagTime() {
    setFlagTime(System.currentTimeMillis());
  }

  long getFlagTime();

  CmdActionItf getCmdAction();

  void setCmdAction(CmdActionItf cmdAction);

  CommandItf setAppendCmd(boolean appendCmd);

  int getTarget();

  void setTarget(int target);

  void setTargetPoint(Point targetPoint);

  @Override
  default void parseFromBytes(ByteStream byteStream) {
    this.setFlagTime(System.currentTimeMillis() - byteStream.getLong());
    this.setTarget(byteStream.getInteger());
    this.setTargetPoint(new Point(byteStream.getInteger(), byteStream.getInteger()));
    try {
      CmdActionItf action =
          (CmdActionItf) JarLoader.NewClass(byteStream.getString(), JarLoader.ObjectDo.Null);
      action.parseFromBytes(byteStream);
      this.setCmdAction(action);
    } catch (Exception e) {
      e.printStackTrace();
      this.setCmdAction(CmdActionItf.Null);
    }
  }

  @Override
  default byte[] encodeToBytes() {
    return CJDeal.ByteArrayConnect(
        CJDeal.long2bytes(System.currentTimeMillis() - this.getFlagTime()),
        CJDeal.int2byte(getTarget()),
        CJDeal.int2byte(getTargetPoint().x),
        CJDeal.int2byte(getTargetPoint().y),
        CJDeal.string2bytes(getCmdAction().getClass().getName()),
        getCmdAction().encodeToBytes());
  }
}
