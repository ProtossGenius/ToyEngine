package com.suremoon.game.kernel.initer.buff_init;

import com.suremoon.game.door.attribute.Buff;
import com.suremoon.game.door.factorys.BuffFactory;

/**
 * Created by Water Moon on 2018/3/6.
 */
public class BuffInfManager implements BuffFactory {
    public static final BuffInfManager bim = new BuffInfManager();

    protected BuffInfManager() {
    }

    public static BuffInfManager getBIM() {
        return bim;
    }

    public Buff productBuff(String buffId) {
        return null;
    }
}

