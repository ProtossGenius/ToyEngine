package com.suremoon.game.kernel.grect_manager;

import com.suremoon.game.door.kernel.GRectDoItf;
import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.kernel.manager.GRectMgrItf;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class LeafManager implements GRectMgrItf {
  public LeafManager(Rectangle area) {
    this.area = area;
    list = new LinkedList<>();
  }

  @Override
  public List<GRectItf> getGRects(Rectangle rectangle) {
    List<GRectItf> res = new LinkedList<>();
    synchronized (this.list) {
      for (GRectItf gRect : this.list) {
        if (GRectMgrItf.inArea(rectangle, gRect.getFootPos().toPoint())) {
          res.add(gRect);
        }
      }
    }
    return res;
  }

  @Override
  public boolean GRectDo(Rectangle rectangle, GRectDoItf gRectDoItf) {
    List<GRectItf> clone = getAllGRects();
    for (GRectItf gRect : clone) {
      if (GRectMgrItf.inArea(rectangle, gRect.getFootPos().toPoint())) {
        if (!gRectDoItf.ActionReturnShouldContinue(gRect)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public void delGRect(GRectItf gRectItf) {
    synchronized (list) {
      for (Iterator<GRectItf> itor = list.iterator(); itor.hasNext(); ) {
        if (itor.next() == gRectItf) {
          itor.remove();
          return;
        }
      }
    }
  }

  @Override
  public void update(GRectItf rect, double newPosX, double newPosY) {
    rect.setPosWithoutUpdateManager(newPosX, newPosY);
  }

  @Override
  public void addGRect(GRectItf gRectItf) {
    synchronized (list) {
      list.add(gRectItf);
    }
  }

  @Override
  public List<GRectItf> getAllGRects() {
    List<GRectItf> res = new LinkedList<>();
    synchronized (list) {
      res.addAll(list);
      return res;
    }
  }

  @Override
  public int size() {
    synchronized (list) {
      return list.size();
    }
  }

  @Override
  public Rectangle Area() {
    return this.area;
  }

  List<GRectItf> list;
  Rectangle area;
}
