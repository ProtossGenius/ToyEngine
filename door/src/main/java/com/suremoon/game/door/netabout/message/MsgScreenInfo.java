package com.suremoon.game.door.netabout.message;

import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;

import java.util.Arrays;

public class MsgScreenInfo implements AGMessage {
    private final AGMessage[][] info;
    private int WorldIndex;

    public MsgScreenInfo() {
        this.info = new AGMessage[5][];
        Arrays.fill(info, new AGMessage[0]);
    }

    public MsgScreenInfo(int worldIndex, AGMessage[][] res) {
        this.WorldIndex = worldIndex;
        this.info = res;
    }

    public MsgScreenInfo(ByteStream bs) {
        this.info = new AGMessage[5][];

        WorldIndex = bs.getInteger();
        info[0] = new AGMessage[]{new MsgUnit(bs)};
        int length = bs.getInteger();
        info[1] = new AGMessage[length];
        for (int i = 0; i < length; ++i) {
            info[1][i] = new MsgUnit(bs);
        }

        length = bs.getInteger();
        info[2] = new AGMessage[length];
        for (int i = 0; i < length; ++i) {
            info[2][i] = new MsgEffect(bs);
        }
        length = bs.getInteger();
        info[3] = new AGMessage[length];
        for (int i = 0; i < length; ++i) {
            info[3][i] = new MsgString(bs);
        }

        length = bs.getInteger();
        info[4] = new AGMessage[length];
        for (int i = 0; i < length; ++i) {
            info[4][i] = new MsgGoods(bs);
        }
    }

    public int getWorldIndex() {
        return WorldIndex;
    }

    public synchronized AGMessage[][] getShowInfo() {
        return info;
    }

    @Override
    public byte[] toBytes() {
        byte[][] bytes = new byte[6 + info[1].length + info[2].length + info[3].length + info[4].length][];
        int index = 0;
        bytes[index++] = CJDeal.int2byte(WorldIndex);
        bytes[index++] = info[0][0].toBytes();
        bytes[index++] = CJDeal.int2byte(info[1].length);
        for (int i = 0; i < info[1].length; ++i) {
            bytes[index++] = info[1][i].toBytes();
        }
        bytes[index++] = CJDeal.int2byte(info[2].length);
        for (int i = 0; i < info[2].length; ++i) {
            bytes[index++] = info[2][i].toBytes();
        }
        bytes[index++] = CJDeal.int2byte(info[3].length);
        for (int i = 0; i < info[3].length; ++i) {
            bytes[index++] = info[3][i].toBytes();
        }
        bytes[index++] = CJDeal.int2byte(info[4].length);
        for (int i = 0; i < info[4].length; ++i) {
            bytes[index++] = info[4][i].toBytes();
        }
        return CJDeal.ByteArrayConnect(bytes);
    }
}
