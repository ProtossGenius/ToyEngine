package com.suremoon.game.kernel.data.units;

import com.suremoon.game.door.kernel.*;
import com.suremoon.game.kernel.data.units.time_tools.GameTimer;
import com.suremoon.game.kernel.data.units.time_tools.SMPlayer;
import com.suremoon.game.door.units_itf.StateItf;
import com.suremoon.game.door.units_itf.UnitItf;

/**
 * Created by Water Moon on 2017/11/28.
 */
public class State implements StateItf {
    UnitItf unit;
    protected boolean isLoop = true;
    public SMPlayer smp = new SMPlayer(System.currentTimeMillis());
    public State(){
    }
//    StateDo ac;
    int UnitType;
    public State(Unit unit, int unitType){
        this();
        UnitType = unitType;
        this.unit = unit;
    }
    @Override
    public void doCalc(WorldItf world, WorldMgrItf wm){
        smp.Update(GameTimer.getGt().getCurrentTime());
    }
    @Override
    public long getPassedTime(){
        return smp.getPassedTime();
    }

    @Override
    public StateItf createState(UnitItf u){
        this.unit = u;
        return this;
    }

    @Override
    public int getAGType() {
        return UnitType;
    }

    @Override
    public void setAGType(int i) {

    }

    @Override
    public void setUnitType(int unitType) {
        UnitType = unitType;
    }

    @Override
    public boolean IsLoop() {
        return isLoop;
    }

    @Override
    public UnitItf getUnit() {
        return unit;
    }

    @Override
    public void setLoop(boolean loop) {
        isLoop = loop;
    }
}
