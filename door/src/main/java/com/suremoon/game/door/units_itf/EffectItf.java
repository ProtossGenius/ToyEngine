package com.suremoon.game.door.units_itf;

import com.suremoon.game.door.attribute.ElementPriorities;
import com.suremoon.game.door.kernel.AGTypeInf;
import com.suremoon.game.door.kernel.CalcAble;
import com.suremoon.game.door.kernel.EffectActionItf;
import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.netabout.AGMessageable;
import com.suremoon.game.door.save_about.SerializeAble;
import com.suremoon.game.door.tools.ByteStream;

public interface EffectItf extends GRectItf, AGTypeInf, AGMessageable, CalcAble, SerializeAble {
    UnitItf getEffectPutter();
    /**
     * @return global id.
     */
    int getGid();
    void setEffectPutter(UnitItf effectPutter);
    void setEffectType(int effectType);
    int getPassedTime();
    default double getHurt_ad(){return getHurt().metal;}
    default double getHurt_ap(){return getHurt().fire;}
    default void setHurt(double ad, double ap){setHurt(new ElementPriorities(ad, ap));}
    void setHurt(ElementPriorities elementPriorities);
    ElementPriorities getHurt();
    void setEffectAction(EffectActionItf ea);
}
