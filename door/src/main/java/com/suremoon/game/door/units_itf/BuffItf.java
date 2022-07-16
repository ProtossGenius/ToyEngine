package com.suremoon.game.door.units_itf;

import com.suremoon.game.door.kernel.CalcAble;
import com.suremoon.game.door.nils.NullBuff;

public interface BuffItf extends CalcAble {

    /**
     * ÉèÖÃBuffµÄÓµÓÐÕß
     * @param u Unit
     * @return ×ÔÉí
     */
    BuffItf setOwner(UnitItf u);

    /**
     * @return Õâ¸öbuffÊÇ·ñÒÀ¾ÉÓÐÐ§£¬Èç¹ûÊ§Ð§µÄ»°½«ÒÆ³ý¸ÃBuff¡£
     */
    boolean isAlive();
    BuffItf Null = new NullBuff();
}
