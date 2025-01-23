package com.suremoon.gametest.real_game_test.my_ui;

import com.suremoon.game.ag_pc_client.show.adapters.unit.UnitSAGetter;
import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.ag_pc_client.ui.DraggableUI;
import com.suremoon.game.door.units_itf.GoodsItf;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.kernel.initer.goods_init.GoodsStatus;
import com.suremoon.game.kernel.initer.state_init.StateInfManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GoodsOnDragUI extends DraggableUI {
    private GoodsItf goods = null;

    public GoodsOnDragUI(AGForm agForm) {
        super(agForm, new Rectangle(0, 0, 100, 100));
        this.setVisible(false);
        this._zIndex = 1;
    }

    public void setGoods(GoodsItf goods) {
        this.goods = goods;
        this.setNeedRedraw(true);
    }

    @Override
    protected void _draw(Graphics cache) {
        cache.setColor(new Color(0, 0, 0, 0));
        cache.fillRect(0, 0, 100, 100);
        if (this.goods == null) return;
        goods.setState(StateInfManager.getSM().productState(GoodsStatus.BAG_INACT));
        goods.setSize(100, 100);
        try {
            UnitSAGetter.getUsag().show(cache, (Unit) goods, new Point(0, 0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean _mouseReleased(MouseEvent e) {
        this.setVisible(false);
        return super._mouseReleased(e);
    }
}
