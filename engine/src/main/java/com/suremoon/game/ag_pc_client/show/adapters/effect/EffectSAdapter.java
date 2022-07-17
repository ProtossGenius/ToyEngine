package com.suremoon.game.ag_pc_client.show.adapters.effect;

import com.suremoon.game.door.client.AGSAdapter;
import com.suremoon.game.kernel.data.units.Effect;
import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.kernel.initer.effect_init.EffectInfManager;
import com.suremoon.game.door.infos.EffectInformation;

import java.awt.*;

/**
 * Created by Water Moon on 2018/4/10.
 */
public class EffectSAdapter {
    AGSAdapter shower;
    EffectInformation ei;
    public EffectSAdapter(int effectType){
        //todo change!!!!
        ei = EffectInfManager.EIM.getEffectInf(effectType);
        shower = ei.getShower();
    }
    public void show(Graphics gp, Effect effect, Point point){
        shower.show(gp, "Effect", effect, PointF.DIRECTION_ZERO, effect.getPassedTime(), ei.getIntervalTime(), true, point);
    }
}
