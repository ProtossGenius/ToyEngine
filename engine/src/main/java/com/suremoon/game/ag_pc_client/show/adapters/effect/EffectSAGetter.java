package com.suremoon.game.ag_pc_client.show.adapters.effect;

import com.suremoon.game.kernel.data.units.Effect;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Water Moon on 2018/4/10.
 */
public class EffectSAGetter {public static final EffectSAGetter ESAG = new EffectSAGetter();
    HashMap<Integer, EffectSAdapter> esas;
    protected EffectSAGetter(){
        esas = new HashMap<>();
    }
    public static EffectSAGetter getTSAG(){
        return ESAG;
    }
    public EffectSAdapter getEsa(int effectType){
        if(esas.containsKey(effectType)){
            return esas.get(effectType);
        }else{
            EffectSAdapter esa = new EffectSAdapter(effectType);
            esas.put(effectType, esa);
            return esa;

        }
    }
    public void show(Graphics gp, Effect effect, Point focusPoint){
        getEsa(effect.getAGType()).show(gp, effect, focusPoint);
    }

}
