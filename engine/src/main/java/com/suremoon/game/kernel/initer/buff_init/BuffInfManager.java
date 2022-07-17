package com.suremoon.game.kernel.initer.buff_init;

import com.suremoon.game.door.code_tools.JarLoader;
import com.suremoon.game.door.factorys.BuffFactory;
import com.suremoon.game.door.units_itf.BuffItf;

/**
 * Created by Water Moon on 2018/3/6.
 */
public class BuffInfManager implements BuffFactory {
    public static final BuffInfManager bim = new BuffInfManager();
    protected BuffInfManager(){

    }
    public static BuffInfManager getBIM(){
        return bim;
    }
    public BuffItf productBuff(String inf){
        BuffLoader buffLoader = new BuffLoader();
        try {
            JarLoader.NewClass(inf, buffLoader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffLoader.getBuff();
    }
}

class BuffLoader implements JarLoader.ObjectDo{
    BuffItf buff = BuffItf.Null;
    @Override
    public void Do(Object o) {
        if(o instanceof BuffItf){
            buff = (BuffItf) o;
        }
    }

    BuffItf getBuff(){
        return buff;
    }
}