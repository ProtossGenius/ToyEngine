package com.suremoon.game.door.factorys;

import com.suremoon.game.door.infos.EffectInformation;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.EffectItf;
import com.suremoon.game.door.units_itf.UnitItf;

public interface EffectFactory {
    EffectItf productEffect(int type, UnitItf unit);
    void putEffectInf(int type, EffectInformation ei);

    default void putEffectInf(String eff_name, EffectInformation ei){
        putEffectInf(IDManager.getID(eff_name), ei);
    }

    EffectInformation getEffectInf(int type);
    default EffectInformation getEffectInf(String eff_name){
        return getEffectInf(IDManager.getID(eff_name));
    }
}
