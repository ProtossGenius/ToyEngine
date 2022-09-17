package com.suremoon.game.door.kernel;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.manager.GRectMgrItf;
import com.suremoon.game.door.save_about.SerializeAble;
import java.awt.*;

public interface GRectItf extends CalcAble, SerializeAble {
  /**
   * @return 转换成awt的Rect
   */
  Rectangle toRect();

  /**
   * @return 获得GRect的大小
   */
  Dimension getSize();

  /**
   * @param size GRect的大小
   */
  void setSize(Point size);

  default void setSize(Dimension size) {
    setSize(size.width, size.height);
  }

  void setSize(int width, int height);
  /**
   * 通过增加值的方式使之移动,同时更新在管理器中的位置
   *
   * @param cg 移动向量
   */
  void moveAdd(PointF cg);

  /**
   * @return 左上角的位置
   */
  PointF getPos();

  /**
   * 设置落脚位置
   *
   * @param x 横坐标
   * @param y 纵坐标
   */
  void setPutPos(double x, double y);

  /**
   * 设置落脚位置
   *
   * @param pos 位置
   */
  default void setPutPos(Point pos) {
    setPutPos(pos.x, pos.y);
  }

  default void setPutPos(PointF pos) {
    setPutPos(pos.X, pos.Y);
  }

  /**
   * 设置左上角位置，且更新其在manager中的位置
   *
   * @param x 横坐标
   * @param y 纵坐标
   */
  void setPos(double x, double y);

  default void setPos(Point pos) {
    setPos(pos.x, pos.y);
  }

  default void setPos(PointF pos) {
    setPos(pos.X, pos.Y);
  }

  /** 仅设置位置 */
  void setPosWithoutUpdateManager(double x, double y);

  default void setPosWithoutUpdateManager(Point pos) {
    setPosWithoutUpdateManager(pos.x, pos.y);
  }

  default void setPosWithoutUpdateManager(PointF pos) {
    setPosWithoutUpdateManager(pos.X, pos.Y);
  }

  /**
   * @return 获得横坐标
   */
  int getX();

  /**
   * @return 获得纵坐标
   */
  int getY();

  int getWidth();

  int getHeight();

  /**
   * @return 获得脚所在的坐标
   */
  PointF getFootPos();

  /**
   * @param footPos 脚在矩形中的相对位置
   */
  void setFootPosPro(PointF footPos);

  /**
   * @return 脚在矩形中的相对位置
   */
  PointF getFootPosPro();

  /**
   * @param grm 设置所在的管理器
   */
  void setGRectMgr(GRectMgrItf grm);

  void setDirect(PointF direct);

  PointF getDirect();

  /**
   * 丢弃的单位会被从计算队列及管理器中丢弃
   *
   * @param isDrop 是否要丢弃该单位
   */
  void setDrop(boolean isDrop);

  /**
   * @return 是否要丢弃该单位
   */
  boolean isDrop();

  // 角色的透明度
  int getTransparency();

  void setTransparency(int trans);
}
