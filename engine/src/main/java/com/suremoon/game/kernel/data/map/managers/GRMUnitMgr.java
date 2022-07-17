package com.suremoon.game.kernel.data.map.managers;

import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.kernel.manager.GRectMgrItf;
import com.suremoon.game.door.kernel.manager.UnitDoItf;
import com.suremoon.game.door.kernel.manager.UnitMgrItf;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.netabout.message.MsgUnit;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.kernel.grect_manager.GRectManager;
import java.awt.*;
import java.util.HashMap;
import java.util.List;

public class GRMUnitMgr implements UnitMgrItf {
  GRectManager grm;
  HashMap<Integer, UnitItf> uMap;
  byte[] lock = new byte[0];

  public GRMUnitMgr(Dimension mapSize, Dimension blockSize) {
    grm = new GRectManager(mapSize, blockSize);
    uMap = new HashMap<>();
  }

  @Override
  public void addUnit(UnitItf unit) {
    unit.setGRectMgr(this.grm);
    synchronized (lock) {
      grm.addGRect(unit);
      uMap.put(unit.getGid(), unit);
    }
  }

  @Override
  public void removeUnit(UnitItf unit) {
    unit.setGRectMgr(GRectMgrItf.Null);
    synchronized (lock) {
      grm.delGRect(unit);
      uMap.remove(unit.getGid());
    }
  }

  @Override
  public Unit[] getUnits() {
    List<GRectItf> list = grm.getAllGRects();
    Unit res[] = new Unit[list.size()];
    int lns = list.size();
    for (int i = 0; i < lns; i++) {
      res[i] = (Unit) list.get(i);
    }
    return res;
  }

  @Override
  public UnitItf[] getUnits(Rectangle screenRect) {
    List<GRectItf> list = grm.getGRects(screenRect);
    Unit res[] = new Unit[list.size()];
    int lns = list.size();
    for (int i = 0; i < lns; i++) {
      res[i] = (Unit) list.get(i);
    }
    return res;
  }

  @Override
  public boolean unitsDo(Rectangle screenRect, UnitDoItf ud) {
    return grm.GRectDo(screenRect, (GRectItf gRect) -> ud.Do((Unit) gRect));
  }

  @Override
  public AGMessage[] getShowers(Rectangle screenRect) {
    List<GRectItf> list = grm.getGRects(screenRect);
    AGMessage msgs[] = new AGMessage[list.size()];
    int lns = list.size();
    for (int i = 0; i < lns; i++) {
      msgs[i] = new MsgUnit(((Unit) list.get(i)));
    }
    return msgs;
  }

  @Override
  public UnitItf getUnit(int ugid) {
    synchronized (lock) {
      return uMap.get(ugid);
    }
  }

  @Override
  public int size() {
    return grm.size();
  }
}
