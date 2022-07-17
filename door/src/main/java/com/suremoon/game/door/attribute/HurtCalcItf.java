package com.suremoon.game.door.attribute;

import com.suremoon.game.door.units_itf.UnitItf;

public interface HurtCalcItf {
    /**
     * @param sHurt 攻击方的攻击属性
     * @return hurt.
     */
    double underAttack(UnitItf unit, ElementPriorities sHurt);
    HurtCalcItf Null = (unit, sHurt) -> {
        AttributeAdapter attr = unit.getAttribute();
        return oneProperty(sHurt.metal, attr.getFire()) +
                oneProperty(sHurt.wood, attr.getMetal()) +
                oneProperty(sHurt.water, attr.getEarth()) +
                oneProperty(sHurt.fire, attr.getWater()) +
                oneProperty(sHurt.earth, attr.getWood()) +
                // think thunder product by wood&water.
                oneProperty(sHurt.thunder, attr.getMetal() * 0.5 + attr.getEarth() * 0.5) +
                // think wind product by hot&cool.
                oneProperty(sHurt.wind, attr.getIce() * 0.5 + attr.getFire() * 0.5) +
                // think ice product by cool&earth.
                oneProperty(sHurt.ice, attr.getFire() * 0.5 + attr.getWood() * 0.5);
    };

    static double oneProperty(double s, double m){
        if(m < 0)return (int)s;
        return s - s / (m + 10);
    }
}
