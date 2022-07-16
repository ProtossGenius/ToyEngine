package com.suremoon.game.door.server;

import com.suremoon.game.door.kernel.WorldMgrItf;

public interface ServerItf {
    /**
     * Õâ¸ö·½·¨Ó¦¸ÃÊÇ×èÈûµÄ
     * @param port  ¶Ë¿Ú
     * @param worldMgr ÊÀ½ç¹ÜÀíÆ÷
     */
    void run(int port, WorldMgrItf worldMgr);
}
