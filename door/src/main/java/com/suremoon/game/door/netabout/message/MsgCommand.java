package com.suremoon.game.door.netabout.message;

import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;
import com.suremoon.game.door.units_itf.CommandItf;

import java.awt.*;

/**
 * Created by Water Moon on 2017/12/28.
 */
public class MsgCommand implements AGMessage {
    Point point;//命令的辅助点
    int uType, extSize, target;
    byte appendCmd;
    public MsgCommand(){point = new Point();}
    public MsgCommand(CommandItf cmd){
        uType = cmd.getAGType();
        target = cmd.getTarget();
        point = cmd.getTargetPoint();
        appendCmd = cmd.isAppendCmd() ? (byte)1 : (byte)0;
    }
    public MsgCommand(ByteStream bs){
        uType = bs.getInteger();
        extSize = bs.getInteger();
        target = bs.getInteger();
        point = new Point(bs.getInteger(), bs.getInteger());
        appendCmd = bs.getBytes(1)[0];
    }


    public Point getPoint() {
        return point;
    }

    public int getuType() {
        return uType;
    }


    public int getTarget() {
        return target;
    }

    public boolean getAppendCmd(){return appendCmd == 1;}

    @Override
    public byte[] toBytes() {
        return CJDeal.ByteArrayConnect(CJDeal.int2byte(uType), CJDeal.int2byte(extSize), CJDeal.int2byte(target),
                CJDeal.int2byte(point.x), CJDeal.int2byte(point.y), new byte[]{appendCmd});
    }
}
