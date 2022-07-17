package com.suremoon.game.door.gometry;

/**
 * TODO: 之后改成多行动等级的连通图。（如，陆行/水行/沙行/岩浆行走等）
 */
public class Connectivity {
    /**
     * 相对标记点的距离
     */
    public int blockPos;
    public int branchFlag;
    /**
     * 可通行性
     */
    public boolean walkable;
    /**
     * 连通块编号
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
