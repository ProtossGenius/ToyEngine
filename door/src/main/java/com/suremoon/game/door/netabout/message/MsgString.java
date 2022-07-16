package com.suremoon.game.door.netabout.message;

import com.suremoon.game.door.netabout.AGMessage;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.tools.CJDeal;

import java.io.UnsupportedEncodingException;

public class MsgString implements AGMessage {
    String str;
    public MsgString(String str){
        this.str = str;
    }
    public MsgString(ByteStream bs){
        str = bs.getString();
    }

    public String getStr(){
        return str;
    }

    @Override
    public byte[] toBytes() {
        return CJDeal.string2bytes(str);
    }
}
