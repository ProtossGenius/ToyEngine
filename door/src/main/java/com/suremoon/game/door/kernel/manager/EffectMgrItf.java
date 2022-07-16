package com.suremoon.game.door.kernel.manager;

import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.units_itf.EffectItf;

import java.awt.*;

/**
 * Created by Water Moon on 2017/12/19.
 */
public interface EffectMgrItf  {
    EffectItf[] getEffects();
    void removeEffect(EffectItf e);
    void addEffect(EffectItf e);
    AGMessage[] getShowers(Rectangle screenRect);
    int size();
}
