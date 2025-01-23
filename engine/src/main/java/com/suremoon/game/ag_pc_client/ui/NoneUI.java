package com.suremoon.game.ag_pc_client.ui;

import java.awt.*;

public class NoneUI extends IGameUI {
    Point lastPos = new Point();

    public NoneUI() {
        super(null, new Rectangle(-1, -1, 1, 1));
    }

    @Override
    protected void _draw(Graphics cache) {

    }

}
