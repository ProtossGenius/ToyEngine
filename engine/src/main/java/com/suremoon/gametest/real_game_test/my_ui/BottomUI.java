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


public class BottomUI extends IGameUI implements FObserverAction {
    public static final int HEIGHT = 100;
    public static final int WIDTH = GameConfiger.DESIGN_SCREEN_WIDTH;
    private final GoodsCellUI[] calls = new GoodsCellUI[10];
    protected AGForm agForm;

    public BottomUI(AGForm agForm) {
        super(agForm, new Rectangle(0, GameConfiger.DESIGN_SCREEN_HEIGHT - HEIGHT, WIDTH, HEIGHT));

        this.agForm = agForm;
        ObserverMgr.mgr.register(ObserverEnum.GOODS, this);
        for (int i = 0; i < calls.length; ++i) {
            calls[i] = new GoodsCellUI(agForm, new Rectangle(i * 110, 0, 100, 100));
        }
    }


    @Override
    public void addChildren(IGameUI ui) {
        super.addChildren(ui);

    }

    @Override
    protected void _draw(Graphics cache) {
        for (var cell : this.calls) {
            cell.draw(cache);
        }
    }

    @Override
    public void accept(ObserverEnum en, Object obj) {
        switch (en) {
            case ObserverEnum.GOODS -> {
                var goods = (MsgGoods[]) obj;
                for (int i = 0; i < Math.min(goods.length, this.calls.length); ++i) {
                    this.calls[i].setGoods(goods[i]);
                }
                this.setNeedRedraw(true);
                break;
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
}
