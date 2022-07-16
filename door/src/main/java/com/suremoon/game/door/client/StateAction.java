package com.suremoon.game.door.client;

/**
 * StateAction Ä¿Ç°Ö÷ÒªÓÃÓÚ¼¼ÄÜµÄÖ¸Ê¾Æ÷£¬ÔÚÄ³Ð©°´¼üµÄÇé¿öÏÂ³ö·¢¼¼ÄÜÖ¸Ê¾Æ÷¡£
 * ½«À´¿ÉÄÜÓÃÓÚÆäËüµÄ²¿·Ö¡£
 */
public class StateAction {
    public static final int NOTHING = 0, POINTER = 1;
    public static final int POINTER_LINE = 11, POINTER_CIRCLE = 12, POINTER_AREA = 13;
    public int width;
    public int sa_type = NOTHING, sa_showType;
}
