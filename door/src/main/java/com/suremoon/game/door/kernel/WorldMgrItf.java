package com.suremoon.game.door.kernel;

import com.suremoon.game.door.units_itf.PlayerItf;

public interface WorldMgrItf extends Runnable{
    Status getStatus();
    WorldItf getWorld(String path);
    WorldItf getWorld(int index);

    void setPlayerInitItf(PlayerInitItf initItf);
    PlayerInitItf getPlayerInitItf();

    /**
     * @param player the player want to be init.
     */
    default void InitPlayer(PlayerItf player){
        getPlayerInitItf().InitPlayer(player);
    }

    /**
     * @param run you should make sure run not block. WorldMgr will do nothing for this.
     */
    void setAfterLoadModAction(Runnable run);
}
