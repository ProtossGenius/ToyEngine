package com.suremoon.game.door.netabout.message;

import com.suremoon.game.door.kernel.GameScreenItf;
import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;

import java.awt.*;

public class MsgScreenAsk implements AGMessage {
    public int posX;
    public int posY;
    public MsgScreenAsk(){}
    public MsgScreenAsk(GameScreenItf screen){
        Rectangle rect = screen.getScreenRect();
        posX = rect.x;
        posY = rect.y;
    }

    public MsgScreenAsk(ByteStream bs){
        posX = bs.getInteger();
        posY = bs.getInteger();
    }
    @Override
    public byte[] toBytes() {
        return CJDeal.ByteArrayConnect(CJDeal.int2byte(posX) ,CJDeal.int2byte(posY));
    }
}
