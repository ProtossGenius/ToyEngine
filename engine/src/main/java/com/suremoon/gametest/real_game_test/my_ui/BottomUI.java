package com.suremoon.gametest.real_game_test.my_ui;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.ag_pc_client.ui.IGameUI;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;

import java.awt.*;

public class BottomUI extends IGameUI {
    public static final int HEIGHT = 100;
    public static final int WIDTH = GameConfiger.DESIGN_SCREEN_WIDTH;

    protected AGForm agForm;

    public BottomUI(AGForm agForm) {
        super(agForm, new Rectangle(0, GameConfiger.DESIGN_SCREEN_HEIGHT - HEIGHT, WIDTH, HEIGHT));

        this.agForm = agForm;
    }

    @Override
    public void addChildren(IGameUI ui) {
        super.addChildren(ui);

    }

    @Override
    protected void _draw(Graphics cache) {
        cache.setColor(Color.GRAY);
        cache.fillRect(100, 100, 100, 100);
    }
}
