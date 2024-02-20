package com.suremoon.game.door.netabout.message;

import com.suremoon.game.door.netabout.AGMessage;

public class MsgGoods implements AGMessage {
    private int gid, // 唯一ID
            gType, // 物品类型，显示用
            passTime, // 动画用，经过的时间
            cd, // 使用CD， ms
            nextMs; // 下次可用的毫秒数

    public MsgGoods() {
    }

    public MsgGoods(int gid, int gType, int passTime, int cd, int nextMs) {
        this.gid = gid;
        this.gType = gType;
        this.passTime = passTime;
        this.cd = cd;
        this.nextMs = nextMs;
    }

    public int getGid() {
        return gid;
    }

    public int getgType() {
        return gType;
    }

    public int getPassTime() {
        return passTime;
    }

    public int getCd() {
        return cd;
    }

    public int getNextMs() {
        return nextMs;
    }

    @Override
    public byte[] toBytes() {
        return new byte[0];
    }
}
