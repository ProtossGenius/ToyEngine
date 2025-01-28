package com.suremoon.gametest.real_game_test.my_ui;

import com.suremoon.game.ag_pc_client.show.adapters.unit.UnitSAGetter;
import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.ag_pc_client.ui.IGameUI;
import com.suremoon.game.door.netabout.message.MsgGoods;
import com.suremoon.game.door.units_itf.GoodsItf;
import com.suremoon.game.kernel.data.units.Goods;
import com.suremoon.game.kernel.data.units.Unit;
import com.suremoon.game.kernel.initer.goods_init.GoodsStatus;
import com.suremoon.game.kernel.initer.state_init.StateInfManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class GoodsCellUI extends IGameUI {
    private GoodsItf goods = null;
    private boolean selected = false;
    private boolean inDrag = false;
    private GoodsOnDragUI goodsOnDragUI;
    private Integer goodsIndex = 0;

    public GoodsCellUI(AGForm agForm, Rectangle _bundle, GoodsOnDragUI goodsOnDragUI, Integer goodsIndex) {
        super(agForm, _bundle);
        this.goodsOnDragUI = goodsOnDragUI;
        this.goodsIndex = goodsIndex;
    }

    public void setGoods(MsgGoods goods) {
        this.setNeedRedraw();
        if (goods.getGid() == -1) {
            this.goods = null;
            return;
        }
        this.goods = new Goods(goods.getgType());
        this.goods.setState(StateInfManager.getSM().productState(GoodsStatus.BAG_INACT));
    }

    @Override
    protected void _draw(Graphics cache) {
        cache.setColor(Color.WHITE);
        cache.fillRect(0, 0, 100, 100);
        if (this.inDrag) {
            return;
        }
        if (this.goods == null) return;
        goods.setState(StateInfManager.getSM().productState(this.selected ? GoodsStatus.BAG_ACT : GoodsStatus.BAG_INACT));
        goods.setSize(100, 100);
        try {
            UnitSAGetter.getUsag().show(cache, (Unit) goods, new Point(0, 0));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected boolean _mouseDragged(MouseEvent e) {
        if (this.goods == null) {
            return true;
        }
        var p = e.getPoint();
        goodsOnDragUI.setPosition(p.x - 50, p.y - 50);
        goodsOnDragUI.setGoods(this.goods, this.goodsIndex);
        goodsOnDragUI.setVisible(true);
        return true;
    }

    @Override
    protected boolean _mouseMoved(MouseEvent e) {
        goodsOnDragUI.setReleaseIndex(this.goodsIndex);
        return false;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
