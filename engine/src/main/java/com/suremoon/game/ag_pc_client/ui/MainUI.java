package com.suremoon.game.ag_pc_client.ui;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;

import java.awt.*;

public class MainUI extends IGameUI {
    public MainUI(AGForm agForm) {
        super(agForm, new Rectangle(0, 0, GameConfiger.DESIGN_SCREEN_WIDTH, GameConfiger.DESIGN_SCREEN_HEIGHT), false);
        setAlwaysRedraw(true);
    }

    @Override
    protected void _draw(Graphics cache) {

    }
}
