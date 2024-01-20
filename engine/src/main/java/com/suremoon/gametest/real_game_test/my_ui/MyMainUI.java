package com.suremoon.gametest.real_game_test.my_ui;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.ag_pc_client.ui.MainUI;

public class MyMainUI extends MainUI {
    public MyMainUI(AGForm agForm) {
        super(agForm);
        addChildren(new BottomUI(agForm));
        addChildren(new BagUI(agForm));
    }
}
