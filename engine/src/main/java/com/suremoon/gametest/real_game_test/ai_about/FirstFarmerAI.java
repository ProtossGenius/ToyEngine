package com.suremoon.gametest.real_game_test.ai_about;

import com.suremoon.game.door.gometry.PointF;
import com.suremoon.game.door.kernel.UnitRemItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.kernel.manager.UnitMgrItf;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;

import java.awt.*;

public class FirstFarmerAI implements UnitRemItf {

    long nextSpeak = 0;

    @Override
    public void underAttack(UnitItf owner, UnitItf unitItf, double v) {
        if(unitItf instanceof PlayerItf){
            PlayerItf player = (PlayerItf)unitItf;
            player.addMessage("ũ��˵��С�ӣ��㾹�Ҵ����ӣ�");
        }
    }

    @Override
    public void doCalc(UnitItf unitItf, WorldItf worldItf, WorldMgrItf worldMgrItf) {
        if(nextSpeak > System.currentTimeMillis()){
            return;
        }
        nextSpeak = System.currentTimeMillis() + 5000;
        UnitMgrItf unitMgr = worldItf.getGameMap().getUnitMgr();
        PointF pos = unitItf.getFootPos();
        unitMgr.unitsDo(new Rectangle(pos.getIntX()-500 ,pos.getIntY()-500, pos.getIntX()+500, pos.getIntY()+500),
                unit->{
            if(unit instanceof PlayerItf){
                PlayerItf player = (PlayerItf) unit;
                player.addMessage("ũ���������������Ī�����ӡ�");
            }
            return false;
        });
    }

    @Override
    public String interactive(String s) {
        return "ũ����欵�˵��Kill me baby!";
    }
}
