package com.suremoon.gametest.real_game_test.my_ui;

import com.suremoon.game.ag_pc_client.show.adapters.unit.UnitSAGetter;
import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.ag_pc_client.ui.DraggableUI;
import com.suremoon.game.door.netabout.message.MsgGoods;
import com.suremoon.game.door.units_itf.GoodsItf;
import com.suremoon.game.kernel.data.units.Goods;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.kernel.initer.goods_init.GoodsStatus;
import com.suremoon.game.kernel.initer.state_init.StateInfManager;

import java.awt.*;

public class GoodsInCellUI extends DraggableUI {
    private GoodsItf goods = null;
    private boolean selected = false;

    public GoodsInCellUI(AGForm agForm, Rectangle _bundle) {
        super(agForm, _bundle);
    }

    public void setGoods(MsgGoods goods) {
        if (goods.getGid() == -1) {
            this.goods = null;
            return;
        }
        this.goods = new Goods(goods.getgType());
        this.goods.setState(StateInfManager.getSM().productState(GoodsStatus.BAG_INACT));
        this.setNeedRedraw(true);
    }

    @Override
    protected void _draw(Graphics cache) {
        cache.setColor(Color.WHITE);
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


    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
