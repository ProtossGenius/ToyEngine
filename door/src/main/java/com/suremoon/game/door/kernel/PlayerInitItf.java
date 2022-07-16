package com.suremoon.game.door.kernel;

import com.suremoon.game.door.nils.NullPlayerInit;
import com.suremoon.game.door.units_itf.PlayerItf;

/**
 * The Interface about how to init an existed player.
 */
public interface PlayerInitItf {
    void InitPlayer(PlayerItf player);
    PlayerInitItf Default = new NullPlayerInit();
}
