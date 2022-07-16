package com.suremoon.game.door.infos;

import com.suremoon.game.door.kernel.CmdActionItf;

/**
 * Created by Water Moon on 2018/4/19.
 */
public class CmdInformation {
    CmdActionItf ca;
    public CmdInformation(CmdActionItf ca, int type) {
        this.ca = ca;
    }

    public CmdActionItf getCmdAction() {
        return ca;
    }
}
