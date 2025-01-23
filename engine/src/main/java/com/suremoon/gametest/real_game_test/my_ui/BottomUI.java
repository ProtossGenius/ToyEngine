package com.suremoon.gametest.real_game_test.my_ui;

import com.suremoon.game.ag_pc_client.show.pc_show.AGForm;
import com.suremoon.game.ag_pc_client.ui.IGameUI;
import com.suremoon.game.door.netabout.message.MsgGoods;
import com.suremoon.game.door.observer.FObserverAction;
import com.suremoon.game.door.observer.ObserverEnum;
import com.suremoon.game.door.observer.ObserverMgr;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;
import com.suremoon.game.kernel.initer.cmd_init.CmdInfManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class BottomUI extends IGameUI implements FObserverAction {
    public static final int HEIGHT = 100;
    public static final int WIDTH = GameConfiger.DESIGN_SCREEN_WIDTH;
    private final GoodsCellUI[] cells = new GoodsCellUI[10];
    private Integer selectedCell = 0;

    public BottomUI(AGForm agForm) {
        super(agForm, new Rectangle(0, GameConfiger.DESIGN_SCREEN_HEIGHT - HEIGHT, WIDTH, HEIGHT));

        ObserverMgr.mgr.register(ObserverEnum.GOODS, this);
        for (int i = 0; i < cells.length; ++i) {
            cells[i] = new GoodsCellUI(agForm, new Rectangle(i * 110, 0, 100, 100));
            addChildren(cells[i]);
        }
    }

    @Override
    protected void _draw(Graphics cache) {
        for (int i = 0; i < this.cells.length; ++i) {
            var cell = this.cells[i];
            cell.setSelected(i == this.selectedCell);
            cell.draw(cache);
        }
    }

    @Override
    public void accept(ObserverEnum en, Object obj) {
        switch (en) {
            case ObserverEnum.GOODS -> {
                var goods = (MsgGoods[]) obj;
                for (int i = 0; i < Math.min(goods.length, this.cells.length); ++i) {
                    this.cells[i].setGoods(goods[i]);
                }
                this.setNeedRedraw(true);
            }
            case ObserverEnum.SELECT_GOODS -> {
                var i = (Integer) obj;
                this.selectedCell = i;
            }
            default -> {

            }
        }
    }

    @Override
    protected boolean _keyPressed(KeyEvent e) {
        if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9') {
            var cmd = CmdInfManager.CIM.productCommand(IDManager.getID("CmdUseGoods"), null, (e.getKeyChar() + 10 - '1') % 10);
            CmdInfManager.CIM.getOnCmd().accept(cmd);
            return true;
        }
        return super._keyPressed(e);
    }

    @Override
    protected boolean _mouseDragged(MouseEvent e) {
        return super._mouseDragged(e);
    }
}
