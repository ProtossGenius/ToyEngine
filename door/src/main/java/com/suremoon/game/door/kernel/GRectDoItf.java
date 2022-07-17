package com.suremoon.game.door.kernel;

public interface GRectDoItf {
    // 返回值用于确定是否需要继续计算下一个GRect，如果为false 的话则会直接退出
    boolean ActionReturnShouldContinue(GRectItf gRect);
}

