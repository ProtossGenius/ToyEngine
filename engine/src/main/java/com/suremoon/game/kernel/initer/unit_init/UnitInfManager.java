package com.suremoon.game.kernel.initer.unit_init;

import com.suremoon.game.door.factorys.UnitFactory;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.infos.UnitInformation;
import com.suremoon.game.door.units_itf.GoodsItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.kernel.data.units.Goods;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.kernel.initer.state_init.StateInfManager;

import java.util.HashMap;

/**
 * Created by Water Moon on 2018/3/6.
 */
public class UnitInfManager implements UnitFactory {
    public static final UnitInfManager UIM = new UnitInfManager();
    private static final byte[] lock = new byte[0];
    static Integer unitGId = 0;
    HashMap<Integer, UnitInformation> unitInfMap;

    protected UnitInfManager() {
        unitInfMap = new HashMap<>();
    }

    public static final UnitInfManager getUim() {
        return UIM;
    }

    public void putUnitInf(int unitType, UnitInformation uif) {
        unitInfMap.put(unitType, uif);
    }

    public UnitInformation getUnitInf(int unitType) {
        return unitInfMap.get(unitType);
    }

    public UnitItf productUnit(int unitId, double exp) {
        // todo: code about bag
        Unit unit = new Unit(unitId);
        synchronized (lock) {
            unit.setGid(unitGId++);
        }
        InitUnit(unit);
        return unit;
    }

    @Override
    public GoodsItf productGoods(int unitId) {
        var goods = new Goods(unitId);
        synchronized (lock) {
            goods.setGid(unitGId++);
        }
        InitUnit(goods);
        return goods;
    }

    @Override
    public void InitUnit(UnitItf unit) {
        unit.setState(StateInfManager.getSM().productState("paused"));
        UnitInformation unitInf = getUnitInf(unit.getAGType());
        unit.setSize(unitInf.getWidth(), unitInf.getHeight());
        unit.setDirect(new PointF(1.0, 0));
        PointF footPos = unitInf.getFootPos();
        unit.setFootPosPro(footPos);
        var attr = unit.getAttribute();
        attr.setMetal(50);
        attr.setFire(50);
        attr.setMaxHp(500);
        attr.setHp(500);
        unit.setShowName(unitInf.getShowName());
    }

    public UnitItf productUnit(int unitId) {
        return productUnit(unitId, 0);
    }
}
