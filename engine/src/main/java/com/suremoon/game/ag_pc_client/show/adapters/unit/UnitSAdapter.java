package com.suremoon.game.ag_pc_client.show.adapters.unit;

import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.ag_pc_client.show.UnitMoreShow;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.kernel.initer.unit_init.UnitInfManager;
import com.suremoon.game.door.infos.UnitInformation;

import java.awt.*;

/**
 * Created by Water Moon on 2018/3/14.
 */
public class UnitSAdapter {
    AGSAdapter shower;
    public UnitSAdapter(int type){
        UnitInformation ui = UnitInfManager.UIM.getUnitInf(type);
        shower = ui.getShower();
    }
    public void show(Graphics gp, Unit unit, Point focusPoint) throws Exception {
        shower.show(gp, unit.getState().getAGType(), unit, unit.getDirect(), unit.getState().getPassedTime(), unit.getIntervalTime(), unit.getState().IsLoop(), focusPoint);
        UnitMoreShow.show(gp, unit, focusPoint);
    }
}
