package com.suremoon.gametest.real_game_test.my_ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.ag_pc_client.ui.DragableUI;
import com.suremoon.game.door.netabout.message.MsgGoods;
import com.suremoon.game.door.observer.FObserverAction;
import com.suremoon.game.door.observer.ObserverEnum;
import com.suremoon.game.door.observer.ObserverMgr;

public class BagUI extends DragableUI implements FObserverAction {
    private Color color = Color.BLUE;

    public BagUI(AGForm agForm) {
        super(agForm, new Rectangle(0, 0, 500, 500));
        ObserverMgr.mgr.register(ObserverEnum.GOODS, this);
    }

    @Override
    protected void _draw(Graphics cache) {
        cache.setColor(color);
        cache.fillRect(0, 0, _bundle.width, _bundle.height);
    }

    @Override
    protected boolean _mousePressed(MouseEvent e) {
        color = Color.RED;
        setNeedRedraw(true);
        return super._mousePressed(e);
    }

    @Override
    protected boolean _mouseMoved(MouseEvent e) {
        return super._mouseMoved(e);
    }

    @Override
    protected boolean _mouseReleased(MouseEvent e) {
        color = Color.BLUE;
        setNeedRedraw(true);
        return super._mouseReleased(e);
    }

    @Override
    public void accept(ObserverEnum en, Object obj) {
        switch (en) {
            case ObserverEnum.GOODS -> {
                var goods = (MsgGoods[]) obj;
//                for (int i = 10; i < Math.min(goods.length, this.calls.length); ++i) {
//                    this.calls[i].setGoods(goods[i]);
//                }
                this.setNeedRedraw(true);
                break;
            }

            default -> {

            }
        }
    }
}
