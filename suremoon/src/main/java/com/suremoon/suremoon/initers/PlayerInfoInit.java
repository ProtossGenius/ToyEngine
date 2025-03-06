package com.suremoon.suremoon.initers;

import com.suremoon.game.door.kernel.PlayerInitItf;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.gametest.real_game_test.diedos.PlayerDieDo;

import java.awt.*;

public class PlayerInfoInit implements PlayerInitItf {
    @Override
    public void InitPlayer(PlayerItf player) {
        player.setPos(new Point(50, 50));
        player.setDieDo(PlayerDieDo.Instance);
        var attr = player.getAttribute();
        attr.setSpd(500.0);
        attr.setBasicHp(1000);
        attr.setHp(1000);
        attr.setBasicMp(5000);
        attr.setMp(5000);
    }
}
