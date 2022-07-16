package com.suremoon.game.door.factorys;

import com.suremoon.game.door.infos.UnitInformation;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.UnitItf;

public interface UnitFactory {
    void putUnitInf(int unitType, UnitInformation uif);
    default void putUnitInf(String unitName, UnitInformation uif){
        putUnitInf(IDManager.getID(unitName), uif);
    }
    UnitInformation getUnitInf(int unitType);
    default UnitInformation getUnitInf(String unitName){
        return getUnitInf(IDManager.getID(unitName));
    }
    UnitItf productUnit(int unitId, double exp);
    void InitUnit(UnitItf unit);
    default UnitItf productUnit(int unitId){
        return productUnit(unitId, 0);
    }
}
