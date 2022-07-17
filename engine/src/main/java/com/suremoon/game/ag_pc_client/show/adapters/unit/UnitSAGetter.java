package com.suremoon.game.ag_pc_client.show.adapters.unit;

import com.suremoon.game.kernel.data.units.Unit;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by Water Moon on 2018/3/14.
 */
public class UnitSAGetter{
    protected UnitSAGetter(){
        usas = new HashMap<>();
    }
    HashMap<Integer, UnitSAdapter> usas;
    public static final UnitSAGetter USAG = new UnitSAGetter();
    public static UnitSAGetter getUsag(){
        return USAG;
    }
    public void show(Graphics gp, Unit unit, Point focusePint) throws Exception {
        getUsa(unit.getAGType()).show(gp, unit, focusePint);
    }
    public UnitSAdapter getUsa(int type){
        if(usas.containsKey(type)){
            return usas.get(type);
        }else{
            UnitSAdapter usa = new UnitSAdapter(type);
            usas.put(type, usa );
            return usa;
        }
    }
}
