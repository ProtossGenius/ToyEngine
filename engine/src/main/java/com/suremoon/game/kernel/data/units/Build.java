package com.suremoon.game.kernel.data.units;

import com.suremoon.game.door.netabout.message.MsgUnit;

public class Build extends Unit {
    public Build(int uType) {
        super(uType);
    }

    public Build(Unit u, Object move) {
        super(u, move);
    }

    public Build(MsgUnit mu) {
        super(mu);
    }
}
