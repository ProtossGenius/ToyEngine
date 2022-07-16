package com.suremoon.game.door.gometry;

/**
 * TODO: Ö®ºó¸Ä³É¶àÐÐ¶¯µÈ¼¶µÄÁ¬Í¨Í¼¡££¨Èç£¬Â½ÐÐ/Ë®ÐÐ/É³ÐÐ/ÑÒ½¬ÐÐ×ßµÈ£©
 */
public class Connectivity {
    /**
     * Ïà¶Ô±ê¼ÇµãµÄ¾àÀë
     */
    public int blockPos;
    public int branchFlag;
    /**
     * ¿ÉÍ¨ÐÐÐÔ
     */
    public boolean walkable;
    /**
     * Á¬Í¨¿é±àºÅ
     */
    public int No;
    public boolean canWalk(){
        return walkable;
    }

    public Connectivity(){}

    public Connectivity(int blockPos, int branchFlag, boolean walkable, int no) {
        this.blockPos = blockPos;
        this.branchFlag = branchFlag;
        this.walkable = walkable;
        No = no;
    }
}
