package com.suremoon.game.door.units_itf;

import com.suremoon.game.door.kernel.GameScreenItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.netabout.message.MsgCommand;
import com.suremoon.game.door.netabout.message.MsgString;

public interface PlayerItf extends UnitItf{
    GameScreenItf getScreen();

    WorldItf getWorld();

    void acceptCmd(MsgCommand cmd);

    MsgString[] getMessages(int maxSize);

    void addMessage(String msg);
}
