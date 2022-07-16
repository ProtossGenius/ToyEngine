package com.suremoon.game.door.netabout.message;

import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;
import com.suremoon.game.door.units_itf.EffectItf;

/**
 * Created by Water Moon on 2017/12/28.
 */
public class MsgEffect implements AGMessage {//4+4+4+4+4+8+8+8=44
    protected int EffectType, pos_x, pos_y, width, height, gid;
    protected int PTime;
    protected double direct_x, direct_y;
    public MsgEffect(){}
    public MsgEffect(ByteStream bs){
        this.EffectType = bs.getInteger();
        this.PTime = bs.getInteger();
        direct_x = bs.getDouble();
        direct_y = bs.getDouble();
        pos_x = bs.getInteger();
        pos_y = bs.getInteger();
        this.width = bs.getInteger();
        this.height = bs.getInteger();
        this.gid = bs.getInteger();
    }

    public MsgEffect(EffectItf effect) {
        super();
        EffectType = effect.getAGType();
        PTime = effect.getPassedTime();
        direct_x = effect.getDirect().X;
        direct_y = effect.getDirect().Y;
        pos_x = effect.getX();
        pos_y = effect.getY();
        width = effect.getWidth();
        height = effect.getHeight();
        gid  = effect.getGid();
    }

    @Override
    public byte[] toBytes() {
        return CJDeal.ByteArrayConnect(CJDeal.int2byte(EffectType), CJDeal.int2byte(PTime), CJDeal.double2bytes(direct_x), CJDeal.double2bytes(direct_y),
                CJDeal.int2byte(pos_x), CJDeal.int2byte(pos_y), CJDeal.int2byte(width), CJDeal.int2byte(height), CJDeal.int2byte(gid));
    }

    public int getEffectType() {
        return EffectType;
    }

    public int getPos_x() {
        return pos_x;
    }

    public int getPos_y() {
        return pos_y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getPTime() {
        return PTime;
    }

    public double getDirect_x() {
        return direct_x;
    }

    public double getDirect_y() {
        return direct_y;
    }

    public int getGid() {
        return gid;
    }
}
