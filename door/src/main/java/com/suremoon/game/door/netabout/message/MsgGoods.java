package com.suremoon.game.door.netabout.message;

import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;
import com.suremoon.game.door.units_itf.GoodsItf;

public class MsgGoods implements AGMessage {
    private final int gid; // 唯一ID
    private final int gType // 物品类型，显示用
            ;
    private final long passTime; // 动画用，经过的时间

    public MsgGoods(GoodsItf goodsItf) {
        if (goodsItf == null) {
            this.gid = -1;
            this.gType = -1;
            this.passTime = -1;
            return;
        }
        this.gid = goodsItf.getGid();
        this.gType = goodsItf.getAGType();
        this.passTime = goodsItf.getState().getPassedTime();

    }

    public MsgGoods(ByteStream bs) {
        this.gid = bs.getInteger();
        this.gType = bs.getInteger();
        this.passTime = bs.getLong();
    }

    public MsgGoods(int gid, int gType, long passTime) {
        this.gid = gid;
        this.gType = gType;
        this.passTime = passTime;
    }

    public int getGid() {
        return gid;
    }

    public int getgType() {
        return gType;
    }

    public long getPassTime() {
        return passTime;
    }

    @Override
    public byte[] toBytes() {
        return CJDeal.ByteArrayConnect(
                CJDeal.int2byte(this.gid), CJDeal.int2byte(this.gType), CJDeal.long2bytes(this.passTime)
        );
    }
}
