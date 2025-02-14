package com.suremoon.gametest.real_game_test.my_ui;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.ag_pc_client.ui.DraggableUI;
import com.suremoon.game.ag_pc_client.ui.format.TextInput;
import com.suremoon.game.door.netabout.message.MsgGoods;
import com.suremoon.game.door.observer.FObserverAction;
import com.suremoon.game.door.observer.ObserverEnum;
import com.suremoon.game.door.observer.ObserverMgr;

import java.awt.*;
import java.awt.event.MouseEvent;

public class BagUI extends DraggableUI implements FObserverAction {
    private Color color = Color.BLUE;

    public BagUI(AGForm agForm, GoodsOnDragUI goodsOnDragUI) {
        super(agForm, new Rectangle(0, 0, 1000, 500));
        ObserverMgr.mgr.register(ObserverEnum.GOODS, this);
        this.addChildren(new TextInput(agForm, new Rectangle(0, 0, 500, 500)));
        this.setVisible(false);
    }

    @Override
    protected void _draw(Graphics cache) {
        cache.setColor(color);
        cache.fillRect(0, 0, getWidth(), getHeight());
    }

    @Override
    protected boolean _mousePressed(MouseEvent e) {
        color = Color.RED;
        setNeedRedraw();
        return super._mousePressed(e);
    }

    @Override
    protected boolean _mouseMoved(MouseEvent e) {
        return super._mouseMoved(e);
    }

    @Override
    protected boolean _mouseReleased(MouseEvent e) {
        color = Color.BLUE;
        setNeedRedraw();
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
                this.setNeedRedraw();
                break;
            }

            default -> {

            }
        }
    }
}
