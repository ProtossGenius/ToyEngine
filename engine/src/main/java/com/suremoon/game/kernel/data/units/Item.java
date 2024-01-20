package com.suremoon.game.kernel.data.units;

import com.suremoon.game.door.netabout.message.MsgUnit;

public class Item extends Unit {
    public Item(int uType) {
        super(uType);
    }

    public Item(Unit u, Object move) {
        super(u, move);
    }

    public Item(MsgUnit mu) {
        super(mu);
    }
}
