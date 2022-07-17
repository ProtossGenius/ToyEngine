package com.suremoon.gametest.real_game_test.effect_actions;

import com.suremoon.game.door.kernel.GameMapItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.units_itf.EffectItf;
import com.suremoon.game.kernel.data.units.Effect;
import com.suremoon.game.door.kernel.EffectActionItf;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.door.infos.EffectInformation;

import java.awt.*;

/**
 * Created by Water Moon on 2018/04/11.
 */
public class FireHoneActionDo implements EffectActionItf {
    @Override
    public void effectDo(EffectItf effect, WorldItf world, WorldMgrItf wm) {
        effect.setHurt(0.2, 0.2);
        GameMapItf gm = world.getGameMap();
        UnitItf puter = effect.getEffectPutter();
        int passedTime = effect.getPassedTime();
        EffectInformation ei = world.getEffectInf(effect.getAGType());
        if(passedTime > ei.getShower().resList.get("Effect").getLength() * ei.getIntervalTime()){
            effect.setDrop(true);
            return;
        }
        Rectangle rect = effect.toRect();
        gm.getUnitMgr().unitsDo(
            new Rectangle(rect.x-rect.width, rect.y-rect.height, rect.width * 3, rect.height * 3),
            (UnitItf u) ->{
                if(u != puter){
                    if(u.toRect().intersects(effect.toRect())){
                        double before = u.getAttribute().getHp();
                        u.underAttack(puter, effect.getHurt_ad() * puter.getAttribute().getMetal(),effect.getHurt_ap() * puter.getAttribute().getFire());
                        puter.BeHeal((before-u.getAttribute().getHp()) * 0.005);
                    }
                }
                return  true;
            });
    }

    @Override
    public void preventLambda() {
    }
}
