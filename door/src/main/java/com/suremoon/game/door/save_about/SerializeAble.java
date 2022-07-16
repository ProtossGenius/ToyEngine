package com.suremoon.game.door.save_about;

import com.suremoon.game.door.tools.ByteStream;

/**
 * ¿ÉÐòÁÐ»¯£¬²»Í¬ÓÚAGMessage£¬Õâ¸öÊÇÍêÈ«ÐòÁÐ»¯£¬ÆÚ´ý¿ÉÒÔÎÞËðµÄ³õÊ¼»¯ËùÊôÀà¡£
 */
public interface SerializeAble {
    void parseFromBytes(ByteStream byteStream);
    byte[] encodeToBytes();
}
