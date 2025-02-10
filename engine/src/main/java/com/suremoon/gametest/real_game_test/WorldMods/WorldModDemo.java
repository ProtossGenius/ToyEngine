package com.suremoon.gametest.real_game_test.WorldMods;

import com.suremoon.game.door.kernel.GoodsUseDetail;
import com.suremoon.game.door.kernel.UnitRemItf;
import com.suremoon.game.door.kernel.WorldItf;
import com.suremoon.game.door.kernel.WorldMgrItf;
import com.suremoon.game.door.mods.WorldModItf;
import com.suremoon.game.door.tools.IDManager;
import com.suremoon.game.door.units_itf.EffectItf;
import com.suremoon.game.door.units_itf.GoodsItf;
import com.suremoon.game.door.units_itf.PlayerItf;
import com.suremoon.game.door.units_itf.UnitItf;
import com.suremoon.game.netabout.MessageUtil;
import com.suremoon.gametest.real_game_test.ai_about.FirstFarmerAI;
import com.suremoon.gametest.real_game_test.diedos.MonsterDieDo;

public class WorldModDemo implements WorldModItf {
    @Override
    public void Do(WorldItf gr) {
        UnitItf farmer = gr.productUnit(IDManager.getID("T_lumberjack"));
        farmer.setPos(5000, 5000);
        farmer.getAttribute().setSpd(150);
        farmer.setDieDo(MonsterDieDo.Instance);
        farmer.setUnitRem(new FirstFarmerAI());
        //        farmer.addCmd(CmdInfManager.CIM.productCommand(IDManager.getID("CmdAttack"), farmer,
        // new Point(100, 100), null, gr.getGm()));
        //        farmer.addCmd(CmdInfManager.CIM.productCommand(IDManager.getID("CmdWalk"), farmer, new
        // Point(500, 500), null, gr.getGm()));
        //        farmer.addCmd(CmdInfManager.CIM.productCommand(IDManager.getID("CmdWalk"), farmer, new
        // Point(5000, 5000), null, gr.getGm()));
        gr.getGameMap().getUnitMgr().addUnit(farmer);

        UnitItf goods = gr.productGoods(IDManager.getID("goods/axe"));
        goods.setPos(5300, 5300);
        goods.setSize(80, 80);
        goods.setShowName("this is !");
        goods.getAttribute().setHp(1000000000);
        goods.setUnitRem(new UnitRemItf() {
            @Override
            public void underAttack(UnitItf owner, UnitItf attacker, double hurt) {
            }

            @Override
            public void doCalc(UnitItf unit, WorldItf world, WorldMgrItf worldMgr) {

            }

            @Override
            public void interactive(UnitItf self, PlayerItf playerItf, WorldItf world, WorldMgrItf worldMgr, Object input) {
                // 拾取
                if ("".equals(input)) {
                    playerItf.getBagManager().pick((GoodsItf) self, world);
                    playerItf.addMessage(MessageUtil.userChatMessage("get item: " + self.getShowName()));
                    self.setDrop(true);
                    return;
                }

                var use = (GoodsUseDetail) input;
                switch (use.getKey()) {
                    case 'q' -> {
                        self.setPutPos(use.getTargetPoint());
                        var bag = playerItf.getBag();
                        bag.set(use.getSelectedIndex(), null);
                        world.addCalcUnit(self);
                    }
                }
                playerItf.addMessage(MessageUtil.userChatMessage("use goods ..." + input));
            }
        });
        System.out.println(goods.getDirect());
        gr.getGameMap().getUnitMgr().addUnit(goods);


        EffectItf e = gr.productEffect(IDManager.getID("LightingStrike"), farmer);
        e.setPos(5500, 5500);
        gr.getGameMap().getEffectMgr().addEffect(e);
        e = gr.productEffect(IDManager.getID("heal"), farmer);
        e.setPos(4500, 4500);
        gr.getGameMap().getEffectMgr().addEffect(e);
    }

    @Override
    public String ModName() {
        return "WorldModDemo";
    }
}
