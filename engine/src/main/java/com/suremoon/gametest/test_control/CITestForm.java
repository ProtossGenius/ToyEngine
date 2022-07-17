package com.suremoon.gametest.test_control;

import com.suremoon.game.ag_pc_client.mkids.MKInpDeal;
import com.suremoon.game.ag_pc_client.show.showable_rect.control_item.GRCtrlItem;
import com.suremoon.game.kernel.data.GameConfig.GameConfiger;

import javax.swing.*;

public class CITestForm extends JFrame {
    protected MKInpDeal mkid;
    public CITestForm(){
        setLayout(null);
        setBounds(100, 100, 200, 200);
        mkid = new MKInpDeal.NullMKID();
        addKeyListener(mkid);
        addMouseListener(mkid);
        addMouseMotionListener(mkid);
        mkid.setParent(this);
        GRCtrlItem ci = new GRCtrlItem();
        GameConfiger.gc.setScreenSize(200, 200);
        mkid.setControlItem(ci);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        CITestForm fm = new CITestForm();
        fm.setVisible(true);
    }

}
