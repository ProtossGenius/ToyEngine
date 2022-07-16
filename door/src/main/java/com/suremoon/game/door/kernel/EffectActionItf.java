package com.suremoon.game.door.kernel;

import com.suremoon.game.door.save_about.SerializeAble;
import com.suremoon.game.door.tools.ByteStream;
import com.suremoon.game.door.units_itf.EffectItf;

/**
 * Created by Water Moon on 2018/4/18.
 * £¨Ä§·¨£©Ð§¹û¶¯×÷
 */
public interface EffectActionItf extends SerializeAble {
    /**
     * @param effect Ä§·¨Ð§¹û
     * @param world ËùÊôÊÀ½ç
     * @param wm    ÊÀ½ç¹ÜÀíÆ÷
     */
    void effectDo(EffectItf effect, WorldItf world, WorldMgrItf wm);

    /**
     * only for prevent lambda.
     */
    void preventLambda();
    EffectActionItf Null = new NullEffectActionItf();

    @Override
    default void parseFromBytes(ByteStream byteStream){}

    @Override
    default byte[] encodeToBytes(){return new byte[0];}
}

class NullEffectActionItf implements EffectActionItf {

    @Override
    public void effectDo(EffectItf effect, WorldItf world, WorldMgrItf wm) {
    }

    @Override
    public void preventLambda() { }
}