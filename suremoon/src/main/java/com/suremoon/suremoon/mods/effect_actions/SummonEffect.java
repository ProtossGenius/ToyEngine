package com.suremoon.suremoon.mods.effect_actions;

import com.suremoon.game.door.infos.EffectInformation;
import com.suremoon.game.door.kernel.EffectActionItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.EffectItf;
import com.suremoon.game.door.units_itf.UnitItf;

public class SummonEffect implements EffectActionItf {
    public SummonEffect(Summon summon) {
        this.summon = summon;
    }

    Summon summon;

    @Override
    public void effectDo(EffectItf effect, WorldItf world, WorldMgrItf worldMgrItf) {
        int passedTime = effect.getPassedTime();
        EffectInformation ei = world.getEffectInf(effect.getAGType());
        if (passedTime > ei.getShower().resList.get("Effect").getLength() * ei.getIntervalTime()) {
            effect.setDrop(true);
            UnitItf unit = summon.Summon();
            unit.setPutPos(effect.getFootPos());
            world.addCalcUnit(unit);
            return;
        }
    }

    @Override
    public void preventLambda() {

    }

    public interface Summon {
        UnitItf Summon();
    }
}
