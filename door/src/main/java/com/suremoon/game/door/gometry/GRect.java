package com.suremoon.game.door.gometry;

import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.kernel.manager.GRectMgrItf;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;
import java.awt.*;

/** Created by Water Moon on 2018/1/10. */
public class GRect implements GRectItf {
  Dimension size;
  PointF pos;
  //    ArrayList<GRInputListener> inputEvents = new ArrayList<>();
  GRectMgrItf manager = GRectMgrItf.Null;

  boolean isDrop;
  int walkLevel;
  protected PointF direct = new PointF(0, 0);

  protected PointF footPos = PointF.DIRECTION_ZERO;

  public GRect() {
    this(0, 0, 0, 0);
  }

  public GRect(GRect rhs, Object move) {
    this.size = rhs.size;
    this.pos = rhs.pos;
    this.manager = rhs.manager;
    this.isDrop = rhs.isDrop;
    this.direct = rhs.direct;
    this.footPos = rhs.footPos;

    rhs.size = null;
    rhs.pos = null;
    rhs.manager = null;
    rhs.direct = null;
    rhs.footPos = null;
  }

  public GRect(int width, int height) {
    this(0, 0, width, height);
  }

  public GRect(int x, int y, int width, int height) {
    size = new Dimension(width, height);
    pos = new PointF(x, y);
  }

  public GRect(Rectangle rect) {
    this(rect.x, rect.y, rect.width, rect.height);
  }

  public GRect(Point pos, Point size) {
    this(pos.x, pos.y, size.x, size.y);
  }

  public GRect(PointF pos, Point size) {
    this(pos.getIntX(), pos.getIntY(), size.x, size.y);
  }

  @Override
  public Rectangle toRect() {
    return new Rectangle(pos.getIntX(), pos.getIntY(), size.width, size.height);
  }

  public boolean pointInGRect(Point p) {
    return p.x - pos.getX() >= 0
        && p.x - pos.getX() <= size.width
        && p.y - pos.getY() >= 0
        && p.y - pos.getY() <= size.height;
  }

  @Override
  public Dimension getSize() {
    return size;
  }

  @Override
  public void setSize(Point size) {
    this.size = new Dimension(size.x, size.y);
  }

  @Override
  public void setSize(int width, int height) {
    size.width = width;
    size.height = height;
  }

  @Override
  public void moveAdd(PointF cg) {
    if (cg.notLegitimacy()) {
      return;
    }
    PointF newPos = this.pos.Add(cg);
    setPos(newPos);
  }

  @Override
  public PointF getPos() {
    return new PointF(pos);
  }

  @Override
  public void setPutPos(double x, double y) {
    setPos(x - footPos.getX() * getWidth(), y - footPos.getY() * getHeight());
  }

  @Override
  public void setPos(double x, double y) {
    if (Double.isNaN(x + y)) {
      return;
    }
    // update to manager. TODO: if in same block can update directly.
    manager.update(this, (int) x, (int) y);
  }

  @Override
  public void setPosWithoutUpdateManager(double x, double y) {
    this.pos.setPoint(x, y);
  }

  @Override
  public int getX() {
    return pos.getIntX();
  }

  @Override
  public int getY() {
    return pos.getIntY();
  }

  @Override
  public int getWidth() {
    return size.width;
  }

  @Override
  public int getHeight() {
    return size.height;
  }

  public boolean judgeNext() {
    return true;
  }

  @Override
  public PointF getFootPos() {
    return getPos().Add(new PointF(footPos.getX() * getWidth(), footPos.getY() * getHeight()));
  }

  @Override
  public void setFootPosPro(PointF footPos) {
    this.footPos = footPos;
  }

  @Override
  public PointF getFootPosPro() {
    return this.footPos;
  }

  public GRectMgrItf getGRectMgr() {
    return manager;
  }

  /**
   * manager will never be null.
   *
   * @param grm 矩形管理器
   */
  @Override
  public void setGRectMgr(GRectMgrItf grm) {
    if (grm == null) {
      return;
    }
    this.manager = grm;
  }

  @Override
  public String toString() {
    return super.toString() + "  Pos = " + pos.toString() + "Size = " + size.toString();
  }

  @Override
  public void doCalc(WorldItf world, WorldMgrItf wm) {}

  public PointF getDirect() {
    return direct;
  }

  public void setDirect(PointF direct) {
    if (direct.equals(PointF.DIRECTION_ZERO) || direct.notLegitimacy()) {
      return;
    }
    this.direct = direct;
  }

  @Override
  public boolean isDrop() {
    return isDrop;
  }

  @Override
  public void setDrop(boolean drop) {
    isDrop = drop;
  }

  @Override
  public void parseFromBytes(ByteStream byteStream) {
    this.size.width = byteStream.getInteger();
    this.size.height = byteStream.getInteger();
    this.pos.parseFromBytes(byteStream);
    this.isDrop = byteStream.getBytes(1)[0] == 1;
    this.direct.parseFromBytes(byteStream);
    this.footPos.parseFromBytes(byteStream);
  }

  @Override
  public byte[] encodeToBytes() {
    return CJDeal.ByteArrayConnect(
        CJDeal.int2byte(this.size.width),
        CJDeal.int2byte(this.size.height),
        this.pos.encodeToBytes(),
        this.isDrop ? new byte[] {1} : new byte[] {0},
        this.direct.encodeToBytes(),
        this.footPos.encodeToBytes());
  }
}
