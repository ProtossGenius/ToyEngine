package com.suremoon.game.kernel.grect_manager;

import com.suremoon.game.door.kernel.*;
import com.suremoon.game.door.kernel.manager.GRectMgrItf;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class GRectManager implements GRectMgrItf {
  private Rectangle area;
  int _size = 0;

  GRectMgrItf _sons[];

  public GRectManager(Dimension mapSize, Dimension blockSize) {
    this.area = new Rectangle(new Point(0, 0), mapSize);
    parseSons(mapSize, blockSize);
  }

  protected GRectManager(Rectangle area, Dimension blockSize) {
    this.area = area;
    parseSons(this.area.getSize(), blockSize);
  }

  private void parseSons(Dimension mapSize, Dimension blockSize) {
    Dimension subSize = new Dimension(mapSize.width / 2, mapSize.height / 2);
    Point loc = area.getLocation();
    if (mapSize.width <= blockSize.width && mapSize.height <= blockSize.height) { // leaf.
      _sons = new GRectMgrItf[] {new LeafManager(this.area)};
    } else if (mapSize.width > blockSize.width && mapSize.height > blockSize.height) { // 4-sub
      _sons = new GRectMgrItf[4];
      _sons[0] = subMgr(new Rectangle(area.getLocation(), subSize), blockSize);

      _sons[1] =
          subMgr(
              new Rectangle(
                  new Point(loc.x + subSize.width + 1, loc.y),
                  new Dimension(mapSize.width - subSize.width - 1, subSize.height)),
              blockSize);
      _sons[2] =
          subMgr(
              new Rectangle(
                  new Point(loc.x, loc.y + subSize.height + 1),
                  new Dimension(subSize.width, mapSize.height - subSize.height - 1)),
              blockSize);
      _sons[3] =
          subMgr(
              new Rectangle(
                  new Point(loc.x + subSize.width + 1, loc.y + subSize.height + 1),
                  new Dimension(
                      mapSize.width - subSize.width - 1, mapSize.height - subSize.height - 1)),
              blockSize);
    } else if (mapSize.width > blockSize.width) { // split horizon
      _sons = new GRectMgrItf[2];
      _sons[0] =
          subMgr(new Rectangle(loc, new Dimension(subSize.width, mapSize.height)), blockSize);
      _sons[1] =
          subMgr(
              new Rectangle(
                  new Point(loc.x + subSize.width + 1, loc.y),
                  new Dimension(mapSize.width - subSize.width - 1, mapSize.height)),
              blockSize);
    } else { // vertical split
      _sons = new GRectMgrItf[2];
      _sons[0] =
          subMgr(new Rectangle(loc, new Dimension(mapSize.width, subSize.height)), blockSize);
      _sons[1] =
          subMgr(
              new Rectangle(
                  new Point(loc.x, loc.y + subSize.height + 1),
                  new Dimension(mapSize.width, mapSize.height - subSize.height - 1)),
              blockSize);
    }
  }

  private GRectMgrItf subMgr(Rectangle area, Dimension blockSize) {
    if (area.width <= blockSize.width && area.height <= blockSize.height) {
      return new LeafManager(area);
    }
    return new GRectManager(area, blockSize);
  }

  @Override
  public List<GRectItf> getGRects(Rectangle rectangle) {
    List<GRectItf> res = new LinkedList<>();
    for (GRectMgrItf son : _sons) {
      if (son.Area().intersects(rectangle)) {
        res.addAll(son.getGRects(rectangle));
      }
    }
    return res;
  }

  @Override
  public boolean GRectDo(Rectangle rectangle, GRectDoItf gRectDoItf) {
    for (GRectMgrItf son : _sons) {
      if (son.Area().intersects(rectangle)) {
        if (!son.GRectDo(rectangle, gRectDoItf)) {
          return false;
        }
      }
    }
    return true;
  }

  @Override
  public void delGRect(GRectItf gRectItf) {
    Point pos = gRectItf.getFootPos().toPoint();
    for (GRectMgrItf son : _sons) {
      if (GRectMgrItf.inArea(son.Area(), pos)) {
        son.delGRect(gRectItf);
        return;
      }
    }
  }

  @Override
  public void update(GRectItf rect, double newPosX, double newPosY) {
    Point oldFootPos = rect.getFootPos().toPoint();
    Point newFootPos =
        new Point(
            oldFootPos.x + (int) newPosX - rect.getX(), oldFootPos.y + (int) newPosY - rect.getY());
    GRectMgrItf oldSon = null, newSon = null;
    for (GRectMgrItf son : _sons) {
      if (GRectMgrItf.inArea(son.Area(), oldFootPos)) {
        oldSon = son;
      }
      if (GRectMgrItf.inArea(son.Area(), newFootPos)) {
        newSon = son;
      }
    }
    if (oldSon == null || newSon == null) {
      return;
    }
    if (oldSon == newSon) {
      oldSon.update(rect, newPosX, newPosY);
    }

    oldSon.delGRect(rect);
    rect.setPosWithoutUpdateManager(newPosX, newPosY);
    newSon.addGRect(rect);
  }

  @Override
  public void addGRect(GRectItf gRectItf) {
    Point pos = gRectItf.getFootPos().toPoint();
    for (GRectMgrItf son : _sons) {
      if (GRectMgrItf.inArea(son.Area(), pos)) {
        son.addGRect(gRectItf);
        return;
      }
    }
  }

  @Override
  public List<GRectItf> getAllGRects() {
    List<GRectItf> res = new LinkedList<>();
    for (GRectMgrItf son : _sons) {
      res.addAll(son.getAllGRects());
    }
    return res;
  }

  @Override
  public int size() {
    return _size;
  }

  @Override
  public Rectangle Area() {
    return this.area;
  }
}
