package com.suremoon.game.door.units_itf;

import com.suremoon.game.door.attribute.ComplexAttribute;
import com.suremoon.game.door.kernel.AGTypeInf;
import com.suremoon.game.door.kernel.DieDo;
import com.suremoon.game.door.kernel.GRectItf;
import com.suremoon.game.door.save_about.SerializeAble;

public interface GoodsItf extends AGTypeInf, GRectItf, SerializeAble {

    /**
     * @return 唯一ID
     */
    int getGid();

    void setGid(int gid);

    ComplexAttribute getAttribute();

    /**
     * @return 动画间隔时间
     */
    int getIntervalTime();

    /**
     * @param hel 治疗
     */
    void BeHeal(double hel);

    void BeHurt(double hurt);

    void setDieDo(DieDo dieDo);

    String getShowName();

    void setShowName(String showName);
}
