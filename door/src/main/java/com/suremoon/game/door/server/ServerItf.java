package com.suremoon.game.door.server;

import com.suremoon.game.door.kernel.WorldMgrItf;

public interface ServerItf {
    /**
     * 这个方法应该是阻塞的
     * @param port  端口
     * @param worldMgr 世界管理器
     */
    void run(int port, WorldMgrItf worldMgr);
}
