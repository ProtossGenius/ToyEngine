package com.suremoon.game.door.units_itf;

import com.suremoon.game.door.kernel.AGTypeInf;
import com.suremoon.game.door.kernel.CalcAble;

public interface StateItf extends AGTypeInf, CalcAble {
    long getPassedTime();
    StateItf createState(UnitItf unit);
    void setUnitType(int unitType);
    boolean IsLoop();
    UnitItf getUnit();
    void setLoop(boolean loop);
}
