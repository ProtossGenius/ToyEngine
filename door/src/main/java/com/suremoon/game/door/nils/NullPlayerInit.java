package com.suremoon.game.door.nils;

import com.suremoon.game.door.kernel.PlayerInitItf;
import com.suremoon.game.door.units_itf.PlayerItf;

import java.awt.*;

public class NullPlayerInit implements PlayerInitItf {

    @Override
    public void InitPlayer(PlayerItf player) {
        player.setPos(new Point(5000, 5000));
        player.getAttribute().setSpd(300);
    }
}
