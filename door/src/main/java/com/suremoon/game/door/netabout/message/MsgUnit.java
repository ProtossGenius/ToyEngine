package com.suremoon.game.door.netabout.message;

import com.suremoon.game.door.attribute.ComplexAttribute;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;
import com.suremoon.game.door.units_itf.UnitItf;

/**
 * Created by Water Moon on 2017/12/28.
 */
public class MsgUnit implements AGMessage {
    public MsgUnit(){}
    public MsgUnit(UnitItf unit){
        gid = unit.getGid();
        UnitType = unit.getAGType();
        StateType = unit.getState().getAGType();
        StatePTime = unit.getState().getPassedTime();
        intervalTime = unit.getIntervalTime();
        direct_x = unit.getDirect().X;
        direct_y = unit.getDirect().Y;
        pos_x = unit.getX();
        pos_y = unit.getY();
        width = unit.getWidth();
        height = unit.getHeight();
        ComplexAttribute attrib = unit.getAttribute();
        this.max_hp = (int)attrib.getMaxHp();
        this.hp = (int)attrib.getHp();
        this.max_mp = (int) attrib.getMaxMp();
        this.mp = (int) attrib.getMp();
        this.showName = unit.getShowName();
    }
    public MsgUnit(ByteStream bs){
        gid = bs.getInteger();
        UnitType = bs.getInteger();
        StateType = bs.getInteger();
        StatePTime = bs.getLong();
        intervalTime = bs.getInteger();
        direct_x = bs.getDouble();
        direct_y = bs.getDouble();
        pos_x = bs.getInteger();
        pos_y = bs.getInteger();
        width = bs.getInteger();
        height = bs.getInteger();
        this.max_hp = bs.getInteger();
        this.hp = bs.getInteger();
        this.max_mp = bs.getInteger();
        this.mp = bs.getInteger();
        this.showName = bs.getString();
    }
    public int UnitType, gid, StateType, pos_x, pos_y, width, height, max_hp, hp, max_mp, mp, intervalTime;
    public long StatePTime;
    public double direct_x, direct_y;
    public String showName = "";

    @Override
    public byte[] toBytes() {
        return CJDeal.ByteArrayConnect(CJDeal.int2byte(gid), CJDeal.int2byte(UnitType), CJDeal.int2byte(StateType), CJDeal.long2bytes(StatePTime), CJDeal.int2byte(intervalTime), CJDeal.double2bytes(direct_x), CJDeal.double2bytes(direct_y), CJDeal.int2byte(pos_x), CJDeal.int2byte(pos_y),
                CJDeal.int2byte(width), CJDeal.int2byte(height), CJDeal.int2byte(max_hp), CJDeal.int2byte(hp), CJDeal.int2byte(max_mp), CJDeal.int2byte(mp), CJDeal.string2bytes(showName));
    }

    public static MsgUnit Null = new MsgUnit();
}
